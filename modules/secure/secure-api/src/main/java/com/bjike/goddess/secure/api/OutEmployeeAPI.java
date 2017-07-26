package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.entity.OutEmployee;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.OutEmployeeTO;

import java.util.List;

/**
 * 离职名单业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OutEmployeeAPI {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 添加
     *
     * @throws SerException
     */
    default void save() throws SerException {
    }

    /**
     * 是否继续购买
     *
     * @param to 离职员工信息
     * @return class OutEmployeeBO
     * @throws SerException
     */
    default OutEmployeeBO is_again(OutEmployeeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 离职员工id
     * @return class OutEmployeeBO
     * @throws SerException
     */
    default OutEmployeeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 离职员工分页信息
     * @return class OutEmployeeBO
     * @throws SerException
     */
    default List<OutEmployeeBO> find(OutEmployeeDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 离职员工id
     * @return class OutEmployeeBO
     * @throws SerException
     */
    default OutEmployeeBO findByID(String id) throws SerException {
        return null;
    }
}