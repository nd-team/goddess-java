package com.bjike.goddess.staffmove.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 人员调动实施
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveImplementATO extends BaseTO {
    public interface TESTEDIT{}

    /**
     * 提出需求时间
     */
    private String demandTime;

    /**
     * 需求发起人
     */
    private String demandPeople;

    /**
     * 发起地区
     */
    private String area;

    /**
     * 发起项目组/部门
     */
    private String projectGroup;

    /**
     * 发起姓名
     */
    private String name;

    /**
     * 人员编制情况
     */
    private String staffSituation;

    /**
     * 需求类型
     */
    private String demandType;

    /**
     * 调动原因类型
     */
    private String reassignment;

    /**
     * 调动原因描述
     */
    private String reasonDescription;

    /**
     * 需求人数
     */
    private Integer demandNum;

    /**
     * 调动人员要求
     */
    private String staffRequirement;

    /**
     * 掌握技能
     */
    private String masterSkill;

    /**
     * 特殊要求
     */
    private String specialRequirement;

    /**
     * 大概到岗时间
     */
    private String workTime;

    /**
     * 计划解决需求时间
     */
    @NotBlank(message = "计划解决需求时间不能为空",groups = {StaffMoveImplementATO.TESTEDIT.class})
    private String planSolveDemandTime;

    /**
     * 可调动人数
     */
    @NotNull(message = "可调动人数不能为空",groups = {StaffMoveImplementATO.TESTEDIT.class})
    private Integer mayTransferNum;
    /**
     * 人员调动B
     */
    private List<StaffMoveImplementBTO> staffMoveImplementBTOS;

    public List<StaffMoveImplementBTO> getStaffMoveImplementBTOS() {
        return staffMoveImplementBTOS;
    }

    public void setStaffMoveImplementBTOS(List<StaffMoveImplementBTO> staffMoveImplementBTOS) {
        this.staffMoveImplementBTOS = staffMoveImplementBTOS;
    }

    public String getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(String demandTime) {
        this.demandTime = demandTime;
    }

    public String getDemandPeople() {
        return demandPeople;
    }

    public void setDemandPeople(String demandPeople) {
        this.demandPeople = demandPeople;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffSituation() {
        return staffSituation;
    }

    public void setStaffSituation(String staffSituation) {
        this.staffSituation = staffSituation;
    }

    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getReassignment() {
        return reassignment;
    }

    public void setReassignment(String reassignment) {
        this.reassignment = reassignment;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public Integer getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(Integer demandNum) {
        this.demandNum = demandNum;
    }

    public String getStaffRequirement() {
        return staffRequirement;
    }

    public void setStaffRequirement(String staffRequirement) {
        this.staffRequirement = staffRequirement;
    }

    public String getMasterSkill() {
        return masterSkill;
    }

    public void setMasterSkill(String masterSkill) {
        this.masterSkill = masterSkill;
    }

    public String getSpecialRequirement() {
        return specialRequirement;
    }

    public void setSpecialRequirement(String specialRequirement) {
        this.specialRequirement = specialRequirement;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPlanSolveDemandTime() {
        return planSolveDemandTime;
    }

    public void setPlanSolveDemandTime(String planSolveDemandTime) {
        this.planSolveDemandTime = planSolveDemandTime;
    }

    public Integer getMayTransferNum() {
        return mayTransferNum;
    }

    public void setMayTransferNum(Integer mayTransferNum) {
        this.mayTransferNum = mayTransferNum;
    }
}