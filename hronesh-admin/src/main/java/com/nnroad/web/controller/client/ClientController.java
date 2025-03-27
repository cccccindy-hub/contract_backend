package com.nnroad.web.controller.client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.client.constant.ClientConstants;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.domain.entity.SysRole;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.utils.*;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.datacenter.domain.DCTable;
import com.nnroad.datacenter.service.IDCTableDataOperateLogService;
import com.nnroad.datacenter.service.IDCTableService;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.framework.web.service.SysPasswordService;
import com.nnroad.system.constants.MailEnum;
import com.nnroad.system.constants.SysConstants;
import com.nnroad.system.domain.SysEmailSend;
import com.nnroad.system.service.ISysMailService;
import com.nnroad.system.service.ISysRoleService;
import com.nnroad.system.service.ISysUserService;
import com.nnroad.vendor.domain.SysVendor;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.MessageUtils;
import com.nnroad.common.utils.NnroadSequence;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.datacenter.domain.DCTable;
import com.nnroad.datacenter.service.IDCTableDataOperateLogService;
import com.nnroad.datacenter.service.IDCTableService;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.framework.web.service.SysPasswordService;
import com.nnroad.system.service.ISysMailService;
import com.nnroad.system.service.ISysRoleService;
import com.nnroad.system.service.ISysUserService;
import com.nnroad.vendor.domain.SysVendor;
import com.nnroad.vendor.service.ISysVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing SysClient operations.
 *
 * @author nick
 * @date 2024-10-10
 */
@RestController
@RequestMapping("/system/client")
public class ClientController extends BaseController {
    @Autowired
    private ISysClientService sysClientService;

    @Autowired
    private ISysEmployeeService sysEmployeeService;

    @Autowired
    private ISysVendorService sysVendorService;

    @Autowired
    private IDCTableService dcTableService;

    @Autowired
    private IDCTableDataOperateLogService dcTableResultLogService;

    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysMailService sysMailServiceImpl;

    @Autowired
    private ISysRoleService sysRoleService;

    @Value("${sys.accessPath}")
    private String sysAccessPath;

    @Value("${sys.emailCompanyName}")
    private String emailCompanyName;

    @Value("${sys.websiteUrl}")
    private String websiteUrl;

    @Value("${sys.registerPath}")
    private String registerPath;

    @Value("${sys.companyCodePrefix}")
    private String companyCodePrefix;

    /**
     * Retrieve a list of SysClient objects.
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysClient sysClient) {
        startPage();
        List<SysClient> list = sysClientService.selectSysClientList(sysClient);
        return getDataTable(list);
    }

    @Anonymous // 不需要验证
    //@PreAuthorize("@ss.hasPermi('system:client:list')") // 需要验证
    @GetMapping("/list/all")
    public TableDataInfo listAll(SysClient sysClient) {
        List<SysClient> list = sysClientService.selectSysClientList(sysClient);
        return getDataTable(list);
    }

    /**
     * Export the list of SysClient objects.
     */
    @PreAuthorize("@ss.hasPermi('client:export')")
    @Log(title = "SysClient", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysClient sysClient) {
        List<SysClient> list = sysClientService.selectSysClientList(sysClient);
        ExcelUtil<SysClient> util = new ExcelUtil<>(SysClient.class);
        util.exportExcel(response, list, "SysClient Data");
    }

    /**
     * Get detailed information for a specific SysClient.
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:edit')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysClientService.selectSysClientById(id));
    }

    /**
     * Add a new SysClient.
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:add')")
    @Log(title = "SysClient", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysClient sysClient) {
        String location = "C";
        String companyCode = sequence.getCode(location + companyCodePrefix, "-", 3);
        SysClient c = new SysClient();
        // 查询数据库存储的code没有"-"分割
        c.setCompanyCode(companyCode.replace("-", ""));
        if (!sysClientService.checkCode(c, location + companyCodePrefix)) {
            companyCode = sysClientService.resetAndGetCode(location + companyCodePrefix);
        }
        // 数据库存储的code没有"-"分割
        sysClient.setCompanyCode(companyCode.replace("-", ""));
        sysClient.setStatus(ClientConstants.CLIENT_STATUS_POTENTIAL);
        return toAjax(sysClientService.insertSysClient(sysClient));
    }

    /**
     * Update an existing SysClient.
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:edit')")
    @Log(title = "SysClient", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysClient sysClient) {
        return toAjax(sysClientService.updateSysClient(sysClient));
    }

    /**
     * Delete one or more SysClient objects.
     */
    //@PreAuthorize("@ss.hasPermi('client:remove')")
    @Anonymous
    @Log(title = "SysClient", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysClientService.deleteSysClientByIds(ids));
    }

    /**
     * Get employees by client id
     */
    //@PreAuthorize("@ss.hasPermi('client:remove')")
    @Anonymous
    @GetMapping("/{clientId}/employees")
    public List<SysEmployee> getEmployeeByClientId(@PathVariable Long clientId) {
        return sysEmployeeService.getEmployeeByClientId(clientId);
    }

    /**
     * Off-board an employee of a client
     */
    // @PreAuthorize("@ss.hasPermi('client:remove')")
    @Anonymous
    @DeleteMapping("/{clientId}/employees/{employeeId}")
    public void removeEmployeeFromClient(@PathVariable Long clientId, @PathVariable Long employeeId) {
        sysEmployeeService.removeEmployeeFromClient(clientId, employeeId);
    }

    /**
     * Get vendors by client id
     */
    //@PreAuthorize("@ss.hasPermi('client:remove')")
    @Anonymous
    @GetMapping("/{clientId}/vendors")
    public TableDataInfo getVendorByClientId(@PathVariable Long clientId) {
        startPage();
        List<SysVendor> list = sysVendorService.getVendorByClientId((long) clientId);
        return getDataTable(list);
    }

    @Anonymous
    @GetMapping("/basic/by_code/{clientCode}")
    public AjaxResult basicByCode(@PathVariable String clientCode) {

        return success(sysClientService.selectBasicByClientCode(clientCode));
    }

    @Anonymous
    @GetMapping("/basic")
    public AjaxResult basic(@RequestParam(required = false) String clientName) {

        return success(sysClientService.selectBasicByClientName(clientName));
    }


    @PreAuthorize("@ss.hasPermi('abp:client:otherInfo:add')")
    @PostMapping("/addSave/{tableId}")
    public AjaxResult addSave(@PathVariable("tableId") Long tableId, @RequestParam Map<String, String> map) {
        DCTable dcTable = dcTableService.selectDCTableById(tableId);
        boolean canInsert = dcTableService.findDataIsDuplicated(tableId, map, false, new Long(0));
        if (canInsert) {
            Long insertIndex = dcTableService.selectInsertIndex(null, dcTable);
            map.put("sort_key", insertIndex.toString());
            dcTableService.formatParam(map, dcTable, false);
            try {
                addSaveTransactional(map, dcTable, tableId);
                return AjaxResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error(MessageUtils.message("datacenter.message.addFailed"));
            }
        } else {
            return AjaxResult.error(MessageUtils.message("datacenter.message.DataIsDuplicated"));
        }
    }

    @Transactional

    public void addSaveTransactional(Map<String, String> map, DCTable dcTable, Long tableId) {
        dcTableService.insertByMap(map, dcTable.getTableDbName());
        dcTableResultLogService.insertByOne(map, tableId, null);
    }


    @Anonymous
    @GetMapping("/sync-data")
    public AjaxResult getClientSyncData(@RequestParam String org, @RequestParam String code)
    {
        return success(sysClientService.getClientSyncData(org, code));
    }

//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:sync:data')")
    @Log(title = "Client", businessType = BusinessType.INSERT)
    @PostMapping("/sync-data")
    public AjaxResult updateClientBySync(@RequestBody SysClient sysClient)
    {
        //check if the client exists
        SysClient client = sysClientService.selectBasicByClientCode(sysClient.getCompanyCode());
        //get the client from the current system and update the associated client ID for the client
        if (client != null) {
            sysClient.setId(client.getId());
            return toAjax(sysClientService.updateClientBySync(sysClient));
        }
        // add the client if not exist
        else{
            return toAjax(sysClientService.insertSysClient(sysClient));
        }
    }

    /**
     * 設置密码并发邮件通知
     */
    @Log(title = "客户联系人", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('client:contact:mailNotice')")
//    @Anonymous
    @PostMapping("/mailNotice/{ids}")
    public AjaxResult mailNotice(@PathVariable Long[] ids) throws Exception {
        try {
            // 邮件内容
            String content = null;
            // 需发送的所有用户
//            String[] idlist = Convert.toStrArray(ids);
            for (Long id : ids) {
                // 邮箱信息存在时发邮件
                SysClient client = sysClientService.selectSysClientById(id);
                if (StringUtils.isNotEmpty(client.getContactPersonEmail())) {
                    // 一个Byte占两个字节，此处生成的5字节，字符串长度为10
                    SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
                    // 重置密码为随机10位字符
                    String password = secureRandom.nextBytes(5).toHex();
                    //check if the current user exists
                    SysUser userInfo = sysUserService.selectUserByUserName(client.getContactPersonEmail());
                    if (userInfo == null) {
                        userInfo = new SysUser();
                        if(client.getContactPersonName() == null) {
                            return AjaxResult.error(MessageUtils.message("Client Contact Person Name is missing!"));
                        }
                        userInfo.setNickName(client.getContactPersonName());
                        userInfo.setUserName(client.getContactPersonEmail());
                        userInfo.setEmail(client.getContactPersonEmail());
                        userInfo.setPassword(SecurityUtils.encryptPassword(password));
                        userInfo.setUpdateBy(SecurityUtils.getUsername());
                        sysUserService.insertUser(userInfo);
                        // assign client role to the new user
                        // 插入现在的角色
                        SysUser newUser = sysUserService.selectUserByUserName(client.getContactPersonEmail());
                        // assign client role to the user
                        Long[] roleIds = new Long[]{15L};
                        sysUserService.insertUserAuth(newUser.getUserId(), roleIds);
                    }
                    else {
                        userInfo.setPassword(SecurityUtils.encryptPassword(password));
                        userInfo.setUpdateBy(SecurityUtils.getUsername());
                        //reassign client role
                        Long[] roleIds = new Long[]{15L};
                        sysUserService.updateUser(userInfo);
                        sysUserService.insertUserAuth(userInfo.getUserId(), roleIds);
                    }

                    String urlStr = registerPath;
//
                    String btnHtml = "<a href=\"" + urlStr + "\"  target=\"_blank\" style=\"display: block; margin-left: auto; " +
                            "margin-right: auto; width: 50%; border-radius: 5px; cursor: pointer;  font-size: 14px; font-weight: " +
                            "bold; padding: 15px 100px; width: 60%; text-decoration: none; background-color: #FEC853; border-color: #88B700; color: #000000; display: block;\n"
                            + " text-align: center;\">Click here to register your information</a><p style=\"padding-top:35px; font-family: sans-serif;" +
                            " font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">" +
                            "If the button doesn't work, please copy the following link to the browser to open: " + urlStr + "</p>";

                    SysEmailSend emailSend = new SysEmailSend ();
                    emailSend.setSendFromName(SysConstants.SEND_FROM_NAME);
                    // 邮件主题
                    emailSend.setSubject(MailEnum.CREATE_ACCOUNT.getTitle());
                    // 邮件内容设定，参数为模版、访问url、部门、日期、用户密码
                    emailSend.setContent(MailTemplate.getContentByTemplate(MailEnum.CREATE_ACCOUNT.getTemplate(), sysAccessPath,
                            MailEnum.CREATE_ACCOUNT.getSupport(), DateUtils.getDate(), password, emailCompanyName, websiteUrl, btnHtml));
                    // 设置收件人
                    emailSend.setToAddress(userInfo.getEmail());
                    // 发送邮件
                    sysMailServiceImpl.mailSend(emailSend);
                }
                else {
                    return AjaxResult.error(MessageUtils.message("Client Contact Email is missing!"));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(MessageUtils.message("mail.send_success"));
    }

    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:graphic:report')")
    @GetMapping("/greports")
    public Map<String, Object> handleLicenseStatus() {
        // 处理 AJAX 请求，返回数据
        Map<String, Object> response = new HashMap<>();
        populateModel(response);
        return response;
    }


    private void populateModel(Map<String, Object> mmap) {
        LocalDateTime currentDate = LocalDateTime.now();
//        String isLicense = isLicenseChecked ? "Y" : null;

//        List<Integer> Existing, New, Exit;
//        Existing = sysClientService.selectTotal(currentDate,"existing",isLicense);
//        New = sysClientService.selectTotal(currentDate, "new",isLicense);
//        Exit = sysClientService.selectTotal(currentDate,"exit",isLicense);

        List<Integer> ExistingHK = sysClientService.selectTotal(currentDate, "existingHK", "HRHK");
        List<Integer> NewHK = sysClientService.selectTotal(currentDate, "newHK", "HRHK");
        List<Integer> ExitHK = sysClientService.selectTotal(currentDate, "exitHK", "HRHK");
//
        List<Integer> ExistingSHAndBJ = sysClientService.selectTotal(currentDate,"existingSHAndBJ", "HRSH");
        List<Integer> NewSHAndBJ = sysClientService.selectTotal(currentDate,"newSHAndBJ", "HRSH");
        List<Integer> ExitSHAndBJ = sysClientService.selectTotal(currentDate,"exitSHAndBJ", "HRSH");

        List<Integer> ExistingTopFDI = sysClientService.selectTotal(currentDate,"existingTopFDI", "Top FDI");
        List<Integer> NewTopFDI = sysClientService.selectTotal(currentDate,"newTopFDI", "Top FDI");
        List<Integer> ExitTopFDI = sysClientService.selectTotal(currentDate,"exitTopFDI", "Top FDI");

        List<Integer> ExistingFDI = sysClientService.selectTotal(currentDate,"existingFDI", "FDI");
        List<Integer> NewFDI = sysClientService.selectTotal(currentDate,"newFDI", "FDI");
        List<Integer> ExitFDI = sysClientService.selectTotal(currentDate,"exitFDI", "FDI");


        List<Integer> Existing = new ArrayList<>();
        List<Integer> New = new ArrayList<>();
        List<Integer> Exit = new ArrayList<>();

        // add up to total
        for (int i = 0; i < ExistingHK.size(); i++) {
            // Adding corresponding elements from each list
            int existingSum = ExistingSHAndBJ.get(i) + ExistingTopFDI.get(i) + ExistingHK.get(i) + ExistingFDI.get(i);
            int newSum = NewHK.get(i) + NewSHAndBJ.get(i) + NewTopFDI.get(i) + NewFDI.get(i);
            int exitSum = ExitHK.get(i) + ExitSHAndBJ.get(i) + ExitTopFDI.get(i) + ExitFDI.get(i);
            Existing.add(existingSum);
            New.add(newSum);
            Exit.add(exitSum);
        }

        mmap.put("existingHK", ExistingHK);
        mmap.put("newHK", NewHK);
        mmap.put("exitHK", ExitHK);
        mmap.put("existingSHAndBJ", ExistingSHAndBJ);
        mmap.put("newSHAndBJ", NewSHAndBJ);
        mmap.put("exitSHAndBJ", ExitSHAndBJ);
        mmap.put("existingTopFDI", ExistingTopFDI);
        mmap.put("newTopFDI", NewTopFDI);
        mmap.put("exitTopFDI", ExitTopFDI);
        mmap.put("existingFDI", ExistingFDI);
        mmap.put("newFDI", NewFDI);
        mmap.put("exitFDI", ExitFDI);
        mmap.put("existing", Existing);
        mmap.put("newClients", New);
        mmap.put("exitClients", Exit);






    }
}
