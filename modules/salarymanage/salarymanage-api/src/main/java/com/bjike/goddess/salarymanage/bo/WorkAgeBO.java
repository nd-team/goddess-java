package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.salarymanage.enums.WorkAge;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkAgeBO extends BaseBO{
    /**
     * 工作年限
     */
    private WorkAge workAge;

    /**
     * 基础信息
     */
    private SalaryTestCollectBO salaryTestCollect;


    public WorkAge getWorkAge() {
        return workAge;
    }

    public void setWorkAge(WorkAge workAge) {
        this.workAge = workAge;
    }

    public SalaryTestCollectBO getSalaryTestCollect() {
        return salaryTestCollect;
    }

    public void setSalaryTestCollect(SalaryTestCollectBO salaryTestCollect) {
        this.salaryTestCollect = salaryTestCollect;
    }
}
