package com.nnroad.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 错误信息处理类。
 *
 * @author ruoyi
 */
public class ExceptionUtil
{
    /**
     * 获取exception的详细错误信息。
     */
    public static String getExceptionMessage(Throwable e)
    {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    public static String getRootErrorMessage(Exception e)
    {
        Throwable root = ExceptionUtils.getRootCause(e);
        root = (root == null ? e : root);
        if (root == null)
        {
            return "";
        }
        String msg = root.getMessage();
        if (msg == null)
        {
            return "null";
        }
        return StringUtils.defaultString(msg);
    }

    public static String getExceptionColumn(String exceptionMessage){
        String startStr = "for column '";
        String endStr = "' at row";
        int start = exceptionMessage.indexOf(startStr);
        int end = exceptionMessage.indexOf(endStr);
        if(start != -1 && end != -1){
            return exceptionMessage.substring(start + startStr.length(), end);
        }else{
            return "";
        }
    }
}
