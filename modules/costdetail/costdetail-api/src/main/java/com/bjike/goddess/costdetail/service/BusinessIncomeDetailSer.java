package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.costdetail.dto.BusinessIncomeDetailDTO;
import com.bjike.goddess.costdetail.entity.BusinessIncomeDetail;

import java.util.List;

/**
 * 主营业务收入明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:27 ]
 * @Description: [ 主营业务收入明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessIncomeDetailSer extends Ser<BusinessIncomeDetail, BusinessIncomeDetailDTO> {
    /**
     * 获取所有明细分类名
     *
     * @return
     * @throws SerException
     */
    default List<String> findTypeName() throws SerException {
        return null;
    }
    /**
     * 成本明细id查找主营业务数据
     *
     * @param id 成本明细
     * @return class BusinessIncomeDetailBO
     * @throws SerException
     */
    List<BusinessIncomeDetail> findByCostId(String id) throws SerException;
}