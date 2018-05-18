package com.bjike.goddess.salarymanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.salarymanage.enums.WorkAge;

import javax.persistence.*;


/**
* 薪资测算明细表
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-16 10:45 ]
* @Description:	[ 薪资测算明细表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "salarymanage_salarycalculatedetail")
public class SalaryCalculateDetail extends BaseEntity {
    /**
     * 姓名
     */
    @Column(name = "userName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '姓名'"  )
    private String  userName;

    /**
     * 岗位
     */
    @Column(name = "position",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  )
    private String  position;

    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 项目组/部门
     */
    @Column(name = "department",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'"  )
    private String  department;

    /**
     * 业务方向
     */
    @Column(name = "businessDirection",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '业务方向'"  )
    private String  businessDirection;

    /**
     * 技能
     */
    @Column(name = "skill",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '技能'"  )
    private String  skill;

    /**
     * 工作年限
     */
    @Column(name = "workAge",nullable = false,columnDefinition = "TINYINT(3)  COMMENT '工作年限'"  )
    private WorkAge workAge;

    /**
     * 期望
     */
    @Column(name = "expectation",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '期望'"  )
    private Integer  expectation;

    /**
     * 智联招聘
     */
    @Column(name = "zhilian",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '智联招聘'"  )
    private Integer  zhilian;

    /**
     * 中华英才网
     */
    @Column(name = "zhonghua",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '中华英才网'"  )
    private Integer  zhonghua;

    /**
     * 猎聘网
     */
    @Column(name = "lieping",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '猎聘网'"  )
    private Integer  lieping;

    /**
     * 前程无忧
     */
    @Column(name = "wuyou",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '前程无忧'"  )
    private Integer  wuyou;

    /**
     * BOSS聘
     */
    @Column(name = "boss",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT 'BOSS聘'"  )
    private Integer  boss;



    public String getUserName () {
        return userName;
    }
    public void setUserName (String userName ) {
        this.userName = userName ;
    }
    public String getPosition () {
        return position;
    }
    public void setPosition (String position ) {
        this.position = position ;
    }
    public String getArea () {
        return area;
    }
    public void setArea (String area ) {
        this.area = area ;
    }
    public String getDepartment () {
        return department;
    }
    public void setDepartment (String department ) {
        this.department = department ;
    }
    public String getBusinessDirection () {
        return businessDirection;
    }
    public void setBusinessDirection (String businessDirection ) {
        this.businessDirection = businessDirection ;
    }
    public String getSkill () {
        return skill;
    }
    public void setSkill (String skill ) {
        this.skill = skill ;
    }

    public WorkAge getWorkAge() {
        return workAge;
    }

    public void setWorkAge(WorkAge workAge) {
        this.workAge = workAge;
    }

    public Integer getExpectation() {
        return expectation;
    }

    public void setExpectation(Integer expectation) {
        this.expectation = expectation;
    }

    public Integer getZhilian () {
        return zhilian;
    }
    public void setZhilian (Integer zhilian ) {
        this.zhilian = zhilian ;
    }
    public Integer getZhonghua () {
        return zhonghua;
    }
    public void setZhonghua (Integer zhonghua ) {
        this.zhonghua = zhonghua ;
    }
    public Integer getLieping () {
        return lieping;
    }
    public void setLieping (Integer lieping ) {
        this.lieping = lieping ;
    }
    public Integer getWuyou () {
        return wuyou;
    }
    public void setWuyou (Integer wuyou ) {
        this.wuyou = wuyou ;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }
}