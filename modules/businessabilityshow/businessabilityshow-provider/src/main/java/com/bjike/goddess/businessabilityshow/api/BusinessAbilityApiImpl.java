package com.bjike.goddess.businessabilityshow.api;

import com.bjike.goddess.businessabilityshow.bo.BACollectEchartBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityCollectBO;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.service.BusinessAbilitySer;
import com.bjike.goddess.businessabilityshow.to.BusinessAbilityTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目合同基本信息业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-16 02:50 ]
 * @Description: [ 项目合同基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessAbilityApiImpl")
public class BusinessAbilityApiImpl implements BusinessAbilityAPI {

    @Autowired
    BusinessAbilitySer businessAbilitySer;

    @Override
    public List<BusinessAbilityBO> list(BusinessAbilityDTO dto) throws SerException {
        return businessAbilitySer.list(dto);
    }

    @Override
    public Long count(BusinessAbilityDTO dto) throws SerException {
        return businessAbilitySer.count(dto);
    }

    @Override
    public BusinessAbilityBO getOne(String id) throws SerException {
        return businessAbilitySer.getOne(id);
    }

    @Override
    public void add(BusinessAbilityTO to) throws SerException {
        businessAbilitySer.add(to);
    }

    @Override
    public void update(BusinessAbilityTO to) throws SerException {
        businessAbilitySer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        businessAbilitySer.delete(id);
    }

    @Override
    public void importExcel(List<BusinessAbilityTO> tos) throws SerException {
        businessAbilitySer.importExcel(tos);
    }

    @Override
    public byte[] exportExcel(BusinessAbilityDTO dto) throws SerException {
        return businessAbilitySer.exportExcel(dto);
    }

    @Override
    public List<BusinessAbilityCollectBO> businessAbilitycollect() throws SerException {
        return businessAbilitySer.businessAbilityCollect();
    }

    @Override
    public List<BACollectEchartBO> businessAbilityEchartCollect() throws SerException {
        return businessAbilitySer.businessAbilityEchartCollect();
    }

    @Override
    public List<BACollectEchartBO> businessProjectEchartcollect() throws SerException {
        return businessAbilitySer.businessProjectEchartcollect();
    }
}