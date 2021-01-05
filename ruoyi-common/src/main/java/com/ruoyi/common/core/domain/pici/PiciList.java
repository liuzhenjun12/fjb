package com.ruoyi.common.core.domain.pici;

import java.io.Serializable;

public class PiciList implements Serializable {
    private String date;
    private String cutoffTime;
    private String examName;
    private String distributionDate;
    private String showTime;
    private String tempSwitch;
    private String type;
    private String terminalTime;
    private String mainSwitch;
    private String bankNO;
    private String id;
    private String state;
    private String auditDeadlineDateTime;
    private String certificateType;

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

    public String getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getTempSwitch() {
        return tempSwitch;
    }

    public void setTempSwitch(String tempSwitch) {
        this.tempSwitch = tempSwitch;
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
