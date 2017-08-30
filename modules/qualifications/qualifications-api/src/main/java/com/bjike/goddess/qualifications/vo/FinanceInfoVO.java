package com.bjike.goddess.qualifications.vo;

/**
 * 财务资料表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 财务报表
     */
    private String reporting;

    /**
     * 审核资料
     */
    private String material;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReporting() {
        return reporting;
    }

    public void setReporting(String reporting) {
        this.reporting = reporting;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}