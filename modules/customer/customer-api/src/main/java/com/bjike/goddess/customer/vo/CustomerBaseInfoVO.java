package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.to.CustomerLevelTO;

/**
 * 客户基本信息表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.047 ]
 * @Description: [ 客户基本信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerBaseInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 客户信息编号
     */
    private String customerNum;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 地区
     */
    private String area;

    /**
     * 性别
     */
    private CustomerSex customerSex;

    /**
     * 客户类别
     */
    private CustomerType customerType;

    /**
     * 客户状态
     */
    private CustomerStatus customerStatus;

    /**
     * 关系程度
     */
    private Double relation;

    /**
     * 客户级别
     */
    private CustomerLevelVO customerLevelVO;

    /**
     * 客户来源
     */
    private String origin;

    /**
     * 介绍人
     */
    private String introducer;

    /**
     * 邮箱
     */
    private String cusEmail;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 座机
     */
    private String phone;

    /**
     * 微信
     */
    private String weChart;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 行业
     */
    private String workProfession;

    /**
     * 组织机构名称
     */
    private String origanizion;

    /**
     * 组织机构规模
     */
    private String origanizationSize;

    /**
     * 岗位
     */
    private String workPosition;

    /**
     * 职级
     */
    private String workLevel;

    /**
     * 职权
     */
    private String workRight;

    /**
     * 生活地区
     */
    private String lifeArea;

    /**
     * 成长地区
     */
    private String grouthArea;

    /**
     * 以往工作地区
     */
    private String oldWorkPlace;

    /**
     * 最近市场接待时间
     */
    private String marketReceptTime;


    /**
     * 最近一次更新人
     */
    private String modifyPersion;

    /**
     * 客户信息完成度
     */
    private String infoComplet;

    /**
     * 客户启用状态
     */
    private Status status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }

    public CustomerLevelVO getCustomerLevelVO() {
        return customerLevelVO;
    }

    public void setCustomerLevelVO(CustomerLevelVO customerLevelVO) {
        this.customerLevelVO = customerLevelVO;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeChart() {
        return weChart;
    }

    public void setWeChart(String weChart) {
        this.weChart = weChart;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWorkProfession() {
        return workProfession;
    }

    public void setWorkProfession(String workProfession) {
        this.workProfession = workProfession;
    }

    public String getOriganizion() {
        return origanizion;
    }

    public void setOriganizion(String origanizion) {
        this.origanizion = origanizion;
    }

    public String getOriganizationSize() {
        return origanizationSize;
    }

    public void setOriganizationSize(String origanizationSize) {
        this.origanizationSize = origanizationSize;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(String workLevel) {
        this.workLevel = workLevel;
    }

    public String getWorkRight() {
        return workRight;
    }

    public void setWorkRight(String workRight) {
        this.workRight = workRight;
    }

    public String getLifeArea() {
        return lifeArea;
    }

    public void setLifeArea(String lifeArea) {
        this.lifeArea = lifeArea;
    }

    public String getGrouthArea() {
        return grouthArea;
    }

    public void setGrouthArea(String grouthArea) {
        this.grouthArea = grouthArea;
    }

    public String getOldWorkPlace() {
        return oldWorkPlace;
    }

    public void setOldWorkPlace(String oldWorkPlace) {
        this.oldWorkPlace = oldWorkPlace;
    }

    public String getMarketReceptTime() {
        return marketReceptTime;
    }

    public void setMarketReceptTime(String marketReceptTime) {
        this.marketReceptTime = marketReceptTime;
    }

    public String getModifyPersion() {
        return modifyPersion;
    }

    public void setModifyPersion(String modifyPersion) {
        this.modifyPersion = modifyPersion;
    }

    public String getInfoComplet() {
        return infoComplet;
    }

    public void setInfoComplet(String infoComplet) {
        this.infoComplet = infoComplet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

}