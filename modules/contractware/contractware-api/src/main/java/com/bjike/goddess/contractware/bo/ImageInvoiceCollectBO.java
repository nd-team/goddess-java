package com.bjike.goddess.contractware.bo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 16:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ImageInvoiceCollectBO {
    /**
     * 增值税专用发票金额
     */
    private Double appreciationDedicated;

    /**
     * 增值税普通发票金额
     */
    private Double appreciationCommon;

    /**
     * 增值税电子发票
     */
    private Double appreciationElectronic;

    /**
     * 普通发票
     */
    private Double common;

    public Double getAppreciationDedicated() {
        return appreciationDedicated;
    }

    public void setAppreciationDedicated(Double appreciationDedicated) {
        this.appreciationDedicated = appreciationDedicated;
    }

    public Double getAppreciationCommon() {
        return appreciationCommon;
    }

    public void setAppreciationCommon(Double appreciationCommon) {
        this.appreciationCommon = appreciationCommon;
    }

    public Double getAppreciationElectronic() {
        return appreciationElectronic;
    }

    public void setAppreciationElectronic(Double appreciationElectronic) {
        this.appreciationElectronic = appreciationElectronic;
    }

    public Double getCommon() {
        return common;
    }

    public void setCommon(Double common) {
        this.common = common;
    }
}
