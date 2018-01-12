package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.rotation.entity.CoverRotation;
import com.bjike.goddess.rotation.entity.RecommendRotation;

/**
 * 岗位轮换记录
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RotationRecordTO extends BaseTO {

    /**
     * 轮岗方式
     */
    private String rotationType;

    /**
     * 自荐表id
     */
    private CoverRotation coverRotation;

    /**
     * 推荐表id
     */
    private RecommendRotation recommendRotation;

    public String getRotationType() {
        return rotationType;
    }

    public void setRotationType(String rotationType) {
        this.rotationType = rotationType;
    }

    public CoverRotation getCoverRotation() {
        return coverRotation;
    }

    public void setCoverRotation(CoverRotation coverRotation) {
        this.coverRotation = coverRotation;
    }

    public RecommendRotation getRecommendRotation() {
        return recommendRotation;
    }

    public void setRecommendRotation(RecommendRotation recommendRotation) {
        this.recommendRotation = recommendRotation;
    }
}