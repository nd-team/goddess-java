package com.bjike.goddess.businessevaluate.action.marketresponse;

import com.bjike.goddess.businessevaluate.api.ProblemDisposeAPI;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.businessevaluate.vo.ProblemDisposeVO;
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
 * 项目问题受理和处理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/problemdispose")
public class ProblemDisposeAct {

    @Autowired
    private ProblemDisposeAPI problemDisposeAPI;

    /**
     * 新增项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProblemDisposeTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.addModel(to), ProblemDisposeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ProblemDisposeTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑定性指标
     *
     * @param to 定性指标信息
     * @version v1
     */
    @PostMapping("v1/qualitativeKPI")
    public Result qualitativeKPI(ProblemDisposeTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑定量指标
     *
     * @param to 定量指标
     * @version v1
     */
    @PostMapping("v1/rationKPI")
    public Result rationKPI(ProblemDisposeTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目问题受理和处理
     *
     * @param id 项目问题受理和处理ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            problemDisposeAPI.delete(id);
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
    public Result pageList(ProblemDisposeDTO dto) throws ActException {
        try {
            List<ProblemDisposeVO> voList = BeanTransform.copyProperties(problemDisposeAPI.pageList(dto), ProblemDisposeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}