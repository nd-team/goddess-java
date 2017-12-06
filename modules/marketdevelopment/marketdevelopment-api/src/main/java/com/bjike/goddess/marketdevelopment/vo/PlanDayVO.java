package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 日计划表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanDayVO {

    /**
     * id
     */
    private String id;
    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 客户编号
     */
    private String customerNum;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职务
     */
    private String position;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 业务方向类型
     */
    private String businessType;

    /**
     * 业务方向-科目
     */
    private String businessSubject;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/
     */
    private String department;

    /**
     * 问题受理编号（对内）
     */
    private String interCode;

    /**
     * 计划事件内容
     */
    private String planContext;

    /**
     * 涉及金额
     */
    private Double money;

    /**
     * 问题归属
     */
    private String attribution;

    /**
     * 问题归类
     */
    private String classification;

    /**
     * 实际事件内容
     */
    private String actualContext;

    /**
     * 业务状态（立项前/后）
     */
    private Boolean businessStatus;

    /**
     * 市场信息编号
     */
    private String markCode;

    /**
     * 内部项目名称
     */
    private String internalProject;

    /**
     * 推荐的拜访时间
     */
    private String visitTime;

    /**
     * 是否需要外出
     */
    private Boolean needOut;

    /**
     * 计划日期
     */
    private String planTime;

    /**
     * 实际日期
     */
    private String actualTime;

    /**
     * 是否进行
     */
    private Boolean conduct;

    /**
     * 未完成原因
     */
    private String unfinishedCause;

    /**
     * 问题
     */
    private String probleam;

    /**
     * 所属阶段
     */
    private String stage;

    /**
     * 任务人
     */
    private String taskMan;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 类型
     */
    private String type;

    /**
     * 收集市场信息量
     */
    private Long marketInfor;

    /**
     * 收集客户信息量
     */
    private Long customerInfor;

    /**
     * 收集竞争对手信息量
     */
    private Long competitorsInfor;

    /**
     * 收集供应商信息量
     */
    private Long supplierInfor;

    /**
     * 已招待次数
     */
    private Long entertainNum;

    /**
     * 招待金额
     */
    private Double entertainMoney;

    /**
     * 已洽谈次数
     */
    private Integer negotiationNum;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public Boolean getNeedOut() {
        return needOut;
    }

    public void setNeedOut(Boolean needOut) {
        this.needOut = needOut;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}