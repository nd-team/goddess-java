package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.service.BusinessNegotiationSer;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务洽谈业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessNegotiationApiImpl")
public class BusinessNegotiationApiImpl implements BusinessNegotiationAPI {
    @Autowired
    private BusinessNegotiationSer businessNegotiationSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return businessNegotiationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessNegotiationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public BusinessNegotiationBO getOne(String id) throws SerException {
        return businessNegotiationSer.getOne(id);
    }
    @Override
    public Long count(BusinessNegotiationDTO dto) throws SerException {
        return businessNegotiationSer.count(dto);
    }
    @Override
    public List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        return businessNegotiationSer.list(dto);
    }

    @Override
    public BusinessNegotiationBO insert(BusinessNegotiationTO to) throws SerException {
        return businessNegotiationSer.insert(to);
    }

    @Override
    public BusinessNegotiationBO edit(BusinessNegotiationTO to) throws SerException {
        return businessNegotiationSer.edit(to);
    }
    @Override
    public void remove(String id) throws SerException {
        businessNegotiationSer.remove(id);
    }

    @Override
    public BusinessNegotiationBO importExcel(List<BusinessNegotiationTO> businessNegotiationTOS) throws SerException {
        return businessNegotiationSer.importExcel(businessNegotiationTOS);
    }

    @Override
    public byte[] exportExcel(BusinessNegotiationDTO dto) throws SerException {
        return businessNegotiationSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return businessNegotiationSer.templateExcel();
    }
}