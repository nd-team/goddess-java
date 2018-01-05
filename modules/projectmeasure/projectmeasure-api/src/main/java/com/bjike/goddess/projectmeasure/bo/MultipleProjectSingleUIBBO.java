package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import java.util.List;

/**
 * 多项目单个界面业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectSingleUIBBO extends BaseBO {


    /**
     * 界面选择
     */
    private InterfaceSelect interfaceSelect;
    /**
     * 项目利润对比
     */
    private String projectProfitContrast;
    /**
     * 多项目单界面
     */
    private List<MultipleProjectSingleUIBO> multipleProjectSingleUIBOS;


    public List<MultipleProjectSingleUIBO> getMultipleProjectSingleUIBOS() {
        return multipleProjectSingleUIBOS;
    }

    public void setMultipleProjectSingleUIBOS(List<MultipleProjectSingleUIBO> multipleProjectSingleUIBOS) {
        this.multipleProjectSingleUIBOS = multipleProjectSingleUIBOS;
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