package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-09-13 11:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentPeopleBO extends BaseBO {
    /**
     * 部门
     */
    private String department;
    /**
     * 人数
     */
    private Long peopleCount;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Long peopleCount) {
        this.peopleCount = peopleCount;
    }
}
