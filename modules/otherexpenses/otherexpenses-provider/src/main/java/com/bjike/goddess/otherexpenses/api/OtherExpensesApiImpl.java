package com.bjike.goddess.otherexpenses.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.otherexpenses.bo.*;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.service.OtherExpensesSer;
import com.bjike.goddess.otherexpenses.to.CollectTO;
import com.bjike.goddess.otherexpenses.to.OtherExpensesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他费用业务接口实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("otherExpensesApiImpl")
public class OtherExpensesApiImpl implements OtherExpensesAPI {

    @Autowired
    private OtherExpensesSer otherExpensesSer;

    @Override
    public OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        return otherExpensesSer.save(to);
    }

    @Override
    public OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        return otherExpensesSer.update(to);
    }

    @Override
    public OtherExpensesBO delete(String id) throws SerException {
        return otherExpensesSer.delete(id);
    }

    @Override
    public OtherExpensesBO getById(String id) throws SerException {
        return otherExpensesSer.getById(id);
    }

    @Override
    public List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        return otherExpensesSer.maps(dto);
    }

    @Override
    public List<AreaCollectBO> areaCollect(CollectTO to) throws SerException {
        return otherExpensesSer.areaCollect(to);
    }

    @Override
    public List<NameCollectBO> nameCollect(CollectTO to) throws SerException {
        return otherExpensesSer.nameCollect(to);
    }

    @Override
    public List<TypeCollectBO> typeCollect(CollectTO to) throws SerException {
        return otherExpensesSer.typeCollect(to);
    }

    @Override
    public List<ProjectCollectBO> projectCollect(CollectTO to) throws SerException {
        return otherExpensesSer.projectCollect(to);
    }

    @Override
    public Long getTotal() throws SerException {
        OtherExpensesDTO dto = new OtherExpensesDTO();
        return otherExpensesSer.count(dto);
    }
}