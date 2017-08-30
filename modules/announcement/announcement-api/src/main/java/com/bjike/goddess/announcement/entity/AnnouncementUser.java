package com.bjike.goddess.announcement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 公告对应的发布对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-12 10:34 ]
 * @Description: [ 公告对应的发布对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "announcement_announcementuser")
public class AnnouncementUser extends BaseEntity {

    /**
     * 是否已经读取
     */
    @Column(name = "is_haveRead",columnDefinition = "TINYINT(1) COMMENT '是否已经读取'")
    private Boolean haveRead;

    /**
     * 公告
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "announcement_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '公告id' ")
    private Announcement announcement;

    /**
     * 用户信息
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '用户id'")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Boolean getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(Boolean haveRead) {
        this.haveRead = haveRead;
    }
}