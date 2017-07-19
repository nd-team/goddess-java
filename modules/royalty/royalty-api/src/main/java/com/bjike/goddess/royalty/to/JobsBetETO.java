package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位间对赌表D
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表D ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetETO extends BaseTO {
    /**
     * 分配对赌权重（%）
     */
    @NotNull(message = "分配对赌权重（%）不能为空",groups = {ADD.class, EDIT.class})
    private Double betWeight;

    /**
     * 指标编号
     */
    @NotBlank(message = "指标编号不能为空",groups = {ADD.class, EDIT.class})
    private String indexNum;

    /**
     * 指标名称
     */
    @NotBlank(message = "指标名称不能为空",groups = {ADD.class, EDIT.class})
    private String indexName;

    /**
     * 对赌承诺-确认目标值
     */
    @NotBlank(message = "对赌承诺-确认目标值不能为空",groups = {ADD.class, EDIT.class})
    private String confirmTargetValue;

    /**
     * 实际目标值
     */
    @NotBlank(message = "实际目标值不能为空",groups = {ADD.class, EDIT.class})
    private String actualTargetValue;

    /**
     * 是否达标
     */
    @NotNull(message = "是否达标不能为空",groups = {ADD.class, EDIT.class})
    private Boolean standard;

    /**
     * 对赌得分（部门总得分*目标-部门分配对赌权重）
     */
    private Double betScore;
    /**
     * 岗位间对赌表F
     */
    private List<JobsBetFTO> jobsBetFTOS;

    public Double getBetWeight() {
        return betWeight;
    }

    public void setBetWeight(Double betWeight) {
        this.betWeight = betWeight;
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getActualTargetValue() {
        return actualTargetValue;
    }

    public void setActualTargetValue(String actualTargetValue) {
        this.actualTargetValue = actualTargetValue;
    }

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public Double getBetScore() {
        return betScore;
    }

    public void setBetScore(Double betScore) {
        this.betScore = betScore;
    }

    public List<JobsBetFTO> getJobsBetFTOS() {
        return jobsBetFTOS;
    }

    public void setJobsBetFTOS(List<JobsBetFTO> jobsBetFTOS) {
        this.jobsBetFTOS = jobsBetFTOS;
    }
}