package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;

public class YueList implements Serializable {
    private String auditDeadlineDateTime;//审核关闭时间
    private String certificateType;//考试类型
    private String checkFlag;//
    private String cutoffTime;
    private String date;//考试时间
    private String examName;//考试名称
    private Integer id;
    private String mainSwitch;//是否生效
    private String state;
    private String tempSwitch;
    private String terminalTime;//报名结束时间
    private String type;

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

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getCutoffTime() {
        return cutoffTime;
    }

    public void setCutoffTime(String cutoffTime) {
        this.cutoffTime = cutoffTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainSwitch() {
        return mainSwitch;
    }

    public void setMainSwitch(String mainSwitch) {
        this.mainSwitch = mainSwitch;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTempSwitch() {
        return tempSwitch;
    }

    public void setTempSwitch(String tempSwitch) {
        this.tempSwitch = tempSwitch;
    }

    public String getTerminalTime() {
        return terminalTime;
    }

    public void setTerminalTime(String terminalTime) {
        this.terminalTime = terminalTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
