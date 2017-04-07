package com.bjike.goddess.individualvision.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;

import java.util.List;

/**
 * 个人愿景计划业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndividualVisionPlanAPI {
    /**
     * 获取个人愿景计划
     *
     * @param individualVisionPlanDTO 个人愿景计划dto
     * @return class individualVisionPlanBO
     * @throws SerException
     */
    default List<IndividualVisionPlanBO> findListIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws SerException {
        return null;
    }

    /**
     * 添加个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @throws SerException
     */
    default IndividualVisionPlanBO insertIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return null;
    }

    /**
     * 编辑个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @return class individualVisionPlanBO
     * @throws SerException
     */
    default IndividualVisionPlanBO editIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除个人愿景计划
     *
     * @param id
     * @throws SerException
     */
    default void removeIndividualVisionPlan(String id) throws SerException {

    }
    /**
     * 审核
     *
     * @param individualVisionPlanTO
     * @throws SerException
     */
    default IndividualVisionPlanBO auditIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws SerException {
        return null;
    }

}