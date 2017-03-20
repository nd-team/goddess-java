package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 企业资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.257 ]
 * @Description: [ 企业资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EnterpriseQualificationTO extends BaseTO {

    /**
     * 企业资质id数组
     */
    private String[] enterprise_ids;

    /**
     * 企业资质序号
     */
    private String[] qualificationNumber;

    /**
     * 证书名称
     */
    private String[] qualificationName;

    /**
     * 证书编号
     */
    private String[] certificateNumber;

    /**
     * 有效期限
     */
    private String[] validityPeriod;

    /**
     * 颁发单位
     */
    private String[] promulgate;

    /**
     * 资质等级
     */
    private String[] aptitude;

    /**
     * 备注
     */
    private String[] remark;

    public String[] getQualificationNumber() {
        return qualificationNumber;
    }

    public void setQualificationNumber(String[] qualificationNumber) {
        this.qualificationNumber = qualificationNumber;
    }

    public String[] getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String[] qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String[] getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String[] certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String[] getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String[] validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String[] getPromulgate() {
        return promulgate;
    }

    public void setPromulgate(String[] promulgate) {
        this.promulgate = promulgate;
    }

    public String[] getAptitude() {
        return aptitude;
    }

    public void setAptitude(String[] aptitude) {
        this.aptitude = aptitude;
    }

    public String[] getRemark() {
        return remark;
    }

    public void setRemark(String[] remark) {
        this.remark = remark;
    }

    public String[] getEnterprise_ids() {
        return enterprise_ids;
    }

    public void setEnterprise_ids(String[] enterprise_ids) {
        this.enterprise_ids = enterprise_ids;
    }
}