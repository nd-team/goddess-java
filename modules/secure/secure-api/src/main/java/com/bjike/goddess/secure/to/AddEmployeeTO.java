package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 社保增员信息名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AddEmployeeTO extends BaseTO {

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
     * 岗位
     */
    @NotBlank(groups = {EDIT.class},message = "岗位不能为空")
    private String job;

    /**
     * 岗位层级
     */
    @NotBlank(groups = {EDIT.class},message = "岗位层级不能为空")
    private String jobLevel;

    /**
     * 身份证号码
     */
    @NotBlank(groups = {EDIT.class},message = "身份证号码不能为空")
    private String idCart;

    /**
     * 身份证籍贯
     */
    @NotBlank(groups = {EDIT.class},message = "身份证籍贯不能为空")
    private String arrival;

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
     * 参保公司
     */
    @NotBlank(groups = {EDIT.class},message = "参保公司不能为空")
    private String company;

    /**
     * 参保地市
     */
    @NotBlank(groups = {EDIT.class},message = "参保地市不能为空")
    private String secureCity;

    /**
     * 参保户口
     */
    @NotBlank(groups = {EDIT.class},message = "参保户口不能为空")
    private String bornLocal;

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
     * 参保记录
     */
    private String record;


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

    public String getGroup() {
        return team;
    }

    public void setGroup(String team) {
        this.team = team;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSecureCity() {
        return secureCity;
    }

    public void setSecureCity(String secureCity) {
        this.secureCity = secureCity;
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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}