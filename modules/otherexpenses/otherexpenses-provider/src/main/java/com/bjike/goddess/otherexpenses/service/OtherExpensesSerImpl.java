package com.bjike.goddess.otherexpenses.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.otherexpenses.bo.*;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.entity.OtherExpenses;
import com.bjike.goddess.otherexpenses.to.CollectTO;
import com.bjike.goddess.otherexpenses.to.OtherExpensesTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "otherexpensesSerCache")
@Service
public class OtherExpensesSerImpl extends ServiceImpl<OtherExpenses, OtherExpensesDTO> implements OtherExpensesSer {

    @Override
    public OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        return null;
    }

    private void countExpenses(OtherExpenses entity) throws SerException{

    }

    @Override
    public OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        return null;
    }

    @Override
    public OtherExpensesBO delete(OtherExpensesTO to) throws SerException {
        return null;
    }

    @Override
    public OtherExpensesBO getById(String id) throws SerException {
        return null;
    }

    @Override
    public List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        return null;
    }

    @Override
    public List<AreaCollectBO> areaCollect(CollectTO to) throws SerException {
        return null;
    }

    @Override
    public List<NameCollectBO> nameCollect(CollectTO to) throws SerException {
        return null;
    }

    @Override
    public List<TypeCollectBO> typeCollect(CollectTO to) throws SerException {
        return null;
    }

    @Override
    public List<ProjectCollectBO> projectCollect(CollectTO to) throws SerException {
        return null;
    }
}