package com.bjike.goddess.democraticmeet.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 民主生活会议组织内容数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemocraticContentDTO extends BaseDTO {

    /**
     * 会议议题
     */
    private String meetTitle;


    /**
     * 会议组织人
     */
    private String organizationMan;

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getOrganizationMan() {
        return organizationMan;
    }

    public void setOrganizationMan(String organizationMan) {
        this.organizationMan = organizationMan;
    }
}