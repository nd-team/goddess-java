package com.bjike.goddess.task.vo;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.DataType;

import java.io.Serializable;

/**
 * 列
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FieldVO implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 列名称
     */
    private String name;

    /**
     * 节点名称
     */
    private String node;

    /**
     * 是否必填
     */
    private boolean need;
    /**
     * 数据类型
     */
    private DataType dataType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public boolean isNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
