package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 成功案例
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SuccessStoriesTO extends BaseTO {

    /**
     * 公司记录id
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