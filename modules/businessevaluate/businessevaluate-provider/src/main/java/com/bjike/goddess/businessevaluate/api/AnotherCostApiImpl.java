package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.AnotherCostBO;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.service.AnotherCostSer;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其它成本业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("anotherCostApiImpl")
public class AnotherCostApiImpl implements AnotherCostAPI {

    @Autowired
    private AnotherCostSer anotherCostSer;

    @Override
    public AnotherCostBO addModel(AnotherCostTO to) throws SerException {
        return anotherCostSer.insertModel(to);
    }

    @Override
    public AnotherCostBO editModel(AnotherCostTO to) throws SerException {
        return anotherCostSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        anotherCostSer.remove(id);
    }

    @Override
    public List<AnotherCostBO> pageList(AnotherCostDTO dto) throws SerException {
        return anotherCostSer.pageList(dto);
    }
}