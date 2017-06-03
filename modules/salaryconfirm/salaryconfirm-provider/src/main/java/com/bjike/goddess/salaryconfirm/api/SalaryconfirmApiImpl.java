package com.bjike.goddess.salaryconfirm.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salaryconfirm.bo.AnalyzeBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.service.SalaryconfirmSer;
import com.bjike.goddess.salaryconfirm.to.ConditionTO;
import com.bjike.goddess.salaryconfirm.to.SalaryconfirmTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 薪资核算确认业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("salaryconfirmApiImpl")
public class SalaryconfirmApiImpl implements SalaryconfirmAPI {

    @Autowired
    private SalaryconfirmSer salaryconfirmSer;

    @Override
    public SalaryconfirmBO add(SalaryconfirmTO to) throws SerException {
        return salaryconfirmSer.insertModel(to);
    }

    @Override
    public SalaryconfirmBO edit(SalaryconfirmTO to) throws SerException {
        return salaryconfirmSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        salaryconfirmSer.remove(id);
    }

    @Override
    public List<SalaryconfirmBO> pageList(SalaryconfirmDTO dto) throws SerException {
        return salaryconfirmSer.pageList(dto);
    }

    @Override
    public Long count(SalaryconfirmDTO dto) throws SerException {
        return salaryconfirmSer.count(dto);
    }

    @Override
    public SalaryconfirmBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(salaryconfirmSer.findById(id), SalaryconfirmBO.class);
    }

    @Override
    public void confirm(String id) throws SerException {
        salaryconfirmSer.confirm(id);
    }

    @Override
    public void firstPay(String id) throws SerException {
        salaryconfirmSer.firstPay(id);
    }

    @Override
    public void secondPay(String id) throws SerException {
        salaryconfirmSer.secondPay(id);
    }

    @Override
    public void firstConfirm(String id) throws SerException {
        salaryconfirmSer.firstConfirm(id);
    }

    @Override
    public void secondConfirm(String id) throws SerException {
        salaryconfirmSer.secondConfirm(id);
    }

    @Override
    public List<SalaryconfirmBO> collectByCondition(ConditionTO to) throws SerException {
        return salaryconfirmSer.collectByCondition(to);
    }

    @Override
    public List<AnalyzeBO> analyzeByArea(ConditionTO to, String type) throws SerException {
        return salaryconfirmSer.analyzeByCondition(to, type);
    }

    @Override
    public List<AnalyzeBO> analyzeByDepart(ConditionTO to, String type) throws SerException {
        return salaryconfirmSer.analyzeByCondition(to, type);
    }

    @Override
    public List<AnalyzeBO> analyzeByName(ConditionTO to, String type) throws SerException {
        return salaryconfirmSer.analyzeByCondition(to, type);
    }

    @Override
    public void importExcel(List<SalaryconfirmTO> toList) throws SerException {
        salaryconfirmSer.importExcel(toList);
    }

    @Override
    public byte[] exportExcel(Integer year, Integer month) throws SerException {
        return salaryconfirmSer.exportExcel(year,month);
    }

}