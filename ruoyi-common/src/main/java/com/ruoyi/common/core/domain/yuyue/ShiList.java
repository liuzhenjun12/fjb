package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;

public class ShiList implements Serializable {
    private String beginTime;//开始时间
    private String cutoffTime;//结束时间
    private String examID;//日期id
    private Integer id;
    private String segOrderCount;//已预约数量
    private Integer segRemainCount;//剩余数量
    private String segmentFlag;//
    private String segmentID;//上下午标识
    private String segmentLimit;//最大数量
    private String sesOrderCount;//
    private Integer sesRemainCount;//可预约数量
    private String sessionFlag;
    private Boolean editing;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getCutoffTime() {
        return cutoffTime;
    }

    public void setCutoffTime(String cutoffTime) {
        this.cutoffTime = cutoffTime;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSegOrderCount() {
        return segOrderCount;
    }

    public void setSegOrderCount(String segOrderCount) {
        this.segOrderCount = segOrderCount;
    }

    public Integer getSegRemainCount() {
        return segRemainCount;
    }

    public void setSegRemainCount(Integer segRemainCount) {
        this.segRemainCount = segRemainCount;
    }

    public String getSegmentFlag() {
        return segmentFlag;
    }

    public void setSegmentFlag(String segmentFlag) {
        this.segmentFlag = segmentFlag;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }

    public String getSegmentLimit() {
        return segmentLimit;
    }

    public void setSegmentLimit(String segmentLimit) {
        this.segmentLimit = segmentLimit;
    }

    public String getSesOrderCount() {
        return sesOrderCount;
    }

    public void setSesOrderCount(String sesOrderCount) {
        this.sesOrderCount = sesOrderCount;
    }

    public Integer getSesRemainCount() {
        return sesRemainCount;
    }

    public void setSesRemainCount(Integer sesRemainCount) {
        this.sesRemainCount = sesRemainCount;
    }

    public String getSessionFlag() {
        return sessionFlag;
    }

    public void setSessionFlag(String sessionFlag) {
        this.sessionFlag = sessionFlag;
    }

    public Boolean getEditing() { return editing; }

    public void setEditing(Boolean editing) { this.editing = editing; }
}
