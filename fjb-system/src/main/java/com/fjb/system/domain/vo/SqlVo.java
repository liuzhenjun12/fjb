package com.fjb.system.domain.vo;

import com.fjb.common.annotation.Excel;

public class SqlVo {
    @Excel(name = "条件1")
    private String attr1;
    @Excel(name = "条件2")
    private String attr2;
    @Excel(name = "条件3")
    private Integer attr3;
    @Excel(name = "条件4")
    private String attr4;
    @Excel(name = "条件5")
    private String attr5;
    @Excel(name = "条件6")
    private String attr6;
    @Excel(name = "条件7")
    private String attr7;
    @Excel(name = "条件8")
    private float attr8;
    @Excel(name = "条件9")
    private float attr9;
    @Excel(name = "条件10")
    private String attr10;

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public Integer getAttr3() {
        return attr3;
    }

    public void setAttr3(Integer attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public float getAttr8() {
        return attr8;
    }

    public void setAttr8(float attr8) {
        this.attr8 = attr8;
    }

    public float getAttr9() {
        return attr9;
    }

    public void setAttr9(float attr9) {
        this.attr9 = attr9;
    }

    public String getAttr10() {
        return attr10;
    }

    public void setAttr10(String attr10) {
        this.attr10 = attr10;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SqlVo{");
        sb.append("attr1='").append(attr1).append('\'');
        sb.append(", attr2='").append(attr2).append('\'');
        sb.append(", attr3='").append(attr3).append('\'');
        sb.append(", attr4='").append(attr4).append('\'');
        sb.append(", attr5='").append(attr5).append('\'');
        sb.append(", attr6='").append(attr6).append('\'');
        sb.append(", attr7='").append(attr7).append('\'');
        sb.append(", attr8='").append(attr8).append('\'');
        sb.append(", attr9='").append(attr9).append('\'');
        sb.append(", attr10='").append(attr10).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
