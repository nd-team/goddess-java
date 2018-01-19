package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.CalculationCollectBO;

import java.util.List;

/**
 * 项目测算管理汇总业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-12 10:27 ]
 * @Description: [ 项目测算管理汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CalculationCollectAPI {
    List<CalculationCollectBO> save() throws SerException;
}