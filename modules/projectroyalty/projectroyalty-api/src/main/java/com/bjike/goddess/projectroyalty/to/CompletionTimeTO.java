package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 完工时间
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:44 ]
 * @Description: [ 完工时间 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompletionTimeTO extends BaseTO {

    /**
     * 完工时间(月)
     */
    @NotNull(message = "完工时间不能为空", groups = {ADD.class, EDIT.class})
    private Integer completion;

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


    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
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