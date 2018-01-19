package com.bjike.goddess.projectcalculation.action.projectcalculation;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcalculation.api.CalculationCollectAPI;
import com.bjike.goddess.projectcalculation.vo.CalculationCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目测算管理汇总
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-12 10:27 ]
 * @Description: [ 项目测算管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("calculationcollect")
public class CalculationCollectAction {

    @Autowired
    private CalculationCollectAPI calculationCollectAPI;
    @GetMapping("v1/test")
    public Result test() throws SerException {
        List<CalculationCollectVO> list = BeanTransform.copyProperties(calculationCollectAPI.save(), CalculationCollectVO.class);
        return new ActResult("success", list);
    }
}