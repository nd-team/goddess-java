package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 工作表现计分方式设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScoreFormulaSetTO extends BaseTO {

    /**
     * 等级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "等级不能为空")
    private String rank;

    /**
     * 最低分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "最低分数不能为空")
    private Integer lowestScore;

    /**
     * 最高分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "最高分数不能为空")
    private Integer highestScore;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(Integer lowestScore) {
        this.lowestScore = lowestScore;
    }

    public Integer getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(Integer highestScore) {
        this.highestScore = highestScore;
    }

}