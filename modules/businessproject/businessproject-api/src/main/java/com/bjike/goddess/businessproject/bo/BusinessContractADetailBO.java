package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.businessproject.entity.BusinessContract;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 商务项目合同业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractADetailBO extends BaseBO {


    /**
     * 总金额
     */
    private Double totalMoney;
    private List<BusinessContractBDetailBO> businessContractBDetailBOS;

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<BusinessContractBDetailBO> getBusinessContractBDetailBOS() {
        return businessContractBDetailBOS;
    }

    public void setBusinessContractBDetailBOS(List<BusinessContractBDetailBO> businessContractBDetailBOS) {
        this.businessContractBDetailBOS = businessContractBDetailBOS;
    }
}