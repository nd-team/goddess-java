package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.BeforeRemoveEmployee;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;

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
     * @param to 减员前信息
     * @return class BeforeRemoveEmployeeBO
     * @throws SerException
     */
    default BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
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
     * 定时方法，轮询查询离职员工
     *
     * @throws SerException
     */
    void send() throws SerException;

//    /**
//     * 启动定时方法
//     *
//     * @throws SerException
//     */
//    void quartz() throws SerException;

    /**
     * 减员
     *
     * @param id 减员前id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(BeforeRemoveEmployeeDTO dto) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(BeforeRemoveEmployeeTO to) throws SerException;
}