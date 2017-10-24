package com.bjike.goddess.carinfo.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentVO {

    /**
     * 部门
     */
    private String department;

    /**
     * 基础子集
     */
    private CollectCarinfoVO collectCarinfo;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CollectCarinfoVO getCollectCarinfo() {
        return collectCarinfo;
    }

    public void setCollectCarinfo(CollectCarinfoVO collectCarinfo) {
        this.collectCarinfo = collectCarinfo;
    }
}
