package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

/**
 * 岗位间对赌表E业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表E业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetEBO extends BaseBO {
    /**
     * 未达标分配岗位
     */
    private String unmetAllocationJobs;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;
    /**
     * 岗位间对赌表D
     */
    private JobsBetDBO jobsBetDBO;

    public String getUnmetAllocationJobs() {
        return unmetAllocationJobs;
    }

    public void setUnmetAllocationJobs(String unmetAllocationJobs) {
        this.unmetAllocationJobs = unmetAllocationJobs;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public JobsBetDBO getJobsBetDBO() {
        return jobsBetDBO;
    }

    public void setJobsBetDBO(JobsBetDBO jobsBetDBO) {
        this.jobsBetDBO = jobsBetDBO;
    }
}