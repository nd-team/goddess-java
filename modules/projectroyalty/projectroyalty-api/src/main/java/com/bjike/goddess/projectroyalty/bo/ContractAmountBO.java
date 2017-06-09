package com.bjike.goddess.projectroyalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同金额业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:45 ]
 * @Description: [ 合同金额业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractAmountBO extends BaseBO {

    /**
     * 合同金额(万)
     */
    private Double contract;

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


    public Double getContract() {
        return contract;
    }

    public void setContract(Double contract) {
        this.contract = contract;
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