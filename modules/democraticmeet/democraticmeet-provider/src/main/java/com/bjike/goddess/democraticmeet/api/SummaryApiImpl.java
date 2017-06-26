package com.bjike.goddess.democraticmeet.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.democraticmeet.bo.SummaryBO;
import com.bjike.goddess.democraticmeet.dto.SummaryDTO;
import com.bjike.goddess.democraticmeet.to.ExportExcelCondition;
import com.bjike.goddess.democraticmeet.excel.SummaryExcelConvert;
import com.bjike.goddess.democraticmeet.service.SummarySer;
import com.bjike.goddess.democraticmeet.to.SummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议纪要业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("summaryApiImpl")
public class SummaryApiImpl implements SummaryAPI {

    @Autowired
    private SummarySer summarySer;


    @Override
    public Long countSummary(SummaryDTO summaryDTO) throws SerException {
        return summarySer.countSummary( summaryDTO);
    }

    @Override
    public SummaryBO getOneById(String id) throws SerException {
        return summarySer.getOneById(id);
    }

    @Override
    public List<SummaryBO> listSummary(SummaryDTO summaryDTO) throws SerException {
        return summarySer.listSummary( summaryDTO);
    }

    @Override
    public SummaryBO addSummary(SummaryTO summaryTO) throws SerException {
        return summarySer.addSummary( summaryTO);
    }

    @Override
    public SummaryBO editSummary(SummaryTO summaryTO) throws SerException {
        return summarySer.editSummary( summaryTO);
    }

    @Override
    public void deleteSummary(String id) throws SerException {
        summarySer.deleteSummary(id);
    }

    @Override
    public void leadExcel(List<SummaryExcelConvert> toList) throws SerException {
        summarySer.leadExcel( toList);
    }

    @Override
    public byte[] exportReport(ExportExcelCondition to) throws SerException {
        return summarySer.exportReport(to);
    }
}