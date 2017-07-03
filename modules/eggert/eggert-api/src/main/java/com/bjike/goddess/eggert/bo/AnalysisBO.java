package com.bjike.goddess.eggert.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 分析记录业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:23 ]
 * @Description: [ 分析记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalysisBO extends BaseBO {

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