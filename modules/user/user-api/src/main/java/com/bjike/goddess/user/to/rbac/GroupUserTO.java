package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 组用户传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupUserTO extends BaseTO {
    /**
     * 组id
     */
    @NotBlank(message = "组id不能为空", groups = {ADD.class, EDIT.class})
    private String groupId;
    /**
     * 用戶id
     */
    @NotBlank(message = "用户id不能为空", groups ={ADD.class, EDIT.class})
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
