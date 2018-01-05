package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.entity.LaborCostDetail;

import java.util.List;

/**
 * 劳务成本明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LaborCostDetailAPI {
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
     * 成本明细id查找劳务成本明细数据
     *
     * @param id 成本明细
     * @return class LaborCostDetailBO
     * @throws SerException
     */
    List<LaborCostDetail> findByCostId(String id) throws SerException;
}