package com.fjb.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fjb.common.core.domain.entity.SysDept;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Cascader implements Serializable {
    /** 节点ID */
    private String value;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Cascader> children;

    public Cascader() { }

    public Cascader(SysDept dept)
    {
        this.value = dept.getDeptId().toString();
        this.label = dept.getJianCheng();
        this.children = dept.getChildren().stream().map(Cascader::new).collect(Collectors.toList());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Cascader> getChildren() {
        return children;
    }

    public void setChildren(List<Cascader> children) {
        this.children = children;
    }
}
