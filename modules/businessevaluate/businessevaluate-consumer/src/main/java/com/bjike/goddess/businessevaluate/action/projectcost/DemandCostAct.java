package com.bjike.goddess.businessevaluate.action.projectcost;

import com.bjike.goddess.businessevaluate.api.DemandCostAPI;
import com.bjike.goddess.businessevaluate.dto.DemandCostDTO;
import com.bjike.goddess.businessevaluate.to.DemandCostTO;
import com.bjike.goddess.businessevaluate.vo.DemandCostVO;
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
 * 需求成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 11:06 ]
 * @Description: [ 需求成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/demandcost")
public class DemandCostAct {

    @Autowired
    private DemandCostAPI demandCostAPI;

    /**
     * 新增需求成本
     *
     * @param to 需求成本
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(DemandCostTO to, BindingResult bindingResult) throws ActException {
        try {
            DemandCostVO vo = BeanTransform.copyProperties(demandCostAPI.addModel(to), DemandCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑需求成本
     *
     * @param to 需求成本
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(DemandCostTO to, BindingResult bindingResult) throws ActException {
        try {
            DemandCostVO vo = BeanTransform.copyProperties(demandCostAPI.editModel(to), DemandCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除需求成本
     *
     * @param id 需求成本id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            demandCostAPI.delete(id);
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
    public Result delete(DemandCostDTO dto) throws ActException {
        try {
            List<DemandCostVO> voList = BeanTransform.copyProperties(demandCostAPI.pageList(dto), DemandCostVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}