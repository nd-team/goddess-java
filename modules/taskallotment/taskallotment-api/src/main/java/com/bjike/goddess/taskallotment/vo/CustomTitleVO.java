package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.taskallotment.enums.TitleType;

/**
 * 自定义字段表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomTitleVO {

    /**
     * id
     */
    private String id;
    /**
     * 标题下标
     */
    private Integer titleIndex;
    /**
     * 字段名
     */
    private String title;

    /**
     * 字段内容
     */
    private String content;

    /**
     * 是否必填选项
     */
    private Boolean mandatory;

    /**
     * 字段类型
     */
    private TitleType titleType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTitleIndex() {
        return titleIndex;
    }

    public void setTitleIndex(Integer titleIndex) {
        this.titleIndex = titleIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }
}