package com.bjike.goddess.announcement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 公告数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnouncementDTO extends BaseDTO {
    /**
     * 编号数组
     */
    private String[] numbers;
    /**
     * 分类数组
     */
    private String[] classifys;
    /**
     * 作者数组
     */
    private String[] authors;
    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 分类
     */
    private String classify;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }

    public String[] getClassifys() {
        return classifys;
    }

    public void setClassifys(String[] classifys) {
        this.classifys = classifys;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}