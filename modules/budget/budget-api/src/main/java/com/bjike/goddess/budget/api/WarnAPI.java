package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.WarnBO;
import com.bjike.goddess.budget.dto.WarnDTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.WarnTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 预警业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarnAPI {
    /**
     * 添加
     *
     * @param to 预警设计信息
     * @return class WarnBO
     * @throws SerException
     */
    default WarnBO save(WarnTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 预警设计信息
     * @throws SerException
     */
    default void edit(WarnTO to) throws SerException {
    }

    /**
     * 分页查找
     *
     * @param dto 预警设计分页信息
     * @return class WarnBO
     * @throws SerException
     */
    default List<WarnBO> list(WarnDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 预警设计id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 预警设计id
     * @return class WarnBO
     * @throws SerException
     */
    default WarnBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto 预警信息
     * @throows SerException
     */
    default Long countNum(WarnDTO dto) throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}