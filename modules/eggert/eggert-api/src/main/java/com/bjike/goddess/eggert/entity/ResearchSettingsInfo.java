package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 调研设置信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_researchsettingsinfo")
public class ResearchSettingsInfo extends BaseEntity {

    /**
     * 调研目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研目的'")
    private String purpose;

    /**
     * 调研对象
     */
    @Column(name = "object", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研对象'")
    private String object;

    /**
     * 调研时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '调研时间'")
    private LocalDate time;

    /**
     * 是否循环
     */
    @Column(name = "is_cycle", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否循环'", insertable = false)
    private Boolean cycle;

    /**
     * 循环周期
     */
    @Column(name = "cyclePeriod", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '循环周期'")
    private String cyclePeriod;

    /**
     * 调研内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研内容'")
    private String content;

    /**
     * 调研内容明细
     */
    @Column(name = "contentSubsidiary", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研内容明细'")
    private String contentSubsidiary;

    /**
     * 调研工具
     */
    @Column(name = "tool", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研工具'")
    private String tool;

    /**
     * 工具来源
     */
    @Column(name = "toolSource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工具来源'")
    private String toolSource;

    /**
     * 答题信息
     */
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "researchSettingsInfo", fetch = FetchType.LAZY)
    private Exam exam;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Boolean getCycle() {
        return cycle;
    }

    public void setCycle(Boolean cycle) {
        this.cycle = cycle;
    }

    public String getCyclePeriod() {
        return cyclePeriod;
    }

    public void setCyclePeriod(String cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSubsidiary() {
        return contentSubsidiary;
    }

    public void setContentSubsidiary(String contentSubsidiary) {
        this.contentSubsidiary = contentSubsidiary;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getToolSource() {
        return toolSource;
    }

    public void setToolSource(String toolSource) {
        this.toolSource = toolSource;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}