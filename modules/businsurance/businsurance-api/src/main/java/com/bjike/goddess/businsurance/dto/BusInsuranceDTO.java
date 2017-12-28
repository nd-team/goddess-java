package com.bjike.goddess.businsurance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商业保险方案数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusInsuranceDTO extends BaseDTO {

    /**
     * 保险公司
     */
    private String insureComapny;

    /**
     * 投保险种
     */
    private String insureType;

    /**
     * 保险条件
     */
    private String insureCondition;

    public String getInsureComapny() {
        return insureComapny;
    }

    public void setInsureComapny(String insureComapny) {
        this.insureComapny = insureComapny;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getInsureCondition() {
        return insureCondition;
    }

    public void setInsureCondition(String insureCondition) {
        this.insureCondition = insureCondition;
    }


}