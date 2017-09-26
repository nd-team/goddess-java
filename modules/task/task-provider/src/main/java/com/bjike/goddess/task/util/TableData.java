package com.bjike.goddess.task.util;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 11:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TableData {
    private Object field;
    private Object rows;

    public TableData(Object field, Object rows) {
        this.field = field;
        this.rows = rows;
    }

    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
