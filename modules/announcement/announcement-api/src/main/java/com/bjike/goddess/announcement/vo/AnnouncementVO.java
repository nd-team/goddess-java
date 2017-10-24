package com.bjike.goddess.announcement.vo;


import com.bjike.goddess.announcement.enums.Status;

import java.util.List;

/**
 * 公告表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnouncementVO {

    /**
     * id
     */
    private String id;
    /**
     * 编号
     */
    private String number;

    /**
     * 分类
     */
    private String classify;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 发布内容
     */
    private String publishContent;

    /**
     * 发布对象
     */
    private String recipient;

    /**
     * 公告状态
     */
    private Status status;

    /**
     * 是否已经读取
     */
    private Boolean haveRead;

    /**
     * 图片路径数组
     */
    private List<String> photos;

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Boolean getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(Boolean haveRead) {
        this.haveRead = haveRead;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
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

}