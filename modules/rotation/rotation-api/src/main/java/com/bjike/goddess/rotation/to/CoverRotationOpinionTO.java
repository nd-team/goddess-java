package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 岗位轮换自荐意见
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CoverRotationOpinionTO extends BaseTO {

    /**
     * 岗位轮换自荐
     */
    private String coverId;

    /**
     * 意见
     */
    private String opinion;


    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}