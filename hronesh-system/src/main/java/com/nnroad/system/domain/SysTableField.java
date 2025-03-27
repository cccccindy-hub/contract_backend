package com.nnroad.system.domain;

public class SysTableField extends SysTable {
    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 字段键类型
     */
    private String columnKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
}
