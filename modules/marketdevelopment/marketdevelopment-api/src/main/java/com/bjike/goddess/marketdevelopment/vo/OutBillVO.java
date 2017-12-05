package com.bjike.goddess.marketdevelopment.vo;

/**
 * 外出单表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutBillVO {

    /**
     * id
     */
    private String id;
    /**
     * 外出单编号
     */
    private String billNum;

    /**
     * 事件序号
     */
    private Integer num;

    /**
     * 计划事件内容
     */
    private String planContext;

    /**
     * 计划日期
     */
    private String planTime;

    /**
     * 是否进行
     */
    private Boolean conduct;

    /**
     * 新增事件内容
     */
    private String newContext;

    /**
     * 未完成原因
     */
    private String reason;

    /**
     * 问题
     */
    private String problem;

    /**
     * 所属阶段
     */
    private String stage;

    /**
     * 类型
     */
    private String type;

    /**
     * 涉及金额
     */
    private Double money;

    /**
     * 姓名
     */
    private String name;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职位
     */
    private String position;

    /**
     * 电话
     */
    private String phone;

    /**
     * 主要目的
     */
    private String aim;

    /**
     * 可顺带事件
     */
    private String events;

    /**
     * 顺带事件完成量
     */
    private Integer eventsNum;

    /**
     * 任务人（外出人）
     */
    private String taskMan;

    /**
     * 实际外出开始时间
     */
    private String actualTime;

    /**
     * 实际回来时间
     */
    private String returnTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}