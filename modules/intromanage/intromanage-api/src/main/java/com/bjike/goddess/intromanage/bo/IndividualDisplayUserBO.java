package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 个人简介显示用户名称集合业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 04:12 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualDisplayUserBO extends BaseBO {

    /**
     * 用户姓名集合
     */
    private String usernames;

    /**
     * 个人简介显示字段id
     */
    private String displayId;


    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }
}