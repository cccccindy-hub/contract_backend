//package com.nnroad.common.utils.excel;
//
//import cn.hutool.core.collection.CollUtil;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
////import com.payslip.core.exception.BusinessException;
////import com.payslip.utils.excel.dto.ExcelHandleDto;
////import com.payslip.utils.excel.handler.CellStyleWriteHandler;
////import com.payslip.utils.excel.handler.ColExplicitListSheetWriteHandler;
////import com.payslip.utils.excel.handler.CommentWriteHandler;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author raven
// * @since 2023/12/20 19:21
// */
//@Slf4j
//public class ExcelUtils {
//
//    /**
//     * 直接导出，针对数据量不大的数据到处
//     *
//     * @param heads          列部
//     * @param data           导出数据
////     * @param excelHandleDto 导出excel的各种样式
//     * @param fileName       文件名称
//     * @param sheetName      sheet名称
//     * @param response       resp
//     */
//    public static void export(List<List<String>> heads,
//                              List<Map<Integer, Object>> data,
////                              ExcelHandleDto excelHandleDto,
//                              String fileName,
//                              String sheetName,
//                              HttpServletResponse response) {
//
//        try {
//            ExcelWriterSheetBuilder sheetBuilder = EasyExcel.write(getOutputStream(fileName, response))
//                    .head(heads)
//                    .sheet(sheetName);
//            registerWriterHandler(sheetBuilder, excelHandleDto);
//            sheetBuilder.doWrite(data);
//        } catch (Exception e) {
//            log.error("到处excel异常：fileName-{}, e-{}", fileName, e);
//            throw new BusinessException(e.getMessage());
//        }
//    }
//
//    private static void registerWriterHandler(ExcelWriterSheetBuilder sheetBuilder, ExcelHandleDto excelHandleDto) {
//        if (excelHandleDto == null) {
//            return;
//        }
//        if (CollUtil.isNotEmpty(excelHandleDto.getAnchorList())) {
//            sheetBuilder.registerWriteHandler(new CommentWriteHandler(excelHandleDto.getAnchorList()));
//        }
//        if (CollUtil.isNotEmpty(excelHandleDto.getColExplicitList())) {
//            sheetBuilder.registerWriteHandler(new ColExplicitListSheetWriteHandler(excelHandleDto.getColExplicitList()));
//        }
//        if (CollUtil.isNotEmpty(excelHandleDto.getCellStyleList())) {
//            sheetBuilder.registerWriteHandler(new CellStyleWriteHandler(excelHandleDto.getCellStyleList()));
//        }
//    }
//
//    /**
//     * 导出文件时为Writer生成OutputStream
//     */
//    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
//        //创建本地文件
//        try {
//            fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());
//
//            return response.getOutputStream();
//        } catch (IOException e) {
//            log.error("创建Stream异常：", e);
//            throw new BusinessException("创建文件失败！");
//        }
//    }
//}
