package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.*;


/**
 * 单个项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_singleprojectmultipleui")
public class SingleProjectMultipleUI extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;

    /**
     * 项目类别
     */
    @Column(name = "projectCategory", nullable = false, columnDefinition = "TINYINT(2) COMMENT '项目类别'")
    private ProjectCategory projectCategory;

    /**
     * 界面选择
     */
    @Column(name = "interfaceSelect", nullable = false, columnDefinition = "TINYINT(2) COMMENT '界面选择'")
    private InterfaceSelect interfaceSelect;

    /**
     * 工作量
     */
    @Column(name = "workload", nullable = false, columnDefinition = "INT(11) COMMENT '工作量'")
    private Integer workload;

    /**
     * 利润
     */
    @Column(name = "profit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '利润'")
    private Double profit;

    /**
     * 界面利润对比
     */
    @Column(name = "interfaceProfitContrast", columnDefinition = "VARCHAR(255) COMMENT '界面利润对比'")
    private String interfaceProfitContrast;


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

    public String getInterfaceProfitContrast() {
        return interfaceProfitContrast;
    }

    public void setInterfaceProfitContrast(String interfaceProfitContrast) {
        this.interfaceProfitContrast = interfaceProfitContrast;
    }
}