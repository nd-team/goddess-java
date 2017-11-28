package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 历史评价
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HistoryAppraiseTO extends BaseTO {

    /**
     * 历史评价
     */
    private String historyAppraise;


    public String getHistoryAppraise() {
        return historyAppraise;
    }

    public void setHistoryAppraise(String historyAppraise) {
        this.historyAppraise = historyAppraise;
    }
}