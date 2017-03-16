package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 编号设计信息
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Entity
@Table(name = "organize_design_number_info")
public class DesignNumberInfo extends BaseEntity {

    /**
     * 编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(24) COMMENT '编号'")
    private String serialNumber;

    /**
     * 分类
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '分类'")
    private String classify;

    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '名称'")
    private String name;

    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '描述'")
    private String description;


    /**
     * 创建时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false)
    private LocalDateTime createTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
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
}
