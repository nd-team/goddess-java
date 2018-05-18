package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.CalculationCollectBO;
import com.bjike.goddess.projectcalculation.service.CalculationCollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目测算管理汇总业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-12 10:27 ]
 * @Description: [ 项目测算管理汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("calculationCollectApiImpl")
public class CalculationCollectApiImpl implements CalculationCollectAPI {

    @Autowired
    private CalculationCollectSer calculationCollectSer;
    @Override
    public List<CalculationCollectBO> save() throws SerException {
        return calculationCollectSer.save();
    }
}