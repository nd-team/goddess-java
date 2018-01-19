package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcalculation.bo.CalculationCollectBO;
import com.bjike.goddess.projectcalculation.dto.CalculationCollectDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationCollect;

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
public interface CalculationCollectSer extends Ser<CalculationCollect, CalculationCollectDTO> {
    List<CalculationCollectBO> save() throws SerException;
}