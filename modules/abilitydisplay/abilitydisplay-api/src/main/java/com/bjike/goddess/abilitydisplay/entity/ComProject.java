package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司项目
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:05 ]
 * @Description: [ 公司项目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_comproject")
public class ComProject extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "name",columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String name;

    /**
     * 项目进度
     */
    @Column(name = "progress",columnDefinition = "VARCHAR(255)   COMMENT '项目进度'")
    private String progress;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}