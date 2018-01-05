package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 购买社保人员
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_buy")
public class Buy extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 参保公司
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '参保公司'")
    private String company;

    /**
     * 参保地市
     */
    @Column(name = "city", columnDefinition = "VARCHAR(255)   COMMENT '参保地市'")
    private String city;

    /**
     * 参保户口
     */
    @Column(name = "bornLocal", columnDefinition = "VARCHAR(255)   COMMENT '参保户口'")
    private String bornLocal;

    /**
     * 参保类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '参保类型'")
    private String type;

    /**
     * 购买方式
     */
    @Column(name = "payType", columnDefinition = "VARCHAR(255)   COMMENT '购买方式'")
    private String payType;

    /**
     * 社保状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '社保状态'")
    private String status;

    /**
     * 审批状态
     */
    @Column(name = "examine", columnDefinition = "TINYINT(1)  DEFAULT 0   COMMENT '审批状态'")
    private Boolean examine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isExamine() {
        return examine;
    }

    public void setExamine(Boolean examine) {
        this.examine = examine;
    }
}