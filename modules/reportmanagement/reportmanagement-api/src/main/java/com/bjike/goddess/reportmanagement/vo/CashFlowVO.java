package com.bjike.goddess.reportmanagement.vo;

/**
 * 现金流量表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-21 03:54 ]
 * @Description: [ 现金流量表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFlowVO {

    /**
     * id
     */
    private String id;
    /**
     * 序号
     */
    private Integer seqNum;

    /**
     * id
     */
    private String projectId;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 金额
     */
    private Double money;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

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
}