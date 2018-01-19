package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CollectBO;

import java.util.List;

/**
 * 成本明细汇总业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectAPI {

    List<CollectBO> getCollect(String date) throws SerException;
}