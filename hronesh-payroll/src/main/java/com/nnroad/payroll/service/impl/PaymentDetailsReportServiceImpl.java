package com.nnroad.payroll.service.impl;

import cn.hutool.core.util.StrUtil;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.exportC.PaymentDetailsReport;
import com.nnroad.payroll.mapper.PaymentDetailsReportMapper;
import com.nnroad.payroll.service.IPaymentDetailsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * payment_details_reportService业务层处理
 *
 */
@Service
public class PaymentDetailsReportServiceImpl implements IPaymentDetailsReportService {

    @Autowired
    private PaymentDetailsReportMapper paymentDetailsReportMapper;

//    @Value("${sys.dbCommon}")
//    private String dbCommon;

    /**
     * 查询payment_details_report
     *
     * @param hroneNo hroneNo
     * @return payment_details_report
     */
    @Override
    public PaymentDetailsReport selectPaymentDetailsReportById(String hroneNo) {
        return paymentDetailsReportMapper.selectPaymentDetailsReportById(hroneNo);
    }

    /**
     * 查询payment_details_report列表
     *
     * @param paymentDetailsReport payment_details_report
     * @return payment_details_report
     */
    @Override
    public List<PaymentDetailsReport> selectPaymentDetailsReportList(PaymentDetailsReport paymentDetailsReport) {
        Map<String, Object> params = paymentDetailsReport.getParams();
        String durationRange = paymentDetailsReport.getDuration();
        if (!StringUtils.isEmpty(durationRange)) {
            String[] durationArray = durationRange.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
        }

        if (StringUtils.isNotEmpty(paymentDetailsReport.getPayDay())) {
            paymentDetailsReport.setPayDayList(Convert.toStrArray(paymentDetailsReport.getPayDay()));
        }
        /*Object erNamelist=paymentDetailsReport.getParams().get("erNamelist");
        if ((erNamelist !=null) && !erNamelist.equals("")){
            paymentDetailsReport.getParams().put("erNamelist",String.valueOf(erNamelist).split(","));
        }*/
        String erName = paymentDetailsReport.getErName();
        if (StrUtil.isNotBlank(erName)) {
            paymentDetailsReport.setErName(null);
            paymentDetailsReport.getParams().put("erNamelist", erName.split(","));
        }
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        paymentDetailsReport.setParams(params);

        return paymentDetailsReportMapper.selectPaymentDetailsReportList(paymentDetailsReport);
    }

}
