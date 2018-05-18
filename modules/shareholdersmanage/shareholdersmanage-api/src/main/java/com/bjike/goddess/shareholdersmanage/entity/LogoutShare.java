package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.shareholdersmanage.type.TypeName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 注销股东
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_logoutshare")
public class LogoutShare extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 注销股东姓名
     */
    @Column(name = "logoutShareName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注销股东姓名'")
    private String logoutShareName;

    /**
     * 类型名称
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "INT(2)   COMMENT '类型名称'")
    private TypeName typeName;

    /**
     * 注销时间
     */
    @Column(name = "logoutDate", nullable = false, columnDefinition = "DATE   COMMENT '注销时间'")
    private LocalDate logoutDate;

    /**
     * 注销股数
     */
    @Column(name = "logoutHold", nullable = false, columnDefinition = "INT(11)   COMMENT '注销股数'")
    private Integer logoutHold;

    /**
     * 出资额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出资额'")
    private Double amount;

    /**
     * 出资方式
     */
    @Column(name = "capitalWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '出资方式'")
    private String capitalWay;

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

    public String getLogoutShareName() {
        return logoutShareName;
    }

    public void setLogoutShareName(String logoutShareName) {
        this.logoutShareName = logoutShareName;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    public LocalDate getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDate logoutDate) {
        this.logoutDate = logoutDate;
    }

    public Integer getLogoutHold() {
        return logoutHold;
    }

    public void setLogoutHold(Integer logoutHold) {
        this.logoutHold = logoutHold;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCapitalWay() {
        return capitalWay;
    }

    public void setCapitalWay(String capitalWay) {
        this.capitalWay = capitalWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}