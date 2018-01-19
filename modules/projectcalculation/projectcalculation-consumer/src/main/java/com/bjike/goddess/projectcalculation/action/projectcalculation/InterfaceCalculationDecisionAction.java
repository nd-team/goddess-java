package com.bjike.goddess.projectcalculation.action.projectcalculation;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcalculation.api.InterfaceCalculationDecisionAPI;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDecisionTO;
import com.bjike.goddess.projectcalculation.vo.InterfaceCalculationDecisionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Resources_sv;

import java.util.List;

/**
 * 界面测算决策
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-09 04:23 ]
 * @Description: [ 界面测算决策 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("interfacecalculationdecision")
public class InterfaceCalculationDecisionAction {

    @Autowired
    private InterfaceCalculationDecisionAPI interfaceCalculationDecisionAPI;

    @GetMapping("v1/getlist")
    public Result getList() throws SerException {
        return new ActResult("success", interfaceCalculationDecisionAPI.getList());
    }

    @GetMapping("v1/searchbyarea/{area}")
    public Result searchByArea(@PathVariable String area) throws SerException {
        List<InterfaceCalculationDecisionVO> vos = BeanTransform.copyProperties(interfaceCalculationDecisionAPI.searchByArea(area), InterfaceCalculationDecisionVO.class);
        return new ActResult("success", vos);
    }

    @GetMapping("v1/searchbyprojectnum/{projectnum}")
    public Result searchByProjectNum(@PathVariable String projectnum) throws SerException {
        return new ActResult("success", interfaceCalculationDecisionAPI.searchByProjectNum(projectnum));
    }

    @PostMapping("v1/save")
    public Result save(InterfaceCalculationDecisionTO to) throws SerException {
        interfaceCalculationDecisionAPI.save(to);
        return new ActResult("success");
    }

    @GetMapping("v1/editor/{id}")
    public Result editor(@PathVariable String id) throws SerException {
        return new ActResult("success", interfaceCalculationDecisionAPI.editor(id));
    }

    @PutMapping("v1/update")
    public Result upDate(InterfaceCalculationDecisionTO to) throws SerException {
        interfaceCalculationDecisionAPI.upDate(to);
        return new ActResult("success");
    }

    //根据地区 内部项目名 搜索
    @GetMapping("v1/search/{condition}")
    public Result search(@PathVariable String condition) {

        return new ActResult();
    }
}