package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.AbilityGrowUpBO;
import com.bjike.goddess.businessevaluate.dto.AbilityGrowUpDTO;
import com.bjike.goddess.businessevaluate.service.AbilityGrowUpSer;
import com.bjike.goddess.businessevaluate.to.AbilityGrowUpTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 能力成长业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("abilityGrowUpApiImpl")
public class AbilityGrowUpApiImpl implements AbilityGrowUpAPI {

    @Autowired
    private AbilityGrowUpSer abilityGrowUpSer;

    @Override
    public AbilityGrowUpBO addModel(AbilityGrowUpTO to) throws SerException {
        return abilityGrowUpSer.insertModel(to);
    }

    @Override
    public AbilityGrowUpBO editModel(AbilityGrowUpTO to) throws SerException {
        return abilityGrowUpSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        abilityGrowUpSer.remove(id);
    }

    @Override
    public List<AbilityGrowUpBO> pageList(AbilityGrowUpDTO dto) throws SerException {
        return abilityGrowUpSer.pageList(dto);
    }
}