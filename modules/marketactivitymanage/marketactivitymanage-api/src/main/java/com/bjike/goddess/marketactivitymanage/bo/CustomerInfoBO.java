package com.bjike.goddess.marketactivitymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户信息业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [ 客户信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoBO extends BaseBO {

    /**
     * 客户信息编号
     */
    private String clientInfoNo;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 重要性级别
     */
    private String importanceLevel;

    /**
     * 市场招待唯一标识
     */
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