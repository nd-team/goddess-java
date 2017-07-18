package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.CompanyLendDetail;
import com.bjike.goddess.costdetail.service.CompanyLendDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司借出明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:18 ]
 * @Description: [ 公司借出明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyLendDetailApiImpl")
public class CompanyLendDetailApiImpl implements CompanyLendDetailAPI {
    @Autowired
    private CompanyLendDetailSer companyLendDetailSer;
    @Override
    public List<String> findTypeName() throws SerException {
        return companyLendDetailSer.findTypeName();
    }

    @Override
    public List<CompanyLendDetail> findByCostId(String id) throws SerException {
        return companyLendDetailSer.findByCostId(id);
    }
}