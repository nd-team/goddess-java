package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.SummarySonBO;
import com.bjike.goddess.stockholdermeeting.dto.SummarySonDTO;
import com.bjike.goddess.stockholdermeeting.service.SummarySonSer;
import com.bjike.goddess.stockholdermeeting.to.SummarySonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股东大会纪要子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summarySonApiImpl")
public class SummarySonApiImpl implements SummarySonAPI {
    @Autowired
    private SummarySonSer summarySonSer;

    @Override
    public SummarySonBO save(SummarySonTO to) throws SerException {
        return summarySonSer.save(to);
    }

    @Override
    public void edit(SummarySonTO to) throws SerException {
        summarySonSer.edit(to);
    }

    @Override
    public List<SummarySonBO> list(SummarySonDTO dto) throws SerException {
        return summarySonSer.list(dto);
    }

    @Override
    public SummarySonBO findByID(String id) throws SerException {
        return summarySonSer.findByID(id);
    }

    @Override
    public Long countNum(SummarySonDTO dto) throws SerException {
        return summarySonSer.countNum(dto);
    }

    @Override
    public void editSpeak(SummarySonTO to) throws SerException {
        summarySonSer.editSpeak(to);
    }
}