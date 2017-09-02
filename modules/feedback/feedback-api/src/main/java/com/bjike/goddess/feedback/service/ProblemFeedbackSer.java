package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.feedback.bo.ProblemAcceptBO;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.entity.ProblemFeedback;
import com.bjike.goddess.feedback.excel.SonPermissionObject;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;

import java.util.List;

/**
 * 问题反馈模块业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemFeedbackSer extends Ser<ProblemFeedback, ProblemFeedbackDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 问题反馈模块列表总条数
     */
    default Long count(ProblemFeedbackDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个问题反馈模块
     *
     * @return class ProblemFeedbackBO
     */
    default ProblemFeedbackBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 问题反馈模块
     *
     * @param dto 问题反馈模块dto
     * @return class ProblemFeedbackBO
     * @throws SerException
     */
    default List<ProblemFeedbackBO> list(ProblemFeedbackDTO dto) throws SerException {
        return null;
    }

    /**
     * 问题反馈/录入
     *
     * @param to 问题反馈模块数据to
     * @return class ProblemFeedbackBO
     * @throws SerException
     */
    default ProblemFeedbackBO insert(ProblemFeedbackTO to) throws SerException {
        return null;
    }

    /**
     * 问题通报
     *
     * @param to 问题反馈模块数据to
     * @return class ProblemFeedbackBO
     * @throws SerException
     */
    default ProblemFeedbackBO edit(ProblemFeedbackTO to) throws SerException {
        return null;
    }

    /**
     * 问题受理
     *
     * @param to 问题受理模块数据to
     * @return class ProblemAcceptBO
     * @throws SerException
     */
    default ProblemAcceptBO problemAccept(ProblemFeedbackTO to) throws SerException {
        return null;
    }

}