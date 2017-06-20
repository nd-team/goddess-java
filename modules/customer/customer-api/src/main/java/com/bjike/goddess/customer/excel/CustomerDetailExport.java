package com.bjike.goddess.customer.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;

import java.util.List;

/**
 * 客户详细信息业务传输对象导出
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.468 ]
 * @Description: [ 客户详细信息业务传输对象导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerDetailExport extends BaseBO {

    /**
     * 客户信息编号
     */
    private String customerNum;
//TODO 少了客户重要级别
    /**
     * 客户类别
     */
    private CustomerType customerType;

    /**
     * 客户状态
     */
    private CustomerStatus customerStatus;

    /**
     * 客户来源
     */
    private String origin;

    /**
     * 关系程度
     */
    private Double relation;

    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 性别
     */
    private CustomerSex customerSex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 地区
     */
    private String area;


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
     * 工作经历
     */
    private String workExperience;

    /**
     * 求学经历
     */
    private String studyExperience;

    /**
     * 爱好
     */
    private String love;

    /**
     * 性格评价
     */
    private String characterEvaluation;

    /**
     * 家庭信息集合
     */
    private List<CusFamilyMemberBO> cusFamilyMemberBOList;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getStudyExperience() {
        return studyExperience;
    }

    public void setStudyExperience(String studyExperience) {
        this.studyExperience = studyExperience;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getCharacterEvaluation() {
        return characterEvaluation;
    }

    public void setCharacterEvaluation(String characterEvaluation) {
        this.characterEvaluation = characterEvaluation;
    }

    public List<CusFamilyMemberBO> getCusFamilyMemberBOList() {
        return cusFamilyMemberBOList;
    }

    public void setCusFamilyMemberBOList(List<CusFamilyMemberBO> cusFamilyMemberBOList) {
        this.cusFamilyMemberBOList = cusFamilyMemberBOList;
    }
}