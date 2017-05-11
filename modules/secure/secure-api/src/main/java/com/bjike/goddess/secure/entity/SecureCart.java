package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 社保卡基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_securecart")
public class SecureCart extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeId", columnDefinition = "VARCHAR(36)   COMMENT '员工编号'")
    private String employeeId;

    /**
     * 社保卡管理分类
     */
    @Column(name = "cart", columnDefinition = "VARCHAR(255)   COMMENT '社保卡管理分类'")
    private String cart;

    /**
     * 地区
     */
    @Column(name = "arrival", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String arrival;

    /**
     * 社保帐户号码
     */
    @Column(name = "cartNum", columnDefinition = "VARCHAR(255)   COMMENT '社保帐户号码'")
    private String cartNum;

    /**
     * 领卡机构
     */
    @Column(name = "institutions", columnDefinition = "VARCHAR(255)   COMMENT '领卡机构'")
    private String institutions;

    /**
     * 物品状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '物品状态'")
    private String status;

    /**
     * 储存位置
     */
    @Column(name = "location", columnDefinition = "VARCHAR(255)   COMMENT '储存位置'")
    private String location;

    /**
     * 曾补办时间
     */
    @Column(name = "reapply", columnDefinition = "DATETIME   COMMENT '曾补办时间'")
    private String reapply;

    /**
     * 曾补办次数
     */
    @Column(name = "reapplyNum", columnDefinition = "INT(11)   COMMENT '曾补办次数'")
    private Integer reapplyNum;

    /**
     * 曾补办原因明细
     */
    @Column(name = "reason", columnDefinition = "VARCHAR(255)   COMMENT '曾补办原因明细'")
    private String reason;

    /**
     * 员工社保信息
     */
    @OneToOne(fetch = FetchType.EAGER/**, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}**/)
    @JoinColumn(name = "employeeSecure_id", nullable = false, unique = true, columnDefinition = "VARCHAR(36)   COMMENT '员工社保信息'")
    private EmployeeSecure employeeSecure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getInstitutions() {
        return institutions;
    }

    public void setInstitutions(String institutions) {
        this.institutions = institutions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReapply() {
        return reapply;
    }

    public void setReapply(String reapply) {
        this.reapply = reapply;
    }

    public Integer getReapplyNum() {
        return reapplyNum;
    }

    public void setReapplyNum(Integer reapplyNum) {
        this.reapplyNum = reapplyNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EmployeeSecure getEmployeeSecure() {
        return employeeSecure;
    }

    public void setEmployeeSecure(EmployeeSecure employeeSecure) {
        this.employeeSecure = employeeSecure;
    }
}