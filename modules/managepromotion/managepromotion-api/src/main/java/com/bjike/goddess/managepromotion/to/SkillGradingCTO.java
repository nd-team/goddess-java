package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 技能定级C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingCTO extends BaseTO {


    /**
     * 转正后间隔时间
     */
    @NotNull(message = "转正后间隔时间不能为空",groups = {ADD.class, EDIT.class})
    private Integer intervalAfterTransfer;

    /**
     * 各档次晋升间隔时间（月）
     */
    @NotNull(message = "各档次晋升间隔时间（月）不能为空",groups = {ADD.class, EDIT.class})
    private Integer gradeAfterTime;

    /**
     * 技能职衔分数
     */
    @NotNull(message = "技能职衔分数不能为空",groups = {ADD.class, EDIT.class})
    private Integer technicalRank;

    /**
     * 转换额度（1分）
     */
    @NotNull(message = "转换额度（1分）不能为空",groups = {ADD.class, EDIT.class})
    private Integer convertLine;

    /**
     * 标准
     */
    @NotBlank(message = "标准不能为空",groups = {ADD.class, EDIT.class})
    private String standard;

    /**
     * 条件
     */
    @NotBlank(message = "条件不能为空",groups = {ADD.class, EDIT.class})
    private String conditions;

    /**
     * 补助额度（元）
     */
    @NotNull(message = "补助额度（元）不能为空",groups = {ADD.class, EDIT.class})
    private Integer subsidiesAmount;

    /**
     * 职衔补助额度(技能职衔分数*转换额度（1分）)
     */
    private Integer quotaJobTitle;

    /**
     * 补助额度合计(补助额度（元）+职衔补助额度)
     */
    private Integer totalAllowance;

    /**
     * 每次晋升增长幅度
     */
    private Integer growth;

    public Integer getIntervalAfterTransfer() {
        return intervalAfterTransfer;
    }

    public void setIntervalAfterTransfer(Integer intervalAfterTransfer) {
        this.intervalAfterTransfer = intervalAfterTransfer;
    }

    public Integer getGradeAfterTime() {
        return gradeAfterTime;
    }

    public void setGradeAfterTime(Integer gradeAfterTime) {
        this.gradeAfterTime = gradeAfterTime;
    }

    public Integer getTechnicalRank() {
        return technicalRank;
    }

    public void setTechnicalRank(Integer technicalRank) {
        this.technicalRank = technicalRank;
    }

    public Integer getConvertLine() {
        return convertLine;
    }

    public void setConvertLine(Integer convertLine) {
        this.convertLine = convertLine;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Integer getSubsidiesAmount() {
        return subsidiesAmount;
    }

    public void setSubsidiesAmount(Integer subsidiesAmount) {
        this.subsidiesAmount = subsidiesAmount;
    }

    public Integer getQuotaJobTitle() {
        return quotaJobTitle;
    }

    public void setQuotaJobTitle(Integer quotaJobTitle) {
        this.quotaJobTitle = quotaJobTitle;
    }

    public Integer getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(Integer totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }
}