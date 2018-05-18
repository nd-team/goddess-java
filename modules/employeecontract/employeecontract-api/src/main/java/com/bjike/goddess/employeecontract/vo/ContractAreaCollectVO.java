package com.bjike.goddess.employeecontract.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-10 15:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractAreaCollectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 基础子集
     */
    private List<ContractCollectVO> contractCollect;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ContractCollectVO> getContractCollect() {
        return contractCollect;
    }

    public void setContractCollect(List<ContractCollectVO> contractCollect) {
        this.contractCollect = contractCollect;
    }
}
