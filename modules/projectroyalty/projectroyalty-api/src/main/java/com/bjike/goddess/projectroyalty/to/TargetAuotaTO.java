package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目提成目标定额
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TargetAuotaTO extends BaseTO {

    /**
     * 项目提成权重分配Id
     */
    @NotBlank(message = "项目提成权重分配Id不能为空", groups = {ADD.class, EDIT.class})
    private String allocationId;

    /**
     * 影响因素(总数)
     */
    @NotNull(message = "影响因素不能为空", groups = {ADD.class, EDIT.class})
    private Integer factor;

    /**
     * 备注
     */
    private String remark;

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}