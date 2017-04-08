package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 问卷调查历史记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyQuestionnaireUserTO extends BaseTO {

    /**
     * 调研实施id
     */
    @NotNull(message = "调研实施id不能为空", groups = {ADD.class, EDIT.class})
    private String actualize_id;

    /**
     * 用户
     */
    private String user;


    public String getActualize_id() {
        return actualize_id;
    }

    public void setActualize_id(String actualize_id) {
        this.actualize_id = actualize_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}