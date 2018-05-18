package com.bjike.goddess.carinfo.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentBO implements Serializable{

    /**
     * 部门
     */
    private String department;


    private CollectCarinfoBO collectCarinfo;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CollectCarinfoBO getCollectCarinfo() {
        return collectCarinfo;
    }

    public void setCollectCarinfo(CollectCarinfoBO collectCarinfo) {
        this.collectCarinfo = collectCarinfo;
    }
}
