package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.bo.DismissionEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.BeforeRemoveEmployee;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;

import java.util.List;

/**
 * 减员前业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:45 ]
 * @Description: [ 减员前业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BeforeRemoveEmployeeSer extends Ser<BeforeRemoveEmployee, BeforeRemoveEmployeeDTO> {
    /**
     * 添加
     *
     * @param to 减员前信息
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
        return null;
    }

    /**
     * 审核
     *
     * @param to 减员前信息
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default BeforeRemoveEmployeeBO exam(BeforeRemoveEmployeeTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 减员前分页信息
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default List<BeforeRemoveEmployeeBO> find(BeforeRemoveEmployeeDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 减员前名单的id
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default BeforeRemoveEmployeeBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 减员前名单的id
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default BeforeRemoveEmployeeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取所有离职员工
     *
     * @return class DismissionEmployeeBO
     * @throws SerException
     */
    default List<DismissionEmployeeBO> all() throws SerException {
        return null;
    }
}