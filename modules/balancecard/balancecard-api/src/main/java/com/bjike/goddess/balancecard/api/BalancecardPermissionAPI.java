package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.BalancecardPermissionBO;
import com.bjike.goddess.balancecard.dto.BalancecardPermissionDTO;
import com.bjike.goddess.balancecard.to.BalancecardPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 平衡计分卡权限配置业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 平衡计分卡权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BalancecardPermissionAPI {


    /**
     * 平衡计分卡权限列表总条数
     */
    default Long countPermission(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 平衡计分卡权限列表
     *
     * @param cusPermissionDTO 平衡计分卡权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<BalancecardPermissionBO> list(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个平衡计分卡权限
     *
     * @param id 平衡计分卡权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default BalancecardPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 平衡计分卡权限操作者
     *
     * @param id 平衡计分卡权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加平衡计分卡权限
     *
     * @param cusPermissionTO 平衡计分卡权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default BalancecardPermissionBO add(List<BalancecardPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑平衡计分卡权限
     *
     * @param cusPermissionTO 平衡计分卡权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default BalancecardPermissionBO edit(BalancecardPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的平衡计分卡权限
     *
     * @param idFlag 平衡计分卡权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 平衡计分卡权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag) throws SerException {
        return null;
    }

}