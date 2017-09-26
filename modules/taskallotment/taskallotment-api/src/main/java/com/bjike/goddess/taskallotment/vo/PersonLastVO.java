package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 个人汇总最底层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 19:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PersonLastVO{
    /**
     * 姓名
     */
    private String name;

    /**
     * 工时差异（小时）
     */
    private Double differ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiffer() {
        return differ;
    }

    public void setDiffer(Double differ) {
        this.differ = differ;
    }
}
