package com.nnroad.timeline.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.nnroad.client.domain.SysClient;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.DataSourceType;

import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.system.domain.SysTableUpdate;
import com.nnroad.system.mapper.SysTableUpdateMapper;
import com.nnroad.utils.ExtraAttributeUtils;
import com.nnroad.utils.ExcelImporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nnroad.extraAttribute.mapper.SysExtraAttributeMapper;
import com.nnroad.client.mapper.SysClientMapper;

import com.nnroad.timeline.mapper.SysTimelineMapper;
import com.nnroad.timeline.domain.SysTimeline;
import com.nnroad.timeline.service.ISysTimelineService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nnroad.common.utils.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Integer.parseInt;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author Haoming
 * @date 2024-10-23
 */
@Service
public class SysTimelineServiceImpl implements ISysTimelineService 
{
    @Autowired
    private SysTimelineMapper sysTimelineMapper;
    
    @Autowired
    private SysExtraAttributeMapper sysExtraAttributeMapper;

    @Autowired
    private SysClientMapper sysClientMapper;


    @Autowired
    private SysTableUpdateMapper sysTableUpdateMapper;

    @Autowired
    private ExtraAttributeUtils extraAttributeUtils;

    @Autowired
    private ExcelImporter excelImporter;

    @Value("${sys.company-name}")
    private String companyName;

    // Create a mapping of db names to DataSourceType values
    private static final Map<String, DataSourceType> DB_TO_DATASOURCE_MAP = new HashMap<>();
    static {
        DB_TO_DATASOURCE_MAP.put("HRSH", DataSourceType.HROneSH);
        DB_TO_DATASOURCE_MAP.put("HRBJ", DataSourceType.HROneBJ);
        DB_TO_DATASOURCE_MAP.put("HRHK", DataSourceType.HROneHK);
        DB_TO_DATASOURCE_MAP.put("FDI", DataSourceType.FDI);
        DB_TO_DATASOURCE_MAP.put("Top FDI", DataSourceType.TOPFDIHK);
    }

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysTimeline selectSysTimelineById(Long id)
    {
        return sysTimelineMapper.selectSysTimelineById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysTimeline> selectSysTimelineList(SysTimeline sysTimeline)
    {
        return sysTimelineMapper.selectSysTimelineList(sysTimeline);
    }

    @Override
    public List<SysTableUpdate> selectSysTimelinePendingChanges()
    {
        SysTableUpdate updateParam = new SysTableUpdate();
        updateParam.setStatus("Pending");
        updateParam.setSourceTable("sys_timeline");
        List<SysTableUpdate> res = sysTableUpdateMapper.selectSysTableUpdateList(updateParam);

        return res;
    }

    @Override
    public AjaxResult acceptSysTimelinePendingChanges()
    {
        SysTableUpdate updateParam = new SysTableUpdate();
        updateParam.setStatus("pending");
        updateParam.setSourceTable("sys_timeline");
        List<SysTableUpdate> timelinePendingChangeList = sysTableUpdateMapper.selectSysTableUpdateList(updateParam);

        List<String> errors = timelinePendingChangeList.parallelStream()
                .map(update -> {
                    try {
                        String source = update.getSource();
                        System.out.println(update.getEntryUuid());

                        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(source);
                        DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                        SysTimeline timelineParam = new SysTimeline();
                        timelineParam.setUuid(update.getEntryUuid());
                        List<SysTimeline> timelineList = sysTimelineMapper.selectSysTimelineList(timelineParam);
                        DynamicDataSourceContextHolder.clearDataSourceType();

                        if (timelineList.isEmpty()) {
                            return "Sync failed: linked timeline not found for UUID " + update.getEntryUuid();
                        }

                        SysTimeline externalTimeline = timelineList.get(0);
                        System.out.println(externalTimeline.getVendorCompanyCode());

                        if (externalTimeline.getVendorCompanyCode() == null) {
                            updateMainSysTimeline(externalTimeline);
                        } else {
                            updateVendorSysTimeline(externalTimeline);
                        }

                        update.setStatus("confirmed");
                        sysTableUpdateMapper.updateSysTableUpdate(update);
                        return null; // No error
                    } catch (Exception e) {
                        return "Error processing UUID " + update.getEntryUuid() + ": " + e.getMessage();
                    }
                })
                .filter(Objects::nonNull) // Filter out null (successful cases)
                .collect(Collectors.toList());

        return errors.isEmpty() ? AjaxResult.success("Sync success") : AjaxResult.error(String.join("\n", errors));
    }



    /**
     * 新增
     * 读取client info里active的客户；2.读客户的EE info中的员工合同签署方，
     * 如果与service party B一致则不需要操作，如果不一致则需要在对应的系统里新增该数据
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    @Override
    public AjaxResult insertSysTimeline(SysTimeline sysTimeline) {
        System.out.println("Inserting timeline");

        List<String> clientCompanyCodeList = sysTimeline.getClientCompanyCodeArray();

        String message = clientCompanyCodeList.parallelStream()
                .map(clientCompanyCode -> processInsertSysTimeline(clientCompanyCode, sysTimeline))
                .filter(msg -> !msg.isEmpty())
                .collect(Collectors.joining("\n"));

        return message.isEmpty() ? AjaxResult.success("Add Success") : AjaxResult.warn(message);
    }

    private String processInsertSysTimeline(String clientCompanyCode, SysTimeline baseTimeline) {
        StringBuilder message = new StringBuilder();
        try{
            SysTimeline sysTimeline = new SysTimeline();
            sysTimeline.setYear(baseTimeline.getYear());
            sysTimeline.setMonth(baseTimeline.getMonth());
            sysTimeline.setExtraData(baseTimeline.getExtraData());

            SysClient sysClientParam = new SysClient();
            sysClientParam.setCompanyCode(clientCompanyCode);
            SysClient client = sysClientMapper.selectClient(sysClientParam);

            String servicePartyB = sysTimelineMapper.getServiceTypeBByClientCompanyCode(clientCompanyCode);
            List<String> EmployeeVendors = sysTimelineMapper.getEmployeeEmploymentContractsByClientCompanyCode(clientCompanyCode);

            Map<String, String> mapping = new HashMap<>();
            mapping.put("HROne HK", "HRHK");
            mapping.put("HROne BJ", "HRBJ");
            mapping.put("HROne SH", "HRSH");
            mapping.put("TOP FDI", "Top FDI");
            mapping.put("Client", "Client");

            //Apply mapping to  servicepartyB and employee vendor list
            List<String> convertedEmployeeVendors = EmployeeVendors.stream()
                    .map(s -> mapping.getOrDefault(s, s))
                    .collect(Collectors.toList());
            servicePartyB = mapping.getOrDefault(servicePartyB, servicePartyB);


            if (Boolean.TRUE.equals(baseTimeline.getParams().get("noDuplicate"))) {
                SysTimeline timelineParam = new SysTimeline();
                timelineParam.setYear(sysTimeline.getYear());
                timelineParam.setMonth(sysTimeline.getMonth());
                timelineParam.setClientCompanyCode(clientCompanyCode);
                timelineParam.setOrganizationName(client.getOrg());
                List<String> existingEmployeeVendor = sysTimelineMapper.getExistingVendorsFromTimeline(timelineParam);
                convertedEmployeeVendors.removeIf(existingEmployeeVendor::contains);
            }

            if (convertedEmployeeVendors.isEmpty()) {
                return "";
            }

            sysTimeline.setOrganizationName(client.getOrg());
            sysTimeline.setClientCompanyCode(clientCompanyCode);

            Set<String> checkVendorSet = new HashSet<>(Arrays.asList("HRSH", "HRBJ", "FDI", "Top FDI"));
            for (String vendor : convertedEmployeeVendors) {
                if (vendor.equals("null") || vendor.isEmpty()) {
                    continue; // Skip processing if vendor is "null"
                }

                if (vendor.equals("Client")) {
                    sysTimelineMapper.insertSysTimeline(sysTimeline);
                    continue;
                }

                // If servicePartyB matches the vendor, insert directly
                if (checkVendorSet.contains(vendor)) {
                    if (servicePartyB.equals(vendor)) {
                        sysTimelineMapper.insertSysTimeline(sysTimeline);
                    } else {
                        insertMainAndVendorSysTimeline(vendor, sysTimeline);
                    }
                } else {
                    sysTimeline.setVendorCompanyCode(vendor);
                    sysTimelineMapper.insertSysTimeline(sysTimeline);
                }

                sysTimeline.setVendorCompanyCode(null); // Reset after processing
            }
            return "";
        }catch(Exception e){
            return message.append("e").toString();
        }
    }
//    public AjaxResult insertSysTimeline(SysTimeline sysTimeline)
//    {
//
//        System.out.println("inserting timeline \n");
//
//        System.out.println("retrieving clientInfoList");
//        String message = "";
//
//        List<String> clientCompanyCodeList = sysTimeline.getClientCompanyCodeArray();
//        for(String clientCompanyCode: clientCompanyCodeList) {
//
//            SysClient sysClientParam = new SysClient();
//            sysClientParam.setCompanyCode(clientCompanyCode);
//            SysClient client = sysClientMapper.selectClient(sysClientParam);
//
//            String servicePartyB = sysTimelineMapper.getServiceTypeBByClientCompanyCode(clientCompanyCode);
//
//            //retrieving employee contract type from every employee and storing into a unique set
//            //System.out.println(clientCompanyCode + "'s ServicePartyB: " + servicePartyB);
//            List<String> EmployeeVendors = sysTimelineMapper.getEmployeeEmploymentContractsByClientCompanyCode(clientCompanyCode);
//
//            Map<String, String> mapping = new HashMap<>();
//            mapping.put("HROne HK", "HRHK");
//            mapping.put("HROne BJ", "HRBJ");
//            mapping.put("HROne SH", "HRSH");
//            mapping.put("Client", "Client");
//            List<String> ConvertedEmployeeVendors = EmployeeVendors.stream()
//                    .map(s -> mapping.getOrDefault(s, s))
//                    .collect(Collectors.toList());
//
//            Map<String, Object> params = sysTimeline.getParams();
//
//            //check if client vs vendor timeline exists in database
//            if(Boolean.TRUE.equals(params.get("noDuplicate"))){
//                //get existing client vendors from timeline given year, month, and client company code
//                SysTimeline timelineParam = new SysTimeline();
//                timelineParam.setYear(sysTimeline.getYear());
//                timelineParam.setMonth(sysTimeline.getMonth());
//                timelineParam.setClientCompanyCode(clientCompanyCode);
//                timelineParam.setOrganizationName(client.getOrg());
//                List<String> existingEmployeeVendor = sysTimelineMapper.getExistingVendorsFromTimeline(timelineParam);
//
//                //filter employeeVendors
//                ConvertedEmployeeVendors.removeIf(existingEmployeeVendor::contains);
//            }
//
//
//            String mainOrg = client.getOrg();
//            sysTimeline.setOrganizationName(mainOrg);
//            sysTimeline.setClientCompanyCode(clientCompanyCode);
//            if(ConvertedEmployeeVendors.size() == 0){
//                System.out.println("no insert");
//                continue;
//            }
//            message += "new " + client.getClientName() + "timeline inserted" + "\n";
//            for(String vendor : ConvertedEmployeeVendors){
//                System.out.println("inserting");
//                switch(vendor){
//                    case "Client"://供应商是自己公司
//                        sysTimeline.setVendorCompanyCode(null);
//                        sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        break;
//                    case "HRHK"://供应商是其他自己公司
//                    case "HROne HK":
//                        if(servicePartyB.equals("HRHK") || servicePartyB.equals("HROne HK"))
//                            sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        else
//                            insertMainAndVendorSysTimeline("HRHK", sysTimeline);
//                        break;
//                    case "HRSH":
//                    case "HROne SH":
//                        if(servicePartyB.equals("HRSH") || servicePartyB.equals("HROne SH"))
//                            sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        else
//                            insertMainAndVendorSysTimeline("HRSH", sysTimeline);
//                        break;
//                    case "HRBJ":
//                    case "HROne BJ":
//                        if(servicePartyB.equals("HRBJ") || servicePartyB.equals("HROne BJ"))
//                            sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        else
//                            insertMainAndVendorSysTimeline("HRBJ", sysTimeline);
//                        break;
//                    case "FDI":
//                        if(servicePartyB.equals("FDI"))
//                            sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        else
//                            insertMainAndVendorSysTimeline("FDI", sysTimeline);
//                        break;
//
//                    case "Top FDI":
//                        if(servicePartyB.equals("Top FDI"))
//                            sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        else
//                            insertMainAndVendorSysTimeline("Top FDI", sysTimeline);
//                        break;
//
//                    case "null":
//                        break;
//
//                    default://异地供应商
//                        sysTimeline.setVendorCompanyCode(vendor);
//                        sysTimelineMapper.insertSysTimeline(sysTimeline);
//                        sysTimeline.setVendorCompanyCode(null);
//                        break;
//                }
//
//            }
//        }
//        return message.isEmpty() ? AjaxResult.success("Client timeline found, no insert") : AjaxResult.success(message);
//    }

    private void insertMainAndVendorSysTimeline(String vendorOrg, SysTimeline sysTimeline){
        String generatedUuid = UUID.randomUUID().toString();
        sysTimeline.setUuid(generatedUuid);
        sysTimeline.setVendorCompanyCode(vendorOrg);
        System.out.println("inserting main timeline");
        sysTimelineMapper.insertSysTimeline(sysTimeline);

        SysTimeline vendorTimeline = new SysTimeline();
        vendorTimeline.setYear(sysTimeline.getYear());
        vendorTimeline.setMonth(sysTimeline.getMonth());
        vendorTimeline.setExtraData(sysTimeline.getExtraData());
        vendorTimeline.setClientCompanyCode(sysTimeline.getClientCompanyCode());
        vendorTimeline.setOrganizationName(vendorOrg);
        vendorTimeline.setUuid(generatedUuid);
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(vendorOrg);
        System.out.println("inserting vendor timeline: " + vendorOrg);
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
        sysTimelineMapper.insertSysTimeline(vendorTimeline);
        DynamicDataSourceContextHolder.clearDataSourceType();

        sysTimeline.setUuid(null);
        sysTimeline.setVendorCompanyCode(null);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param sysTimelines 【请填写功能名称】
     * @return 结果
     */
    @Override
    public AjaxResult updateMultipleSysTimelineByColumn(List<SysTimeline> sysTimelines)
    {
        boolean allAjaxResultSuccess = true;
        sysTimelineMapper.batchUpdateSysTimelineByColumn(sysTimelines);
        List<SysTimeline> updatedTimelineList = sysTimelineMapper.selectSysTimelineByIds(sysTimelines.stream()
                .map(SysTimeline::getId)
                .collect(Collectors.toList()));

//        for(SysTimeline sysTimeline: updatedTimelineList) {
//
//                AjaxResult result = updateLinkedTimeline2(sysTimeline);
//                if (!result.isSuccess()) {  // If any update fails, flag failure
//                    allAjaxResultSuccess = false;
//                }
//        }
//
//        if (allAjaxResultSuccess) {
//            return AjaxResult.success("All timelines updated successfully.");
//        } else {
//            return AjaxResult.error("One or more updates failed.");
//        }
        // Process updates in parallel
        allAjaxResultSuccess = updatedTimelineList.parallelStream()
                .map(this::updateLinkedTimeline2)   // Call updateLinkedTimeline2 in parallel
                .allMatch(AjaxResult::isSuccess);   // Check if all updates are successful

        return allAjaxResultSuccess
                ? AjaxResult.success("All timelines updated successfully.")
                : AjaxResult.error("One or more updates failed.");

    }

    @Override
    public AjaxResult updateSysTimelineByColumn(SysTimeline sysTimeline)
    {
        if (extraAttributeUtils.validateData(sysTimeline.getExtraData(), "sys_timeline")) {

            sysTimelineMapper.updateSysTimelineByColumn(sysTimeline);
            SysTimeline curTimeline = sysTimelineMapper.selectSysTimelineById(sysTimeline.getId());

            return updateLinkedTimeline2(curTimeline);
        }
        else {
            DynamicDataSourceContextHolder.clearDataSourceType();
            System.err.println("Invalid input data for extra attributes.");
            return AjaxResult.error("Invalid input data for extra attributes.");
        }
    }



    /**
     * 修改【请填写功能名称】
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    @Override
    public AjaxResult updateSysTimeline(SysTimeline sysTimeline)
    {
        if (extraAttributeUtils.validateData(sysTimeline.getExtraData(), "sys_timeline")) {

            //computeDelayDays(sysTimeline);
            sysTimelineMapper.updateSysTimeline(sysTimeline);
            return updateLinkedTimeline2(sysTimeline);
        }
        else {
            DynamicDataSourceContextHolder.clearDataSourceType();
            System.err.println("Invalid input data for extra attributes.");
            return AjaxResult.error("Invalid input data for extra attributes.");
        }
    }

    @Override
    public int updateSysExternalVendorTimeline(SysTimeline sysTimeline) {
        return sysTimelineMapper.updateSysTimelineByColumn(sysTimeline);
    }

    private AjaxResult updateLinkedTimeline2(SysTimeline sysTimeline){
        if(sysTimeline.getUuid() == null){
            return AjaxResult.success("update success.");
        }

        SysClient clientParam =  new SysClient();
        String companyCode = sysTimeline.getClientCompanyCode();
        clientParam.setCompanyCode(companyCode);
        List<SysClient> clientList = sysClientMapper.selectSysClientList(clientParam);
        if(clientList.isEmpty())
            return AjaxResult.success("update error: client not found in database.");
        SysClient client = clientList.get(0);
        String clientOrg = client.getOrg();

        String linkedTimelineOrg = null;
        linkedTimelineOrg = clientOrg.equals(sysTimeline.getOrganizationName())//check if timeline client is from current org
                ? sysTimeline.getVendorCompanyCode()
                : clientOrg;


        //run this when origin and target are HRSH and HRBJ (same database, update automatically)
        if((linkedTimelineOrg.equals("HRSH") && sysTimeline.getOrganizationName().equals("HRBJ"))|| linkedTimelineOrg.equals("HRBJ") && sysTimeline.getOrganizationName().equals("HRSH")){
            String targetOrg = linkedTimelineOrg;
            String originOrg = sysTimeline.getOrganizationName();

            if(clientOrg.equals(originOrg)){//timeline is main timeline
                updateVendorSysTimeline(sysTimeline);
            }
            else{//timeline is vendor timeline
                updateMainSysTimeline(sysTimeline);
            }
            return AjaxResult.success("update success");
        }

        SysTableUpdate update = new SysTableUpdate();
        update.setEntryUuid(sysTimeline.getUuid());
        update.setSource(sysTimeline.getOrganizationName());
        update.setStatus("pending");
        update.setSourceTable("sys_timeline");

        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(linkedTimelineOrg);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
            List<SysTableUpdate> res = sysTableUpdateMapper.selectSysTableUpdateList(update);
            if (res.isEmpty())
                sysTableUpdateMapper.insertSysTableUpdate(update);
        }
        DynamicDataSourceContextHolder.clearDataSourceType();
        return AjaxResult.success("update success.");
    }


//    private AjaxResult updateLinkedTimeline(SysTimeline sysTimeline){
//        SysClient clientParam =  new SysClient();
//        if(sysTimeline.getClientCompanyCode()==null)
//            return AjaxResult.error("no client company code found");
//        String companyCode = sysTimeline.getClientCompanyCode();
//
//        clientParam.setCompanyCode(companyCode);
//        List<SysClient> clientList = sysClientMapper.selectSysClientList(clientParam);
//        if(clientList.isEmpty())
//            return AjaxResult.error("timeline client is not found");
//
//        SysClient client = clientList.get(0);
//        String clientOrg = client.getOrg();
//        if(!clientOrg.equals(sysTimeline.getOrganizationName()))//client is not from this company
//        {
//            DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(clientOrg);
//            if (dataSourceType != null) {
//                DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
//                updateMainSysTimeline(sysTimeline);
//            } else {
//                System.out.println("clientOrg is not found, no main timeline update.");
//            }
//        }
//        else {//client is from this company
//            String vendorCompanyCode = sysTimeline.getVendorCompanyCode();
//            DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(vendorCompanyCode);
//
//            //update PN Ready Planned date only for main timeline
//            //computePNReadyDatePlanned(sysTimeline);
//
//            if(vendorCompanyCode == null || vendorCompanyCode.equals(sysTimeline.getOrganizationName()) || dataSourceType == null)
//                System.out.println("no vendor timeline is found");
//            else{
//                DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
//                updateVendorSysTimeline(sysTimeline);
//            }
//        }
//
//        DynamicDataSourceContextHolder.clearDataSourceType();
//        return AjaxResult.success("timeline updated.");
//    }

    private int updateMainSysTimeline(SysTimeline vendorSysTimeLine) {
        //find mainTimeline
        String uuid = vendorSysTimeLine.getUuid();
        SysTimeline param = new SysTimeline();
        param.setUuid(uuid);
//        param.setVendorCompanyCode("NOT_NULL");
        SysTimeline mainSysTimeline = sysTimelineMapper.selectSysTimelineByUuid(param);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainExtraData = objectMapper.valueToTree(mainSysTimeline.getExtraData());
            JsonNode vendorExtraData = objectMapper.valueToTree(vendorSysTimeLine.getExtraData());

            //Source vendorExtraData ：把供应商PN to Client（373）的PN Ready Date（390）日期 赋值给 Target mainExtraData ：PN Ready Date from Vendor（371） 的 Actual（385）
            // 获取源节点373.390的值
            JsonNode valueToCopy1 = vendorExtraData.path("373").path("390");
            // 目标节点371.385去赋值
            ((ObjectNode)((ObjectNode) mainExtraData).get("371")).set("385", valueToCopy1);

            // Helper method to copy values
            // copyJsonValue(vendorExtraData, mainExtraData, "376", "402");
            // copyJsonValue(vendorExtraData, mainExtraData, "376", "404");
            // copyJsonValue(vendorExtraData, mainExtraData, "378", "406");
            // copyJsonValue(vendorExtraData, mainExtraData, "378", "407");
            // copyJsonValue(vendorExtraData, mainExtraData, "372", "388", "371", "385");

            mainSysTimeline.setExtraData(objectMapper.treeToValue(mainExtraData, Map.class));

            sysTimelineMapper.updateSysTimeline(mainSysTimeline);
            System.out.println("Main timeline updated");
            return 1;
        } catch (Exception e) {
            DynamicDataSourceContextHolder.clearDataSourceType();
            System.err.println("Error invoking computeDelayDays function");
            e.printStackTrace();
            return 0;
        }
    }

    private int updateVendorSysTimeline(SysTimeline mainSysTimeLine) {
        try {
            //find main vendor timeline
            String uuid = mainSysTimeLine.getUuid();
            SysTimeline param = new SysTimeline();
            param.setUuid(uuid);
//            param.setVendorCompanyCode(null);
            SysTimeline vendorSysTimeline = sysTimelineMapper.selectSysTimelineByUuid(param);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainExtraData = objectMapper.valueToTree(mainSysTimeLine.getExtraData());
            JsonNode vendorExtraData = objectMapper.valueToTree(vendorSysTimeline.getExtraData());

            //Source mainExtraData ：把供应商PN to Client（373）的PN Ready Date（390）日期 赋值给 Target vendorExtraData ：PN Ready Date from Vendor（371） 的 Actual（385）
            // 获取源节点373.390的值
            JsonNode valueToCopy1 = mainExtraData.path("373").path("390");
            // 目标节点371.385去赋值
            ((ObjectNode)((ObjectNode) vendorExtraData).get("371")).set("385", valueToCopy1);
            
//            vendorExtraData.get("371").get("385") = mainExtraData.get("373").get("390");
            // Helper method to copy values
//            copyJsonValue(mainExtraData, vendorExtraData, "370", "379");
//            copyJsonValue(mainExtraData, vendorExtraData, "370", "380");
//            copyJsonValue(mainExtraData, vendorExtraData, "370", "381");
//            //copyJsonValue(mainExtraData, vendorExtraData, "371", "383", "372", "387");
//            copyJsonValue(mainExtraData, vendorExtraData, "374", "394");
//            copyJsonValue(mainExtraData, vendorExtraData, "373", "391");
//            copyJsonValue(mainExtraData, vendorExtraData, "376", "401");
//            copyJsonValue(mainExtraData, vendorExtraData, "376", "403");

            vendorSysTimeline.setExtraData(objectMapper.treeToValue(vendorExtraData, Map.class));
            System.out.println("Vendor timeline updated");
            //computeDelayDays(vendorSysTimeline);
            sysTimelineMapper.updateSysTimeline(vendorSysTimeline);

            return 1;
        } catch (Exception e) {
            DynamicDataSourceContextHolder.clearDataSourceType();
            System.err.println("Error updating vendor timeline");
            e.printStackTrace();
            return 0;
        }
    }


    private void copyJsonValue(JsonNode source, JsonNode target, String srcParent, String srcKey) {
        copyJsonValue(source, target, srcParent, srcKey, srcParent, srcKey);
    }

    private void copyJsonValue(JsonNode source, JsonNode target, String srcParent, String srcKey, String tgtParent, String tgtKey) {
        if (source.has(srcParent) && source.get(srcParent).has(srcKey)) {
            ((ObjectNode) target.get(tgtParent)).put(tgtKey, source.get(srcParent).get(srcKey));
        }
    }



    @Override
    public List<SysTimeline> selectExternalVendorSysTimelineList(SysTimeline sysTimeline)
    {
        return sysTimelineMapper.selectSysExternalVendorTimelineList(sysTimeline);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysTimelineByIds(Long[] ids)
    {
        //需要查是否存在相对应的timeline并删除

        //删除本数据库的timeline
        for(long id: ids){
            SysTimeline sysTimeline = sysTimelineMapper.selectSysTimelineById(id);
            SysClient clientParam =  new SysClient();
            if(sysTimeline.getClientCompanyCode()==null){
                return 0;
            }

            String companyCode = sysTimeline.getClientCompanyCode();
            clientParam.setCompanyCode(companyCode);
            List<SysClient> clientList = sysClientMapper.selectSysClientList(clientParam);
            if(clientList.isEmpty())
                return 0;

            SysClient client = clientList.get(0);
            String clientOrg = client.getOrg();
            if(!clientOrg.equals(sysTimeline.getOrganizationName()))//client is not from this company
            {
                DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(clientOrg);
                if (dataSourceType != null) {
                    DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                    sysTimelineMapper.deleteSysTimelineByUuid(sysTimeline.getUuid());
                    DynamicDataSourceContextHolder.clearDataSourceType();
                } else {
                    System.out.println("clientOrg is not found, no main timeline");
                }

            }
            else {//client is from this company
                String vendorCompanyCode = sysTimeline.getVendorCompanyCode();
                DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(vendorCompanyCode);

                if(vendorCompanyCode == null || vendorCompanyCode.equals(sysTimeline.getOrganizationName()) || dataSourceType == null)
                    System.out.println("no vendor timeline is found");
                else{
                    DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                    sysTimelineMapper.deleteSysTimelineByUuid(sysTimeline.getUuid());
                    DynamicDataSourceContextHolder.clearDataSourceType();
                }
            }
            sysTimelineMapper.deleteSysTimelineById(id);
        }
        return 1;
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysTimelineById(Long id)
    {
        return sysTimelineMapper.deleteSysTimelineById(id);
    }

//    private void computeBalance(SysTimeline entry) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Object extraData = entry.getExtraData();
//            JsonNode rootNode = objectMapper.valueToTree(extraData);
//            //PaymentReceivedFromClient.balance
//            JsonNode PaymentReceivedFromClient = rootNode.get("374");
//            //TODO: need to write function for computing balance
//            ((ObjectNode) PaymentReceivedFromClient).put("396", 0);
//            entry.setExtraData((Map<String, Object>) objectMapper.treeToValue(rootNode, Object.class));
//        }catch (Exception e) {
//            System.err.println("Error invoking computeDelayDays function: " + e.getMessage());
//        }
//    }

//    private void computePNReadyDatePlanned(SysTimeline entry) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Object extraData = entry.getExtraData();
//            if (extraData == null)
//                return;
//            JsonNode rootNode = objectMapper.valueToTree(extraData);
//
//            String expectString = null;
//            String actualString = null;
//            int delayDate = 0;
//
//            //PNReadyDateFromVendor Planned date
//            JsonNode PNReadyDateFromVendor = rootNode.get("371");
//            JsonNode clientPNDate = rootNode.get("372");
//            String clientPNDatePlannedStr = !clientPNDate.get("387").isNull() ? clientPNDate.get("387").asText() : null;
//            if (clientPNDatePlannedStr != null && !clientPNDatePlannedStr.isEmpty()) {
//                ((ObjectNode) PNReadyDateFromVendor).put("383", clientPNDatePlannedStr);
//            }
//            entry.setExtraData((Map<String, Object>) objectMapper.treeToValue(rootNode, Object.class));
//        }catch (Exception e) {
//            System.err.println("Error invoking computePNReadyDatePlanned function");
//        }
//    }


    //update delay days for every event, only compute delay days if expected and actual are present
//    private void computeDelayDays(SysTimeline entry) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Object extraData = entry.getExtraData();
//            if (extraData == null)
//                throw new Exception();
//
//            JsonNode rootNode = objectMapper.valueToTree(extraData);
//            String expectString = null;
//            String actualString = null;
//            int delayDate = 0;
//
//            //ClientInfoExchangeDateFromClient.delayDays
//            JsonNode ClientInfoExchangeDateFromClient = rootNode.get("370");
//            expectString = !ClientInfoExchangeDateFromClient.get("379").isNull() ? ClientInfoExchangeDateFromClient.get("379").asText() : null;
//            actualString = !ClientInfoExchangeDateFromClient.get("381").isNull() ? ClientInfoExchangeDateFromClient.get("381").asText() : null;
//            if(expectString != null && !expectString.isEmpty() && actualString != null && !actualString.isEmpty()) {
//                delayDate = computeNumOfDelayDays(expectString, actualString);
//                ((ObjectNode) ClientInfoExchangeDateFromClient).put("382", delayDate);
//            }else{
//                ((ObjectNode) ClientInfoExchangeDateFromClient).put("382", (JsonNode) null);
//            }
//
//
//            //PNReadyDateFromVendor delay days
//            JsonNode PNReadyDateFromVendor = rootNode.get("371");
//            expectString = !PNReadyDateFromVendor.get("383").isNull() ? PNReadyDateFromVendor.get("383").asText() : null;
//            actualString = !PNReadyDateFromVendor.get("385").isNull() ? PNReadyDateFromVendor.get("385").asText() : null;
//            if(expectString != null && !expectString.isEmpty() && actualString != null && !actualString.isEmpty()) {
//                delayDate = computeNumOfDelayDays(expectString, actualString);
//                ((ObjectNode) PNReadyDateFromVendor).put("386", delayDate);
//            }else{
//                ((ObjectNode) PNReadyDateFromVendor).put("386", (JsonNode) null);
//            }
//            //ClientPNDate delay days
//            JsonNode ClientPNDate = rootNode.get("372");
//            expectString = !ClientPNDate.get("387").isNull() ? ClientPNDate.get("387").asText() : null;
//            actualString = !ClientPNDate.get("388").isNull() ? ClientPNDate.get("388").asText(): null;
//            if(expectString != null && !expectString.isEmpty() && actualString != null && !actualString.isEmpty()) {
//                delayDate = computeNumOfDelayDays(expectString, actualString);
//                ((ObjectNode) ClientPNDate).put("389", delayDate);
//            }else{
//                ((ObjectNode) ClientPNDate).put("389", (ObjectNode) null);
//            }
//
//
//            entry.setExtraData((Map<String, Object>) objectMapper.treeToValue(rootNode, Object.class));
//
//        }catch (Exception e) {
//            System.err.println("Error invoking computeDelayDays function");
//            //e.printStackTrace();
//        }
//
//    }
//    private int computeNumOfDelayDays(String expect, String actual){
//
//        Date actualDate = DateUtils.parseDate(actual);
//        Date expectedDate = DateUtils.parseDate(expect);
//        // Calculate the difference in days
//        return DateUtils.differentDaysByMillisecond(expectedDate, actualDate);
//    };

//    public static void setValuesToEmptyString(Map<String, Object> map) {
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            if (entry.getValue() instanceof Map) {
//                // Recursively set nested map values to empty string
//                setValuesToEmptyString((Map<String, Object>) entry.getValue());
//            } else {
//                // Set value to empty string
//                entry.setValue(null);
//            }
//        }
//    };
//
//    public static Map<String, Object> deepCopyMap(Map<String, Object> original) {
//        Map<String, Object> copy = new HashMap<>();
//        for (Map.Entry<String, Object> entry : original.entrySet()) {
//            Object value = entry.getValue();
//            if (value instanceof Map) {
//                // Recursively copy nested maps
//                copy.put(entry.getKey(), deepCopyMap((Map<String, Object>) value));
//            }  else {
//                // Copy primitive or immutable types as-is
//                copy.put(entry.getKey(), value);
//            }
//        }
//        return copy;
//    }

    public List<Map<String, Object>> getTimelineClientList(){
        return sysTimelineMapper.getTimelineClientList();
    }

}
