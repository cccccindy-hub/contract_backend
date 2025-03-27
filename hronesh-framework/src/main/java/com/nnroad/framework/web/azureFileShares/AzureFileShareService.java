package com.nnroad.framework.web.azureFileShares;
import com.azure.storage.common.ParallelTransferOptions;
import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.models.ShareFileUploadInfo;
import com.google.common.collect.ImmutableMap;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.MessageUtils;
import com.nnroad.common.utils.ShiroUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.common.core.domain.MultipartFileFromBytes;
import com.nnroad.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class AzureFileShareService {
    @Autowired
    private ShareClient shareClient;

    @Autowired
    private ServletContext servletContext;

    @Value("${azure.requestLifeTime}")
    private Integer requestLifeTimeMin;

    private ParallelTransferOptions parallelTransferOptions;

    public ParallelTransferOptions getParallelTransferOptions() {
        if(parallelTransferOptions == null) {
            ParallelTransferOptions parallelTransferOptions = new ParallelTransferOptions();
            parallelTransferOptions.setMaxConcurrency(4);
            parallelTransferOptions.setBlockSizeLong((long) (1000 * 1024));
        }
        return parallelTransferOptions;
    }

    public void setParallelTransferOptions(ParallelTransferOptions parallelTransferOptions) {
        this.parallelTransferOptions = parallelTransferOptions;
    }

    /**
     * upload file to the cloud, creates directory if doesn't exist
     * @param dir
     * @param fileName should be withoud extension. (Extension automatically added according to the extension of the MultipartFile file)
     * @param file
     * @param overwrite
     * @return
     * @throws IOException
     * @throws AzureFileShareException
     * @throws NoSuchAlgorithmException
     */
    public Boolean uploadFile(AzureFileShareDirs dir, String fileName, MultipartFile file, Boolean overwrite) throws IOException, AzureFileShareException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(fileName)) throw new AzureFileShareException(AzureFileShareExceptionEnum.emptyFileName);
        fileName += "." + FileUtils.getExtension(file);
        //move to directory (create if doesn't exist)
        String[] dirTree = dir.getDirectory().split("/");
        ShareDirectoryClient shareDirectoryClient = shareClient.getDirectoryClient(dirTree[0]);
        shareDirectoryClient.createIfNotExists();
        for(int i = 1; i < dirTree.length; i++) {
            shareDirectoryClient = shareDirectoryClient.getSubdirectoryClient(dirTree[i]);
            shareDirectoryClient.createIfNotExists();
        }
        //create the empty file
        ShareFileClient shareFileClient = shareDirectoryClient.getFileClient(fileName);
        if(shareFileClient.exists()) {
            if(overwrite)
                shareFileClient.delete();
            else
                throw new AzureFileShareException(AzureFileShareExceptionEnum.fileAlreadyExists);
        }

        shareFileClient.create(file.getSize());
        //upload the file
        ShareFileUploadInfo shareFileUploadInfo = shareFileClient.upload(file.getInputStream(), file.getSize(), getParallelTransferOptions());
        if(FileUtils.base64encode(shareFileUploadInfo.getContentMd5()).equals(getMD5ofFileContent(file))) {
            log.info(String.format("Azure File Shares - File Upload :: file: %s, size: %sB, overwrite: %s", shareFileClient.getFilePath(), file.getSize(), overwrite));
            return true;
        }
        return false;
    }

    /**
     * checks file size and file extension
     * uploads file to the cloud, creates directory if doesn't exist
     * @param dir
     * @param fileName
     * @param file
     * @param overwrite
     * @param maxFileSize
     * @param allowedExtensions
     * @return
     * @throws Exception
     */
    public Boolean uploadFile(AzureFileShareDirs dir, String fileName, MultipartFile file, Boolean overwrite, long maxFileSize, String[] allowedExtensions) throws Exception{
        checkFileSize(file, maxFileSize);
        checkFileExtension(file, allowedExtensions);
        return uploadFile(dir, fileName, file, overwrite);
    }

    public byte[] downloadFile(AzureFileShareDirs dir, String fileName) throws AzureFileShareException, IOException {
        //move to directory (create if doesn't exist)
        String[] dirTree = dir.getDirectory().split("/");
        ShareDirectoryClient shareDirectoryClient = shareClient.getDirectoryClient(dirTree[0]);
        if(!shareDirectoryClient.exists()) throw new AzureFileShareException(AzureFileShareExceptionEnum.noSuchDirectory);
        for(int i = 1; i < dirTree.length; i++) {
            shareDirectoryClient = shareDirectoryClient.getSubdirectoryClient(dirTree[i]);
            if(!shareDirectoryClient.exists()) throw new AzureFileShareException(AzureFileShareExceptionEnum.noSuchDirectory);
        }
        //download file if exists
        ShareFileClient shareFileClient = shareDirectoryClient.getFileClient(fileName);
        if(!shareFileClient.exists()) throw new AzureFileShareException(AzureFileShareExceptionEnum.noSuchFile);

        byte[] result;
        int size;
        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            shareFileClient.download(stream);
            result = stream.toByteArray();
            size = stream.size();
        }
        log.info(String.format("Azure File Shares - File Download :: file: %s, size: %sB", shareFileClient.getFilePath(), size));
        return result;
    }

    public void downloadFile(AzureFileShareDirs dir, String fileName, HttpServletResponse response) throws IOException, AzureFileShareException {
        byte[] fileBytes = downloadFile(dir, fileName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName);
        try(OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(fileBytes);
            outputStream.flush();
        }
    }

    public MultipartFileFromBytes downloadMultipartFile(AzureFileShareDirs dir, String fileName) throws IOException, AzureFileShareException {
        String contentType = servletContext.getMimeType(fileName);
        String originalFileName = dir.getDirectory() + "/" + fileName;
        byte[] bytes = downloadFile(dir, fileName);
        return new MultipartFileFromBytes(bytes, fileName, originalFileName, contentType);
    }

    /**
     * upload file to the cloud, creates directory if doesn't exist
     * @param file
     * @return
     * @throws IOException
     */
    public Boolean uploadFile(AzureFileShareDirs dir, String fileName, MultipartFile file, Boolean overwrite, ParallelTransferOptions parallelTransferOptions) throws IOException, AzureFileShareException, NoSuchAlgorithmException {
        setParallelTransferOptions(parallelTransferOptions);
        return uploadFile(dir, fileName, file, overwrite);
    }

    /**
     * get md5 of file's content in Base64 format as same as in ShareFileUploadInfo class (Azure File Share Service cloud)
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public String getMD5ofFileContent(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        return FileUtils.getMD5ofFile(file);
    }

    /**
     * prepare request to download from Azure File Shares (for Security)
     * @param dir
     * @param fileName
     */
    public void prepareToDownload(AzureFileShareDirs dir, String fileName) {
        Map<String, Object> params = ImmutableMap.of(
                "expirationTime", DateUtils.addMinute(new Date(), requestLifeTimeMin),
                "dir", dir,
                "fileName", fileName
        );
        ShiroUtils.getSysUser().getParams().put("azureFileDownload", params);
    }

    /**
     * checks if file size has exceeded the maxFileSize
     * @param file
     * @param maxFileSize
     * @return error string or empty string
     */
    public String fileSizeErrorStr(MultipartFile file, long maxFileSize) {
        String sizeErrorStr = MessageUtils.message("common.file.sizeError");
        String emptyFileErrorStr = MessageUtils.message("common.file.emptyError");
        StringBuilder errorString = new StringBuilder();
        if(file.isEmpty()) return String.format(emptyFileErrorStr, file.getOriginalFilename());
        if(!FileUtils.isAllowedFileSize(file, maxFileSize)) {
            errorString.append("<span style='color: red'>ERROR!</span> ")
                    .append(String.format(sizeErrorStr, file.getOriginalFilename(), maxFileSize))
                    .append("<br><br>");
        }
        return errorString.toString();
    }

    /**
     * checks if file size has exceeded the maxFileSize
     * @param file
     * @param maxFileSize
     * @throws Exception
     */
    public void checkFileSize(MultipartFile file, long maxFileSize) throws Exception {
        String errorStr = fileSizeErrorStr(file, maxFileSize);
        if(StringUtils.isNotEmpty(errorStr)) throw new Exception(errorStr);
    }

    /**
     * checks if file's extension is in allowedExtensions list
     * @param file
     * @param allowedExtensions
     * @return error string or empty string
     */
    public String fileExtensionErrorStr(MultipartFile file, String[] allowedExtensions) {
        String allowedExtensionsStr = StringUtils.join(allowedExtensions, ", ");
        String extensionErrorStr = MessageUtils.message("common.file.extensionError");
        String emptyFileErrorStr = MessageUtils.message("common.file.emptyError");
        StringBuilder errorString = new StringBuilder();
        if(file.isEmpty()) return String.format(emptyFileErrorStr, file.getOriginalFilename());
        if (!FileUtils.isAllowedExtension(FileUtils.getExtension(file), allowedExtensions)) {
            errorString.append("<span style='color: red'>ERROR!</span> ")
                    .append(String.format(extensionErrorStr, file.getOriginalFilename(), allowedExtensionsStr))
                    .append("<br><br>");
        }
        return errorString.toString();
    }

    /**
     * checks if file's extension is in allowedExtensions list
     * @param file
     * @param allowdExtensions
     * @throws Exception
     */
    public void checkFileExtension(MultipartFile file, String[] allowdExtensions) throws Exception {
        String errorStr = fileExtensionErrorStr(file, allowdExtensions);
        if(StringUtils.isNotEmpty(errorStr)) throw new Exception(errorStr);
    }

    /**
     * get file name from file path
     *
     * a/b/c.txt -> c.txt
     * /a/b/c.txt -> c.txt
     * a/b/c -> c
     * @param filePath
     * @return
     */
    public String getFileNameFromFilePath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/")+1);
    }

}

