package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.qualifications.enums.HandleStatus;

import javax.validation.constraints.NotNull;

/**
 * 资质办理管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsHandleTO extends BaseTO {

    /**
     * 资质类别
     */
    @NotNull(message = "资质类别不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 预算的费用
     */
    @NotNull(message = "预算的费用不能为空", groups = {ADD.class, EDIT.class})
    private Double cost;

    /**
     * 备注
     */
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}