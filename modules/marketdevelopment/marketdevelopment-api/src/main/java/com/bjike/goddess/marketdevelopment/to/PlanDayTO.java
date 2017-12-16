package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 日计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanDayTO extends BaseTO {

//    /**
//     * 序号
//     */
//    private String serialNumber;

    /**
     * 客户编号
     */
    @NotBlank(message = "客户编号不能为空", groups = {ADD.class, EDIT.class})
    private String customerNum;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 职务
     */
    @NotBlank(message = "职务不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 业务方向类型
     */
    @NotBlank(message = "业务方向类型不能为空", groups = {ADD.class, EDIT.class})
    private String businessType;

    /**
     * 业务方向-科目
     */
    @NotBlank(message = "业务方向-科目不能为空", groups = {ADD.class, EDIT.class})
    private String businessSubject;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 问题受理编号（对内）
     */
    @NotBlank(message = "问题受理编号（对内）不能为空", groups = {ADD.class, EDIT.class})
    private String interCode;

    /**
     * 计划事件内容
     */
    @NotBlank(message = "计划事件内容")
    private String planContext;

    /**
     * 涉及金额
     */
    @NotBlank(message = "涉及金额不能为空", groups = {ADD.class, EDIT.class})
    private Double money;

    /**
     * 问题归属
     */
    @NotBlank(message = "问题归属不能为空", groups = {ADD.class, EDIT.class})
    private String attribution;

    /**
     * 问题归类
     */
    @NotBlank(message = "问题归类不能为空", groups = {ADD.class, EDIT.class})
    private String classification;

    /**
     * 实际事件内容
     */
    @NotBlank(message = "实际事件内容")
    private String actualContext;

    /**
     * 业务状态（立项前/后）
     */
    @NotBlank(message = "业务状态（立项前/后）不能为空", groups = {ADD.class, EDIT.class})
    private Boolean businessStatus;

    /**
     * 市场信息编号
     */
    @NotBlank(message = "市场信息编号不能为空", groups = {ADD.class, EDIT.class})
    private String markCode;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String internalProject;

    /**
     * 推荐的拜访时间
     */
    @NotBlank(message = "推荐的拜访时间不能为空", groups = {ADD.class, EDIT.class})
    private String visitTime;

    /**
     * 是否需要外出
     */
    @NotBlank(message = "是否需要外出不能为空", groups = {ADD.class, EDIT.class})
    private Boolean needOut;

    /**
     * 计划日期
     */
    @NotBlank(message = "计划日期不能为空", groups = {ADD.class, EDIT.class})
    private String planTime;

    /**
     * 实际日期
     */
    @NotBlank(message = "实际日期不能为空", groups = {ADD.class, EDIT.class})
    private String actualTime;

    /**
     * 是否进行
     */
    @NotBlank(message = "是否进行不能为空", groups = {ADD.class, EDIT.class})
    private Boolean conduct;

    /**
     * 未完成原因
     */
    @NotBlank(message = "未完成原因不能为空", groups = {ADD.class, EDIT.class})
    private String unfinishedCause;

    /**
     * 问题
     */
    @NotBlank(message = "问题")
    private String probleam;

    /**
     * 所属阶段
     */
    @NotBlank(message = "所属阶段不能为空", groups = {ADD.class, EDIT.class})
    private String stage;

    /**
     * 任务人
     */
    @NotBlank(message = "任务人不能为空", groups = {ADD.class, EDIT.class})
    private String taskMan;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = {ADD.class, EDIT.class})
    private String leader;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 收集市场信息量
     */
    @NotBlank(message = "收集市场信息量不能为空", groups = {ADD.class, EDIT.class})
    private Long marketInfor;

    /**
     * 收集客户信息量
     */
    @NotBlank(message = "收集客户信息量不能为空", groups = {ADD.class, EDIT.class})
    private Long customerInfor;

    /**
     * 收集竞争对手信息量
     */
    @NotBlank(message = "收集竞争对手信息量不能为空", groups = {ADD.class, EDIT.class})
    private Long competitorsInfor;

    /**
     * 收集供应商信息量
     */
    @NotBlank(message = "收集供应商信息量不能为空", groups = {ADD.class, EDIT.class})
    private Long supplierInfor;

    /**
     * 已招待次数
     */
    @NotBlank(message = "已招待次数不能为空", groups = {ADD.class, EDIT.class})
    private Long entertainNum;

    /**
     * 招待金额
     */
    @NotBlank(message = "招待金额不能为空", groups = {ADD.class, EDIT.class})
    private Double entertainMoney;

    /**
     * 已洽谈次数
     */
    @NotBlank(message = "已洽谈次数不能为空", groups = {ADD.class, EDIT.class})
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
}