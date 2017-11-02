package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitFormulaDTO;
import com.bjike.goddess.reportmanagement.service.ProfitSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitFormulaTO;
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

    @Override
    public void addFormula(ProfitFormulaTO to) throws SerException {
        profitSer.addFormula(to);
    }

    @Override
    public void editFormula(ProfitFormulaTO to) throws SerException {
        profitSer.editFormula(to);
    }

    @Override
    public void deleteFormula(String id) throws SerException {
        profitSer.deleteFormula(id);
    }

    @Override
    public String findFormula(String id) throws SerException {
        return profitSer.findFormula(id);
    }

    @Override
    public ProfitFormulaBO findFormulaByID(String id) throws SerException {
        return profitSer.findFormulaByID(id);
    }

    @Override
    public List<ProfitFormulaBO> decreaseRatioList(ProfitFormulaDTO profitFormulaDTO) throws SerException {
        return profitSer.decreaseRatioList(profitFormulaDTO);
    }

    @Override
    public List<String> analysisChanges(ProfitDTO dto) throws SerException {
        return profitSer.analysisChanges(dto);
    }

    @Override
    public Long getFormulaTotal(ProfitFormulaDTO dto) throws SerException {
        return profitSer.getFormulaTotal(dto);
    }

    @Override
    public List<ProfitFormulaBO> analysisChangesList(ProfitFormulaDTO dto) throws SerException {
        return profitSer.analysisChangesList(dto);
    }

    @Override
    public void analysisChangesAdd(ProfitFormulaTO to) throws SerException {
        profitSer.analysisChangesAdd(to);
    }

    @Override
    public Long analysisChangesTotal(ProfitFormulaDTO dto) throws SerException {
        return profitSer.analysisChangesTotal(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return profitSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return profitSer.guidePermission(guidePermissionTO);
    }
}