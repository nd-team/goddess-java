package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 难易度
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FacilityTO extends BaseTO {

    /**
     * 难易度
     */
    @NotNull(message = "难易度不能为空", groups = {ADD.class, EDIT.class})
    private Double facility;

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


    public Double getFacility() {
        return facility;
    }

    public void setFacility(Double facility) {
        this.facility = facility;
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