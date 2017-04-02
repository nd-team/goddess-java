package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.HandlePlanImplementBO;
import com.bjike.goddess.qualifications.to.HandlePlanImplementTO;

import java.util.List;

/**
 * 资质办理计划阶段实施工作记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HandlePlanImplementAPI {

    /**
     * 保存
     *
     * @param to 实施工作记录传输对象
     * @return
     * @throws SerException
     */
    default HandlePlanImplementBO save(HandlePlanImplementTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 实施工作记录传输对象
     * @return
     * @throws SerException
     */
    default HandlePlanImplementBO update(HandlePlanImplementTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 实施工作记录id
     * @return
     * @throws SerException
     */
    default HandlePlanImplementBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据计划阶段ID集合查询实施工作记录
     *
     * @param stage_ids 计划阶段ID
     * @return
     * @throws SerException
     */
    default List<HandlePlanImplementBO> findByStageIds(String[] stage_ids) throws SerException {
        return null;
    }

    /**
     * 根据计划阶段ID查询实施工作记录
     *
     * @param stage_id 计划阶段ID
     * @return
     * @throws SerException
     */
    default List<HandlePlanImplementBO> findByStage(String stage_id) throws SerException {
        return null;
    }

    /**
     * 根据资质办理查询实施工作记录
     *
     * @param handle_id 资质办理ID
     * @return
     * @throws SerException
     */
    default List<HandlePlanImplementBO> findByHandle(String handle_id) throws SerException {
        return null;
    }

}