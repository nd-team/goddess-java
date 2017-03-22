package com.bjike.goddess.supplier.vo;

/**
 * 企业资质表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.258 ]
 * @Description: [ 企业资质表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EnterpriseQualificationVO {

    /**
     * id
     */
    private String id;
    /**
     * 供应商基本信息
     */
    private String information_id;

    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书编号
     */
    private String certificateNumber;

    /**
     * 有效期限
     */
    private String validityPeriod;

    /**
     * 颁发单位
     */
    private String promulgate;

    /**
     * 资质等级
     */
    private String aptitude;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getInformation_id() {
        return information_id;
    }

    public void setInformation_id(String information_id) {
        this.information_id = information_id;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPromulgate() {
        return promulgate;
    }

    public void setPromulgate(String promulgate) {
        this.promulgate = promulgate;
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}