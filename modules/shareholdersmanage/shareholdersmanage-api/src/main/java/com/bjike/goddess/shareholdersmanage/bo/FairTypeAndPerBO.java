package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 公允值变动一个类型对应一个每股价格业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动一个类型对应一个每股价格业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FairTypeAndPerBO extends BaseBO {

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 每股价格/元
     */
    private Double perSharePrice;


    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }
}