package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

public class CountUserVo implements Serializable {
    @Excel(name = "机构名称",  width = 20,type = Excel.Type.EXPORT)
    private String jigou;//机构名称
    @Excel(name = "报名总数",  width = 10,type = Excel.Type.EXPORT)
    private Integer zongshu;//报名总人数
    @Excel(name = "成功数量",  width = 10,type = Excel.Type.EXPORT)
    private Integer shi;//报名成功数
    @Excel(name = "失败数量",  width = 10,type = Excel.Type.EXPORT)
    private Integer bu;//未成功数量
    private String pici;//批次
    private Long id;//机构id

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public Integer getZongshu() {
        return zongshu;
    }

    public void setZongshu(Integer zongshu) {
        this.zongshu = zongshu;
    }

    public Integer getShi() {
        return shi;
    }

    public void setShi(Integer shi) {
        this.shi = shi;
    }

    public Integer getBu() {
        return bu;
    }

    public void setBu(Integer bu) {
        this.bu = bu;
    }

    public String getPici() { return pici; }

    public void setPici(String pici) { this.pici = pici; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
