package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 模块关联业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModuleAssembleSer extends Ser<ModuleAssemble, ModuleAssembleDTO> {
    /**
     * 添加模块关联
     *
     * @param moduleAssembleTO
     */
    default void add(ModuleAssembleTO moduleAssembleTO)throws SerException {

    }

    /**
     * 删除模块关联
     *
     * @param id
     */
    default void delete(String id)throws SerException {

    }
}