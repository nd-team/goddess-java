package com.bjike.goddess.contractcommunicat.vo;

/**
 * 历史评价表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HistoryAppraiseVO {

    /**
     * id
     */
    private String id;
    /**
     * 历史评价
     */
    private String historyAppraise;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistoryAppraise() {
        return historyAppraise;
    }

    public void setHistoryAppraise(String historyAppraise) {
        this.historyAppraise = historyAppraise;
    }
}