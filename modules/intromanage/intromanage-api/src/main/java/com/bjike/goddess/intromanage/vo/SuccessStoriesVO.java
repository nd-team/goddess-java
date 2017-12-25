package com.bjike.goddess.intromanage.vo;

/**
 * 成功案例表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SuccessStoriesVO {

    /**
     * id
     */
    private String id;

    /**
     * 公司名称
     */
    private String firmId;

    /**
     * 通信类
     */
    private String communication;

    /**
     * 软件类
     */
    private String software;

    /**
     * 系统继承类
     */
    private String systemIntegration;

    /**
     * 营销策划类
     */
    private String marketingPlanning;


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

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getSystemIntegration() {
        return systemIntegration;
    }

    public void setSystemIntegration(String systemIntegration) {
        this.systemIntegration = systemIntegration;
    }

    public String getMarketingPlanning() {
        return marketingPlanning;
    }

    public void setMarketingPlanning(String marketingPlanning) {
        this.marketingPlanning = marketingPlanning;
    }
}