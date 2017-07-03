package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 问卷填写信息表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_questionnaire_option_user")
public class SurveyQuestionnaireOptionUser extends BaseEntity {

    /**
     * 表名
     */
    @Column(name = "tableName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表名'")
    private String tableName;

    /**
     * 员工
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工'")
    private String user;

    /**
     * 选择选项
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "option_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '选择选项'")
    private SurveyQuestionnaireOption option;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public SurveyQuestionnaireOption getOption() {
        return option;
    }

    public void setOption(SurveyQuestionnaireOption option) {
        this.option = option;
    }
}