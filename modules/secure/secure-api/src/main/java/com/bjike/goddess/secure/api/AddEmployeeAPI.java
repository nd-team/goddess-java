package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.SonPermissionObject;

import java.util.List;

/**
 * 社保增员信息名单业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AddEmployeeAPI {
    /**
     * 下拉导航权限
     */
    List<SonPermissionObject> sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 查找
     *
     * @param dto 社保增员页码信息
     * @return class AddEmployeeBO
     * @throws SerException
     */
    default List<AddEmployeeBO> find(AddEmployeeDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 社保增员信息名单的id
     * @return class AddEmployeeBO
     * @throws SerException
     */
    default AddEmployeeBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 社保增员信息名单的id
     * @return class AddEmployeeBO
     * @throws SerException
     */
    default AddEmployeeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AddEmployeeBO save(AddEmployeeTO to) throws SerException;

    /**
     * 查找全部
     *
     * @return class AddEmployeeBO
     * @throws SerException
     */
    default List<AddEmployeeBO> findAll() throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 社保增员信息
     * @throws SerException
     */
    void edit(AddEmployeeTO to) throws SerException;

    /**
     * 运营商务部审核
     *
     * @param to to
     * @throws SerException
     */
    void commerceAudit(AddEmployeeTO to) throws SerException;

    /**
     * 总经办确认新增
     *
     * @param id
     * @throws SerException
     */
    void managerConfirmAdd(AddEmployeeDTO dto, String id) throws SerException;

    /**
     * 社保管理负责人确认增员
     *
     * @param id
     * @throws SerException
     */
    void confirmAdd(String id) throws SerException;

    /**
     * 查找总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AddEmployeeDTO dto) throws SerException;
}