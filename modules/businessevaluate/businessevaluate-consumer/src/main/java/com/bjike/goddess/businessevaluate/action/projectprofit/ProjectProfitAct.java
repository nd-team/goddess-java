package com.bjike.goddess.businessevaluate.action.projectprofit;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.businessevaluate.vo.ProjectProfitRateVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("projectprofitAct")
public class ProjectProfitAct {

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = evaluateProjectInfoAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询利润率
     *
     * @param dto 分页条件
     * @return class ProjectProfitRateVO
     * @version v1
     */
    @GetMapping("v1/profitPageList")
    public Result profitPageList(EvaluateProjectInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectProfitRateVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.profitPageList(dto), ProjectProfitRateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}