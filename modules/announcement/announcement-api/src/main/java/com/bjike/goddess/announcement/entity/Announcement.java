package com.bjike.goddess.announcement.entity;

import com.bjike.goddess.announcement.enums.Status;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * 公告
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "announcement_announcement")
public class Announcement extends BaseEntity {

    /**
     * 编号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '编号'")
    private String number;

    /**
     * 分类
     */
    @Column(name = "classify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classify;

    /**
     * 标题
     */
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标题'")
    private String title;

    /**
     * 作者
     */
    @Column(name = "author", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '作者'")
    private String author;

    /**
     * 发布日期
     */
    @Column(name = "publishDate", nullable = false, columnDefinition = "DATE   COMMENT '发布日期'")
    private LocalDate publishDate;

    /**
     * 发布内容
     */
    @Column(name = "publishContent", nullable = false, columnDefinition = "TEXT   COMMENT '发布内容'")
    private String publishContent;

    /**
     * 发布对象
     */
    @Column(name = "recipient", nullable = false, columnDefinition = "TEXT   COMMENT '发布对象'")
    private String recipient;

    /**
     * 是否必读
     */
    @Column(name = "is_required", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否必读'")
    private Boolean required;

    /**
     * 是否发送邮件
     */
    @Column(name = "is_send", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否发送邮件'")
    private Boolean send;

//    /**
//     * 是否已经读取
//     */
//    @Column(name = "is_haveRead", columnDefinition = "TINYINT(1)   COMMENT '是否已经读取'")
//    private Boolean haveRead;

    /**
     * 公告状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '公告状态'")
    private Status status;
    /**
     * uuid
     */
    @Column(name = "uuid", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'uuid'")
    private String uuid;

    /**
     * 公告对应的发布对象
     */
//    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "announcement")
    @Transient
    private Set<AnnouncementUser> announcementUsers = new HashSet<AnnouncementUser>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<AnnouncementUser> getAnnouncementUsers() {
        return announcementUsers;
    }

    public void setAnnouncementUsers(Set<AnnouncementUser> announcementUsers) {
        this.announcementUsers = announcementUsers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }
}