package com.bjike.goddess.function.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.function.enums.FunctionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 模块功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "function")
public class Function extends BaseEntity {

    /**
     * 功能名
     */
    @Column(nullable = false, unique = true,columnDefinition = "VARCHAR(255)   COMMENT '功能名'")
    private String name;

    /**
     * 图标
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '图标'")
    private String icon;

    /**
     * 功能链接
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能链接'")
    private String url;


    /**
     * 排序序列
     */
    @Column(nullable = false, unique = true, columnDefinition = "INT(3)   COMMENT '排序序列'")
    private Integer seq;

    /**
     * 功能类型
     */
    @Column(nullable = false, columnDefinition = "TINYINT(2)   COMMENT '功能类型'")
    private FunctionType type;

    /**
     * 是否开启
     */
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否开启'", insertable = false)
    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public FunctionType getType() {
        return type;
    }

    public void setType(FunctionType type) {
        this.type = type;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}