package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 客户详细信息业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.468 ]
 * @Description: [ 客户详细信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerDetailBO extends BaseBO {

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
     * 毕业学校
     */
    private String graduatedSchool;
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
    private CustomerBaseInfoBO customerBaseInfoBO;

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

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
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

    public CustomerBaseInfoBO getCustomerBaseInfoBO() {
        return customerBaseInfoBO;
    }

    public void setCustomerBaseInfoBO(CustomerBaseInfoBO customerBaseInfoBO) {
        this.customerBaseInfoBO = customerBaseInfoBO;
    }

    public List<CusFamilyMemberBO> getCusFamilyMemberBOList() {
        return cusFamilyMemberBOList;
    }

    public void setCusFamilyMemberBOList(List<CusFamilyMemberBO> cusFamilyMemberBOList) {
        this.cusFamilyMemberBOList = cusFamilyMemberBOList;
    }
}