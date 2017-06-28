package com.bjike.goddess.enterpriseculturemanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.enterpriseculturemanage.enums.UpdateType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 企业文化信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EnterpriseCultureInfoEditTO extends BaseTO {

    /**
     * 主题
     */
    @NotBlank(message = "主题不能为空",groups = {EDIT.class})
    private String theme;

    /**
     * 执行阶段
     */
    @NotBlank(message = "执行阶段不能为空",groups = {EDIT.class})
    private String executeStage;

    /**
     * 执行问题
     */
    @NotBlank(message = "执行问题不能为空",groups = {EDIT.class})
    private String executeQuestion;

    /**
     * 解决方案
     */
    @NotBlank(message = "解决方案不能为空",groups = {EDIT.class})
    private String solution;

    /**
     * 员工意见
     */
    @NotBlank(message = "员工意见不能为空",groups = {EDIT.class})
    private String employeeSuggest;

    /**
     * 更新类型
     */
    @NotNull(message = "员工意见不能为空",groups = {EDIT.class})
    private UpdateType updateType;


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getExecuteStage() {
        return executeStage;
    }

    public void setExecuteStage(String executeStage) {
        this.executeStage = executeStage;
    }

    public String getExecuteQuestion() {
        return executeQuestion;
    }

    public void setExecuteQuestion(String executeQuestion) {
        this.executeQuestion = executeQuestion;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getEmployeeSuggest() {
        return employeeSuggest;
    }

    public void setEmployeeSuggest(String employeeSuggest) {
        this.employeeSuggest = employeeSuggest;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }
}