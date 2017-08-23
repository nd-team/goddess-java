package com.bjike.goddess.rotation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 岗位补贴标准业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubsidyStandardBO extends BaseBO {

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 补贴标准
     */
    private Double standard;

    /**
     * 状态
     */
    private Status status;


    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}