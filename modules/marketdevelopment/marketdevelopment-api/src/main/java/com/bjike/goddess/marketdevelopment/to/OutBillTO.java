package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 外出单
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutBillTO extends BaseTO {

    /**
     * 外出单编号
     */
    @NotBlank(message = "外出单编号不能为空", groups = {ADD.class, EDIT.class})
    private String billNum;
//
//    /**
//     * 事件序号
//     */
//    private Integer num;

    /**
     * 计划事件内容
     */
    @NotBlank(message = "计划事件内容不能为空", groups = {ADD.class, EDIT.class})
    private String planContext;

    /**
     * 计划日期
     */
    @NotBlank(message = "计划日期不能为空", groups = {ADD.class, EDIT.class})
    private String planTime;

    /**
     * 是否进行
     */
    @NotNull(message = "是否进行不能为空", groups = {ADD.class, EDIT.class})
    private Boolean conduct;

    /**
     * 新增事件内容
     */
    @NotBlank(message = "新增事件内容不能为空", groups = {ADD.class, EDIT.class})
    private String newContext;

    /**
     * 未完成原因
     */
    @NotBlank(message = "未完成原因不能为空", groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 问题
     */
    @NotBlank(message = "问题不能为空", groups = {ADD.class, EDIT.class})
    private String problem;

    /**
     * 所属阶段
     */
    @NotBlank(message = "所属阶段不能为空", groups = {ADD.class, EDIT.class})
    private String stage;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 涉及金额
     */
    @NotNull(message = "涉及金额不能为空", groups = {ADD.class, EDIT.class})
    private Double money;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 主要目的
     */
    @NotBlank(message = "主要目的不能为空", groups = {ADD.class, EDIT.class})
    private String aim;

    /**
     * 可顺带事件
     */
    @NotBlank(message = "可顺带事件不能为空", groups = {ADD.class, EDIT.class})
    private String events;

    /**
     * 顺带事件完成量
     */
    @NotNull(message = "顺带事件完成量不能为空", groups = {ADD.class, EDIT.class})
    private Integer eventsNum;

    /**
     * 任务人（外出人）
     */
    @NotBlank(message = "任务人（外出人）不能为空", groups = {ADD.class, EDIT.class})
    private String taskMan;

    /**
     * 实际外出开始时间
     */
    @NotBlank(message = "实际外出开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String actualTime;

    /**
     * 实际回来时间
     */
    @NotBlank(message = "实际回来时间不能为空", groups = {ADD.class, EDIT.class})
    private String returnTime;


    public String getPlanContext() {
        return planContext;
    }

    public void setPlanContext(String planContext) {
        this.planContext = planContext;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Boolean getConduct() {
        return conduct;
    }

    public void setConduct(Boolean conduct) {
        this.conduct = conduct;
    }

    public String getNewContext() {
        return newContext;
    }

    public void setNewContext(String newContext) {
        this.newContext = newContext;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public Integer getEventsNum() {
        return eventsNum;
    }

    public void setEventsNum(Integer eventsNum) {
        this.eventsNum = eventsNum;
    }

    public String getTaskMan() {
        return taskMan;
    }

    public void setTaskMan(String taskMan) {
        this.taskMan = taskMan;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }
}