package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.qualifications.enums.HandleStatus;

/**
 * 资质办理管理业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsHandleBO extends BaseBO {

    /**
     * 资质类别
     */
    private String type;

    /**
     * 是否办理成功
     */
    private HandleStatus status;

    /**
     * 预算的费用
     */
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

    public HandleStatus getStatus() {
        return status;
    }

    public void setStatus(HandleStatus status) {
        this.status = status;
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