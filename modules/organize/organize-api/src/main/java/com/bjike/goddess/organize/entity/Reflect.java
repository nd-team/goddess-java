package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 体现类型
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_reflect")
public class Reflect extends BaseEntity {

    /**
     * 名称
     */
    @Column(unique = true, nullable = false,columnDefinition = "VARCHAR(50) COMMENT '名称'")
    private String name;

    /**
     * 分类
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "classify_id", columnDefinition = "VARCHAR(36) COMMENT '分类'")
    private InstructionClassify classify;

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

    /**
     * 使用状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态'", nullable = false, insertable = false)
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstructionClassify getClassify() {
        return classify;
    }

    public void setClassify(InstructionClassify classify) {
        this.classify = classify;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
