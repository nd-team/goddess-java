package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 获奖情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.054 ]
 * @Description: [ 获奖情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardSituationTO extends BaseTO {

    /**
     * 供应商基本信息
     */
    @NotNull(message = "供应商信息传输错误", groups = {ADD.class, EDIT.class})
    private String informationId;

    /**
     * 获奖名称
     */
    @NotNull(message = "获奖名称不能为空", groups = {ADD.class, EDIT.class})
    private String awardName;

    /**
     * 获得时间
     */
    @NotNull(message = "获得时间不能为空", groups = {ADD.class, EDIT.class})
    private String acquisition;

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }
}