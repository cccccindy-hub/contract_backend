package com.nnroad.quartz.task;

//import com.nnroad.calendar.service.ICalendarGlobalConfigService;
import com.nnroad.client.service.IClientOtherServiceFeeService;
import com.nnroad.common.constant.ScheduleConstants;
//import com.nnroad.payroll.service.IEmployeeContractService;
//import com.nnroad.system.constants.MailConstants;
//import com.nnroad.system.domain.EmailBatch;
//import com.nnroad.system.service.IEmailBatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务调度测试
 *
 * @author Hrone
 */
@Component("payrollTask")
public class PayrollTask {

    @Value("${sys.quartzMode}")
    private String quartzMode;

//    @Autowired
//    private IEmployeeContractService employeeContractService;
//    @Autowired
//    private IEmailBatchService emailBatchService;
    @Autowired
    private IClientOtherServiceFeeService clientOtherServiceFeeService;

//    @Autowired
//    private ICalendarGlobalConfigService configService;

    protected final Logger logger = LoggerFactory.getLogger(PayrollTask.class);

//    public void ryRemindContract2() {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = employeeContractService.selectRemindContact();
//            System.out.println("已发送" + count + "封邮件提醒合同即将到期员工！");
//        }
//    }
//
//    public void ryRemindContract2WithArgs(Integer monthInAdvance, String htmlTemplate, String groupName, String... emails) {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = employeeContractService.selectRemindContractWithArgs(monthInAdvance, htmlTemplate, groupName, emails);
//            System.out.println("已发送" + count + "封邮件提醒合同即将到期员工！");
//        }
//    }
//
//    public void ryRemindRetire() {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = employeeContractService.selectRemindRetire();
//            System.out.println("已发送" + count + "封邮件醒即将退休员工！");
//        }
//    }
//
//    public void ryRemindEndContractBeforeThreeMonth() {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = employeeContractService.selectRemindEndEEContractToEmployer(3, 4);
//            System.out.println("已发送" + count + "封邮件提醒合同即将到期员工！");
//        }
//    }

//    public void ryRemindEndContractBeforeOneMonth() {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = employeeContractService.selectRemindEndEEContractToEmployer(1, 2);
//            System.out.println("已发送" + count + "封邮件提醒合同即将到期员工！");
//        }
//    }


//    public void birthdayEmail() {
//        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
//            int count = 0;
//            // emailBatch.setBirthdate(new Date(c.get(Calendar.YEAR)-1900, c.get(Calendar.MONTH), c.get(Calendar.DATE)));
//            List<EmailBatch> emailBatchList = emailBatchService.selectBirthDayEmailBatchList();
//            try {
//                if(emailBatchList != null && !emailBatchList.isEmpty()) {
//                    String ids = emailBatchList.stream().map(emailBatch1 -> String.valueOf(emailBatch1.getId())).collect(Collectors.joining(","));
//                    emailBatchService.sendFestivalMail(ids, "HAPPY BIRTHDAY FROM HRONE", MailConstants.BIRTHDAY_CARD_MAIL, "生日", 1L);
//                    count = emailBatchList.size();
//                }
//            } catch (Exception ex) {
//                logger.error(ex.getMessage());
//                ex.printStackTrace();
//            }
//            System.out.println("已发送 " + count + " BirthDay Emails！");
//        }
//    }

    public void otherServiceFeeGenerator() {
        if(quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
            int count = clientOtherServiceFeeService.generateOtherServiceFee();
            logger.info(count + " Employee Benefits(大礼包) added to the Payroll - Other Service Fee Table!");
        }
    }

//    public void genByYear(Integer year) {
//        configService.genByYear(year);
//    }


}
