package com.nnroad.system.constants;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

public enum MailEnum {

    COMPLETE_INFORMATION("Complete Your Information","templates/mail/complete-information-template.html", " CRM"),
    CREATE_ACCOUNT("Your Account/Password","templates/mail/create-account-template.html", " IT"),
    ONETIME_PASSWORD("Onetime  Password","templates/mail/onetime-password-template.html", " IT"),
    CALENDAR_REMIND("Calendar Remind", "templates/mail/employer-template.html" , " IT"),

    QuestionnaireDB("Customer Experience Survey","templates/mail/questionnaire.html", " CRM"),
    QuestionnaireCRM("HROne 2024 Customer Satisfaction Survey","templates/mail/questionnaire_1.html", " CRM"),
    COMPLETE_REGISTRATION("Employee Registration Completed","templates/mail/employee-registration-complete-template.html", "IT");
    private String title;

    private String template;

    private String support;

    private MailEnum(String title, String template, String support) {
        this.title = title;
        this.template = template;
        this.support = support;
    }

    public String getTitle() throws UnsupportedEncodingException {
        return MimeUtility.encodeWord(title,"UTF-8", "Q");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
