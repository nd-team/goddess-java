package com.bjike.goddess.moneyprepare.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-13 19:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProBO implements Serializable{
    /**
     * 项目名
     */
    private String name;
    /**
     * 项目组准备金
     */
    private Double fund;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }
}
