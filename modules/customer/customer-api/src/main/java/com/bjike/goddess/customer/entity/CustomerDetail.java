package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 客户详细信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.466 ]
 * @Description: [ 客户详细信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_customerdetail")
public class CustomerDetail extends BaseEntity {

    /**
     * 客户信息编号
     */
    @Column(name = "customerNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户信息编号'")
    private String customerNum;

    /**
     * 年龄
     */
    @Range(min = 0, max = 120)
    @Column(columnDefinition = "TINYINT COMMENT '年龄' ")
    private Integer age;

    /**
     * 出生年月日
     */
    @Column(name = "birthday",  columnDefinition = "DATE   COMMENT '出生年月日'")
    private LocalDate birthday;

    /**
     * 工作经历
     */
    @Column(name = "workExperience",  columnDefinition = "TEXT   COMMENT '工作经历'")
    private String workExperience;

    /**
     * 求学经历
     */
    @Column(name = "studyExperience", columnDefinition = "TEXT  COMMENT '求学经历'")
    private String studyExperience;

    /**
     * 爱好
     */
    @Column(name = "love",  columnDefinition = "TEXT   COMMENT '爱好'")
    private String love;

    /**
     * 性格评价
     */
    @Column(name = "CharacterEvaluation",  columnDefinition = "TEXT   COMMENT '性格评价'")
    private String CharacterEvaluation;


    /**
     * 客户基本信息
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH )
    @JoinColumn(name = "customerBaseInfo_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '客户基本信息'")
    private CustomerBaseInfo customerBaseInfo;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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
        return CharacterEvaluation;
    }

    public void setCharacterEvaluation(String CharacterEvaluation) {
        this.CharacterEvaluation = CharacterEvaluation;
    }

    public CustomerBaseInfo getCustomerBaseInfo() {
        return customerBaseInfo;
    }

    public void setCustomerBaseInfo(CustomerBaseInfo customerBaseInfo) {
        this.customerBaseInfo = customerBaseInfo;
    }
}