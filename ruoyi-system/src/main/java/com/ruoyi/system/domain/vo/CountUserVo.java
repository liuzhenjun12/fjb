package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class CountUserVo implements Serializable {
    @Excel(name = "机构名称",  width = 20,type = Excel.Type.EXPORT)
    private String jigou;//机构名称
    @Excel(name = "报名总数",  width = 20,type = Excel.Type.EXPORT)
    private Integer zongshu;//报名总人数
    @Excel(name = "成功数量",  width = 20,type = Excel.Type.EXPORT)
    private Integer shi;//报名成功数
    @Excel(name = "失败数量",  width = 20,type = Excel.Type.EXPORT)
    private Integer bu;//未成功数量
    @Excel(name = "理论合格数量",  width = 20,type = Excel.Type.EXPORT)
    private Integer liluenshi;
    @Excel(name = "理论不合格数",  width = 20,type = Excel.Type.EXPORT)
    private Integer liluenbu;
    @Excel(name = "理论缺考数量",  width = 20,type = Excel.Type.EXPORT)
    private Integer liluenque;
    @Excel(name = "实操合格数量",  width = 20,type = Excel.Type.EXPORT)
    private Integer shichaoshi;
    @Excel(name = "理论合格率",  width = 20,type = Excel.Type.EXPORT)
    private String liluenhegeli;
    @Excel(name = "实操合格率",  width = 20,type = Excel.Type.EXPORT)
    private String shicaohegeli;
    @Excel(name = "考试日期",  width = 20,dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private String kaoshitime;

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

    public Integer getLiluenshi() { return liluenshi; }

    public void setLiluenshi(Integer liluenshi) { this.liluenshi = liluenshi; }

    public Integer getLiluenbu() { return liluenbu; }

    public void setLiluenbu(Integer liluenbu) { this.liluenbu = liluenbu; }

    public Integer getLiluenque() { return liluenque; }

    public void setLiluenque(Integer liluenque) { this.liluenque = liluenque; }

    public Integer getShichaoshi() { return shichaoshi; }

    public void setShichaoshi(Integer shichaoshi) { this.shichaoshi = shichaoshi; }

    public String getLiluenhegeli() { return liluenhegeli; }

    public void setLiluenhegeli(String liluenhegeli) { this.liluenhegeli = liluenhegeli; }

    public String getShicaohegeli() { return shicaohegeli; }

    public void setShicaohegeli(String shicaohegeli) { this.shicaohegeli = shicaohegeli; }

    public String getKaoshitime() { return kaoshitime; }

    public void setKaoshitime(String kaoshitime) { this.kaoshitime = kaoshitime; }
}
