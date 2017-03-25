package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.recruit.type.TemplateStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "recruit_templatemanage")
public class TemplateManage extends BaseEntity {

    /**
     * 模板名称
     */
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '模板名称' ")
    private String templteName;

    /**
     * 模板类型
     */
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '模板类型' ")
    private String templateType;

    /**
     * 模板内容
     */
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '模板内容' ")
    private String templateContent;

    /**
     * 模板状态
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 1 COMMENT '模板状态' ")
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
