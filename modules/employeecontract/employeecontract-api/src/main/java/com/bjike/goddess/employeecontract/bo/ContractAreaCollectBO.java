package com.bjike.goddess.employeecontract.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 15:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractAreaCollectBO {
    /**
     * 地区
     */
    private String area;

    /**
     * 基础子集
     */
    private List<ContractCollectBO> contractCollect;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ContractCollectBO> getContractCollect() {
        return contractCollect;
    }

    public void setContractCollect(List<ContractCollectBO> contractCollect) {
        this.contractCollect = contractCollect;
    }
}
