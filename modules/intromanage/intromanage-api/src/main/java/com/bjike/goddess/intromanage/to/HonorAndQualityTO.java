package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 荣誉与资质
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HonorAndQualityTO extends BaseTO {

    /**
     * 公司名称
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