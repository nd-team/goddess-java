package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.DataType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FieldBO extends BaseBO {
    /**
     * 列名称
     */
    private String name;
    /**
     * 序列
     */
    private Integer seq;
    /**
     * 是否必填
     */
    private Boolean need;
    /**
     * 数据类型
     */
    private DataType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Boolean isNeed() {
        return need;
    }

    public void setNeed(Boolean need) {
        this.need = need;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
