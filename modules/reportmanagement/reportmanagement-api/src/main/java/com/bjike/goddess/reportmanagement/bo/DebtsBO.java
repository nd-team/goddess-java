package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: [董添添]
 * @Date: [2018-05-02 14:25]
 * @Description: [负债表查询返回对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */
public class DebtsBO extends BaseBO{
    /**
     * 开始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    /**
     * 项目
     */
    private String project;

    /**
     * 行次
     */
    private Integer num;

    /**
     * 负债和所有者权益年初数
     */
    private double beginAsset;

    private String systemId;

    /**
     * 资产期末数
     */
    private double endAsset;

    /**
     * 项目id
     */
    private String projectId;

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public double getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(double beginAsset) {
        this.beginAsset = beginAsset;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public double getEndAsset() {
        return endAsset;
    }

    public void setEndAsset(double endAsset) {
        this.endAsset = endAsset;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "DebtsBO{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", project='" + project + '\'' +
                ", num=" + num +
                ", beginAsset=" + beginAsset +
                ", systemId='" + systemId + '\'' +
                ", endAsset=" + endAsset +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
