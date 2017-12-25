package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import java.util.List;

/**
 * 多项目多个界面业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectMultipleUIBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;


    /**
     * 项目比重
     */
    private String projectRatio;

    /**
     * 项目利润对比
     */
    private String projectProfitContrast;

    /**
     * 界面利润对比
     */
    private String interfaceProfitContrast;

    /**
     * 多项目多界面
     */
    private List<MultipleProjectMultipleUIBBO> multipleProjectMultipleUIBBOS;

    public List<MultipleProjectMultipleUIBBO> getMultipleProjectMultipleUIBBOS() {
        return multipleProjectMultipleUIBBOS;
    }

    public void setMultipleProjectMultipleUIBBOS(List<MultipleProjectMultipleUIBBO> multipleProjectMultipleUIBBOS) {
        this.multipleProjectMultipleUIBBOS = multipleProjectMultipleUIBBOS;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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