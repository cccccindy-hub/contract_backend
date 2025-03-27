package com.nnroad.utils;

import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.mapper.SysExtraAttributeMapper;

import com.nnroad.system.mapper.SysDictDataMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelImporter {

    @Autowired
    private SysExtraAttributeMapper sysExtraAttributeMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;


    public List<Map<String, Object>> parseExcel(MultipartFile file, String tableName) throws IOException {

        List<SysExtraAttribute> extraAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy(tableName, null);

//        System.out.println(extraAttributeList);
//        for (SysExtraAttribute attr: extraAttributeList){
//            System.out.println(attr.toString());
//        }

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        List<Map<String, Object>> jsonData = new ArrayList<>();

        // Assuming headers are in the first row
        Row headerRow = sheet.getRow(0);
        Row subHeaderRow = sheet.getRow(1);

        int numColumns = headerRow.getPhysicalNumberOfCells();
        String[] headers = new String[numColumns];
        String[] subHeaders = new String[numColumns];

        for (int i = 0; i < numColumns; i++) {
            headers[i] = dataFormatter.formatCellValue(headerRow.getCell(i));
            subHeaders[i] = dataFormatter.formatCellValue(subHeaderRow.getCell(i));
        }
        for(String header: headers)
            System.out.print(header+", ");

        System.out.println("");
        for(String sub: subHeaders)
            System.out.print(sub+", ");
        // Process the data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row currentRow = sheet.getRow(i);
            Map<String, Object> dataEntry = new HashMap<>();

            String header = null;
            String subHeader = null;

            for (int j = 0; j < numColumns; j++) {
                header = headers[j] == null || headers[j].isEmpty() ? header : headers[j];
                subHeader = subHeaders[j];

                String cellValue = dataFormatter.formatCellValue(currentRow.getCell(j));

                // Use IDs from extraAttributeList if available
                // Use IDs from extraAttributeList if available
                String headerId = (findAttributeIdByName(extraAttributeList, header) != null) ? String.valueOf(findAttributeIdByName(extraAttributeList, header).getId()) : null;

                String subHeaderId = (findChildAttributeIdByName(extraAttributeList, header, subHeader) != null) ? String.valueOf(findChildAttributeIdByName(extraAttributeList, header, subHeader).getId()) : null;


                if (headerId == null) {
                    if(cellValue.equals("")) {
                        dataEntry.put(header, subHeader);
                    }
                    else {
                        dataEntry.put(header, cellValue);
                    }
                    continue;
                }

                if(!dataEntry.containsKey("extraData")){
                    dataEntry.put("extraData", new HashMap<String, Object>());
                }
//                System.out.println(subHeader);
//
//                System.out.println(subHeader);
                if (subHeader == null || subHeader.isEmpty()) {
                    if(cellValue.equals(""))
                        ((Map<String, Object>) dataEntry.get("extraData")).put(headerId, null);
                    else
                        ((Map<String, Object>) dataEntry.get("extraData")).put(headerId, cellValue);
                } else {
                    if (!((Map<String, Object>) dataEntry.get("extraData")).containsKey(headerId)) {
                        ((Map<String, Object>) dataEntry.get("extraData")).put(headerId, new HashMap<String, Object>());
                    }
                    if(subHeaderId != null)
                        if(cellValue.equals(""))
                            ((Map<String, Object>)((Map<String, Object>) dataEntry.get("extraData")).get(headerId)).put(subHeaderId, null);
                        else
                            ((Map<String, Object>)((Map<String, Object>) dataEntry.get("extraData")).get(headerId)).put(subHeaderId, cellValue);
                    else
                        if(cellValue.equals(""))
                            ((Map<String, Object>) dataEntry.get("extraData")).put(headerId, subHeader);
                       else
                            ((Map<String, Object>)((Map<String, Object>) dataEntry.get("extraData")).get(headerId)).put(subHeader, cellValue);
                }
            }

            jsonData.add(dataEntry);
        }

        workbook.close();
        // Convert to pretty JSON string
        // Convert to pretty JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData.get(0));

        // Print the JSON string
        System.out.println(prettyJson);
        return jsonData;
    }

    public List<Map<String, Object>> parseEmployeeExcel(MultipartFile file, String tableName) throws IOException {

        List<SysExtraAttribute> basicAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy("sys_employee", null);
        List<SysExtraAttribute> contractAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy("employee_contract", null);
        List<SysExtraAttribute> crmAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy("employee_payroll_crm", null);
        List<SysExtraAttribute> dcAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy("employee_payroll_dc", null);
        List<SysExtraAttribute> offBoardAttributeList = sysExtraAttributeMapper.selectSysExtraAttributeInHierarchy("employee_off_board_info", null);

        List<SysExtraAttribute> allAttributes = new ArrayList<>();
        allAttributes.addAll(basicAttributeList);
        allAttributes.addAll(contractAttributeList);
        allAttributes.addAll(crmAttributeList);
        allAttributes.addAll(dcAttributeList);
        allAttributes.addAll(offBoardAttributeList);



        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        List<Map<String, Object>> jsonData = new ArrayList<>();

        // Assuming headers are in the first row
        Row headerRow = sheet.getRow(0);
//        Row subHeaderRow = sheet.getRow(1);

        int numColumns = headerRow.getPhysicalNumberOfCells();
        String[] headers = new String[numColumns];
//        String[] subHeaders = new String[numColumns];

        for (int i = 0; i < numColumns; i++) {
            headers[i] = dataFormatter.formatCellValue(headerRow.getCell(i));
//            subHeaders[i] = dataFormatter.formatCellValue(subHeaderRow.getCell(i));
        }
        for(String header: headers)
            System.out.print(header+", ");

        // Process the data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row currentRow = sheet.getRow(i);
            Map<String, Object> dataEntry = new HashMap<>();
            dataEntry.put("extraData", new HashMap<String, Object>());
            dataEntry.put("contractExtraData", new HashMap<String, Object>());
            dataEntry.put("crmExtraData", new HashMap<String, Object>());
            dataEntry.put("dcExtraData", new HashMap<String, Object>());
            dataEntry.put("offBoardExtraData", new HashMap<String, Object>());

            String header = null;
            String subHeader = null;

            for (int j = 0; j < numColumns; j++) {
                header = headers[j] == null || headers[j].isEmpty() ? header : headers[j];
//                subHeader = subHeaders[j];

                String cellValue = dataFormatter.formatCellValue(currentRow.getCell(j));

                // Use IDs from extraAttributeList if available
                SysExtraAttribute headerAttribute = (findAttributeIdByName(allAttributes, header) != null) ? findAttributeIdByName(allAttributes, header) : null;
                if (headerAttribute == null) {
                    //column not in extra attribute
                    if(cellValue.equals("")) {
                        dataEntry.put(header, subHeader);
                    }
                    else {
                        dataEntry.put(header, cellValue);
                    }
                    continue;
                }

                String headerId = String.valueOf(headerAttribute.getId());
                String curExtraData = null;

                //put the cell value into associated table's extra data
                switch (headerAttribute.getTableName()) {
                    case "sys_employee":
                        curExtraData = "extraData";
                        break;
                    case "employee_contract":
                        curExtraData = "contractExtraData";
                        break;
                    case "employee_payroll_crm":
                        curExtraData = "crmExtraData";
                        break;
                    case "employee_payroll_dc":
                        curExtraData = "dcExtraData";
                        break;
                    case "employee_off_board_info":
                        curExtraData = "offBoardExtraData";
                        break;
                }

                //set the cell value to null if it is empty string
                cellValue = (cellValue != null && cellValue.isEmpty()) ? null : cellValue;
                // convert cellValue to dict value
                cellValue = (headerAttribute.getDictType() != null)
                        ? sysDictDataMapper.selectDictValue(headerAttribute.getDictType(), cellValue)
                        : cellValue;

                ((Map<String, Object>) dataEntry.get(curExtraData)).put(headerId, cellValue);
//                if (subHeader == null || subHeader.isEmpty()) {
//                    if(cellValue.equals("")) {
//                        System.out.println("fdfdfdfdfd");
//                        ((Map<String, Object>) dataEntry.get(curExtraData)).put(headerId, null);
//                    }
//                    else {
//                        System.out.println("2222");
//                        ((Map<String, Object>) dataEntry.get(curExtraData)).put(headerId, cellValue);
//                    }
//                } else {
//                    System.out.println("333333");
//                    System.out.println(curExtraData);
//                    if (!((Map<String, Object>) dataEntry.get(curExtraData)).containsKey(headerId)) {
//                        System.out.println("44444");
//                        ((Map<String, Object>) dataEntry.get(curExtraData)).put(headerId, new HashMap<String, Object>());
//                    }
//                    if (subHeaderId != null) {
//                        if (cellValue.equals("")) {
//                            System.out.println("55555");
//                            ((Map<String, Object>) ((Map<String, Object>) dataEntry.get(curExtraData)).get(headerId)).put(subHeaderId, null);
//                        }
//                        else {
//                            System.out.println("66666");
//                            ((Map<String, Object>) ((Map<String, Object>) dataEntry.get(curExtraData)).get(headerId)).put(subHeaderId, cellValue);
//                        }
//                    } else {
//                        if (cellValue == null || cellValue.equals("")) {
//                            System.out.println("777777");
//                            ((Map<String, Object>) dataEntry.get(curExtraData)).put(headerId, subHeader);
//                        }
//                        else {
//                            System.out.println("888888");
//                            ((Map<String, Object>) ((Map<String, Object>) dataEntry.get(curExtraData)).get(headerId)).put(subHeader, cellValue);
//                        }
//                    }
//                }
            }

            jsonData.add(dataEntry);
        }

        workbook.close();
        // Convert to pretty JSON string
        // Convert to pretty JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData.get(0));

        // Print the JSON string
        System.out.println(prettyJson);
        return jsonData;
    }



    private SysExtraAttribute findAttributeIdByName(List<SysExtraAttribute> root, String name) {
        if (root == null) return null;

        for (SysExtraAttribute attribute : root) {
            if (name.equals(attribute.getName())) {
                return attribute;
            }
        }
        return null;
    }


    private SysExtraAttribute findChildAttributeIdByName(List<SysExtraAttribute> root, String parentName, String childName) {
        SysExtraAttribute parent = null;
        for (SysExtraAttribute attribute : root) {
            if (parentName.equals(attribute.getName())) {
                parent = attribute;
            }
        }
        if (parent == null) return null;
        return findAttributeIdByName(parent.getChildren(), childName);
    }
}
