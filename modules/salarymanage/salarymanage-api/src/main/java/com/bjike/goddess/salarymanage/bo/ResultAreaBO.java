package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultAreaBO implements Serializable {
    /**
     * id
     */
    private Set<String> id;
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private List<ResultDepartmentBO> resultDepartment;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ResultDepartmentBO> getResultDepartment() {
        return resultDepartment;
    }

    public void setResultDepartment(List<ResultDepartmentBO> resultDepartment) {
        this.resultDepartment = resultDepartment;
    }


    public Set<String> getId() {
        return id;
    }


    public void setId(Set<String> id) {
        this.id = id;
    }
}
