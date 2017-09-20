package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.OperatExpensesBO;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.service.OperatExpensesSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesCollectTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 营业费用业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("operatExpensesApiImpl")
public class OperatExpensesApiImpl implements OperatExpensesAPI {
    @Autowired
    private OperatExpensesSer operatExpensesSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return operatExpensesSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return operatExpensesSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(OperatExpensesDTO operatExpensesDTO) throws SerException {
        return operatExpensesSer.count(operatExpensesDTO);
    }

    @Override
    public OperatExpensesBO getOne(String id) throws SerException {
        return operatExpensesSer.getOne(id);
    }

    @Override
    public List<OperatExpensesBO> findList(OperatExpensesDTO operatExpensesDTO) throws SerException {
        return operatExpensesSer.findList(operatExpensesDTO);
    }

    @Override
    public OperatExpensesBO insert(OperatExpensesTO operatExpensesTO) throws SerException {
        return operatExpensesSer.insert(operatExpensesTO);
    }

    @Override
    public OperatExpensesBO edit(OperatExpensesTO operatExpensesTO) throws SerException {
        return operatExpensesSer.edit(operatExpensesTO);
    }

    @Override
    public void remove(String id) throws SerException {
        operatExpensesSer.remove(id);
    }
    @Override
    public LinkedHashMap<String,String> collect(OperatExpensesCollectTO to) throws SerException {
        return operatExpensesSer.collect(to);
    }
    @Override
    public List<String> listType() throws SerException {
        return operatExpensesSer.listType();
    }
    @Override
    public OperatExpensesBO importExcel(List<OperatExpensesTO> operatExpensesTOS) throws SerException {
        return operatExpensesSer.importExcel(operatExpensesTOS);
    }
    @Override
    public byte[] templateExport() throws SerException {
        return operatExpensesSer.templateExport();
    }
}