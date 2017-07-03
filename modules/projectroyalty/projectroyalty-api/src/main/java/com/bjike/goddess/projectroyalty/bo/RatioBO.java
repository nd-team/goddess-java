package com.bjike.goddess.projectroyalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 毛利率业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RatioBO extends BaseBO {

    /**
     * 毛利率
     */
    private Double ratio;

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