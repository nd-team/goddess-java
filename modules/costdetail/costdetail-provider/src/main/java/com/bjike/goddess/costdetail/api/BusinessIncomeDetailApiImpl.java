package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.BusinessIncomeDetail;
import com.bjike.goddess.costdetail.service.BusinessIncomeDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主营业务收入明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:27 ]
 * @Description: [ 主营业务收入明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessIncomeDetailApiImpl")
public class BusinessIncomeDetailApiImpl implements BusinessIncomeDetailAPI {
    @Autowired
    private BusinessIncomeDetailSer businessIncomeDetailSer;
    @Override
    public List<String> findTypeName() throws SerException {
        return businessIncomeDetailSer.findTypeName();
    }

    @Override
    public List<BusinessIncomeDetail> findByCostId(String id) throws SerException {
        return businessIncomeDetailSer.findByCostId(id);
    }
}