package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

import java.util.List;

/**
 * 岗位间对赌表B业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表B业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetBBO extends BaseBO {
    /**
     * 部门
     */
    private String department;
    /**
     * 部门间对赌表A
     */
    private JobsBetABO jobsBetABO;
    /**
     * 部门间对赌表C
     */
    private List<JobsBetCBO> jobsBetCBOS;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public JobsBetABO getJobsBetABO() {
        return jobsBetABO;
    }

    public void setJobsBetABO(JobsBetABO jobsBetABO) {
        this.jobsBetABO = jobsBetABO;
    }

    public List<JobsBetCBO> getJobsBetCBOS() {
        return jobsBetCBOS;
    }

    public void setJobsBetCBOS(List<JobsBetCBO> jobsBetCBOS) {
        this.jobsBetCBOS = jobsBetCBOS;
    }
}