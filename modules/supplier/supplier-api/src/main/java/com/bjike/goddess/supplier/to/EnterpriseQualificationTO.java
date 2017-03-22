package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

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
     * 供应商基本信息
     */
    @NotNull(message = "供应商信息传输错误", groups = {ADD.class, EDIT.class})
    private String information_id;

    /**
     * 序号
     */
    @NotNull(message = "序号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 证书名称
     */
    @NotNull(message = "证书名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 证书编号
     */
    @NotNull(message = "证书编号不能为空", groups = {ADD.class, EDIT.class})
    private String certificateNumber;

    /**
     * 有效期限
     */
    @NotNull(message = "有效期限不能为空", groups = {ADD.class, EDIT.class})
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

    public String getInformation_id() {
        return information_id;
    }

    public void setInformation_id(String information_id) {
        this.information_id = information_id;
    }

    public String getSerialNumber() {
        return serialNumber;
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