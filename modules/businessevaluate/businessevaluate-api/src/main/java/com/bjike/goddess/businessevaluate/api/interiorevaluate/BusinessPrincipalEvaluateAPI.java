package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.BusinessPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface BusinessPrincipalEvaluateAPI {

    /**
     * 添加商务负责人评价
     *
     * @param to 商务负责人评价
     * @return 商务负责人评价
     */
    BusinessPrincipalEvaluateBO addModel(BusinessPrincipalEvaluateTO to) throws SerException;

    /**
     * 编辑商务负责人评价
     *
     * @param to 商务负责人评价
     * @return 商务负责人评价
     */
    BusinessPrincipalEvaluateBO editModel(BusinessPrincipalEvaluateTO to) throws SerException;

    /**
     * 删除商务负责人评价
     *
     * @param id 商务负责人评价id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 商务负责人评价结果集
     */
    List<BusinessPrincipalEvaluateBO> pageList(BusinessPrincipalEvaluateDTO dto) throws SerException;
}