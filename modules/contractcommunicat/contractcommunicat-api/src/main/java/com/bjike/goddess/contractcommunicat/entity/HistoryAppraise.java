package com.bjike.goddess.contractcommunicat.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 历史评价
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractcommunicat_historyappraise")
public class HistoryAppraise extends BaseEntity {

    /**
     * 历史评价
     */
    @Column(name = "historyAppraise", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '历史评价'")
    private String historyAppraise;


    public String getHistoryAppraise() {
        return historyAppraise;
    }

    public void setHistoryAppraise(String historyAppraise) {
        this.historyAppraise = historyAppraise;
    }
}