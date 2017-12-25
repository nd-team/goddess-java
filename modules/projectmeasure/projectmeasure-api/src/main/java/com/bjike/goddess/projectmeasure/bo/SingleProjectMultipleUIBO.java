package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.Column;
import java.util.List;

/**
 * 单个项目多个界面业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SingleProjectMultipleUIBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;


    /**
     * 界面利润对比
     */
    private String interfaceProfitContrast;
    /**
     * 单项目多界面
     */
    private List<SingleProjectMultipleUIBBO> singleProjectMultipleUIBBOS;

    public List<SingleProjectMultipleUIBBO> getSingleProjectMultipleUIBBOS() {
        return singleProjectMultipleUIBBOS;
    }

    public void setSingleProjectMultipleUIBBOS(List<SingleProjectMultipleUIBBO> singleProjectMultipleUIBBOS) {
        this.singleProjectMultipleUIBBOS = singleProjectMultipleUIBBOS;
    }

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