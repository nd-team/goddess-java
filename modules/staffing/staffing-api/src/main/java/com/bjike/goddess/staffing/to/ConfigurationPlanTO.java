package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 人数配置-计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConfigurationPlanTO extends BaseTO {

    /**
     * 分类
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类不能为空")
    private String classify;

    /**
     * 子表信息
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "子表信息不能为空")
    private PlanSonTO planSonTO;

    /**
     * 占比标准
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "占比标准不能为空")
    private String standard;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public PlanSonTO getPlanSonTO() {
        return planSonTO;
    }

    public void setPlanSonTO(PlanSonTO planSonTO) {
        this.planSonTO = planSonTO;
    }
}