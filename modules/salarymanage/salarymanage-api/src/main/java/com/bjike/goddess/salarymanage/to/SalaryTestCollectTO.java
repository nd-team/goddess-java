package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* 薪资测算汇总表
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryTestCollectTO extends BaseTO {
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空!", groups = {ADD.class, EDIT.class})
    private String  area;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空!", groups = {ADD.class, EDIT.class})
    private String  department;

    /**
     * 业务方向
     */
    @NotBlank(message = "业务方向不能为空!", groups = {ADD.class, EDIT.class})
    private String  businessDirection;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空!", groups = {ADD.class, EDIT.class})
    private String  position;

    /**
     * 技能
     */
    @NotBlank(message = "技能不能为空!", groups = {ADD.class, EDIT.class})
    private String  skill;

    /**
     * 工作年限
     */
    @NotBlank(message = "工作年限不能为空!", groups = {ADD.class, EDIT.class})
    private String  workAge;

    /**
     * 最后薪资测试参考标准
     */
    @NotNull(message = "最后薪资测试参考标准不能为空", groups = {ADD.class, EDIT.class})
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