package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 多项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_multipleprojectmultipleui")
public class MultipleProjectMultipleUI extends BaseEntity {

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
     * 项目比重
     */
    @Column(name = "projectRatio", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目比重'")
    private String projectRatio;

    /**
     * 项目利润对比
     */
    @Column(name = "projectProfitContrast", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目利润对比'")
    private String projectProfitContrast;

    /**
     * 界面利润对比
     */
    @Column(name = "interfaceProfitContrast", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '界面利润对比'")
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

    public String getInterfaceProfitContrast() {
        return interfaceProfitContrast;
    }

    public void setInterfaceProfitContrast(String interfaceProfitContrast) {
        this.interfaceProfitContrast = interfaceProfitContrast;
    }
}