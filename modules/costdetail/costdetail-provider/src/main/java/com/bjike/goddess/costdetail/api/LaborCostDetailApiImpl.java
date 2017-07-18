package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.LaborCostDetail;
import com.bjike.goddess.costdetail.service.LaborCostDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 劳务成本明细业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("laborCostDetailApiImpl")
public class LaborCostDetailApiImpl implements LaborCostDetailAPI {
    @Autowired
    private LaborCostDetailSer laborCostDetailSer;
    @Override
    public List<String> findTypeName() throws SerException {
        return laborCostDetailSer.findTypeName();
    }

    @Override
    public List<LaborCostDetail> findByCostId(String id) throws SerException {
        return laborCostDetailSer.findByCostId(id);
    }
}