package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 外出单
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_outbill")
public class OutBill extends BaseEntity {

    /**
     * 外出单编号
     */
    @Column(name = "billNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外出单编号'")
    private String billNum;

    /**
     * 事件序号
     */
    @Column(name = "num", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '事件序号'")
    private Integer num;

    /**
     * 计划事件内容
     */
    @Column(name = "planContext", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划事件内容'")
    private String planContext;

    /**
     * 计划日期
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划日期'")
    private LocalDate planTime;

    /**
     * 是否进行
     */
    @Column(name = "is_conduct", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否进行'")
    private Boolean conduct;

    /**
     * 新增事件内容
     */
    @Column(name = "newContext", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '新增事件内容'")
    private String newContext;

    /**
     * 未完成原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未完成原因'")
    private String reason;

    /**
     * 问题
     */
    @Column(name = "problem", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String problem;

    /**
     * 所属阶段
     */
    @Column(name = "stage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属阶段'")
    private String stage;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 涉及金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '涉及金额'")
    private Double money;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话'")
    private String phone;

    /**
     * 主要目的
     */
    @Column(name = "aim", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主要目的'")
    private String aim;

    /**
     * 可顺带事件
     */
    @Column(name = "events", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '可顺带事件'")
    private String events;

    /**
     * 顺带事件完成量
     */
    @Column(name = "eventsNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '顺带事件完成量'")
    private Integer eventsNum;

    /**
     * 任务人（外出人）
     */
    @Column(name = "taskMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务人（外出人）'")
    private String taskMan;

    /**
     * 实际外出开始时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际外出开始时间'")
    private LocalDateTime actualTime;

    /**
     * 实际回来时间
     */
    @Column(name = "returnTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际回来时间'")
    private LocalDateTime returnTime;


    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPlanContext() {
        return planContext;
    }

    public void setPlanContext(String planContext) {
        this.planContext = planContext;
    }

    public LocalDate getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDate planTime) {
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

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }


}