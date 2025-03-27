package com.nnroad.web.controller.payroll;


import com.nnroad.common.annotation.Log;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.*;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.PsOpLog;
import com.nnroad.payroll.domain.common.*;
import com.nnroad.payroll.service.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ps_op_logController
 *
 * @author Hrone
 * @date 2021-01-17
 */
@Controller
@RequestMapping("/payroll/ps_op_log")
public class PsOpLogController extends BaseController {
    private String prefix = "payroll/ps_op_log";


    @Autowired
    private IPsOpLogService psOpLogService;

    @Autowired
    private IPsBasicInfoHisService psBasicInfoHisService;

    @Autowired
    private IPsBasicUnfixedInfoHisService psBasicUnfixedInfoHisService;

    @Autowired
    private IPsCalculationResultHisService psCalculationResultHisService;

    @Autowired
    private IPsBasicInfoService psBasicInfoService;

    @Autowired
    private IPsCalculationResultService psCalculationResultService;

    @Autowired
    private IPsPayrollService psPayrollService;

    @Autowired
    private IPsPayslipService psPayslipService;

    @Autowired
    private IPsLeaveService psLeaveService;

    /**
     * 查询ps_op_log列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:ps_op_log:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody PsOpLog psOpLog) {
        startPage();
        List<PsOpLog> list = psOpLogService.selectPsOpLogList(psOpLog);
        return getDataTable(list);
    }




    /**
     * 导出ps_op_log列表
     */
    @RequiresPermissions("payroll:ps_op_log:export")
    @Log(title = "ps_op_log", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsOpLog psOpLog) {
        List<PsOpLog> list = psOpLogService.selectPsOpLogList(psOpLog);
        ExcelUtil<PsOpLog> util = new ExcelUtil<PsOpLog>(PsOpLog.class);
        return util.exportExcel(list, "ps_op_log");
    }

    /**
     * 新增保存ps_op_log
     */
    @RequiresPermissions("payroll:ps_op_log:add")
    @Log(title = "ps_op_log", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsOpLog psOpLog) {
        return toAjax(psOpLogService.insertPsOpLog(psOpLog));
    }

    /**
     * 修改ps_op_log
     */
//    @GetMapping("/edit/{opId}")
//    public String edit(@PathVariable("opId") Long opId, ModelMap mmap) {
//        PsOpLog psOpLog = psOpLogService.selectPsOpLogById(opId);
//        mmap.put("psOpLog", psOpLog);
//        return prefix + "/edit";
//    }

    /**
     * 修改保存ps_op_log
     */
    @RequiresPermissions("payroll:ps_op_log:edit")
    @Log(title = "ps_op_log", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsOpLog psOpLog) {
        return toAjax(psOpLogService.updatePsOpLog(psOpLog));
    }

    /**
     * 删除ps_op_log
     */
    @RequiresPermissions("payroll:ps_op_log:remove")
    @Log(title = "ps_op_log", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(psOpLogService.deletePsOpLogByIds(ids));
    }


    /**
     * 单个导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "ps_op_log", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('payroll:ps_op_log:import')")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(@RequestParam("file") MultipartFile file,@RequestParam("updateSupport") boolean updateSupport) throws Exception {
        String message = "";
        String duration = "";

        HroneExcelUtil<PsBasicInfo> psBasicInfoUtil = new HroneExcelUtil<>(PsBasicInfo.class);
        HroneExcelUtil<PsCalculationResult> psCalculationResultUtil = new HroneExcelUtil<>(PsCalculationResult.class);
        HroneExcelUtil<PsPayroll> psPayrollUtil = new HroneExcelUtil<>(PsPayroll.class);
        HroneExcelUtil<PsPayslip> psPayslipUtil = new HroneExcelUtil<>(PsPayslip.class);
        HroneExcelUtil<PsLeave> psPsLeaveUtil = new HroneExcelUtil<>(PsLeave.class);


        List<PsBasicInfo> psBasicInfoList = null;
        List<PsCalculationResult> psCalculationResultList = null;
        List<PsPayroll> psPayrollList = null;
        List<PsPayslip> psPayslipList = null;
        List<PsLeave> psLeaveList = null;
        try {
            psBasicInfoList = psBasicInfoUtil.importExcel("基本信息", true, true, file.getInputStream());
            if (!Pattern.matches("^(FDI|HROne) Salary [0-9]{6}[.](xlsx|xls)$", file.getOriginalFilename())) {
                try {
                    psBasicInfoService.checkRequire(psBasicInfoList);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return AjaxResult.error(e.getMessage());
                }
            }
            psCalculationResultList = psCalculationResultUtil.importExcel("计算结果", true, true, file.getInputStream());
            String[] psPayrollRequire = {"ID No. 工号", "Name 姓名"};
            psPayrollList = psPayrollUtil.importExcel("S-Payroll Report", true, true, file.getInputStream(), 5, 6, psPayrollRequire);
            String[] psPayslipRequire = {"ID No. 工号","Name 姓名"};
            psPayslipList = psPayslipUtil.importExcel("S-Payslip", true, true, file.getInputStream(), 4, 5, psPayslipRequire);
            // 年假的sheet不是必须
            psLeaveList = psPsLeaveUtil.importExcel("带薪假期", false, false, file.getInputStream());


            if (psLeaveList != null) {
                for (PsLeave leaveInfo : psLeaveList) {
                    if (StringUtils.isNotEmpty(leaveInfo.getEeNo()) && StringUtils.isNotEmpty(leaveInfo.getLeaveName())) {
                        PsLeave selectParam = new PsLeave();
                        selectParam.setEeNo(leaveInfo.getEeNo());
                        selectParam.setLeaveName(leaveInfo.getLeaveName());
                        List<PsLeave> deleteLeaves = psLeaveService.selectPsLeaveList(selectParam);
                        if (deleteLeaves != null && deleteLeaves.size() > 0) {
                            List<Long> ids = deleteLeaves.stream().filter(o -> o.getId() != null).map(PsLeave::getId).distinct().collect(Collectors.toList());
                            psLeaveService.deletePsLeaveByIds(ids);

                        }
                        psLeaveService.insertPsLeave(leaveInfo);
                    }
                }
            }

            //获取导入期间
            String filename = file.getOriginalFilename();//获得原始文件名

            String regex = "\\d{4}(0[1-9]|1[0-2])";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(filename);
            if (matcher.find()) {
                duration = matcher.group(0);
            } else {
                return error("文件名不正确");
            }

            // 删除旧数据
            List<String> clientCodes = new ArrayList<String>();
            for (PsBasicInfo basic : psBasicInfoList) {
                if (StringUtils.isNotEmpty(basic.getClientCode()) && !clientCodes.contains(basic.getClientCode())) {
                    clientCodes.add(basic.getClientCode());
                }
            }
            // clientCode去重复
            clientCodes = clientCodes.stream().distinct().collect(Collectors.toList());
            psBasicInfoService.deletePaymentNotice(duration, clientCodes);

            //导入信息
            StringBuilder messageStringBuilder = new StringBuilder();
            String psBasicInfoMessage = psBasicInfoService.importPsBasicInfo(psBasicInfoList, updateSupport, duration);
            String psCalculationResultMessage = psCalculationResultService.importPsCalculationResult(psCalculationResultList, updateSupport, duration);
            String psPayrollMessage = psPayrollService.importPsPayroll(psPayrollList, updateSupport, duration);
            String psPayslipMessage = psPayslipService.importPsPayslip(psPayslipList, updateSupport, duration);
            setCurrencyRate(file, duration, psBasicInfoList);

            psPayrollService.updatePsPayrollByPayslip(duration,psBasicInfoList);

            messageStringBuilder.append(psBasicInfoMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psCalculationResultMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayrollMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayslipMessage);
            message = messageStringBuilder.toString();

            PsOpLog pol = new PsOpLog();
            pol.setCreateBy(SecurityUtils.getUsername());
//            pol.setRoleName(getLoginUser().getUser().getRole);
//            pol.setRoleName(sysUser.getRoles().get(0).getRoleName());
            pol.setImputFileName(filename);
            pol.setOpType("单个导入");
            psOpLogService.insertPsOpLog(pol);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if (StringUtils.isNotEmpty(e.getMessage())) {
                return AjaxResult.error("文件导入失败!");
            } else {
                return AjaxResult.error("文件导入失败!");
            }
        }

        return AjaxResult.success(message.toString());
    }
    /**
     * 供应商导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "ps_op_log", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('payroll:ps_op_log:importVendor')")
    @PostMapping("/importDataVendor")
    @ResponseBody
    public AjaxResult importDataVendor(@RequestParam("file") MultipartFile file,@RequestParam("updateSupport") boolean updateSupport) throws Exception {
        String message = "";
        String duration = "";

        HroneExcelUtil<PsBasicInfo> psBasicInfoUtil = new HroneExcelUtil<>(PsBasicInfo.class);
        HroneExcelUtil<PsCalculationResult> psCalculationResultUtil = new HroneExcelUtil<>(PsCalculationResult.class);
        HroneExcelUtil<PsPayroll> psPayrollUtil = new HroneExcelUtil<>(PsPayroll.class);
        HroneExcelUtil<PsPayslip> psPayslipUtil = new HroneExcelUtil<>(PsPayslip.class);
        HroneExcelUtil<PsLeave> psPsLeaveUtil = new HroneExcelUtil<>(PsLeave.class);


        List<PsBasicInfo> psBasicInfoList = null;
        List<PsCalculationResult> psCalculationResultList = null;
        List<PsPayroll> psPayrollList = null;
        List<PsPayslip> psPayslipList = null;
        List<PsLeave> psLeaveList = null;
        try {
            psBasicInfoList = psBasicInfoUtil.importExcel("基本信息", true, true, file.getInputStream());
            if (!Pattern.matches("^(FDI|HROne) Salary [0-9]{6}[.](xlsx|xls)$", file.getOriginalFilename())) {
                try {
                    psBasicInfoService.checkRequire(psBasicInfoList);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return AjaxResult.error(e.getMessage());
                }
            }
            psCalculationResultList = psCalculationResultUtil.importExcel("计算结果", true, true, file.getInputStream());
            String[] psPayrollRequire = {"ID No. 工号", "Name 姓名"};
            psPayrollList = psPayrollUtil.importExcel("S-Payroll Report", true, true, file.getInputStream(), 5, 6, psPayrollRequire);
            String[] psPayslipRequire = {"ID No. 工号","Name 姓名"};
            psPayslipList = psPayslipUtil.importExcel("S-Payslip", true, true, file.getInputStream(), 4, 5, psPayslipRequire);
            // 年假的sheet不是必须
            psLeaveList = psPsLeaveUtil.importExcel("带薪假期", false, false, file.getInputStream());


            if (psLeaveList != null) {
                for (PsLeave leaveInfo : psLeaveList) {
                    if (StringUtils.isNotEmpty(leaveInfo.getEeNo()) && StringUtils.isNotEmpty(leaveInfo.getLeaveName())) {
                        PsLeave selectParam = new PsLeave();
                        selectParam.setEeNo(leaveInfo.getEeNo());
                        selectParam.setLeaveName(leaveInfo.getLeaveName());
                        List<PsLeave> deleteLeaves = psLeaveService.selectPsLeaveList(selectParam);
                        if (deleteLeaves != null && !deleteLeaves.isEmpty()) {
                            List<Long> ids = deleteLeaves.stream().filter(o -> o.getId() != null).map(PsLeave::getId).distinct().collect(Collectors.toList());
                            psLeaveService.deletePsLeaveByIds(ids);

                        }
                        psLeaveService.insertPsLeave(leaveInfo);
                    }
                }
            }

            //获取导入期间
            String filename = file.getOriginalFilename();//获得原始文件名

            String regex = "\\d{4}(0[1-9]|1[0-2])";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(filename);
            if (matcher.find()) {
                duration = matcher.group(0);
            } else {
                return error("文件名不正确");
            }

            //导入信息
            StringBuilder messageStringBuilder = new StringBuilder();
            String psBasicInfoMessage = psBasicInfoService.importPsBasicInfo(psBasicInfoList, updateSupport, duration);
            String psCalculationResultMessage = psCalculationResultService.importPsCalculationResult(psCalculationResultList, updateSupport, duration);
            String psPayrollMessage = psPayrollService.importVendorPsPayroll(psPayrollList, updateSupport, duration);
            String psPayslipMessage = psPayslipService.importPsPayslip(psPayslipList, updateSupport, duration);
            setCurrencyRate(file, duration, psBasicInfoList);

            psPayrollService.updateVendorPsPayrollByPayslip(duration,psBasicInfoList);

            messageStringBuilder.append(psBasicInfoMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psCalculationResultMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayrollMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayslipMessage);
            message = messageStringBuilder.toString();

            PsOpLog pol = new PsOpLog();
            pol.setCreateBy(SecurityUtils.getUsername());
            pol.setImputFileName(filename);
            pol.setOpType("供应商导入");
            psOpLogService.insertPsOpLog(pol);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if (StringUtils.isNotEmpty(e.getMessage())) {
                return AjaxResult.error("文件导入失败!");
            } else {
                return AjaxResult.error("文件导入失败!");
            }
        }

        return AjaxResult.success(message.toString());
    }


    @Log(title = "ps_op_log", businessType = BusinessType.IMPORT)
    @RequiresPermissions("payroll:ps_op_log:importBat")
    @PostMapping("/importBatData")
    @ResponseBody
    public AjaxResult importBatData(MultipartHttpServletRequest files, boolean updateSupportBat, String groupIdsBat, String remarksBat, boolean sendMailBat) throws Exception {
        String message = "";
        String uploadFileName = "";
        StringBuffer errorMsg = new StringBuffer();
        for (MultipartFile file : files.getFiles("file")) {
            String fileRealName = file.getOriginalFilename();//获得原始文件名

            logger.debug("file {} upload start!", fileRealName);

            HroneExcelUtil<PsBasicInfo> psBasicInfoUtil = new HroneExcelUtil<>(PsBasicInfo.class);
            HroneExcelUtil<PsCalculationResult> psCalculationResultUtil = new HroneExcelUtil<>(PsCalculationResult.class);
            HroneExcelUtil<PsPayroll> psPayrollUtil = new HroneExcelUtil<>(PsPayroll.class);
            // HroneExcelUtil<PsPayrollTax> psPayrollTaxUtil = new HroneExcelUtil<>(PsPayrollTax.class);
            HroneExcelUtil<PsPayslip> psPayslipUtil = new HroneExcelUtil<>(PsPayslip.class);
            // HroneExcelUtil<PsYearEndBonus> psYearEndNonusUtil = new HroneExcelUtil<>(PsYearEndBonus.class);
            HroneExcelUtil<PsLeave> psPsLeaveUtil = new HroneExcelUtil<>(PsLeave.class);

            List<PsBasicInfo> psBasicInfoList = null;
            List<PsCalculationResult> psCalculationResultList = null;
            List<PsPayroll> psPayrollList = null;
            // List<PsPayrollTax> psPayrollTaxList = null;
            List<PsPayslip> psPayslipList = null;
            // List<PsYearEndBonus> psYearEndNonusList = null;
            List<PsLeave> psLeaveList = null;

            //导入信息
            StringBuilder messageStringBuilder = new StringBuilder();

            try {
                if (fileRealName.contains("/")) {
                    String[] fileArry = fileRealName.split("/");
                    fileRealName = fileArry[fileArry.length - 1];
                }

                psBasicInfoList = psBasicInfoUtil.importExcel(MessageUtils.message("import.sheetname.ps_basic_info"), true, true, file.getInputStream());
                if (!Pattern.matches("^(FDI|HROne) Salary [0-9]{6}[.](xlsx|xls)$", file.getOriginalFilename())) {
                    try {
                        psBasicInfoService.checkRequire(psBasicInfoList);
                    } catch (Exception e) {
                        logger.error(fileRealName + e.getMessage());
                        errorMsg.append(fileRealName + "upload failure!");
                        errorMsg.append("<br/>");
                        errorMsg.append("sheet[" + MessageUtils.message("import.sheetname.ps_basic_info") + "}" + e.getMessage());
                        errorMsg.append("<br/>");
                        continue;
                    }
                }
                logger.debug("file {} get psBasicInfoList data finished!", fileRealName);
                psCalculationResultList = psCalculationResultUtil.importExcel(MessageUtils.message("import.sheetname.ps_calculation_result"), true, true, file.getInputStream());
                logger.debug("file {} get psCalculationResultList data finished!", fileRealName);
                String[] psPayrollRequire = {MessageUtils.message("excel.payroll.id_no"), MessageUtils.message("excel.payroll.name")};
                psPayrollList = psPayrollUtil.importExcel(MessageUtils.message("import.sheetname.ps_payroll"), true, true, file.getInputStream(), 5, 6, psPayrollRequire);
                logger.debug("file {} get psPayrollList data finished!", fileRealName);
                // psPayrollTaxList = psPayrollTaxUtil.importExcel(MessageUtils.message("import.sheetname.ps_payroll_tax"),true, true, file.getInputStream());
                // logger.debug("file {} get psPayrollTaxList data finished!", fileRealName);
                String[] psPayslipRequire = {MessageUtils.message("excel.payslip.id_no"), MessageUtils.message("excel.payroll.name")};
                psPayslipList = psPayslipUtil.importExcel(MessageUtils.message("import.sheetname.ps_payslip"), true, true, file.getInputStream(), 4, 5, psPayslipRequire);
                logger.debug("file {} get psPayslipList data finished!", fileRealName);
                // psYearEndNonusList = psYearEndNonusUtil.importExcel(MessageUtils.message("import.sheetname.ps_year_end_bonus"),true, true, file.getInputStream());
                // logger.debug("file {} get psYearEndBonusList data finished!", fileRealName);

                // 年假的sheet不是必须
                psLeaveList = psPsLeaveUtil.importExcel(MessageUtils.message("import.sheetname.ps_leave"), false, false, file.getInputStream());


                if (psLeaveList != null) {
                    for (PsLeave leaveInfo : psLeaveList) {
                        if (StringUtils.isNotEmpty(leaveInfo.getEeNo()) && StringUtils.isNotEmpty(leaveInfo.getLeaveName())) {
                            PsLeave selectParam = new PsLeave();
                            selectParam.setEeNo(leaveInfo.getEeNo());
                            selectParam.setLeaveName(leaveInfo.getLeaveName());
                            List<PsLeave> deleteLeaves = psLeaveService.selectPsLeaveList(selectParam);
                            if (deleteLeaves != null && deleteLeaves.size() > 0) {
                                List<Long> ids = deleteLeaves.stream().filter(o -> o.getId() != null).map(PsLeave::getId).distinct().collect(Collectors.toList());
                                psLeaveService.deletePsLeaveByIds(ids);
                            }
                            psLeaveService.insertPsLeave(leaveInfo);
                        }
                    }
                }

                //获取导入期间
                String duration = "";

                if (StringUtils.isEmpty(uploadFileName)) {
                    uploadFileName = fileRealName;
                } else {
                    uploadFileName = uploadFileName + "," + fileRealName;
                }
                logger.debug("file {} get duration finished!", fileRealName);

                String regex = "\\d{4}(0[1-9]|1[0-2])";
                Pattern p = Pattern.compile(regex);
                Matcher matcher = p.matcher(fileRealName);
                if (matcher.find()) {
                    duration = matcher.group(0);
                } else {
                    errorMsg.append(MessageUtils.message("msg.errer.file_name_incorrect"));
                }

                // 删除旧数据
                List<String> clientCodes = new ArrayList<String>();
                for (PsBasicInfo basic : psBasicInfoList) {
                    if (StringUtils.isNotEmpty(basic.getClientCode()) && !clientCodes.contains(basic.getClientCode())) {
                        clientCodes.add(basic.getClientCode());
                    }
                }
                // clientCode去重复
                clientCodes = clientCodes.stream().distinct().collect(Collectors.toList());
                psBasicInfoService.deletePaymentNotice(duration, clientCodes);

                String psBasicInfoMessage = psBasicInfoService.importPsBasicInfo(psBasicInfoList, updateSupportBat, duration);
                String psCalculationResultMessage = psCalculationResultService.importPsCalculationResult(psCalculationResultList, updateSupportBat, duration);
                String psPayrollMessage = psPayrollService.importPsPayroll(psPayrollList, updateSupportBat, duration);
                // String psPayrollTaxMessage = psPayrollTaxService.importPsPayrollTax(psPayrollTaxList, updateSupportBat, duration, groupIdsBat);
                String psPayslipMessage = psPayslipService.importPsPayslip(psPayslipList, updateSupportBat, duration);
                // String psYearEndBonusMessage = psYearEndBonusService.importPsYearEndBonus(psYearEndNonusList, updateSupportBat, duration, groupIdsBat);

                // 从文件封面取汇率信息
                setCurrencyRate(file, duration, psBasicInfoList);

                messageStringBuilder.append(fileRealName);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psBasicInfoMessage);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psCalculationResultMessage);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psPayrollMessage);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psPayslipMessage);
                message += messageStringBuilder.toString();

            } catch (Exception e) {
                logger.error(e.getMessage());
                // errorMsg.append(fileRealName + "upload failure!");
                errorMsg.append(fileRealName + e.getMessage());
                errorMsg.append("<br/>");
            }
        }

//        if (StringUtils.isNotEmpty(groupIdsBat) && PayrollConstants.MAIL_MODE.equals(mailMode) && sendMailBat) {
            //发邮件
//            try {
//                String subject = MimeUtility.encodeWord(PayrollConstants.MAIL_TITLE, PayrollConstants.MAIL_CHARSET, PayrollConstants.MAIL_ENCODING);
//                String content = MailTemplate.getContentByTemplate(PayrollConstants.MAIL_TEMPLATE_FILE1, remarksBat, PayrollConstants.MAIL_DEPT_IT, DateUtils.getDate());

//                String[] userIds = leaderIdBat.split(",");
//                List<String> emailList = new ArrayList<String>();
//                for (String userId : userIds) {
//                    SysUser u = userService.selectUserById(Long.parseLong(userId));
//                    if (StringUtils.isNotEmpty(u.getEmail())) {
//                        emailList.add(u.getEmail());
//                    }
//                }
                // 根据组编号查询所有组员的邮箱
//                List<SysUser> userList = sysGroupService.selectUserByGroupIds(groupIdsBat);
//                List<String> emailList = new ArrayList<String>();
//                for (SysUser user : userList) {
//                    if (StringUtils.isNotEmpty(user.getEmail())) {
//                        emailList.add(user.getEmail());
//                    }
//                }
//                // 去重复
//                emailList = emailList.stream().distinct().collect(Collectors.toList());
//                if (emailList.size() != 0) {
//                    String[] toAddress = new String[emailList.size()];
//                    emailList.toArray(toAddress);
//                    // 发送邮件
//                    SysMailServiceImpl.mailSend(subject, content, null, toAddress, null, null);
//                }
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                return AjaxResult.error(MessageUtils.message("mail.send_failure"));
//            }
//        }

//        SysUser sysUser = ShiroUtils.getSysUser();
        PsOpLog pol = new PsOpLog();
        pol.setCreateBy(SecurityUtils.getUsername());
//        pol.setRoleName(sysUser.getRoles().get(0).getRoleName());
        pol.setImputFileName(uploadFileName);
        pol.setOpType("批量导入");
//        if (leaderIdBat.length() == 0) {
//            if (sysUser.isAdmin()) {
//                pol.setGroupIds(ShiroUtils.getUserId().toString());
//            } else {
//                pol.setGroupIds("1," + ShiroUtils.getUserId().toString());
//            }
//        } else {
//            if (sysUser.isAdmin()) {
//                pol.setGroupIds(leaderIdBat + "," + ShiroUtils.getUserId().toString());
//            } else {
//                pol.setGroupIds(leaderIdBat + ",1," + ShiroUtils.getUserId().toString());
//            }
//
//        }
        // 设置权限组
        if (StringUtils.isNotEmpty(groupIdsBat)) {
            pol.setGroupIds(groupIdsBat);
        }

        psOpLogService.insertPsOpLog(pol);
        if (StringUtils.isEmpty(errorMsg.toString())) {
            return AjaxResult.success(message);
        } else {
            return AjaxResult.error(errorMsg.toString() + message);
        }
    }

    @Log(title = "ps_op_log", businessType = BusinessType.IMPORT)
    @RequiresPermissions("payroll:ps_op_log:importBat")
    @PostMapping("/importHisData")
    @ResponseBody
    public AjaxResult importHisData(MultipartHttpServletRequest files, boolean updateSupportBat, String groupIdsBat, String remarksBat, boolean sendMailBat) throws Exception {
        String message = "";
        String uploadFileName = "";
        StringBuffer errorMsg = new StringBuffer();
        for (MultipartFile file : files.getFiles("file")) {
            String fileRealName = file.getOriginalFilename();//获得原始文件名

            if (fileRealName.contains("/PN/")) {
                logger.debug("file {} upload start!", fileRealName);
            } else {
                logger.debug("file {} upload skip!", fileRealName);
                continue;
            }

            logger.debug("file {} upload start!", fileRealName);

            HroneExcelUtil<PsBasicInfoHis> psBasicInfoUtil = new HroneExcelUtil<>(PsBasicInfoHis.class);
            HroneExcelUtil<PsCalculationResultHis> psCalculationResultUtil = new HroneExcelUtil<>(PsCalculationResultHis.class);
            HroneExcelUtil<PsBasicUnfixedInfoHis> psBasicUnfixedInfoHisUtil = new HroneExcelUtil<>(PsBasicUnfixedInfoHis.class);

            List<PsBasicInfoHis> psBasicInfoList = null;
            List<PsCalculationResultHis> psCalculationResultList = null;
            List<PsBasicUnfixedInfoHis> psBasicUnfixedInfoHisList = null;

            //导入信息
            StringBuilder messageStringBuilder = new StringBuilder();

            try {
                if (fileRealName.contains("/")) {
                    String[] fileArry = fileRealName.split("/");
                    fileRealName = fileArry[fileArry.length - 1];
                }

                psBasicInfoList = psBasicInfoUtil.importExcel(MessageUtils.message("import.sheetname.ps_basic_info"), true, false, file.getInputStream());
                logger.debug("file {} get psBasicInfoList data finished!", fileRealName);
                psCalculationResultList = psCalculationResultUtil.importExcel(MessageUtils.message("import.sheetname.ps_calculation_result"), true, false, file.getInputStream());
                logger.debug("file {} get psCalculationResultList data finished!", fileRealName);
                psBasicUnfixedInfoHisList = psBasicUnfixedInfoHisUtil.importExcel("变动信息", false, false, file.getInputStream());
                logger.debug("file {} get psBasicInfoHisList data finished!", fileRealName);

                //获取导入期间
                String duration = "";

                if (StringUtils.isEmpty(uploadFileName)) {
                    uploadFileName = fileRealName;
                } else {
                    uploadFileName = uploadFileName + "," + fileRealName;
                }
                logger.debug("file {} get duration finished!", fileRealName);

                String regex = "\\d{4}(0[1-9]|1[0-2])";
                Pattern p = Pattern.compile(regex);
                Matcher matcher = p.matcher(fileRealName);
                if (matcher.find()) {
                    duration = matcher.group(0);
                } else {
                    errorMsg.append(MessageUtils.message("msg.errer.file_name_incorrect"));
                }


                String psBasicInfoMessage = psBasicInfoHisService.importPsBasicInfo(psBasicInfoList, updateSupportBat, duration, groupIdsBat);
                String psCalculationResultMessage = psCalculationResultHisService.importPsCalculationResult(psCalculationResultList, updateSupportBat, duration, groupIdsBat);
                String importRetMsg = psBasicUnfixedInfoHisService.importPsBasicUnfixedInfo(psBasicUnfixedInfoHisList, updateSupportBat, duration, groupIdsBat);

                messageStringBuilder.append(fileRealName);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psBasicInfoMessage);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(psCalculationResultMessage);
                messageStringBuilder.append("<br/>");
                messageStringBuilder.append(importRetMsg);
                messageStringBuilder.append("<br/>");
                message += messageStringBuilder.toString();

            } catch (Exception e) {
                logger.error(e.getMessage());
                // errorMsg.append(fileRealName + "upload failure!");
                errorMsg.append(fileRealName + e.getMessage());
                errorMsg.append("<br/>");
            }
        }


//        SysUser sysUser = ShiroUtils.getSysUser();
        PsOpLog pol = new PsOpLog();
        pol.setCreateBy(SecurityUtils.getUsername());
//        pol.setRoleName(sysUser.getRoles().get(0).getRoleName());
        pol.setImputFileName(uploadFileName);
        pol.setOpType("批量导入");

        // 设置权限组
        if (StringUtils.isNotEmpty(groupIdsBat)) {
            pol.setGroupIds(groupIdsBat);
        }

        psOpLogService.insertPsOpLog(pol);
        if (StringUtils.isEmpty(errorMsg.toString())) {
            return AjaxResult.success(message);
        } else {
            return AjaxResult.error(errorMsg.toString() + message);
        }
    }

    /**
     * 追加导入
     *
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "ps_op_log", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('payroll:ps_op_log:import')")
    @PostMapping("/importAddData")
    @ResponseBody
    public AjaxResult importAddData(@RequestParam("file") MultipartFile file,@RequestParam("updateSupport") boolean updateSupport) throws Exception {
        String message = "";
        String duration = "";

        HroneExcelUtil<PsBasicInfo> psBasicInfoUtil = new HroneExcelUtil<>(PsBasicInfo.class);
        HroneExcelUtil<PsCalculationResult> psCalculationResultUtil = new HroneExcelUtil<>(PsCalculationResult.class);
        HroneExcelUtil<PsPayroll> psPayrollUtil = new HroneExcelUtil<>(PsPayroll.class);
        HroneExcelUtil<PsPayslip> psPayslipUtil = new HroneExcelUtil<>(PsPayslip.class);
        HroneExcelUtil<PsLeave> psPsLeaveUtil = new HroneExcelUtil<>(PsLeave.class);

        List<PsBasicInfo> psBasicInfoList = null;
        List<PsCalculationResult> psCalculationResultList = null;
        List<PsPayroll> psPayrollList = null;
        // List<PsPayrollTax> psPayrollTaxList = null;
        List<PsPayslip> psPayslipList = null;
        // List<PsYearEndBonus> psYearEndNonusList = null;
        List<PsLeave> psLeaveList = null;

        try {
            psBasicInfoList = psBasicInfoUtil.importExcel("基本信息", true, true, file.getInputStream());
            if (!Pattern.matches("^(FDI|HROne) Salary [0-9]{6}[.](xlsx|xls)$", file.getOriginalFilename())) {
                try {
                    psBasicInfoService.checkRequire(psBasicInfoList);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return AjaxResult.error(e.getMessage());
                }
            }
            psCalculationResultList = psCalculationResultUtil.importExcel("计算结果", true, true, file.getInputStream());
            String[] psPayrollRequire = {"ID No. 工号","Name 姓名Name 姓名"};
            psPayrollList = psPayrollUtil.importExcel("S-Payroll Report", true,true, file.getInputStream(), 5, 6, psPayrollRequire);
            String[] psPayslipRequire = {"ID No. 工号", "Name 姓名"};
            psPayslipList = psPayslipUtil.importExcel("S-Payslip", true, true, file.getInputStream(), 4, 5, psPayslipRequire);

            // 年假的sheet不是必须
            psLeaveList = psPsLeaveUtil.importExcel("带薪假期", false, false, file.getInputStream());

            if (psLeaveList != null) {
                for (PsLeave leaveInfo : psLeaveList) {
                    if (StringUtils.isNotEmpty(leaveInfo.getEeNo()) && StringUtils.isNotEmpty(leaveInfo.getLeaveName())) {
                        PsLeave selectParam = new PsLeave();
                        selectParam.setEeNo(leaveInfo.getEeNo());
                        selectParam.setLeaveName(leaveInfo.getLeaveName());
                        List<PsLeave> deleteLeaves = psLeaveService.selectPsLeaveList(selectParam);
                        if (deleteLeaves != null && deleteLeaves.size() > 0) {
                            List<Long> ids = deleteLeaves.stream().filter(o -> o.getId() != null).map(PsLeave::getId).distinct().collect(Collectors.toList());
                            psLeaveService.deletePsLeaveByIds(ids);

                        }
                        psLeaveService.insertPsLeave(leaveInfo);
                    }
                }
            }

            //获取导入期间
//            SysUser sysUser = ShiroUtils.getSysUser();
            String filename = file.getOriginalFilename();//获得原始文件名

            String regex = "\\d{4}(0[1-9]|1[0-2])";
            Pattern p = Pattern.compile(regex);
            Matcher matcher = p.matcher(filename);
            if (matcher.find()) {
                duration = matcher.group(0);
            } else {
                return error("文件名不正确");
            }

            //导入信息
            StringBuilder messageStringBuilder = new StringBuilder();
            String psBasicInfoMessage = psBasicInfoService.importPsBasicInfo(psBasicInfoList, updateSupport, duration);
            String psCalculationResultMessage = psCalculationResultService.importPsCalculationResult(psCalculationResultList, updateSupport, duration);
            String psPayrollMessage = psPayrollService.importPsPayroll(psPayrollList, updateSupport, duration);
            String psPayslipMessage = psPayslipService.importPsPayslip(psPayslipList, updateSupport, duration);


            // 从文件封面取汇率信息
            setCurrencyRate(file, duration, psBasicInfoList);

            messageStringBuilder.append(psBasicInfoMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psCalculationResultMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayrollMessage);
            messageStringBuilder.append("<br/>");
            messageStringBuilder.append(psPayslipMessage);
            message = messageStringBuilder.toString();

            PsOpLog pol = new PsOpLog();
            pol.setCreateBy(SecurityUtils.getUsername());
//            pol.setRoleName(sysUser.getRoles().get(0).getRoleName());
            pol.setImputFileName(filename);
            pol.setOpType("追加导入");
            psOpLogService.insertPsOpLog(pol);

        } catch (Exception e) {
            logger.error(e.getMessage());
            if (StringUtils.isNotEmpty(e.getMessage())) {
                return AjaxResult.error("文件导入失败!" + e.getMessage());
            } else {
                return AjaxResult.error("文件导入失败!");
            }
        }

        return AjaxResult.success(message.toString());
    }

    @PostMapping("/search")
    @ResponseBody
    public AjaxResult search(MultipartFile file) {

        PsOpLog psOpLog = new PsOpLog();
        String fileRealName = file.getOriginalFilename();//获得原始文件名
        if (fileRealName.contains("/")) {
            String[] fileArry = fileRealName.split("/");
            fileRealName = fileArry[fileArry.length - 1];
        }
        psOpLog.setImputFileName(fileRealName);
        List<PsOpLog> list = psOpLogService.selectPsOpLogList(psOpLog);
        StringBuilder sb = new StringBuilder();
        String message = "";
        if (list.size() > 0) {
            PsOpLog p = list.get(0);
            sb.append("该文件在");
            sb.append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, p.getCreateTime()));
            sb.append("已由");
            sb.append(p.getCreateBy());
            sb.append("导入您是否还要再导入并且覆盖？<br/>");
            message = sb.toString();
        }
        return AjaxResult.success(message);
    }

    @PostMapping("/searchAll")
    @ResponseBody
    public AjaxResult searchAll(MultipartHttpServletRequest files) {
        String message = "";
        for (MultipartFile file : files.getFiles("file")) {
            PsOpLog psOpLog = new PsOpLog();
            String fileRealName = file.getOriginalFilename();//获得原始文件名
            if (fileRealName.contains("/")) {
                String[] fileArry = fileRealName.split("/");
                fileRealName = fileArry[fileArry.length - 1];
            }
            psOpLog.setImputFileName(fileRealName);
            List<PsOpLog> list = psOpLogService.selectPsOpLogList(psOpLog);
            StringBuilder sb = new StringBuilder();

            if (list.size() > 0) {
                sb.append(MessageUtils.message("code.bat.overwrite.data"));
                message = message + sb.toString();
                break;
            }
        }

        return AjaxResult.success(message);
    }

    private void setCurrencyRate(MultipartFile file, String duration, List<PsBasicInfo> psBasicInfoList) throws IOException {
        // 从文件封面取汇率信息
        if (!psBasicInfoList.isEmpty()) {
             String erNo = psBasicInfoList.get(0).getClientCode();
             String erName = psBasicInfoList.get(0).getErName();
            // 公司编号去重复
            List<String> erNoList = psBasicInfoList.stream().map(PsBasicInfo::getClientCode).distinct().collect(Collectors.toList());
            // 读取sheet
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet spnSheet = wb.getSheet("S-Payment Notice");
            XSSFSheet pnSheet = wb.getSheet("Payment Notice");
            // 初始值
            String currency = "CNY", currency2 = "USD";
            BigDecimal exchangeRate = BigDecimal.ONE, exchangeRate2 = null;
            // 从封面的指定单元格读取汇率和货币
            if (pnSheet != null) {
                for (int i = 10; i < pnSheet.getLastRowNum(); i++) {
                    String cellValue = null;
                    if (pnSheet.getRow(i) != null && pnSheet.getRow(i).getCell(0) != null && pnSheet.getRow(i).getCell(0).getCellType() == CellType.STRING) {
                        cellValue = pnSheet.getRow(i).getCell(0).getStringCellValue();
                    }
                    if (StringUtils.isNotEmpty(cellValue) && (cellValue.contains(PayrollConstants.EXCEL_KEYWORD_EXCHANGE_RATE))) {
                        exchangeRate = new BigDecimal(pnSheet.getRow(i).getCell(2).getNumericCellValue());
                        cellValue = pnSheet.getRow(i).getCell(3).getStringCellValue();
                        if (cellValue.length() > 3) {
                            currency = cellValue.substring(3);
                        } else {
                            currency = "USD";
                        }
                        // 判断下一行是否也是汇率
                        cellValue = pnSheet.getRow(i + 1).getCell(0).getStringCellValue();
                        if (StringUtils.isNotEmpty(cellValue) && (cellValue.contains(PayrollConstants.EXCEL_KEYWORD_EXCHANGE_RATE))) {
                            exchangeRate2 = new BigDecimal(pnSheet.getRow(i + 1).getCell(2).getNumericCellValue());
                            cellValue = pnSheet.getRow(i + 1).getCell(3).getStringCellValue();
                            if (cellValue.length() > 3) {
                                currency2 = cellValue.substring(3);
                            } else {
                                currency2 = "CNY";
                            }
                        }
                        break;
                    }
                }
            } else if (spnSheet != null) {
                for (int i = 16; i < spnSheet.getLastRowNum(); i++) {
                    String cellValue = null;
                    if (spnSheet.getRow(i) != null && spnSheet.getRow(i).getCell(1) != null && spnSheet.getRow(i).getCell(1).getCellType() == CellType.STRING) {
                        cellValue = spnSheet.getRow(i).getCell(1).getStringCellValue();
                    }
                    if (StringUtils.isNotEmpty(cellValue) && (cellValue.contains(PayrollConstants.EXCEL_KEYWORD_EXCHANGE_RATE))) {
                        exchangeRate = new BigDecimal(spnSheet.getRow(i).getCell(2).getNumericCellValue());
                        // 判断下一行是否也是汇率
                        cellValue = spnSheet.getRow(i + 1).getCell(1).getStringCellValue();
                        if (StringUtils.isNotEmpty(cellValue) && (cellValue.contains(PayrollConstants.EXCEL_KEYWORD_EXCHANGE_RATE))) {
                            exchangeRate2 = new BigDecimal(spnSheet.getRow(i + 1).getCell(2).getNumericCellValue());
                            cellValue = spnSheet.getRow(i + 2).getCell(3).getStringCellValue();
                            if (cellValue.length() > 4) {
                                currency = cellValue.substring(3);
                            }
                            cellValue = spnSheet.getRow(i + 1).getCell(3).getStringCellValue();
                            if (cellValue.length() > 4) {
                                currency2 = cellValue.substring(3);
                            }
                        } else {
                            // 只有一行汇率时,下一行是货币
                            currency = spnSheet.getRow(i + 1).getCell(3).getStringCellValue();
                            if (currency.length() > 3) {
                                currency = currency.substring(3);
                            }
                        }
                        break;
                    }
                }
            }
            // 汇率数据入库
            for (String erNoDetail : erNoList) {
                if (StringUtils.isNotEmpty(erNoDetail)) {
                    PsCoverInfo psCoverInfo = new PsCoverInfo();
                    psCoverInfo.setDuration(duration);
                    psCoverInfo.setErNo(erNoDetail);
                    // 数据库存在汇率数据时先删除再插入
                    List<PsCoverInfo> psCoverInfoList = psOpLogService.selectPsCoverInfoList(psCoverInfo);
                    if (!psCoverInfoList.isEmpty()) {
                        psOpLogService.deletePsCoverInfoByCondition(psCoverInfo);
                    }
                    // 插入数据
                    // psCoverInfo.setErName(erName);
                    psCoverInfo.setExchangeRate(exchangeRate);
                    psCoverInfo.setCurrency(currency);
                    psOpLogService.insertPsCoverInfo(psCoverInfo);
                    // 隐藏行汇率
                    if (exchangeRate2 != null && !BigDecimal.ZERO.equals(exchangeRate2)) {
                        psCoverInfo.setExchangeRate(exchangeRate2);
                        psCoverInfo.setCurrency(currency2);
                        // 标记多余的汇率数据
                        psCoverInfo.setGroupIds("extra_USD");
                        psOpLogService.insertPsCoverInfo(psCoverInfo);
                    }
                }
            }
        }
    }

}
