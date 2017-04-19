package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.LeaseCarCostBO;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.service.LeaseCarCostSer;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租车费用基本信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("leaseCarCostApiImpl")
public class LeaseCarCostApiImpl implements LeaseCarCostAPI {

    @Autowired
    private LeaseCarCostSer leaseCarCostSer;

    @Override
    public LeaseCarCostBO addModel(LeaseCarCostTO to) throws SerException {
        return leaseCarCostSer.insertModel(to);
    }

    @Override
    public LeaseCarCostBO editModel(LeaseCarCostTO to) throws SerException {
        return leaseCarCostSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        leaseCarCostSer.remove(id);
    }

    @Override
    public List<LeaseCarCostBO> pageList(LeaseCarCostDTO dto) throws SerException {
        return leaseCarCostSer.pageList(dto);
    }
}