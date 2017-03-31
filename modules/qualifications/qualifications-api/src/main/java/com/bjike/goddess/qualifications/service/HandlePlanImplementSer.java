package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.HandlePlanImplementBO;
import com.bjike.goddess.qualifications.dto.HandlePlanImplementDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanImplement;
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
public interface HandlePlanImplementSer extends Ser<HandlePlanImplement, HandlePlanImplementDTO> {


    default HandlePlanImplementBO save(HandlePlanImplementTO to) throws SerException {
        return null;
    }

    default HandlePlanImplementBO update(HandlePlanImplementTO to) throws SerException {
        return null;
    }

    default HandlePlanImplementBO delete(String id) throws SerException {
        return null;
    }

    default List<HandlePlanImplementBO> findByStageIds(String[] stage_ids) throws SerException {
        return null;
    }

    default List<HandlePlanImplementBO> findByStage(String stage_id) throws SerException {
        return null;
    }

    default List<HandlePlanImplementBO> findByHandle(String handle_id) throws SerException {
        return null;
    }
}