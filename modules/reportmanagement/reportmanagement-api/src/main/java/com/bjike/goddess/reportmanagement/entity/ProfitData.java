package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 存储利润表查询的数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 上午11:26
 * @Description: [ 存储利润表查询的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_profitdata")
public class ProfitData extends BaseEntity {

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '开始时间'")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
    private LocalDate endTime;

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(30)   COMMENT '项目'")
    private String project;

    /**
     * 行次
     */
    @Column(name = "num", nullable = false, columnDefinition = "VARCHAR(8)   COMMENT '行次'")
    private Integer num;

    /**
     * 本月数金额
     */
    @Column(name = "monthMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本月数金额'")
    private BigDecimal monthMoney;

    /**
     * 本年累计数金额
     */
    @Column(name = "yearMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本年累计数金额'")
    private BigDecimal yearMoney;

    /**
     * 项目类型
     */
    @Column(name = "projectType", columnDefinition = "VARCHAR(255)   COMMENT '项目类型'")
    private String projectType;

    /**
     * 利润类型
     */
    @Column(name = "profitType", columnDefinition = "TINYINT(2)   COMMENT '利润类型'")
    private ProfitType profitType;

    /**
     * 项目id
     */
    @Column(name = "projectId", columnDefinition = "VARCHAR(50)   COMMENT '项目id'")
    private String projectId;

    /**
     * 运算类型
     */
    @Column(name = "type", columnDefinition = "TINYINT(2)   COMMENT '运算类型'")
    private Type type;

    private String systemId;

    public ProfitData() {
    }

    public ProfitData(LocalDate startTime, LocalDate endTime, String project, Integer num, BigDecimal monthMoney, BigDecimal yearMoney, String projectType, ProfitType profitType, Type type, String systemId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.project = project;
        this.num = num;
        this.monthMoney = monthMoney;
        this.yearMoney = yearMoney;
        this.projectType = projectType;
        this.profitType = profitType;
        this.type = type;
        this.systemId = systemId;
    }

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

    public BigDecimal getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(BigDecimal monthMoney) {
        this.monthMoney = monthMoney;
    }

    public BigDecimal getYearMoney() {
        return yearMoney;
    }

    public void setYearMoney(BigDecimal yearMoney) {
        this.yearMoney = yearMoney;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public ProfitType getProfitType() {
        return profitType;
    }

    public void setProfitType(ProfitType profitType) {
        this.profitType = profitType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}