package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 移动端版本
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_mobileversion")
public class MobileVersion extends BaseEntity {

    /**
     * 作者
     */
    @Column(name = "author",  columnDefinition = "VARCHAR(255)   COMMENT '作者'")
    private String author;

    /**
     * 项目名称
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String name;

    /**
     * 版本描述
     */
    @Column(name = "description",  columnDefinition = "VARCHAR(255)   COMMENT '版本描述'")
    private String description;

    /**
     * 时间
     */
    @Column(name = "time",  columnDefinition = "DATETIME   COMMENT '时间'")
    private LocalDateTime time;

    /**
     * 版本号
     */
    @Column(name = "version",  columnDefinition = "VARCHAR(255)   COMMENT '版本号'")
    private String version;

    /**
     * 下载地址
     */
    @Column(name = "url",  columnDefinition = "VARCHAR(255)   COMMENT '下载地址'")
    private String url;

    /**
     * 状态
     */
    @Column(name = "status",  columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}