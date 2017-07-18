package com.bjike.goddess.announcement.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 公告对应的发布对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-12 10:34 ]
 * @Description: [ 公告对应的发布对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnouncementUserTO extends BaseTO {

    /**
     * 是否已经读取
     */
    private Boolean haveRead;

    /**
     * 公告
     */
    private String announcementId;

    /**
     * 用户信息
     */
    private String userId;

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(Boolean haveRead) {
        this.haveRead = haveRead;
    }
}