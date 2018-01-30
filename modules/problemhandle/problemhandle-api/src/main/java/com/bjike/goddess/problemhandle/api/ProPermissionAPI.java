package com.bjike.goddess.problemhandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.problemhandle.bo.ProPermissionBO;
import com.bjike.goddess.problemhandle.dto.ProPermissionDTO;
import com.bjike.goddess.problemhandle.to.ProPermissionTO;

import java.util.List;

/**
 * 问题权限配置业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProPermissionAPI {


    /**
     * 问题权限列表总条数
     */
    default Long countPermission(ProPermissionDTO proPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 问题权限列表
     *
     * @param proPermissionDTO 问题权限数据
     * @return ProPermissionBO
     * @throws SerException
     */
    default List<ProPermissionBO> list(ProPermissionDTO proPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个问题权限
     *
     * @param id 问题权限id
     * @return ProPermissionBO
     * @throws SerException
     */
    default ProPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 问题权限操作者
     *
     * @param id 问题权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加问题权限
     *
     * @param proPermissionTOS 问题权限数据
     * @return ProPermissionBO
     * @throws SerException
     */
    default ProPermissionBO add(List<ProPermissionTO> proPermissionTOS) throws SerException {
        return null;
    }

    /**
     * 编辑问题权限
     *
     * @param proPermissionTO 问题权限数据
     * @return ProPermissionBO
     * @throws SerException
     */
    default ProPermissionBO edit(ProPermissionTO proPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的问题权限
     *
     * @param idFlag 问题权限idFlag
     * @throws SerException
     */
    default Boolean getProPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 问题权限idFlag
     * @throws SerException
     */
    default Boolean busProPermission(String idFlag) throws SerException {
        return null;
    }

}