package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 公允值变动
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_fairchange")
public class FairChange extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 变动时间
     */
    @Column(name = "changDate", nullable = false, columnDefinition = "DATE   COMMENT '变动时间'")
    private LocalDate changDate;

    /**
     * 每股价格/元
     */
    @Column(name = "perSharePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股价格/元'")
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

    public LocalDate getChangDate() {
        return changDate;
    }

    public void setChangDate(LocalDate changDate) {
        this.changDate = changDate;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }
}