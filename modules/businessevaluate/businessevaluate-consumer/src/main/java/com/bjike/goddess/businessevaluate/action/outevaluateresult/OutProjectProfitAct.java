package com.bjike.goddess.businessevaluate.action.outevaluateresult;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.vo.ProjectProfitRateVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目利润率
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目利润率 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outprojectprofitAct")
public class OutProjectProfitAct {

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 查询利润率最高最低项目信息
     *
     * @return class ProjectProfitRateVO
     * @version v1
     */
    @GetMapping("v1/profitscope")
    public Result profitScope(HttpServletRequest request) throws ActException {
        try {
            List<ProjectProfitRateVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.profitScope(), ProjectProfitRateVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}