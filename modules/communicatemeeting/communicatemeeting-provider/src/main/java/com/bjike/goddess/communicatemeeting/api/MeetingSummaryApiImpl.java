package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.communicatemeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.communicatemeeting.excel.MeetingSummaryExcel;
import com.bjike.goddess.communicatemeeting.service.MeetingSummarySer;
import com.bjike.goddess.communicatemeeting.to.MeetingSummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 交流会纪要业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingSummaryApiImpl")
public class MeetingSummaryApiImpl implements MeetingSummaryAPI {
    @Autowired
    private MeetingSummarySer meetingSummarySer;

    @Override
    public MeetingSummaryBO save(MeetingSummaryTO to) throws SerException {
        return meetingSummarySer.save(to);
    }

    @Override
    public void edit(MeetingSummaryTO to) throws SerException {
        meetingSummarySer.edit(to);
    }

    @Override
    public List<MeetingSummaryBO> list(MeetingSummaryDTO dto) throws SerException {
        return meetingSummarySer.list(dto);
    }

    @Override
    public MeetingSummaryBO findByID(String id) throws SerException {
        return meetingSummarySer.findByID(id);
    }

    @Override
    public Long countNum(MeetingSummaryDTO dto) throws SerException {
        return meetingSummarySer.countNum(dto);
    }

    @Override
    public Set<String> meetingNumbers() throws SerException {
        return meetingSummarySer.meetingNumbers();
    }

    @Override
    public byte[] exportExcel(String id) throws SerException {
        return meetingSummarySer.exportExcel(id);
    }

    @Override
    public void leadExcel(List<MeetingSummaryExcel> toList) throws SerException {
        meetingSummarySer.leadExcel(toList);
    }
}