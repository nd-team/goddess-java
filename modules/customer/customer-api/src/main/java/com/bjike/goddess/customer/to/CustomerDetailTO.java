package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 客户详细信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.469 ]
 * @Description: [ 客户详细信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerDetailTO extends BaseTO {

    /**
     * 客户信息编号
     */
    @NotBlank(message = "客户编号不能为空")
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
    private CustomerBaseInfoTO customerBaseInfoTO;

    /**
     * 客户基本信息集合
     */
    private List<CusFamilyMemberTO> cusFamilyMemberTOList;

    /**
     * 客户基本信息集合
     */
    private String[] cusFamilyMemberTOLists;

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

    public CustomerBaseInfoTO getCustomerBaseInfoTO() {
        return customerBaseInfoTO;
    }

    public void setCustomerBaseInfoTO(CustomerBaseInfoTO customerBaseInfoTO) {
        this.customerBaseInfoTO = customerBaseInfoTO;
    }

    public List<CusFamilyMemberTO> getCusFamilyMemberTOList() {
        return cusFamilyMemberTOList;
    }

    public void setCusFamilyMemberTOList(List<CusFamilyMemberTO> cusFamilyMemberTOList) {
        this.cusFamilyMemberTOList = cusFamilyMemberTOList;
    }

    public String[] getCusFamilyMemberTOLists() {
        return cusFamilyMemberTOLists;
    }

    public void setCusFamilyMemberTOLists(String[] cusFamilyMemberTOLists) {
        this.cusFamilyMemberTOLists = cusFamilyMemberTOLists;
    }
}