package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_multipleprojectsingleuib")
public class MultipleProjectSingleUIB extends BaseEntity {

    /**
     * 界面选择
     */
    @Column(name = "interfaceSelect", nullable = false, columnDefinition = "TINYINT(2) COMMENT '界面选择'")
    private InterfaceSelect interfaceSelect;

    /**
     * 项目利润对比
     */
    @Column(name = "projectProfitContrast", columnDefinition = "VARCHAR(255) COMMENT '项目利润对比'")
    private String projectProfitContrast;

    public InterfaceSelect getInterfaceSelect() {
        return interfaceSelect;
    }

    public void setInterfaceSelect(InterfaceSelect interfaceSelect) {
        this.interfaceSelect = interfaceSelect;
    }

    public String getProjectProfitContrast() {
        return projectProfitContrast;
    }

    public void setProjectProfitContrast(String projectProfitContrast) {
        this.projectProfitContrast = projectProfitContrast;
    }
}