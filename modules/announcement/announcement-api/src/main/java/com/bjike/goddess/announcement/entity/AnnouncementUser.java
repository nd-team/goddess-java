package com.bjike.goddess.announcement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


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
    @Column(name = "is_haveRead", columnDefinition = "TINYINT(1) COMMENT '是否已经读取'")
    private Boolean haveRead;

    /**
     * 公告
     */
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
//    @JoinColumn(name = "announcement_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '公告id' ")
    @Transient
    private Announcement announcement;

    /**
     * 公告id
     */
    @Column(name = "announcement_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '公告id' ")
    private String announcementId;

    /**
     * 用户信息
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '用户id'")
    private String name;

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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