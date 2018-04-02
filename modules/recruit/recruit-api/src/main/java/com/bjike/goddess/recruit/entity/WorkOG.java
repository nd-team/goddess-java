package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;


/**
 * 工作对赌
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

@Entity
@Table(name = "recruit_workog")
public class WorkOG extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "quarters", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String quarters;

    /**
     * 模块
     */
    @Column(name = "modular", columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String modular;

    /**
     * 评分对象
     */
    @Column(name = "scoringOB", columnDefinition = "VARCHAR(255)   COMMENT '评分对象'")
    private String scoringOB;

    /**
     * 评分者
     */
    @Column(name = "raters", columnDefinition = "VARCHAR(255)   COMMENT '评分者'")
    private String raters;

    /**
     * 项目流程
     */
    @Column(name = "projectPRO", columnDefinition = "VARCHAR(255)   COMMENT '项目流程'")
    private String projectPRO;

    /**
     * 节点内容
     */
    @Column(name = "nodeCON", columnDefinition = "VARCHAR(255)   COMMENT '节点内容'")
    private String nodeCON;

    /**
     * 时间
     */
    @Column(name = "time", columnDefinition = "VARCHAR(255)   COMMENT '时间'")
    private String time;

    /**
     * 对赌总比例
     */
    @Column(name = "proportionOG", columnDefinition = "VARCHAR(255)   COMMENT '对赌总比例'")
    private String proportionOG;

    /**
     * 各标签对赌比例
     */
    @Column(name = "labelPRO", columnDefinition = "VARCHAR(255)   COMMENT '各标签对赌比例'")
    private String labelPRO;

    /**
     * 指标类型
     */
    @Column(name = "indexType", columnDefinition = "VARCHAR(255)   COMMENT '指标类型'")
    private String indexType;

    /**
     * 工作指标
     */
    @Column(name = "workIndex", columnDefinition = "VARCHAR(255)   COMMENT '工作指标'")
    private String workIndex;

    /**
     * 各标签评分
     */
    @Column(columnDefinition = "INT(10)   COMMENT '各标签评分'")
    private Integer labelSCO;

    /**
     * 得分
     */
    @Column(name = "score", columnDefinition = "VARCHAR(255)   COMMENT '得分'")
    private String score;

    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workog_id")
    private List<AlertIndex> alertIndices;

    /**
     * 状态
     */
    @Column(name = "state", columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String state;

    /**
     * 接受时间
     */
    @Column(name = "acceptTime", columnDefinition = "VARCHAR(255)   COMMENT '接受时间'")
    private String acceptTime;

    /**
     * 评分内容
     */
    @Column(name = "scoreContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '评分内容'")
    private String scoreContent;

    /**
     * 对赌内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '对赌内容'")
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<AlertIndex> getAlertIndices() {
        return alertIndices;
    }

    public void setAlertIndices(List<AlertIndex> alertIndices) {
        this.alertIndices = alertIndices;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    @Override
    public String toString() {
        return "WorkOG{" +
                "department='" + department + '\'' +
                ", quarters='" + quarters + '\'' +
                ", modular='" + modular + '\'' +
                ", scoringOB='" + scoringOB + '\'' +
                ", raters='" + raters + '\'' +
                ", projectPRO='" + projectPRO + '\'' +
                ", nodeCON='" + nodeCON + '\'' +
                ", time='" + time + '\'' +
                ", proportionOG='" + proportionOG + '\'' +
                ", labelPRO='" + labelPRO + '\'' +
                ", indexType='" + indexType + '\'' +
                ", workIndex='" + workIndex + '\'' +
                ", labelSCO=" + labelSCO +
                ", score='" + score + '\'' +
                ", alertIndices=" + alertIndices +
                ", state='" + state + '\'' +
                ", acceptTime='" + acceptTime + '\'' +
                ", scoreContent='" + scoreContent + '\'' +
                '}';
    }
}