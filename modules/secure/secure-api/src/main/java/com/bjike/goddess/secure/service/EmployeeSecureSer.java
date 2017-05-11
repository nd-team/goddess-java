package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.to.EmployeeSecureTO;

import java.util.List;

/**
 * 员工社保基本信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmployeeSecureSer extends Ser<EmployeeSecure, EmployeeSecureDTO> {
    /**
     * 添加
     *
     * @param to 社保信息
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default EmployeeSecureBO save(EmployeeSecureTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 员工社保基本信息
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default EmployeeSecureBO edit(EmployeeSecureTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 员工社保分页信息
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default List<EmployeeSecureBO> find(EmployeeSecureDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 员工社保id
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default EmployeeSecureBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 员工社保id
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default EmployeeSecureBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 通过sql查找
     *
     * @param employeeId 员工编号
     * @return class EmployeeSecureBO
     * @throws SerException
     */
    default List<EmployeeSecureBO> findBySql(String[] employeeId) throws SerException {
        return null;
    }

    /**
     * 更新社保状态为减员
     *
     * @param to 员工社保基本信息
     * @throws SerException
     */
    default void remove(EmployeeSecureTO to) throws SerException {
    }
}