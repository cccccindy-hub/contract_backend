package com.nnroad.web.controller.common;

import cn.hutool.core.util.StrUtil;
import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.constant.Constants;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.exception.Asserts;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.common.utils.file.FileUploadUtils;
import com.nnroad.common.utils.file.FileUtils;
import com.nnroad.common.utils.ip.IpModel;
import com.nnroad.common.utils.ip.IpUtils;
import com.nnroad.framework.config.EmailConfig;
import com.nnroad.framework.config.ServerConfig;
import com.nnroad.framework.thread.EmailThreadPool;
import com.nnroad.system.domain.SysEmailSend;
import com.nnroad.system.service.ISysMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ISysMailService sysMailService;

    @Autowired
    private Configuration configuration;

    @Autowired
    private EmailConfig emailConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
//            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = NNRoadConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = NNRoadConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
        try {
            // 上传文件路径
            String filePath = NNRoadConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            if (!FileUtils.checkAllowDownload(resource)) {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = NNRoadConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    @PostMapping("/email")
    @Anonymous
    public AjaxResult sendMail(@RequestBody Map<String, String> params, HttpServletRequest request) throws Exception {
        String referer = request.getHeader("referer");
        Asserts.isTrue(StrUtil.contains(referer, "hrone.com"), "Invalid Domain!");
        String toMail = params.get("email");
        Asserts.notBlank(toMail, "Please input you email");
        resolveParam(params);

        Template template = configuration.getTemplate("HRoneContactTemplate.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);

        SysEmailSend sysEmailSend = new SysEmailSend("HROne", "HROne new Leads", content, emailConfig.getHrone().toArray(new String[0]));
        sysMailService.mailSend(sysEmailSend);

        replyTo(toMail, params);

        return AjaxResult.success(null);
    }

    private void resolveParam(Map<String, String> params) {
        //真实ipi地址
        String ipAddr = IpUtils.getIpAddr();
        IpModel ipModel = IpUtils.getIpDetail(ipAddr);
        if (ipModel != null) {
            ipAddr += "__" + StrUtil.blankToDefault(ipModel.getCountry(), "") + "-" + StrUtil.blankToDefault(ipModel.getRegionName(), "");
        }
        params.put("ipAddr", ipAddr);
        //兼容老地逻辑
        String msg = params.get("msg");
        if (StrUtil.isBlank(msg)) {
            msg = params.get("message");
            if (StrUtil.isNotBlank(msg)) {
                String regex = "<div><b>Message:</b>\\s*([^<]+)</div>";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(msg);
                if (matcher.find()) {
                    params.put("msg", matcher.group(1));
                } else {
                    params.put("msg", msg);
                }
            }
        }
    }

    public void replyTo(String toMail, Map<String, String> params) {
        EmailThreadPool.execute(() -> {
            try {
                Template template = configuration.getTemplate("HRoneReplyTemplate.ftl");
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);

                SysEmailSend sysEmailSend = new SysEmailSend("HROne", "Thank you for contacting HROne!", content, new String[]{toMail});
                sysMailService.mailSend(sysEmailSend);
            } catch (Exception e) {
                log.error("HROne发送Reply邮件失败：toMail-{}, e-{}", toMail, e.getMessage());
            }
        });

    }
}
