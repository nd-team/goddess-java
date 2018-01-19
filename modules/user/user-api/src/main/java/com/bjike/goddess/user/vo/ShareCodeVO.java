package com.bjike.goddess.user.vo;

import com.bjike.goddess.common.api.to.BaseTO;

import java.io.Serializable;

/**
 * 邀请码实体
 *
 * @Author: [chenyang]
 * @Date: [2017-01-15 09:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ShareCodeVO implements Serializable {


    /**
     * 用户ID
     */
    private String userId;
    /**
     * 邀请码
     */
    private String shareCode;
    /**
     * 邀请人ID
     */
    private String inviterId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
}
