package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserTO extends BaseTO {

    /**
     * 用户信息
     */
    @NotNull(message = "用户信息不能为空", groups = {ADD.class, EDIT.class})
    private String userId;

    /**
     * 岗位详细数据id
     */
    private String[] positionIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String[] positionIds) {
        this.positionIds = positionIds;
    }
}