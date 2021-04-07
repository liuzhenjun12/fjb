package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;

public class Pay implements Serializable {
    private String payTypeID;
    private String valueInfo;
    private Integer examID;
    private String examType;
    private String amSelectedCheck;
    private String amOrderCountEdit;
    private String pmSelectedCheck;
    private String pmOrderCountEdit;
    private String nmSelectedCheck;
    private String nmOrderCountEdit;
    private String serviceType;
    private String sessionChangeData;

    public String getPayTypeID() {
        return payTypeID;
    }

    public void setPayTypeID(String payTypeID) {
        this.payTypeID = payTypeID;
    }

    public String getValueInfo() {
        return valueInfo;
    }

    public void setValueInfo(String valueInfo) {
        this.valueInfo = valueInfo;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getAmSelectedCheck() {
        return amSelectedCheck;
    }

    public void setAmSelectedCheck(String amSelectedCheck) {
        this.amSelectedCheck = amSelectedCheck;
    }

    public String getAmOrderCountEdit() {
        return amOrderCountEdit;
    }

    public void setAmOrderCountEdit(String amOrderCountEdit) {
        this.amOrderCountEdit = amOrderCountEdit;
    }

    public String getPmSelectedCheck() {
        return pmSelectedCheck;
    }

    public void setPmSelectedCheck(String pmSelectedCheck) {
        this.pmSelectedCheck = pmSelectedCheck;
    }

    public String getPmOrderCountEdit() {
        return pmOrderCountEdit;
    }

    public void setPmOrderCountEdit(String pmOrderCountEdit) {
        this.pmOrderCountEdit = pmOrderCountEdit;
    }

    public String getNmSelectedCheck() {
        return nmSelectedCheck;
    }

    public void setNmSelectedCheck(String nmSelectedCheck) {
        this.nmSelectedCheck = nmSelectedCheck;
    }

    public String getNmOrderCountEdit() {
        return nmOrderCountEdit;
    }

    public void setNmOrderCountEdit(String nmOrderCountEdit) {
        this.nmOrderCountEdit = nmOrderCountEdit;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSessionChangeData() {
        return sessionChangeData;
    }

    public void setSessionChangeData(String sessionChangeData) {
        this.sessionChangeData = sessionChangeData;
    }
}
