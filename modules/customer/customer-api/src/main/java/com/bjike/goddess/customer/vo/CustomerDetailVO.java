package com.bjike.goddess.customer.vo;

import java.util.List;

/**
 * 客户详细信息表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.470 ]
 * @Description: [ 客户详细信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerDetailVO {

    /**
     * id
     */
    private String id;

    /**
     * 客户信息编号
     */
    private String customerNum;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生年月日
     */
    private String birthday;

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
     * 家庭成员
     */
    private CusFamilyMemberVO cusFamilyMemberVO;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 客户基本信息
     */
    private CustomerBaseInfoVO customerBaseInfoVO;

    /**
     * 客户基本信息集合
     */
    private List<CusFamilyMemberVO> cusFamilyMemberVOList;

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

    public CusFamilyMemberVO getCusFamilyMemberVO() {
        return cusFamilyMemberVO;
    }

    public void setCusFamilyMemberVO(CusFamilyMemberVO cusFamilyMemberVO) {
        this.cusFamilyMemberVO = cusFamilyMemberVO;
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

    public CustomerBaseInfoVO getCustomerBaseInfoVO() {
        return customerBaseInfoVO;
    }

    public void setCustomerBaseInfoVO(CustomerBaseInfoVO customerBaseInfoVO) {
        this.customerBaseInfoVO = customerBaseInfoVO;
    }

    public List<CusFamilyMemberVO> getCusFamilyMemberVOList() {
        return cusFamilyMemberVOList;
    }

    public void setCusFamilyMemberVOList(List<CusFamilyMemberVO> cusFamilyMemberVOList) {
        this.cusFamilyMemberVOList = cusFamilyMemberVOList;
    }
}