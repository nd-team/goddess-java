package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 租车协议业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarRentalAgreementBO extends BaseBO {

    /**
     * 时间
     */
    private String times;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 内部名称
     */
    private String internalName;

    /**
     * 合同编号
     */
    private String contractId;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 交接时间
     */
    private String heirTime;

    /**
     * 交接人
     */
    private String heir;

    /**
     * 交接数量
     */
    private Integer heirCount;

    /**
     * 保管人
     */
    private String preserver;

    /**
     * 保管地点
     */
    private String preserverPlace;

    /**
     * 备注
     */
    private String remark;


    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
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

    public String getHeirTime() {
        return heirTime;
    }

    public void setHeirTime(String heirTime) {
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