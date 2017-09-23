package com.bjike.goddess.salarymanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 薪资测算汇总表
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "salarymanage_salarytestcollect")
public class SalaryTestCollect extends BaseEntity {
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
     * 岗位
     */
    @Column(name = "position",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  )
    private String  position;

    /**
     * 技能
     */
    @Column(name = "skill",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '技能'"  )
    private String  skill;

    /**
     * 工作年限
     */
    @Column(name = "workAge",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '工作年限'"  )
    private String  workAge;

    /**
     * 最后薪资测试参考标准
     */
    @Column(name = "consultStandard",columnDefinition = "VARCHAR(255)   COMMENT '最后薪资测试参考标准'"  )
    private Integer  consultStandard;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public Integer getConsultStandard() {
        return consultStandard;
    }

    public void setConsultStandard(Integer consultStandard) {
        this.consultStandard = consultStandard;
    }
}