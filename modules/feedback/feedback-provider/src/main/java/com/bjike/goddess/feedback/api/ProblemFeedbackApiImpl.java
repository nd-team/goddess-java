package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ProblemAcceptBO;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.excel.SonPermissionObject;
import com.bjike.goddess.feedback.service.ProblemFeedbackSer;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题反馈模块业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemFeedbackApiImpl")
public class ProblemFeedbackApiImpl implements ProblemFeedbackAPI {
    @Autowired
    private ProblemFeedbackSer problemFeedbackSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return problemFeedbackSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return problemFeedbackSer.guidePermission(guidePermissionTO);
    }


    @Override
    public Long count(ProblemFeedbackDTO dto) throws SerException {
        return problemFeedbackSer.count(dto);
    }

    @Override
    public ProblemFeedbackBO getOne(String id) throws SerException {
        return problemFeedbackSer.getOne(id);
    }

    @Override
    public List<ProblemFeedbackBO> list(ProblemFeedbackDTO dto) throws SerException {
        return problemFeedbackSer.list(dto);
    }

    @Override
    public ProblemFeedbackBO insert(ProblemFeedbackTO to) throws SerException {
        return problemFeedbackSer.insert(to);
    }

    @Override
    public ProblemFeedbackBO edit(ProblemFeedbackTO to) throws SerException {
        return problemFeedbackSer.edit(to);
    }

    @Override
    public ProblemAcceptBO problemAccept(ProblemFeedbackTO to) throws SerException {
        return problemFeedbackSer.problemAccept(to);
    }

    @Override
    public Long currentUserProblemCount() throws SerException {
        return problemFeedbackSer.currentUserProblemCount();
    }
}