package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 荣誉与资质
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [ 荣誉与资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_honorandquality")
public class HonorAndQuality extends BaseEntity {

    /**
     * 公司记录id
     */
    @Column(name = "firmId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司记录id'")
    private String firmId;

    /**
     * 各类证书
     */
    @Column(name = "certificates", columnDefinition = "VARCHAR(255) COMMENT '各类证书'")
    private String certificates;

    /**
     * 软件著作权
     */
    @Column(name = "softwareCopyright", columnDefinition = "VARCHAR(255) COMMENT '软件著作权'")
    private String softwareCopyright;

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getSoftwareCopyright() {
        return softwareCopyright;
    }

    public void setSoftwareCopyright(String softwareCopyright) {
        this.softwareCopyright = softwareCopyright;
    }
}