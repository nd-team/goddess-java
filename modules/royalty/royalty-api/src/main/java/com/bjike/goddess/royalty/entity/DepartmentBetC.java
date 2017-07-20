package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbetc")
public class DepartmentBetC extends BaseEntity {
    /**
     * 目标-部门分配对赌权重（%）
     */
    @Column(name = "betWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标-部门分配对赌权重（%）'")
    private Double betWeight;

    /**
     * 指标编号
     */
    @Column(name = "indexNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标编号'")
    private String indexNum;

    /**
     * 指标名称
     */
    @Column(name = "indexName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String indexName;

    /**
     * 部门对赌承诺-确认目标值
     */
    @Column(name = "confirmTargetValue", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门对赌承诺-确认目标值'")
    private String confirmTargetValue;

    /**
     * 实际目标值
     */
    @Column(name = "actualTargetValue", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实际目标值'")
    private String actualTargetValue;

    /**
     * 项目对赌是否达标
     */
    @Column(name = "is_standard", nullable = false, columnDefinition = "TINYINT(2) COMMENT '项目对赌是否达标'")
    private Boolean standard;
    /**
     * 对赌得分（部门总得分*目标-部门分配对赌权重）
     */
    @Column(name = "betScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '对赌得分（部门总得分*目标-部门分配对赌权重）'")
    private Double betScore;

    /**
     * 部门间对赌表B
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门间对赌表B'")
    private DepartmentBetB departmentBetB;

    public Double getBetWeight() {
        return betWeight;
    }

    public void setBetWeight(Double betWeight) {
        this.betWeight = betWeight;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getActualTargetValue() {
        return actualTargetValue;
    }

    public void setActualTargetValue(String actualTargetValue) {
        this.actualTargetValue = actualTargetValue;
    }

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public Double getBetScore() {
        return betScore;
    }

    public void setBetScore(Double betScore) {
        this.betScore = betScore;
    }

    public DepartmentBetB getDepartmentBetB() {
        return departmentBetB;
    }

    public void setDepartmentBetB(DepartmentBetB departmentBetB) {
        this.departmentBetB = departmentBetB;
    }
}