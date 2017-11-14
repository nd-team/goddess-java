package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-10 11:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartCollectBO extends BaseBO {
    /**
     * 部门
     */
    private String department;
    /**
     * 人
     */
    private String person;
    /**
     * 内部项目名称
     */
    private String innerName;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }
}
