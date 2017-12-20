package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 获奖情况业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:00 ]
 * @Description: [ 获奖情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationBO extends BaseBO {

    /**
     * 业务类型
     */
    private String bussType;
    /**
     * 与市场信息关联的供应商
     */
    private Integer supplierMarketInfoNum;

    /**
     * 供应商数量
     */
    private Integer supplierNum;
    /**
     * 供应商已完善信息
     */
    private Integer supplierImprovedNum;
    /**
     * 供应商未完善信息
     */
    private Integer supplierNoImprovedNum;

    /**
     * 正在合作供应商
     */
    private Integer cooperatingSupplier;
    /**
     * 未合作供应商
     */
    private Integer noCooperationSupplier;

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public Integer getSupplierMarketInfoNum() {
        return supplierMarketInfoNum;
    }

    public void setSupplierMarketInfoNum(Integer supplierMarketInfoNum) {
        this.supplierMarketInfoNum = supplierMarketInfoNum;
    }

    public Integer getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(Integer supplierNum) {
        this.supplierNum = supplierNum;
    }

    public Integer getSupplierImprovedNum() {
        return supplierImprovedNum;
    }

    public void setSupplierImprovedNum(Integer supplierImprovedNum) {
        this.supplierImprovedNum = supplierImprovedNum;
    }

    public Integer getSupplierNoImprovedNum() {
        return supplierNoImprovedNum;
    }

    public void setSupplierNoImprovedNum(Integer supplierNoImprovedNum) {
        this.supplierNoImprovedNum = supplierNoImprovedNum;
    }

    public Integer getCooperatingSupplier() {
        return cooperatingSupplier;
    }

    public void setCooperatingSupplier(Integer cooperatingSupplier) {
        this.cooperatingSupplier = cooperatingSupplier;
    }

    public Integer getNoCooperationSupplier() {
        return noCooperationSupplier;
    }

    public void setNoCooperationSupplier(Integer noCooperationSupplier) {
        this.noCooperationSupplier = noCooperationSupplier;
    }
}