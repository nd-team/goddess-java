package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.type.TemplateStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TemplateManageTO extends BaseTO {

    /**
     * 模板名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板名称不能为空")
    private String templteName;

    /**
     * 模板类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板类型不能为空")
    private String templateType;

    /**
     * 模板内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "模板内容不能为空")
    private String templateContent;

    /**
     * 模板状态
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "模板状态不能为空")
    private TemplateStatus templateStatus;

    public String getTemplteName() {
        return templteName;
    }

    public void setTemplteName(String templteName) {
        this.templteName = templteName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public TemplateStatus getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(TemplateStatus templateStatus) {
        this.templateStatus = templateStatus;
    }
}
