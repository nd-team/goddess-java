package com.bjike.goddess.marketactivitymanage.vo;

/**
 * 客户信息表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoVO {

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

}