package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * 针对拟为我公司提供产品
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:06 ]
 * @Description: [ 针对拟为我公司提供产品 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProvideProductTO extends BaseTO {
    /**
     * 供应商信息登记id
     */
    private String supplierInfoRegiId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 产品/服务内容
     */
    private String productContent;

    /**
     * 合作时间
     */
    private LocalDate cooperationDate;

    /**
     * 合作期限
     */
    private String cooperationTimeLimit;


    public String getSupplierInfoRegiId() {
        return supplierInfoRegiId;
    }

    public void setSupplierInfoRegiId(String supplierInfoRegiId) {
        this.supplierInfoRegiId = supplierInfoRegiId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public LocalDate getCooperationDate() {
        return cooperationDate;
    }

    public void setCooperationDate(LocalDate cooperationDate) {
        this.cooperationDate = cooperationDate;
    }

    public String getCooperationTimeLimit() {
        return cooperationTimeLimit;
    }

    public void setCooperationTimeLimit(String cooperationTimeLimit) {
        this.cooperationTimeLimit = cooperationTimeLimit;
    }
}