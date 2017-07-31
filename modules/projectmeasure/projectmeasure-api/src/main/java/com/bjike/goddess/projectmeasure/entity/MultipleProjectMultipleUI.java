package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.*;


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
     * 项目比重
     */
    @Column(name = "projectRatio", columnDefinition = "VARCHAR(255) COMMENT '项目比重'")
    private String projectRatio;

    /**
     * 项目利润对比
     */
    @Column(name = "projectProfitContrast", columnDefinition = "VARCHAR(255) COMMENT '项目利润对比'")
    private String projectProfitContrast;

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

    public String getProjectRatio() {
        return projectRatio;
    }

    public void setProjectRatio(String projectRatio) {
        this.projectRatio = projectRatio;
    }
}