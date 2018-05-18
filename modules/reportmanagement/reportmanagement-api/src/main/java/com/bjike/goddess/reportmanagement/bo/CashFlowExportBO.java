package com.bjike.goddess.reportmanagement.bo;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-23 18:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CashFlowExportBO {

    /**
     * 项目
     */
    private String project;

    /**
     * 行次
     */
    private Integer projectAsset;

    /**
     * 金额
     */
    private Double projectMoney;

    /**
     * 补充资料
     */
    private String projectData;

    /**
     * 行次
     */
    private Integer projectDataAsset;

    /**
     * 金额
     */
    private Double projectDataMoney;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getProjectAsset() {
        return projectAsset;
    }

    public void setProjectAsset(Integer projectAsset) {
        this.projectAsset = projectAsset;
    }

    public Double getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(Double projectMoney) {
        this.projectMoney = projectMoney;
    }

    public String getProjectData() {
        return projectData;
    }

    public void setProjectData(String projectData) {
        this.projectData = projectData;
    }

    public Integer getProjectDataAsset() {
        return projectDataAsset;
    }

    public void setProjectDataAsset(Integer projectDataAsset) {
        this.projectDataAsset = projectDataAsset;
    }

    public Double getProjectDataMoney() {
        return projectDataMoney;
    }

    public void setProjectDataMoney(Double projectDataMoney) {
        this.projectDataMoney = projectDataMoney;
    }
}
