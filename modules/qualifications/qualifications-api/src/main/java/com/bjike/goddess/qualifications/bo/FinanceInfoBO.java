package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 财务资料业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceInfoBO extends BaseBO {

    /**
     * 财务报表
     */
    private String reporting;

    /**
     * 审核资料
     */
    private String material;


    public String getReporting() {
        return reporting;
    }

    public void setReporting(String reporting) {
        this.reporting = reporting;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}