package com.bjike.goddess.secure.vo;

import java.io.Serializable;


/**
 * 社保购买情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SecureVO implements Serializable {

    /**
     * 参保单位
     */
    private String company;

    /**
     * 是否购买社保
     */
    private Boolean buySecure;

    /**
     * 参保类型
     */
    private String type;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getBuySecure() {
        return buySecure;
    }

    public void setBuySecure(Boolean buySecure) {
        this.buySecure = buySecure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}