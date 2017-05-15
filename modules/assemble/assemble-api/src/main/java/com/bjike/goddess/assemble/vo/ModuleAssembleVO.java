package com.bjike.goddess.assemble.vo;

import com.bjike.goddess.assemble.entity.Module;

/**
 * 模块关联表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleAssembleVO {

    /**
     * id
     */
    private String id;
    /**
     * 模块
     */
    private Module module;

    /**
     * 关联模块
     */
    private Module relation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}