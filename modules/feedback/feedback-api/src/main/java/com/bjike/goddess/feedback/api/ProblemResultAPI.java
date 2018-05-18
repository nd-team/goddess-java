package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemResultTO;

import java.util.List;

/**
 * 问题处理结果业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemResultAPI {
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
     * 问题处理结果列表总条数
     */
    default Long count(ProblemResultDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个问题处理结果
     *
     * @return class ProblemResultBO
     */
    default ProblemResultBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 问题处理结果列表
     *
     * @param dto 问题处理结果dto
     * @return class ProblemResultBO
     * @throws SerException
     */
    default List<ProblemResultBO> list(ProblemResultDTO dto) throws SerException {
        return null;
    }

    /**
     * 当事人确认
     *
     * @param to 问题处理结果数据to
     * @return class ReceivedFeedbackBO
     * @throws SerException
     */
    default ProblemResultBO partyConfirm(ProblemResultTO to) throws SerException {
        return null;
    }

    /**
     * 确认是否需要协调
     *
     * @param to 问题处理结果数据to
     * @return class ProblemResultBO
     * @throws SerException
     */
    default ProblemResultBO coordinate(ProblemResultTO to) throws SerException {
        return null;
    }

    /**
     * 协调结果
     *
     * @param to 问题处理结果数据to
     * @return class ProblemResultBO
     * @throws SerException
     */
    default ProblemResultBO result(ProblemResultTO to) throws SerException {
        return null;
    }
}