package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.SummaryBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryDTO;
import com.bjike.goddess.negotiatemeeting.dto.SummarySonDTO;
import com.bjike.goddess.negotiatemeeting.excel.SummaryExcel;
import com.bjike.goddess.negotiatemeeting.service.SummarySer;
import com.bjike.goddess.negotiatemeeting.to.SummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 协商会议纪要业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:49 ]
 * @Description: [ 协商会议纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summaryApiImpl")
public class SummaryApiImpl implements SummaryAPI {
    @Autowired
    private SummarySer summarySer;

    @Override
    public SummaryBO save(SummaryTO to) throws SerException {
        return summarySer.save(to);
    }

    @Override
    public void edit(SummaryTO to) throws SerException {
        summarySer.edit(to);
    }

    @Override
    public List<SummaryBO> list(SummaryDTO dto) throws SerException {
        return summarySer.list(dto);
    }

    @Override
    public SummaryBO findByID(String id) throws SerException {
        return summarySer.findByID(id);
    }

    @Override
    public Long countNum(SummaryDTO dto) throws SerException {
        return summarySer.countNum(dto);
    }

    @Override
    public Set<String> meetingNumbers() throws SerException {
        return summarySer.meetingNumbers();
    }

    @Override
    public byte[] exportExcel(String id, String summaryId) throws SerException {
        return summarySer.exportExcel(id,summaryId);
    }

    @Override
    public void leadExcel(List<SummaryExcel> toList) throws SerException {
        summarySer.leadExcel(toList);
    }
}