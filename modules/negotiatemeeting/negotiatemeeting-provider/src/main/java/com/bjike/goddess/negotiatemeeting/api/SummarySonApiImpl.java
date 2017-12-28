package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.SummarySonBO;
import com.bjike.goddess.negotiatemeeting.dto.SummarySonDTO;
import com.bjike.goddess.negotiatemeeting.entity.SummarySon;
import com.bjike.goddess.negotiatemeeting.service.SummarySonSer;
import com.bjike.goddess.negotiatemeeting.to.SummarySonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 协商会议纪要子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summarySonApiImpl")
public class SummarySonApiImpl implements SummarySonAPI {
    @Autowired
    private SummarySonSer summarySonSer;

    @Override
    public List<SummarySon> findBySummaryId(String summaryId) throws SerException {
        return summarySonSer.findBySummaryId(summaryId);
    }

    @Override
    public SummarySonBO save(SummarySonTO to) throws SerException {
        return summarySonSer.save(to);
    }

    @Override
    public void editPerson(SummarySonTO to) throws SerException {
        summarySonSer.editPerson(to);
    }

    @Override
    public void edit(SummarySonTO to) throws SerException {
        summarySonSer.edit(to);
    }

    @Override
    public Long count(SummarySonDTO dto) throws SerException {
        return summarySonSer.count(dto);
    }

    @Override
    public SummarySonBO findByID(String id) throws SerException {
        return summarySonSer.findByID(id);
    }
}