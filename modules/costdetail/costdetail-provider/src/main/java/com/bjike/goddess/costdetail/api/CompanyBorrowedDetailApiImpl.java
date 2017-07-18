package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.CompanyBorrowedDetail;
import com.bjike.goddess.costdetail.service.CompanyBorrowedDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司借入明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:11 ]
 * @Description: [ 公司借入明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyBorrowedDetailApiImpl")
public class CompanyBorrowedDetailApiImpl implements CompanyBorrowedDetailAPI {
    @Autowired
    private CompanyBorrowedDetailSer companyBorrowedDetailSer;
    @Override
    public List<String> findTypeName() throws SerException {
        return companyBorrowedDetailSer.findTypeName();
    }

    @Override
    public List<CompanyBorrowedDetail> findByCostId(String id) throws SerException {
        return companyBorrowedDetailSer.findByCostId(id);
    }
}