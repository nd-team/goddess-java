package com.bjike.goddess.projectmeasure.vo;

import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

/**
 * 多项目单个界面表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectSingleUIVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目类别
     */
    private ProjectCategory projectCategory;

    /**
     * 界面选择
     */
    private InterfaceSelect interfaceSelect;

    /**
     * 工作量
     */
    private Integer workload;

    /**
     * 利润
     */
    private Double profit;

    /**
     * 项目比重
     */
    private String projectRatio;

    /**
     * 项目利润对比
     */
    private String projectProfitContrast;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public InterfaceSelect getInterfaceSelect() {
        return interfaceSelect;
    }

    public void setInterfaceSelect(InterfaceSelect interfaceSelect) {
        this.interfaceSelect = interfaceSelect;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getProjectRatio() {
        return projectRatio;
    }

    public void setProjectRatio(String projectRatio) {
        this.projectRatio = projectRatio;
    }

    public String getProjectProfitContrast() {
        return projectProfitContrast;
    }

    public void setProjectProfitContrast(String projectProfitContrast) {
        this.projectProfitContrast = projectProfitContrast;
    }
}