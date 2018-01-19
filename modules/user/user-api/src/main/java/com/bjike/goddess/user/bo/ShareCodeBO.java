package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 邀请码业务传输对象
 *
 * @Author: [chenyang]
 * @Date: [2017-01-15 09:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ShareCodeBO extends BaseTO {


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
