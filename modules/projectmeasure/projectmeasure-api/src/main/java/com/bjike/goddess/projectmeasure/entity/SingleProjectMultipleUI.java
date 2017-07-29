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


    public String getInterfaceProfitContrast() {
        return interfaceProfitContrast;
    }

    public void setInterfaceProfitContrast(String interfaceProfitContrast) {
        this.interfaceProfitContrast = interfaceProfitContrast;
    }
}