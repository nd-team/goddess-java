package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultDepartmentVO {

    /**
     * 部门
     */
    private String department;

    /**
     * 业务方向
     */
    private List<ResultDirectionVO> resultDirection;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<ResultDirectionVO> getResultDirection() {
        return resultDirection;
    }

    public void setResultDirection(List<ResultDirectionVO> resultDirection) {
        this.resultDirection = resultDirection;
    }
}
