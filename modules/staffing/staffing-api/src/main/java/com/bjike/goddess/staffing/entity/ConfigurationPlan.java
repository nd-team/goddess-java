package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 人数配置-计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_configurationplan")
public class ConfigurationPlan extends BaseEntity {

    /**
     * 分类
     */
    @Column(name = "classify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classify;

    /**
     * 权重
     */
    @Column(name = "classifyWeight", columnDefinition = "VARCHAR(255)   COMMENT '权重'")
    private String classifyWeight;

    /**
     * 人数合计
     */
    @Column(name = "total", columnDefinition = "INT(11)   COMMENT '人数合计'")
    private Integer total;

    /**
     * 人数占比
     */
    @Column(name = "proportion", columnDefinition = "VARCHAR(255)   COMMENT '人数占比'")
    private String proportion;

    /**
     * 占比标准
     */
    @Column(name = "standard", columnDefinition = "VARCHAR(255)   COMMENT '占比标准'")
    private String standard;

//    /**
//     * 人数配置-计划子表信息
//     */
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "configurationPlan")
//    private List<PlanSon> sons = new ArrayList<>();
//
//    public List<PlanSon> getSons() {
//        return sons;
//    }
//
//    public void setSons(List<PlanSon> sons) {
//        this.sons = sons;
//    }

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