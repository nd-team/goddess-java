package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.interiorrecommend.entity.CollectAward;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 内部推荐奖管理汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectAwardBO extends BaseBO {
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

    /**
     * 推荐转正人数
     */
    private Long recommendRegular;

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

    public Long getRecommendRegular() {
        return recommendRegular;
    }

    public void setRecommendRegular(Long recommendRegular) {
        this.recommendRegular = recommendRegular;
    }

    public CollectAwardBO(){

    }

    public CollectAwardBO(Long schemeNumber,Long recommendAward,Long embodimentNumber,Long recommenderNumber,Long recommendEntry,Long recommendRegular){
        this.schemeNumber = schemeNumber;
        this.recommendAward = recommendAward;
        this.embodimentNumber = embodimentNumber;
        this.recommenderNumber = recommenderNumber;
        this.recommendEntry = recommendEntry;
    }
}