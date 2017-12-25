package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 问题编码规则
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_problemcoderule")
public class ProblemCodeRule extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String projectGroup;

    /**
     * 模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String module;

    /**
     * 问题编码规则(问题)
     */
    @Column(name = "problemCodeRule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题编码规则(问题)'")
    private String problemCodeRule;

    /**
     * 问题编码规则(受理)
     */
    @Column(name = "acceptCodeRule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题编码规则(受理)'")
    private String acceptCodeRule;

    /**
     * 问题编码固定规则
     */
    @Column(name = "codeFixedRule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题编码固定规则'")
    private String codeFixedRule;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getProblemCodeRule() {
        return problemCodeRule;
    }

    public void setProblemCodeRule(String problemCodeRule) {
        this.problemCodeRule = problemCodeRule;
    }

    public String getAcceptCodeRule() {
        return acceptCodeRule;
    }

    public void setAcceptCodeRule(String acceptCodeRule) {
        this.acceptCodeRule = acceptCodeRule;
    }

    public String getCodeFixedRule() {
        return codeFixedRule;
    }

    public void setCodeFixedRule(String codeFixedRule) {
        this.codeFixedRule = codeFixedRule;
    }
}