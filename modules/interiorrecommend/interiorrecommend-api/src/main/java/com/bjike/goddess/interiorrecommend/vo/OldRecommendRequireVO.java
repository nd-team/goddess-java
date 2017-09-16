package com.bjike.goddess.interiorrecommend.vo;

import com.bjike.goddess.interiorrecommend.enums.AssessWay;

import java.util.List;

/**
 * 推荐要求表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OldRecommendRequireVO {

    /**
     * id
     */
    private String id;

    /**
     * 推荐考核内容
     */
    private List<OldRecommendAssessDetailVO> detailList;

    /**
     * 推荐时长
     */
    private Integer recommendTime;

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
    private String recommendTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Integer recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getIndicatorResource() {
        return indicatorResource;
    }

    public List<OldRecommendAssessDetailVO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OldRecommendAssessDetailVO> detailList) {
        this.detailList = detailList;
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

    public String getRecommendTypeName() {
        return recommendTypeName;
    }

    public void setRecommendTypeName(String recommendTypeName) {
        this.recommendTypeName = recommendTypeName;
    }
}