package com.bjike.goddess.dimission.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总条件
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DistinctCollectConditionBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位层级
     */
    private String positionLever;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionLever() {
        return positionLever;
    }

    public void setPositionLever(String positionLever) {
        this.positionLever = positionLever;
    }

}