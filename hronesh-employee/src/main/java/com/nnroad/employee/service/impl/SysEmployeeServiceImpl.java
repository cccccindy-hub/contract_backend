package com.nnroad.employee.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

//
//import com.nnroad.client.domain.SysClient;
//import com.nnroad.client.mapper.SysClientMapper;
//import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.*;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.employee.constants.enums.EEStatus;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.getDataSyncFromHRHKMapper;
import com.nnroad.employee.model.dto.EEBaseDTO;
import com.nnroad.employee.service.EmployeeSyncService;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
//import com.nnroad.framework.web.domain.server.Sys;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.system.constants.MailEnum;
import com.nnroad.system.constants.SysConstants;
import com.nnroad.system.domain.SysEmailSend;
import com.nnroad.system.mapper.SysDictDataMapper;
import com.nnroad.system.mapper.SysUserMapper;
import com.nnroad.system.service.ISysMailService;
import com.nnroad.utils.ExcelImporter;
import com.nnroad.utils.ExtraAttributeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.ISysEmployeeService;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Integer.parseInt;


/**
 * Service implementation for managing Employee data
 * 
 * This class handles the business logic for employee operations.
 * 
 * @author nick
 * @date 2024-10-11
 */
@Service
public class SysEmployeeServiceImpl implements ISysEmployeeService
{

    private static final Logger logger = LoggerFactory.getLogger(ISysEmployeeService.class);

    private static final ExecutorService executor = Executors.newFixedThreadPool(10); // You can adjust the number of threads

    public static final String EMPLOYEETEMP_EDIT = "eeportal/basicInfo/edit";

    @Autowired
    private ExcelImporter excelImporter;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    // Create a mapping of db names to DataSourceType values
    private static final Map<String, DataSourceType> DB_TO_DATASOURCE_MAP = new HashMap<>();
    static {
        DB_TO_DATASOURCE_MAP.put("HRSH", DataSourceType.HROneSH);
        DB_TO_DATASOURCE_MAP.put("HRBJ", DataSourceType.HROneBJ);
        DB_TO_DATASOURCE_MAP.put("HRHK", DataSourceType.HROneHK);
        DB_TO_DATASOURCE_MAP.put("FDI", DataSourceType.FDI);
        DB_TO_DATASOURCE_MAP.put("Top FDI", DataSourceType.TOPFDIHK);
    }

    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;
    
    @Autowired
    private ExtraAttributeUtils extraAttributeUtils;

    @Autowired
    private ISysExtraAttributeService attributeService;

    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private ISysMailService sysMailService;

    @Autowired
    private getDataSyncFromHRHKMapper getDataSyncFromHRHKMapper;

    @Autowired
    private EmployeeSyncService employeeSyncService;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${sys.registerPath}")
    private String registerPath;

    @Value("${sys.company-name}")
    private String org;

    @Value("${sys.emailCompanyName}")
    private String emailCompanyName;

    @Value("${sys.websiteUrl}")
    private String websiteUrl;


    /**
     * Retrieve an employee by ID
     * 
     * @param id Employee primary key
     * @return Employee object
     */
    @Override
    public SysEmployee selectSysEmployeeById(Long id)
    {
        return sysEmployeeMapper.selectSysEmployeeById(id);
    }

    /**
     * Retrieve a list of employees based on criteria
     * 
     * @param sysEmployee Filter criteria for employee retrieval
     * @return List of Employee objects
     */
    @Override
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee)
    {
        if (sysEmployee.getExtraData() != null) {
            Map<String, Object> processedExtraData = extraAttributeUtils.processExtraDataForSearching(sysEmployee.getExtraData());
            sysEmployee.setExtraData(processedExtraData);
        }

        return sysEmployeeMapper.selectSysEmployeeList(sysEmployee);
    }

    /**
     * Add a new employee
     * 
     * @param sysEmployee Employee object to be added
     * @return Result of the insertion
     */
    @Override
    public int insertSysEmployee(SysEmployee sysEmployee)
    {
    	if (extraAttributeUtils.validateData(sysEmployee.getExtraData(), "sys_employee")) {
            if(sysEmployee.getOrg() == null) {
                sysEmployee.setOrg(org);
            }
            sysEmployee.setStatus(EEStatus.TOBESENT.getStatus());
            //generate uuid for new employee
            String uuid = UUID.randomUUID().toString();
            String curUserName = SecurityUtils.getUsername();
            sysEmployee.setEmployeeUuid(uuid);
            sysEmployee.setCreateBy(curUserName);
            return sysEmployeeMapper.insertSysEmployee(sysEmployee);
    	}
    	else {
    		throw new IllegalArgumentException("Invalid input data for extra attributes");
    	}
    }

    /**
     * Update an existing employee's information
     * 
     * @param sysEmployee Employee object with updated values
     * @return Result of the update
     */
    @Override
    public int updateSysEmployee(SysEmployee sysEmployee)
    {
    	if (extraAttributeUtils.validateData(sysEmployee.getExtraData(), "sys_employee")) {
            employeeSyncService.checkVendorSync(sysEmployee, "sys_employee");
            employeeSyncService.checkClientSync(sysEmployee, "sys_employee");
            // set the update by to current user if it is null
            if (sysEmployee.getUpdateBy() == null) {
                String userName = SecurityUtils.getUsername();
                sysEmployee.setUpdateBy(userName);
            }
    		return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    	}
    	else {
    		throw new IllegalArgumentException("Invalid input data for extra attributes");
    	}
    }

    /**
     * Update an existing employee's basic information by employee
     * @param sysEmployee Employee object with updated values
     * @return Result of the update
     */
    @Override
    public int updateEmployeeBasic(SysEmployee sysEmployee) {
        return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    }

    /**
     * Batch delete employees by their IDs
     * 
     * @param ids Array of primary keys for employees to be deleted
     * @return Result of the batch deletion
     */
    @Override
    public int deleteSysEmployeeByIds(Long[] ids)
    {
        //batch delete employee info by employee ids
        employeeInfoMapper.batchDeleteEmployeeInfoByIds(ids, "employee_contract");
        employeeInfoMapper.batchDeleteEmployeeInfoByIds(ids, "employee_payroll_crm");
        employeeInfoMapper.batchDeleteEmployeeInfoByIds(ids, "employee_payroll_dc");
        employeeInfoMapper.batchDeleteEmployeeInfoByIds(ids, "employee_off_board_info");
        return sysEmployeeMapper.deleteSysEmployeeByIds(ids);
    }

    /**
     * Delete an employee by ID
     * 
     * @param id Employee primary key
     * @return Result of the deletion
     */
    @Override
    public int deleteSysEmployeeById(Long id)
    {
        return sysEmployeeMapper.deleteSysEmployeeById(id);
    }
    
    
    /**
     * Get list of employees by client id
     * 
     * @param id Client primary key
     * @return List of Employee objects
     */
	@Override
	public List<SysEmployee> getEmployeeByClientId(Long clientId) {
		return sysEmployeeMapper.getEmployeesByClientId(clientId);
	}

    @Override
    public List<EEBaseDTO> queryBaseByClientCode(String clientCode, Collection<String> statusList) {

        return sysEmployeeMapper.queryBaseByClientCode(clientCode, statusList);
    }

    /**
     * Get list of employees by vendor id
     * 
     * @param id Vendor primary key
     * @return List of Employee objects
     */
	@Override
	public List<SysEmployee> getEmployeeByVendorId(Long vendorId) {
		return sysEmployeeMapper.getEmployeesByVendorId(vendorId);
	}

	 /**
     * Off board and employee of a client by employee id
     * 
     * @param clientId Client primary key, employeeId Employee primary key
     * 
     */
	@Override
	public void removeEmployeeFromClient(Long clientId, Long employeeId) {
		sysEmployeeMapper.removeEmployeeFromClient(clientId, employeeId);
	}
	
	 /**
     * Off board and employee of a vendor by employee id
     * 
     * @param employeeId Employee primary key, employeeId Employee primary key
     * @return Result of the deletion
     * 
     */
	@Override
	public int removeEmployeeFromVendor(Long vendorId, Long employeeId) {
		return sysEmployeeMapper.removeEmployeeFromVendor(vendorId, employeeId);
	}

    @Override
    public boolean checkCode(SysEmployee employee, String prefix) {
        boolean ret = true;
        Map<String, Object> params = new HashMap<>();
        params.put("keyPrefix", prefix);
        employee.setParams(params);
        int cnt = sysEmployeeMapper.checkMaxCodeEmployee(employee);
        if (cnt >0){
            ret = false;
        }

        return ret;
    }

    @Override
    public String resetAndGetCode(String joiner, String prefix) {
        String maxCode = sysEmployeeMapper.getMaxCodeEmployee(prefix);
        return sequence.resetAndGetCode(maxCode, joiner);
    }

    @Override
    public AjaxResult sendMail(Long[] ids) {
        // 按id查询雇员
        for (Long id : ids) {
            // 查询员工信息
            SysEmployee clientEmployeeTemp = sysEmployeeMapper.selectSysEmployeeById(id);

            String name = null == clientEmployeeTemp.getEmployeeName() ? clientEmployeeTemp.getLocalName() : clientEmployeeTemp.getEmployeeName();
            String urlStr = registerPath + EMPLOYEETEMP_EDIT  + "?uuid="+ clientEmployeeTemp.getEmployeeUuid();
//              String urlStr = "temp";
//            String btnHtml = "<a href=\"" + urlStr + "\"  target=\"_blank\" style=\"display: block; margin-left: auto; margin-right: auto; width: 50%; border-radius: 5px; cursor: pointer;  font-size: 14px; font-weight: bold; padding: 15px 100px; width: 60%; text-decoration: none; background-color: #88B700; border-color: #88B700; color: #ffffff; display: block;\n" +
//                    " text-align: center;\">Click here to register your information</a>";

            String btnHtml = "<a href=\"" + urlStr + "\"  target=\"_blank\" style=\"display: block; margin-left: auto; " +
                    "margin-right: auto; width: 50%; border-radius: 5px; cursor: pointer;  font-size: 14px; font-weight: " +
                    "bold; padding: 15px 100px; width: 60%; text-decoration: none; background-color: #FEC853; border-color: #88B700; color: #000000; display: block;\n"
                    + " text-align: center;\">Click here to register your information</a><p style=\"padding-top:35px; font-family: sans-serif;" +
                    " font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">" +
                    "If the button doesn't work, please copy the following link to the browser to open: " + urlStr + "</p>";

            // 发送邮件通知用户
            try {
                SysEmailSend emailSend = new SysEmailSend ();
                emailSend.setSendFromName(SysConstants.SEND_FROM_NAME);
                // 邮件主题
                emailSend.setSubject(MailEnum.COMPLETE_INFORMATION.getTitle());
                // 邮件内容设定，参数为模版、访问url、部门、日期、验证码
                emailSend.setContent(MailTemplate.getContentByTemplate(MailEnum.COMPLETE_INFORMATION.getTemplate(),
                        MailEnum.COMPLETE_INFORMATION.getSupport(), DateUtils.getDate(), name, btnHtml, emailCompanyName));
                // 设置收件人
                emailSend.setToAddress(clientEmployeeTemp.getEmployeeEmail());
                // 发送邮件
                sysMailService.mailSend(emailSend);
                //发送成功修改状态
//                employeeTemp.setId(id);
                clientEmployeeTemp.setStatus(EEStatus.SENT.getStatus());
                clientEmployeeTemp.setUpdateBy(SecurityUtils.getUsername());
//                employeeTemp.setSendDate(new Date());
                updateSysEmployee(clientEmployeeTemp);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return AjaxResult.error(MessageFormat.format(MessageUtils.message("msg.error.mail_delivery"), clientEmployeeTemp.getEmployeeEmail()));
            }
        }
        return AjaxResult.success();
    }

    @Override
    public SysEmployee selectEmployeeByCode(String employeeCode) {
        return sysEmployeeMapper.selectClientEmployeeByCode(employeeCode);
    }

    @Override
    public SysEmployee selectClientEE(String clientCode, String eeCode) {

         return sysEmployeeMapper.selectEEByCode(clientCode, eeCode);
    }

    @Override
    public SysEmployee selectClientEE(String clientCode, String eeName, String idNumber) {
        return sysEmployeeMapper.selectClientEE(clientCode, eeName, idNumber);
    }

    @Override
    public SysEmployee selectSysEmployeeByUUID(String uuid) {
        return sysEmployeeMapper.selectSysEmployeeByUUID(uuid);
    }

    @Override
    public SysEmployee getEmployeeSyncData(String org, String code) {
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
            return sysEmployeeMapper.selectClientEmployeeByCode(code);
        } else {
            return null;
        }
    }

    @Override
    public int updateSysEmployeeBySync(SysEmployee sysEmployee) {
        return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    }

    @Override
    public int importEmployeeExcel(MultipartFile file) {
        try {
            List<Map<String, Object>> result = excelImporter.parseEmployeeExcel(file, "sys_employee");
            try {
                int i = 0;
                for (Map<String, Object> jsonEntry : result) {
                    String clientCompanyCode = (String) jsonEntry.get("Company Code");
                    SysEmployee employee = new SysEmployee();
                    employee.setCompanyCode(clientCompanyCode);
                    employee.setLocalName((String) jsonEntry.get("Local Name"));
                    employee.setEmployeeName((String) jsonEntry.get("Employee Name"));
                    employee.setStatus((String) jsonEntry.get("Status"));
                    String idType = (String) jsonEntry.get("ID Type");
                    employee.setIdType(sysDictDataMapper.selectDictValue("id_type", idType));
                    employee.setIdNumber((String) jsonEntry.get("ID Number"));
                    employee.setNationality((String) jsonEntry.get("国籍/国家名称"));
                    String employeeSex = (String) jsonEntry.get("性别");
                    employee.setSex(sysDictDataMapper.selectDictValue("employee_sex", employeeSex));
                    employee.setEmployeeEmail((String)  jsonEntry.get("Email"));
                    employee.setEmployeePhone((String)  jsonEntry.get("Mobile"));
                    employee.setEmployeePhone((String)  jsonEntry.get("Organization"));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    System.out.println(jsonEntry.get("合同起始日期"));
                    System.out.println(jsonEntry.get("合同终止日期"));
                    Date contractStartDate = null;
                    Date contractEndDate = null;
                    if (jsonEntry.get("合同起始日期") != null && !((String) jsonEntry.get("合同起始日期")).trim().isEmpty()) {
                        contractStartDate = dateFormat.parse((String) jsonEntry.get("合同起始日期"));
                    }
                    if (jsonEntry.get("合同终止日期") != null && !((String) jsonEntry.get("合同终止日期")).trim().isEmpty()) {
                        contractEndDate = dateFormat.parse((String) jsonEntry.get("合同终止日期"));
                    }

                    employee.setContractStartDate(contractStartDate);
                    employee.setContractEndDate(contractEndDate);
                    //set extraData
                    employee.setExtraData((Map<String, Object>) jsonEntry.get("extraData"));

                    // 没有录入员工编号的通过clientCode生成最新的员工编号
                    String eeCode =  sequence.getCode(employee.getCompanyCode(),"-" , 4);
                    employee.setEmployeeCode(eeCode);
                    if(!checkCode(employee, employee.getCompanyCode())){
                        eeCode = resetAndGetCode("-", employee.getCompanyCode());
                    }
                    employee.setEmployeeCode(eeCode);
                    insertSysEmployee(employee);
                    //insert employee contract info
                    SysEmployee newEmployee = sysEmployeeMapper.selectClientEmployeeByCode(eeCode);
                    EmployeeInfo employeeInfo = new EmployeeInfo();
                    employeeInfo.setEmployeeCode(eeCode);
                    employeeInfo.setEmployeeId(newEmployee.getId());
                    employeeInfo.setTableName("employee_contract");
                    employeeInfo.setExtraData((Map<String, Object>) jsonEntry.get("contractExtraData"));
                    employeeInfoMapper.insertEmployeeInfo(employeeInfo);

                    //insert employee payroll crm info
                    employeeInfo.setTableName("employee_payroll_crm");
                    employeeInfo.setExtraData((Map<String, Object>) jsonEntry.get("crmExtraData"));
                    employeeInfoMapper.insertEmployeeInfo(employeeInfo);

                    //insert employee payroll dc info
                    employeeInfo.setTableName("employee_payroll_dc");
                    employeeInfo.setExtraData((Map<String, Object>) jsonEntry.get("dcExtraData"));
                    employeeInfoMapper.insertEmployeeInfo(employeeInfo);

                    //insert employee off board info
                    employeeInfo.setTableName("employee_off_board_info");
                    employeeInfo.setExtraData((Map<String, Object>) jsonEntry.get("offBoardExtraData"));
                    employeeInfoMapper.insertEmployeeInfo(employeeInfo);

                }
            }
            catch (Exception e) {
                System.err.println("Error inserting: \n");
                e.printStackTrace();
                throw new RuntimeException("Error during import employees", e);
            }

        } catch (IOException e) {
            System.err.println("Error invoking parseExcel: " + e.getMessage());
            throw new RuntimeException("Error during import employees", e);
        }
        return 0;
    }

    @Override
    public List<SysEmployee> selectEmployeeAllInfoList(SysEmployee sysEmployee) {
        List<SysEmployee> list = new ArrayList<>();
        // export employee list from all system
        if (sysEmployee.getParams().get("exportAll") != null && sysEmployee.getParams().get("exportAll").equals("1")) {
            List<DataSourceType> dataSourceTypeList = Arrays.asList(DataSourceType.TOPFDIHK, DataSourceType.HROneSH, DataSourceType.FDI, DataSourceType.HROneHK);
            // List to hold futures to wait for task completion
            // Initialize a fixed thread pool executor
            ExecutorService executor = Executors.newFixedThreadPool(dataSourceTypeList.size());
            List<Future<?>> futures = new ArrayList<>();
            for (DataSourceType entry : dataSourceTypeList) {
                // Use a Callable instead of Runnable to capture the result
                futures.add(executor.submit(() -> {
                    List<SysEmployee> employeeInfoList = new ArrayList<>();
                    try {
                        // Set the DataSourceType dynamically
                        DynamicDataSourceContextHolder.setDataSourceType(entry.name());

                        // Call the method to fetch employee info
                        employeeInfoList = sysEmployeeMapper.selectEmployeeAllInfoList(sysEmployee);

                        // Log the source name for fetched data
                        System.out.println("Fetched data for: " + entry.name());

                    } catch (Exception e) {
                        // Handle exceptions if necessary
                        e.printStackTrace();
                    }
                    return employeeInfoList;  // Return the result of the task (employee data)
                }));
            }

            try {
                for (Future<?> future : futures) {
                    List<SysEmployee> employeeInfoList = (List<SysEmployee>) future.get();  // Get the result of the future
                    if (!employeeInfoList.isEmpty()) {
                        synchronized (list) {  // Ensure thread-safety while adding to the list
                            list.addAll(employeeInfoList);
                        }
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                // Shutdown the executor after all tasks are completed
                executor.shutdown();
            }
            return list;

    }
        // export employee list from current system
        return sysEmployeeMapper.selectEmployeeAllInfoList(sysEmployee);
    }

    @Override
    public void sendFilesToOperator(SysEmployee employeeInfo, List<MultipartFile> files) {
        String operatorName = employeeInfo.getCreateBy();
        SysUser operator = sysUserMapper.selectUserByUserName(operatorName);
        String operatorEmail = operator.getEmail();

            // 发送邮件通知Operator
            try {
                SysEmailSend emailSend = new SysEmailSend ();
                emailSend.setSendFromName(SysConstants.SEND_FROM_NAME);
                // 邮件主题
                emailSend.setSubject(MailEnum.COMPLETE_REGISTRATION.getTitle());
                // 邮件内容设定，参数为模版、访问url、部门、日期、验证码
                emailSend.setContent(MailTemplate.getContentByTemplate(MailEnum.COMPLETE_REGISTRATION.getTemplate(),
                        MailEnum.COMPLETE_REGISTRATION.getSupport(), DateUtils.getDate(), operatorName, employeeInfo.getLocalName(), emailCompanyName));
                // 设置收件人
                emailSend.setToAddress(operatorEmail);
                //set files
                Map<String, MultipartFile> filesMap = new HashMap<>();
                for (MultipartFile file: files) {
                    filesMap.put(file.getName() + "." + FileUtils.getExtension(file), file);
                }
                emailSend.setFiles(filesMap);
                // 发送邮件
                sysMailService.mailSend(emailSend);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
    }

    @Override
    public List<Integer> selectEorEmployeeTotal(LocalDateTime currentDate, String type, String dataSource, String orgs) {
        List<Integer> leadsData = new ArrayList<>();
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(dataSource);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
        }
        for (int i = 1; i < 13; i++) {
            LocalDateTime targetDateTime = currentDate.minusMonths(i);
            // 获取该月的最后一天的时间
            LocalDateTime endDate = targetDateTime.withDayOfMonth(targetDateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
            LocalDateTime startDate = targetDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).minusSeconds(1);

            LocalDateTime EtargetDateTime = currentDate.minusMonths(i + 1);
            LocalDateTime EstartDate = EtargetDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).minusSeconds(1);
            LocalDateTime EendDate = EtargetDateTime.withDayOfMonth(EtargetDateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
//            LocalDateTime EendDate = EstartDate.plusMonths(1);


            if ("Eexisting".equals(type)) {
                // 获取 startDateTime 的上一秒
                LocalDateTime previousSecond = startDate.minusSeconds(1);
                leadsData.add(sysEmployeeMapper.selectTotalExistingEmployee(startDate, orgs));
            } else if ("Enew".equals(type)) {
                leadsData.add(sysEmployeeMapper.selectTotalNewEmployee(startDate, endDate, orgs));
            } else if ("Eexit".equals(type)) {
                leadsData.add(sysEmployeeMapper.selectTotalExitEmployee(EstartDate, EendDate, orgs));
            }
        }
        return leadsData;
    }


}
