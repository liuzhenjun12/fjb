package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;

public class PicList implements Serializable {
    private String date;//考试日期
    private String cutoffTime;
    private String examName;//考试名称
    private String showTime;//分配显示日期
    private String distributionDate;//分配显示日期
    private String tempSwitch;
    private String PBExamID;
    private String type;//临柜或者清分
    private String terminalTime;//录入通道关闭时间
    private String mainSwitch;//是否生效
    private String bankNO;//机构代码
    private String id;//考试日期id
    private String state;
    private String auditDeadlineDateTime;//审核关闭时间
    private String certificateType;//考试类型

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCutoffTime() {
        return cutoffTime;
    }

    public void setCutoffTime(String cutoffTime) {
        this.cutoffTime = cutoffTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getTempSwitch() {
        return tempSwitch;
    }

    public void setTempSwitch(String tempSwitch) {
        this.tempSwitch = tempSwitch;
    }

    public String getPBExamID() {
        return PBExamID;
    }

    public void setPBExamID(String PBExamID) {
        this.PBExamID = PBExamID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTerminalTime() {
        return terminalTime;
    }

    public void setTerminalTime(String terminalTime) {
        this.terminalTime = terminalTime;
    }

    public String getMainSwitch() {
        return mainSwitch;
    }

    public void setMainSwitch(String mainSwitch) {
        this.mainSwitch = mainSwitch;
    }

    public String getBankNO() {
        return bankNO;
    }

    public void setBankNO(String bankNO) {
        this.bankNO = bankNO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuditDeadlineDateTime() {
        return auditDeadlineDateTime;
    }

    public void setAuditDeadlineDateTime(String auditDeadlineDateTime) {
        this.auditDeadlineDateTime = auditDeadlineDateTime;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
}
