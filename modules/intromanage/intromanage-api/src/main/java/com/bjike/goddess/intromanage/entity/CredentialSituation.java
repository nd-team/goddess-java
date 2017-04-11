package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 证书情况
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:52 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_credentialsituation")
public class CredentialSituation extends BaseEntity {

    /**
     * 员工id
     */
    @Column(name = "staffId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String staffId;

    /**
     * 获取的专业证书
     */
    @Column(name = "certificateTitle", columnDefinition = "VARCHAR(255) COMMENT '获取的专业证书'")
    private String certificateTitle;

    /**
     * 获取证书的时间
     */
    @Column(name = "certificateTime",columnDefinition = "VARCHAR(255) COMMENT '获取证书的时间'")
    private String certificateTime;

    /**
     * 证书编号
     */
    @Column(name = "certificateNo", columnDefinition = "VARCHAR(255) COMMENT '证书编号'")
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