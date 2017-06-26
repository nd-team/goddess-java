package com.bjike.goddess.democraticmeet.vo;

/**
 * 民主生活会议组织内容表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemocraticContentVO {

    /**
     * id
     */
    private String id;
    /**
     * 会议类型
     */
    private String meetType;

    /**
     * 会议议题
     */
    private String meetTitle;

    /**
     * 会议内容
     */
    private String meetContent;

    /**
     * 会议组织人
     */
    private String organizationMan;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 会议组织部分内容
     */
    private MeetDesignVO meetDesignVO;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getMeetContent() {
        return meetContent;
    }

    public void setMeetContent(String meetContent) {
        this.meetContent = meetContent;
    }

    public String getOrganizationMan() {
        return organizationMan;
    }

    public void setOrganizationMan(String organizationMan) {
        this.organizationMan = organizationMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public MeetDesignVO getMeetDesignVO() {
        return meetDesignVO;
    }

    public void setMeetDesignVO(MeetDesignVO meetDesignVO) {
        this.meetDesignVO = meetDesignVO;
    }
}