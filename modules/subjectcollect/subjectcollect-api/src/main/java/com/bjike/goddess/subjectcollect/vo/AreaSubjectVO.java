package com.bjike.goddess.subjectcollect.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-27 10:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaSubjectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组集合
     */
    private List<DepartmentSubjectVO> departmentSubjectList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentSubjectVO> getDepartmentSubjectList() {
        return departmentSubjectList;
    }

    public void setDepartmentSubjectList(List<DepartmentSubjectVO> departmentSubjectList) {
        this.departmentSubjectList = departmentSubjectList;
    }
}
