package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 毛利率
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RatioTO extends BaseTO {

    /**
     * 毛利率
     */
    @NotNull(message = "毛利率不能为空", groups = {ADD.class, EDIT.class})
    private Double ratio;

    /**
     * 重要性
     */
    @NotNull(message = "重要性不能为空", groups = {ADD.class, EDIT.class})
    private Double importance;

    /**
     * 提成比例
     */
    @NotNull(message = "提成比例不能为空", groups = {ADD.class, EDIT.class})
    private Double rate;

    /**
     * 管理能力
     */
    @NotNull(message = "管理能力不能为空", groups = {ADD.class, EDIT.class})
    private Double manage;


    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }
}