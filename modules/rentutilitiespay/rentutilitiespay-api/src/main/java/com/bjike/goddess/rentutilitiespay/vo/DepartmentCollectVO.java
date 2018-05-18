package com.bjike.goddess.rentutilitiespay.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-20 16:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentCollectVO {
    /**
     * 部门
     */
    private String department;

    /**
     * 基础子集
     */
    private List<CollectVO> collectList;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<CollectVO> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<CollectVO> collectList) {
        this.collectList = collectList;
    }
}
