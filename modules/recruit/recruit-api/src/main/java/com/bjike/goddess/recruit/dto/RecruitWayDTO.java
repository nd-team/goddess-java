package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitWayDTO extends BaseDTO {
    /**
     * 渠道联系人
     */
    private String channelContact;
    /**
     * 招聘网站
     */
    private String recruitSite;
    /**
     * 状态
     */
    private Status status;

    public String getChannelContact() {
        return channelContact;
    }

    public void setChannelContact(String channelContact) {
        this.channelContact = channelContact;
    }

    public String getRecruitSite() {
        return recruitSite;
    }

    public void setRecruitSite(String recruitSite) {
        this.recruitSite = recruitSite;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
