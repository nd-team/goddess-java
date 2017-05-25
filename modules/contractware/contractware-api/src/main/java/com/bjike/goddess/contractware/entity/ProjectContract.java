package com.bjike.goddess.contractware.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 项目合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractware_projectcontract")
public class ProjectContract extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "times", columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate times;

    /**
     * 合同名称
     */
    @Column(name = "contractName", columnDefinition = "VARCHAR(255)   COMMENT '合同名称'")
    private String contractName;

    /**
     * 内部名称
     */
    @Column(name = "internalName", columnDefinition = "VARCHAR(255)   COMMENT '内部名称'")
    private String internalName;

    /**
     * 合同编号
     */
    @Column(name = "contractId", columnDefinition = "VARCHAR(255)   COMMENT '合同编号'")
    private String contractId;

    /**
     * 负责人
     */
    @Column(name = "principal", columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String principal;

    /**
     * 交接时间
     */
    @Column(name = "time", columnDefinition = "DATE   COMMENT '交接时间'")
    private LocalDate heirTime;

    /**
     * 交接人
     */
    @Column(name = "heir", columnDefinition = "VARCHAR(255)   COMMENT '交接人'")
    private String heir;

    /**
     * 交接数量
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '交接数量'")
    private Integer heirCount;

    /**
     * 保管人
     */
    @Column(name = "preserver", columnDefinition = "VARCHAR(255)   COMMENT '保管人'")
    private String preserver;

    /**
     * 保管地点
     */
    @Column(name = "preserverPlace", columnDefinition = "VARCHAR(255)   COMMENT '保管地点'")
    private String preserverPlace;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getTimes() {
        return times;
    }

    public void setTimes(LocalDate times) {
        this.times = times;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public LocalDate getHeirTime() {
        return heirTime;
    }

    public void setHeirTime(LocalDate heirTime) {
        this.heirTime = heirTime;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public Integer getHeirCount() {
        return heirCount;
    }

    public void setHeirCount(Integer heirCount) {
        this.heirCount = heirCount;
    }

    public String getPreserver() {
        return preserver;
    }

    public void setPreserver(String preserver) {
        this.preserver = preserver;
    }

    public String getPreserverPlace() {
        return preserverPlace;
    }

    public void setPreserverPlace(String preserverPlace) {
        this.preserverPlace = preserverPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}