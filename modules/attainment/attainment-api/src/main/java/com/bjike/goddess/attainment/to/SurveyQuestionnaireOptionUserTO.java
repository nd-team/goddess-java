package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 问卷填写信息表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireOptionUserTO extends BaseTO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 员工
     */
    private String user;

    /**
     * 选择选项id
     */
    private String option_id;


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

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }
}