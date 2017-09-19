package com.bjike.goddess.interiorrecommend.vo;

import com.bjike.goddess.interiorrecommend.enums.RecommendType;

import java.time.LocalDate;

/**
* 内部推荐奖管理汇总表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectAwardVO {

    /**
     * 制定内部推荐奖方案
     */
    private Long  schemeNumber;

    /**
     * 推荐奖预算费用
     */
    private Long  recommendAward;

    /**
     * 实施方案数
     */
    private Long  embodimentNumber;

    /**
     * 推荐人数
     */
    private Long recommenderNumber;

    /**
     * 推荐入职人数
     */
    private Long recommendEntry;


    public Long getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(Long schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public Long getRecommendAward() {
        return recommendAward;
    }

    public void setRecommendAward(Long recommendAward) {
        this.recommendAward = recommendAward;
    }

    public Long getEmbodimentNumber() {
        return embodimentNumber;
    }

    public void setEmbodimentNumber(Long embodimentNumber) {
        this.embodimentNumber = embodimentNumber;
    }

    public Long getRecommenderNumber() {
        return recommenderNumber;
    }

    public void setRecommenderNumber(Long recommenderNumber) {
        this.recommenderNumber = recommenderNumber;
    }

    public Long getRecommendEntry() {
        return recommendEntry;
    }

    public void setRecommendEntry(Long recommendEntry) {
        this.recommendEntry = recommendEntry;
    }
}