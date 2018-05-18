package com.bjike.goddess.contractware.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 发票管理数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InvoiceManagementDTO extends BaseDTO {

    /**
     * 内部合同编号
     */
    private String internalContractNumber;

    public String getInternalContractNumber() {
        return internalContractNumber;
    }

    public void setInternalContractNumber(String internalContractNumber) {
        this.internalContractNumber = internalContractNumber;
    }
}