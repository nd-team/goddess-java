package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 周计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekPlanTO extends BaseTO {

    /**
     * 月计划 id
     */
    @NotNull(message = "月计划id不能为空",groups = {ADD.class, EDIT.class})
    private String monthId;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 总任务量
     */
    @NotNull(message = "总任务量不能为空",groups = {ADD.class, EDIT.class})
    private Double monthTotal;

    /**
     * 周期(开始日期)
     */
    @NotNull(message = "周期(开始日期)不能为空",groups = {ADD.class, EDIT.class})
    private String startCycle;

    /**
     * 周期(结束日期)
     */
    @NotNull(message = "周期(结束日期)不能为空",groups = {ADD.class, EDIT.class})
    private String endCycle;

    /**
     * 各周工作量在整月占比
     */
    @NotNull(message = "各周工作量在整月占比不能为空",groups = {ADD.class, EDIT.class})
    private Double accounted;

    /**
     * 查询
     */
    @NotNull(message = "查询不能为空",groups = {ADD.class, EDIT.class})
    private Double inquire;

    /**
     * 了解
     */
    @NotNull(message = "了解不能为空",groups = {ADD.class, EDIT.class})
    private Double know;

    /**
     * 接触
     */
    @NotNull(message = "接触不能为空",groups = {ADD.class, EDIT.class})
    private Double contact;

    /**
     * 拜访
     */
    @NotNull(message = "拜访不能为空",groups = {ADD.class, EDIT.class})
    private Double visit;

    /**
     * 活动
     */
    @NotNull(message = "活动不能为空",groups = {ADD.class, EDIT.class})
    private Double activity;

    /**
     * 合计
     */
    private Double total;


    public String getMonthId() {
        return monthId;
    }

    public void setMonthId(String monthId) {
        this.monthId = monthId;
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