package com.bjike.goddess.customer.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.enums.Origin;

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
    @ExcelHeader(name = "客户信息编号",notNull = true)
    private String customerNum;

    /**
     * 客户级别
     */
    @ExcelHeader(name = "客户级别",notNull = true)
    private String level;
    /**
     * 客户类别
     */
    @ExcelHeader(name = "客户类别",notNull = true)
    private String customerType;

    /**
     * 客户状态
     */
    @ExcelHeader(name = "客户状态",notNull = true)
    private String customerStatus;

    /**
     * 客户来源
     */
    @ExcelHeader(name = "客户来源",notNull = true)
    private String origin;

    /**
     * 关系程度
     */
    @ExcelHeader(name = "关系程度",notNull = true)
    private Double relation;

    /**
     * 客户姓名
     */
    @ExcelHeader(name = "客户姓名",notNull = true)
    private String customerName;
    /**
     * 性别
     */
    @ExcelHeader(name = "性别",notNull = true)
    private String customerSex;

    /**
     * 年龄
     */
    @ExcelHeader(name = "年龄",notNull = true)
    private Integer age;

    /**
     * 出生年月日
     */
    @ExcelHeader(name = "出生年月日",notNull = true)
    private String birthday;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;


    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱",notNull = true)
    private String cusEmail;

    /**
     * 手机号
     */
    @ExcelHeader(name = "手机号",notNull = true)
    private String tel;

    /**
     * 座机
     */
    @ExcelHeader(name = "座机",notNull = true)
    private String phone;

    /**
     * 微信
     */
    @ExcelHeader(name = "微信",notNull = true)
    private String weChart;

    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号",notNull = true)
    private String qq;


    /**
     * 组织机构名称
     */
    @ExcelHeader(name = "组织机构名称",notNull = true)
    private String origanizion;

    /**
     * 组织机构规模
     */
    @ExcelHeader(name = "组织机构规模",notNull = true)
    private String origanizationSize;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String workPosition;

    /**
     * 职级
     */
    @ExcelHeader(name = "职级",notNull = true)
    private String workLevel;

    /**
     * 职权
     */
    @ExcelHeader(name = "职权",notNull = true)
    private String workRight;

    /**
     * 生活地区
     */
    @ExcelHeader(name = "生活地区",notNull = true)
    private String lifeArea;

    /**
     * 成长地区
     */
    @ExcelHeader(name = "成长地区",notNull = true)
    private String grouthArea;

    /**
     * 以往工作地区
     */
    @ExcelHeader(name = "以往工作地区",notNull = true)
    private String oldWorkPlace;

    /**
     * 工作经历
     */
    @ExcelHeader(name = "工作经历",notNull = true)
    private String workExperience;

    /**
     * 求学经历
     */
    @ExcelHeader(name = "求学经历",notNull = true)
    private String studyExperience;

    /**
     * 爱好
     */
    @ExcelHeader(name = "爱好",notNull = true)
    private String love;

    /**
     * 性格评价
     */
    @ExcelHeader(name = "性格评价",notNull = true)
    private String characterEvaluation;

    /**
     * 家庭成员称谓
     */
    @ExcelHeader(name = "家庭成员称谓",notNull = true)
    private String title;

    /**
     * 家庭成员姓名
     */
    @ExcelHeader(name = "家庭成员姓名",notNull = true)
    private String name;

    /**
     * 家庭成员联系方式
     */
    @ExcelHeader(name = "家庭成员联系方式",notNull = true)
    private String relationWay;

    /**
     * 家庭成员性格爱好
     */
    @ExcelHeader(name = "家庭成员性格爱好",notNull = true)
    private String charactLove;

    /**
     * 家庭成员工作单位
     */
    @ExcelHeader(name = "家庭成员工作单位",notNull = true)
    private String workPlace;

    /**
     * 家庭成员职位
     */
    @ExcelHeader(name = "家庭成员职位",notNull = true)
    private String jobPost;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
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

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationWay() {
        return relationWay;
    }

    public void setRelationWay(String relationWay) {
        this.relationWay = relationWay;
    }

    public String getCharactLove() {
        return charactLove;
    }

    public void setCharactLove(String charactLove) {
        this.charactLove = charactLove;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }
}