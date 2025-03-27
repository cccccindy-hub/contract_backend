package com.nnroad.web.controller.payroll;


import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.ShiroUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.payroll.domain.Employer;
import com.nnroad.payroll.domain.exportC.PsPnTemplate;
import com.nnroad.payroll.domain.exportC.SPayrollReport;
import com.nnroad.payroll.service.IPaymentNoticeReportService;
import com.nnroad.payroll.service.IPsPnTemplateService;
import com.nnroad.web.controller.common.CommonController;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Payment_Notice_Report_Controller
 *
 * @author Hrone
 * @date 2021-06-09
 */
@Controller
@RequestMapping("/payroll/payment_notice/template")
public class PaymentNoticeTemplateController extends BaseController {
    private String prefix = "payroll/payment_notice/template";
    @Autowired
    private IPaymentNoticeReportService paymentNoticeReportService;
    @Autowired
    private IPsPnTemplateService psPnTemplateService;
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 查询PN模板管理列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice_template:view')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody SPayrollReport sPayrollReport) {
        startPage();
        PsPnTemplate psPnTemplate = new PsPnTemplate();
        psPnTemplate.setClientCode(sPayrollReport.getErId());
        List<PsPnTemplate> list = psPnTemplateService.selectPsPnTemplateList(psPnTemplate);
        return getDataTable(list);
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
     * 新增保存PN模板管理
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice_template:add')")
    @Log(title = "PN模板管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody PsPnTemplate psPnTemplate) {
        return toAjax(psPnTemplateService.insertPsPnTemplate(psPnTemplate));
    }

    /**
     * 修改PN模板管理
     */
    @GetMapping("/getInfo/{id}")
    @ResponseBody
    public AjaxResult getInfo(@PathVariable Long id) {
        PsPnTemplate psPnTemplate = psPnTemplateService.selectPsPnTemplateById(id);
        return success(psPnTemplate);
    }

    /**
     * 修改保存PN模板管理
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_notice_template:edit')")
    @Log(title = "PN模板管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody PsPnTemplate psPnTemplate) {
        return toAjax(psPnTemplateService.updatePsPnTemplate(psPnTemplate));
    }

    /**
     * 删除PN模板管理
     */
    @RequiresPermissions("payroll:payment_notice_template:remove")
    @Log(title = "PN模板管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(psPnTemplateService.deletePsPnTemplateByIds(ids));
    }

    /**
     * 上传模板文件
     */
    @PutMapping("/upload_file")
    @ResponseBody
    public AjaxResult uploadFile(Long id, String fileName, String url) {
        PsPnTemplate psPnTemplate = new PsPnTemplate();
        psPnTemplate.setId(id);
        psPnTemplate.setFileName(fileName);
        psPnTemplate.setFileAddress(url);
        psPnTemplate.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(psPnTemplateService.updatePsPnTemplate(psPnTemplate));
    }


    /**
     * 下载文件
     */
    //@RequiresPermissions("proposal:contract:edit")
    @GetMapping("/downloadFileByFileAddress")
    public void downloadFile(String fileUrl, HttpServletResponse response) throws Exception {
        try {
            String downloadName = StringUtils.substringAfterLast(fileUrl, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(fileUrl, response.getOutputStream());
        } catch (Exception e) {
            log.error("Failed to download Template File", e);
        }
    }

    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult saveFile(MultipartFile file) throws Exception {
        try {
            String uploadAddress = "";
            //1.生成文件，
            String fileRealName = file.getOriginalFilename();//获得原始文件名;
            if (fileRealName.contains("/")) {
                String[] fileAry = fileRealName.split("/");
                fileRealName = fileAry[fileAry.length - 1];
            }
            String fileNamePrefix = fileRealName.substring(0, fileRealName.lastIndexOf("."));
            String[] split = fileRealName.split("\\.");
            String fileNameSuffixes = split[split.length - 1];
            //String saveFileName = fileNamePrefix + "_" + UUID.randomUUID() + "." + fileNameSuffixes;
            String saveFileName = fileNamePrefix + "." + fileNameSuffixes;
            String filePath = NNRoadConfig.getProfile() + "/download/template/";
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
            if (isCreateSuccess) {      //将文件写入
                savedFile.delete();
                file.transferTo(savedFile);
            }
            uploadAddress = filePath + saveFileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileRealName);
            ajax.put("url", uploadAddress);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
