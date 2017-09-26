package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 11:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemTypeBO extends BaseBO {
    /**
     * 类型名
     */
    private String name;
    /**
     * 是否开启
     */
    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
