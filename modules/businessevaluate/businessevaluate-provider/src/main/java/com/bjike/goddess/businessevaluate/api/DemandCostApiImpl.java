package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.DemandCostBO;
import com.bjike.goddess.businessevaluate.dto.DemandCostDTO;
import com.bjike.goddess.businessevaluate.service.DemandCostSer;
import com.bjike.goddess.businessevaluate.to.DemandCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 需求成本业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 11:06 ]
 * @Description: [ 需求成本业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("demandCostApiImpl")
public class DemandCostApiImpl implements DemandCostAPI {

    @Autowired
    private DemandCostSer demandCostSer;

    @Override
    public DemandCostBO addModel(DemandCostTO to) throws SerException {
        return demandCostSer.addModel(to);
    }

    @Override
    public DemandCostBO editModel(DemandCostTO to) throws SerException {
        return demandCostSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        demandCostSer.remove(id);
    }

    @Override
    public List<DemandCostBO> pageList(DemandCostDTO dto) throws SerException {
        return demandCostSer.pageList(dto);
    }
}