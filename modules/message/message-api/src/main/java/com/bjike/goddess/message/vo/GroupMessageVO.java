package com.bjike.goddess.message.vo;

/**
 * 组消息表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:07:50.366 ]
 * @Description: [ 组消息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupMessageVO {

    /**
     * id
     */
    private String id;
    /**
     * 所属组
     */
    private String groupId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}