package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightalAdjustTO extends BaseTO {


    /**
     * 调整后的干股和公司预留利润综合比例
     */
    @NotNull(message = "调整后的干股和公司预留利润综合比例不能为空", groups = {ADD.class, EDIT.class})
    private Double adjCompreRatio;


    /**
     * 调整后的业务提成综合比例
     */
    @NotNull(message = "调整后的业务提成综合比例不能为空", groups = {ADD.class, EDIT.class})
    private Double adjBusCompRatio;


    /**
     * 调整后的管理提成综合比例
     */
    @NotNull(message = "调整后的管理提成综合比例不能为空", groups = {ADD.class, EDIT.class})
    private Double adjMeCompreRatio;

    /**
     * 调整后的资金方综合比例
     */
    @NotNull(message = "调整后的资金方综合比例不能为空", groups = {ADD.class, EDIT.class})
    private Double adjCaCompreRatio;

    public Double getAdjCompreRatio() {
        return adjCompreRatio;
    }

    public void setAdjCompreRatio(Double adjCompreRatio) {
        this.adjCompreRatio = adjCompreRatio;
    }

    public Double getAdjBusCompRatio() {
        return adjBusCompRatio;
    }

    public void setAdjBusCompRatio(Double adjBusCompRatio) {
        this.adjBusCompRatio = adjBusCompRatio;
    }

    public Double getAdjMeCompreRatio() {
        return adjMeCompreRatio;
    }

    public void setAdjMeCompreRatio(Double adjMeCompreRatio) {
        this.adjMeCompreRatio = adjMeCompreRatio;
    }

    public Double getAdjCaCompreRatio() {
        return adjCaCompreRatio;
    }

    public void setAdjCaCompreRatio(Double adjCaCompreRatio) {
        this.adjCaCompreRatio = adjCaCompreRatio;
    }
}