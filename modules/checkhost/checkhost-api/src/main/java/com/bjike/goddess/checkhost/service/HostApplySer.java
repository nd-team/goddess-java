package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.bo.HostApplyBO;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.to.HostApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.checkhost.entity.HostApply;
import com.bjike.goddess.checkhost.dto.HostApplyDTO;

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
public interface HostApplySer extends Ser<HostApply, HostApplyDTO> {
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
     * @param hostApplyTO 离宿申请
     * @return class HostApplyBO
     */
    default HostApplyBO auditHostApply(HostApplyTO hostApplyTO) throws SerException {
        return null;
    }


}