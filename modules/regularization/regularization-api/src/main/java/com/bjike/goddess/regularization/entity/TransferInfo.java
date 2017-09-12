package com.bjike.goddess.regularization.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 转正人员信息
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-12 02:20 ]
* @Description:	[ 转正人员信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "regularization_transferinfo")
public class TransferInfo extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 部门/项目组
*/
@Column(name = "department",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'"  ) 
 private String  department; 

/**
* 岗位
*/
@Column(name = "jobs",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  ) 
 private String  jobs; 

/**
* 姓名
*/
@Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '姓名'"  ) 
 private String  name; 

/**
* 员工编号
*/
@Column(name = "empNo",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '员工编号'"  ) 
 private String  empNo; 

/**
* 性别
*/
@Column(name = "gender",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '性别'"  ) 
 private String  gender; 

/**
* 学历
*/
@Column(name = "education",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '学历'"  ) 
 private String  education; 

/**
* 专业
*/
@Column(name = "profession",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '专业'"  ) 
 private String  profession; 

/**
* 工作年限
*/
@Column(name = "workingYear",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '工作年限'"  ) 
 private Double  workingYear; 

/**
* 入职时间
*/
@Column(name = "hiredate",nullable = false,columnDefinition = "DATE   COMMENT '入职时间'"  ) 
 private LocalDate  hiredate; 

/**
* 同意试用期时长
*/
@Column(name = "probationaryPer",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '同意试用期时长'"  ) 
 private String  probationaryPer; 

/**
* 试用期到期时间
*/
@Column(name = "probationDue",nullable = false,columnDefinition = "DATE   COMMENT '试用期到期时间'"  ) 
 private LocalDate  probationDue; 

/**
* 三天是否跟进
*/
@Column(name = "is_threeFollow",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '三天是否跟进'"  , insertable = false  ) 
 private Boolean  threeFollow; 

/**
* 三天跟进收集的意见
*/
@Column(name = "threeFollowOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '三天跟进收集的意见'"  ) 
 private String  threeFollowOpinion; 

/**
* 一周内是否跟进
*/
@Column(name = "is_weekFollow",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '一周内是否跟进'"  , insertable = false  ) 
 private Boolean  weekFollow; 

/**
* 一周跟进收集的意见
*/
@Column(name = "weekFollowOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '一周跟进收集的意见'"  ) 
 private String  weekFollowOpinion; 

/**
* 一个月是否跟进
*/
@Column(name = "is_monthFollow",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '一个月是否跟进'"  , insertable = false  ) 
 private Boolean  monthFollow; 

/**
* 一个月跟进收集的意见
*/
@Column(name = "monthFollowOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '一个月跟进收集的意见'"  ) 
 private String  monthFollowOpinion; 

/**
* 状态
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '状态'"  ) 
 private StaffStatus  staffStatus; 

/**
* 转正申请日期
*/
@Column(name = "applyDate",nullable = false,columnDefinition = "DATE   COMMENT '转正申请日期'"  ) 
 private LocalDate  applyDate; 

/**
* 截止目前试用期时长
*/
@Column(name = "asProbationLength",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '截止目前试用期时长'"  ) 
 private String  asProbationLength; 

/**
* 确定事项是否确认
*/
@Column(name = "is_confirmEvent",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '确定事项是否确认'"  , insertable = false  ) 
 private Boolean  confirmEvent; 

/**
* 确认人
*/
@Column(name = "confirmPeople",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '确认人'"  ) 
 private String  confirmPeople; 

/**
* 截至申请日期考勤情况
*/
@Column(name = "applyDateAtten",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '截至申请日期考勤情况'"  ) 
 private String  applyDateAtten; 

/**
* 奖励处罚情况
*/
@Column(name = "rewardPunOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '奖励处罚情况'"  ) 
 private String  rewardPunOpinion; 

/**
* 转正后主项技能
*/
@Column(name = "additionalSkill",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正后主项技能'"  ) 
 private String  additionalSkill; 

/**
* 转正后主项技能等级
*/
@Column(name = "additionalSkillGrade",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正后主项技能等级'"  ) 
 private String  additionalSkillGrade; 

/**
* 转正后小项技能
*/
@Column(name = "eventsSkill",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正后小项技能'"  ) 
 private String  eventsSkill; 

/**
* 转正后小项技能等级
*/
@Column(name = "eventsSkillGrade",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正后小项技能等级'"  ) 
 private String  eventsSkillGrade; 

/**
* 是否符合人员编制
*/
@Column(name = "is_conformStaffing",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否符合人员编制'"  , insertable = false  ) 
 private Boolean  conformStaffing; 

/**
* 收入与成本分析意见
*/
@Column(name = "incomeCostOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '收入与成本分析意见'"  ) 
 private String  incomeCostOpinion; 

/**
* 模块负责人
*/
@Column(name = "moduleLeader",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '模块负责人'"  ) 
 private String  moduleLeader; 

/**
* 模块负责人意见
*/
@Column(name = "moduleLeaderOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '模块负责人意见'"  ) 
 private String  moduleLeaderOpinion; 

/**
* 项目经理
*/
@Column(name = "proManage",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目经理'"  ) 
 private String  proManage; 

/**
* 项目经理意见
*/
@Column(name = "proManageOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目经理意见'"  ) 
 private String  proManageOpinion; 

/**
* 总经理
*/
@Column(name = "generManage",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '总经理'"  ) 
 private String  generManage; 

/**
* 总经理意见
*/
@Column(name = "generManageOpinion",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '总经理意见'"  ) 
 private String  generManageOpinion; 

/**
* 是否同意转正
*/
@Column(name = "is_consentPositive",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否同意转正'"  , insertable = false  ) 
 private Boolean  consentPositive; 

/**
* 转正开始日期
*/
@Column(name = "positiveStartDate",nullable = false,columnDefinition = "DATE   COMMENT '转正开始日期'"  ) 
 private LocalDate  positiveStartDate; 

/**
* 转正是否通过
*/
@Column(name = "is_positiveThrough",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '转正是否通过'"  , insertable = false  ) 
 private Boolean  positiveThrough; 

/**
* 实际试用期时长
*/
@Column(name = "praProbationaryPer",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '实际试用期时长'"  ) 
 private String  praProbationaryPer; 

/**
* 转正情况面谈人
*/
@Column(name = "interviewPeper",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '转正情况面谈人'"  ) 
 private String  interviewPeper; 

/**
* 面谈内容
*/
@Column(name = "interviewContent",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '面谈内容'"  ) 
 private String  interviewContent; 



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
 public String getJobs () { 
 return jobs;
 } 
 public void setJobs (String jobs ) { 
 this.jobs = jobs ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public String getEmpNo () { 
 return empNo;
 } 
 public void setEmpNo (String empNo ) { 
 this.empNo = empNo ; 
 } 
 public String getGender () { 
 return gender;
 } 
 public void setGender (String gender ) { 
 this.gender = gender ; 
 } 
 public String getEducation () { 
 return education;
 } 
 public void setEducation (String education ) { 
 this.education = education ; 
 } 
 public String getProfession () { 
 return profession;
 } 
 public void setProfession (String profession ) { 
 this.profession = profession ; 
 } 
 public Double getWorkingYear () { 
 return workingYear;
 } 
 public void setWorkingYear (Double workingYear ) { 
 this.workingYear = workingYear ; 
 } 
 public LocalDate getHiredate () { 
 return hiredate;
 } 
 public void setHiredate (LocalDate hiredate ) { 
 this.hiredate = hiredate ; 
 } 
 public String getProbationaryPer () { 
 return probationaryPer;
 } 
 public void setProbationaryPer (String probationaryPer ) { 
 this.probationaryPer = probationaryPer ; 
 } 
 public LocalDate getProbationDue () { 
 return probationDue;
 } 
 public void setProbationDue (LocalDate probationDue ) { 
 this.probationDue = probationDue ; 
 } 
 public Boolean getThreeFollow () { 
 return threeFollow;
 } 
 public void setThreeFollow (Boolean threeFollow ) { 
 this.threeFollow = threeFollow ; 
 } 
 public String getThreeFollowOpinion () { 
 return threeFollowOpinion;
 } 
 public void setThreeFollowOpinion (String threeFollowOpinion ) { 
 this.threeFollowOpinion = threeFollowOpinion ; 
 } 
 public Boolean getWeekFollow () { 
 return weekFollow;
 } 
 public void setWeekFollow (Boolean weekFollow ) { 
 this.weekFollow = weekFollow ; 
 } 
 public String getWeekFollowOpinion () { 
 return weekFollowOpinion;
 } 
 public void setWeekFollowOpinion (String weekFollowOpinion ) { 
 this.weekFollowOpinion = weekFollowOpinion ; 
 } 
 public Boolean getMonthFollow () { 
 return monthFollow;
 } 
 public void setMonthFollow (Boolean monthFollow ) { 
 this.monthFollow = monthFollow ; 
 } 
 public String getMonthFollowOpinion () { 
 return monthFollowOpinion;
 } 
 public void setMonthFollowOpinion (String monthFollowOpinion ) { 
 this.monthFollowOpinion = monthFollowOpinion ; 
 } 
 public StaffStatus getStaffStatus () { 
 return staffStatus;
 } 
 public void setStaffStatus (StaffStatus staffStatus ) { 
 this.staffStatus = staffStatus ; 
 } 
 public LocalDate getApplyDate () { 
 return applyDate;
 } 
 public void setApplyDate (LocalDate applyDate ) { 
 this.applyDate = applyDate ; 
 } 
 public String getAsProbationLength () { 
 return asProbationLength;
 } 
 public void setAsProbationLength (String asProbationLength ) { 
 this.asProbationLength = asProbationLength ; 
 } 
 public Boolean getConfirmEvent () { 
 return confirmEvent;
 } 
 public void setConfirmEvent (Boolean confirmEvent ) { 
 this.confirmEvent = confirmEvent ; 
 } 
 public String getConfirmPeople () { 
 return confirmPeople;
 } 
 public void setConfirmPeople (String confirmPeople ) { 
 this.confirmPeople = confirmPeople ; 
 } 
 public String getApplyDateAtten () { 
 return applyDateAtten;
 } 
 public void setApplyDateAtten (String applyDateAtten ) { 
 this.applyDateAtten = applyDateAtten ; 
 } 
 public String getRewardPunOpinion () { 
 return rewardPunOpinion;
 } 
 public void setRewardPunOpinion (String rewardPunOpinion ) { 
 this.rewardPunOpinion = rewardPunOpinion ; 
 } 
 public String getAdditionalSkill () { 
 return additionalSkill;
 } 
 public void setAdditionalSkill (String additionalSkill ) { 
 this.additionalSkill = additionalSkill ; 
 } 
 public String getAdditionalSkillGrade () { 
 return additionalSkillGrade;
 } 
 public void setAdditionalSkillGrade (String additionalSkillGrade ) { 
 this.additionalSkillGrade = additionalSkillGrade ; 
 } 
 public String getEventsSkill () { 
 return eventsSkill;
 } 
 public void setEventsSkill (String eventsSkill ) { 
 this.eventsSkill = eventsSkill ; 
 } 
 public String getEventsSkillGrade () { 
 return eventsSkillGrade;
 } 
 public void setEventsSkillGrade (String eventsSkillGrade ) { 
 this.eventsSkillGrade = eventsSkillGrade ; 
 } 
 public Boolean getConformStaffing () { 
 return conformStaffing;
 } 
 public void setConformStaffing (Boolean conformStaffing ) { 
 this.conformStaffing = conformStaffing ; 
 } 
 public String getIncomeCostOpinion () { 
 return incomeCostOpinion;
 } 
 public void setIncomeCostOpinion (String incomeCostOpinion ) { 
 this.incomeCostOpinion = incomeCostOpinion ; 
 } 
 public String getModuleLeader () { 
 return moduleLeader;
 } 
 public void setModuleLeader (String moduleLeader ) { 
 this.moduleLeader = moduleLeader ; 
 } 
 public String getModuleLeaderOpinion () { 
 return moduleLeaderOpinion;
 } 
 public void setModuleLeaderOpinion (String moduleLeaderOpinion ) { 
 this.moduleLeaderOpinion = moduleLeaderOpinion ; 
 } 
 public String getProManage () { 
 return proManage;
 } 
 public void setProManage (String proManage ) { 
 this.proManage = proManage ; 
 } 
 public String getProManageOpinion () { 
 return proManageOpinion;
 } 
 public void setProManageOpinion (String proManageOpinion ) { 
 this.proManageOpinion = proManageOpinion ; 
 } 
 public String getGenerManage () { 
 return generManage;
 } 
 public void setGenerManage (String generManage ) { 
 this.generManage = generManage ; 
 } 
 public String getGenerManageOpinion () { 
 return generManageOpinion;
 } 
 public void setGenerManageOpinion (String generManageOpinion ) { 
 this.generManageOpinion = generManageOpinion ; 
 } 
 public Boolean getConsentPositive () { 
 return consentPositive;
 } 
 public void setConsentPositive (Boolean consentPositive ) { 
 this.consentPositive = consentPositive ; 
 } 
 public LocalDate getPositiveStartDate () { 
 return positiveStartDate;
 } 
 public void setPositiveStartDate (LocalDate positiveStartDate ) { 
 this.positiveStartDate = positiveStartDate ; 
 } 
 public Boolean getPositiveThrough () { 
 return positiveThrough;
 } 
 public void setPositiveThrough (Boolean positiveThrough ) { 
 this.positiveThrough = positiveThrough ; 
 } 
 public String getPraProbationaryPer () { 
 return praProbationaryPer;
 } 
 public void setPraProbationaryPer (String praProbationaryPer ) { 
 this.praProbationaryPer = praProbationaryPer ; 
 } 
 public String getInterviewPeper () { 
 return interviewPeper;
 } 
 public void setInterviewPeper (String interviewPeper ) { 
 this.interviewPeper = interviewPeper ; 
 } 
 public String getInterviewContent () { 
 return interviewContent;
 } 
 public void setInterviewContent (String interviewContent ) { 
 this.interviewContent = interviewContent ; 
 } 
 }