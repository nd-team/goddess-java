package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 合作情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.651 ]
 * @Description: [ 合作情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_cooperation_situation")
public class CooperationSituation extends BaseEntity {

    /**
     * 供应商基本信息
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '供应商基本信息'")
    private SupplierInformation information;

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 产品服务内容
     */
    @Column(name = "product", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '产品/服务内容'")
    private String product;

    /**
     * 合作时间
     */
    @Column(name = "cooperationTime", columnDefinition = "VARCHAR(50)   COMMENT '合作时间'")
    private String cooperationTime;

    /**
     * 合作期限
     */
    @Column(name = "cooperationTerm", columnDefinition = "VARCHAR(50)   COMMENT '合作期限'")
    private String cooperationTerm;


    public SupplierInformation getInformation() {
        return information;
    }

    public void setInformation(SupplierInformation information) {
        this.information = information;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCooperationTime() {
        return cooperationTime;
    }

    public void setCooperationTime(String cooperationTime) {
        this.cooperationTime = cooperationTime;
    }

    public String getCooperationTerm() {
        return cooperationTerm;
    }

    public void setCooperationTerm(String cooperationTerm) {
        this.cooperationTerm = cooperationTerm;
    }
}