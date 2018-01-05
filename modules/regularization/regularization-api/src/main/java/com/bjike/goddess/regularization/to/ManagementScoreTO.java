package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 管理层评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ManagementScoreTO extends BaseTO {

    public interface IManagementScore{}

    /**
     * 管理层意见
     */
    @NotBlank(groups = {ManagementScoreTO.IManagementScore.class}, message = "管理层意见不能为空")
        private String opinion;

    /**
     * 评分等级
     */
    @NotBlank(groups = {ManagementScoreTO.IManagementScore.class}, message = "评分等级不能为空")
    private String scoreGrade;

    /**
     * 具体分数
     */
    @NotNull(groups = {ManagementScoreTO.IManagementScore.class}, message = "具体分数不能为空")
    private Integer specificScore;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public Integer getSpecificScore() {
        return specificScore;
    }

    public void setSpecificScore(Integer specificScore) {
        this.specificScore = specificScore;
    }

}