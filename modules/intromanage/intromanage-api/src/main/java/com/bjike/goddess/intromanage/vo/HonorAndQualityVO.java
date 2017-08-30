package com.bjike.goddess.intromanage.vo;

/**
 * 荣誉与资质表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HonorAndQualityVO {

    /**
     * id
     */
    private String id;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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