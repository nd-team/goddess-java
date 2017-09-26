package com.bjike.goddess.salarymanage.vo;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkAgeVO {
    /**
     * 工作年限
     */
    private String workAge;

    /**
     * 基础信息
     */
    private SalaryTestCollectVO salaryTestCollect;

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public SalaryTestCollectVO getSalaryTestCollect() {
        return salaryTestCollect;
    }

    public void setSalaryTestCollect(SalaryTestCollectVO salaryTestCollect) {
        this.salaryTestCollect = salaryTestCollect;
    }
}
