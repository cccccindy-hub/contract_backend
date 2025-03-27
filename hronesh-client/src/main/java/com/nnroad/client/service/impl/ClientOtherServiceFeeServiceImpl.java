package com.nnroad.client.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import com.nnroad.client.domain.ClientInfo;
import com.nnroad.client.domain.ClientOtherServiceFeePayment;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.ClientInfoMapper;
import com.nnroad.client.mapper.ClientOtherServiceFeePaymentMapper;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.client.service.IClientOtherServiceFeePaymentService;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import org.apache.commons.collections.CollectionUtils;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.ShiroUtils;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import org.apache.commons.compress.archivers.sevenz.CLI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.client.mapper.ClientOtherServiceFeeMapper;
import com.nnroad.client.domain.ClientOtherServiceFee;
import com.nnroad.client.service.IClientOtherServiceFeeService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-17
 */
@Service
public class ClientOtherServiceFeeServiceImpl implements IClientOtherServiceFeeService
{
    private static final Logger log = LoggerFactory.getLogger(ClientOtherServiceFeeServiceImpl.class);
    @Autowired
    private ClientOtherServiceFeeMapper clientOtherServiceFeeMapper;

    @Autowired
    private SysClientMapper sysClientMapper;

    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    @Autowired
    private ClientOtherServiceFeePaymentMapper clientOtherServiceFeePaymentMapper;

    @Autowired
    private ClientInfoMapper clientInfoMapper;





    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ClientOtherServiceFee selectClientOtherServiceFeeById(Long id)
    {
        ClientOtherServiceFee clientOtherServiceFee = clientOtherServiceFeeMapper.selectClientOtherServiceFeeById(id);
        ClientOtherServiceFeePayment clientOtherServiceFeePayment = new ClientOtherServiceFeePayment();
        clientOtherServiceFeePayment.setClientOtherServiceFeeId(clientOtherServiceFee.getId());
        List<ClientOtherServiceFeePayment> list = clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentList(clientOtherServiceFeePayment);
        List<ClientOtherServiceFeePayment> otherServiceFeeList = new ArrayList<>();
        List<ClientOtherServiceFeePayment>  otherBehalfFeeList = new ArrayList<>();
        List<ClientOtherServiceFeePayment> oneTimeServiceList = new ArrayList<>();

        list.forEach(x -> {
            if (new Long(0).equals(x.getPaymentType())) {
                otherServiceFeeList.add(x);
            } else if (new Long(1).equals(x.getPaymentType())) {
                otherBehalfFeeList.add(x);
            } else if (new Long(2).equals(x.getPaymentType())) {
                clientOtherServiceFee.setDelayFee(x);
            } else if (new Long(3).equals(x.getPaymentType())) {
                clientOtherServiceFee.setBalance(x);
            } else if (new Long(4).equals(x.getPaymentType())) {
                clientOtherServiceFee.setYearlyManagementServiceFee(x);
            } else if (new Long(5).equals(x.getPaymentType())) {
                clientOtherServiceFee.setExpenseClaimServiceFee(x);
            } else if (new Long(6).equals(x.getPaymentType())) {
                oneTimeServiceList.add(x);
            }
        });
        clientOtherServiceFee.setOtherServiceFee(otherServiceFeeList);
        clientOtherServiceFee.setOtherBehalfFee(otherBehalfFeeList);
        clientOtherServiceFee.setOneTimeService(oneTimeServiceList);

        return clientOtherServiceFee;
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ClientOtherServiceFee> selectClientOtherServiceFeeList(ClientOtherServiceFee clientOtherServiceFee)
    {
        Map<String, Object> params = clientOtherServiceFee.getParams();
        String durationRange = params.get("durationRange") != null ? params.get("durationRange").toString() : "";
        if (!StringUtils.isEmpty(durationRange)) {
            String[] durationArray = durationRange.split(",");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            //convert beginTime endTime to correct format
            ZonedDateTime beginDateTime = ZonedDateTime.parse(beginTime);
            String beginTimeyearMonth = beginDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            ZonedDateTime endDateTime = ZonedDateTime.parse(endTime);
            String endTimeyearMonth = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            params.put("beginTime", beginTimeyearMonth);
            params.put("endTime", endTimeyearMonth.trim());
        }
        return clientOtherServiceFeeMapper.selectClientOtherServiceFeeList(clientOtherServiceFee);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClientOtherServiceFee(ClientOtherServiceFee clientOtherServiceFee)
    {
        SysClient client = sysClientMapper.selectSysClientById(clientOtherServiceFee.getClientId());
        SysEmployee employee = sysEmployeeMapper.selectSysEmployeeById(clientOtherServiceFee.getEmployeeId());
        clientOtherServiceFee.setCompanyCode(client.getCompanyCode());
        clientOtherServiceFee.setClientName(client.getClientName());
        // Check if employee is null, then set "ALL" for employeeCode and employeeName
        if (employee != null) {
            clientOtherServiceFee.setEmployeeCode(employee.getEmployeeCode());
            clientOtherServiceFee.setEmployeeName(employee.getEmployeeName());
        } else {
            clientOtherServiceFee.setEmployeeCode("ALL");
            clientOtherServiceFee.setEmployeeName("ALL");
        }
        clientOtherServiceFeeMapper.insertClientOtherServiceFee(clientOtherServiceFee);
        Long generatedId = clientOtherServiceFee.getId();
        insertServicePaymentInBatch(client, generatedId, clientOtherServiceFee);
        return Math.toIntExact(generatedId);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClientOtherServiceFee(ClientOtherServiceFee clientOtherServiceFee)
    {
        SysClient client = sysClientMapper.selectSysClientById(clientOtherServiceFee.getClientId());
        SysEmployee employee = sysEmployeeMapper.selectSysEmployeeById(clientOtherServiceFee.getEmployeeId());
        clientOtherServiceFee.setCompanyCode(client.getCompanyCode());
        clientOtherServiceFee.setClientName(client.getClientName());
        // Check if employee is null, then set "ALL" for employeeCode and employeeName
        if (employee != null) {
            clientOtherServiceFee.setEmployeeCode(employee.getEmployeeCode());
            clientOtherServiceFee.setEmployeeName(employee.getEmployeeName());
        } else {
            clientOtherServiceFee.setEmployeeCode("ALL");
            clientOtherServiceFee.setEmployeeName("ALL");
        }
        clientOtherServiceFeeMapper.updateClientOtherServiceFee(clientOtherServiceFee);
        Long clientServiceFeeId = clientOtherServiceFee.getId();
        clientOtherServiceFeePaymentMapper.deleteClientOtherServiceFeePaymentByPid(clientServiceFeeId);
        insertServicePaymentInBatch(client, clientServiceFeeId, clientOtherServiceFee);
        return Math.toIntExact(clientServiceFeeId);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientOtherServiceFeeByIds(Long[] ids)
    {
        return clientOtherServiceFeeMapper.deleteClientOtherServiceFeeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientOtherServiceFeeById(Long id)
    {
        return clientOtherServiceFeeMapper.deleteClientOtherServiceFeeById(id);
    }

    private void insertServicePaymentInBatch(SysClient client, Long clientServiceFeeId, ClientOtherServiceFee clientOtherServiceFee) {
        String userName = SecurityUtils.getUsername();
        List<ClientOtherServiceFeePayment> allPayments = new ArrayList<>();
        if (!clientOtherServiceFee.getOtherServiceFee().isEmpty()) {
            setVatRate(client, clientOtherServiceFee.getOtherServiceFee());
            allPayments.addAll(clientOtherServiceFee.getOtherServiceFee());

        }
        if (!clientOtherServiceFee.getOtherBehalfFee().isEmpty()) {
            allPayments.addAll(clientOtherServiceFee.getOtherBehalfFee());

        }
        if (!clientOtherServiceFee.getOneTimeService().isEmpty()) {
            allPayments.addAll(clientOtherServiceFee.getOneTimeService());

        }
        allPayments.forEach(x -> {
                    x.setClientOtherServiceFeeId(clientServiceFeeId);
                    x.setUpdateBy(userName);
                    x.setCreateBy(userName);
                }
        );
        if (clientOtherServiceFee.getDelayFee() != null && clientOtherServiceFee.getDelayFee().getServiceCost() != null) {
            ClientOtherServiceFeePayment payment = clientOtherServiceFee.getDelayFee();
            payment.setClientOtherServiceFeeId(clientServiceFeeId);
            payment.setHasTax(false);
            payment.setTargetCurrency(null);
            payment.setCreateBy(userName);
            payment.setUpdateBy(userName);
            allPayments.add(payment);
        }

        if (clientOtherServiceFee.getBalance() != null && clientOtherServiceFee.getBalance().getServiceCost() != null) {
            ClientOtherServiceFeePayment payment = clientOtherServiceFee.getBalance();
            payment.setClientOtherServiceFeeId(clientServiceFeeId);
            payment.setHasTax(false);
            payment.setTargetCurrency(null);
            payment.setCreateBy(userName);
            payment.setUpdateBy(userName);
            allPayments.add(payment);

        }

        if (clientOtherServiceFee.getYearlyManagementServiceFee() != null && clientOtherServiceFee.getYearlyManagementServiceFee().getServiceCost() != null) {
            ClientOtherServiceFeePayment payment = clientOtherServiceFee.getYearlyManagementServiceFee();
            payment.setClientOtherServiceFeeId(clientServiceFeeId);
            payment.setHasTax(false);
            payment.setTargetCurrency(null);
            payment.setCreateBy(userName);
            payment.setUpdateBy(userName);
            allPayments.add(payment);

        }

        if (clientOtherServiceFee.getExpenseClaimServiceFee() != null && clientOtherServiceFee.getExpenseClaimServiceFee().getProofCount() != null) {
            ClientInfo clientBillingInfo = clientInfoMapper.selectClientInfoByClientId(clientOtherServiceFee.getClientId(), "client_billing_status");
            ClientOtherServiceFeePayment payment = clientOtherServiceFee.getExpenseClaimServiceFee();
            payment.setServiceName("Expense claim service fee 报销服务费");
            payment.setClientOtherServiceFeeId(clientServiceFeeId);
            payment.setCreateBy(userName);
            payment.setUpdateBy(userName);
            // get 报销服务单价 from client billing status
            String expenseClaimServiceUnit = String.valueOf(clientBillingInfo.getExtraData().get("212"));
            if (expenseClaimServiceUnit == null || expenseClaimServiceUnit.isEmpty()) {
                payment.setServiceCost(BigDecimal.ZERO);
            } else {
                try{
                    payment.setServiceCost(BigDecimal.valueOf((long) payment.getProofCount() * (Double.parseDouble(expenseClaimServiceUnit))));
                }
                catch (NumberFormatException e) {
                    //报销服务单价 is not a valid integer
                    throw new NumberFormatException("报销服务单价 is not in valid integer format");
                }

            }
            allPayments.add(payment);
        }

        if (!allPayments.isEmpty()) {
            clientOtherServiceFeePaymentMapper.insertBatch(allPayments);
        } else {
            throw new BusinessException("Please fill in Other Service Fee");
        }
    }

    private void setVatRate(SysClient client, List<ClientOtherServiceFeePayment> otherServiceFee) {
        ClientInfo billStatusInfo = clientInfoMapper.selectClientInfoByCode(client.getCompanyCode(), "client_billing_status");
        Double vat_rate = 6.77;
        if(billStatusInfo != null) {
            Map<String, Object> extraData = billStatusInfo.getExtraData();
            if (extraData != null) {
                String mainServiceType = (String) extraData.get("185");
                if ("Agency".equals(mainServiceType) || "Advanced Agency".equals(mainServiceType) || "Agency-Channel".equals(mainServiceType)) {
                    vat_rate = 6.0;
                }
            }
        }
        for (ClientOtherServiceFeePayment payment: otherServiceFee) {
            payment.setVatRate(vat_rate);
        }
    }

    public int generateOtherServiceFee() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
        //get otherServiceFeeMain data for the past month 上个月所有otherServiceFeeMain
        ClientOtherServiceFee clientOtherServiceFee = new ClientOtherServiceFee();
        LocalDate desiredDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        String formattedDate = desiredDate.format(dateFormat);
        clientOtherServiceFee.getParams().put("beginTime", formattedDate);
        clientOtherServiceFee.getParams().put("endTime", formattedDate);
        List<ClientOtherServiceFee> pastMonthList = clientOtherServiceFeeMapper.selectClientOtherServiceFeeList(clientOtherServiceFee);

        //get otherServiceFee data for current month 这个月所有的OtherServiceFee
        ClientOtherServiceFee clientOtherServiceFeeCurrentMonth = new ClientOtherServiceFee();
        clientOtherServiceFeeCurrentMonth.getParams().put("beginTime", DateUtils.parseDateToStr("yyyy-MM", DateUtils.getNowDate()));
        clientOtherServiceFeeCurrentMonth.getParams().put("endTime", DateUtils.parseDateToStr("yyyy-MM", DateUtils.getNowDate()));
        List<ClientOtherServiceFee> currentMonthList = clientOtherServiceFeeMapper.selectClientOtherServiceFeeList(clientOtherServiceFeeCurrentMonth );

        //exclude psOtherServiceFeeMain data from pastMonthList if the service fee with the same client code and employee code has been added to current month already
        //对比这个月和上个月记录，标记出当月已经生成的记录
        for (ClientOtherServiceFee currentMonthItem : currentMonthList) {
            for (ClientOtherServiceFee item : pastMonthList) {
                if (item.getCompanyCode().equals(currentMonthItem.getCompanyCode()) && item.getEmployeeCode().equals(currentMonthItem.getEmployeeCode())) {
                    item.getParams().put("remove!", true);
                }
            }
        }
        //过滤掉已经生成的记录
        pastMonthList = pastMonthList.stream().filter(item -> item.getParams().get("remove!") == null).collect(Collectors.toList());
        if (pastMonthList.isEmpty()) {
            return 0;
        }
        //get past month otherServiceFeePayment which only has '大礼包' in service_remark column
        //筛选出上个月已经生成包含【”大礼包的“】otherServiceFeePayment
        ClientOtherServiceFeePayment clientOtherServiceFeePayment = new ClientOtherServiceFeePayment();
        clientOtherServiceFeePayment.setPaymentType(0L);
        clientOtherServiceFeePayment.getParams().put("otherServiceFeeIds", pastMonthList.stream().map(ClientOtherServiceFee::getId).distinct().collect(Collectors.toList()));
        clientOtherServiceFeePayment.getParams().put("serviceRemarks", Arrays.asList("大礼包", "dalibao", "da libao", "dali bao", "da li bao"));
        List<ClientOtherServiceFeePayment> clientOtherServiceFeePayments = clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentList(clientOtherServiceFeePayment);

        //get past month otherServiceFeePayment which only has 'Office Space 办公室租赁服务' in service_name
        //筛选出上个月包含【Office Space 办公室租赁服务】的数据
        clientOtherServiceFeePayment = new ClientOtherServiceFeePayment();
        clientOtherServiceFeePayment.setPaymentType(0L);
        clientOtherServiceFeePayment.getParams().put("otherServiceFeeIds", pastMonthList.stream().map(ClientOtherServiceFee::getId).distinct().collect(Collectors.toList()));
        clientOtherServiceFeePayment.setServiceName("Office Space 办公室租赁服务");
        List<ClientOtherServiceFeePayment> officeSpacePayments = clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentList(clientOtherServiceFeePayment);

        //get past month otherServiceFeePayment which only has 'Seat fee 座位费' in service_name
        //筛选出上个月包含【Seat fee 座位费】的数据
        clientOtherServiceFeePayment = new ClientOtherServiceFeePayment();
        clientOtherServiceFeePayment.setPaymentType(0L);
        clientOtherServiceFeePayment.getParams().put("otherServiceFeeIds", pastMonthList.stream().map(ClientOtherServiceFee::getId).distinct().collect(Collectors.toList()));
        clientOtherServiceFeePayment.setServiceName("Seat fee 座位费");
        List<ClientOtherServiceFeePayment> seeFeePayments = clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentList(clientOtherServiceFeePayment);

        //concatenate office payments with dalibao payments
        //整合【”大礼包的“】和【Office Space 办公室租赁服务】和【Seat fee 座位费】的数据

        // Initialize the final list
        List<ClientOtherServiceFeePayment> finalClientOtherServiceFeePayments = new ArrayList<>();
        // Add dalibao payments if available
        if (clientOtherServiceFeePayments != null && !clientOtherServiceFeePayments.isEmpty()) {
            finalClientOtherServiceFeePayments.addAll(clientOtherServiceFeePayments);
        }

        // Add office space payments if available
        if (officeSpacePayments != null && !officeSpacePayments.isEmpty()) {
            finalClientOtherServiceFeePayments.addAll(officeSpacePayments);
        }

        // Add seat fee payments if available
        if (seeFeePayments != null && !seeFeePayments.isEmpty()) {
            finalClientOtherServiceFeePayments.addAll(seeFeePayments);
        }

        if (finalClientOtherServiceFeePayments.isEmpty()) {
            return 0;  // or any other logic you need if the list is empty
        }


        //generate same otherServiceFeeMains for this month as in psOtherServiceFeePayments
        List<Long> desiredOtherServiceFeeIds = finalClientOtherServiceFeePayments.stream().map(ClientOtherServiceFeePayment::getClientOtherServiceFeeId).collect(Collectors.toList());
        //过滤掉没有【”大礼包的“】和【Office Space 办公室租赁服务】和【Seat fee 座位费】的otherServiceFeeMain
        pastMonthList = pastMonthList.stream()
                .filter(item -> desiredOtherServiceFeeIds.contains(item.getId()))
                .collect(Collectors.toList());
        //put current month as ForPeriod
        pastMonthList.forEach(item -> {
            item.setPeriod(DateUtils.parseDate(desiredDate.plusMonths(1).format(dateFormat)));
            //save current id
            item.getParams().put("oldId", item.getId());
            item.setCreateBy("!CronJob");
            item.setCreateTime(DateUtils.getNowDate());
            item.setUpdateBy(null);
            item.setUpdateTime(null);
        });

        // DB insert to client_other_service_fee
        clientOtherServiceFeeMapper.insertBatchByImport(pastMonthList);
        //map Old Id to New Id
        HashMap<Object, Long> mainOldIdtoNewIdMap = new HashMap<>();
        pastMonthList.forEach(item -> mainOldIdtoNewIdMap.put(item.getParams().get("oldId"), item.getId()));

        //set finalClientOtherServiceFeePayments' other_service_fee_id
        finalClientOtherServiceFeePayments.forEach(item -> {
            item.setClientOtherServiceFeeId(mainOldIdtoNewIdMap.get(item.getClientOtherServiceFeeId()));
            item.setCreateBy("!CronJob");
            item.setUpdateBy(null);
            item.setUpdateTime(null);
            item.setCreateTime(DateUtils.getNowDate());
        });

        log.info("job add sOtherServiceFeePayments:{}", JSONObject.toJSONString(finalClientOtherServiceFeePayments));

        // DB insert to client_other_service_fee_payment
        return clientOtherServiceFeePaymentMapper.insertBatch(finalClientOtherServiceFeePayments);
    }
}
