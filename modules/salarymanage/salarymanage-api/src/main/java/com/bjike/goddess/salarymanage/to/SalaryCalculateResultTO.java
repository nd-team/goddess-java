package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.salarymanage.enums.WorkAge;

/**
* 薪资测算结果
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryCalculateResultTO extends BaseTO {

    /**
     * 地区
     */
    private String  area;

    /**
     * 项目组/部门
     */
    private String  department;

    /**
     * 业务方向
     */
    private String  businessDirection;

    /**
     * 技能
     */
    private String  skill;

    /**
     * 岗位
     */
    private String  position;

    /**
     * 工作年限
     */
    private WorkAge workAge;



    /**
     * 技能等级份额
     */
    private Integer  skillRankLot;



    /**
     * 管理等级份额
     */
    private Integer  manageRankLot;

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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public WorkAge getWorkAge() {
        return workAge;
    }

    public void setWorkAge(WorkAge workAge) {
        this.workAge = workAge;
    }

    public Integer getSkillRankLot() {
        return skillRankLot;
    }

    public void setSkillRankLot(Integer skillRankLot) {
        this.skillRankLot = skillRankLot;
    }

    public Integer getManageRankLot() {
        return manageRankLot;
    }

    public void setManageRankLot(Integer manageRankLot) {
        this.manageRankLot = manageRankLot;
    }
}