package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.SummaryFeedbackBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.negotiatemeeting.service.SummaryFeedbackSer;
import com.bjike.goddess.negotiatemeeting.to.SummaryFeedbackTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 纪要反馈投诉业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summaryFeedbackApiImpl")
public class SummaryFeedbackApiImpl implements SummaryFeedbackAPI {
    @Autowired
    private SummaryFeedbackSer summaryFeedbackSer;

    @Override
    public SummaryFeedbackBO save(SummaryFeedbackTO to) throws SerException {
        return summaryFeedbackSer.save(to);
    }

    @Override
    public void edit(SummaryFeedbackTO to) throws SerException {
        summaryFeedbackSer.edit(to);
    }

    @Override
    public List<SummaryFeedbackBO> list(SummaryFeedbackDTO dto) throws SerException {
        return summaryFeedbackSer.list(dto);
    }

    @Override
    public SummaryFeedbackBO findByID(String id) throws SerException {
        return summaryFeedbackSer.findByID(id);
    }

    @Override
    public Long countNum(SummaryFeedbackDTO dto) throws SerException {
        return summaryFeedbackSer.countNum(dto);
    }
}