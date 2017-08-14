package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.QuestionBO;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.to.AuswerTO;
import com.bjike.goddess.system.to.QuestionTO;

import java.util.List;

/**
 * 问题业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QuestionAPI {
    /**
     * 问题业务列表总条数
     */
    default Long count(QuestionDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个问题业务
     *
     * @return class FeatureListBO
     */
    default QuestionBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 问题业务
     *
     * @param dto 问题业务dto
     * @return class QuestionBO
     * @throws SerException
     */
    default List<QuestionBO> list(QuestionDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加问题业务
     *
     * @param to 问题业务数据to
     * @return class QuestionBO
     * @throws SerException
     */
    default QuestionBO insert(QuestionTO to) throws SerException {
        return null;
    }

    /**
     * 编辑问题业务
     *
     * @param to 问题业务数据to
     * @return class QuestionBO
     * @throws SerException
     */
    default QuestionBO edit(QuestionTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除问题业务
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 详情
     *
     * @param id
     * @return String
     * @throws SerException
     */
    default String detail(String id) throws SerException {
        return null;
    }

    /**
     * 解答
     *
     * @param id
     * @param auswerTO
     * @throws SerException
     */
    default void answer(String id, AuswerTO auswerTO) throws SerException {
    }
}