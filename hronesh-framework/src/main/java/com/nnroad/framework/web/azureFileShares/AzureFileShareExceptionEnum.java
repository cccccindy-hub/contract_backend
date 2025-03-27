package com.nnroad.framework.web.azureFileShares;

public enum AzureFileShareExceptionEnum {
    contentMissMatch("cloud file content doesn't match with the original file content", 1),
    fileAlreadyExists("File already exists", 2),
    noSuchDirectory("No such directory", 3),
    noSuchFile("No such file", 4),
    emptyFileName("File name is empty", 5);

    private String msg;
    private int code;
    AzureFileShareExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}