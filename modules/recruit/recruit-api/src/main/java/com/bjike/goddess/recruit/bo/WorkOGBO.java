package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 工作对赌业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkOGBO extends BaseBO {

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String quarters;

    /**
     * 模块
     */
    private String modular;

    /**
     * 评分对象
     */
    private String scoringOB;

    /**
     * 评分者
     */
    private String raters;

    /**
     * 项目流程
     */
    private String projectPRO;

    /**
     * 节点内容
     */
    private String nodeCON;

    /**
     * 时间
     */
    private LocalDate time;

    /**
     * 对赌总比例
     */
    private String proportionOG;

    /**
     * 各标签对赌比例
     */
    private String labelPRO;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 工作指标
     */
    private String workIndex;

    /**
     * 各标签评分
     */
    private Integer labelSCO;

    /**
     * 得分
     */
    private String score;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getScoringOB() {
        return scoringOB;
    }

    public void setScoringOB(String scoringOB) {
        this.scoringOB = scoringOB;
    }

    public String getRaters() {
        return raters;
    }

    public void setRaters(String raters) {
        this.raters = raters;
    }

    public String getProjectPRO() {
        return projectPRO;
    }

    public void setProjectPRO(String projectPRO) {
        this.projectPRO = projectPRO;
    }

    public String getNodeCON() {
        return nodeCON;
    }

    public void setNodeCON(String nodeCON) {
        this.nodeCON = nodeCON;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getProportionOG() {
        return proportionOG;
    }

    public void setProportionOG(String proportionOG) {
        this.proportionOG = proportionOG;
    }

    public String getLabelPRO() {
        return labelPRO;
    }

    public void setLabelPRO(String labelPRO) {
        this.labelPRO = labelPRO;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getWorkIndex() {
        return workIndex;
    }

    public void setWorkIndex(String workIndex) {
        this.workIndex = workIndex;
    }

    public Integer getLabelSCO() {
        return labelSCO;
    }

    public void setLabelSCO(Integer labelSCO) {
        this.labelSCO = labelSCO;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}