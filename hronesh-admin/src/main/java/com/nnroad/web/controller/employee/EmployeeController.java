package com.nnroad.web.controller.employee;
import com.alibaba.excel.EasyExcel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.domain.entity.SysDictData;
import com.nnroad.common.utils.*;
import com.nnroad.common.utils.excel.ExcelCellWriteHandler;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.employee.constants.enums.EEStatus;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
import com.nnroad.framework.web.azureFileShares.*;
import com.nnroad.system.service.ISysDictDataService;
import com.nnroad.web.controller.common.CommonController;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * Employee Controller
 * 
 * This controller handles requests related to employee management, including
 * listing, exporting, adding, editing, and deleting employee records.
 * 
 * @author nnroad
 * @date 2024-10-11
 */
@RestController
@RequestMapping("/system/employee")
public class EmployeeController extends BaseController
{

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private ISysEmployeeService sysEmployeeService;

    @Autowired
    private ISysClientService sysClientService;


    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private AzureFileShareService azureFileShareService;

    @Autowired
    private ISysExtraAttributeService extraAttributeService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Value("${sys.company-name}")
    private String companyName;


    /**
     * Retrieve a list of employees.
     * 
     * @param sysEmployee Filters to apply to the employee list
     * @return A paginated list of employees
     */
    @PreAuthorize("@ss.hasPermi('employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysEmployee sysEmployee)
    {
        startPage();
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('employee:import')")
    @PostMapping("/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file)  {
        sysEmployeeService.importEmployeeExcel(file);
        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasPermi('employee:import')")
    @GetMapping("/download_temp")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        //get all extra attributes
        List<SysExtraAttribute> basicAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("sys_employee", null);
        List<SysExtraAttribute> contractAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_contract", null);
        List<SysExtraAttribute> crmAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_payroll_crm", null);
        List<SysExtraAttribute> dcAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_payroll_dc", null);
        List<SysExtraAttribute> offBoardAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_off_board_info", null);
        List<SysExtraAttribute> allAttributes = Stream.of(basicAttributeList, contractAttributeList, crmAttributeList, dcAttributeList, offBoardAttributeList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        List<List<String>> headers = new ArrayList<>();
        headers.addAll(Arrays.asList(
                Arrays.asList("Company Code"),
                Arrays.asList("Employee Code"),
                Arrays.asList("Local Name"),
                Arrays.asList("Employee Name"),
                Arrays.asList("Status"),
                Arrays.asList("ID Type"),
                Arrays.asList("ID Number"),
                Arrays.asList("国籍/国家名称"),
                Arrays.asList("性别"),
                Arrays.asList("Email"),
                Arrays.asList("Mobile"),
                Arrays.asList("Organization"),
                Arrays.asList("合同起始日期"),
                Arrays.asList("合同终止日期")
        ));
        // Generate the extra headers
        List<List<String>> generatedHeaders = generateHeaders(allAttributes);
        headers.addAll(generatedHeaders);
        // Define file name for the Excel file
        String fileName = "employee_info.xlsx";

        // Set the response header for Excel file download
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        List<Object> data = null;

        // Write data to Excel using EasyExcel
        EasyExcel.write(response.getOutputStream())
                .head(headers)
                .sheet(fileName) // Sheet name
                .doWrite(data); // Data to write
    }



    /**
     * Export the list of employees.
     * 
     * @param response The HTTP response to write the Excel file to
     * @param sysEmployee Filters to apply to the employee list
     */
//    @PreAuthorize("@ss.hasPermi('employee:export')")
    @Anonymous
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEmployee sysEmployee) throws IOException {

        //get all extra attributes
        List<SysExtraAttribute> basicAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("sys_employee", null);
        List<SysExtraAttribute> contractAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_contract", null);
        List<SysExtraAttribute> crmAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_payroll_crm", null);
        List<SysExtraAttribute> dcAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_payroll_dc", null);
        List<SysExtraAttribute> offBoardAttributeList = extraAttributeService.selectSysExtraAttributeInHierarchy("employee_off_board_info", null);

        //get all associated dicts
        List<SysDictData> dictDataList = dictDataService.selectByDictTypes(Arrays.asList("id_type","country","employee_status","employee_sex","organization"));
        Map<String, List<SysDictData>> basicDictList = extraAttributeService.getDicts("sys_employee");
        Map<String, List<SysDictData>> contractDictList = extraAttributeService.getDicts("employee_contract");
        Map<String, List<SysDictData>> payrollCrmDictList = extraAttributeService.getDicts("employee_payroll_crm");
        Map<String, List<SysDictData>> payrollDcDictList = extraAttributeService.getDicts("employee_payroll_dc");
        Map<String, List<SysDictData>> offBoardDictList = extraAttributeService.getDicts("employee_off_board_info");

        //Create new maps where the key is the ID of the SysExtraAttribute and the value is a Map of dictValue to dictLabel.
        Map<String, Map<String, String>> dictDataMap = dictDataList.stream().collect(Collectors.groupingBy(SysDictData::getDictType, Collectors.toMap(SysDictData::getDictValue, SysDictData::getDictLabel)));
        Map<String, Map<String, String>> basicDictMap = createDictMap(basicAttributeList, basicDictList);
        Map<String, Map<String, String>> contractDictMap = createDictMap(contractAttributeList, contractDictList);
        Map<String, Map<String, String>> payrollCrmDictMap = createDictMap(crmAttributeList, payrollCrmDictList);
        Map<String, Map<String, String>> payrollDcDictMap = createDictMap(dcAttributeList, payrollDcDictList);
        Map<String, Map<String, String>> offBoardDictMap = createDictMap(offBoardAttributeList, offBoardDictList);

        List<SysExtraAttribute> allAttributes = Stream.of(basicAttributeList, contractAttributeList, crmAttributeList, dcAttributeList, offBoardAttributeList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        List<List<String>> headers = new ArrayList<>();
        headers.addAll(Arrays.asList(
                Arrays.asList("Company Name"),
                Arrays.asList("Company Code"),
                Arrays.asList("Employee Code"),
                Arrays.asList("Local Name"),
                Arrays.asList("Employee Name"),
                Arrays.asList("Status"),
                Arrays.asList("ID Type"),
                Arrays.asList("ID Number"),
                Arrays.asList("国籍/国家名称"),
                Arrays.asList("性别"),
                Arrays.asList("Email"),
                Arrays.asList("Mobile"),
                Arrays.asList("Organization"),
                Arrays.asList("合同起始日期"),
                Arrays.asList("合同终止日期")
        ));
        // Generate the extra headers
        List<List<String>> generatedHeaders = generateHeaders(allAttributes);
        headers.addAll(generatedHeaders);


        List<SysEmployee> employeeList = sysEmployeeService.selectEmployeeAllInfoList(sysEmployee);
        List<Object> data = employeeList.stream()
                .map(employee -> {
                    // Create a List of employee fields (the row data for each employee)
                    List<Object> row = new ArrayList<>();
                    row.add(employee.getClientName());
                    row.add(employee.getCompanyCode());
                    row.add(employee.getEmployeeCode());
                    row.add(employee.getLocalName());
                    row.add(employee.getEmployeeName());
                    row.add(employee.getStatus());
                    row.add(dictDataMap.get("id_type").get(employee.getIdType()));
                    row.add(employee.getIdNumber());
                    row.add(dictDataMap.getOrDefault("country", new HashMap<>()).getOrDefault(employee.getNationality(), employee.getNationality()));
                    row.add(dictDataMap.get("employee_sex").get(employee.getSex()));
                    row.add(employee.getEmployeeEmail());
                    row.add(employee.getEmployeePhone());
                    row.add(employee.getOrg());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    row.add(employee.getContractStartDate() != null ? dateFormat.format(employee.getContractStartDate()) : null);
                    row.add(employee.getContractEndDate() != null ? dateFormat.format(employee.getContractEndDate()) : null);


                    // Cast the nested JSON data (extraData) to a Map and flatten it
                    //sort the flattened data by the attribute's order defined in the sys_extra_attribute
                    if (employee.getExtraData() != null && employee.getExtraData() instanceof Map) {
                        Map<String, Object> extraDataMap = flattenMap((Map<String, Object>) employee.getExtraData());
                        row.addAll(sortData(extraDataMap, basicAttributeList, basicDictMap));
                    }
                    if (employee.getContractData() != null && employee.getContractData() instanceof Map) {
                        Map<String, Object> extraDataMap = (Map<String, Object>) employee.getContractData();
                        row.addAll(sortData(extraDataMap, contractAttributeList, contractDictMap));
                    }
                    if (employee.getPayrollCrmData() != null && employee.getPayrollCrmData() instanceof Map) {
                        Map<String, Object> extraDataMap = (Map<String, Object>) employee.getPayrollCrmData();
                        row.addAll(sortData(extraDataMap, crmAttributeList, payrollCrmDictMap));
                    }
                    if (employee.getPayrollDcData() != null && employee.getPayrollDcData() instanceof Map) {
                        Map<String, Object> extraDataMap = (Map<String, Object>) employee.getPayrollDcData();
                        row.addAll(sortData(extraDataMap, dcAttributeList, payrollDcDictMap));
                    }
                    if (employee.getOffBoardInfoData() != null && employee.getOffBoardInfoData() instanceof Map) {
                        Map<String, Object> extraDataMap = (Map<String, Object>) employee.getOffBoardInfoData();
                        row.addAll(sortData(extraDataMap, offBoardAttributeList, offBoardDictMap));
                    }
                    return row;
                })
                .collect(Collectors.toList());

        // Define file name for the Excel file
        String fileName = "employee_info_" + companyName + ".xlsx";

        // Set the response header for Excel file download
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        EasyExcel.write(response.getOutputStream())
                .head(headers)
                .sheet(fileName) // Sheet name
                .registerWriteHandler(new ExcelCellWriteHandler())
                .doWrite(data); //
    }

    private Map<String, Object> flattenMap(Map<String, Object> map) {
        Map<String, Object> flattenedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                // Recursively flatten the nested map
                flattenedMap.putAll(flattenMap((Map<String, Object>) value));
            } else {
                // Only add non-null values to the flattened map
                flattenedMap.put(key, value);
            }
        }

        return flattenedMap;
    }

    private List<Object> sortData(Map<String, Object> extraDataMap, List<SysExtraAttribute> extraAttributeList, Map<String, Map<String, String>> valueToLabelMap) {
        Map<Long, Integer> attributeOrderMap = new HashMap<>();

        for (SysExtraAttribute attribute : extraAttributeList) {
            if (!attribute.getChildren().isEmpty()) {
                for (SysExtraAttribute childAttribute: attribute.getChildren()) {
                    Long childAttributeId = childAttribute.getId();
                    // If the attribute is missing from extraDataMap, insert it with a default value (e.g., null or a placeholder)
                    extraDataMap.putIfAbsent(childAttributeId.toString(), null);
                    attributeOrderMap.put(childAttribute.getId(), childAttribute.getSortOrder());
                }
            }
            else {
                Long attributeId = attribute.getId();
                // If the attribute is missing from extraDataMap, insert it with a default value (e.g., null or a placeholder)
                extraDataMap.putIfAbsent(attributeId.toString(), null);  // Default empty value or use a placeholder
                attributeOrderMap.put(attribute.getId(), attribute.getSortOrder());
            }
        }
        //Sort the extraDataMap based on the order defined in extraAttributeList
        List<Object> sortedDataList = extraDataMap.entrySet().stream()
                // filter out entries whose key is not present in the attributeOrderMap
                .filter(entry -> attributeOrderMap.containsKey(Long.parseLong(entry.getKey())))
                .sorted((entry1, entry2) -> {
                    Integer order1 = attributeOrderMap.get(Long.parseLong(entry1.getKey()));
                    Integer order2 = attributeOrderMap.get(Long.parseLong(entry2.getKey()));
                    // sort the entry by order first
                    int orderComparison = (order1 != null ? order1 : Integer.MAX_VALUE) - (order2 != null ? order2 : Integer.MAX_VALUE);
                    return orderComparison;
                })
                .map(entry -> {

                    String value = String.valueOf(entry.getValue());
                    String key = (String) entry.getKey();
                    // map dict value to dict label
                    String label = null;
                    if (valueToLabelMap.get(key) != null && value != null) {
                        label = valueToLabelMap.get(key).get(value);
                    }
                    // Return label if found, otherwise return the value itself
                    value = label != null ? label : value;
                    return value != null && !value.equals("null")  ? value : "";
                })
                .collect(Collectors.toList());
        return sortedDataList;
    }

    /**
     * Get detailed information about a specific employee.
     * 
     * @param id The ID of the employee to retrieve
     * @return The employee's details
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:edit')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysEmployeeService.selectSysEmployeeById(id));
    }

//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:sync:data')")
    @GetMapping("/sync-data")
    public AjaxResult getEmployeeSyncData(@RequestParam String org, @RequestParam String code)
    {
        return success(sysEmployeeService.getEmployeeSyncData(org, code));
    }

//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:sync:data')")
    @Log(title = "Employee", businessType = BusinessType.INSERT)
    @PostMapping("/sync-data")
    public AjaxResult updateEmployeeBySync(@RequestBody SysEmployee sysEmployee)
    {
        //check if the employee exists
        SysEmployee employee = sysEmployeeService.selectEmployeeByCode(sysEmployee.getEmployeeCode());
        // update the employee if exists
        if (employee != null) {
            //get the client from the current system and update the associated client ID for the employee.
            SysClient client = sysClientService.selectBasicByClientCode(sysEmployee.getCompanyCode());
            if (client == null) {
                return AjaxResult.error("Please sync the associated client first.");
            }
            sysEmployee.setClientId(client.getId());
            return toAjax(sysEmployeeService.updateSysEmployeeBySync(sysEmployee));
        }
        // add the employee if not exists
        else{
            SysClient client = sysClientService.selectBasicByClientCode(sysEmployee.getCompanyCode());
            if (client == null) {
                return AjaxResult.error("Please sync the associated client first.");
            }
            sysEmployee.setClientId(client.getId());
            return toAjax(sysEmployeeService.insertSysEmployee(sysEmployee));
        }
    }

    @Anonymous
    //    @PreAuthorize("@ss.hasPermi('employee:query')")
    @GetMapping(value = "/uuid/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(sysEmployeeService.selectSysEmployeeByUUID(uuid));
    }

    /**
     * Add a new employee.
     * 
     * @param sysEmployee The employee information to add
     * @return The result of the add operation
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:add')")
    @Log(title = "Employee", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEmployee sysEmployee)
    {
        SysClient c = sysClientService.selectSysClientById(sysEmployee.getClientId());
        sysEmployee.setCompanyCode(c.getCompanyCode());
        // 没有录入员工编号的通过clientCode生成最新的员工编号
        if (StringUtils.isEmpty(sysEmployee.getEmployeeCode())) {
            String eeCode =  sequence.getCode(sysEmployee.getCompanyCode(),"-" , 4);
            sysEmployee.setEmployeeCode(eeCode);
            if(!sysEmployeeService.checkCode(sysEmployee, sysEmployee.getCompanyCode())){
                eeCode = sysEmployeeService.resetAndGetCode("-", sysEmployee.getCompanyCode());
            }
            sysEmployee.setEmployeeCode(eeCode);
        }
        else {
            if (sysEmployeeService.selectEmployeeByCode(sysEmployee.getEmployeeCode()) != null) {
                return AjaxResult.error(MessageUtils.message("html.client.employee.temp.messageErrer"));
            }
        }
        Long userId = SecurityUtils.getUserId();
//        sysEmployee.setInvitationCode(userId);
        return toAjax(sysEmployeeService.insertSysEmployee(sysEmployee));
    }

    /**
     * 发邮件通知
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:sendEmail')")
    @GetMapping("/sendMail/{ids}")
    public AjaxResult sendMail(@PathVariable Long[] ids) throws Exception {
        return sysEmployeeService.sendMail(ids);
    }

    /**
     * Update an existing employee.
     * 
     * @param sysEmployee The updated employee information
     * @return The result of the update operation
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:edit')")
    @Log(title = "Employee", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEmployee sysEmployee)
    {
        String curUserName = SecurityUtils.getUsername();
        sysEmployee.setUpdateBy(curUserName);
        return toAjax(sysEmployeeService.updateSysEmployee(sysEmployee));
    }



    @Anonymous
    @Log(title = "Employee", businessType = BusinessType.UPDATE)
    @PutMapping("/basicInfo")
    public AjaxResult editBasicInfo(@RequestParam("sysEmployee") String sysEmployeeJsonData,
                                    MultipartHttpServletRequest files)
    {
        String[] allowedExtensions = new String[]{"pdf", "jpeg", "jpg", "png"};
        long fileMaxSize = 4L; //MB
        Map<String, MultipartFile> filesMap = files.getFileMap();
        StringBuilder errorString = new StringBuilder();
        for (Map.Entry<String, MultipartFile> entry : filesMap.entrySet()) {
            MultipartFile file = entry.getValue();
        }
        //validate files (extension should match png, jpeg, jpg, png && maxSize of the file should me 4MB)
        filesMap.forEach((key, file)->{
            errorString.append(azureFileShareService.fileSizeErrorStr(file, fileMaxSize))
                    .append(azureFileShareService.fileExtensionErrorStr(file, allowedExtensions));
        });


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SysEmployee sysEmployee = objectMapper.readValue(sysEmployeeJsonData, SysEmployee.class);
            //save files (name: path) as json in sys_employee table in basic_files column
            Map<String, String> basicFiles = new HashMap<>();
            processClientEmployeeInfo(sysEmployee, new ArrayList<>(filesMap.values()), basicFiles);
            processClientEmployee(sysEmployee, basicFiles);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return AjaxResult.error(MessageUtils.message("html.client.employee.temp.messageErrer"));
        }
        return success();
    }

    private void processClientEmployee(SysEmployee sysEmployee, Map<String, String> basicFiles) {
        //修改状态
        sysEmployee.setStatus(EEStatus.REGISTRATION.getStatus());
        sysEmployee.setBasicFiles(basicFiles);
        sysEmployee.setUpdateBy(sysEmployee.getLocalName());
        sysEmployeeService.updateEmployeeBasic(sysEmployee);
    }

    private void processClientEmployeeInfo(SysEmployee employeeInfo, List<MultipartFile> files, Map<String, String> basicFiles) throws IOException, NoSuchAlgorithmException, AzureFileShareException {
        //upload files to the local storage
        uploadFilesToLocalStorage(employeeInfo, files, basicFiles);
        //send files to associated operator
        sysEmployeeService.sendFilesToOperator(employeeInfo, files);
    }

    private void uploadFilesToLocalStorage(SysEmployee employeeInfo, List<MultipartFile> files, Map<String, String> basicFiles) {
        for(MultipartFile file : files) {
            String fileRealName = file.getName();
            String saveFileName = fileRealName + "." + FileUtils.getExtension(file);
            String saveFilePath = AzureFileShareDirsConstants.clientEmployees.getValue() +
                    "/" + employeeInfo.getEmployeeCode() +
                    "/" + AzureFileShareDirsConstants.basicFiles.getValue() +
                    "/" + fileRealName + "." + FileUtils.getExtension(file);
//
            basicFiles.put(file.getName(), saveFilePath);
            try {
                String filePath = NNRoadConfig.getProfile() + "/download/clientEmployees/" + employeeInfo.getEmployeeCode() + "/basicFiles/";
                File path = new File(filePath); //判断文件路径下的文件夹是否存在，不存在则创建
                if (!path.exists()) {
                    path.mkdirs();
                }

                File savedFile = new File(filePath + saveFileName);
                // 删除旧文件
                if (savedFile.exists()) {
                    savedFile.delete();
                }
                boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
                if (isCreateSuccess) {
                    //将文件写入
                    savedFile.delete();
                    file.transferTo(savedFile);
                }
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
            logger.info(file.getName() + " has been uploaded to the Local Storage!");
        }
    }

    /**
     * Delete one or more employees.
     * 
     * @param ids The IDs of the employees to delete
     * @return The result of the delete operation
     */
    @PreAuthorize("@ss.hasPermi('employee:remove')")
    @Log(title = "Employee", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysEmployeeService.deleteSysEmployeeByIds(ids));
    }

    @Anonymous
//    @PreAuthorize("@ss.hasPermi('employee:graphic:report')")
    @GetMapping("/greports")
    public Map<String, Object> gReports(ModelMap mmap) {
        // 处理 AJAX 请求，返回数据
        Map<String, Object> response = new HashMap<>();
        populateModel(response);
        return response;
    }

    @PreAuthorize("@ss.hasPermi('employee:download:files')")
//    @Anonymous
    @GetMapping("/download/basicFiles/{id}")
    public void fileDownload(@PathVariable("id") Long id, HttpServletResponse response, HttpServletRequest request) {
        SysEmployee employee = sysEmployeeService.selectSysEmployeeById(id);
        Map<String, String> basicFiles = employee.getBasicFiles();

        try {
            // Create a zip output stream to package the files
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

            // Iterate through the entries in the basicFiles map
            for (Map.Entry<String, String> entry : basicFiles.entrySet()) {
                String fileName = entry.getKey(); // The file name or identifier
                String filePath = entry.getValue(); // The actual file path

                // Full path to the file
                String fullFilePath = NNRoadConfig.getDownloadPath() + filePath;

                // Add file to the zip
                File file = new File(fullFilePath);
                if (file.exists()) {
                    zipOutputStream.putNextEntry(new ZipEntry(fileName + "." + FilenameUtils.getExtension(filePath)));
                    // Write the file content to the zip output stream
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                    fileInputStream.close();
                    zipOutputStream.closeEntry();
                }
            }

            // Close the zip output stream
            zipOutputStream.close();

            // Set response headers for downloading the zip file
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=files.zip");

            // Write the zip file content to the response output stream
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());

        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }


    private void populateModel(Map<String, Object> mmap) {
        LocalDateTime currentDate = LocalDateTime.now();

        List<Integer> HRHKExisting= sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexisting", "HRHK", "HRHK");
        List<Integer> HRHKNew =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Enew", "HRHK", "HRHK");
        List<Integer> HRHKExit =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexit", "HRHK", "HRHK");
        List<Integer> HRSHExisting = sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexisting", "HRSH", "HRSH,HRBJ");
        List<Integer> HRHSHew =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Enew", "HRSH", "HRSH,HRBJ");
        List<Integer> HRSHExit =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexit", "HRSH", "HRSH,giHRBJ");
        List<Integer> TOPFDIExisting= sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexisting", "Top FDI", "Top FDI");
        List<Integer> TOPFDINew =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Enew", "Top FDI", "Top FDI");
        List<Integer> TOPFDIExit =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexit", "Top FDI", "Top FDI");
        List<Integer> FDIExisting = sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexisting", "FDI", "FDI");
        List<Integer> FDINew =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Enew", "FDI", "FDI");
        List<Integer> FDIExit =   sysEmployeeService.selectEorEmployeeTotal(currentDate,"Eexit", "FDI", "FDI");


        List<Integer> Existing = new ArrayList<>();
        List<Integer> New = new ArrayList<>();
        List<Integer> Exit = new ArrayList<>();

        // add up to total
        for (int i = 0; i < HRHKExisting.size(); i++) {
            // Adding corresponding elements from each list
            int existingSum = HRSHExisting.get(i) + TOPFDIExisting.get(i) + HRHKExisting.get(i) + FDIExisting.get(i);
            int newSum = HRHKNew.get(i) + HRHSHew.get(i) + TOPFDINew.get(i) + FDINew.get(i);
            int exitSum = HRHKExit.get(i) + HRSHExit.get(i) + TOPFDIExit.get(i) + FDIExit.get(i);
            Existing.add(existingSum);
            New.add(newSum);
            Exit.add(exitSum);
        }

        mmap.put("existingHK", HRHKExisting);
        mmap.put("newHK", HRHKNew);
        mmap.put("exitHK", HRHKExit);
        mmap.put("existingSHAndBJ", HRSHExisting);
        mmap.put("newSHAndBJ", HRHSHew);
        mmap.put("exitSHAndBJ", HRSHExit);
        mmap.put("existingTopFDI", TOPFDIExisting);
        mmap.put("newTopFDI", TOPFDINew);
        mmap.put("exitTopFDI", TOPFDIExit);
        mmap.put("existingFDI", FDIExisting);
        mmap.put("newFDI", FDINew);
        mmap.put("exitFDI", FDIExit);
        mmap.put("existing", Existing);
        mmap.put("newEmployee", New);
        mmap.put("exitEmployee", Exit);
    }

    private List<List<String>> generateHeaders(List<SysExtraAttribute> attributes) {
        // Recursively flatten the attributes and their children
        return attributes.stream()
                .flatMap(attribute -> flattenAttributes(attribute).stream()) // Flatten children recursively
                .map(attribute -> Collections.singletonList(attribute.getName())) // Convert each name to a single-element list
                .collect(Collectors.toList());
    }

    // Flatten the attributes and their children
    private List<SysExtraAttribute> flattenAttributes(SysExtraAttribute attribute) {
        List<SysExtraAttribute> flattened = new ArrayList<>();
        // Add the current attribute only if it does not have children
        if (attribute.getChildren() == null || attribute.getChildren().isEmpty()) {
            flattened.add(attribute); // Add the current attribute
        }

        // Recursively add the children if any (only for attributes that do not have children)
        if (attribute.getChildren() != null) {
            for (SysExtraAttribute child : attribute.getChildren()) {
                flattened.addAll(flattenAttributes(child)); // Recursively flatten the children
            }
        }
        return flattened;
    }

    // create the dict Map for extraAttribute
    public Map<String, Map<String, String>> createDictMap(List<SysExtraAttribute> extraAttributeList, Map<String, List<SysDictData>> dictDataMap) {
        Map<String, Map<String, String>> resultMap = new HashMap<>();

        // Iterate through the basicAttributeList
        for (SysExtraAttribute attribute : extraAttributeList) {
            // Retrieve the DictType for the current attribute
            String dictType = attribute.getDictType();

            // Get the list of SysDictData corresponding to the dictType
            List<SysDictData> dictDataList = dictDataMap.get(dictType);

            if (dictDataList != null) {
                // Create an inner map for this attribute's id
                Map<String, String> innerMap = new HashMap<>();

                // Iterate through the dictDataList and add dictValue -> dictLabel mappings to innerMap
                for (SysDictData dictData : dictDataList) {
                    innerMap.put(dictData.getDictValue(), dictData.getDictLabel());
                }

                // Put the innerMap in the resultMap with the attribute's id as the key
                resultMap.put(attribute.getId().toString(), innerMap);
            }
        }

        return resultMap;
    }

}
