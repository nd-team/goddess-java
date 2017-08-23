package com.bjike.goddess.message.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 用户消息
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:40:27.608 ]
 * @Description: [ 用户消息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UserMessageTO extends BaseTO {

    /**
     * 所属用户
     */
    private String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}