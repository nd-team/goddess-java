package com.bjike.goddess.projectcost.bo;

import java.io.Serializable;

/**
 * 项目成本控制柱状图业务传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-05-09 17:51]
 * @Description: [ 项目成本控制柱状图业务传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HistogramBO implements Serializable {

    private String[] fields;

    private String[] values;

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
