package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股权继承
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_equityinheritance")
public class EquityInheritance extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 继承人
     */
    @Column(name = "heir", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '继承人'")
    private String heir;

    /**
     * 被继承人
     */
    @Column(name = "beHeir", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被继承人'")
    private String beHeir;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 继承日期
     */
    @Column(name = "heirDate", nullable = false, columnDefinition = "DATE   COMMENT '继承日期'")
    private LocalDate heirDate;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getBeHeir() {
        return beHeir;
    }

    public void setBeHeir(String beHeir) {
        this.beHeir = beHeir;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public LocalDate getHeirDate() {
        return heirDate;
    }

    public void setHeirDate(LocalDate heirDate) {
        this.heirDate = heirDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}