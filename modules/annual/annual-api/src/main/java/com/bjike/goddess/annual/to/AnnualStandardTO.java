package com.bjike.goddess.annual.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 年假标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualStandardTO extends BaseTO {

    /**
     * 开始工龄范围(年)
     */
    @NotNull(message = "开始工龄范围不能为空", groups = {EDIT.class, ADD.class})
    private Integer startCycle;

    /**
     * 结束工龄范围(年)
     */
    @NotNull(message = "结束工龄范围不能为空", groups = {EDIT.class, ADD.class})
    private Integer endCycle;

    /**
     * 病假限制(月)
     */
    @NotNull(message = "病假限制不能为空", groups = {EDIT.class, ADD.class})
    private Integer astrict;

    /**
     * 备注
     */
    private String remark;


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