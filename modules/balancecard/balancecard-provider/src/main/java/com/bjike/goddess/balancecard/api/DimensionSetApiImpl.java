package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.DimensionSetBO;
import com.bjike.goddess.balancecard.dto.DimensionSetDTO;
import com.bjike.goddess.balancecard.service.DimensionSetSer;
import com.bjike.goddess.balancecard.to.DimensionSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维度设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dimensionSetApiImpl")
public class DimensionSetApiImpl implements DimensionSetAPI {

    @Autowired
    private DimensionSetSer dimensionSetSer;

    @Override
    public Long countDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        return dimensionSetSer.countDimensionSet( dimensionSetDTO);
    }

    @Override
    public DimensionSetBO getOneById(String id) throws SerException {
        return dimensionSetSer.getOneById(id);
    }

    @Override
    public List<DimensionSetBO> listDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        return dimensionSetSer.listDimensionSet(dimensionSetDTO);
    }

    @Override
    public DimensionSetBO addDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        return dimensionSetSer.addDimensionSet(dimensionSetTO);
    }

    @Override
    public DimensionSetBO editDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        return dimensionSetSer.editDimensionSet(dimensionSetTO);
    }

    @Override
    public void deleteDimensionSet(String id) throws SerException {
        dimensionSetSer.deleteDimensionSet(id);
    }

    @Override
    public List<String> listName( ) throws SerException {
        return dimensionSetSer.listName();
    }
}