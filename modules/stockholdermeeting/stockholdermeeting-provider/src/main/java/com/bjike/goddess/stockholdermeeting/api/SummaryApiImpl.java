package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.SummaryBO;
import com.bjike.goddess.stockholdermeeting.dto.ExcelDTO;
import com.bjike.goddess.stockholdermeeting.dto.SummaryDTO;
import com.bjike.goddess.stockholdermeeting.excel.SummaryExcel;
import com.bjike.goddess.stockholdermeeting.service.SummarySer;
import com.bjike.goddess.stockholdermeeting.to.ExportExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股东大会纪要业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要业务接口实现 ]
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
    public byte[] exportExcel(ExcelDTO excelDTO) throws SerException {
        return summarySer.exportExcel(excelDTO);
    }

    @Override
    public void leadExcel(List<SummaryExcelTO> toList) throws SerException {
        summarySer.leadExcel(toList);
    }

    @Override
    public void freeze(String id) throws SerException {
        summarySer.freeze(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        summarySer.thaw(id);
    }
}