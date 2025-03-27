package com.nnroad.lead.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.NnroadSequence;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.lead.domain.ClientLeads;
import com.nnroad.lead.domain.ClientLeadsConfig;
import com.nnroad.lead.enums.ClientLeadsStatus;
import com.nnroad.lead.mapper.ClientLeadsMapper;
import com.nnroad.lead.service.IClientLeadsConfigService;
import com.nnroad.lead.service.IClientLeadsService;
import com.nnroad.lead.vo.Leads2ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ClientLeadsService业务层处理
 *
 */
@Service
public class ClientLeadsServiceImpl implements IClientLeadsService {

    @Autowired
    private ClientLeadsMapper clientLeadsMapper;

    @Autowired
    private IClientLeadsConfigService clientLeadsConfigService;

    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private ISysClientService clientService;

    /**
     * 查询ClientLeads
     *
     * @param id ClientLeadsID
     * @return ClientLeads
     */
    @Override
    public ClientLeads selectClientLeadsById(Long id) {
        return clientLeadsMapper.selectClientLeadsById(id);
    }

    /**
     * 查询ClientLeads列表
     *
     * @param clientLeads ClientLeads
     * @return ClientLeads
     */
    @Override
    public List<ClientLeads> selectClientLeadsList(ClientLeads clientLeads) {

        mapClientLeads(clientLeads);

        return clientLeadsMapper.selectClientLeadsList(clientLeads);

    }

    /**
     * 新增ClientLeads
     *
     * @param clientLeads ClientLeads
     * @return 结果
     */
    @Override
    public int insertClientLeads(ClientLeads clientLeads) {

        clientLeads.setLeadStatus(ClientLeadsStatus.Ongoing.getStrValue());

        clientLeads.setCreateTime(DateUtils.getNowDate());


        return clientLeadsMapper.insertClientLeads(clientLeads);
    }

    /**
     * 修改ClientLeads
     *
     * @param clientLeads ClientLeads
     * @return 结果
     */
    @Override
    public int updateClientLeads(ClientLeads clientLeads) {
        clientLeads.setUpdateTime(DateUtils.getNowDate());
        return clientLeadsMapper.updateClientLeads(clientLeads);
    }

    /**
     * 删除ClientLeads对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClientLeadsByIds(String ids) {
        return clientLeadsMapper.deleteClientLeadsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ClientLeads信息
     *
     * @param id ClientLeadsID
     * @return 结果
     */
    @Override
    public int deleteClientLeadsById(Long id) {
        return clientLeadsMapper.deleteClientLeadsById(id);
    }


    @Override
    public int converteClientLeads(Leads2ClientVo leads2ClientVo, SysClient client) {
        ClientLeads clientLeads = clientLeadsMapper.selectClientLeadsById(leads2ClientVo.getId());

        client.setClientName(clientLeads.getCompanyName());
        client.setIndustry(clientLeads.getIndustry());
        client.setClientLocation(leads2ClientVo.getLocation());
        client.setContactPersonName(clientLeads.getContactName());
        client.setContactPersonEmail(clientLeads.getContactEmail());
        client.setExtraData(JSONObject.of("80", "inactive"));
        client.setRemark(leads2ClientVo.getRemark());
        //插入Client Leads 数据
        clientService.insertSysClient(client);
        leads2ClientVo.setLeadStatus(ClientLeadsStatus.Converted.getStrValue());
        leads2ClientVo.setConvertedDate(new Date());
        leads2ClientVo.setUpdateBy(SecurityUtils.getUsername());
        //If leads is marked as unqualified and they need they are converter
        String yesId = clientLeadsConfigService.selectClientLeadsConfigList("qualified_leads", "Yes", "0").get(0).getId().toString();
        leads2ClientVo.setQualifiedLeads(yesId);

        return clientLeadsMapper.updateClientLeads(leads2ClientVo);
    }

    @Override
    public int holdClientLeads(ClientLeads clientLeads) {
        return clientLeadsMapper.updateClientLeads(clientLeads);
    }

    @Override
    public List<ClientLeads> selectExportClientLeadsList(ClientLeads clientLeads) {
        Map<String, Object> params = clientLeads.getParams();
        String engageDate = params.get("engageDate") != null ? params.get("engageDate").toString() : "";
        if (!StringUtils.isEmpty(engageDate)) {
            String[] durationArray = engageDate.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("entryDateBegin", beginTime.trim());
            params.put("entryDateEnd", endTime.trim());
        }

        clientLeads.setParams(params);
        return clientLeadsMapper.selectExportClientLeadsList(clientLeads);
    }

    @Override
    public List<Map<String, Object>> getFromSQL(String sql) {
        return clientLeadsMapper.getFromSQL(sql);
    }

    @Override
    public List<Object> getMonthlyReport(ClientLeads clientLeads) throws Exception {
            mapClientLeads(clientLeads);

//        String noOfLeadsStrCurrentYearToMonth = "No. of leads current year to month";
//        String noOfQLeadsStrCurrentYearToMonth = "No. of qualified leads current year to month";
//        String TBCLeadsStrCurrentYearToMonth = "No. of TBC leads current year to month";
//        String convertedLeadsStrCurrentYearToMonth = "No. of converted leads current year to month";
//        String QlOutOfTotalLStrCurrentYearToMonth = "Qualified leads out of total leads current year to month";
//        String conversionRateStrCurrentYearToMonth = "Conversion rate current year to month";

        String noOfLeadsStr = "No. of leads";
        String noOfQLeadsStr = "No. of qualified leads";
        String TBCLeadsStr = "No. of TBC leads";
        String convertedLeadsStr = "No. of converted leads";
        String QlOutOfTotalLStr = "Qualified leads out of total leads";
        String conversionRateStr = "Conversion rate";
        String inquiryReceivedDateColumn = "inquiry_received_date";

        String rowName = noOfLeadsStr;
        List<Map<String, Object>> numOfLeads = clientLeadsMapper.getMonthlyReportByYears(clientLeads, rowName, inquiryReceivedDateColumn);


        List<Map<String, Object>> resultListMap = new ArrayList<>(numOfLeads);

        //get qualified_leads config
        rowName = noOfQLeadsStr;  //Qualified leads
        ClientLeadsConfig qualifiedConfig = new ClientLeadsConfig();
        qualifiedConfig.setColumnName("qualified_leads");
        List<ClientLeadsConfig> qualifiedConfigList = clientLeadsConfigService.selectClientLeadsConfigList(qualifiedConfig);
        String TBCLeads = qualifiedConfigList.stream()
                .filter(conf -> conf.getName().equals("TBC"))
                .findFirst()
                .orElseThrow(() -> new Exception("Leads Config doesn't contain Qualified : TBC config!"))
                .getId().toString();
        String yesQualifiedLeads = qualifiedConfigList.stream()
                .filter(conf -> conf.getName().equals("Yes"))
                .findFirst()
                .orElseThrow(() -> new Exception("Leads Config doesn't contain Qualified : Yes config!"))
                .getId().toString();

        //handle default view with default rows
        ClientLeads tempClientLead = mapClientLeads(clientLeads);;  // 使用复制构造函数创建副本

        if (StringUtils.isEmpty(tempClientLead.getQualifiedLeads())) {
            tempClientLead.setQualifiedLeads(yesQualifiedLeads);
            List<Map<String, Object>> numOfQualifiedLeads = clientLeadsMapper.getMonthlyReportByYears(tempClientLead, rowName, inquiryReceivedDateColumn);

            //add No. of qualified leads to result List
            resultListMap.addAll(numOfQualifiedLeads);
            //add QualifiedLeadsRationRow to result list
            resultListMap.addAll(calculateRatio(QlOutOfTotalLStr, numOfQualifiedLeads, numOfLeads));


            //No. of TBC leads
            rowName = TBCLeadsStr;
            tempClientLead.setQualifiedLeads(TBCLeads);
            List<Map<String, Object>> numOfTBCLeads = clientLeadsMapper.getMonthlyReportByYears(tempClientLead, rowName, inquiryReceivedDateColumn);

            //add TBCLeads to resultList
            resultListMap.addAll(numOfTBCLeads);

            //No. of Converted Leads
            rowName = convertedLeadsStr;
            tempClientLead.setQualifiedLeads(yesQualifiedLeads);
            tempClientLead.setLeadStatus(ClientLeadsStatus.Converted.getStrValue());
            String convertedDateColumn = "contract_signed_date"; // not converted_date because converted_date is when "Converted" btn was pressed, contract_signed_date is what BD counts as a converted date
            List<Map<String, Object>> numOfConvertedLeads = clientLeadsMapper.getMonthlyReportByYears(tempClientLead, rowName, convertedDateColumn);

            //add convertedLeadsList to resultList
            resultListMap.addAll(numOfConvertedLeads);
            //add conversion rate List to result list
            resultListMap.addAll(calculateRatio(conversionRateStr, numOfConvertedLeads, numOfQualifiedLeads));
            //clean if the row doesn't have any Year data
            resultListMap.removeIf(l -> !l.containsKey("Year"));


            List<Map<String, Object>> year2024List = new ArrayList<>();

            for (Map<String, Object> leadData : resultListMap) {
                Object yearObj = leadData.get("Year");
                // 假设 yearObj 是一个字符串
                if (yearObj instanceof String) {
                    String yearString = (String) yearObj;
                    // 将字符串转换为 BigDecimal
                    BigDecimal year = new BigDecimal(yearString);
                    // 进行比较
                    LocalDate currentDate = LocalDate.now();

                    // 获取当前年份
                    int currentYear = currentDate.getYear();
                    if (year.compareTo(new BigDecimal(currentDate.getYear())) == 0) {
                        year2024List.add(leadData);
                    }
                }

            }


//            Year to Month
            // 年度到月份的汇总
            List<Map<String, Object>> newData = new ArrayList<>();
            List<Map<String, Object>> noOfLeadsData = new ArrayList<>();
            List<Map<String, Object>> qualifiedLeadsData = new ArrayList<>();
            List<Map<String, Object>> convertedLeadsData = new ArrayList<>();
            for (Map<String, Object> row : year2024List) {

                BigDecimal cumulativeSum = BigDecimal.ZERO;
                Map<String, Object> updatedRow = new HashMap<>(row);  // 用于存储累加后的数据
                // 设置年份为"Year to Month"，将Total字段设为null
                updatedRow.put("Year", "Year to Month");
                updatedRow.put("Total", null);
                // 遍历1到12月
                for (int month = 1; month <= 12; month++) {
                    String monthKey = getMonthKey(month);  // 获取月份对应的字段名（如"January", "February"等）

                    if (row.containsKey(monthKey)) {
                        Object value = row.get(monthKey);  // 获取该月的值
                        // 如果该值是 BigDecimal 类型，进行累加
                        if (value instanceof BigDecimal) {
                            BigDecimal monthValue = (BigDecimal) value;
                            cumulativeSum = cumulativeSum.add(monthValue);  // 累加值
                            updatedRow.put(monthKey, cumulativeSum);
                            // 检查行名，分类存储数据
                            if ("No. of leads".equals(row.get("Row Name"))) {
                                noOfLeadsData.add(updatedRow);
                            } else if ("No. of qualified leads".equals(row.get("Row Name"))) {
                                qualifiedLeadsData.add(updatedRow);
                            } else if ("No. of converted leads".equals(row.get("Row Name"))) {
                                convertedLeadsData.add(updatedRow);
                            }
                            newData.add(updatedRow);  // 完成累加后再添加到newData列表// 更新累加值到该月
                        }
                    }
                }
            }


// 获取当前月份
            Calendar calendar2 = Calendar.getInstance();
            int currentMonth = calendar2.get(Calendar.MONTH) + 1;  // 获取当前月份（从0开始，需要加1）

// 对newData进行遍历，将当前月份之后的数据设为null
            for (Map<String, Object> data : newData) {
                for (int month = 1; month <= 12; month++) {
                    if (month >= currentMonth) {
                        // 根据月份名称将其设为null
                        String monthKey = getMonthKey(month);
                        data.put(monthKey, null);
                    }
                }
            }

// 将 newData 中不存在于 resultListMap 的数据添加进去
            for (Map<String, Object> data : newData) {
                if (!resultListMap.contains(data)) {
                    resultListMap.add(data);
                }
            }
            // 计算 "Qualified leads out of total leads" 比例
            List<Map<String, Object>> qualifiedRatioData = calculateRatio(QlOutOfTotalLStr, qualifiedLeadsData, noOfLeadsData);
            resultListMap.addAll(qualifiedRatioData);

// 计算 "Conversion rate" 比例
            List<Map<String, Object>> conversionRateData = calculateRatio(conversionRateStr, convertedLeadsData, qualifiedLeadsData);
            resultListMap.addAll(conversionRateData);


            // year to all

            List<Map<String, Object>> noOfLeadsList = new ArrayList<>();
            List<Map<String, Object>> noOfqualifiedList = new ArrayList<>();
            List<Map<String, Object>> noOfTBCList = new ArrayList<>();
            List<Map<String, Object>> noOfconvertedList = new ArrayList<>();


            // 遍历原始列表并筛选出 "Row Name" 为 "No. of leads" 的项
            for (Map<String, Object> entry : resultListMap) {
                if ("No. of leads".equals(entry.get("Row Name")) && !"Year to Month".equals(entry.get("Year"))) {
                    noOfLeadsList.add(entry);
                }else if ("No. of qualified leads".equals(entry.get("Row Name")) && !"Year to Month".equals(entry.get("Year"))){
                    noOfqualifiedList.add(entry);
                }else if ("No. of TBC leads".equals(entry.get("Row Name")) && !"Year to Month".equals(entry.get("Year"))){
                    noOfTBCList.add(entry);
                }else if ("No. of converted leads".equals(entry.get("Row Name")) && !"Year to Month".equals(entry.get("Year"))){
                    noOfconvertedList.add(entry);
                }
            }

            //计算前几年的total，获取最新一年数据
            BigDecimal cumulativeSum1 = BigDecimal.ZERO;
            BigDecimal cumulativeSum2 = BigDecimal.ZERO;
            BigDecimal cumulativeSum3 = BigDecimal.ZERO;
            BigDecimal cumulativeSum4 = BigDecimal.ZERO;
            Map<String,Object> temp1= new HashMap<>();
            Map<String,Object> temp2= new HashMap<>();
            Map<String,Object> temp3= new HashMap<>();
            Map<String,Object> temp4= new HashMap<>();
            for (Map<String, Object> filteredEntry : noOfLeadsList) {
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                if (String.valueOf(currentYear).equals(filteredEntry.get("Year"))) {
                    temp1 = new HashMap<>(filteredEntry);
                }else {
                    cumulativeSum1 = cumulativeSum1.add((BigDecimal) filteredEntry.get("Total"));
                }
            }
            System.out.println(temp1);
            for (Map<String, Object> filteredEntry : noOfqualifiedList) {

                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                if (String.valueOf(currentYear).equals(filteredEntry.get("Year"))) {
                    temp2 = new HashMap<>(filteredEntry);
                }else {
                    cumulativeSum2 = cumulativeSum2.add((BigDecimal) filteredEntry.get("Total"));
                }
            }
            for (Map<String, Object> filteredEntry : noOfTBCList) {

                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                if (String.valueOf(currentYear).equals(filteredEntry.get("Year"))) {
                    temp3 = new HashMap<>(filteredEntry);
                }else {
                    cumulativeSum3 = cumulativeSum3.add((BigDecimal) filteredEntry.get("Total"));
                }
            }
            for (Map<String, Object> filteredEntry : noOfconvertedList) {
//                System.out.println(filteredEntry);
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                if (String.valueOf(currentYear).equals(filteredEntry.get("Year"))) {
                    temp4 = new HashMap<>(filteredEntry);
                }else {
                    cumulativeSum4 = cumulativeSum4.add((BigDecimal) filteredEntry.get("Total"));
                }
            }

            BigDecimal previousMonthValue = BigDecimal.ZERO; // 初始化前一个月的值
            BigDecimal previousMonthValue2 = BigDecimal.ZERO; // 初始化前一个月的值
            BigDecimal previousMonthValue3 = BigDecimal.ZERO; // 初始化前一个月的值
            BigDecimal previousMonthValue4 = BigDecimal.ZERO; // 初始化前一个月的值

            for (int month = 1; month <= 12; month++) {
                String monthKey1 = getMonthKey(month);
                if (temp1.containsKey(monthKey1)) {
                    Object value1 = temp1.get(monthKey1); // 获取该月份的值
                    previousMonthValue = previousMonthValue.add((BigDecimal) value1);

                    //System.out.println(previousMonthValue);

                }
                BigDecimal totalValue = previousMonthValue.add(cumulativeSum1);
                temp1.put(monthKey1, totalValue);
            }

            for (int month = 1; month <= 12; month++) {
                String monthKey1 = getMonthKey(month);
                if (temp2.containsKey(monthKey1)) {
                    Object value1 = temp2.get(monthKey1); // 获取该月份的值
                    System.out.println(value1);
                    previousMonthValue2 = previousMonthValue2.add((BigDecimal) value1);

                }
                BigDecimal totalValue = previousMonthValue2.add(cumulativeSum2);
                temp2.put(monthKey1, totalValue);
            }
            for (int month = 1; month <= 12; month++) {
                String monthKey1 = getMonthKey(month);
                if (temp3.containsKey(monthKey1)) {
                    Object value1 = temp3.get(monthKey1); // 获取该月份的值
                    previousMonthValue3 = previousMonthValue3.add((BigDecimal) value1);

                    System.out.println(previousMonthValue3);

                }
                BigDecimal totalValue = previousMonthValue3.add(cumulativeSum3);
                temp3.put(monthKey1, totalValue);
            }
            for (int month = 1; month <= 12; month++) {
                String monthKey1 = getMonthKey(month);
                if (temp4.containsKey(monthKey1)) {
                    Object value1 = temp4.get(monthKey1); // 获取该月份的值
                    previousMonthValue4 = previousMonthValue4.add((BigDecimal) value1);

                    System.out.println(previousMonthValue4);

                }
                BigDecimal totalValue = previousMonthValue4.add(cumulativeSum4);
                temp4.put(monthKey1, totalValue);
            }
            // 添加比例计算
            Map<String, Object> qualifiedRatio = new HashMap<>();
            qualifiedRatio.put("Year", "All to Month");
            qualifiedRatio.put("Row Name", "Qualified leads out of total leads");
            qualifiedRatio.put("Total", null);

// 创建 DecimalFormat 实例，保留两位小数
            DecimalFormat df = new DecimalFormat("#.00");

            for (int month = 1; month <= 12; month++) {
                String monthKey = getMonthKey(month);
                BigDecimal totalLeads = (BigDecimal) temp1.getOrDefault(monthKey, BigDecimal.ZERO);
                BigDecimal qualifiedLeads = (BigDecimal) temp2.getOrDefault(monthKey, BigDecimal.ZERO);

                if (totalLeads.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal ratio = qualifiedLeads.divide(totalLeads, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                    // 使用 DecimalFormat 格式化比例，并添加百分号
                    qualifiedRatio.put(monthKey, df.format(ratio) + "%");
                } else {
                    qualifiedRatio.put(monthKey, null);
                }
            }


// 添加转化率计算
            // 计算转化率比例
            Map<String, Object> conversionRate = new HashMap<>();
            conversionRate.put("Year", "All to Month");
            conversionRate.put("Row Name", "Conversion rate");
            conversionRate.put("Total", null);
            for (int month = 1; month <= 12; month++) {
                String monthKey = getMonthKey(month);
                BigDecimal qualifiedLeads = (BigDecimal) temp2.getOrDefault(monthKey, BigDecimal.ZERO);
                BigDecimal convertedLeads = (BigDecimal) temp4.getOrDefault(monthKey, BigDecimal.ZERO);

                if (qualifiedLeads.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal rate = convertedLeads.divide(qualifiedLeads, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                    // 使用 DecimalFormat 格式化 rate 保留两位小数
                    DecimalFormat df1 = new DecimalFormat("#.00");
                    conversionRate.put(monthKey, df1.format(rate) + "%");
                } else {
                    conversionRate.put(monthKey, null);
                }
            }



            LocalDate currentDate = LocalDate.now();
            String currentMonthKey = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

            temp1.put("Year", "All to Month");
            temp2.put("Year", "All to Month");
            temp3.put("Year", "All to Month");
            temp4.put("Year", "All to Month");
            temp1.put("Total",null);
            temp2.put("Total",null);
            temp3.put("Total",null);
            temp4.put("Total",null);

            // 获取当前月份（1-12）
            Calendar calendar = Calendar.getInstance();
            //int currentMonth = calendar.get(Calendar.MONTH) ;

            for (int month = 1; month <= 12; month++) {
                if (month >= currentMonth) {
                    String monthKey = getMonthKey(month);
                    qualifiedRatio.put(monthKey, null);
                    conversionRate.put(monthKey, null);
                }
            }

            // 将当前月份之后的数据设为null
            for (int month = 1; month <= 12; month++) {
                if (month >= currentMonth) {
                    // 根据月份名称将其设为null
                    switch (month) {
                        case 1: temp1.put("January", null); break;
                        case 2: temp1.put("February", null); break;
                        case 3: temp1.put("March", null); break;
                        case 4: temp1.put("April", null); break;
                        case 5: temp1.put("May", null); break;
                        case 6: temp1.put("June", null); break;
                        case 7: temp1.put("July", null); break;
                        case 8: temp1.put("August", null); break;
                        case 9: temp1.put("September", null); break;
                        case 10: temp1.put("October", null); break;
                        case 11: temp1.put("November", null); break;
                        case 12: temp1.put("December", null); break;
                    };
                    switch (month) {
                        case 1: temp2.put("January", null); break;
                        case 2: temp2.put("February", null); break;
                        case 3: temp2.put("March", null); break;
                        case 4: temp2.put("April", null); break;
                        case 5: temp2.put("May", null); break;
                        case 6: temp2.put("June", null); break;
                        case 7: temp2.put("July", null); break;
                        case 8: temp2.put("August", null); break;
                        case 9: temp2.put("September", null); break;
                        case 10: temp2.put("October", null); break;
                        case 11: temp2.put("November", null); break;
                        case 12: temp2.put("December", null); break;
                    }
                    switch (month) {
                        case 1: temp3.put("January", null); break;
                        case 2: temp3.put("February", null); break;
                        case 3: temp3.put("March", null); break;
                        case 4: temp3.put("April", null); break;
                        case 5: temp3.put("May", null); break;
                        case 6: temp3.put("June", null); break;
                        case 7: temp3.put("July", null); break;
                        case 8: temp3.put("August", null); break;
                        case 9: temp3.put("September", null); break;
                        case 10: temp3.put("October", null); break;
                        case 11: temp3.put("November", null); break;
                        case 12: temp3.put("December", null); break;
                    }
                    switch (month) {
                        case 1: temp4.put("January", null); break;
                        case 2: temp4.put("February", null); break;
                        case 3: temp4.put("March", null); break;
                        case 4: temp4.put("April", null); break;
                        case 5: temp4.put("May", null); break;
                        case 6: temp4.put("June", null); break;
                        case 7: temp4.put("July", null); break;
                        case 8: temp4.put("August", null); break;
                        case 9: temp4.put("September", null); break;
                        case 10: temp4.put("October", null); break;
                        case 11: temp4.put("November", null); break;
                        case 12: temp4.put("December", null); break;
                    }
                }
            }




            resultListMap.add(temp1);
            resultListMap.add(temp2);
            resultListMap.add(temp3);
            resultListMap.add(temp4);
            resultListMap.add(qualifiedRatio);  // 添加 qualified leads out of total leads 的比例行
            resultListMap.add(conversionRate);  // 添加 conversion rate 的比例行
            System.out.println(resultListMap);


            //check if there is more then one year:
            Set<Object> distinctYears = new HashSet<>();
            resultListMap.forEach(r -> distinctYears.add(r.get("Year")));
            //if yes: add historical data (Total of the years)
            if ((long) distinctYears.size() > 1) {
                tempClientLead = new ClientLeads(clientLeads);
                List<Map<String, Object>> historicalNumOfLeads = clientLeadsMapper.getHistoricalMonthlyReport(clientLeads, noOfLeadsStr, inquiryReceivedDateColumn);
                tempClientLead.setQualifiedLeads(yesQualifiedLeads);
                List<Map<String, Object>> historicalNumOfQualifiedLeads = clientLeadsMapper.getHistoricalMonthlyReport(tempClientLead, noOfQLeadsStr, inquiryReceivedDateColumn);
                resultListMap.addAll(historicalNumOfLeads);
                resultListMap.addAll(historicalNumOfQualifiedLeads);
                resultListMap.addAll(calculateRatio(QlOutOfTotalLStr, historicalNumOfQualifiedLeads, historicalNumOfLeads));
                tempClientLead.setQualifiedLeads(TBCLeads);
                List<Map<String, Object>> historicalNumOfTBCLeads = clientLeadsMapper.getHistoricalMonthlyReport(tempClientLead, TBCLeadsStr, inquiryReceivedDateColumn);
                resultListMap.addAll(historicalNumOfTBCLeads);
                tempClientLead.setQualifiedLeads(clientLeads.getQualifiedLeads());
                tempClientLead.setLeadStatus(ClientLeadsStatus.Converted.getStrValue());
                List<Map<String, Object>> historicalNumOfConvertedLeads = clientLeadsMapper.getHistoricalMonthlyReport(tempClientLead, rowName, convertedDateColumn);
                resultListMap.addAll(historicalNumOfConvertedLeads);
                resultListMap.addAll(calculateRatio(conversionRateStr, historicalNumOfConvertedLeads, historicalNumOfQualifiedLeads));
            }
        }
        //sort resultListMap
        resultListMap.removeIf(o -> "All".equals(String.valueOf(o.get("Year"))));
        resultListMap.sort(Comparator.comparingInt(
                (Map<String, Object> o) -> {
                    String yearValue = String.valueOf(o.get("Year"));

                    // 定义排序规则: "ALL" 最小， "Year to Month" 其次
                    if ("All to Month".equals(yearValue)) return 0; // "ALL" 排在最前面
                    if ("Year to Month".equals(yearValue)) return 1; // "Year to Month" 排在第二
                    // 其他年份按字符串返回
                    return 2; // 其余年份返回较大的值，以确保它们在后面
                }
        ).thenComparing(o -> String.valueOf(o.get("Year")), Comparator.reverseOrder()));

        return new ArrayList<>(resultListMap);

    }
    private static String getMonthKey(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return null;
        }
    }

    private List<Map<String, Object>> calculateRatio(String rowName, List<Map<String, Object>> numeratorList, List<Map<String, Object>> denominatorList) {
        // 计算比例 = converted leads / qualified leads
        denominatorList.removeIf(map -> map.get("Year") == null); // 移除年份为空的情况
        List<Map<String, Object>> resultList = new ArrayList<>();
        DecimalFormat doubleFormatter = new DecimalFormat("#.00");

        // 遍历numeratorList, 确保每个年份只处理一次
        Set<String> processedYears = new HashSet<>();

        for (Map<String, Object> numeratorMap : numeratorList) {
            Map<String, Object> ratioMap = new HashMap<>();
            Map<String, Object> denominatorMapByYear;

            // 获取年份，并跳过已经处理过的年份，避免重复处理
            String year = (String) numeratorMap.get("Year");
            if (year == null || processedYears.contains(year)) {
                continue;
            }

            // 标记年份为已处理
            processedYears.add(year);

            // 获取对应年份的分母数据
            try {
                denominatorMapByYear = denominatorList.stream()
                        .filter(l -> l.get("Year").equals(numeratorMap.get("Year")))
                        .findFirst()
                        .orElse(null);
            } catch (Exception ex) {
                continue;
            }

            if (denominatorMapByYear == null) {
                continue;
            }

            // 处理每个月的数据
            for (String key : numeratorMap.keySet()) {
                if (key.equals("Row Name")) {
                    ratioMap.put(key, rowName);
                    continue;
                }
                if (key.equals("Year")) {
                    ratioMap.put(key, numeratorMap.get(key));
                    continue;
                }

                // 检查当前月份的值是否为null
                Object numeratorValue = numeratorMap.get(key);
                Object denominatorValue = denominatorMapByYear.get(key);

                if (numeratorValue == null || denominatorValue == null) {
                    ratioMap.put(key, null); // 如果有任何一个为null，设置为null
                    continue;
                }

                // 将值转换为 double 进行计算
                double n1 = Double.parseDouble(numeratorValue.toString());
                double n2 = Double.parseDouble(denominatorValue.toString());
                double ratio;

                if (n2 == 0) {
                    ratioMap.put(key, null); // 避免除以0
                } else {
                    ratio = n1 / n2;
                    ratioMap.put(key, doubleFormatter.format(ratio * 100) + "%"); // 计算比例并格式化
                }
            }
            resultList.add(ratioMap);  // 每次只处理一个年份，生成一行数据
        }
        return resultList;
    }



    private ClientLeads mapClientLeads(ClientLeads clientLead) {
        Map<String, Object> params = clientLead.getParams();
        String inquiryReceivedDate = params.get("inquiryReceivedDate") != null ? params.get("inquiryReceivedDate").toString() : "";
        if (StringUtils.isNotEmpty(inquiryReceivedDate)) {
            String[] durationArray = inquiryReceivedDate.split("~");
            String beginTime = durationArray[0].trim();
            String endTime = durationArray[1].trim() + " 23:59:59";
            params.put("entryDateBegin", beginTime);
            params.put("entryDateEnd", endTime);
        }

        String convertedDate = params.get("convertedDate") != null ? params.get("convertedDate").toString() : "";
        if (StringUtils.isNotEmpty(convertedDate)) {
            String[] convertedDates = convertedDate.split("~");
            params.put("convertedDateBegin", convertedDates[0].trim());
            params.put("convertedDateEnd", convertedDates[1].trim() + " 23:59:59");
        }
        String contractSignedDate = params.get("contractSignedDate") != null ? params.get("contractSignedDate").toString() : "";
        if (StringUtils.isNotEmpty(contractSignedDate)) {
            String[] contractSignedDates = contractSignedDate.split("~");
            params.put("contractSignedDateBegin", contractSignedDates[0].trim());
            params.put("contractSignedDateEnd", contractSignedDates[1].trim() + " 23:59:59");
        }
        String nextFollowupTime = params.get("nextFollowupTime") != null ? params.get("nextFollowupTime").toString() : "";
        if (StringUtils.isNotEmpty(nextFollowupTime)) {
            String[] nextFollowupTimes = nextFollowupTime.split("~");
            params.put("nextFollowupTimeBegin", nextFollowupTimes[0].trim());
            params.put("nextFollowupTimeEnd", nextFollowupTimes[1].trim() + " 23:59:59");
        }
        clientLead.setParams(params);

        return clientLead;
    }


    /**
     * select exact lead without LIKE statement
     *
     * @param clientLead
     * @return
     */
    @Override
    public List<ClientLeads> selectExactLeadsList(ClientLeads clientLead) {
        return clientLeadsMapper.selectExactLeadsList(clientLead);
    }


    @Override
    public List<ClientLeads> nnroadTopLocations(ClientLeads clientLeads,List<String> memberCode) {
        Map<String, Object> params = clientLeads.getParams();
        String engageDate = params.get("inquiryReceivedDate") != null ? params.get("inquiryReceivedDate").toString() : "";
        if (!StringUtils.isEmpty(engageDate)) {
            String[] durationArray = engageDate.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("entryDateBegin", beginTime.trim());
            params.put("entryDateEnd", endTime.trim());
            clientLeads.setEntryDateBegin(beginTime);
            clientLeads.setEntryDateEnd(endTime);
        }
        clientLeads.setParams(params);

        return clientLeadsMapper.nnroadTopLocations(clientLeads,memberCode);
    }

    @Override
    public List<Integer> getLeadsData(List<String> memberCode, String leadType, LocalDateTime currentDate, String bdConsultant) {
        List<Integer> leadsData = new ArrayList<>();
        String yesId = clientLeadsConfigService.selectClientLeadsConfigList("qualified_leads", "Yes", "0").get(0).getId().toString();

        for (int i = 0; i < 12; i++) {
            LocalDateTime targetDateTime = currentDate.minusMonths(i);
            // 获取该月的最后一天的时间
            LocalDateTime endDate = targetDateTime.withDayOfMonth(targetDateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
            if ("qualified".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getQualifiedLeads(memberCode, endDate, yesId, bdConsultant));
            } else if ("converted".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getConvertedLeads(memberCode, endDate, bdConsultant));
            } else if ("convertedEOR".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getConvertedLeadsByServiceType(memberCode, endDate, "1", bdConsultant));
            } else if ("convertedPayroll".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getConvertedLeadsByServiceType(memberCode, endDate, "2", bdConsultant));
            } else if ("convertedTax".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getConvertedLeadsByServiceType(memberCode, endDate, "7", bdConsultant));
            } else if ("convertedRecruit".equalsIgnoreCase(leadType)) {
                leadsData.add(clientLeadsMapper.getConvertedLeadsByServiceType(memberCode, endDate, "3", bdConsultant));
            }
        }
        Collections.reverse(leadsData);
        return leadsData;
    }

    @Override
    public int getLeadsBetweenDates(LocalDateTime start, LocalDateTime end, String type,List<String> memberCode) {
        return clientLeadsMapper.getLeadsBetweenDates(start,end,type,memberCode);
    }

    @Override
    public List<String> getBDNames() {
        return clientLeadsMapper.getBDNames();
    }

    @Override
    public LocalDateTime getLatestMonthForBD(String bdConsultant) {
        return clientLeadsMapper.getLatestMonthForBD(bdConsultant);
    }

    @Override
    public String selectBD() {
        return clientLeadsMapper.selectBD();
    }

    @Override
    public String selectBDTitle() {
        return clientLeadsMapper.selectBDTitle();
    }

    @Override
    public boolean checkCode(ClientLeads client, String prefix) {
        boolean ret = true;

        int cnt = countClientBranches(client, prefix);
        if (cnt > 0) {
            ret = false;
        }

        return ret;
    }

    @Override
    public Integer countClientBranches(ClientLeads client, String prefix) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyPrefix", prefix);
        client.setParams(params);

        return clientLeadsMapper.checkMaxCodeClient(client);
    }


    @Override
    public String resetAndGetCode(String prefix) {
        String maxCode = clientLeadsMapper.getMaxCodeClient(prefix);

        String tempCode = prefix + "-" + maxCode.replace(prefix, "");

        return sequence.resetAndGetCode(tempCode, "-");
    }



}


