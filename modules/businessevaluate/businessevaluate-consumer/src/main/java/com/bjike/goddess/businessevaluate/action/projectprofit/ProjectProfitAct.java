package com.bjike.goddess.businessevaluate.action.projectprofit;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
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
@RequestMapping("businessevaluate/projectprofitAct")
public class ProjectProfitAct {

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 分页查询利润率
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/profitPageList")
    public Result profitPageList(EvaluateProjectInfoDTO dto) throws ActException {
        try {
            List<ProjectProfitRateVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.profitPageList(dto), ProjectProfitRateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}