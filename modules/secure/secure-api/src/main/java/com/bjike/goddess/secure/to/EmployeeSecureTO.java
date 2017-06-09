package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工社保基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmployeeSecureTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {EDIT.class},message = "姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {EDIT.class},message = "员工编号不能为空")
    private String employeeNum;

    /**
     * 地区
     */
    @NotBlank(groups = {EDIT.class},message = "地区不能为空")
    private String city;

    /**
     * 项目组
     */
    @NotBlank(groups = {EDIT.class},message = "项目组不能为空")
    private String team;

    /**
     * 参保单位
     */
    @NotBlank(groups = {EDIT.class},message = "参保单位不能为空")
    private String company;

    /**
     * 身份证号码
     */
    @NotBlank(groups = {EDIT.class},message = "身份证号码不能为空")
    private String idCart;

    /**
     * 身份证籍贯
     */
    @NotBlank(groups = {EDIT.class},message = "身份证籍贯不能为空")
    private String born;

    /**
     * 联系方式
     */
    @NotBlank(groups = {EDIT.class},message = "联系方式不能为空")
    private String tel;

    /**
     * 入职时间
     */
    @NotBlank(groups = {EDIT.class},message = "入职时间不能为空")
    private String startTime;

    /**
     * 转正时间
     */
    @NotBlank(groups = {EDIT.class},message = "转正时间不能为空")
    private String officialTime;

    /**
     * 前购买社保地市
     */
    @NotBlank(groups = {EDIT.class},message = "前购买社保地市不能为空")
    private String beforeCity;

    /**
     * 前地市参保时间
     */
    @NotBlank(groups = {EDIT.class},message = "前地市参保时间不能为空")
    private String beforeTime;

    /**
     * 参保户口
     */
    @NotBlank(groups = {EDIT.class},message = "参保户口不能为空")
    private String bornLocal;

    /**
     * 参保时间
     */
    @NotBlank(groups = {EDIT.class},message = "参保时间不能为空")
    private String secureTime;

    /**
     * 参保类型
     */
    @NotBlank(groups = {EDIT.class},message = "参保类型不能为空")
    private String type;

    /**
     * 购买方式
     */
    @NotBlank(groups = {EDIT.class},message = "购买方式不能为空")
    private String payType;

    /**
     * 已参保年限
     */
    @NotBlank(groups = {EDIT.class},message = "已参保年限不能为空")
    private String insuredYear;

    /**
     * 运营商务部意见
     */
    @NotBlank(groups = {EDIT.class},message = "运营商务部意见不能为空")
    private String businessAdvice;

    /**
     * 总经办意见
     */
    @NotBlank(groups = {EDIT.class},message = "总经办意见不能为空")
    private String managerAdvice;

    /**
     * 社保状态
     */
    @NotBlank(groups = {EDIT.class},message = "社保状态不能为空")
    private String status;

    /**
     * 备注
     */
    @NotBlank(groups = {EDIT.class},message = "备注不能为空")
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
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

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
    }

    public String getSecureTime() {
        return secureTime;
    }

    public void setSecureTime(String secureTime) {
        this.secureTime = secureTime;
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

    public String getInsuredYear() {
        return insuredYear;
    }

    public void setInsuredYear(String insuredYear) {
        this.insuredYear = insuredYear;
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