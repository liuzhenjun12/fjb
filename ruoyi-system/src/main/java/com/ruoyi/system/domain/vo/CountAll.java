package com.ruoyi.system.domain.vo;

import java.io.Serializable;

public class CountAll implements Serializable {
    private Integer xueyuan;//测评人数
    private Integer hegers;//合格人数
    private Integer jine;//箱柜锁销售金额
    private Integer dingdan;//箱柜锁销售订单

    public Integer getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(Integer xueyuan) {
        this.xueyuan = xueyuan;
    }

    public Integer getHegers() {
        return hegers;
    }

    public void setHegers(Integer hegers) {
        this.hegers = hegers;
    }

    public Integer getJine() {
        return jine;
    }

    public void setJine(Integer jine) {
        this.jine = jine;
    }

    public Integer getDingdan() {
        return dingdan;
    }

    public void setDingdan(Integer dingdan) {
        this.dingdan = dingdan;
    }
}
