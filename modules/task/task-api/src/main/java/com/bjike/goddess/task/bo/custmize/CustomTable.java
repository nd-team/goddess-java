package com.bjike.goddess.task.bo.custmize;

import java.util.List;

/**
 * 定制汇总表
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-28 15:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomTable {
    private String name; //表名
    private List<FixedField> fixedFields; //固定表头

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FixedField> getFixedFields() {
        return fixedFields;
    }

    public void setFixedFields(List<FixedField> fixedFields) {
        this.fixedFields = fixedFields;
    }
}
