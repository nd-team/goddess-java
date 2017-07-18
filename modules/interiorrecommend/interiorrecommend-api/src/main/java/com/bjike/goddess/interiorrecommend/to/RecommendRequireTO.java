package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.interiorrecommend.enums.AssessWay;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐要求
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendRequireTO extends BaseTO {

    /**
     * 推荐方案id
     */
    @NotBlank(message = "推荐方案id不能为空", groups = {ADD.class, EDIT.class})
    private String recommendSchemeId;

    /**
     * 推荐时长
     */
    @NotNull(message = "推荐时长不能为空", groups = {ADD.class, EDIT.class})
    private Integer recommendTime;

    /**
     * 推荐类型id
     */
    @NotBlank(message = "推荐类型id不能为空", groups = {ADD.class, EDIT.class})
    private String recommendTypeId;

    /**
     * 推荐考核内容
     */
    @NotNull(message = "推荐考核内容不能为空", groups = {ADD.class, EDIT.class})
    private List<RecommendAssessDetailTO> assessDetailList;

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
    @NotNull(message = "考核方式不能为空", groups = {ADD.class, EDIT.class})
    private AssessWay assessWay;

    /**
     * 推荐发起人
     */
    private String recommendSponsor;


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

    public List<RecommendAssessDetailTO> getAssessDetailList() {
        return assessDetailList;
    }

    public void setAssessDetailList(List<RecommendAssessDetailTO> assessDetailList) {
        this.assessDetailList = assessDetailList;
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