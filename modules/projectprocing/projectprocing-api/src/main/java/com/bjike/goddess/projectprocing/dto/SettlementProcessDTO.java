package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 结算流程存储记录数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettlementProcessDTO extends BaseDTO {
    /**
     * 外包单位
     */
    private String outUnit;

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }
}