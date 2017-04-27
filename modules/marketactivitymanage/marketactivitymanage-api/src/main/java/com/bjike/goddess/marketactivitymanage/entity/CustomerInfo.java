package com.bjike.goddess.marketactivitymanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketactivitymanage_customerinfo")
public class CustomerInfo extends BaseEntity {

    /**
     * 客户信息编号
     */
    @Column(name = "clientInfoNo", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '客户信息编号'")
    private String clientInfoNo;

    /**
     * 客户姓名
     */
    @Column(name = "clientName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户姓名'")
    private String clientName;

    /**
     * 重要性级别
     */
    @Column(name = "importanceLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '重要性级别'")
    private String importanceLevel;

    /**
     * 市场招待唯一标识
     */
    @Column(name = "marketServeId", length = 36, nullable = false, columnDefinition = "VARCHAR(36) COMMENT '市场招待唯一标识'")
    private String marketServeId;


    public String getClientInfoNo() {
        return clientInfoNo;
    }

    public void setClientInfoNo(String clientInfoNo) {
        this.clientInfoNo = clientInfoNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public String getMarketServeId() {
        return marketServeId;
    }

    public void setMarketServeId(String marketServeId) {
        this.marketServeId = marketServeId;
    }
}