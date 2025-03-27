package com.nnroad.framework.web.azureFileShares;

public enum AzureFileShareDirsConstants {
    clientEmployees("clientEmployees"),
    basicFiles("basicFiles");

    final String value;

    AzureFileShareDirsConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}