package com.bjike.goddess.annual.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 年假标准业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualStandardBO extends BaseBO {

    /**
     * 开始工龄范围(年)
     */
    private Integer startCycle;

    /**
     * 结束工龄范围(年)
     */
    private Integer endCycle;

    /**
     * 病假限制(月)
     */
    private Integer astrict;

    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStartCycle() {
        return startCycle;
    }

    public void setStartCycle(Integer startCycle) {
        this.startCycle = startCycle;
    }

    public Integer getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(Integer endCycle) {
        this.endCycle = endCycle;
    }

    public Integer getAstrict() {
        return astrict;
    }

    public void setAstrict(Integer astrict) {
        this.astrict = astrict;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}