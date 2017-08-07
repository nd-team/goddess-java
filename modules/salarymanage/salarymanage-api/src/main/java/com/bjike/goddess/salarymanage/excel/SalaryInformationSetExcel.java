package com.bjike.goddess.salarymanage.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 薪资资料
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-08-2 09:11 ]
 * @Description: [ 薪资资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryInformationSetExcel {
    /**
     * 指标名称
     */
    @ExcelHeader(name = "指标名称",notNull = true)
    private String indexName;
    /**
     * 计薪周期开始时间
     */
    @ExcelHeader(name = "计薪周期开始时间",notNull = true)
    private String payStarTime;

    /**
     * 计薪周期结束时间
     */
    @ExcelHeader(name = "计薪周期结束时间",notNull = true)
    private String  payEndTime;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String  area;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String  employeeId;

    /**
     * 姓名
     */@ExcelHeader(name = "姓名",notNull = true)
    private String  employeeName;

    /**
     * 体系
     */
    @ExcelHeader(name = "体系",notNull = true)
    private String  system;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组",notNull = true)
    private String  section;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String  station;

    /**
     * 岗位层级
     */
    @ExcelHeader(name = "岗位层级",notNull = true)
    private String  stationLevel;

    /**
     * 管理层级
     */
    @ExcelHeader(name = "管理层级",notNull = true)
    private String  manageLevel;

    /**
     * 技能项
     */
    @ExcelHeader(name = "技能项",notNull = true)
    private String  skill;

    /**
     * 技能专业
     */
    @ExcelHeader(name = "技能专业",notNull = true)
    private String  proSkills;

    /**
     * 技能级别
     */
    @ExcelHeader(name = "技能级别",notNull = true)
    private String  skillLevel;

    /**
     * 入职时间
     */
    @ExcelHeader(name = "入职时间",notNull = true)
    private String  hiredate;

    /**
     * 转正时间
     */
    @ExcelHeader(name = "转正时间",notNull = true)
    private String  positiveTime;

    /**
     * 在职时间
     */
    @ExcelHeader(name = "在职时间",notNull = true)
    private String  workingTime;

    /**
     * 基本工资
     */
    @ExcelHeader(name = "基本工资",notNull = true)
    private Double  basicSalary;

    /**
     * 岗位工资
     */
    @ExcelHeader(name = "岗位工资",notNull = true)
    private Double  postSalary;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组",notNull = true)
    private String  department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String  position;

    /**
     * 基本工资
     */
    @ExcelHeader(name = "基本工资",notNull = true)
    private Double  basePay;

    /**
     * 技能工资
     */
    @ExcelHeader(name = "技能工资",notNull = true)
    private Double  skillPay;

    /**
     * 管理工资
     */
    @ExcelHeader(name = "管理工资",notNull = true)
    private Double  managePay;

    /**
     * 技能职衔补助
     */
    @ExcelHeader(name = "技能职衔补助",notNull = true)
    private Double  skillSubsidies;

    /**
     * 管理等级职衔补助
     */
    @ExcelHeader(name = "管理等级职衔补助",notNull = true)
    private Double  manageSubsidies;

    /**
     * 工龄职衔补助
     */
    @ExcelHeader(name = "工龄职衔补助",notNull = true)
    private Double  seniorityLevSubsidies;

    /**
     * 职衔补助总额
     */
    @ExcelHeader(name = "职衔补助总额",notNull = true)
    private Double  allSubsidies;

    /**
     * 项目津贴
     */
    @ExcelHeader(name = "项目津贴",notNull = true)
    private Double  projectBenefits;

    /**
     * 定薪
     */
    @ExcelHeader(name = "定薪",notNull = true)
    private Double  wage;

    /**
     * 工资
     */
    @ExcelHeader(name = "工资",notNull = true)
    private Double  salary;

    /**
     * 电脑补助
     */
    @ExcelHeader(name = "电脑补助",notNull = true)
    private Double  computerSubsidies;

    /**
     * 住宿补助
     */
    @ExcelHeader(name = "住宿补助",notNull = true)
    private Double  accommodationSubsidies;

    /**
     * 工龄补助
     */
    @ExcelHeader(name = "工龄补助",notNull = true)
    private Double  senioritySubsidies;

    /**
     * 高温补助
     */
    @ExcelHeader(name = "高温补助",notNull = true)
    private Double  hyperthermiaSubsidies;

    /**
     * 工资总额
     */
    @ExcelHeader(name = "工资总额",notNull = true)
    private Double  allSalary;

    /**
     * 扣社保
     */
    @ExcelHeader(name = "扣社保",notNull = true)
    private Double  jinpoCost;

    /**
     * 社保补助
     */
    @ExcelHeader(name = "社保补助",notNull = true)
    private Double  jinpoSubsidies;

    /**
     * 水电费
     */
    @ExcelHeader(name = "水电费",notNull = true)
    private Double  utilities;

    /**
     * 个税
     */
    @ExcelHeader(name = "个税",notNull = true)
    private Double  personTax;

    /**
     * 奖励处罚得分汇总
     */
    @ExcelHeader(name = "奖励处罚得分汇总",notNull = true)
    private Double  allRewardScore;

    /**
     * 奖励处罚费用汇总
     */
    @ExcelHeader(name = "奖励处罚费用汇总",notNull = true)
    private Double  allRewardCost;

    /**
     * 出勤天数
     */
    @ExcelHeader(name = "出勤天数",notNull = true)
    private Double  attendanceDay;

    /**
     * 请假天数
     */
    @ExcelHeader(name = "请假天数",notNull = true)
    private Double  vacateDay;

    /**
     * 旷工天数
     */
    @ExcelHeader(name = "旷工天数",notNull = true)
    private Double  absenteeismDay;

    /**
     * 未完成任务工时
     */
    @ExcelHeader(name = "未完成任务工时",notNull = true)
    private Double  unfinishedTime;

    /**
     * 正常工作日加班天数
     */
    @ExcelHeader(name = "正常工作日加班天数",notNull = true)
    private Double  normalOvertimeDay;

    /**
     * 法定节假日实际休息天数
     */
    @ExcelHeader(name = "法定节假日实际休息天数",notNull = true)
    private Double  legalRestDay;

    /**
     * 法定节假日加班天数
     */
    @ExcelHeader(name = "法定节假日加班天数",notNull = true)
    private Double  legalOvertimeDay;

    /**
     * 正常休息天数实际休息天数
     */
    @ExcelHeader(name = "正常休息天数实际休息天数",notNull = true)
    private Double  normalRestDay;

    /**
     * 正常休息天数加班天数
     */
    @ExcelHeader(name = "正常休息天数加班天数",notNull = true)
    private Double  RestOvertimeDay;

    /**
     * 剩余加班天数
     */
    @ExcelHeader(name = "剩余加班天数",notNull = true)
    private Double  surplusOvertimeDay;

    /**
     * 加班抵事假和其他假的天数
     */
    @ExcelHeader(name = "加班抵事假和其他假的天数",notNull = true)
    private Double  offsetOvertime;

    /**
     * 抵扣事假和其他假后剩余加班天数
     */
    @ExcelHeader(name = "抵扣事假和其他假后剩余加班天数",notNull = true)
    private Double  effectiveOvertime;

    /**
     * 月工作日
     */
    @ExcelHeader(name = "月工作日",notNull = true)
    private Double  weekDays;

    /**
     * 可享受带薪天数
     */
    @ExcelHeader(name = "可享受带薪天数",notNull = true)
    private Double  paidDay;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注",notNull = true)
    private String  remark;



    public String getPayStarTime () {
        return payStarTime;
    }
    public void setPayStarTime (String payStarTime ) {
        this.payStarTime = payStarTime ;
    }
    public String getPayEndTime () {
        return payEndTime;
    }
    public void setPayEndTime (String payEndTime ) {
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
    public String getHiredate () {
        return hiredate;
    }
    public void setHiredate (String hiredate ) {
        this.hiredate = hiredate ;
    }
    public String getPositiveTime () {
        return positiveTime;
    }
    public void setPositiveTime (String positiveTime ) {
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
    public String getDepartment () {
        return department;
    }
    public void setDepartment (String department ) {
        this.department = department ;
    }
    public String getPosition () {
        return position;
    }
    public void setPosition (String position ) {
        this.position = position ;
    }
    public Double getBasePay () {
        return basePay;
    }
    public void setBasePay (Double basePay ) {
        this.basePay = basePay ;
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
