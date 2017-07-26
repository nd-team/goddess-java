package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 问卷填写信息表业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireOptionUsersBO extends BaseBO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 员工
     */
    private String user;

    /**
     * 选择选项
     */
    private String optionName;

    /**
     * 选择选项id
     */
    private String optionId;

    /**
     * 问题选项
     */
    private List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOList;


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

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public List<SurveyQuestionnaireOptionBO> getSurveyQuestionnaireOptionBOList() {
        return surveyQuestionnaireOptionBOList;
    }

    public void setSurveyQuestionnaireOptionBOList(List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOList) {
        this.surveyQuestionnaireOptionBOList = surveyQuestionnaireOptionBOList;
    }
}