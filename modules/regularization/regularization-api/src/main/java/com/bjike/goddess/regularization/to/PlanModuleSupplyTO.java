package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 规划模块补充TO
 *
 * @Author: [sunfengtao]
 * @Date: [2017-06-02 10:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PlanModuleSupplyTO extends BaseTO {

    public interface PlanModuleSupply {
    }

    /**
     * 转正后岗位
     */
    @NotBlank(groups = {PlanModuleSupplyTO.PlanModuleSupply.class}, message = "转正后岗位不能为空")
    private String afterPost;

    /**
     * 转正技能定级
     */
    @NotBlank(groups = {PlanModuleSupplyTO.PlanModuleSupply.class}, message = "转正技能定级不能为空")
    private String afterSkillRank;

    /**
     * 规划模块转正意见
     */
    @NotBlank(groups = {PlanModuleSupplyTO.PlanModuleSupply.class}, message = "规划模块转正意见不能为空")
    private String planPositiveComment;

    public String getAfterPost() {
        return afterPost;
    }

    public void setAfterPost(String afterPost) {
        this.afterPost = afterPost;
    }

    public String getAfterSkillRank() {
        return afterSkillRank;
    }

    public void setAfterSkillRank(String afterSkillRank) {
        this.afterSkillRank = afterSkillRank;
    }

    public String getPlanPositiveComment() {
        return planPositiveComment;
    }

    public void setPlanPositiveComment(String planPositiveComment) {
        this.planPositiveComment = planPositiveComment;
    }

}
