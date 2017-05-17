package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.common.api.exception.SerException;

/**
 * 模块关联业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModuleAssembleAPI {
    /**
     * 添加模块关联
     *
     * @param moduleAssembleTO
     */
    default void add(ModuleAssembleTO moduleAssembleTO) throws SerException {

    }

    /**
     * 删除模块关联
     *
     * @param ids
     */
    default void delete(String[] ids) throws SerException {

    }

}