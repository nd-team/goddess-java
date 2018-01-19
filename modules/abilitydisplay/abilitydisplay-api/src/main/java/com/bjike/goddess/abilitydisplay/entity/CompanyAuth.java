package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 公司认证
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 05:31 ]
 * @Description: [ 公司认证 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_companyauth")
public class CompanyAuth extends BaseEntity {

    /**
     * 中兴技能认证证书数量
     */
    @Column(name = "zteNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '中兴技能认证证书数量'")
    private Integer zteNum;

    /**
     * 华为技能认证证书数量
     */
    @Column(name = "huaweiNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '华为技能认证证书数量'")
    private Integer huaweiNum;

    /**
     * 公司证书
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyAuthid")
    private Set<ComCertificate> certificate;


    public Integer getZteNum() {
        return zteNum;
    }

    public void setZteNum(Integer zteNum) {
        this.zteNum = zteNum;
    }

    public Integer getHuaweiNum() {
        return huaweiNum;
    }

    public void setHuaweiNum(Integer huaweiNum) {
        this.huaweiNum = huaweiNum;
    }

    public Set<ComCertificate> getCertificate() {
        return certificate;
    }

    public void setCertificate(Set<ComCertificate> certificate) {
        this.certificate = certificate;
    }
}