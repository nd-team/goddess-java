package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 成功案例
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_successstories")
public class SuccessStories extends BaseEntity {

    /**
     * 公司记录id
     */
    @Column(name = "firmId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司记录id'")
    private String firmId;

    /**
     * 通信类
     */
    @Column(name = "communication", columnDefinition = "VARCHAR(255) COMMENT '通信类'")
    private String communication;

    /**
     * 软件类
     */
    @Column(name = "software", columnDefinition = "VARCHAR(255) COMMENT '软件类'")
    private String software;

    /**
     * 系统集成类
     */
    @Column(name = "systemIntegration", columnDefinition = "VARCHAR(255) COMMENT '系统集成类'")
    private String systemIntegration;

    /**
     * 营销策划类
     */
    @Column(name = "marketingPlanning", columnDefinition = "VARCHAR(255) COMMENT '营销策划类'")
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