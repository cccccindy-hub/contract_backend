package com.nnroad.web.controller.lead;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.client.service.IClientQuestionService;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.MailTemplate;
import com.nnroad.common.utils.NnroadSequence;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.datacenter.domain.DCDictData;
import com.nnroad.datacenter.service.IDCTableConfigService;
import com.nnroad.lead.domain.ClientLeads;
import com.nnroad.lead.domain.ClientLeadsConfig;
import com.nnroad.lead.enums.ClientLeadsStatus;
import com.nnroad.lead.enums.LeadsCodePrefixEnum;
import com.nnroad.lead.service.IClientLeadsConfigService;
import com.nnroad.lead.service.IClientLeadsService;
import com.nnroad.lead.vo.Leads2ClientVo;
import com.nnroad.system.constants.MailEnum;
import com.nnroad.system.domain.SysEmailSend;
import com.nnroad.system.service.ISysMailService;
import com.nnroad.system.service.ISysUserService;
import com.nnroad.web.controller.client.ClientController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.nnroad.system.constants.SysConstants;
import com.nnroad.client.constant.ClientConstants;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClientLeadsController
 *
 * @author Aaron
 * @date 2022-06-10
 */
@RestController
@RequestMapping("/client/clientLeads")
public class ClientLeadsController extends BaseController {

    @Value("${api.surveyApiUrl}")
    private String surveyApiUrl;

    @Value("${api.questionHtml}")
    private String questionHtml;

    public static final String CLIENT_PREFIX = "C";

    @Autowired
    private IClientLeadsService clientLeadsService;

    @Autowired
    private IClientQuestionService clientQuestionService;

    @Autowired
    private IDCTableConfigService dcTableConfigService;

    @Autowired
    private IClientLeadsConfigService clientLeadsConfigService;

    @Autowired
    private ISysMailService sysMailServiceImpl;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private ISysClientService clientService;

    @Autowired
    private SysClientMapper clientMapper;


    /**
     * 查询ClientLeads列表
     */
    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermi('client:leads:list')")
    public TableDataInfo list(@RequestBody ClientLeads clientLeads)
    {
        startPage();
        List<ClientLeads> list = clientLeadsService.selectClientLeadsList(clientLeads);
        return getDataTable(list);
    }

    // 挂载时，获取下拉框数据
    @GetMapping("/getLeadsNameList")
    @PreAuthorize("@ss.hasPermi('client:leads:list')")
    public AjaxResult getLeadsNameList() {
        HashMap<String,Object> map = new HashMap<>();
        ClientLeads clientLeads = new ClientLeads();
        clientLeads.setDeleteFlag("0");

        List<String> configDictList = Arrays.asList("service", "channel", "qualified_leads");
        ClientLeadsConfig clientLeadsConfig = new ClientLeadsConfig();
        clientLeadsConfig.setDeleteFlag("0");
        Map<String, List<ClientLeadsConfig>> configLists = clientLeadsConfigService.getClientLeadsConfigs(configDictList, clientLeadsConfig);
        map.put("channels", configLists.get("channel"));
        map.put("serviceArray", configLists.get("service"));
        map.put("qualifiedLeads", configLists.get("qualified_leads"));

        List<String> dictList = Arrays.asList("member", "country", "leadStatus", "s_location", "industry", "member");
        //Query the data from the DC TableConfig
        Map<String, List<DCDictData>> dictLists = dcTableConfigService.getDictList(dictList);
        map.put("country", Optional.ofNullable(dictLists.get("country")).orElse(new ArrayList<>()));
        map.put("leadStatus", Optional.ofNullable(dictLists.get("leadStatus")).orElse(new ArrayList<>()));
        map.put("location", Optional.ofNullable(dictLists.get("s_location")).orElse(new ArrayList<>()));
        map.put("industry", Optional.ofNullable(dictLists.get("industry")).orElse(new ArrayList<>()));
        map.put("members", Optional.ofNullable(dictLists.get("member")).orElse(new ArrayList<>()));
        //BD ROLE
        map.put("bdInfos", sysUserService.findByRoleCode("bd_role"));

        return success(map);
    }

    /**
     * 新增保存ClientLeads
     */
    @PreAuthorize("@ss.hasPermi('client:leads:add')")
    @PutMapping("/add")
    public AjaxResult addSave(@RequestBody ClientLeads clientLeads, @RequestParam Boolean forceAdd) {
        if(forceAdd != null && forceAdd) {
            return toAjax(clientLeadsService.insertClientLeads(clientLeads));
        }
        //check if we already have the lead with the same name in the DB
        ClientLeads duplicatedLead = new ClientLeads();
        duplicatedLead.setCompanyName(clientLeads.getCompanyName());
        List<ClientLeads> duplicatedLeads = clientLeadsService.selectExactLeadsList(duplicatedLead);
        //in case we don't have lead with the same name, we add the lead to DB
        if (duplicatedLeads == null || duplicatedLeads.isEmpty()) {
            //Get code for the lead
            String leadCode = getClientCode(clientLeads.getMemberCode());
            clientLeads.setLeadCode(leadCode);
            return toAjax(clientLeadsService.insertClientLeads(clientLeads));
        }
        return AjaxResult.warn("The table already contains a lead with the name: '" + clientLeads.getCompanyName() + "'.");
    }

    /**
     * 修改时获取 原ClientLeads 数据
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("@ss.hasPermi('client:leads:edit')")
    public AjaxResult edit(@PathVariable("id") Long id) {
        ClientLeads clientLeads = clientLeadsService.selectClientLeadsById(id);
        return success(clientLeads);
    }

    /**
     * 修改保存ClientLeads
     */
    @PreAuthorize("@ss.hasPermi('client:leads:edit')")
    @PostMapping("/edit")
    public AjaxResult editSave(@RequestBody ClientLeads clientLeads) {
        return toAjax(clientLeadsService.updateClientLeads(clientLeads));
    }

    /**
     * 删除ClientLeads
     */
    @PreAuthorize("@ss.hasPermi('client:leads:remove')")
    @Log(title = "ClientLeads", businessType = BusinessType.DELETE)
    @DeleteMapping( "/remove/{id}")
    public AjaxResult remove(@PathVariable("id") String ids) {
        return toAjax(clientLeadsService.deleteClientLeadsByIds(ids));
    }


    /**
     * 归档时  获取相关数据
     */
    @GetMapping("/archive/{id}")
    @PreAuthorize("@ss.hasPermi('client:leads:archive')")
    public AjaxResult archive(@PathVariable("id") Long id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("archiveCategories", clientLeadsConfigService.getClientLeadsConfigs(Collections.singletonList("archive_category")).get("archive_category"));
        map.put("id", id);
        return success(map);
    }
    /**
     * 归档
     */
    @PreAuthorize("@ss.hasPermi('client:leads:archive')")
    @Log(title = "ClientLeads", businessType = BusinessType.UPDATE)
    @PostMapping("/archive")
    public AjaxResult archive(@RequestBody ClientLeads clientLeads) {
        clientLeads.setLeadStatus(ClientLeadsStatus.Archived.getStrValue());
        int temp=clientLeadsService.updateClientLeads(clientLeads);
        ClientLeads cl = clientLeadsService.selectClientLeadsById(clientLeads.getId());
        mailNotice(cl);
        return toAjax(temp);
    }

    /**
     * 转正时 获取已有数据
     */
    @PreAuthorize("@ss.hasPermi('client:leads:converte')")
    @GetMapping("/convert/{id}")
    public AjaxResult convert(@PathVariable("id") Long id){
        HashMap<String,Object> map = new HashMap<>();
        ClientLeads clientLeads = clientLeadsService.selectClientLeadsById(id);
        if ("".equals(clientLeads.getService()) || clientLeads.getService().isEmpty()){
            throw new BusinessException("service type 为 空 ！");
        }
        map.put("clientLeads", clientLeads);
        List<String> dictList = new ArrayList<String>();
        dictList.add("country");
        dictList.add("s_location");
        dictList.add("service_fee_type");
        dictList.add("client_deposit_type");
        dictList.add("exchange_rate_strategy");

        //Query the data from the DC TableConfig
        Map<String, List<DCDictData>> dictLists = dcTableConfigService.getDictList(dictList);
        map.put("country", dictLists.get("country"));
        map.put("location", dictLists.get("s_location"));
        map.put("service_fee_type", dictLists.get("service_fee_type"));
        map.put("client_deposit_type", dictLists.get("client_deposit_type"));
        map.put("exchange_rate_strategy", dictLists.get("exchange_rate_strategy"));

        return success(map);
    }

    /**
     * 转正
     */
    @PreAuthorize("@ss.hasPermi('client:leads:converte')")
    @PostMapping("/convert")
    @Transactional
    public AjaxResult saveConvert(@RequestBody Leads2ClientVo leads2ClientVo) {
        //check if the lead has full information:
        ClientLeads clientLeads = clientLeadsService.selectClientLeadsById(leads2ClientVo.getId());

        String qualifiedLeads = clientLeads.getQualifiedLeads();

        //check if the lead is qualified
        if(!"32".equals(qualifiedLeads)){
            throw new BusinessException("Lead is not qualified.");
        }
        // convert Leads to Client if the lead does not have a code
        SysClient client = new SysClient();
        // ClientCode重新生成，跟Leads不一样
        client.setCompanyCode(getClientCode(null));
        clientLeadsService.converteClientLeads(leads2ClientVo, client);

        try {
            SysClient sysClient = clientMapper.selectClient(client);

            // save contract information
            Map<String, String> map = new HashMap<>();
            //map.put("client_name", clientLeads.getCompanyName());
            map.put("173", String.valueOf(leads2ClientVo.getServiceFeeType()));
            map.put("175", String.valueOf(leads2ClientVo.getServiceFeeValue()));
            map.put("177", String.valueOf(leads2ClientVo.getDepositType()));
            map.put("178", String.valueOf(leads2ClientVo.getDepositValue()));
            // map.put("signed_date", DateUtil.format(leads2ClientVo.getContractSignedDate(), "yyyy-MM-dd"));
            map.put("179", String.valueOf(leads2ClientVo.getExchangeRate()));
            map.put("287", String.valueOf(leads2ClientVo.getEmploymentContract()));
            // clientController.addSave(112L, map);

            // 将Map转换为JSON字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);

            Map<String, String> contractMap = new HashMap<>();
            contractMap.put("client_id",String.valueOf(sysClient.getId()));
            contractMap.put("company_code",sysClient.getCompanyCode());
            contractMap.put("extra_data",json);
            clientService.insertClientContract(contractMap);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting client contract with map", e);
        }
        // 发送邮件(问卷)
        mailNotice(clientLeads);
        return AjaxResult.success();
    }

    /**
     * 发送邮件
     * @param clientLeads
     */
    public void mailNotice(ClientLeads clientLeads) {
        try {
            Calendar calendar = Calendar.getInstance();
            // 当前时间加上 30 天
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            // 获取时间戳
            long expiredAt = calendar.getTimeInMillis();
            String email = clientLeads.getContactEmail();
            if (StringUtils.isNotEmpty(email)) {
                String surveyId = clientLeadsService.selectBD();
                String reciverId = clientLeads.getLeadCode();
                String reciverName = clientLeads.getCompanyName();
                String other = clientLeads.getLeadStatus();

                // 构建 JSON 字符串
                String jsonString = String.format("{"
                        + "\"surveyId\":\"%s\","
                        + "\"expiredAt\":%d,"
                        + "\"reciverId\":\"%s\","
                        + "\"reciverName\":\"%s\","
                        + "\"reciverEmail\":\"%s\","
                        + "\"other\":%s"  // 添加新的字段
                        + "}", surveyId, expiredAt, reciverId, reciverName, email, other);  // 增加 other 参数

                // 设置请求
                URL url = new URL(surveyApiUrl + "/server/mail_to");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // 发送 JSON 数据
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                // 读取服务器的响应内容
                int responseCode = conn.getResponseCode();
                StringBuilder response = new StringBuilder();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                    }
                    logger.info("Response Body: " + response.toString());
                } else {
                    logger.error("Request failed with response code: " + responseCode);
                }
            }
        } catch (Exception e) {

            logger.error(e.getMessage());
            // 可以根据需求处理异常或记录日志
            throw new RuntimeException("处理请求时发生错误", e);
        }
    }

    /**
     * 获取客户code
     * @return
     */
    private String getClientCode(String memberCode) {

        String location = CLIENT_PREFIX + SysConstants.DEFAULT_LOCATION;
        if (StrUtil.isNotBlank(memberCode)) {
            String prefix = LeadsCodePrefixEnum.getByMemberCode(memberCode);
            if(StrUtil.isNotBlank(location)) {
                location = prefix;
            }
        }
        String clientCode = sequence.getCode(location,"-" , 4);
        SysClient c = new SysClient();
        // 查询数据库存储的code没有"-"分割
        c.setCompanyCode(clientCode.replace("-", ""));
        if(!clientService.checkCode(c , location)){
            clientCode = clientService.resetAndGetCode(location);
        }
        // 数据库存储的code没有"-"分割
        clientCode = clientCode.replace("-","");
        return clientCode;
    }




    private String prefix = "client/clientLeads";
    /**
     * 选择人员树
     * with selecting the default role
     */
    @RequiresPermissions("client:clientLeads:add")
    @GetMapping("/selectUserTree")
    public String selectUserTree(String selectedUserIds, String selectedUserNames, Boolean multiSelectFlag, String defRole, ModelMap mmap) {
        mmap.put("defRole", defRole);
        mmap.put("selectedUserIds", selectedUserIds);
        mmap.put("selectedUserNames", selectedUserNames);
        mmap.put("multiSelectFlag", multiSelectFlag);
        return prefix + "/tree";
    }

    /**
     *  不合格
     *
     * @param id 文件导入配置ID
     * @return 结果
     */
    @RequiresPermissions("client:clientLeads:edit")
    @PostMapping("/noQualified")
    @ResponseBody
    public AjaxResult noQualified(Long id) {
        ClientLeads clientLeads = new ClientLeads();
        clientLeads.setId(id);
        String noId = clientLeadsConfigService.selectClientLeadsConfigList("qualified_leads", "No", "0").get(0).getId().toString();
        clientLeads.setQualifiedLeads(noId);
        clientLeads.setLeadStatus(ClientLeadsStatus.Archived.getStrValue());
        clientLeads.setConvertedDate(new Date());
        clientLeads.setArchiveReason("unqualified leads");
        return toAjax(clientLeadsService.updateClientLeads(clientLeads));
    }

    /**
     *  合格
     *
     * @param id ID
     * @return 结果
     */
    @RequiresPermissions("client:clientLeads:edit")
    @PostMapping("/qualified")
    @ResponseBody
    public AjaxResult qualified(Long id) {
        ClientLeads clientLeads = new ClientLeads();
        clientLeads.setId(id);
        String yesId = clientLeadsConfigService.selectClientLeadsConfigList("qualified_leads", "Yes", "0").get(0).getId().toString();
        clientLeads.setQualifiedLeads(yesId);
        clientLeads.setLeadStatus(ClientLeadsStatus.Ongoing.getStrValue());
        return toAjax(clientLeadsService.updateClientLeads(clientLeads));
    }


}

