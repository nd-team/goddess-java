package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.CountType;
import com.bjike.goddess.taskallotment.enums.ForObject;
import com.bjike.goddess.taskallotment.enums.Spacing;
import com.bjike.goddess.taskallotment.enums.Status;

/**
 * 完成情况汇总设置业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinishCountEmailBO extends BaseBO {

    /**
     * 汇总表名称
     */
    private String name;

    /**
     * 项目组
     */
    private String depart;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目表
     */
    private String table;

    /**
     * 提醒间隔
     */
    private Spacing spacing;

    /**
     * 提醒频率
     */
    private Integer remind;

    /**
     * 设置时间
     */
    private String setTime;

    /**
     * 上次发送时间
     */
    private String lastTime;

    /**
     * 通报对象
     */
    private ForObject forObject;

    /**
     * 汇总对象
     */
    private CountType countType;
    /**
     * 汇总开始时间
     */
    private String startTime;

    /**
     * 汇总结束时间
     */
    private String endTime;
    /**
     * 创建人
     */
    private String creater;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String remark;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}