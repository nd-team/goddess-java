package com.bjike.goddess.assemble.entity;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 模块关联
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "module_assemble")
public class ModuleAssemble extends BaseEntity {

    /**
     * 模块
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "module_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '模块id' ")
    private Module module;

    /**
     * 关联模块
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "relation_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '关联模块id' ")
    private Module relation;

    /**
     * 选中状态
     */
    @Column(name = "checkType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '选中状态'")
    private CheckType checkType;


    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getRelation() {
        return relation;
    }

    public void setRelation(Module relation) {
        this.relation = relation;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}