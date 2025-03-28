package com.nnroad.contract.domain;

import com.nnroad.common.core.domain.BaseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientCRMQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long fid;

    // 日期格式转换器
    private static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String fsurvey;

    private String freciverId;

    private String freciverName;

    private String contact;

    private String accountManager;

    private String mainServiceType;

    private String fother;

    private String fsubmitAt;

    // 邮件发送时间
    private String fsendAt;

    // 发送开始时间
    private String sendAtStart;

    // 发送结束时间
    private String sendAtEnd;

    private String fresult;

    private String freciverEmail;

    private String status;

    // 跟进时间
    private long ffollowedAt;

    // 跟进人
    private String ffollowedBy;

    // 分数
    private String score;

    // 查询分数
    private List<Integer> scores;

    // 评论状态
    private String remarkStatus;

    public String getFreciverEmail() {
        return freciverEmail;
    }

    public void setFreciverEmail(String freciverEmail) {
        this.freciverEmail = freciverEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClientCRMQuestion() {
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getFsurvey() {
        return fsurvey;
    }

    public void setFsurvey(String fsurvey) {
        this.fsurvey = fsurvey;
    }

    public String getFreciverId() {
        return freciverId;
    }

    public void setFreciverId(String freciverId) {
        this.freciverId = freciverId;
    }

    public String getFreciverName() {
        return freciverName;
    }

    public void setFreciverName(String freciverName) {
        this.freciverName = freciverName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getMainServiceType() {
        return mainServiceType;
    }

    public void setMainServiceType(String mainServiceType) {
        this.mainServiceType = mainServiceType;
    }

    public String getFother() {
        return fother;
    }

    public void setFother(String fother) {
        this.fother = fother;
    }

    public String getFsubmitAt() {
        return formatTimestamp(fsubmitAt);
    }

    public void setFsubmitAt(String fsubmitAt) {
        this.fsubmitAt = fsubmitAt;
    }

    public String getFsendAt() {
        return formatTimestamp(fsendAt);
    }

    public void setFsendAt(String fsendAt) {
        this.fsendAt = fsendAt;
    }

    public String getFresult() {
        return fresult;
    }

    public void setFresult(String fresult) {
        this.fresult = fresult;
    }

    public String getSendAtStart() {
        return convertDate(sendAtStart);
    }

    public void setSendAtStart(String sendAtStart) {
        this.sendAtStart = sendAtStart;
    }

    public String getSendAtEnd() {
        return convertDate(sendAtEnd);
    }

    public void setSendAtEnd(String sendAtEnd) {
        this.sendAtEnd = sendAtEnd;
    }

    public String getRemarkStatus() {
        return remarkStatus;
    }

    public void setRemarkStatus(String remarkStatus) {
        this.remarkStatus = remarkStatus;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFfollowedBy() {
        return ffollowedBy;
    }

    public void setFfollowedBy(String ffollowedBy) {
        this.ffollowedBy = ffollowedBy;
    }

    // 时间戳格式化方法
    private String formatTimestamp(String timestamp) {
        if (timestamp == null || timestamp.isEmpty()) {
            return "";
        }
        try {
            long timeInMillis = Long.parseLong(timestamp);
            Date date = new Date(timeInMillis);

            // 检查日期是否成功创建
            if (date != null) {
                return OUTPUT_FORMAT.format(date);
            } else {
                return "";
            }
        } catch (NumberFormatException e) {
            // 如果时间戳无法解析，返回原始时间戳或其他默认值
            System.err.println("Invalid timestamp format: " + timestamp);
            return "";
        }
    }

    // 转 字符型 时间戳
    private String timeToTimestamp(String time) {
        if (time == null || time.isEmpty()) {
            return "";
        }
        try{
            String timestampString = String.valueOf(OUTPUT_FORMAT.parse(time).getTime());
            if (timestampString != null) {
                return timestampString;
            } else {
                return "";
            }
        } catch (NumberFormatException e){
            System.err.println("Invalid timestamp format: " + time);
            return "";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // 时间转为 yyyy-MM-dd HH:mm:ss
    public static String convertDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return "";
        }
        try {
            Date date = INPUT_FORMAT.parse(dateStr);
            return OUTPUT_FORMAT.format(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + dateStr);
            return "";
        }
    }


    public long getFfollowedAt() {
        return ffollowedAt;
    }

    public void setFfollowedAt(long ffollowedAt) {
        this.ffollowedAt = ffollowedAt;
    }
}
