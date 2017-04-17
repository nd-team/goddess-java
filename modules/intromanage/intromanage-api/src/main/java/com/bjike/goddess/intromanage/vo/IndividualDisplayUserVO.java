package com.bjike.goddess.intromanage.vo;

/**
 * 个人简介显示用户名称集合表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 04:12 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndividualDisplayUserVO {

    /**
     * id
     */
    private String id;
    /**
     * 用户姓名集合
     */
    private String usernames;

    /**
     * 个人简介显示字段id
     */
    private String displayId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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