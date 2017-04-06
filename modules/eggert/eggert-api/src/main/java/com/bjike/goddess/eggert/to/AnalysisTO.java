package com.bjike.goddess.eggert.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 分析记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:23 ]
 * @Description: [ 分析记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalysisTO extends BaseTO {

    /**
     * 意见
     */
    private String opinion;


    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}