package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 荣誉与资质业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HonorAndQualityBO extends BaseBO {

    /**
     * 公司记录id
     */
    private String firmId;

    /**
     * 各类证书
     */
    private String certificates;

    /**
     * 软件著作权
     */
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