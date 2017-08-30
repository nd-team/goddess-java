package com.bjike.goddess.royalty.vo;

import com.bjike.goddess.royalty.bo.JobsBetDBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

/**
 * 岗位间对赌表B表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表B表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetEVO {

    /**
     * id
     */
    private String id;
    /**
     * 未达标分配岗位
     */
    private String unmetAllocationJobs;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}