package com.bjike.goddess.assemble.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 模块应用
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "module_apply")
public class ModuleApply extends BaseEntity {

    /**
     * 模块
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "module_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '模块id' ")
    private Module module;

    /**
     * 公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;


    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}