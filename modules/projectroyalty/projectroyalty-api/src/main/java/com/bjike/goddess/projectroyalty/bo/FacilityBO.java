package com.bjike.goddess.projectroyalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 难易度业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FacilityBO extends BaseBO {

    /**
     * 难易度
     */
    private Double facility;

    /**
     * 重要性
     */
    private Double importance;

    /**
     * 提成比例
     */
    private Double rate;

    /**
     * 管理能力
     */
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