package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.bo.ReceivedFeedbackBO;
import com.bjike.goddess.feedback.dto.ReceivedFeedbackDTO;
import com.bjike.goddess.feedback.service.ReceivedFeedbackSer;
import com.bjike.goddess.feedback.to.ReceivedFeedbackTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 已受理的反馈业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("receivedFeedbackApiImpl")
public class ReceivedFeedbackApiImpl implements ReceivedFeedbackAPI {
    @Autowired
    private ReceivedFeedbackSer receivedFeedbackSer;
    @Override
    public Long count(ReceivedFeedbackDTO dto) throws SerException {
        return receivedFeedbackSer.count(dto);
    }
    @Override
    public ReceivedFeedbackBO getOne(String id) throws SerException {
        return receivedFeedbackSer.getOne(id);
    }
    @Override
    public List<ReceivedFeedbackBO> list(ReceivedFeedbackDTO dto) throws SerException {
        return receivedFeedbackSer.list(dto);
    }

    @Override
    public ReceivedFeedbackBO provideAdvice(ReceivedFeedbackTO to) throws SerException {
        return receivedFeedbackSer.provideAdvice(to);
    }

    @Override
    public ReceivedFeedbackBO priority(ReceivedFeedbackTO to) throws SerException {
        return receivedFeedbackSer.priority(to);
    }
    @Override
    public ProblemResultBO solve(ReceivedFeedbackTO to) throws SerException {
        return receivedFeedbackSer.solve(to);
    }
}