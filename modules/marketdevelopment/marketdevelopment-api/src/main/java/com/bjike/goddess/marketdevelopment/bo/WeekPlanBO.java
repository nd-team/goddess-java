package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 周计划业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekPlanBO extends BaseBO {

    /**
     * 月计划 id
     */
    private String month_id;

    /**
     * 业务方向科目
     */
    private String course;

    /**
     * 总任务量
     */
    private Double monthTotal;

    /**
     * 周期(开始日期)
     */
    private String startCycle;

    /**
     * 周期(结束日期)
     */
    private String endCycle;

    /**
     * 各周工作量在整月占比
     */
    private Double accounted;

    /**
     * 查询
     */
    private Double inquire;

    /**
     * 了解
     */
    private Double know;

    /**
     * 接触
     */
    private Double contact;

    /**
     * 拜访
     */
    private Double visit;

    /**
     * 活动
     */
    private Double activity;

    /**
     * 合计
     */
    private Double total;


    public String getMonth_id() {
        return month_id;
    }

    public void setMonth_id(String month_id) {
        this.month_id = month_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(Double monthTotal) {
        this.monthTotal = monthTotal;
    }

    public String getStartCycle() {
        return startCycle;
    }

    public void setStartCycle(String startCycle) {
        this.startCycle = startCycle;
    }

    public String getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(String endCycle) {
        this.endCycle = endCycle;
    }

    public Double getAccounted() {
        return accounted;
    }

    public void setAccounted(Double accounted) {
        this.accounted = accounted;
    }

    public Double getInquire() {
        return inquire;
    }

    public void setInquire(Double inquire) {
        this.inquire = inquire;
    }

    public Double getKnow() {
        return know;
    }

    public void setKnow(Double know) {
        this.know = know;
    }

    public Double getContact() {
        return contact;
    }

    public void setContact(Double contact) {
        this.contact = contact;
    }

    public Double getVisit() {
        return visit;
    }

    public void setVisit(Double visit) {
        this.visit = visit;
    }

    public Double getActivity() {
        return activity;
    }

    public void setActivity(Double activity) {
        this.activity = activity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}