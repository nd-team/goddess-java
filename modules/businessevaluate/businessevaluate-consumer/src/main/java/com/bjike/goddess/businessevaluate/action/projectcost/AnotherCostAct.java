package com.bjike.goddess.businessevaluate.action.projectcost;

import com.bjike.goddess.businessevaluate.api.AnotherCostAPI;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
import com.bjike.goddess.businessevaluate.vo.AnotherCostVO;
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
 * 其它成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/anothercost")
public class AnotherCostAct {

    @Autowired
    private AnotherCostAPI anotherCostAPI;

    /**
     * 新增其它成本
     *
     * @param to 其它成本
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(AnotherCostTO to, BindingResult bindingResult) throws ActException {
        try {
            AnotherCostVO vo = BeanTransform.copyProperties(anotherCostAPI.addModel(to), AnotherCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑其它成本
     *
     * @param to 其它成本
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(AnotherCostTO to, BindingResult bindingResult) throws ActException {
        try {
            AnotherCostVO vo = BeanTransform.copyProperties(anotherCostAPI.editModel(to), AnotherCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除其它成本
     *
     * @param id 其它成本id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            anotherCostAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result delete(AnotherCostDTO dto) throws ActException {
        try {
            List<AnotherCostVO> voList = BeanTransform.copyProperties(anotherCostAPI.pageList(dto), AnotherCostVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}