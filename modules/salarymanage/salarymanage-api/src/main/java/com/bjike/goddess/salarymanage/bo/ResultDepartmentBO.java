package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultDepartmentBO extends BaseBO{
    /**
     * 部门
     */
    private String department;

    /**
     * 业务方向子集
     */
    private List<ResultDirectionBO> resultDirection;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<ResultDirectionBO> getResultDirection() {
        return resultDirection;
    }

    public void setResultDirection(List<ResultDirectionBO> resultDirection) {
        this.resultDirection = resultDirection;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResultDepartmentBO){
            ResultDepartmentBO bo=(ResultDepartmentBO) obj;
            if (this.hashCode()==bo.hashCode()){
                if (this.department.equals(bo.getDepartment())){
                    return true;
                }
            }
        }
        return false;
    }

}
