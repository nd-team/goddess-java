package com.bjike.goddess.employeecontract.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.employeecontract.enums.ContractStatus;

/**
* 员工合同信息数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractInformationDTO extends BaseDTO {
    /**
     * 合同状态
     */
    private ContractStatus contractStatus;

    /**
     * 姓名
     */
    private String name;

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}