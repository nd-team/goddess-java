package com.bjike.goddess.assemble.bo;

import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 模块关联业务传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleAssembleBO extends BaseBO {

    /**
     * 模块
     */
    private Module module;

    /**
     * 关联模块
     */
    private Module relation;


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