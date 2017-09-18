package com.bjike.goddess.task.vo;

import com.bjike.goddess.common.api.to.BaseTO;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GridVO implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 行
     */
    private String rowId;
    /**
     * 列
     */
    private String fieldId;
    /**
     * 值
     */
    private String valId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getValId() {
        return valId;
    }

    public void setValId(String valId) {
        this.valId = valId;
    }
}
