package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_problem_type")
public class ProblemType extends BaseEntity {
    /**
     * 问题类型名
     */
    @Column(columnDefinition = "VARCHAR(255) comment '类型名' ",nullable = false,unique = true)
    private String name;
    /**
     * 是否使用
     */
    @Column(name = "is_enable", columnDefinition = "TINYINT(1)  COMMENT '是否使用'", nullable = false)
    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
