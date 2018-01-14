package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 工作对赌业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 工作对赌业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LifeOGBO extends BaseBO {

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
     * 生活情感标签一级分类
     */
    private String emotionOne;

    /**
     * 生活情感标签二级分类
     */
    private String emotionTwo;

    /**
     * 生活情感小标签
     */
    private String label;

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