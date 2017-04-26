package com.bjike.goddess.employeecontract.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同信息业务选项传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-21 18:29]
 * @Description: [ 合同信息业务选项传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractManageChoiceBO extends BaseBO {

    /**
     * 显示字段
     */
    private String showValue;

    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }
}
