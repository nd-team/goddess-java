package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.service.ProfitSer;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 利润表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("profitApiImpl")
public class ProfitApiImpl implements ProfitAPI {
    @Autowired
    private ProfitSer profitSer;

    @Override
    public ProfitBO save(ProfitTO to) throws SerException {
        return profitSer.save(to);
    }

    @Override
    public List<ProfitBO> list(ProfitDTO dto) throws SerException {
        return profitSer.list(dto);
    }

    @Override
    public List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException {
        return profitSer.levelAnalyze(dto);
    }

    @Override
    public List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException {
        return profitSer.verticalAnalyze(dto);
    }

    @Override
    public List<ProfitAnalyzeIndicatorBO> analyzeIndicator(ProfitDTO dto) throws SerException {
        return profitSer.analyzeIndicator(dto);
    }

    @Override
    public List<DetailBO> findDetails(String id, ProfitDTO dto) throws SerException {
        return profitSer.findDetails(id, dto);
    }

    @Override
    public ProfitBO findByID(String id) throws SerException {
        return profitSer.findByID(id);
    }

    @Override
    public void edit(ProfitTO to) throws SerException {
        profitSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        profitSer.delete(id);
    }

    @Override
    public Long count(ProfitDTO dto) throws SerException {
        return profitSer.count(dto);
    }
}