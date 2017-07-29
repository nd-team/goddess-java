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
public class MultipleProjectSingleUIBVO {

    /**
     * id
     */
    private String id;

    /**
     * 界面选择
     */
    private InterfaceSelect interfaceSelect;

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