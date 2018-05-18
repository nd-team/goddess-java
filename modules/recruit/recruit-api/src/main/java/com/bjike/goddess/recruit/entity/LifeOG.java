package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


/**
 * 生活对赌
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 生活对赌 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_lifeog")
public class LifeOG extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "quarters",  columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String quarters;

    /**
     * 模块
     */
    @Column(name = "modular",  columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String modular;

    /**
     * 评分对象
     */
    @Column(name = "scoringOB",  columnDefinition = "VARCHAR(255)   COMMENT '评分对象'")
    private String scoringOB;

    /**
     * 评分者
     */
    @Column(name = "raters",  columnDefinition = "VARCHAR(255)   COMMENT '评分者'")
    private String raters;

    /**
     * 项目流程
     */
    @Column(name = "projectPRO",  columnDefinition = "VARCHAR(255)   COMMENT '项目流程'")
    private String projectPRO;

    /**
     * 节点内容
     */
    @Column(name = "nodeCON",  columnDefinition = "VARCHAR(255)   COMMENT '节点内容'")
    private String nodeCON;

    /**
     * 时间
     */
    @Column(name = "time",  columnDefinition = "DATE   COMMENT '时间'")
    private String time;

    /**
     * 生活情感标签一级分类
     */
    @Column(name = "emotionOne",  columnDefinition = "VARCHAR(255)   COMMENT '生活情感标签一级分类'")
    private String emotionOne;

    /**
     * 生活情感标签二级分类
     */
    @Column(name = "emotionTwo",  columnDefinition = "VARCHAR(255)   COMMENT '生活情感标签二级分类'")
    private String emotionTwo;

    /**
     * 生活情感小标签
     */
    @Column(name = "label",  columnDefinition = "VARCHAR(255)   COMMENT '生活情感小标签'")
    private String label;

    /**
     * 各标签评分
     */
    @Column(name = "labelSCO",  columnDefinition = "INT(10)   COMMENT '各标签评分'")
    private Integer labelSCO;

    /**
     * 得分
     */
    @Column(name = "score",  columnDefinition = "VARCHAR(255)   COMMENT '得分'")
    private String score;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lifeog_id")
    private Set<ScoreWithStart> scoreWithStarts;

    /**
     * 接受时间
     */
    @Column(name = "acceptTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '接受时间'")
    private String acceptTime;

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Set<ScoreWithStart> getScoreWithStarts() {
        return scoreWithStarts;
    }

    public void setScoreWithStarts(Set<ScoreWithStart> scoreWithStarts) {
        this.scoreWithStarts = scoreWithStarts;
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

    public String getEmotionOne() {
        return emotionOne;
    }

    public void setEmotionOne(String emotionOne) {
        this.emotionOne = emotionOne;
    }

    public String getEmotionTwo() {
        return emotionTwo;
    }

    public void setEmotionTwo(String emotionTwo) {
        this.emotionTwo = emotionTwo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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