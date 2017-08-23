package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 证书情况业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:52 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CredentialSituationBO extends BaseBO {

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 获取的专业证书
     */
    private String certificateTitle;

    /**
     * 获取证书的时间
     */
    private String certificateTime;

    /**
     * 证书编号
     */
    private String certificateNo;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCertificateTitle() {
        return certificateTitle;
    }

    public void setCertificateTitle(String certificateTitle) {
        this.certificateTitle = certificateTitle;
    }

    public String getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(String certificateTime) {
        this.certificateTime = certificateTime;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }
}