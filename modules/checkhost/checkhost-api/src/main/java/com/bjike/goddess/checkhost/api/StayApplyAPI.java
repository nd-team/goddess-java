package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayApplyTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 住宿申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StayApplyAPI {
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
     * 住宿申请列表总条数
     */
    default Long countStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        return null;
    }
    /**
     * 一个住宿申请
     *
     * @return class StayApplyBO
     */
    default StayApplyBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 获取住宿申请
     *
     * @param stayApplyDTO 住宿申请dto
     * @return class StayApplyBO
     * @throws SerException
     */
    default List<StayApplyBO> findListStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加住宿申请
     *
     * @param stayApplyTO 住宿申请数据to
     * @return class StayApplyBO
     * @throws SerException
     */
    default StayApplyBO insertStayApply(StayApplyTO stayApplyTO) throws SerException {
        return null;
    }

    /**
     * 编辑住宿申请
     *
     * @param stayApplyTO 住宿申请数据to
     * @return class StayApplyBO
     * @throws SerException
     */
    default StayApplyBO editStayApply(StayApplyTO stayApplyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除住宿申请
     *
     * @param id
     * @throws SerException
     */
    default void removeStayApply(String id) throws SerException {

    }

    /**
     * 福利模块负责人审核
     *
     * @return class StayApplyBO
     * @throws SerException
     */
    StayApplyBO manageAudit(StayApplyTO to) throws SerException;

}