package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.HostApplyBO;
import com.bjike.goddess.checkhost.dto.HostApplyDTO;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.HostApplyTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 离宿申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HostApplyAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 离宿申请列表总条数
     */
    default Long countHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        return null;
    }
    /**
     * 一个离宿申请
     *
     * @return class HostApplyBO
     */
    default HostApplyBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 获取离宿申请
     *
     * @param hostApplyDTO 离宿申请dto
     * @return class HostApplyBO
     * @throws SerException
     */
    default List<HostApplyBO> findListHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加离宿申请
     *
     * @param hostApplyTO 离宿申请数据to
     * @return class HostApplyBO
     * @throws SerException
     */
    default HostApplyBO insertHostApply(HostApplyTO hostApplyTO) throws SerException {
        return null;
    }

    /**
     * 编辑离宿申请
     *
     * @param hostApplyTO 离宿申请数据to
     * @return class HostApplyBO
     * @throws SerException
     */
    default HostApplyBO editHostApply(HostApplyTO hostApplyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除离宿申请
     *
     * @param id
     * @throws SerException
     */
    default void removeHostApply(String id) throws SerException {

    }

    /**
     * 审核
     *
     * @param id          id
     * @param dto
     * @return class HostApplyBO
     * @paramcheckStatus
     */
    HostApplyBO auditHostApply(String id, HostApplyDTO dto) throws SerException;

}