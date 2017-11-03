package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.BussTypeWeightSetBO;
import com.bjike.goddess.customer.dto.BussTypeWeightSetDTO;
import com.bjike.goddess.customer.service.BussTypeWeightSetSer;
import com.bjike.goddess.customer.to.BussTypeWeightSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务类型权重设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bussTypeWeightSetApiImpl")
public class BussTypeWeightSetApiImpl implements BussTypeWeightSetAPI {
    @Autowired
    private BussTypeWeightSetSer bussTypeWeightSetSer;

    @Override
    public Long countBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        return bussTypeWeightSetSer.countBussType(bussTypeWeightSetDTO);
    }

    @Override
    public BussTypeWeightSetBO getOneBussType(String id) throws SerException {
        return bussTypeWeightSetSer.getOneBussType(id);
    }

    @Override
    public List<BussTypeWeightSetBO> listBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        return bussTypeWeightSetSer.listBussType(bussTypeWeightSetDTO);
    }

    @Override
    public BussTypeWeightSetBO addBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        return bussTypeWeightSetSer.addBussType(bussTypeWeightSetTO);
    }

    @Override
    public BussTypeWeightSetBO editBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        return bussTypeWeightSetSer.editBussType(bussTypeWeightSetTO);
    }

    @Override
    public void deleteBussType(String id) throws SerException {
        bussTypeWeightSetSer.deleteBussType(id);
    }
}