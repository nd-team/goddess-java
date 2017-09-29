package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.to.AttachedTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.NameTO;

import java.util.List;
import java.util.Set;

/**
 * 挂靠业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttachedSer extends Ser<Attached, AttachedDTO> {
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
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     * @version v1
     */
    default AttachedBO save(AttachedTO to) throws SerException {
        return null;
    }


    /**
     * 删除记录
     *
     * @param id 挂靠信息名单的id
     * @return class AttachedBO
     * @throws SerException
     * @version v1
     */
    default AttachedBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 挂靠的页码信息
     * @return class AttachedBO
     * @throws SerException
     */
    default List<AttachedBO> find(AttachedDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 挂靠信息名单的id
     * @return class AttachedBO
     * @throws SerException
     */
    default AttachedBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找全部
     *
     * @return class AttachedBO
     * @throws SerException
     */
    default List<AttachedBO> findALL() throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     */
    AttachedBO edit(AttachedTO to) throws SerException;

    /**
     * 通过
     *
     * @param id id
     * @throws SerException
     */
    void pass(AddEmployeeDTO dto, String id) throws SerException;

    /**
     * 不通过
     *
     * @param id
     * @throws SerException
     */
    void notPass(AddEmployeeDTO dto, String id) throws SerException;

    /**
     * 补全信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    AttachedBO complete(AttachedTO to) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AttachedDTO dto) throws SerException;

    /**
     * 根据员工姓名查找扣社保情况
     */
    AttachedBO findAttached(String name) throws SerException;
    /**
     * 根据姓名查找
     *
     * @param to to
     * @return AttachedBO
     * @throws SerException
     */
    List<AttachedBO> byName(NameTO to) throws SerException;
    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    Set<String> allName() throws SerException;
}