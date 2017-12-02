package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 外出单业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutBillImportExcel extends BaseBO {

    /**
     * 外出单编号
     */
    @ExcelHeader(name = "外出单编号", notNull = true)
    private String billNum;

//    /**
//     * 事件序号
//     */
//    @ExcelHeader(name = "事件序号", notNull = true)
//    private Integer num;

    /**
     * 计划事件内容
     */
    @ExcelHeader(name = "计划事件内容", notNull = true)
    private String planContext;

    /**
     * 计划日期
     */
    @ExcelHeader(name = "计划日期", notNull = true)
    private LocalDate planTime;

    /**
     * 是否进行
     */
    @ExcelHeader(name = "是否进行", notNull = true)
    private Boolean conduct;

    /**
     * 新增事件内容
     */
    @ExcelHeader(name = "新增事件内容", notNull = true)
    private String newContext;

    /**
     * 未完成原因
     */
    @ExcelHeader(name = "未完成原因", notNull = true)
    private String reason;

    /**
     * 问题
     */
    @ExcelHeader(name = "问题", notNull = true)
    private String problem;

    /**
     * 所属阶段
     */
    @ExcelHeader(name = "所属阶段", notNull = true)
    private String stage;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型", notNull = true)
    private String type;

    /**
     * 涉及金额
     */
    @ExcelHeader(name = "涉及金额", notNull = true)
    private Double money;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称", notNull = true)
    private String companyName;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位", notNull = true)
    private String position;

    /**
     * 电话
     */
    @ExcelHeader(name = "电话", notNull = true)
    private String phone;

    /**
     * 主要目的
     */
    @ExcelHeader(name = "主要目的", notNull = true)
    private String aim;

    /**
     * 可顺带事件
     */
    @ExcelHeader(name = "可顺带事件", notNull = true)
    private String events;

    /**
     * 顺带事件完成量
     */
    @ExcelHeader(name = "顺带事件完成量", notNull = true)
    private Integer eventsNum;

    /**
     * 任务人（外出人）
     */
    @ExcelHeader(name = "任务人（外出人）", notNull = true)
    private String taskMan;

    /**
     * 实际外出开始时间
     */
    @ExcelHeader(name = "实际外出开始时间", notNull = true)
    private LocalDateTime actualTime;

    /**
     * 实际回来时间
     */
    @ExcelHeader(name = "实际回来时间", notNull = true)
    private LocalDateTime returnTime;


    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

//    public Integer getNum() {
//        return num;
//    }
//
//    public void setNum(Integer num) {
//        this.num = num;
//    }

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