package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.TemplateStatus;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TemplateManageVO {

    /**
     * 模板名称
     */
    private String templteName;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 模板状态
     */
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
