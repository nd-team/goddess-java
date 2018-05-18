package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;

import java.util.List;
import java.util.Set;

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
     * @param to 减员名单信息
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    RemoveEmployeeBO findByNameAndId(RemoveEmployeeTO to) throws SerException;

    /**
     * 确认减员
     *
     * @param id 减员名单信息id
     * @throws SerException
     */
    void confirmRemove(String id) throws SerException;

    /**
     * 查找全部
     *
     * @return class RemoveEmployeeBO
     * @throws SerException
     */
    default List<RemoveEmployeeBO> findALL() throws SerException {
        return null;
    }

    /**
     * 查找总条数
     *
     * @param dto dto
     * @throws SerException
     */
    Long count(RemoveEmployeeDTO dto) throws SerException;
    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    Set<String> allName() throws SerException;
}