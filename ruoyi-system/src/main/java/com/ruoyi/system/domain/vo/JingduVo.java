package com.ruoyi.system.domain.vo;

import java.io.Serializable;

public class JingduVo implements Serializable {
    private String name;//城市名称
    private Integer value=60;
    private Integer num;//订单数
    private Integer money;//订单总金额
    private Float jindu;//经度
    private Float weidu;//纬度

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Float getJindu() {
        return jindu;
    }

    public void setJindu(Float jindu) {
        this.jindu = jindu;
    }

    public Float getWeidu() {
        return weidu;
    }

    public void setWeidu(Float weidu) {
        this.weidu = weidu;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JingduVo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", num=" + num +
                ", money=" + money +
                ", jindu=" + jindu +
                ", weidu=" + weidu +
                '}';
    }
}
