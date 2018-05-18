package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultAreaVO {
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
    private List<ResultDepartmentVO> resultDepartment;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ResultDepartmentVO> getResultDepartment() {
        return resultDepartment;
    }

    public void setResultDepartment(List<ResultDepartmentVO> resultDepartment) {
        this.resultDepartment = resultDepartment;
    }

    public Set<String> getId() {
        return id;
    }

    public void setId(Set<String> id) {
        this.id = id;
    }
}
