package com.bjike.goddess.message.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 组消息
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:07:50.366 ]
 * @Description: [ 组消息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupMessageTO extends BaseTO {

    /**
     * 所属组
     */
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}