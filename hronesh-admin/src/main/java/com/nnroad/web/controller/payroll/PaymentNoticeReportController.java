package com.nnroad.web.controller.payroll;

import com.alibaba.fastjson2.JSONObject;
import com.nnroad.client.domain.ClientBillinginfo;
import com.nnroad.client.service.ISysClientInfoService;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.constants.ClientConstants;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.ClientEmployeeCalculate;
import com.nnroad.payroll.domain.Employer;
import com.nnroad.payroll.domain.common.*;
import com.nnroad.payroll.domain.exportC.*;
import com.nnroad.payroll.service.*;
import com.nnroad.web.controller.common.CommonController;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Payment_Notice_Report_Controller

 */
@Controller
@RequestMapping("/payroll/payment_notice")
public class PaymentNoticeReportController extends BaseController {

    @Autowired
    private IPaymentNoticeReportService paymentNoticeReportService;

    @Autowired
    private ISocialBenefitReportService socialBenefitReportService;

    @Autowired
    private IIITReportService iITReportService;

    @Autowired
    private IPsOpLogService psOpLogService;

    @Autowired
    private IPsPnExportLogService psPnExportLogService;

    @Autowired
    IPsPnTemplateService psPnTemplateService;

    @Autowired
    IPsBasicInfoService psBasicInfoService;

    @Autowired
    IPsCalculationResultService psCalculationResultService;

    @Autowired
    IPsPayrollService psPayrollService;

    @Autowired
    IPsPayslipService psPayslipService;

    @Autowired
    ISendPaymentService sendPaymentService;

    @Autowired
    private IClientEmployeeCalculateService clientEmployeeCalculateService;


    @Autowired
    private ISysClientInfoService clientInfoService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:view')")
    @GetMapping
    @ResponseBody
    public AjaxResult payment_details_report() {
        List<Employer> erList = paymentNoticeReportService.selectAllClient();
        return AjaxResult.success(erList);
    }

    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:view')")
    @GetMapping("/employee_list")
    @ResponseBody
    public AjaxResult employeeList( @RequestParam(required = false) String duration,
                                    @RequestParam(required = false) String clientCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("duration", duration);
        map.put("clientCode", clientCode);
        List<PsBasicInfo> eeList = paymentNoticeReportService.selectEmployeeListByClient(map);
        return AjaxResult.success(eeList);
    }


    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:view')")
    @PutMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody SPayrollReport sPayrollReport,
                              @RequestParam(value = "fileStatus", required = false) String fileStatusStr) {


        startPage();
        PsPnExportLog psPnExportLog = new PsPnExportLog();
        // 检查 fileStatus 参数并进行相应处理
        if (fileStatusStr != null && !fileStatusStr.trim().isEmpty()) {
            try {
                Long fileStatus = Long.parseLong(fileStatusStr);
                psPnExportLog.setFileStatus(fileStatus);
            } catch (NumberFormatException e) {
                // 处理无效的 fileStatus 值
                System.out.println("Invalid fileStatus value: " + fileStatusStr);
            }
        }

        psPnExportLog.setDuration(sPayrollReport.getDuration());
        psPnExportLog.setErNo(sPayrollReport.getErId());

        List<PsPnExportLog> list = psPnExportLogService.selectPsPnExportLogList(psPnExportLog);

        return getDataTable(list);
    }


    @ResponseBody
    @Log(title = "Send to HK", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:send')")
    @PostMapping("/sendToHROneHK")
    public AjaxResult sendToHK(@RequestBody SPayrollReport sPayrollReport) {
        return handleSendPayment(sPayrollReport, "HROneHK");
    }


    @ResponseBody
    @Log(title = "Send to TOP FDI", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:send')")
    @PostMapping("/sendToTopFDIHK")
    public AjaxResult sendToTopFDI(@RequestBody SPayrollReport sPayrollReport) {
        return handleSendPayment(sPayrollReport, "TOPFDIHK");
    }

    private AjaxResult handleSendPayment(SPayrollReport sPayrollReport, String dataSource) {
        // 使用 split() 分割字符串并将其转换为 List
        List<String> erName = Arrays.asList(sPayrollReport.getErName().split(","));
        List<String> erId = Arrays.asList(sPayrollReport.getErId().split(","));

        boolean allSuccess = true; // 标记所有操作是否成功
        StringBuilder errorMessages = new StringBuilder(); // 收集错误信息

        for (int i = 0; i < erName.size(); i++) {
            // 获取每个 erName 和 erId 对应的值
            String name = erName.get(i);
            String erNO = erId.get(i);
            String servicePartB=paymentNoticeReportService.selectServiceContractPartyB(erNO);
            if (StringUtils.isEmpty(servicePartB)) {
                return AjaxResult.error("Service Contract Party B is missing for Client: " + erNO+":"+name);
            }


            Map<String, Object> map = new HashMap<>();
            map.put("duration", sPayrollReport.getDuration());
            map.put("erName", sPayrollReport.getErName());
            map.put("erNo", erNO);
            // 选择雇员时只导出选中的数据
            if (StringUtils.isNotEmpty(sPayrollReport.getIdNo())) {
                map.put("idNo", "'" + sPayrollReport.getIdNo().replaceAll(",", "','") + "'");
            }
            List<SPayrollReport> SPayrollList = paymentNoticeReportService.selectSPayrollList(map);

            for (SPayrollReport report : SPayrollList) {
                String duration=report.getDuration();
                String eeCode=report.getIdNo();

                PsBasicInfo psBasicInfo=new PsBasicInfo();
                psBasicInfo.setIdNo(eeCode);
                psBasicInfo.setDuration(duration);
                int basicResult = sendPaymentService.SendPsBasicInfo(psBasicInfo,servicePartB,dataSource);
                if (basicResult == 0) {
                    allSuccess = false;
                    errorMessages.append("Failed to send PsBasicInfo for ID: ").append(eeCode).append("\n");
                }

                PsCalculationResult psCalculationResult=new PsCalculationResult();
                psCalculationResult.setDuration(duration);
                psCalculationResult.setIdNo(eeCode);
                int CalResult = sendPaymentService.SendPsCalculationResult(psCalculationResult,servicePartB,dataSource);
                if (CalResult == 0) {
                    allSuccess = false;
                    errorMessages.append("Failed to send PsCalculationResult for ID: ").append(eeCode).append("\n");
                }

                PsPayslip psPayslip=new PsPayslip();
                psPayslip.setDuration(duration);
                psPayslip.setIdNo(eeCode);
                int payslipResult = sendPaymentService.SendPsPayslip(psPayslip,servicePartB,dataSource);
                if (payslipResult== 0) {
                    allSuccess = false;
                    errorMessages.append("Failed to send PsPayslip for ID: ").append(eeCode).append("\n");
                }

                PsPayroll psPayroll=new PsPayroll();
                psPayroll.setDuration(duration);
                psPayroll.setIdNo(eeCode);
                int PsPayrollResult = sendPaymentService.SendPsPayroll(psPayroll,psPayslip,servicePartB,dataSource);
                if (PsPayrollResult == 0) {
                    allSuccess = false;
                    errorMessages.append("Failed to send PsPayroll for ID: ").append(eeCode).append("\n");
                }


                PsCoverInfo psCoverInfo=new PsCoverInfo();
                psCoverInfo.setDuration(duration);
                psCoverInfo.setErNo(erNO);
                int CoverResult = sendPaymentService.SendPsCoverInfo(psCoverInfo,servicePartB,dataSource);
                if (CoverResult == 0) {
                    allSuccess = false;
                    errorMessages.append("Failed to send Currency for ID: ").append(erNO).append("\n");
                }


            }
        }
        if (allSuccess) {
            return success(); // 全部成功
        } else {
            return AjaxResult.error("Some operations failed", errorMessages.toString());
        }
    }




    /**
     * 导出payment_notice_reportC版
     * GENERATE button
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:export')")
    @Log(title = "payment_notice", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult exportAll(@RequestBody SPayrollReport sPayrollReport) throws Exception{
        // 使用 split() 分割字符串并将其转换为 List
        List<String> erName = Arrays.asList(sPayrollReport.getErName().split(","));
        List<String> erId = Arrays.asList(sPayrollReport.getErId().split(","));
        boolean isVendor= false;
        String filename="";
        for (int i = 0; i < erName.size(); i++) {
            // 获取每个 erName 和 erId 对应的值
            String name = erName.get(i);
            String id = erId.get(i);

            sPayrollReport.setErName(name);
            sPayrollReport.setErId(id);
            // 输出或处理每对 erName 和 erId
            filename += export(sPayrollReport,isVendor);
            System.out.println(filename);
        }

        if (filename.startsWith("ERROR:")) {
            // 提取错误信息并提示用户
            return error(filename.substring(6)); // 截取掉 "ERROR:" 部分
        }else{
            return success(filename);
        }

    }

    /**
     * 导出payment_notice_reportC版
     * GENERATE button
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:export')")
    @Log(title = "payment_notice", businessType = BusinessType.EXPORT)
    @PostMapping("/exportVendor")
    @ResponseBody
    public AjaxResult exportVendorAll(@RequestBody SPayrollReport sPayrollReport) throws Exception{
        // 使用 split() 分割字符串并将其转换为 List
        List<String> erName = Arrays.asList(sPayrollReport.getErName().split(","));
        List<String> erId = Arrays.asList(sPayrollReport.getErId().split(","));
        boolean isVendor=true;

        String filename="";
        for (int i = 0; i < erName.size(); i++) {
            // 获取每个 erName 和 erId 对应的值
            String name = erName.get(i);
            String id = erId.get(i);

            sPayrollReport.setErName(name);
            sPayrollReport.setErId(id);
            // 输出或处理每对 erName 和 erId
            filename += export(sPayrollReport,isVendor);
            System.out.println(filename);
        }

        if (filename.startsWith("ERROR:")) {
            // 提取错误信息并提示用户
            return error(filename.substring(6)); // 截取掉 "ERROR:" 部分
        }else{
            return success(filename);
        }

    }


    public String export(SPayrollReport sPayrollReport,Boolean isVendor) throws Exception {
        // 模版路径
        String filePath = null;
        XSSFWorkbook wb = null;
        FileOutputStream out = null;
        FileOutputStream fos = null;
        String erType = null;
        String erNo = null;
        String erName = "";
        String eeServicePartB=null;
        String org= null;
        int errRowNum = 0, errColNum = 0;
        try {
            // 根据公司编号查询公司类型，根据公司类型来选择用不同的模板
            if (StringUtils.isNotEmpty(sPayrollReport.getErId())) {
                Employer employer = paymentNoticeReportService.selectClientInfo(sPayrollReport.getErId());
                erType = employer.getErType();
                erNo = employer.getErNo();
                erName = employer.getErName();
                org = employer.getOrg();

            }
            String filePathPrefix = NNRoadConfig.getDownloadPath() + "PN_invoice/" + sPayrollReport.getErName();
            // 路径不存在时要重新创建
            File dir = new File(filePathPrefix);
            if(!dir.exists()){
                dir.mkdirs();
            }
            // 有专属模板时用专属模板
            if (ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType) || ClientConstants.SERVICE_TYPE_CONSULTANT_CHANNEL.equals(erType)) {
                if (ClientConstants.Organization_SH.equals(org)){
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT_SH.replaceAll("#0#", erNo);
                }else if (ClientConstants.Organization_BJ.equals(org)){
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT_BJ.replaceAll("#0#", erNo);
                } else {
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT.replaceAll("#0#", erNo);
                }
            } else {
                if (ClientConstants.Organization_SH.equals(org)){
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT_SH.replaceAll("#0#", erNo);
                }else if (ClientConstants.Organization_BJ.equals(org)){
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT_BJ.replaceAll("#0#", erNo);
                } else {
                    filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CLIENT.replaceAll("#0#", erNo);
                }
            }

            // 从finance数据库查询封面的打款银行信息
            PsCoverInfo bankInfo = paymentNoticeReportService.selectBankInfo(erNo);
            if (bankInfo == null) {
                logger.error("银行信息为空，无法导出！erNo: {}", erNo);
                return "ERROR:银行信息为空，请联系管理员！";
            }

            // 打款到FDI账户时用FDI模板
            if (PayrollConstants.PAYMENT_RECEIVED_FDI.equals(bankInfo.getErNo())) {
                filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FDI_FILENAME_PN_CLIENT.replaceAll("#0#", erNo);
            }
//            获取Client contract info中Service Contract Part B
            String servicePartB=paymentNoticeReportService.selectServiceContractPartyB(erNo);
            if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(servicePartB) &&
                    (ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType) || ClientConstants.SERVICE_TYPE_CONSULTANT_CHANNEL.equals(erType))) {
                filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CONSULTANT_CLIENT.replaceAll("#0#", erNo);
            } else if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(servicePartB)) {
                filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CLIENT.replaceAll("#0#", erNo);
            }

            if (PayrollConstants.Client_Source_HK.equals(org)){
                if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                    filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_SH_HK;
                }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                    filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_BJ_HK;
                }
            }else if (PayrollConstants.Client_Source_TopFDI.equals(org)){
                if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                    filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_SH_TOPFDI;
                }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                    filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_BJ_TOPFDI;
                }
            }


            File file = new File(filePath);
            if (!file.exists()) {
                // hrone客户
                if (ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType) || ClientConstants.SERVICE_TYPE_CONSULTANT_CHANNEL.equals(erType)) {
                    if(ClientConstants.Organization_SH.equals(org)){
                        filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT_SH;
                    }else if (ClientConstants.Organization_BJ.equals(org)){
                        filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT_BJ;
                    }else {
                        if (PayrollConstants.Client_Source_HK.equals(org)){
                            if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_Consultant_SH_HK;
                            }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_Consultant_BJ_HK;
                            }
                        }else if (PayrollConstants.Client_Source_TopFDI.equals(org)){
                            if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_Consultant_SH_TopFDI;
                            }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_Consultant_BJ_TopFDI;
                            }
                        }else {
                            filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT;
                        }
                    }
                } else {
                    if (ClientConstants.Organization_SH.equals(org)){
                        filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_SH;
                    }else if (ClientConstants.Organization_BJ.equals(org)){
                        filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_BJ;
                    }else {
                        if (PayrollConstants.Client_Source_HK.equals(org)){
                            if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_SH_HK;
                            }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_BJ_HK;
                            }
                        }else if (PayrollConstants.Client_Source_TopFDI.equals(org)){
                            if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_SH_TOPFDI;
                            }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                                filePath=NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN_BJ_TOPFDI;
                            }
                        }else {
                            filePath = NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FILENAME_PN;
                        }

                    }
                }
                // FDI客户
                if (PayrollConstants.PAYMENT_RECEIVED_FDI.equals(bankInfo.getErNo())) {
                    filePath =  NNRoadConfig.getDownloadPath() + PayrollConstants.EXPORT_TEMPLATE_FDI_FILENAME_PN;
                }
//                TOP FDI客户
                if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(servicePartB) &&
                        (ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType) || ClientConstants.SERVICE_TYPE_CONSULTANT_CHANNEL.equals(erType))){
                    filePath= NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CONSULTANT;
                }else if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(servicePartB)){
                    filePath= NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN;
                }

                if (StringUtils.isNotEmpty(sPayrollReport.getIdNo()) && !sPayrollReport.getIdNo().contains(",")) {
                    eeServicePartB=paymentNoticeReportService.selectEmployeeServiceContractPartyB(sPayrollReport.getIdNo());
                    if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(eeServicePartB) &&
                            (ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType) || ClientConstants.SERVICE_TYPE_CONSULTANT_CHANNEL.equals(erType))){
                        filePath= NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CONSULTANT;
                    }else if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(eeServicePartB)){
                        filePath= NNRoadConfig.getDownloadPath()+PayrollConstants.EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN;
                    }
                }
                file = new File(filePath);
            }

            FileInputStream in = new FileInputStream(file);
            // 读取excel模板
            wb = new XSSFWorkbook(in);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            // 读取要填充的sheet内容
            XSSFSheet payrollSheet = wb.getSheet("S-Payroll Report");
            XSSFSheet payslipSheet = wb.getSheet("S-Payslip");
            XSSFSheet hiddenSheet = wb.getSheet("hidden");
            XSSFSheet pnSheet = wb.getSheet("S-Payment Notice");
            XSSFSheet otherFeeSheet = wb.getSheet("Other Fee");
            XSSFSheet employeeInfoSheet = wb.getSheet("Employee Details");
            // 如果这行没有了，整个公式都不会有自动计算的效果的
            payrollSheet.setForceFormulaRecalculation(true);
            payslipSheet.setForceFormulaRecalculation(true);
            // 查询数据
            Map<String, Object> map = new HashMap<>();
            map.put("duration", sPayrollReport.getDuration());
            map.put("erName", sPayrollReport.getErName());
            map.put("erNo", erNo);
            // 选择雇员时只导出选中的数据
            if (StringUtils.isNotEmpty(sPayrollReport.getIdNo())) {
                map.put("idNo", "'" + sPayrollReport.getIdNo().replaceAll(",", "','") + "'");
            }
            List<SPayrollReport> payrollList = paymentNoticeReportService.selectSPayrollList(map);
            List<SPayslipReport> payslipList = paymentNoticeReportService.selectSPayslipList(map);

            List<SOtherFeeReport> otherFeeList = paymentNoticeReportService.selectSOtherFeeList(map);

            // 填充数据到模板
            HroneExcelUtil<SPayrollReport> payrollExcelUtil = new HroneExcelUtil<SPayrollReport>(SPayrollReport.class);
            payrollExcelUtil.fillSheetDataByTemplate(payrollList, payrollSheet, 7);
            HroneExcelUtil<SPayslipReport> payslipExcelUtil = new HroneExcelUtil<SPayslipReport>(SPayslipReport.class);

            payslipExcelUtil.fillSheetDataByTemplate(payslipList, payslipSheet, 7);
            // employee信息sheet存在时
            if (employeeInfoSheet != null) {
                List<SEmployeeInfo> employeeInfoList = paymentNoticeReportService.selectSEmployeeInfoList(map);
                HroneExcelUtil<SEmployeeInfo> employeeInfoUtil = new HroneExcelUtil<SEmployeeInfo>(SEmployeeInfo.class);
                employeeInfoUtil.fillSheetDataByTemplate(employeeInfoList, employeeInfoSheet, 8);
                // 填充周期和客户名
                employeeInfoSheet.getRow(3).getCell(2).setCellValue(sPayrollReport.getDuration());
                employeeInfoSheet.getRow(3).getCell(7).setCellValue(sPayrollReport.getErName());
            }
            // sum合计行的行数
            int sumRowNum = 7 + payrollList.size() + 2;
            // 把payroll的sum行移动到hiddensheet，方便封面公式调用
            for (int i = 0; i < payrollSheet.getRow(sumRowNum).getLastCellNum(); i++) {
                Cell cell = payrollSheet.getRow(sumRowNum).getCell(i);
                // 带公式时取最终值
                if (cell != null && cell.getCellType() == CellType.FORMULA) {
                    hiddenSheet.getRow(1).getCell(i).setCellValue((Double) getFormulaVal(evaluator.evaluate(cell)));
                } else {
                    // 非公式时复制单元格
                    hiddenSheet.getRow(1).getCell(i).setCellValue(payrollExcelUtil.getCellValue(payrollSheet.getRow(sumRowNum), i).toString());
                }

               String forcell=hiddenSheet.getRow(0).getCell(i).getStringCellValue();
                if (forcell.equals("Subsistence Allowance for Foreigners\n外籍生活津贴")){
                    if (payrollList.stream().allMatch(report -> "不发薪".equals(report.getPayrollBelong()))) {
                        hiddenSheet.getRow(1).getCell(i).setCellValue(0);
                    } else {
                        BigDecimal totalforeignerAllowance = payrollList.stream()
                                .filter(report -> !"不发薪".equals(report.getPayrollBelong()))
                                .map(report -> report.getForeignerAllowance())
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                        hiddenSheet.getRow(1).getCell(i).setCellValue(totalforeignerAllowance.doubleValue());
                    }
                }
                }
            // 复制完删除多余列（代缴社保、公积金、个税、实际发薪···）
            for (int i = 5; i < sumRowNum + 1; i++) {
                Row row = payrollSheet.getRow(i);
                for (int j = 9; j > 0; j--) {
                    row.removeCell(row.getCell(row.getLastCellNum() - j));
                }
            }
            // 默认值
            String currency = "USD";
            double exchangeRate = 6.70;
            // Icona不需要计算汇率
            if (!("CCN1109-1".equals(erNo) || "CCN1109-2".equals(erNo) || "CCN1109-3".equals(erNo))) {
                // 从数据库查询汇率和货币
                PsCoverInfo psCoverInfo = new PsCoverInfo();
                psCoverInfo.setDuration(sPayrollReport.getDuration());
                psCoverInfo.setErNo(erNo);
                List<PsCoverInfo> psCoverInfoList = psOpLogService.selectPsCoverInfoList(psCoverInfo);
                // 页面录入时优先用录入的
                if (StringUtils.isNotEmpty(sPayrollReport.getCurrency()) && StringUtils.isNotEmpty(sPayrollReport.getExchangeRate())) {
                    currency = sPayrollReport.getCurrency();
                    exchangeRate = Double.parseDouble(sPayrollReport.getExchangeRate());
                    // 只有一种货币时
                } else if (psCoverInfoList.size() == 1) {
                    currency = psCoverInfoList.get(0).getCurrency();
                    exchangeRate = psCoverInfoList.get(0).getExchangeRate().doubleValue();
                    // 账单导入多种货币时，优先用不是美元的
                } else if (psCoverInfoList.size() > 1) {
                    for (PsCoverInfo detail : psCoverInfoList) {
                        if (StringUtils.isNotEmpty(detail.getCurrency()) && !"USD".equals(detail.getCurrency())) {
                            currency = detail.getCurrency();
                            exchangeRate = detail.getExchangeRate().doubleValue();
                        }
                    }
                } else if (psCoverInfoList.isEmpty()) {
                    logger.error("cover exchange information is null！");
                    return ("ERROR:货币/汇率信息为空，请重新导入账单！");
                }
            }
            // 银行信息
            JSONObject bankEnJsonObject = JSONObject.parseObject(bankInfo.getBankInfoEn());
            JSONObject bankCnJsonObject = JSONObject.parseObject(bankInfo.getBankInfoCn());
            // 英文银行信息
            StringBuffer enStringBuffer = new StringBuffer();
            if (bankEnJsonObject != null) {
                for (Map.Entry<String, Object> entry : bankEnJsonObject.entrySet()) {
                    enStringBuffer.append(entry.getKey() + ":" + entry.getValue() + "\n");
                }
            } else {
                enStringBuffer.append("");
            }
            // 中文银行信息
            StringBuffer cnStringBuffer = new StringBuffer();
            if (bankCnJsonObject != null) {
                for (Map.Entry<String, Object> entry : bankCnJsonObject.entrySet()) {
                    cnStringBuffer.append(entry.getKey() + ":" + entry.getValue() + "\n");
                }
            } else {
                cnStringBuffer.append("");
            }

            // 把payslip的sum行移动到hiddensheet2，方便封面公式调用
            if (pnSheet == null || "Payment Notice".equals(pnSheet.getSheetName())) {
                XSSFSheet hiddenSheet2 = wb.getSheet("hidden2");
                for (int i = 0; i <= payslipSheet.getRow(sumRowNum).getLastCellNum(); i++) {
                    Cell cell = payslipSheet.getRow(sumRowNum).getCell(i);
                    // 带公式时取最终值
                    if (cell != null && cell.getCellType() == CellType.FORMULA) {
                        hiddenSheet2.getRow(1).getCell(i).setCellValue((Double) getFormulaVal(evaluator.evaluate(cell)));
                    } else {
                        // 非公式时复制单元格
                        hiddenSheet2.getRow(1).getCell(i).setCellValue(payslipExcelUtil.getCellValue(payslipSheet.getRow(sumRowNum), i).toString());
                    }
                }
                // 复制完删除多余列（每月固定工资）
                for (int i = 5; i < sumRowNum + 1; i++) {
                    Row row = payslipSheet.getRow(i);
                    if (row != null) {
                        row.removeCell(row.getCell(row.getLastCellNum() - 1));
                    }
                }
                pnSheet = wb.getSheet("Payment Notice");
                // 货币和汇率
                pnSheet.getRow(35).getCell(3).setCellValue("or " + currency);
                pnSheet.getRow(35).getCell(2).setCellValue(exchangeRate);
                // 银行信息
                pnSheet.getRow(43).getCell(0).setCellValue(enStringBuffer.toString());
            } else {
                // 复制完删除多余列（每月固定工资）
                for (int i = 5; i < sumRowNum + 1; i++) {
                    Row row = payslipSheet.getRow(i);
                    if (row != null) {
                        row.removeCell(row.getCell(row.getLastCellNum() - 1));
                    }
                }
                // 货币和汇率
                pnSheet.getRow(51).getCell(3).setCellValue("or " + currency);
                pnSheet.getRow(50).getCell(2).setCellValue(exchangeRate);
                // 银行信息
                pnSheet.getRow(59).getCell(0).setCellValue(enStringBuffer.toString());
                pnSheet.getRow(59).getCell(2).setCellValue(cnStringBuffer.toString());
                // HROne HK账户将仅保留海外支付银行信息
                if ("HROne HK".equals(bankInfo.getErNo())) {
                    pnSheet.getRow(58).getCell(2).setCellValue("");
                    pnSheet.getRow(59).getCell(2).setCellValue("");

                }
            }
            // 填充周期和客户名
            payrollSheet.getRow(3).getCell(3).setCellValue(sPayrollReport.getDuration());
            payrollSheet.getRow(3).getCell(5).setCellValue(sPayrollReport.getErName());
            payslipSheet.getRow(3).getCell(3).setCellValue(sPayrollReport.getDuration());
            payslipSheet.getRow(3).getCell(5).setCellValue(sPayrollReport.getErName());
            pnSheet.getRow(7).getCell(0).setCellValue(pnSheet.getRow(7).getCell(0).getStringCellValue() + erName);
            // Other charge on behalf Fee变动行数
            int otherChargeOnBehalfFeeLineNum = 0;
            // 差额,滞纳金,年度管理服务费,报销服务费
            Double balance = 0.0, delayFee = 0.0, yearlyManagementServiceFee = 0.0, expenseClaimServiceFee = 0.0;
            // 填充other fee数据
            if (otherFeeList != null && !otherFeeList.isEmpty() && otherFeeList.size() > 0) {
                BigDecimal tax = BigDecimal.ZERO;
                for (SOtherFeeReport sOtherFeeReport : otherFeeList) {
                    // 服务类型：服务费
                    if (sOtherFeeReport.getPaymentType() == 0 && sOtherFeeReport.getServiceCost()!=null) {
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME1.equals(sOtherFeeReport.getServiceName())) {
                            expenseClaimServiceFee += sOtherFeeReport.getServiceCost().doubleValue();
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME2.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(15).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME3.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(16).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME4.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(17).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME5.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(18).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME6.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(19).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME7.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(20).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME8.equals(sOtherFeeReport.getServiceName())) {
                            // 确保第 21 行存在
                            if (otherFeeSheet.getRow(21) == null) {
                                otherFeeSheet.createRow(21); // 创建该行
                            }
                            if (otherFeeSheet.getRow(21).getCell(3) == null) {
                                otherFeeSheet.getRow(21).createCell(3); // 创建该单元格
                            }

                            otherFeeSheet.getRow(21).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                            otherFeeSheet.getRow(21).getCell(4).setCellValue(sOtherFeeReport.getRemark());
                        }
                        // Advance Payment Fee 预付费 算到其他代收代付中
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME9.equals(sOtherFeeReport.getServiceName())) {
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(0).setCellValue(sOtherFeeReport.getServiceName());
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(4).setCellValue(sOtherFeeReport.getRemark());
                            otherChargeOnBehalfFeeLineNum++;
                        }
                    }
                    // 服务类型：其他代收代付
                    if (sOtherFeeReport.getPaymentType() == 1) {
                        otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(0).setCellValue(sOtherFeeReport.getServiceName());
                        if (otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(3)==null){
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).createCell(3);
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }else {
                            otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(3).setCellValue(sOtherFeeReport.getServiceCost().doubleValue());
                        }
                        otherFeeSheet.getRow(25 + otherChargeOnBehalfFeeLineNum).getCell(4).setCellValue(sOtherFeeReport.getRemark());
                        otherChargeOnBehalfFeeLineNum++;
                    }
                    // 服务类型：差额
                    if (sOtherFeeReport.getPaymentType() == 3) {
                        balance = sOtherFeeReport.getServiceCost().doubleValue();
                    }
                    // 服务类型：滞纳金
                    if (sOtherFeeReport.getPaymentType() == 2) {
                        delayFee = sOtherFeeReport.getServiceCost().doubleValue();
                    }
                    // 服务类型：年度管理服务费
                    if (sOtherFeeReport.getPaymentType() == 4) {
                        yearlyManagementServiceFee = sOtherFeeReport.getServiceCost().doubleValue();
                    }
                    // 服务类型：报销服务费
                    if (sOtherFeeReport.getPaymentType() == 5) {
                        if (PayrollConstants.OTHER_SERVICE_FEE_NAME1.equals(sOtherFeeReport.getServiceName())) {
                            expenseClaimServiceFee += sOtherFeeReport.getServiceCost().doubleValue();
                        }
                    }

                    if (sOtherFeeReport.getServiceTax() != null) {
                        tax = tax.add(sOtherFeeReport.getServiceTax());
                    }

                }
                // 报销服务费贴到excel
                otherFeeSheet.getRow(14).getCell(3).setCellValue(expenseClaimServiceFee);
                // 服务税贴到excel
                otherFeeSheet.getRow(12).getCell(3).setCellValue(tax.doubleValue());
            }
            // 把封面的公式转成数值再粘贴一遍
            if (pnSheet == null || "Payment Notice".equals(pnSheet.getSheetName())) {
                // if (!PayrollConstants.PAYMENT_RECEIVED_FDI.equals(bankInfo.getErNo()) && ClientConstants.SERVICE_TYPE_CONSULTANT.equals(erType)) {
                // 年度管理服务费
                pnSheet.getRow(30).getCell(4).setCellValue(yearlyManagementServiceFee);
                // 差额
                pnSheet.getRow(31).getCell(4).setCellValue(balance);
                // 滞纳金
                pnSheet.getRow(32).getCell(4).setCellValue(delayFee);
                for (int i = 10; i < 37; i++) {
                    errRowNum = i + 1;
                    for (int j = 0; j < 5; j++) {
                        errColNum = j + 1;
                        Cell cell = pnSheet.getRow(i).getCell(j);
                        // 带公式时取最终值
                        if (cell != null && cell.getCellType() == CellType.FORMULA) {
                            if (i == 10) {
                                String val = getFormulaVal(evaluator.evaluate(cell)).toString();
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(val);
                            } else {
                                Double val = (Double) getFormulaVal(evaluator.evaluate(cell));
                                cell.setCellType(CellType.NUMERIC);
                                cell.setCellValue(val);
                            }
                        }
                    }
                }
                // 填充编号 日期+时间
                pnSheet.getRow(10).getCell(4).setCellValue(DateUtils.dateTimeNow("yyyyMMddHHmm"));
                // 删除隐藏sheet
                wb.removeSheetAt(wb.getSheetIndex(wb.getSheet("hidden")));
                wb.removeSheetAt(wb.getSheetIndex(wb.getSheet("hidden2")));
            } else {
                // 差额
                pnSheet.getRow(36).getCell(4).setCellValue(balance);
                // 滞纳金
                pnSheet.getRow(37).getCell(4).setCellValue(delayFee);
                for (int i = 11; i < 53; i++) {
                    errRowNum = i + 1;
                    for (int j = 0; j < 5; j++) {
                        errColNum = j + 1;
                        Cell cell = pnSheet.getRow(i).getCell(j);
                        // 带公式时取最终值
                        if (cell != null && cell.getCellType() == CellType.FORMULA) {
                            if (i == 11) {
                                String val = getFormulaVal(evaluator.evaluate(cell)).toString();
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(val);
                            } else {
                                Double val = (Double) getFormulaVal(evaluator.evaluate(cell));
                                cell.setCellType(CellType.NUMERIC);
                                cell.setCellValue(val);
                            }
                        }
                    }
                }
                // 填充编号 日期+时间
                if (!("CCN1109-1".equals(erNo) || "CCN1109-2".equals(erNo) || "CCN1109-3".equals(erNo))) {
                    pnSheet.getRow(11).getCell(4).setCellValue(DateUtils.dateTimeNow("yyyyMMddHHmm"));
                }
                // 删除隐藏sheet
                wb.removeSheetAt(wb.getSheetIndex(wb.getSheet("hidden")));
            }
            // 新文件名
            String filename;
            if (PayrollConstants.PAYMENT_RECEIVED_FDI.equals(bankInfo.getErNo())) {
                filename = "FDI Payment Notice-" +sPayrollReport.getErId()+"-"+ sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
            } else {
                if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)){
                    filename = "HROne SH Payment Notice-" + sPayrollReport.getErId()+"-"+sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
                }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                    filename = "HROne BJ Payment Notice-" + sPayrollReport.getErId()+"-"+sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
                }else {
                    filename = "HROne Payment Notice-" + sPayrollReport.getErId()+"-"+ sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
                }
            }

            if (PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(servicePartB) ||PayrollConstants.Service_Contract_Party_B_TOP_FDI.equals(eeServicePartB)){
                filename="TOP FDI Payment Notice-"+sPayrollReport.getErId()+"-"+sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() +"C.xlsx";
            }

            if (PayrollConstants.Client_Source_HK.equals(org)){
                if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)) {
                    filename = "HROne SH Payment Notice-HROne HK-" + sPayrollReport.getErId() + "-" + sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
                }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                    filename="HROne BJ Payment Notice-HROne HK-"+sPayrollReport.getErId()+"-"+sPayrollReport.getErName()+ "-" +sPayrollReport.getDuration()+"C.xlsx";
                }
            }

            if (PayrollConstants.Client_Source_TopFDI.equals(org)){
                if (PayrollConstants.Service_Contract_Party_B_SH.equals(servicePartB)) {
                    filename = "HROne SH Payment Notice-TOP FDI-" + sPayrollReport.getErId() + "-" + sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + "C.xlsx";
                }else if (PayrollConstants.Service_Contract_Party_B_BJ.equals(servicePartB)){
                    filename="HROne BJ Payment Notice-TOP FDI-"+sPayrollReport.getErId()+"-"+sPayrollReport.getErName()+ "-"+sPayrollReport.getDuration()+"C.xlsx";
                }
            }


            // 导出新文件
            out = new FileOutputStream(filePathPrefix + "/" + filename);
            wb.write(out);

            // 查询该账单的导出日志
            PsPnExportLog psPnExportLog = new PsPnExportLog();
            psPnExportLog.setDuration(sPayrollReport.getDuration());
            psPnExportLog.setErNo(sPayrollReport.getErId());
            List<PsPnExportLog> logList = psPnExportLogService.selectPsPnExportLogList(psPnExportLog);
            // 0条的时候插入生成一条新的导出记录
            if (logList.isEmpty()) {
                String[] nameArr = sPayrollReport.getErName().split(" - ");
                if (nameArr.length > 1) {
                    psPnExportLog.setErName(nameArr[1]);
                } else {
                    psPnExportLog.setErName(erName);
                }
                psPnExportLog.setFileName(filename);
                psPnExportLog.setFileStatus(0L);
                psPnExportLog.setFileAddress(filePathPrefix + "/" + filename);
                psPnExportLog.setCreateBy(SecurityUtils.getUsername());
                psPnExportLogService.insertPsPnExportLog(psPnExportLog);
                // 记录为未通过时,更新状态为未检查
            } else if (logList.get(0).getFileStatus() == 2L) {
                PsPnExportLog updateDto = logList.get(0);
                updateDto.setFileStatus(0L);
                updateDto.setUpdateBy(SecurityUtils.getUsername());
                psPnExportLogService.updatePsPnExportLog(updateDto);
            } else {
                PsPnExportLog updateDto = logList.get(0);
                updateDto.setUpdateBy(SecurityUtils.getUsername());
                psPnExportLogService.updatePsPnExportLog(updateDto);
            }
            return filename;
        } catch (ClassCastException e) {
            logger.error("export Excel error :{}", e);
            throw new BusinessException(MessageFormat.format("文件封面第[{0}]行第[{1}]列数据异常！", errRowNum, errColNum));
        } catch (Exception e) {
            logger.error("export Excel error :{}", e);
            throw new BusinessException("导出Excel失败，请联系网站管理员");
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }



    /**
     * 导出Stefanini的C版账单
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:export')")
    @Log(title = "payment_notice_stefaniniExport", businessType = BusinessType.EXPORT)
    @PostMapping("/stefaniniExport")
    @ResponseBody
    public AjaxResult stefaniniExport(@RequestBody SPayrollReport sPayrollReport) throws Exception {
        Workbook wb = new SXSSFWorkbook(500);
        OutputStream out = null;
        // 文件名
        String filename = "IIT&Benefits - " + sPayrollReport.getErName() + "-" + sPayrollReport.getDuration() + ".xlsx";
        try {
            // 查询数据
            Map<String, Object> map = new HashMap<>();
            map.put("duration", sPayrollReport.getDuration());
            map.put("erName", sPayrollReport.getErName());
            map.put("erNo", sPayrollReport.getErId());
            // 选择雇员时只导出选中的数据
            if (StringUtils.isNotEmpty(sPayrollReport.getIdNo())) {
                map.put("idNo", "'" + sPayrollReport.getIdNo().replaceAll(",", "','") + "'");
            }
            // 查询iit和socialBenefit数据
            List<StefaniniIITReport> iitList = iITReportService.selectStefaniniIitReportList(map);
            List<StefaniniSocialBenefitReport> socialBenefitList = socialBenefitReportService.selectStefaniniSocialBenefitReportList(map);
            // excel中生成对应sheet
            HroneExcelUtil<StefaniniIITReport> iitUtil = new HroneExcelUtil<StefaniniIITReport>(StefaniniIITReport.class);
            iitUtil.addSheetToExcel(wb, 0, sPayrollReport.getErName() + " IIT-" + sPayrollReport.getDuration(), iitList);
            HroneExcelUtil<StefaniniSocialBenefitReport> benefitUtil = new HroneExcelUtil<StefaniniSocialBenefitReport>(StefaniniSocialBenefitReport.class);
            benefitUtil.addSheetToExcel(wb, 1, sPayrollReport.getErName() + " Benefits-" + sPayrollReport.getDuration(), socialBenefitList);
            // 导出新文件
            out = new FileOutputStream(NNRoadConfig.getDownloadPath() + filename);
            wb.write(out);
            return AjaxResult.success(filename);
        } catch (Exception e) {
            logger.error("export Excel error :{}", e);
            throw new BusinessException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     */
    @GetMapping("/exportDownload")
    public void fileDownload(String fileName,String erName, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new IOException(MessageFormat.format("文件名称({})非法，不允许下载。", fileName));
            }
            //String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = NNRoadConfig.getDownloadPath() + "PN_invoice/" + erName + "/" + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            log.info("文件成功写入: {}", filePath);
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }


    /**
     * 取出单元格公式的结果
     */
    private Object getFormulaVal(CellValue cellValue) {

        switch (cellValue.getCellType()) {
            case BOOLEAN:
                return cellValue.getBooleanValue();
            case NUMERIC:
                return cellValue.getNumberValue();
            case STRING:
                return cellValue.getStringValue();
            case ERROR:
                return "0";
            default:
                return "";
        }
    }

    /**
     * 下载文件
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:download')")
    @GetMapping("/downloadFileByFileAddress")
    public void downloadFile(String fileUrl, String fileStatus, String erNo, HttpServletResponse response) throws Exception {
        try {
            // 解码文件路径，处理可能的特殊字符
            String decodedFileUrl = URLDecoder.decode(fileUrl, "UTF-8");
            File file = new File(decodedFileUrl);  // 直接使用传入的文件路径

            // 检查文件是否存在
            if (!file.exists() || !file.isFile()) {
                log.error("File not found: " + decodedFileUrl);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
                return;
            }
            String downloadName = file.getName();
            // 如果文件是 .xlsx 并且需要加密
            if ("1".equals(fileStatus) && downloadName.endsWith(".xlsx")) {
                // 获取加密密码
                ClientBillinginfo clientPayPassword =clientInfoService.getClientPayPassword(erNo);
                String password = generatePassword(clientPayPassword);

                // 加密Excel文件并输出
                try (FileInputStream fis = new FileInputStream(file);
                     XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                    POIFSFileSystem fs = new POIFSFileSystem();
                    EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile); // 使用agile加密模式
                    Encryptor encryptor = info.getEncryptor();
                    encryptor.confirmPassword(password);  // 设置加密密码

                    // 输出加密文件到流
                    try (OutputStream os = encryptor.getDataStream(fs)) {
                        workbook.write(os);  // 将未加密的内容写入加密流
                    }

                    // 设置响应头，准备下载
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadName, "UTF-8"));

                    // 将加密后的文件内容写入响应
                    try (OutputStream out = response.getOutputStream()) {
                        fs.writeFilesystem(out);  // 输出加密文件
                    }
                    return;
                }
            }

            // 如果文件不需要加密，直接下载文件
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadName, "UTF-8"));

            // 使用 Apache Commons IO 简化文件写入过程
            try {
                FileUtils.copyFile(file, response.getOutputStream());
                response.getOutputStream().flush(); // 确保数据完全写入
            } catch (IOException e) {
                log.error("Error while writing file to output stream", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to download file.");
            }

        } catch (Exception e) {
            log.error("Failed to download file", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to download file.");
        }
    }


    /**
     * 账单通过
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:approve')")
    @Log(title = "payment_notice", businessType = BusinessType.UPDATE)
    @PutMapping("/approve")
    @ResponseBody
    public AjaxResult approve(@RequestParam("ids") String ids) {
        for (String id : ids.split(",")) {
            PsPnExportLog psPnExportLog = psPnExportLogService.selectPsPnExportLogById(Long.parseLong(id));
            psPnExportLog.setFileStatus(1L);
            psPnExportLog.setUpdateBy(SecurityUtils.getUsername());
            int ret = psPnExportLogService.updatePsPnExportLog(psPnExportLog);
            if (ret == 0) {
                return AjaxResult.error();
            }
        }
        return AjaxResult.success();
    }

    /**
     * 账单未通过
     */

    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:reject')")
    @Log(title = "payment_notice", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    @ResponseBody
    public AjaxResult reject(@RequestParam("ids") String ids) {
        for (String id : ids.split(",")) {
            PsPnExportLog psPnExportLog = psPnExportLogService.selectPsPnExportLogById(Long.parseLong(id));
            psPnExportLog.setFileStatus(2L);
            psPnExportLog.setUpdateBy(SecurityUtils.getUsername());
            int ret = psPnExportLogService.updatePsPnExportLog(psPnExportLog);
            if (ret == 0) {
                return AjaxResult.error();
            }
        }
        return AjaxResult.success();
    }

    /**
     * 删除PN日志
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:remove')")
    @Log(title = "payment_notice", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(psPnExportLogService.deletePsPnExportLogByIds(ids));
    }


    private XSSFCell getOrCreateCell(XSSFSheet sheet, int rowNum, int cellNum) {
        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        return row.getCell(cellNum) == null ? row.createCell(cellNum) : row.getCell(cellNum);
    }


    public String generatePassword(ClientBillinginfo clientPayPassword) {
        // 获取当前的年月，格式为yyyyMM
        if (clientPayPassword.getIsPasswordDynamic() != null &&
                !clientPayPassword.getIsPasswordDynamic().isEmpty() &&
                ("Yes".equalsIgnoreCase(clientPayPassword.getIsPasswordDynamic()) ||
                        "Y".equalsIgnoreCase(clientPayPassword.getIsPasswordDynamic()))) {

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
            String currentYearMonth = now.format(formatter);

            if (clientPayPassword.getPayNoticePassword() != null
                    && !clientPayPassword.getPayNoticePassword().trim().isEmpty()) {
                // 返回 password + 当前年月
                return clientPayPassword.getPayNoticePassword() + currentYearMonth;
            }else{
//              返回 ClientName+当前年月
                return clientPayPassword.getClientName()+currentYearMonth;
            }
        }
        // 如果不满足上述条件，则返回原来的 payNoticePassword
        return clientPayPassword.getPayNoticePassword();
    }


    /**
     * 账单数据计算
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice:calculate')")
    @Log(title = "payment_notice", businessType = BusinessType.UPDATE)
    @PutMapping("/calculate")
    @ResponseBody
    public AjaxResult calculate(@RequestParam("ids") String ids) {
        int successCount=0;
        int failCount=0;
        int result;
        for (String id : ids.split(",")) {
            PsPayroll psPayroll=psPayrollService.selectPsPayrollById(Long.parseLong(id));
            String idNo=psPayroll.getIdNo();
            result = clientEmployeeCalculateService.selectCalculate(idNo, psPayroll);
            if (result > 0) {
                successCount++; // 记录成功修改的条数
            } else {
                failCount++; // 修改失败的记录数
            }
        }
        // 返回成功和失败的统计结果
        return AjaxResult.success("计算完成：成功 " + successCount + " 条，失败 " + failCount + " 条");
    }
}
