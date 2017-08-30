package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 岗位间对赌表E
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表E ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetFTO extends BaseTO {
    /**
     * 未达标分配岗位
     */
    @NotBlank(message = "未达标分配岗位不能为空",groups = {ADD.class, EDIT.class})
    private String unmetAllocationJobs;

    /**
     * 未达标分配
     */
    @NotNull(message = "未达标分配不能为空",groups = {ADD.class, EDIT.class})
    private Double unmetAllocation;

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