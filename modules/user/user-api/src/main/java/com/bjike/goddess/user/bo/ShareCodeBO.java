package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.to.BaseTO;

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
     * 用户积分；
     */
    private String integral;

    /**
     * 邀请人积分；
     */
    private String inviterIntegral;

    /**
     * 邀请人邀请码
     */
    private String inviterShareCode;

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

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getInviterIntegral() {
        return inviterIntegral;
    }

    public void setInviterIntegral(String inviterIntegral) {
        this.inviterIntegral = inviterIntegral;
    }

    public String getInviterShareCode() {
        return inviterShareCode;
    }

    public void setInviterShareCode(String inviterShareCode) {
        this.inviterShareCode = inviterShareCode;
    }
}
