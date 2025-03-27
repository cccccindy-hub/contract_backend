package com.nnroad.payroll.constants;

/**
 * ps_op_log对象 ps_op_log
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public class PayrollConstants
{
    /** ee_no 是否唯一的返回结果 */
    public final static String EMPLOYER_ER_NO_UNIQUE = "0";
    public final static String EMPLOYER_ER_NO_NOT_UNIQUE = "1";

    /** ee_no 是否唯一的返回结果 */
    public final static String EMPLOYEE_EE_NO_UNIQUE = "0";
    public final static String EMPLOYEE_EE_NO_NOT_UNIQUE = "1";

    /** e-mail 是否唯一的返回结果 */
    public final static String EMPLOYEE_EMAIL_UNIQUE = "0";
    public final static String EMPLOYEE_EMAIL_NOT_UNIQUE = "1";

    /** id_card 是否唯一的返回结果 */
    public final static String EMPLOYEE_ID_CARD_UNIQUE = "0";
    public final static String EMPLOYEE_ID_CARD_NOT_UNIQUE = "1";

    /** 是否在职 */
    public final static String EMPLOYEE_ON_THE_JOB_ON = "0";
    public final static String EMPLOYEE_ON_THE_JOB_OFF = "1";

    /** 用户状态 */
    /**正常*/
    public final static String EMPLOYEE_STATUS_ON = "0";
    /**停用*/
    public final static String EMPLOYEE_STATUS_OFF = "1";

    /** Payment Notice 检查状态 */
    /**未检查*/
    public final static String PAYMENT_NOTICE_CHECK_STATUS_NONE = "00";
    /**已检查*/
    public final static String PAYMENT_NOTICE_CHECK_STATUS_COMPLETE = "01";

    /** 用户默认密码 */
    public final static String EMPLOYEE_PASSWORD = "123456";

    /** 是否要发邮件通知 */
    public final static String EMAIL_NOTIFICATION_ON = "on";

    /** 证件号码的后6位长度 */
    public final static int EMPLOYEE_ID_NUM_6 = 6;

    /** 普通用户 */
    public final static Long EMPLOYEE_POST = 4L;
    /** 一般使用者 */
    public final static Long EMPLOYEE_ROLE = 101L;

    /** 角色_CRM */
    public final static String ROLE_ID_CRM = "2";

    /** 部门_HRONE */
    public final static String DEPT_ID_HRONE = "101";

    /** 提醒类型_合同到期 */
    public final static String REMIND_TYPE_EXPIRY_CONTRACT = "1";

    /** 提醒类型_合同日期空白 */
    public final static String REMIND_TYPE_DATED_BLANK_CONTRACT = "2";

    /** 提醒类型_退休提醒 */
    public final static String REMIND_TYPE_RETIRE = "3";

    /** 提醒类型_生日提醒 */
    public final static String REMIND_TYPE_BIRTHDAY = "4";

    /** 雇员雇主导入模版名 */
    public final static String IMPORT_TEMPLATE_FILENAME_EMPLOYEE = "雇员雇主导入模版.xlsx";

    /** payment_received名称FDI */
    public final static String PAYMENT_RECEIVED_FDI = "FDI";

    /** Service_Contract_Party_B 名称TOP FDI*/
    public final static String Service_Contract_Party_B_TOP_FDI="TOP FDI";
    public final static String Service_Contract_Party_B_SH="HROne SH";
    public final static String Service_Contract_Party_B_BJ="HROne BJ";


    /** Client Source HRHK*/
    public final static String Client_Source_HK="HRHK";
    public final static String Client_Source_TopFDI="Top FDI";


    /** Payment Notice C版模版名(标准模板) */
    public final static String EXPORT_TEMPLATE_FILENAME_PN = "template/HROne Payment Notice-template_S.xlsx";
    /** SH Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_SH = "template/HROne SH Payment Notice-template_S.xlsx";
    /** BJ Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_BJ = "template/HROne BJ Payment Notice-template_S.xlsx";

    /** BJ-HK Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_BJ_HK = "template/HROne BJ_HROneHK Payment Notice-template_S.xlsx";
    /** SH-HK Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_SH_HK = "template/HROne SH_HROneHK Payment Notice-template_S.xlsx";


    /** BJ-HK Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_BJ_TOPFDI = "template/HROne BJ_TOPFDI Payment Notice-template_S.xlsx";
    /** SH-HK Payment Notice C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_SH_TOPFDI = "template/HROne SH_TOPFDI Payment Notice-template_S.xlsx";




    /** Payment Notice FDI C版模版名(指定公司) */
    public final static String EXPORT_TEMPLATE_FDI_FILENAME_PN_CLIENT = "template/FDI Payment Notice-template_S_#0#.xlsx";



    /** Payment Notice TOP FDI C版模版名 */
    public final static String EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN = "template/TOP FDI Payment Notice-template_S.xlsx";


    /** Payment Notice TOP FDI C版模版名(指定公司)*/
    public final static String EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CLIENT = "template/TOP FDI Payment Notice-template_S_#0#.xlsx";

    /** Payment Notice TOP FDI - Consultant  C版模版名 */
    public final static String EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CONSULTANT = "template/TOP FDI Payment Notice-template-Consultant.xlsx";

    /** Payment Notice TOP FDI C版模版名(指定公司)*/
    public final static String EXPORT_TEMPLATE_TOP_FDI_FILENAME_PN_CONSULTANT_CLIENT = "template/TOP FDI Payment Notice-template-Consultant_#0#.xlsx";


    /** Payment Notice FDI C版模版名 */
    public final static String EXPORT_TEMPLATE_FDI_FILENAME_PN = "template/FDI Payment Notice-template_S.xlsx";

    /** Payment Notice C版模版名(顾问公司) */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT = "template/HROne Payment Notice-template.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT_SH = "template/HROne SH Payment Notice-template.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CONSULTANT_BJ = "template/HROne BJ Payment Notice-template.xlsx";

    /** Payment Notice C版模版名(指定公司) */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT = "template/HROne Payment Notice-template_S_#0#.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT_SH = "template/HROne SH Payment Notice-template_S_#0#.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT_BJ = "template/HROne BJ Payment Notice-template_S_#0#.xlsx";


    /** Payment Notice C版模版名(指定顾问公司) */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT = "template/HROne Payment Notice-template_#0#.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT_SH = "template/HROne SH Payment Notice-template_#0#.xlsx";
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CLIENT_CONSULTANT_BJ = "template/HROne BJ Payment Notice-template_#0#.xlsx";




    /** Payment Notice CIEE专属C版模版名 */
    public final static String EXPORT_TEMPLATE_FILENAME_PN_CIEE = "template/HROne Payment Notice-CIEE.xlsx";


    /** Payment Notice SH--HK Consultant模板*/
    public final static String EXPORT_TEMPLATE_FILENAME_PN_Consultant_SH_HK = "template/HROne SH_HROneHK Payment Notice-template.xlsx";
    /** Payment Notice BJ--HK Consultant模板*/
    public final static String EXPORT_TEMPLATE_FILENAME_PN_Consultant_BJ_HK = "template/HROne BJ_HROneHK Payment Notice-template.xlsx";


    /** Payment Notice SH--TopFDI 专属模板*/
    public final static String EXPORT_TEMPLATE_FILENAME_PN_Consultant_SH_TopFDI = "template/HROne SH_TOPFDI Payment Notice-template.xlsx";
    /** Payment Notice BJ--TopFDI 专属模板*/
    public final static String EXPORT_TEMPLATE_FILENAME_PN_Consultant_BJ_TopFDI = "template/HROne BJ_TOPFDI Payment Notice-template.xlsx";



    /** One Time Service */
    public final static String EXPORT_TEMPLATE_FILENAME_ONE_TIME_SERVICE_FEE = "template/HROne Payment Notice One Time Service.xlsx";

    /**邮件发送模式 0表示允许发生*/
    public final static String MAIL_MODE = "0";

    /** 邮件通知*/
    public final static String MAIL_TITLE ="邮件通知(Notification)";

    /** 邮件通知-员工合同到期提醒 */
    public final static String MAIL_TITLE_REMIND ="Employee contract expiration reminder - 员工合同到期提醒";

    /** 邮件通知-半年内退休员工提醒 */
    public final static String MAIL_TITLE_RETIRE ="员工半年内退休提醒";

    /** 邮件通知-paystub平台员工信息缺失提醒 */
    public final static String MAIL_TITLE_REMIND_MISS_EMPLOYEE ="paystub平台员工信息缺失提醒";

    /** 邮件字符集*/
    public final static String MAIL_CHARSET ="UTF-8";

    /** 邮件编码*/
    public final static String MAIL_ENCODING ="Q";

    /** HRONE-IT*/
    public final static String MAIL_DEPT_IT ="HRONE-IT";

    /** 月初提醒合同到期员工邮件附件名 */
    public final static String MAIL_FILE_NAME_REMIND ="Contract_End_Employee_List";

    /** 月初提醒半年内退休员工邮件附件名 */
    public final static String MAIL_FILE_NAME_RETIRE ="Retire_Employee_List";

    /** 缺失员工数据提醒邮件附件名 */
    public final static String MAIL_FILE_NAME_REMIND_MISS_EMPLOYEE ="Missing_Employee_List";

    /** template_1*/
    public final static String MAIL_TEMPLATE_FILE1 ="templates/mail/employer-template.html";

    /** template_2*/
    public final static String MAIL_TEMPLATE_FILE2 ="templates/mail/payslip-template.html";

    /** template_3*/
    public final static String MAIL_TEMPLATE_FILE3 ="templates/mail/employee-template.html";

    /** template_4*/
    public final static String MAIL_TEMPLATE_FILE4 ="templates/mail/payslip-template_1.html";

    /** 雇员账号密码通知邮件 */
    public final static String MAIL_TEMPLATE_FILE5 ="templates/mail/employee_password-template.html";

    /** 用户登陆发送验证码邮件 */
    public final static String MAIL_TEMPLATE_FILE6 ="templates/mail/login_dynamic_code-template.html";

    /** 月初提醒合同到期员工邮件 */
    public final static String MAIL_TEMPLATE_FILE7 ="templates/mail/remind_contact_end-template.html";

    /** 月初提醒退休员工邮件 */
    public final static String MAIL_TEMPLATE_FILE8 ="templates/mail/remind_retire-template.html";

    /** 月初提醒平台缺失数据的员工邮件 */
    public final static String MAIL_TEMPLATE_FILE9 ="templates/mail/remind_missing_employee-template.html";

    /** 每天提醒汇率爬虫的员工邮件 */
    public final static String MAIL_TEMPLATE_FILE10 = "templates/mail/remind_missing_exchangeRate-template.html";

    /** 月初提醒合同到期员工邮件 */
    public final static String MAIL_TEMPLATE_FILE11 ="templates/mail/remind_contact_end_for_client-template.html";

    /** 上传文件后缀*/
    public final static String UPLOAD_FILE_SUFFIX ="xls,xlsx,xlsm";

    /** 另存为类型*/
    /** 需要*/
    public final static String SAVE_AS_NEED ="1";
    /** 不需要*/
    public final static String SAVE_AS_NOT_NEEDED ="0";

    /**#REF!*/
    public final static String REF_STR = "#REF!";

    /** Other Service Fee名称 */
    /** Expense claim service fee 报销服务费 */
    public final static String OTHER_SERVICE_FEE_NAME1 = "Expense claim service fee 报销服务费";

    /** Visa/Hukou Residence Permit 签证&户口 */
    public final static String OTHER_SERVICE_FEE_NAME2 = "Visa/Hukou Residence Permit 签证&户口";

    /** Field service 外勤服务 */
    public final static String OTHER_SERVICE_FEE_NAME3 = "Field service 外勤服务";

    /** Reference Check 背景调查 */
    public final static String OTHER_SERVICE_FEE_NAME4 = "Reference Check 背景调查";

    /** Office Space 办公室租赁服务 */
    public final static String OTHER_SERVICE_FEE_NAME5 = "Office Space 办公室租赁服务";

    /** Personal tax collection&bookkeeping service 个税汇缴及代理记账服务 */
    public final static String OTHER_SERVICE_FEE_NAME6 = "Personal tax collection&bookkeeping service 个税汇缴及代理记账服务";

    /** Company formation 公司注册 */
    public final static String OTHER_SERVICE_FEE_NAME7 = "Company formation 公司注册";

    /** 其他 Others  */
    public final static String OTHER_SERVICE_FEE_NAME8 = "other";

    /** Advance Payment Fee 预付费 */
    public final static String OTHER_SERVICE_FEE_NAME9 = "Advance Payment Fee 预付费";

    /** Exchange rate  */
    public final static String EXCEL_KEYWORD_EXCHANGE_RATE = "Exchange rate";
    public final static String EXCEL_KEYWORD_EXCHANGE_RATE_CN = "汇率";



    /** Client_Employee_Source */


}
