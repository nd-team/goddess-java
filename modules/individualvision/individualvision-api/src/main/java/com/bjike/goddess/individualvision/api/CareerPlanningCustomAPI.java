package com.bjike.goddess.individualvision.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.individualvision.bo.CareerPlanningCustomBO;
import com.bjike.goddess.individualvision.dto.CareerPlanningCustomDTO;
import com.bjike.goddess.individualvision.to.CareerPlanningCustomTO;

import java.util.List;

/**
 * 职业规划定制业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:45 ]
 * @Description: [ 职业规划定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CareerPlanningCustomAPI {
    /**
     * 获取职业规划定制
     *
     * @param careerPlanningCustomDTO 职业规划定制dto
     * @return class careerPlanningCustomBO
     * @throws SerException
     */
    default List<CareerPlanningCustomBO> findListCareerPlanningCustom(CareerPlanningCustomDTO careerPlanningCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加职业规划定制
     *
     * @param careerPlanningCustomTO 职业规划定制数据to
     * @throws SerException
     */
    default CareerPlanningCustomBO insertCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return null;
    }

    /**
     * 编辑职业规划定制
     *
     * @param careerPlanningCustomTO 职业规划定制数据to
     * @return class careerPlanningCustomBO
     * @throws SerException
     */
    default CareerPlanningCustomBO editCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除职业规划定制
     *
     * @param id
     * @throws SerException
     */
    default void removeCareerPlanningCustom(String id) throws SerException {

    }
    /**
     * 发送邮件
     *
     * @param careerPlanningCustomTO
     * @throws SerException
     */
    default CareerPlanningCustomBO sendCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return null;
    }

}