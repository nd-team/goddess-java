package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workjoin.bo.WorkJoinDutyBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDutyDTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinDutyTO;

import java.util.List;

/**
 * 工作交接责任义务业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkJoinDutyAPI {
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
     * 工作交接责任义务列表总条数
     */
    default Long countWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        return null;
    }

    /**
     * 一个工作交接责任义务
     *
     * @return class WorkJoinDutyBO
     */
    default WorkJoinDutyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工作交接责任义务
     *
     * @param workJoinDutyDTO 工作交接责任义务dto
     * @return class WorkJoinDutyBO
     * @throws SerException
     */
    default List<WorkJoinDutyBO> findListWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        return null;
    }

    /**
     * 添加工作交接责任义务
     *
     * @param workJoinDutyTO 工作交接责任义务数据to
     * @return class WorkJoinDutyBO
     * @throws SerException
     */
    default WorkJoinDutyBO insertWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        return null;
    }

    /**
     * 编辑工作交接责任义务
     *
     * @param workJoinDutyTO 工作交接责任义务数据to
     * @return class WorkJoinDutyBO
     * @throws SerException
     */
    default WorkJoinDutyBO editWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工作交接责任义务
     *
     * @param id
     * @throws SerException
     */
    default void removeWorkJoinDuty(String id) throws SerException {

    }

}