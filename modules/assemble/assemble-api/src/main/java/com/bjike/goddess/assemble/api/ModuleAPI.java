package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 模块接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ModuleAPI {

    /**
     * 模块列表
     *
     * @param moduleDTO
     * @return
     * @throws SerException
     */
    default List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        return null;
    }


    /**
     * 添加模块
     *
     * @param moduleTO
     * @throws SerException
     */
    default void add(ModuleTO moduleTO) throws SerException {

    }

    /**
     * 删除模块
     *
     * @param name
     * @throws SerException
     */
    default void delete(String name) throws SerException {

    }

    /**
     * 勾选模块
     *
     * @param ids
     * @throws SerException
     */
    default void check(String[] ids) throws SerException {

    }

    /**
     * 模块是否选中
     *
     * @param id
     * @throws SerException
     */
    default Boolean isCheck(String id) throws SerException {
        return null;
    }


}
