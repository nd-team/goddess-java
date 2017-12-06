package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 周计划的周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekCycleUpdateTO extends BaseTO {

    /**
     * 周期ID
     */
    private String weekCycleId;

    /**
     * 表头数据集合
     */
    private List<WeekFilesTO> weekFilesTOs;

    /**
     * 备注
     */
    private String remark;

    public String getWeekCycleId() {
        return weekCycleId;
    }

    public void setWeekCycleId(String weekCycleId) {
        this.weekCycleId = weekCycleId;
    }

    public List<WeekFilesTO> getWeekFilesTOs() {
        return weekFilesTOs;
    }

    public void setWeekFilesTOs(List<WeekFilesTO> weekFilesTOs) {
        this.weekFilesTOs = weekFilesTOs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}