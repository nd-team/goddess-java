package com.bjike.goddess.secure.vo;

import com.bjike.goddess.secure.enums.CartStatus;

/**
 * 社保卡基本信息表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SecureCartVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 社保卡管理分类
     */
    private CartStatus cartStatus;

    /**
     * 地区
     */
    private String arrival;

    /**
     * 社保帐户号码
     */
    private String cartNum;

    /**
     * 领卡机构
     */
    private String institutions;

    /**
     * 物品状态
     */
    private String status;

    /**
     * 储存位置
     */
    private String location;

    /**
     * 曾补办时间
     */
    private String reapply;

    /**
     * 曾补办次数
     */
    private Integer reapplyNum;

    /**
     * 曾补办原因明细
     */
    private String reason;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
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
}