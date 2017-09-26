package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.salarymanage.enums.WorkAge;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultWorkAgeBO extends BaseBO{
    /**
     * 工作年限
     */
    private WorkAge workAge;

    /**
     * 基础子集
     */
    private SalaryCalculateResultBO salaryCalculateResult;

    public WorkAge getWorkAge() {
        return workAge;
    }

    public void setWorkAge(WorkAge workAge) {
        this.workAge = workAge;
    }

    public SalaryCalculateResultBO getSalaryCalculateResult() {
        return salaryCalculateResult;
    }

    public void setSalaryCalculateResult(SalaryCalculateResultBO salaryCalculateResult) {
        this.salaryCalculateResult = salaryCalculateResult;
    }
}
