package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司文化活动
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:13 ]
 * @Description: [ 公司文化活动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

@Entity
@Table(name = "abilitydisplay_comcultural")
public class ComCultural extends BaseEntity {

    /**
     * 活动名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '活动名称'")
    private String name;

    /**
     * 活动内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '活动内容'")
    private String content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}