package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 人数配置-实际
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:05 ]
 * @Description: [ 人数配置-实际 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_configurationactual")
public class ConfigurationActual extends BaseEntity {

    /**
     * 分类
     */
    @Column(name = "classify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classify;

    /**
     * 权重
     */
    @Column(name = "classifyWeight",columnDefinition = "VARCHAR(255)   COMMENT '权重'")
    private String classifyWeight;

    /**
     * 人数合计
     */
    @Column(name = "total", columnDefinition = "INT(11)   COMMENT '人数合计'")
    private Integer total;

    /**
     * 人数占比
     */
    @Column(name = "proportion",columnDefinition = "VARCHAR(255)   COMMENT '人数占比'")
    private String proportion;

    /**
     * 占比标准
     */
    @Column(name = "standard",columnDefinition = "VARCHAR(255)   COMMENT '占比标准'")
    private String standard;


    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getClassifyWeight() {
        return classifyWeight;
    }

    public void setClassifyWeight(String classifyWeight) {
        this.classifyWeight = classifyWeight;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}