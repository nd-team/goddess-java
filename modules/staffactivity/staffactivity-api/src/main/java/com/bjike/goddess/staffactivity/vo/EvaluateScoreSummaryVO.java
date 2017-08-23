package com.bjike.goddess.staffactivity.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 活动评价得分汇总
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-10 10:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EvaluateScoreSummaryVO extends BaseBO {

    /**
     * 活动方案
     */
    private String scheme;

    /**
     * 活动总得分
     */
    private Integer totalScores;

    /**
     * 组织者评分占总分的比例
     */
    private String organizerRatio;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Integer getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(Integer totalScores) {
        this.totalScores = totalScores;
    }

    public String getOrganizerRatio() {
        return organizerRatio;
    }

    public void setOrganizerRatio(String organizerRatio) {
        this.organizerRatio = organizerRatio;
    }
}
