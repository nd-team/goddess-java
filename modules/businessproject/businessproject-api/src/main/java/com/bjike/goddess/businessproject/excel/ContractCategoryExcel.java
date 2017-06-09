package com.bjike.goddess.businessproject.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 商务项目合同类型excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-08 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractCategoryExcel extends BaseTO {
    /**
     * 合同类型名称
     */
    @ExcelHeader(name = "合同类型名称", notNull = true)
    private String contractName;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
    private String remark;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
