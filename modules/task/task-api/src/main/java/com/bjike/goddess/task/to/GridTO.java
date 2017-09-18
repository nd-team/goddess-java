package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GridTO extends BaseTO {
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
