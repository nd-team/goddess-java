package com.bjike.goddess.secure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 员工社保基本信息业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeeSecureBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeNum;

    /**
     * 地区
     */
    private String arrival;

    /**
     * 项目组
     */
    private String team;

    /**
     * 参保单位
     */
    private String secureDepartment;

    /**
     * 身份证号码
     */
    private String idCart;

    /**
     * 身份证籍贯
     */
    private String idArrival;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 入职时间
     */
    private String startTime;

    /**
     * 转正时间
     */
    private String officialTime;

    /**
     * 前购买社保地市
     */
    private String beforeCity;

    /**
     * 前地市参保时间
     */
    private String beforeTime;

    /**
     * 参保户口
     */
    private String secureBorn;

    /**
     * 参保时间
     */
    private String secureTime;

    /**
     * 参保类型
     */
    private String secureType;

    /**
     * 购买方式
     */
    private String payType;

    /**
     * 已参保年限
     */
    private Double time;

    /**
     * 运营商务部意见
     */
    private String businessAdvice;

    /**
     * 总经办意见
     */
    private String managerAdvice;

    /**
     * 社保状态
     */
    private String status;

    /**
     * 备注
     */
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getGroup() {
        return team;
    }

    public void setGroup(String team) {
        this.team = team;
    }

    public String getSecureDepartment() {
        return secureDepartment;
    }

    public void setSecureDepartment(String secureDepartment) {
        this.secureDepartment = secureDepartment;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getIdArrival() {
        return idArrival;
    }

    public void setIdArrival(String idArrival) {
        this.idArrival = idArrival;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(String officialTime) {
        this.officialTime = officialTime;
    }

    public String getBeforeCity() {
        return beforeCity;
    }

    public void setBeforeCity(String beforeCity) {
        this.beforeCity = beforeCity;
    }

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getSecureBorn() {
        return secureBorn;
    }

    public void setSecureBorn(String secureBorn) {
        this.secureBorn = secureBorn;
    }

    public String getSecureTime() {
        return secureTime;
    }

    public void setSecureTime(String secureTime) {
        this.secureTime = secureTime;
    }

    public String getSecureType() {
        return secureType;
    }

    public void setSecureType(String secureType) {
        this.secureType = secureType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getBusinessAdvice() {
        return businessAdvice;
    }

    public void setBusinessAdvice(String businessAdvice) {
        this.businessAdvice = businessAdvice;
    }

    public String getManagerAdvice() {
        return managerAdvice;
    }

    public void setManagerAdvice(String managerAdvice) {
        this.managerAdvice = managerAdvice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}