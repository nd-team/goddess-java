package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.interiorrecommend.enums.AssessWay;

/**
 * 推荐要求业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendRequireBO extends BaseBO {

    /**
     * 推荐方案id
     */
    private String recommendSchemeId;

    /**
     * 推荐时长
     */
    private Integer recommendTime;

    /**
     * 推荐类型id
     */
    private String recommendTypeId;

    /**
     * 指标来源
     */
    private String indicatorResource;

    /**
     * 推荐途径
     */
    private String recommendWay;

    /**
     * 考核方式
     */
    private AssessWay assessWay;

    /**
     * 推荐发起人
     */
    private String recommendSponsor;

    /**
     * 推荐开通时间
     */
    private String openTime;

    /**
     * 推荐关闭时间
     */
    private String closeTime;

    /**
     * 推荐类型
     */
    private String recommendType;

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getRecommendSchemeId() {
        return recommendSchemeId;
    }

    public void setRecommendSchemeId(String recommendSchemeId) {
        this.recommendSchemeId = recommendSchemeId;
    }

    public Integer getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Integer recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getRecommendTypeId() {
        return recommendTypeId;
    }

    public void setRecommendTypeId(String recommendTypeId) {
        this.recommendTypeId = recommendTypeId;
    }

    public String getIndicatorResource() {
        return indicatorResource;
    }

    public void setIndicatorResource(String indicatorResource) {
        this.indicatorResource = indicatorResource;
    }

    public String getRecommendWay() {
        return recommendWay;
    }

    public void setRecommendWay(String recommendWay) {
        this.recommendWay = recommendWay;
    }

    public AssessWay getAssessWay() {
        return assessWay;
    }

    public void setAssessWay(AssessWay assessWay) {
        this.assessWay = assessWay;
    }

    public String getRecommendSponsor() {
        return recommendSponsor;
    }

    public void setRecommendSponsor(String recommendSponsor) {
        this.recommendSponsor = recommendSponsor;
    }
}