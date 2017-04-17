package com.bjike.goddess.user.bo.rbac;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 组用户传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-01 11:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupUserBO extends BaseBO {
    private String groupId;
    private String userId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
