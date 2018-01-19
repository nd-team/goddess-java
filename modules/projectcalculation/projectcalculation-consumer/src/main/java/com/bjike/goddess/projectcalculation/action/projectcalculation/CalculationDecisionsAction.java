package com.bjike.goddess.projectcalculation.action.projectcalculation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcalculation.api.CalculationDecisionsAPI;
import com.bjike.goddess.projectcalculation.to.CalculationDecisionsTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.vo.CalculationDecisionsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测算决策
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-07 02:41 ]
 * @Description: [ 测算决策 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("calculationdecisions")
public class CalculationDecisionsAction {
    @Autowired
    private CalculationDecisionsAPI calculationDecisionsAPI;


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

            Boolean isHasPermission = calculationDecisionsAPI.guidePermission(guidePermissionTO);
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


    //获取列表
    @GetMapping("v1/getlist")
    public Result getList() throws SerException {
        return new ActResult("success", calculationDecisionsAPI.getList());
    }

    //项目经理审核
    @GetMapping("v1/getprojectmanage")
    public Result getProjectManage(CalculationDecisionsTO to) throws SerException {
        CalculationDecisionsVO vo = BeanTransform.copyProperties(calculationDecisionsAPI.getProjectManage(to), CalculationDecisionsVO.class, "generalManager", "generalManagerOpinion");
        return new ActResult();
    }

    //总经理审核
    @GetMapping("v1/getgeneralmanager")
    public Result getGeneralManager(CalculationDecisionsTO to) throws SerException {
        CalculationDecisionsVO vo = BeanTransform.copyProperties(calculationDecisionsAPI.getProjectManage(to), CalculationDecisionsVO.class);
        return new ActResult("success", vo);
    }


    @PostMapping("v1/update")
    public Result upDate(CalculationDecisionsTO to) throws SerException {
        calculationDecisionsAPI.upDate(to);
        return new ActResult("success");
    }

    @PostMapping("v1/save")
    public Result save(CalculationDecisionsTO calculationDecisionsTO) throws SerException {
        calculationDecisionsAPI.save(calculationDecisionsTO);
        return new ActResult("success");
    }

    //根据市场编号查询
    @GetMapping("v1/searchlist/{marketInfoNum}")
    public Result getSearchList(@PathVariable String marketInfoNum) throws SerException {
        List<CalculationDecisionsVO> vos = BeanTransform.copyProperties(calculationDecisionsAPI.getSearchList(marketInfoNum), CalculationDecisionsVO.class);
        return new ActResult("success", vos);
    }

}