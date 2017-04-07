package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.AnotherCostBO;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.entity.AnotherCost;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 其它成本业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnotherCostSer extends Ser<AnotherCost, AnotherCostDTO> {

    /**
     * 添加其它成本
     *
     * @param to 其它成本
     * @return 其它成本
     */
    AnotherCostBO insertModel(AnotherCostTO to) throws SerException;

    /**
     * 编辑其它成本
     *
     * @param to 其它成本
     * @return 其它成本
     */
    AnotherCostBO updateModel(AnotherCostTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 其它成本结果集
     */
    List<AnotherCostBO> pageList(AnotherCostDTO dto) throws SerException;
}