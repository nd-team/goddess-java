package com.bjike.goddess.feedback.vo;

/**
 * 问题编码规则表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemCodeRuleVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 模块
     */
    private String module;

    /**
     * 问题编码规则(问题)
     */
    private String problemCodeRule;

    /**
     * 问题编码规则(受理)
     */
    private String acceptCodeRule;

    /**
     * 问题编码固定规则
     */
    private String codeFixedRule;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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