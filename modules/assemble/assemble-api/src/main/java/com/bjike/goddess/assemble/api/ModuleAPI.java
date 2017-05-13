package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
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
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 勾选模块
     *
     * @param id
     * @param checkType
     * @throws SerException
     */
    default void check(String id,  CheckType checkType) throws SerException {

    }


}
