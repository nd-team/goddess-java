package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.DemandCostBO;
import com.bjike.goddess.businessevaluate.dto.DemandCostDTO;
import com.bjike.goddess.businessevaluate.to.DemandCostTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 需求成本业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 10:50 ]
 * @Description: [ 需求成本业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandCostAPI {

    /**
     * 添加需求成本
     *
     * @param to 需求成本
     * @return 需求成本
     */
    DemandCostBO addModel(DemandCostTO to) throws SerException;

    /**
     * 编辑需求成本
     *
     * @param to 需求成本
     * @return 需求成本
     */
    DemandCostBO editModel(DemandCostTO to) throws SerException;

    /**
     * 编辑需求成本
     *
     * @param id 需求成本id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 需求成本结果集
     */
    List<DemandCostBO> pageList(DemandCostDTO dto) throws SerException;
}