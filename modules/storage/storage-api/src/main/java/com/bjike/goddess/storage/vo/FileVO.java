package com.bjike.goddess.storage.vo;

import com.bjike.goddess.storage.enums.FileType;

import java.time.LocalDateTime;

/**
 * 文件存储表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FileVO {
    /**
     * 文件名
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件类型
     */
    private FileType fileType;

    /**
     * 文件大小
     */
    private String size;
    /**
     * 文件长度
     */
    private Long length;

    /**
     * 是否为目录
     */
    private Boolean dir;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 大图
     */
    private String bigPicture;

    /**
     * 小图
     */
    private String minPicture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Boolean getDir() {
        return dir;
    }

    public void setDir(Boolean dir) {
        this.dir = dir;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public String getMinPicture() {
        return minPicture;
    }

    public void setMinPicture(String minPicture) {
        this.minPicture = minPicture;
    }
}