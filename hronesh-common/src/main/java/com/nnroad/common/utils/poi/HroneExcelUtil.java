package com.nnroad.common.utils.poi;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.annotation.Excels;
import com.nnroad.common.config.NNRoadConfig;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.DictUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.common.utils.reflect.ReflectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel相关处理
 * 
 * @author Hrone
 */
public class HroneExcelUtil<T>
{
    private static final Logger log = LoggerFactory.getLogger(HroneExcelUtil.class);

    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 65536;

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Excel.Type type;

    private List<String> IGNORES = Arrays.asList("No.\n序号", "序列");

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 导入导出数据列表
     */
    private List<T> list;

    /**
     * 注解列表
     */
    private List<Object[]> fields;

    /**
     * 统计列表
     */
    private Map<Integer, Double> statistics = new HashMap<Integer, Double>();

    /**
     * 数字格式
     */
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

    /**
     *     fields of class or its superclass that should not be included to the header
     */
    private List<String> filterFieldsList;

    /**
     *     allowed fields of class or its superclass that should be included to the header
     */
    private List<String> allowedFieldsList;

    /**
     * 实体对象
     */
    public Class<T> clazz;


    public List<String> getFilterFieldsList() {
        return filterFieldsList;
    }

    public void setFilterFieldsList(List<String> filterFieldsList) {
        this.filterFieldsList = filterFieldsList;
    }

    public List<String> getAllowedFieldsList() {
        return allowedFieldsList;
    }

    public void setAllowedFieldsList(List<String> allowedFieldsList) {
        this.allowedFieldsList = allowedFieldsList;
    }

    public HroneExcelUtil(Class<T> clazz)
    {
        this(clazz, new ArrayList<>(), new ArrayList<>());
    }

    public HroneExcelUtil(Class<T> clazz, List<String> allowedFieldsList, List<String> filterFieldsList)
    {
        this.clazz = clazz;
        this.filterFieldsList = filterFieldsList;
        this.allowedFieldsList = allowedFieldsList;
    }

    public void init(List<T> list, String sheetName, Excel.Type type)
    {
        if (list == null)
        {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        createExcelField();
        createWorkbook();
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     * 
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is) throws Exception
    {
        return importExcel(StringUtils.EMPTY, true,true, is);
    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * 如果指定的sheet不存在直接报错
     *
     * @param sheetName 表格索引名
     * @param existCheck sheet不存在是否报错
     * @param is 输入流
     * @return 转换后集合
     */
    public Map<String, Integer> getHeader(String sheetName ,boolean existCheck, InputStream is, int headerSatrt, int headerEnd) throws Exception
    {
        this.type = Excel.Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName))
        {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        }

        if (sheet == null)
        {
            if(existCheck) {
                throw new IOException(MessageFormat.format("文件中sheet[{0}]不存在!", sheetName));
            } else {
                return null;
            }
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows > 0) {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> headMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(headerEnd);
            if (headerEnd != 0) {
                for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                    Cell cell = heard.getCell(i);
                    if (StringUtils.isNotNull(cell) && StringUtils.isNotEmpty(cell.toString())) {
                        // 标题不为空，则正常，不需要往前取一行
                    } else {
                        // 标题为空，则需要往前取一行的列
                        Cell cellTemp = sheet.getRow(headerSatrt).getCell(i);
                        heard.createCell(i).setCellValue(cellTemp != null ? cellTemp.toString() : "");
                    }
                }

            }

            if (headerEnd != 0) {
                for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                    Cell cell = heard.getCell(i);
                    if (StringUtils.isNotNull(cell) && StringUtils.isNotEmpty(cell.toString())) {
                        // 标题不为空，则正常，不需要往前取一行
                    } else {
                        // 标题为空，则需要往前取一行的列
                        Cell cellTemp = sheet.getRow(headerSatrt).getCell(i);
                        heard.createCell(i).setCellValue(cellTemp != null ? cellTemp.toString() : "");
                    }
                }

            }
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell)) {
                    String value = this.getCellValue(heard, i).toString();
                    headMap.put(value, i);
                } else {
                    headMap.put(null, i);
                }
            }

            return headMap;

        }


        return null;

    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * 如果指定的sheet不存在直接报错
     *
     * @param sheetName 表格索引名
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName, boolean sheetCheck,boolean columnCheck, InputStream is, int headerSatrt, int headerEnd, String...requireHeader) throws Exception
    {
        this.type = Excel.Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName))
        {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        }

        if (sheet == null)
        {
            if(sheetCheck) {
                throw new IOException(MessageFormat.format("<br/>{0}、雇主 {1} 导入成功", sheetName));
            } else {
                return null;
            }
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows > 0)
        {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(headerEnd);
            if(headerEnd != 0){
                for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++)
                {
                    Cell cell = heard.getCell(i);
                    if (StringUtils.isNotNull(cell) && StringUtils.isNotEmpty(cell.toString()))
                    {
                        // 标题不为空，则正常，不需要往前取一行
                    }
                    else
                    {
                        // 标题为空，则需要往前取一行的列
                        Cell cellTemp = sheet.getRow(headerSatrt).getCell(i);
                        heard.createCell(i).setCellValue(cellTemp!=null?cellTemp.toString():"");
                    }
                }

            }
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++)
            {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell))
                {
                    String value = this.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                }
                else
                {
                    cellMap.put(null, i);
                }
            }
            for (String head: requireHeader) {
                if(!cellMap.containsKey(head)){
                    throw new IOException(MessageFormat.format("文件中sheet[{0}] 的表头默认应为{1}~{2}行，请确认文件是否正确。", sheetName, headerSatrt+1, headerEnd+1));
                }
            }
            // 有数据时才处理 得到类的所有field.
            Field[] allFields = clazz.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (int col = 0; col < allFields.length; col++)
            {
                Field field = allFields[col];
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type))
                {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    String excelHeader=attr.name();
                    if(cellMap.keySet().contains(excelHeader)){
                        Integer column = cellMap.get(excelHeader);
                        fieldsMap.put(column, field);
                    } else {
                        // 不需要报错，因为payment_notice文件的列可能缺失顺序也不一定
//                        throw new IOException(MessageFormat.format(MessageUtils.message("msg.errer.col_not_exist"), sheetName ,cellMap.keySet().toString(),excelHeader));
//                        throw new IOException("文件中sheet[" + sheetName + "]的所有列名为" + cellMap.keySet() +",不存在列名为'" + excelHeader +"'");
                    }
                }
            }

            if(columnCheck) {
                // 校验是否导入的列名存在未定义的
                StringBuilder messageBuilder = new StringBuilder();
                for(String excelHeader : cellMap.keySet()){
                    boolean errorFlg = true;
                    for (Field value : fieldsMap.values()) {
                        String fieldHeader= value.getAnnotation(Excel.class).name();
                        if (fieldHeader.equals(excelHeader) || IGNORES.contains(excelHeader)|| StringUtils.isEmpty(excelHeader.trim())){
                            errorFlg = false;
                            break;
                        }
                    }
                    if (errorFlg){
                        messageBuilder.append("文件中sheet[" + sheetName + "]的列名[" + excelHeader +"]与系统定义不符");
                        messageBuilder.append("<br/>");
                    }
                }
                if (StringUtils.isNotEmpty(messageBuilder.toString())){
                    throw new IOException(messageBuilder.toString());
                }
            }

            for (int i = headerEnd + 1; i < rows; i++)
            {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                T entity = null;
                for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet())
                {
                    Object val = this.getCellValue(row, entry.getKey());

                    // 如果不存在实例则新建.
                    entity = (entity == null ? clazz.newInstance() : entity);
                    // 从map中得到对应列的field.
                    Field field = fieldsMap.get(entry.getKey());
                    // 取得类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType)
                    {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0"))
                        {
                            val = StringUtils.substringBefore(s, ".0");
                        }
                        else
                        {
                            String dateFormat = field.getAnnotation(Excel.class).dateFormat();
                            if (StringUtils.isNotEmpty(dateFormat))
                            {
                                val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                            }
                            else
                            {
                                val = Convert.toStr(val);
                            }
                        }
                    }
                    else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val)))
                    {
                        val = Convert.toInt(val);
                    }
                    else if (Long.TYPE == fieldType || Long.class == fieldType)
                    {
                        val = Convert.toLong(val);
                    }
                    else if (Double.TYPE == fieldType || Double.class == fieldType)
                    {
                        val = Convert.toDouble(val);
                    }
                    else if (Float.TYPE == fieldType || Float.class == fieldType)
                    {
                        val = Convert.toFloat(val);
                    }
                    else if (BigDecimal.class == fieldType)
                    {
                        val = Convert.toBigDecimal(val, BigDecimal.valueOf(0));
                    }
                    else if (Date.class == fieldType)
                    {
                        if (val instanceof String)
                        {
                            val = DateUtils.parseDate(val);
                        }
                        else if (val instanceof Double)
                        {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    }
                    else if (Boolean.TYPE == fieldType || Boolean.class == fieldType)
                    {
                        val = Convert.toBool(val, false);
                    }
                    if (StringUtils.isNotNull(fieldType))
                    {
                        Excel attr = field.getAnnotation(Excel.class);
                        String propertyName = field.getName();
                        if (StringUtils.isNotEmpty(attr.targetAttr()))
                        {
                            propertyName = field.getName() + "." + attr.targetAttr();
                        }
                        else if (StringUtils.isNotEmpty(attr.readConverterExp()))
                        {
                            val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                        }
                        else if (StringUtils.isNotEmpty(attr.dictType()))
                        {
                            val = reverseDictByExp(Convert.toStr(val), attr.dictType(), attr.separator());
                        }
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                if(entity != null){
                    list.add(entity);
                }
            }
        }


        return list;
    }




    /**
     * 对excel表单指定表格索引名转换成list
     * 
     * @param sheetName 表格索引名
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName , boolean sheetCheck, boolean columnCheck, InputStream is) throws Exception
    {
        this.type = Excel.Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName))
        {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        }
        else
        {
            // 如果传入的sheet名不存在则默认指向第1个sheet.
            sheet = wb.getSheetAt(0);
        }

        if (sheet == null)
        {
            if(sheetCheck) {
                throw new IOException(MessageFormat.format("文件中sheet[{0}]不存在!", sheetName));
            } else {
                return null;
            }
        }

        int rows = sheet.getPhysicalNumberOfRows();


        if (rows > 0)
        {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(0);
            for (int i = 0; i < heard.getLastCellNum(); i++)
            {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell))
                {
                    String value = this.getCellValue(heard, i).toString().trim();
                    cellMap.put(value, i);
                }
                else
                {
                    cellMap.put(null, i);
                }
            }
            // 有数据时才处理 得到类的所有field.
            Field[] allFields = clazz.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (int col = 0; col < allFields.length; col++)
            {
                Field field = allFields[col];
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type))
                {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    String excelHeader=attr.name();
                    if(cellMap.keySet().contains(excelHeader)){
                        Integer column = cellMap.get(excelHeader);
                        fieldsMap.put(column, field);
                    } else {
                        // 不需要报错，因为payment_notice文件的列可能缺失顺序也不一定
//                        throw new IOException(MessageFormat.format(MessageUtils.message("msg.errer.col_not_exist"), sheetName ,cellMap.keySet().toString(),excelHeader));
//                        throw new IOException("文件中sheet[" + sheetName + "]的所有列名为" + cellMap.keySet() +",不存在列名为'" + excelHeader +"'");
                    }
                }
            }
            if (columnCheck) {
                // 校验是否导入的列名存在未定义的
                StringBuilder messageBuilder = new StringBuilder();
                for(String map1 : cellMap.keySet()){
                    boolean errorFlg = true;
                    for (Field value : fieldsMap.values()) {
                        String excelHeader= value.getAnnotation(Excel.class).name();
                        if (StringUtils.isEmpty(map1) || map1.equals("No.\n序号") || excelHeader.equals(map1.trim())){
                            errorFlg = false;
                            break;
                        }
                    }
                    if (errorFlg){
                        messageBuilder.append("文件中sheet[" + sheetName + "]的列名[" + map1 +"]与系统定义不符");
                        messageBuilder.append("<br/>");
                    }
                }
                if (StringUtils.isNotEmpty(messageBuilder.toString())){
                    throw new IOException(messageBuilder.toString());
                }
            }

            for (int i = 1; i < rows; i++)
            {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                T entity = null;
                for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet())
                {
                    Object val = this.getCellValue(row, entry.getKey());

                    // 如果不存在实例则新建.
                    entity = (entity == null ? clazz.newInstance() : entity);
                    // 从map中得到对应列的field.
                    Field field = fieldsMap.get(entry.getKey());
                    // 取得类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType)
                    {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0"))
                        {
                            val = StringUtils.substringBefore(s, ".0");
                        }
                        else
                        {
                            String dateFormat = field.getAnnotation(Excel.class).dateFormat();
                            if (StringUtils.isNotEmpty(dateFormat))
                            {
                                val = DateUtils.parseDateToStr(dateFormat, (Date) val);
                            }
                            else
                            {
                                val = Convert.toStr(val);
                            }
                        }
                    }
                    else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val)))
                    {
                        val = Convert.toInt(val);
                    }
                    else if (Long.TYPE == fieldType || Long.class == fieldType)
                    {
                        val = Convert.toLong(val);
                    }
                    else if (Double.TYPE == fieldType || Double.class == fieldType)
                    {
                        val = Convert.toDouble(val);
                    }
                    else if (Float.TYPE == fieldType || Float.class == fieldType)
                    {
                        val = Convert.toFloat(val);
                    }
                    else if (BigDecimal.class == fieldType)
                    {
                        val = Convert.toBigDecimal(val, BigDecimal.valueOf(0));
                    }
                    else if (Date.class == fieldType)
                    {
                        if (val instanceof String)
                        {
                            val = DateUtils.parseDate(val);
                        }
                        else if (val instanceof Double)
                        {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    }
                    else if (Boolean.TYPE == fieldType || Boolean.class == fieldType)
                    {
                        val = Convert.toBool(val, false);
                    }
                    if (StringUtils.isNotNull(fieldType))
                    {
                        Excel attr = field.getAnnotation(Excel.class);
                        String propertyName = field.getName();
                        if (StringUtils.isNotEmpty(attr.targetAttr()))
                        {
                            propertyName = field.getName() + "." + attr.targetAttr();
                        }
                        else if (StringUtils.isNotEmpty(attr.readConverterExp()))
                        {
                            val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                        }
                        else if (StringUtils.isNotEmpty(attr.dictType()))
                        {
                            val = reverseDictByExp(Convert.toStr(val), attr.dictType(), attr.separator());
                        }
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public AjaxResult exportExcel(List<T> list, String sheetName)
    {
        this.init(list, sheetName, Excel.Type.EXPORT);
        return exportExcel();
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public AjaxResult importTemplateExcel(String sheetName)
    {
        this.init(null, sheetName, Excel.Type.IMPORT);
        return exportExcel();
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 
     * @return 结果
     */
    public AjaxResult exportExcel()
    {
        OutputStream out = null;
        try
        {
            // 取出一共有多少个sheet.
            double sheetNo = Math.ceil(list.size() / sheetSize);
            for (int index = 0; index <= sheetNo; index++)
            {
                createSheet(sheetNo, index);

                // 产生一行
                Row row = sheet.createRow(0);
                int column = 0;
                // 写入各个字段的列头名称
                for (Object[] os : fields)
                {
                    Excel excel = (Excel) os[1];
                    this.createCell(excel, row, column++);
                }
                if (Excel.Type.EXPORT.equals(type))
                {
                    fillExcelData(index, row);
                    addStatisticsRow();
                }
            }
            String filename = encodingFilename(sheetName);
            out = new FileOutputStream(getAbsoluteFile(filename));
            wb.write(out);
            return AjaxResult.success(filename);
        }
        catch (Exception e)
        {
            log.error("export Excel error :{}", e.getMessage());
            throw new BusinessException("msg.export.error_failed");
        }
        finally
        {
            if (wb != null)
            {
                try
                {
                    wb.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param targetWB excel对象
     * @param sheetNo sheet顺序
     * @param sheetName 工作表的名称
     * @param list 数据集
     * @return 结果
     */
    public void addSheetToExcel(Workbook targetWB, int sheetNo, String sheetName, List<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        createExcelField();
        // 生成sheet
        this.sheet = targetWB.createSheet();
        this.styles = createStyles(targetWB);
        targetWB.setSheetName(sheetNo, sheetName);
        // 产生一行
        Row row = sheet.createRow(0);
        int column = 0;
        // 写入各个字段的列头名称
        for (Object[] os : fields) {
            Excel excel = (Excel) os[1];
            this.createCell(excel, row, column++);
        }
        fillExcelData(0, row);
    }

    /**
     * 填充excel数据
     * 
     * @param index 序号
     * @param row 单元格行
     */
    public void fillExcelData(int index, Row row)
    {
        int startNo = index * sheetSize;
        int endNo = Math.min(startNo + sheetSize, list.size());
        for (int i = startNo; i < endNo; i++)
        {
            row = sheet.createRow(i + 1 - startNo);
            // 得到导出对象.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os : fields)
            {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * 填充excel数据
     *
     * @param list 对象数据
     * @param sheet 对象sheet
     * @param startNo 开始处理行数
     */
    public void fillSheetDataByTemplate(List<T> list, XSSFSheet sheet, int startNo) throws Exception{
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;

        int head1 = startNo - 2;
        Map<String, Integer> excelFieldIndexMap = new HashMap<String, Integer>();
        int cellCount = sheet.getRow(head1).getLastCellNum();
        for (int i = 0; i < cellCount; i++) {
            XSSFCell cell1 = sheet.getRow(head1).getCell(i);
            XSSFCell cell2 = sheet.getRow(head1 + 1).getCell(i);
            if (cell1 == null && cell2 == null) {
                continue;
            }

            String fieldName = cell2 != null && StringUtils.isNotEmpty(cell2.getStringCellValue().trim()) ? cell2.getStringCellValue().trim() : cell1.getStringCellValue().trim();
            if (StringUtils.isNotEmpty(fieldName)) {
                excelFieldIndexMap.put(fieldName.replaceAll(" ","").replaceAll("\n",""), i);
            }
        }

        // 初始化实体名称
        createExcelField();
        // 为要填充的数据增加空行
        int beginRowNo = startNo + 1;
        Row beginRow = sheet.getRow(beginRowNo);
        int addNo = list.size();
        int moveBeginRow = beginRowNo+1;
        //如果移动的开始行不存在，则创建一个空行，否则复制的时候会丢掉不存在的行
        if(sheet.getRow(moveBeginRow)==null){
            sheet.createRow(moveBeginRow);
        }
        int lastRowNo = sheet.getLastRowNum();

        //计算移动的行数
        int movedNo = lastRowNo - moveBeginRow + 1;
        //临时区的开始行
        int tempBeginRowNo = lastRowNo + addNo + 1;
        //临时区的结束行
        int tempLastRowNo = tempBeginRowNo + movedNo - 1;
        //末尾部分的最终开始行
        int finalBeginRowNo = addNo + beginRowNo;
        //移动开始行以后的行到临时的开始行
        moveRows(sheet,moveBeginRow,lastRowNo,tempBeginRowNo);
        //在空出位置插入新行并复制第一行的样式
        for (int i = moveBeginRow; i <finalBeginRowNo; i++){
            Row row = sheet.getRow(i);
            if (row != null){
                sheet.removeRow(row);
            }
            //创建空白的行
            sheet.createRow(i);
            if(beginRow!=null){
                //复制第一行的样式到当前行
                sheet.copyRows(beginRowNo,beginRowNo, i, new CellCopyPolicy());
            }
        }
        //将移动的行从临时开始行回移新增好的行的末尾
        moveRows(sheet,tempBeginRowNo,tempLastRowNo,finalBeginRowNo);
        // 填充数据
        for (int i = 0; i < list.size(); i++) {
            // 得到需填充的明细数据
            T vo = (T) list.get(i);
            // 第一列填入番号
            sheet.getRow(startNo + i).getCell(0).setCellValue(i + 1);
            for(int j = 0; j < fields.size(); j++) {
                Object[] os = fields.get(j);
                Field field = (Field) os[0];
                Excel attr = (Excel) os[1];

                // 根据列名获得列索引值，定位单元格
                String fieldName = attr.name().replaceAll(" ", "").replaceAll("\n", "");

                if (!excelFieldIndexMap.containsKey(fieldName)) {
                    continue;
                }
                XSSFCell cell = sheet.getRow(startNo + i).getCell(excelFieldIndexMap.get(fieldName));

                // 设置实体类私有属性可访问
                field.setAccessible(true);
                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                String dictType = attr.dictType();
                // 数据填充
                // 日期格式
                if (cell == null) continue;
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                    // 读取内容转表达式(性别)
                } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                    // 数据字典类型
                } else if (StringUtils.isNotEmpty(dictType) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertDictByExp(Convert.toStr(value), dictType, separator));
                    // BigDecimal型
                } else if (value instanceof BigDecimal) {
                    // sheet.getRow(startNo + i).getCell(j+1).setCellValue((((BigDecimal) value).setScale(2, excel.roundingMode())).toString());
                    cell.setCellValue(
                            StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value) : Convert.toInt(value));
                    // String型
                } else if (value instanceof String) {
                    cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
                } else {
                    if (String.class.getName().equals(field.getType().getName())) {
                        cell.setCellValue("");
                    } else if (BigDecimal.class.getName().equals(field.getType().getName())) {
                        cell.setCellValue("0");
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
    }

    /**
     * 创建表格样式
     * 
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb)
    {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font totalFont = wb.createFont();
        totalFont.setFontName("Arial");
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);
        styles.put("total", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        return styles;
    }

    /**
     * 创建单元格
     */
    public Cell createCell(Excel attr, Row row, int column)
    {
        // 创建列
        Cell cell = row.createCell(column);
        // 表头国际化
        String excelHeader= attr.name();
        // 写入列信息
        cell.setCellValue(excelHeader);
        setDataValidation(attr, row, column);
        cell.setCellStyle(styles.get("header"));
        return cell;
    }

    /**
     * 设置单元格信息
     * 
     * @param value 单元格值
     * @param attr 注解相关
     * @param cell 单元格信息
     */
    public void setCellVo(Object value, Excel attr, Cell cell)
    {
        if (Excel.ColumnType.STRING == attr.cellType())
        {
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        }
        else if (Excel.ColumnType.NUMERIC == attr.cellType())
        {
            if(value == null || value.equals("")) {
                cell.setCellValue(attr.defaultValue());
            } else {
                cell.setCellValue(StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value, null) : Convert.toInt(value));
            }
        }
    }

    /**
     * 创建表格样式
     */
    public void setDataValidation(Excel attr, Row row, int column)
    {
        if (attr.name().indexOf("注：") >= 0)
        {
            sheet.setColumnWidth(column, 6000);
        }
        else
        {
            // 设置列宽
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
        }
        // 如果设置了提示信息则鼠标放上去提示.
        if (StringUtils.isNotEmpty(attr.prompt()))
        {
            // 这里默认设了2-101列提示.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        // 如果设置了combo属性则本列只能选择不能输入
        if (attr.combo().length > 0)
        {
            // 这里默认设了2-101列只能选择不能输入.
            setXSSFValidation(sheet, attr.combo(), 1, 100, column, column);
        }
    }

    /**
     * 添加单元格
     */
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column)
    {
        Cell cell = null;
        try
        {
            // 设置行高
            row.setHeight((short) (attr.height() * 20));
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport())
            {
                // 创建cell
                cell = row.createCell(column);
                int align = attr.align().value();
                cell.setCellStyle(styles.get("data" + (align >= 1 && align <= 3 ? align : "")));

                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                String dictType = attr.dictType();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                }
                else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                }
                else if (StringUtils.isNotEmpty(dictType) && StringUtils.isNotNull(value))
                {
                    cell.setCellValue(convertDictByExp(Convert.toStr(value), dictType, separator));
                }
                else if (value instanceof BigDecimal && -1 != attr.scale())
                {
                    cell.setCellValue((((BigDecimal) value).setScale(attr.scale(), attr.roundingMode())).toString());
                }
                else
                {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
                addStatisticsData(column, Convert.toStr(value), attr);
            }
        }
        catch (Exception e)
        {
            log.error("export Excel failed :{}", e);
            e.printStackTrace();
        }
        return cell;
    }

    /**
     * 设置 POI XSSFSheet 单元格提示
     * 
     * @param sheet 表单
     * @param promptTitle 提示标题
     * @param promptContent 提示内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
            int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     * 
     * @param sheet 要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol 开始列
     * @param endCol 结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol)
    {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation)
        {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        }
        else
        {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     * 
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @param separator 分隔符
     * @return 解析后值
     * @throws Exception
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) throws Exception
    {
        StringBuilder propertyString = new StringBuilder();
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (StringUtils.containsAny(separator, propertyValue))
                {
                    for (String value : propertyValue.split(separator))
                    {
                        if (itemArray[0].equals(value))
                        {
                            propertyString.append(itemArray[1] + separator);
                            break;
                        }
                    }
                }
                else
                {
                    if (itemArray[0].equals(propertyValue))
                    {
                        return itemArray[1];
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     * 
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @param separator 分隔符
     * @return 解析后值
     * @throws Exception
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) throws Exception
    {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource)
        {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue))
            {
                for (String value : propertyValue.split(separator))
                {
                    if (itemArray[1].equals(value))
                    {
                        propertyString.append(itemArray[0] + separator);
                        break;
                    }
                }
            }
            else
            {
                if (itemArray[1].equals(propertyValue))
                {
                    return itemArray[0];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 解析字典值
     * 
     * @param dictValue 字典值
     * @param dictType 字典类型
     * @param separator 分隔符
     * @return 字典标签
     */
    public static String convertDictByExp(String dictValue, String dictType, String separator) throws Exception
    {
        return DictUtils.getDictLabel(dictType, dictValue, separator);
    }

    /**
     * 反向解析值字典值
     * 
     * @param dictLabel 字典标签
     * @param dictType 字典类型
     * @param separator 分隔符
     * @return 字典值
     */
    public static String reverseDictByExp(String dictLabel, String dictType, String separator) throws Exception
    {
        return DictUtils.getDictValue(dictType, dictLabel, separator);
    }

    /**
     * 合计统计信息
     */
    private void addStatisticsData(Integer index, String text, Excel entity)
    {
        if (entity != null && entity.isStatistics())
        {
            Double temp = 0D;
            if (!statistics.containsKey(index))
            {
                statistics.put(index, temp);
            }
            try
            {
                temp = Double.valueOf(text);
            }
            catch (NumberFormatException e)
            {
            }
            statistics.put(index, statistics.get(index) + temp);
        }
    }

    /**
     * 创建统计行
     */
    public void addStatisticsRow()
    {
        if (statistics.size() > 0)
        {
            Cell cell = null;
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            Set<Integer> keys = statistics.keySet();
            cell = row.createCell(0);
            cell.setCellStyle(styles.get("total"));
            cell.setCellValue("合计");

            for (Integer key : keys)
            {
                cell = row.createCell(key);
                cell.setCellStyle(styles.get("total"));
                cell.setCellValue(DOUBLE_FORMAT.format(statistics.get(key)));
            }
            statistics.clear();
        }
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename)
    {
        // 取系统时间
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNowStr = sdf.format(nowDate);
        // 定时任务提醒邮件附件时，文件名不拼时间戳
        if (filename.equals("Contact_End_Employee_List") || filename.equals("Missing_Employee_List")){
            filename = filename + ".xlsx";
        } else {
            filename = filename + "_" + dateNowStr+ ".xlsx";
        }
        return filename;
    }

    /**
     * 获取下载路径
     * 
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename)
    {
        String downloadPath = NNRoadConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 获取bean中的属性值
     * 
     * @param vo 实体对象
     * @param field 字段
     * @param excel 注解
     * @return 最终的属性值
     * @throws Exception
     */
    private Object getTargetValue(T vo, Field field, Excel excel) throws Exception
    {
        Object o = field.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr()))
        {
            String target = excel.targetAttr();
            if (target.indexOf(".") > -1)
            {
                String[] targets = target.split("[.]");
                for (String name : targets)
                {
                    o = getValue(o, name);
                }
            }
            else
            {
                o = getValue(o, target);
            }
        }
        return o;
    }

    /**
     * 以类的属性的get方法方法形式获取值
     * 
     * @param o
     * @param name
     * @return value
     * @throws Exception
     */
    private Object getValue(Object o, String name) throws Exception
    {
        if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name))
        {
            Class<?> clazz = o.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(o);
        }
        return o;
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField()
    {
        this.fields = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.stream(
                clazz.getSuperclass().getDeclaredFields())
                .filter(f->(allowedFieldsList.isEmpty() || allowedFieldsList.contains(f.getName())) && !filterFieldsList.contains(f.getName()))
                .collect(Collectors.toList()));
        tempFields.addAll(Arrays.stream(clazz.getDeclaredFields())
                .filter(f->(allowedFieldsList.isEmpty() || allowedFieldsList.contains(f.getName())) && !filterFieldsList.contains(f.getName()))
                .collect(Collectors.toList()));

        for (Field field : tempFields)
        {
            // 单注解
            if (field.isAnnotationPresent(Excel.class))
            {
                putToField(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class))
            {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels)
                {
                    putToField(field, excel);
                }
            }
        }
        this.fields = this.fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());
    }

    /**
     * 放到字段集合中
     */
    private void putToField(Field field, Excel attr)
    {
        if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type))
        {
            this.fields.add(new Object[] { field, attr });
        }
    }

    /**
     * 创建一个工作簿
     */
    public void createWorkbook()
    {
        this.wb = new SXSSFWorkbook(500);
    }

    /**
     * 创建工作表
     * 
     * @param sheetNo sheet数量
     * @param index 序号
     */
    public void createSheet(double sheetNo, int index)
    {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        // 设置工作表的名称.
        if (sheetNo == 0)
        {
            wb.setSheetName(index, sheetName);
        }
        else
        {
            wb.setSheetName(index, sheetName + index);
        }
    }

    /**
     * 获取单元格值
     * 
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column)
    {
        if (row == null)
        {
            return row;
        }
        Object val = "";
        try
        {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell))
            {
                if(cell.getCellType() == CellType.FORMULA){
                    switch (cell.getCachedFormulaResultType()) {
                        case STRING:
                            val = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setGroupingUsed(false);
                            val = String.valueOf(nf.format(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            val = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            val = cell.getCellFormula();
                    }
                }else if(cell.getCellType() == CellType.NUMERIC)
                {
                    short format = cell.getCellStyle().getDataFormat();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = null;
                        if (format == 20 || format == 32) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            double value = cell.getNumericCellValue();
                            Date date = DateUtil
                                    .getJavaDate(value);
                            val = sdf.format(date);
                        }else {
                            // 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        try {
                            // 日期
                            val = sdf.format(cell.getDateCellValue());
                        } catch (Exception e) {
                            try {
                                throw new Exception("exception on get date data !".concat(e.toString()));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }finally{
                            sdf = null;
                        }
                    }  else {
                        Double value = cell.getNumericCellValue();
                        BigDecimal bd1 = new BigDecimal(Double.toString(value));
                        String temp = bd1.toPlainString();
                        if (temp.indexOf('.') > -1) {
                            val = temp.replaceAll("0+?$", "").replaceAll("[.]$", "");
                        } else {
                            val = temp;
                        }
                    }
                }
                else if (cell.getCellType() == CellType.STRING)
                {
                    val = cell.getStringCellValue();
                }
                else if (cell.getCellType() == CellType.BOOLEAN)
                {
                    val = cell.getBooleanCellValue();
                }
                else if (cell.getCellType() == CellType.ERROR)
                {
                    val = cell.getErrorCellValue();
                }

            }
        }
        catch (Exception e)
        {
            return val;
        }
        return val;
    }

    /**
     * 移动指定行区间到指定位置，并删除移动后原地的行
     * @param sheet
     * @param srcBeginRow
     * @param srcEndRow
     * @param destBeginRow
     * @return void
     */
    private static void moveRows(XSSFSheet sheet,int srcBeginRow,int srcEndRow,int destBeginRow){
        //先复制到目的位置
        sheet.copyRows(srcBeginRow, srcEndRow, destBeginRow, new CellCopyPolicy());
        //清理之前残留的合并单元格
        List<Integer> removeMergedRegion = new ArrayList<>();
        for(int i=sheet.getMergedRegions().size()-1;i>=0;i--){
            CellRangeAddress address = sheet.getMergedRegions().get(i);
            //如果合并单元格在复制前的位置，则删除
            if(address.getFirstRow() >= srcBeginRow && address.getFirstRow()<destBeginRow ){
                removeMergedRegion.add(i);
            }
        };
        //执行清理合并的单元格
        for(Integer i:removeMergedRegion){
            sheet.removeMergedRegion(i);
        }
        //删除移动后原地的行
        for (int i = srcBeginRow; i <= srcEndRow; i++){
            Row row = sheet.getRow(i);
            if (row != null){
                //删除复制后残留的行
                sheet.removeRow(row);
            }
        }

    }
}