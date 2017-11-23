package com.bjike.goddess.subjectcollect.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-27 10:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentSubjectBO {
    /**
     * 部门
     */
    private String department;

    /**
     * 子集合
     */
    private List<SubjectCollectsBO> subjectCollectList;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SubjectCollectsBO> getSubjectCollectList() {
        return subjectCollectList;
    }

    public void setSubjectCollectList(List<SubjectCollectsBO> subjectCollectList) {
        this.subjectCollectList = subjectCollectList;
    }
}
