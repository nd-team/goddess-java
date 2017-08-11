package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.assemble.type.CheckType;
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

    /**
     * 勾选模块
     *
     * @param moduleId
     * @param relationIds
     * @throws SerException
     */
    default void relation(String moduleId, String[] relationIds) throws SerException {

    }
    /**
     * 通过模块名查询模块
     *
     * @param name
     * @return
     * @throws SerException
     */
    default ModuleBO modulesByName(String name, CheckType checkType) throws SerException {
        return null;
    }
    /**
     * 通过模块名称判断改模块关联关系是否被选中lijuntao
     *
     * @param moduleNames
     * @return
     * @throws SerException
     */
    default Boolean checkByName(String[] moduleNames) throws SerException {
        return null;
    }

}