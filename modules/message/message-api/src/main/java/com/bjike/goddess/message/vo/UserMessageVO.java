package com.bjike.goddess.message.vo;

/**
 * 用户消息表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:40:27.609 ]
 * @Description: [ 用户消息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UserMessageVO {

    /**
     * id
     */
    private String id;
    /**
     * 所属用户
     */
    private String userId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}