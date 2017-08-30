package com.bjike.goddess.incomecheck.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 指标设置业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CheckIndexBO extends BaseBO {

    /**
     * 收入比例差异指标
     */
    private Double incomeRate;

    /**
     * 收入比例差异指标
     */
    private Double completeRate;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Double completeRate) {
        this.completeRate = completeRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}