package com.nnroad.framework.web.azureFileShares;

public class AzureFileShareException extends Exception{
    int code = 0;

    public int getCode() {
        return code;
    }

    public AzureFileShareException(AzureFileShareExceptionEnum azureFileShareExceptionEnum) {
        super(azureFileShareExceptionEnum.getMsg());
        this.code = azureFileShareExceptionEnum.getCode();
    }
}
