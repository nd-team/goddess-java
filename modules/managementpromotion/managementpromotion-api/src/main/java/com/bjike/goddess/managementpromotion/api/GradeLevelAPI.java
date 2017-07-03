package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.GradeLevelBO;
import com.bjike.goddess.managementpromotion.dto.GradeLevelDTO;
import com.bjike.goddess.managementpromotion.to.GradeLevelTO;

import java.util.List;
import java.util.Set;

/**
 * 管理等级定级业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface GradeLevelAPI {
    /**
     * 添加
     *
     * @param to 管理等级定级信息
     * @return class GradeLevelBO
     * @throws SerException
     */
    default GradeLevelBO save(GradeLevelTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 管理等级定级id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 编辑
     *
     * @param to 管理等级定级信息
     * @throws SerException
     */
    default void edit(GradeLevelTO to) throws SerException {
    }

    /**
     * 管理等级定级列表
     *
     * @param dto 管理等级定级dto
     * @return class GradeLevelBO
     * @throws SerException
     */
    default List<GradeLevelBO> find(GradeLevelDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 管理等级定级id
     * @return class GradeLevelBO
     * @throws SerException
     */
    default GradeLevelBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long count(GradeLevelDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取所有体系
     *
     * @throws SerException
     */
    Set<String> allHierarchys() throws SerException;
}