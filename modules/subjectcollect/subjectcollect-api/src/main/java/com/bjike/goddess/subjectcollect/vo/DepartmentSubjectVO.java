package com.bjike.goddess.subjectcollect.vo;

import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-27 10:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentSubjectVO {
    /**
     * 部门
     */
    private String department;

    /**
     * 子集合
     */
    private List<SubjectCollectBO> subjectCollectList;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SubjectCollectBO> getSubjectCollectList() {
        return subjectCollectList;
    }

    public void setSubjectCollectList(List<SubjectCollectBO> subjectCollectList) {
        this.subjectCollectList = subjectCollectList;
    }
}
