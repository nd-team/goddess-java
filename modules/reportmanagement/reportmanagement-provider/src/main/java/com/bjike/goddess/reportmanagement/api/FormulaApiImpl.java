package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.service.FormulaSer;
import com.bjike.goddess.reportmanagement.to.FormulaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对应的公式业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("formulaApiImpl")
public class FormulaApiImpl implements FormulaAPI {
    @Autowired
    private FormulaSer formulaSer;

    @Override
    public List<FormulaBO> findByFid(String foreignId, String startTime, String endTime, String projectGroup) throws SerException {
        return formulaSer.findByFid(foreignId, startTime, endTime, projectGroup);
    }

    @Override
    public List<FormulaBO> profitAnalyze(String foreignId, String time, String projectGroup) throws SerException {
        return formulaSer.profitAnalyze(foreignId, time, projectGroup);
    }

    @Override
    public FormulaBO add(FormulaTO to) throws SerException {
        return formulaSer.add(to);
    }

    @Override
    public FormulaBO remove(FormulaTO to) throws SerException {
        return formulaSer.remove(to);
    }

    @Override
    public void delete(String id) throws SerException {
        formulaSer.delete(id);
    }

    @Override
    public FormulaBO save(FormulaTO to) throws SerException {
        return formulaSer.save(to);
    }
}