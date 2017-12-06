package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 日计划业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanDayImportExcel extends BaseBO {

    /**
     * 客户编号
     */
    @ExcelHeader(name = "客户编号", notNull = true)
    private String customerNum;

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称", notNull = true)
    private String companyName;

    /**
     * 职务
     */
    @ExcelHeader(name = "职务", notNull = true)
    private String position;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 电话
     */
    @ExcelHeader(name = "电话", notNull = true)
    private String phone;

    /**
     * 业务方向类型
     */
    @ExcelHeader(name = "业务方向类型", notNull = true)
    private String businessType;

    /**
     * 业务方向-科目
     */
    @ExcelHeader(name = "业务方向-科目", notNull = true)
    private String businessSubject;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String department;

    /**
     * 问题受理编号（对内）
     */
    @ExcelHeader(name = "问题受理编号（对内）", notNull = true)
    private String interCode;

    /**
     * 计划事件内容
     */
    @ExcelHeader(name = "计划事件内容")
    private String planContext;

    /**
     * 涉及金额
     */
    @ExcelHeader(name = "涉及金额", notNull = true)
    private Double money;

    /**
     * 问题归属
     */
    @ExcelHeader(name = "问题归属", notNull = true)
    private String attribution;

    /**
     * 问题归类
     */
    @ExcelHeader(name = "问题归类", notNull = true)
    private String classification;

    /**
     * 实际事件内容
     */
    @ExcelHeader(name = "实际事件内容")
    private String actualContext;

    /**
     * 业务状态（立项前/后）
     */
    @ExcelHeader(name = "业务状态（立项前/后）", notNull = true)
    private Boolean businessStatus;

    /**
     * 市场信息编号
     */
    @ExcelHeader(name = "市场信息编号", notNull = true)
    private String markCode;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String internalProject;

    /**
     * 推荐的拜访时间
     */
    @ExcelHeader(name = "推荐的拜访时间", notNull = true)
    private LocalDateTime visitTime;

    /**
     * 是否需要外出
     */
    @ExcelHeader(name = "是否需要外出", notNull = true)
    private Boolean needOut;

    /**
     * 计划日期
     */
    @ExcelHeader(name = "计划日期", notNull = true)
    private LocalDate planTime;

    /**
     * 实际日期
     */
    @ExcelHeader(name = "实际日期", notNull = true)
    private LocalDate actualTime;

    /**
     * 是否进行
     */
    @ExcelHeader(name = "是否进行", notNull = true)
    private Boolean conduct;

    /**
     * 未完成原因
     */
    @ExcelHeader(name = "未完成原因", notNull = true)
    private String unfinishedCause;

    /**
     * 问题
     */
    @ExcelHeader(name = "问题")
    private String probleam;

    /**
     * 所属阶段
     */
    @ExcelHeader(name = "所属阶段", notNull = true)
    private String stage;

    /**
     * 任务人
     */
    @ExcelHeader(name = "任务人", notNull = true)
    private String taskMan;

    /**
     * 负责人
     */
    @ExcelHeader(name = "负责人", notNull = true)
    private String leader;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型", notNull = true)
    private String type;

    /**
     * 收集市场信息量
     */
    @ExcelHeader(name = "收集市场信息量", notNull = true)
    private Long marketInfor;

    /**
     * 收集客户信息量
     */
    @ExcelHeader(name = "收集客户信息量", notNull = true)
    private Long customerInfor;

    /**
     * 收集竞争对手信息量
     */
    @ExcelHeader(name = "收集竞争对手信息量", notNull = true)
    private Long competitorsInfor;

    /**
     * 收集供应商信息量
     */
    @ExcelHeader(name = "收集供应商信息量", notNull = true)
    private Long supplierInfor;

    /**
     * 已招待次数
     */
    @ExcelHeader(name = "已招待次数", notNull = true)
    private Long entertainNum;

    /**
     * 招待金额
     */
    @ExcelHeader(name = "招待金额", notNull = true)
    private Double entertainMoney;

    /**
     * 已洽谈次数
     */
    @ExcelHeader(name = "已洽谈次数", notNull = true)
    private Integer negotiationNum;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

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

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getPlanContext() {
        return planContext;
    }

    public void setPlanContext(String planContext) {
        this.planContext = planContext;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getActualContext() {
        return actualContext;
    }

    public void setActualContext(String actualContext) {
        this.actualContext = actualContext;
    }

    public Boolean getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Boolean businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode;
    }

    public String getInternalProject() {
        return internalProject;
    }

    public void setInternalProject(String internalProject) {
        this.internalProject = internalProject;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public Boolean getNeedOut() {
        return needOut;
    }

    public void setNeedOut(Boolean needOut) {
        this.needOut = needOut;
    }

    public LocalDate getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDate planTime) {
        this.planTime = planTime;
    }

    public LocalDate getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDate actualTime) {
        this.actualTime = actualTime;
    }

    public Boolean getConduct() {
        return conduct;
    }

    public void setConduct(Boolean conduct) {
        this.conduct = conduct;
    }

    public String getUnfinishedCause() {
        return unfinishedCause;
    }

    public void setUnfinishedCause(String unfinishedCause) {
        this.unfinishedCause = unfinishedCause;
    }

    public String getProbleam() {
        return probleam;
    }

    public void setProbleam(String probleam) {
        this.probleam = probleam;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTaskMan() {
        return taskMan;
    }

    public void setTaskMan(String taskMan) {
        this.taskMan = taskMan;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMarketInfor() {
        return marketInfor;
    }

    public void setMarketInfor(Long marketInfor) {
        this.marketInfor = marketInfor;
    }

    public Long getCustomerInfor() {
        return customerInfor;
    }

    public void setCustomerInfor(Long customerInfor) {
        this.customerInfor = customerInfor;
    }

    public Long getCompetitorsInfor() {
        return competitorsInfor;
    }

    public void setCompetitorsInfor(Long competitorsInfor) {
        this.competitorsInfor = competitorsInfor;
    }

    public Long getSupplierInfor() {
        return supplierInfor;
    }

    public void setSupplierInfor(Long supplierInfor) {
        this.supplierInfor = supplierInfor;
    }

    public Long getEntertainNum() {
        return entertainNum;
    }

    public void setEntertainNum(Long entertainNum) {
        this.entertainNum = entertainNum;
    }

    public Double getEntertainMoney() {
        return entertainMoney;
    }

    public void setEntertainMoney(Double entertainMoney) {
        this.entertainMoney = entertainMoney;
    }

    public Integer getNegotiationNum() {
        return negotiationNum;
    }

    public void setNegotiationNum(Integer negotiationNum) {
        this.negotiationNum = negotiationNum;
    }
}