package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 回款周期
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectionPeriodTO extends BaseTO {

    /**
     * 回款周期(月)
     */
    @NotNull(message = "回款周期不能为空", groups = {ADD.class, EDIT.class})
    private Integer collection;

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
     * 资金方
     */
    @NotNull(message = "资金方不能为空", groups = {ADD.class, EDIT.class})
    private Double capital;


    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
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

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }
}