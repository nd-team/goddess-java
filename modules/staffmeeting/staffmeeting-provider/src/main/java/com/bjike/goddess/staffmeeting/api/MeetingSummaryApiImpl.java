package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.service.MeetingSummarySer;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工代表大会纪要业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingSummaryApiImpl")
public class MeetingSummaryApiImpl implements MeetingSummaryAPI {

    @Autowired
    private MeetingSummarySer meetingSummarySer;

    @Override
    public MeetingSummaryBO findById(String id) throws SerException {
        return meetingSummarySer.findAndSet(id);
    }

    @Override
    public Long count(MeetingSummaryDTO dto) throws SerException {
        return meetingSummarySer.count(dto);
    }

    @Override
    public MeetingSummaryBO edit(MeetingSummaryTO to) throws SerException {
        return meetingSummarySer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        meetingSummarySer.freeze(id);
    }

    @Override
    public List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException {
        return meetingSummarySer.pageList(dto);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        meetingSummarySer.unFreeze(id);
    }
}