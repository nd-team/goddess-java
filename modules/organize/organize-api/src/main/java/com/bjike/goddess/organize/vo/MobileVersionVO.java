package com.bjike.goddess.organize.vo;

/**
 * 移动端版本表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MobileVersionVO {

    /**
     * id
     */
    private String id;
    /**
     * 作者
     */
    private String author;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 时间
     */
    private String time;

    /**
     * 版本号
     */
    private String version;

    /**
     * 下载地址
     */
    private String url;

    /**
     * 状态
     */
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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