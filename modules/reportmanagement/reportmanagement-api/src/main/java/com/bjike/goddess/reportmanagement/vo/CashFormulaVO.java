package com.bjike.goddess.reportmanagement.vo;

/**
 * 项目公式表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-21 05:09 ]
 * @Description: [ 项目公式表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashFormulaVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 公式
     */
    private String form;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}