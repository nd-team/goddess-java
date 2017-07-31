package com.bjike.goddess.projectmeasure.vo;

import java.util.List;

/**
 * 单个项目多个界面表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SingleProjectMultipleUIVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 界面利润对比
     */
    private String interfaceProfitContrast;

    private List<SingleProjectMultipleUIBVO> singleProjectMultipleUIBVOS;

    public List<SingleProjectMultipleUIBVO> getSingleProjectMultipleUIBVOS() {
        return singleProjectMultipleUIBVOS;
    }

    public void setSingleProjectMultipleUIBVOS(List<SingleProjectMultipleUIBVO> singleProjectMultipleUIBVOS) {
        this.singleProjectMultipleUIBVOS = singleProjectMultipleUIBVOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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