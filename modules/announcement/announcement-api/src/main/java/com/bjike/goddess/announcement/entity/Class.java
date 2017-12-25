package com.bjike.goddess.announcement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公告分类
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "announcement_class")
public class Class extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;


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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}