package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.PaidCapitalDetail;
import com.bjike.goddess.costdetail.service.PaidCapitalDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实收资本明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 07:06 ]
 * @Description: [ 实收资本明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("paidCapitalApiImpl")
public class PaidCapitalDetailApiImpl implements PaidCapitalDetailAPI {
    @Autowired
    private PaidCapitalDetailSer paidCapitalDetailSer;

    @Override
    public List<String> findTypeName() throws SerException {
        return paidCapitalDetailSer.findTypeName();
    }

    @Override
    public List<PaidCapitalDetail> findByCostId(String id) throws SerException {
        return paidCapitalDetailSer.findByCostId(id);
    }
}