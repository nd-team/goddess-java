package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

/**
 * 单个项目单个界面业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SingleProjectSingleUIBO extends BaseBO {

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
}