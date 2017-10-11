package com.bjike.goddess.task.bo.custmize;


import com.bjike.goddess.task.enums.TitleType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-28 15:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomField {
    private String name; // 名称
    private String value; //值
    private TitleType titleType; //类型
    private String nodeId; //所属节点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
