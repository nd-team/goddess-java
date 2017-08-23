package com.bjike.goddess.businessevaluate.service.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.BusinessPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.BusinessPrincipalEvaluate;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 商务负责人评价业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessPrincipalEvaluateSer extends Ser<BusinessPrincipalEvaluate, BusinessPrincipalEvaluateDTO> {

    /**
     * 添加商务负责人评价
     *
     * @param to 商务负责人评价
     * @return 商务负责人评价
     */
    BusinessPrincipalEvaluateBO insertModel(BusinessPrincipalEvaluateTO to) throws SerException;

    /**
     * 编辑商务负责人评价
     *
     * @param to 商务负责人评价
     * @return 商务负责人评价
     */
    BusinessPrincipalEvaluateBO updateModel(BusinessPrincipalEvaluateTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param to 分页条件
     * @return 商务负责人评价结果集
     */
    List<BusinessPrincipalEvaluateBO> pageList(BusinessPrincipalEvaluateDTO to) throws SerException;
}