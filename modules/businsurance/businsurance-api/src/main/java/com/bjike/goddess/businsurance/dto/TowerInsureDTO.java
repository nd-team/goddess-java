package com.bjike.goddess.businsurance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 塔工意外险信息管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TowerInsureDTO extends BaseDTO {

    /**
     * 投保单号
     */
    private String insureNumber;

    /**
     * 投保人
     */
    private String insurer;


    /**
     * 被投保人
     */
    private String insureByPerson;

    /**
     * 是否续签(在保/不在保)
     */
    private String reSign;

    public String getInsureNumber() {
        return insureNumber;
    }

    public void setInsureNumber(String insureNumber) {
        this.insureNumber = insureNumber;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getInsureByPerson() {
        return insureByPerson;
    }

    public void setInsureByPerson(String insureByPerson) {
        this.insureByPerson = insureByPerson;
    }

    public String getReSign() {
        return reSign;
    }

    public void setReSign(String reSign) {
        this.reSign = reSign;
    }
}