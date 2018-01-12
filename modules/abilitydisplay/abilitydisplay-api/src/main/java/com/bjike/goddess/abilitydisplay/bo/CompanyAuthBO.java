package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.abilitydisplay.entity.ComCertificate;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * 公司认证业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 05:31 ]
 * @Description: [ 公司认证业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyAuthBO extends BaseBO {

    /**
     * 中兴技能认证证书数量
     */
    private Integer zteNum;

    /**
     * 华为技能认证证书数量
     */
    private Integer huaweiNum;

    /**
     * 公司证书
     */
    private Set<ComCertificateBO> certificateBOS;


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

    public Set<ComCertificateBO> getCertificateBOS() {
        return certificateBOS;
    }

    public void setCertificateBOS(Set<ComCertificateBO> certificateBOS) {
        this.certificateBOS = certificateBOS;
    }
}