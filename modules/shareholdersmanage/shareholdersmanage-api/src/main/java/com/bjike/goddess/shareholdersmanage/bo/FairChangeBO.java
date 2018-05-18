package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 公允值变动业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FairChangeBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 变动时间
     */
    private String changDate;

    /**
     * 每股价格/元
     */
    private Double perSharePrice;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getChangDate() {
        return changDate;
    }

    public void setChangDate(String changDate) {
        this.changDate = changDate;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }
}