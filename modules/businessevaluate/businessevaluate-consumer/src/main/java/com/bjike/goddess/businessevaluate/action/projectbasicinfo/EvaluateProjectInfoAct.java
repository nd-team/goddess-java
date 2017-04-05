package com.bjike.goddess.businessevaluate.action.projectbasicinfo;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/evaluateprojectinfo")
public class EvaluateProjectInfoAct {

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 新增商务评估项目基本信息
     *
     * @param to 商务评估项目基本信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(EvaluateProjectInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EvaluateProjectInfoVO vo = BeanTransform.copyProperties(evaluateProjectInfoAPI.addModel(to), EvaluateProjectInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务评估项目基本信息
     *
     * @param to 商务评估项目基本信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(EvaluateProjectInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EvaluateProjectInfoVO vo = BeanTransform.copyProperties(evaluateProjectInfoAPI.editModel(to), EvaluateProjectInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商务评估项目基本信息
     *
     * @param id 商务评估项目基本信息ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            evaluateProjectInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目基本信息
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(EvaluateProjectInfoDTO dto) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.pageList(dto), EvaluateProjectInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /*
    *//**
     * 查询利润率最高最低项目信息
     *
     * @version v1
     *//*
    @GetMapping("v1/profitScope")
    public Result profitScope() throws ActException {
        try {
            List<ProjectProfitRateVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.profitScope(), ProjectProfitRateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }*/


}