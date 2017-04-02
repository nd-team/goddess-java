package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.BusinessPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.BusinessPrincipalEvaluateSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务负责人评价业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessPrincipalEvaluateApiImpl")
public class BusinessPrincipalEvaluateApiImpl implements BusinessPrincipalEvaluateAPI {

    @Autowired
    private BusinessPrincipalEvaluateSer businessPrincipalEvaluateSer;

    @Override
    public BusinessPrincipalEvaluateBO addModel(BusinessPrincipalEvaluateTO to) throws SerException {
        return businessPrincipalEvaluateSer.insertModel(to);
    }

    @Override
    public BusinessPrincipalEvaluateBO editModel(BusinessPrincipalEvaluateTO to) throws SerException {
        return businessPrincipalEvaluateSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        businessPrincipalEvaluateSer.remove(id);
    }

    @Override
    public List<BusinessPrincipalEvaluateBO> pageList(BusinessPrincipalEvaluateDTO dto) throws SerException {
        return businessPrincipalEvaluateSer.pageList(dto);
    }
}