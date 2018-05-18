package com.bjike.goddess.shareholdersmanage.vo;

import java.time.LocalDate;

/**
 * 公允值变动表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FairChangeVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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