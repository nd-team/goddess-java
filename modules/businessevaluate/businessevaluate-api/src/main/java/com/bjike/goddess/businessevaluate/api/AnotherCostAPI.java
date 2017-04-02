package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.AnotherCostBO;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface AnotherCostAPI {

    /**
     * 添加其它成本
     *
     * @param to 其它成本
     * @return 其它成本
     */
    AnotherCostBO addModel(AnotherCostTO to) throws SerException;

    /**
     * 编辑其它成本
     *
     * @param to 其它成本
     * @return 其它成本
     */
    AnotherCostBO editModel(AnotherCostTO to) throws SerException;

    /**
     * 删除其它成本
     *
     * @param id 其它成本id
     * @return 其它成本
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 其它成本结果集
     */
    List<AnotherCostBO> pageList(AnotherCostDTO dto) throws SerException;
}