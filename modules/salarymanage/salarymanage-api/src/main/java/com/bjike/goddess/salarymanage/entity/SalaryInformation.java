package com.bjike.goddess.salarymanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;
import java.time.LocalDate;


/**
* 薪资管理
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "salarymanage_salaryinformation")
public class SalaryInformation extends BaseEntity {
    /**
     * 计薪周期开始时间
     */
    @Column(name = "payStarTime",nullable = false,columnDefinition = "DATE   COMMENT '计薪周期开始时间'"  )
    private LocalDate payStarTime;

    /**
     * 计薪周期结束时间
     */
    @Column(name = "payEndTime",nullable = false,columnDefinition = "DATE   COMMENT '计薪周期结束时间'"  )
    private LocalDate  payEndTime;

    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 员工编号
     */
    @Column(name = "employeeId",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '员工编号'"  )
    private String  employeeId;

    /**
     * 姓名
     */
    @Column(name = "employeeName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '姓名'"  )
    private String  employeeName;

    /**
     * 体系
     */
    @Column(name = "system",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '体系'"  )
    private String  system;

    /**
     * 部门/项目组
     */
    @Column(name = "section",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'"  )
    private String  section;

    /**
     * 岗位
     */
    @Column(name = "station",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  )
    private String  station;

    /**
     * 岗位层级
     */
    @Column(name = "stationLevel",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'"  )
    private String  stationLevel;

    /**
     * 管理层级
     */
    @Column(name = "manageLevel",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '管理层级'"  )
    private String  manageLevel;

    /**
     * 技能项
     */
    @Column(name = "skill",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '技能项'"  )
    private String  skill;

    /**
     * 技能专业
     */
    @Column(name = "proSkills",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '技能专业'"  )
    private String  proSkills;

    /**
     * 技能级别
     */
    @Column(name = "skillLevel",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '技能级别'"  )
    private String  skillLevel;

    /**
     * 入职时间
     */
    @Column(name = "hiredate",nullable = false,columnDefinition = "DATE   COMMENT '入职时间'"  )
    private LocalDate  hiredate;

    /**
     * 转正时间
     */
    @Column(name = "positiveTime",nullable = false,columnDefinition = "DATE   COMMENT '转正时间'"  )
    private LocalDate  positiveTime;

    /**
     * 在职时间
     */
    @Column(name = "workingTime",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '在职时间'"  )
    private String  workingTime;

    /**
     * 基本工资
     */
    @Column(name = "basicSalary",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '基本工资'"  )
    private Double  basicSalary;

    /**
     * 岗位工资
     */
    @Column(name = "postSalary",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '岗位工资'"  )
    private Double  postSalary;

    /**
     * 技能工资
     */
    @Column(name = "skillPay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '技能工资'"  )
    private Double  skillPay;

    /**
     * 管理工资
     */
    @Column(name = "managePay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '管理工资'"  )
    private Double  managePay;

    /**
     * 技能职衔补助
     */
    @Column(name = "skillSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '技能职衔补助'"  )
    private Double  skillSubsidies;

    /**
     * 管理等级职衔补助
     */
    @Column(name = "manageSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '管理等级职衔补助'"  )
    private Double  manageSubsidies;

    /**
     * 工龄职衔补助
     */
    @Column(name = "seniorityLevSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '工龄职衔补助'"  )
    private Double  seniorityLevSubsidies;

    /**
     * 职衔补助总额
     */
    @Column(name = "allSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '职衔补助总额'"  )
    private Double  allSubsidies;

    /**
     * 项目津贴
     */
    @Column(name = "projectBenefits",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '项目津贴'"  )
    private Double  projectBenefits;

    /**
     * 定薪
     */
    @Column(name = "wage",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '定薪'"  )
    private Double  wage;

    /**
     * 工资
     */
    @Column(name = "salary",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '工资'"  )
    private Double  salary;

    /**
     * 电脑补助
     */
    @Column(name = "computerSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '电脑补助'"  )
    private Double  computerSubsidies;

    /**
     * 住宿补助
     */
    @Column(name = "accommodationSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '住宿补助'"  )
    private Double  accommodationSubsidies;

    /**
     * 工龄补助
     */
    @Column(name = "senioritySubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '工龄补助'"  )
    private Double  senioritySubsidies;

    /**
     * 高温补助
     */
    @Column(name = "hyperthermiaSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '高温补助'"  )
    private Double  hyperthermiaSubsidies;

    /**
     * 工资总额
     */
    @Column(name = "allSalary",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '工资总额'"  )
    private Double  allSalary;

    /**
     * 扣社保
     */
    @Column(name = "jinpoCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '扣社保'"  )
    private Double  jinpoCost;

    /**
     * 社保补助
     */
    @Column(name = "jinpoSubsidies",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '社保补助'"  )
    private Double  jinpoSubsidies;

    /**
     * 水电费
     */
    @Column(name = "utilities",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '水电费'"  )
    private Double  utilities;

    /**
     * 个税
     */
    @Column(name = "personTax",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '个税'"  )
    private Double  personTax;

    /**
     * 奖励处罚得分汇总
     */
    @Column(name = "allRewardScore",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '奖励处罚得分汇总'"  )
    private Double  allRewardScore;

    /**
     * 奖励处罚费用汇总
     */
    @Column(name = "allRewardCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '奖励处罚费用汇总'"  )
    private Double  allRewardCost;

    /**
     * 出勤天数
     */
    @Column(name = "attendanceDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '出勤天数'"  )
    private Double  attendanceDay;

    /**
     * 请假天数
     */
    @Column(name = "vacateDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '请假天数'"  )
    private Double  vacateDay;

    /**
     * 旷工天数
     */
    @Column(name = "absenteeismDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '旷工天数'"  )
    private Double  absenteeismDay;

    /**
     * 未完成任务工时
     */
    @Column(name = "unfinishedTime",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '未完成任务工时'"  )
    private Double  unfinishedTime;

    /**
     * 正常工作日加班天数
     */
    @Column(name = "normalOvertimeDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '正常工作日加班天数'"  )
    private Double  normalOvertimeDay;

    /**
     * 法定节假日实际休息天数
     */
    @Column(name = "legalRestDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '法定节假日实际休息天数'"  )
    private Double  legalRestDay;

    /**
     * 法定节假日加班天数
     */
    @Column(name = "legalOvertimeDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '法定节假日加班天数'"  )
    private Double  legalOvertimeDay;

    /**
     * 正常休息天数实际休息天数
     */
    @Column(name = "normalRestDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '正常休息天数实际休息天数'"  )
    private Double  normalRestDay;

    /**
     * 正常休息天数加班天数
     */
    @Column(name = "RestOvertimeDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '正常休息天数加班天数'"  )
    private Double  RestOvertimeDay;

    /**
     * 剩余加班天数
     */
    @Column(name = "surplusOvertimeDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '剩余加班天数'"  )
    private Double  surplusOvertimeDay;

    /**
     * 加班抵事假和其他假的天数
     */
    @Column(name = "offsetOvertime",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '加班抵事假和其他假的天数'"  )
    private Double  offsetOvertime;

    /**
     * 抵扣事假和其他假后剩余加班天数
     */
    @Column(name = "effectiveOvertime",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '抵扣事假和其他假后剩余加班天数'"  )
    private Double  effectiveOvertime;

    /**
     * 月工作日
     */
    @Column(name = "weekDays",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '月工作日'"  )
    private Double  weekDays;

    /**
     * 可享受带薪天数
     */
    @Column(name = "paidDay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '可享受带薪天数'"  )
    private Double  paidDay;

    /**
     * 备注
     */
    @Column(name = "remark",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '备注'"  )
    private String  remark;



    public LocalDate getPayStarTime () {
        return payStarTime;
    }
    public void setPayStarTime (LocalDate payStarTime ) {
        this.payStarTime = payStarTime ;
    }
    public LocalDate getPayEndTime () {
        return payEndTime;
    }
    public void setPayEndTime (LocalDate payEndTime ) {
        this.payEndTime = payEndTime ;
    }
    public String getArea () {
        return area;
    }
    public void setArea (String area ) {
        this.area = area ;
    }
    public String getEmployeeId () {
        return employeeId;
    }
    public void setEmployeeId (String employeeId ) {
        this.employeeId = employeeId ;
    }
    public String getEmployeeName () {
        return employeeName;
    }
    public void setEmployeeName (String employeeName ) {
        this.employeeName = employeeName ;
    }
    public String getSystem () {
        return system;
    }
    public void setSystem (String system ) {
        this.system = system ;
    }
    public String getSection () {
        return section;
    }
    public void setSection (String section ) {
        this.section = section ;
    }
    public String getStation () {
        return station;
    }
    public void setStation (String station ) {
        this.station = station ;
    }
    public String getStationLevel () {
        return stationLevel;
    }
    public void setStationLevel (String stationLevel ) {
        this.stationLevel = stationLevel ;
    }
    public String getManageLevel () {
        return manageLevel;
    }
    public void setManageLevel (String manageLevel ) {
        this.manageLevel = manageLevel ;
    }
    public String getSkill () {
        return skill;
    }
    public void setSkill (String skill ) {
        this.skill = skill ;
    }
    public String getProSkills () {
        return proSkills;
    }
    public void setProSkills (String proSkills ) {
        this.proSkills = proSkills ;
    }
    public String getSkillLevel () {
        return skillLevel;
    }
    public void setSkillLevel (String skillLevel ) {
        this.skillLevel = skillLevel ;
    }
    public LocalDate getHiredate () {
        return hiredate;
    }
    public void setHiredate (LocalDate hiredate ) {
        this.hiredate = hiredate ;
    }
    public LocalDate getPositiveTime () {
        return positiveTime;
    }
    public void setPositiveTime (LocalDate positiveTime ) {
        this.positiveTime = positiveTime ;
    }
    public String getWorkingTime () {
        return workingTime;
    }
    public void setWorkingTime (String workingTime ) {
        this.workingTime = workingTime ;
    }
    public Double getBasicSalary () {
        return basicSalary;
    }
    public void setBasicSalary (Double basicSalary ) {
        this.basicSalary = basicSalary ;
    }
    public Double getPostSalary () {
        return postSalary;
    }
    public void setPostSalary (Double postSalary ) {
        this.postSalary = postSalary ;
    }
    public Double getSkillPay () {
        return skillPay;
    }
    public void setSkillPay (Double skillPay ) {
        this.skillPay = skillPay ;
    }
    public Double getManagePay () {
        return managePay;
    }
    public void setManagePay (Double managePay ) {
        this.managePay = managePay ;
    }
    public Double getSkillSubsidies () {
        return skillSubsidies;
    }
    public void setSkillSubsidies (Double skillSubsidies ) {
        this.skillSubsidies = skillSubsidies ;
    }
    public Double getManageSubsidies () {
        return manageSubsidies;
    }
    public void setManageSubsidies (Double manageSubsidies ) {
        this.manageSubsidies = manageSubsidies ;
    }
    public Double getSeniorityLevSubsidies () {
        return seniorityLevSubsidies;
    }
    public void setSeniorityLevSubsidies (Double seniorityLevSubsidies ) {
        this.seniorityLevSubsidies = seniorityLevSubsidies ;
    }
    public Double getAllSubsidies () {
        return allSubsidies;
    }
    public void setAllSubsidies (Double allSubsidies ) {
        this.allSubsidies = allSubsidies ;
    }
    public Double getProjectBenefits () {
        return projectBenefits;
    }
    public void setProjectBenefits (Double projectBenefits ) {
        this.projectBenefits = projectBenefits ;
    }
    public Double getWage () {
        return wage;
    }
    public void setWage (Double wage ) {
        this.wage = wage ;
    }
    public Double getSalary () {
        return salary;
    }
    public void setSalary (Double salary ) {
        this.salary = salary ;
    }
    public Double getComputerSubsidies () {
        return computerSubsidies;
    }
    public void setComputerSubsidies (Double computerSubsidies ) {
        this.computerSubsidies = computerSubsidies ;
    }
    public Double getAccommodationSubsidies () {
        return accommodationSubsidies;
    }
    public void setAccommodationSubsidies (Double accommodationSubsidies ) {
        this.accommodationSubsidies = accommodationSubsidies ;
    }
    public Double getSenioritySubsidies () {
        return senioritySubsidies;
    }
    public void setSenioritySubsidies (Double senioritySubsidies ) {
        this.senioritySubsidies = senioritySubsidies ;
    }
    public Double getHyperthermiaSubsidies () {
        return hyperthermiaSubsidies;
    }
    public void setHyperthermiaSubsidies (Double hyperthermiaSubsidies ) {
        this.hyperthermiaSubsidies = hyperthermiaSubsidies ;
    }
    public Double getAllSalary () {
        return allSalary;
    }
    public void setAllSalary (Double allSalary ) {
        this.allSalary = allSalary ;
    }
    public Double getJinpoCost () {
        return jinpoCost;
    }
    public void setJinpoCost (Double jinpoCost ) {
        this.jinpoCost = jinpoCost ;
    }
    public Double getJinpoSubsidies () {
        return jinpoSubsidies;
    }
    public void setJinpoSubsidies (Double jinpoSubsidies ) {
        this.jinpoSubsidies = jinpoSubsidies ;
    }
    public Double getUtilities () {
        return utilities;
    }
    public void setUtilities (Double utilities ) {
        this.utilities = utilities ;
    }
    public Double getPersonTax () {
        return personTax;
    }
    public void setPersonTax (Double personTax ) {
        this.personTax = personTax ;
    }
    public Double getAllRewardScore () {
        return allRewardScore;
    }
    public void setAllRewardScore (Double allRewardScore ) {
        this.allRewardScore = allRewardScore ;
    }
    public Double getAllRewardCost () {
        return allRewardCost;
    }
    public void setAllRewardCost (Double allRewardCost ) {
        this.allRewardCost = allRewardCost ;
    }
    public Double getAttendanceDay () {
        return attendanceDay;
    }
    public void setAttendanceDay (Double attendanceDay ) {
        this.attendanceDay = attendanceDay ;
    }
    public Double getVacateDay () {
        return vacateDay;
    }
    public void setVacateDay (Double vacateDay ) {
        this.vacateDay = vacateDay ;
    }
    public Double getAbsenteeismDay () {
        return absenteeismDay;
    }
    public void setAbsenteeismDay (Double absenteeismDay ) {
        this.absenteeismDay = absenteeismDay ;
    }
    public Double getUnfinishedTime () {
        return unfinishedTime;
    }
    public void setUnfinishedTime (Double unfinishedTime ) {
        this.unfinishedTime = unfinishedTime ;
    }
    public Double getNormalOvertimeDay () {
        return normalOvertimeDay;
    }
    public void setNormalOvertimeDay (Double normalOvertimeDay ) {
        this.normalOvertimeDay = normalOvertimeDay ;
    }
    public Double getLegalRestDay () {
        return legalRestDay;
    }
    public void setLegalRestDay (Double legalRestDay ) {
        this.legalRestDay = legalRestDay ;
    }
    public Double getLegalOvertimeDay () {
        return legalOvertimeDay;
    }
    public void setLegalOvertimeDay (Double legalOvertimeDay ) {
        this.legalOvertimeDay = legalOvertimeDay ;
    }
    public Double getNormalRestDay () {
        return normalRestDay;
    }
    public void setNormalRestDay (Double normalRestDay ) {
        this.normalRestDay = normalRestDay ;
    }
    public Double getRestOvertimeDay () {
        return RestOvertimeDay;
    }
    public void setRestOvertimeDay (Double RestOvertimeDay ) {
        this.RestOvertimeDay = RestOvertimeDay ;
    }
    public Double getSurplusOvertimeDay () {
        return surplusOvertimeDay;
    }
    public void setSurplusOvertimeDay (Double surplusOvertimeDay ) {
        this.surplusOvertimeDay = surplusOvertimeDay ;
    }
    public Double getOffsetOvertime () {
        return offsetOvertime;
    }
    public void setOffsetOvertime (Double offsetOvertime ) {
        this.offsetOvertime = offsetOvertime ;
    }
    public Double getEffectiveOvertime () {
        return effectiveOvertime;
    }
    public void setEffectiveOvertime (Double effectiveOvertime ) {
        this.effectiveOvertime = effectiveOvertime ;
    }
    public Double getWeekDays () {
        return weekDays;
    }
    public void setWeekDays (Double weekDays ) {
        this.weekDays = weekDays ;
    }
    public Double getPaidDay () {
        return paidDay;
    }
    public void setPaidDay (Double paidDay ) {
        this.paidDay = paidDay ;
    }
    public String getRemark () {
        return remark;
    }
    public void setRemark (String remark ) {
        this.remark = remark ;
    }

}