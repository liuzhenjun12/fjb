package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;
import java.util.List;

public class YueYue implements Serializable {
    private List<YueList> examSelected;
    private List<String> examUpdated;
    private List<String> examDeleted;

    public List<YueList> getExamSelected() {
        return examSelected;
    }

    public void setExamSelected(List<YueList> examSelected) {
        this.examSelected = examSelected;
    }

    public List<String> getExamUpdated() {
        return examUpdated;
    }

    public void setExamUpdated(List<String> examUpdated) {
        this.examUpdated = examUpdated;
    }

    public List<String> getExamDeleted() {
        return examDeleted;
    }

    public void setExamDeleted(List<String> examDeleted) {
        this.examDeleted = examDeleted;
    }
}
