package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;

import java.util.List;

/**
 * 减员名单业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RemoveEmployeeSer extends Ser<RemoveEmployee, RemoveEmployeeDTO> {
    /**
     * 添加
     *
     * @param to 减员名单信息
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default RemoveEmployeeBO save(RemoveEmployeeTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 减员名单信息
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default RemoveEmployeeBO edit(RemoveEmployeeTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 减员名单分页信息
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default List<RemoveEmployeeBO> find(RemoveEmployeeDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 减员名单id
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default RemoveEmployeeBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 减员名单id
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default RemoveEmployeeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 通过姓名和员工编号查找
     *
     * @param removeName 姓名数组
     * @param employeeId 员工编号数组
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default RemoveEmployeeBO findByNameAndId(String[] removeName, String[] employeeId) throws SerException {
        return null;
    }

    /**
     * 确认减员
     *
     * @param to 减员名单信息
     * @throws SerException
     */
    default void confirm(RemoveEmployeeTO to) throws SerException {
    }

    /**
     * 查找全部
     *
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default List<RemoveEmployeeBO> findALL() throws SerException {
        return null;
    }
}