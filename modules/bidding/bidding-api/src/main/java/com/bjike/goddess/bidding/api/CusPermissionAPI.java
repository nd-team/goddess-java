package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.CusPermissionBO;
import com.bjike.goddess.bidding.dto.CusPermissionDTO;
import com.bjike.goddess.bidding.to.CusPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 招投标权限配置业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 招投标权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CusPermissionAPI {


    /**
     * 招投标权限列表总条数
     */
    default Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 招投标权限列表
     *
     * @param cusPermissionDTO 招投标权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }


    /**
     * 一个招投标权限
     *
     * @param id 招投标权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 招投标权限操作者
     *
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加招投标权限
     *
     * @param cusPermissionTO 招投标权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑招投标权限
     *
     * @param cusPermissionTO 招投标权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的招投标权限
     *
     * @param idFlag 招投标权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 招投标权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag) throws SerException {
        return null;
    }
}