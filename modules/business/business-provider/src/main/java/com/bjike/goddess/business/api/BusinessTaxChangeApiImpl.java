package com.bjike.goddess.business.api;

import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.service.BusinessTaxChangeSer;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工商税务变更业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessTaxChangeApiImpl")
public class BusinessTaxChangeApiImpl implements BusinessTaxChangeAPI {
    @Autowired
    private BusinessTaxChangeSer businessTaxChangeSer;
    @Override
    public Long countBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        return businessTaxChangeSer.countBusinessTaxChange(businessTaxChangeDTO);
    }
    @Override
    public BusinessTaxChangeBO getOne(String id) throws SerException {
        return businessTaxChangeSer.getOne(id);
    }

    @Override
    public List<BusinessTaxChangeBO> findListBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        return businessTaxChangeSer.findListBusinessTaxChange(businessTaxChangeDTO);
    }

    @Override
    public BusinessTaxChangeBO insertBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        return businessTaxChangeSer.insertBusinessTaxChange(businessTaxChangeTO);
    }

    @Override
    public BusinessTaxChangeBO editBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        return businessTaxChangeSer.editBusinessTaxChange(businessTaxChangeTO);
    }

    @Override
    public void removeBusinessTaxChange(String id) throws SerException {
        businessTaxChangeSer.removeBusinessTaxChange(id);
    }

    @Override
    public void upload() throws SerException {
        businessTaxChangeSer.upload();

    }

    @Override
    public void download() throws SerException {
        businessTaxChangeSer.download();

    }

}