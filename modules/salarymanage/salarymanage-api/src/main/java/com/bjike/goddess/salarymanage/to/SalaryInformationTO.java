package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
* 薪资管理
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryInformationTO extends BaseTO {


    public interface TestAdd{

    }

    /**
     * 计薪周期开始时间
     */
    @NotBlank(message = "计薪周期开始时间不能为空!", groups = {ADD.class, EDIT.class})
    private String payStarTime;

    /**
     * 计薪周期结束时间
     */
    @NotBlank(message = "计薪周期结束时间不能为空!", groups = {ADD.class, EDIT.class})
    private String  payEndTime;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空!", groups = {ADD.class, EDIT.class})
    private String  area;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空!", groups = {ADD.class, EDIT.class})
    private String  employeeId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空!", groups = {ADD.class, EDIT.class})
    private String  employeeName;

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空!", groups = {ADD.class, EDIT.class})
    private String  system;

    /**
     * 部门/项目组
     */
    @NotBlank(message = "部门/项目组不能为空!", groups = {ADD.class, EDIT.class})
    private String  section;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空!", groups = {ADD.class, EDIT.class})
    private String  station;

    /**
     * 岗位层级
     */
    @NotBlank(message = "岗位层级不能为空!", groups = {ADD.class, EDIT.class})
    private String  stationLevel;

    /**
     * 管理层级
     */
    @NotBlank(message = "管理层级不能为空!", groups = {ADD.class, EDIT.class})
    private String  manageLevel;

    /**
     * 技能项
     */
    @NotBlank(message = "技能项不能为空!", groups = {ADD.class, EDIT.class})
    private String  skill;

    /**
     * 技能专业
     */
    @NotBlank(message = "技能专业不能为空!", groups = {ADD.class, EDIT.class})
    private String  proSkills;

    /**
     * 技能级别
     */
    @NotBlank(message = "技能级别不能为空!", groups = {ADD.class, EDIT.class})
    private String  skillLevel;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空!", groups = {ADD.class, EDIT.class})
    private String  hiredate;

    /**
     * 转正时间
     */
    private String  positiveTime;

    /**
     * 在职时间
     */
    @NotBlank(message = "在职时间不能为空!", groups = {ADD.class, EDIT.class})
    private String  workingTime;

    /**
     * 基本工资
     */
    @NotBlank(message = "基本工资不能为空!", groups = {ADD.class, EDIT.class})
    private Double  basicSalary;

    /**
     * 岗位工资
     */
    @NotBlank(message = "岗位工资不能为空!", groups = {ADD.class, EDIT.class})
    private Double  postSalary;

    /**
     * 技能工资
     */
    @NotNull(message = "技能工资不能为空!", groups = {ADD.class, EDIT.class})
    private Double  skillPay;

    /**
     * 管理工资
     */
    @NotNull(message = "管理工资不能为空!", groups = {ADD.class, EDIT.class})
    private Double  managePay;

    /**
     * 技能职衔补助
     */
    @NotNull(message = "技能职衔补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  skillSubsidies;

    /**
     * 管理等级职衔补助
     */
    @NotNull(message = "管理等级职衔补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  manageSubsidies;

    /**
     * 工龄职衔补助
     */
    @NotNull(message = "工龄职衔补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  seniorityLevSubsidies;

    /**
     * 职衔补助总额
     */
    @NotNull(message = "职衔补助总额不能为空!", groups = {ADD.class, EDIT.class})
    private Double  allSubsidies;

    /**
     * 项目津贴
     */
    @NotNull(message = "项目津贴不能为空!", groups = {ADD.class, EDIT.class})
    private Double  projectBenefits;

    /**
     * 定薪
     */

    @NotNull(message = "定薪不能为空!", groups = {ADD.class, EDIT.class})
    private Double  wage;

    /**
     * 工资
     */
    @NotNull(message = "工资不能为空!", groups = {ADD.class, EDIT.class})
    private Double  salary;

    /**
     * 电脑补助
     */
    @NotNull(message = "电脑补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  computerSubsidies;

    /**
     * 住宿补助
     */
    @NotNull(message = "住宿补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  accommodationSubsidies;

    /**
     * 工龄补助
     */
    @NotNull(message = "工龄补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  senioritySubsidies;

    /**
     * 高温补助
     */
    @NotNull(message = "高温补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  hyperthermiaSubsidies;

    /**
     * 工资总额
     */
    @NotNull(message = "工资总额不能为空!", groups = {ADD.class, EDIT.class})
    private Double  allSalary;

    /**
     * 扣社保
     */
    @NotNull(message = "扣社保不能为空!", groups = {ADD.class, EDIT.class})
    private Double  jinpoCost;

    /**
     * 社保补助
     */
    @NotNull(message = "社保补助不能为空!", groups = {ADD.class, EDIT.class})
    private Double  jinpoSubsidies;

    /**
     * 水电费
     */
    @NotNull(message = "水电费不能为空!", groups = {ADD.class, EDIT.class})
    private Double  utilities;

    /**
     * 个税
     */
    @NotNull(message = "个税不能为空!", groups = {ADD.class, EDIT.class})
    private Double  personTax;

    /**
     * 奖励处罚得分汇总
     */
    @NotNull(message = "奖励处罚得分汇总不能为空!", groups = {ADD.class, EDIT.class})
    private Double  allRewardScore;

    /**
     * 奖励处罚费用汇总
     */
    @NotNull(message = "奖励处罚费用汇总不能为空!", groups = {ADD.class, EDIT.class})
    private Double  allRewardCost;

    /**
     * 出勤天数
     */
    @NotNull(message = "出勤天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  attendanceDay;

    /**
     * 请假天数
     */
    @NotNull(message = "请假天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  vacateDay;

    /**
     * 旷工天数
     */
    @NotNull(message = "旷工天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  absenteeismDay;

    /**
     * 未完成任务工时
     */
    @NotNull(message = "未完成任务工时不能为空!", groups = {ADD.class, EDIT.class})
    private Double  unfinishedTime;

    /**
     * 正常工作日加班天数
     */
    @NotNull(message = "正常工作日加班天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  normalOvertimeDay;

    /**
     * 法定节假日实际休息天数
     */
    @NotNull(message = "法定节假日实际休息天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  legalRestDay;

    /**
     * 法定节假日加班天数
     */
    @NotNull(message = "法定节假日加班天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  legalOvertimeDay;

    /**
     * 正常休息天数实际休息天数
     */
    @NotNull(message = "正常休息天数实际休息天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  normalRestDay;

    /**
     * 正常休息天数加班天数
     */
    @NotNull(message = "正常休息天数加班天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  RestOvertimeDay;

    /**
     * 剩余加班天数
     */
    @NotNull(message = "剩余加班天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  surplusOvertimeDay;

    /**
     * 加班抵事假和其他假的天数
     */
    @NotNull(message = "加班抵事假和其他假的天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  offsetOvertime;

    /**
     * 抵扣事假和其他假后剩余加班天数
     */
    @NotNull(message = "抵扣事假和其他假后剩余加班天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  effectiveOvertime;

    /**
     * 月工作日
     */
    @NotNull(message = "月工作日不能为空!", groups = {ADD.class, EDIT.class})
    private Double  weekDays;

    /**
     * 可享受带薪天数
     */
    @NotNull(message = "可享受带薪天数不能为空!", groups = {ADD.class, EDIT.class})
    private Double  paidDay;

    /**
     * 备注
     */
    private String  remark;


    public String getPayStarTime() {
        return payStarTime;
    }

    public void setPayStarTime(String payStarTime) {
        this.payStarTime = payStarTime;
    }

    public String getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime) {
        this.payEndTime = payEndTime;
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

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
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