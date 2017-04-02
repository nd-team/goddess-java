package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.FinanceInfoBO;
import com.bjike.goddess.qualifications.service.FinanceInfoSer;
import com.bjike.goddess.qualifications.to.FinanceInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务资料业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("financeInfoApiImpl")
public class FinanceInfoApiImpl implements FinanceInfoAPI {

    @Autowired
    private FinanceInfoSer financeInfoSer;

    @Override
    public FinanceInfoBO save(FinanceInfoTO to) throws SerException {
        return financeInfoSer.save(to);
    }

    @Override
    public FinanceInfoBO update(FinanceInfoTO to) throws SerException {
        return financeInfoSer.update(to);
    }

    @Override
    public FinanceInfoBO delete(String id) throws SerException {
        return financeInfoSer.delete(id);
    }

    @Override
    public List<FinanceInfoBO> all() throws SerException {
        return financeInfoSer.all();
    }
}