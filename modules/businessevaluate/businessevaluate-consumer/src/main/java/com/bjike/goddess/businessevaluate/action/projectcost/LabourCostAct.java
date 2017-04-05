package com.bjike.goddess.businessevaluate.action.projectcost;

import com.bjike.goddess.businessevaluate.api.LabourCostAPI;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
import com.bjike.goddess.businessevaluate.vo.LabourCostVO;
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
 * 劳动成本
 *
 * @Author: [Jason]
 * @Date: [17-3-28 上午9:24]
 * @Description: [劳动成本]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("businessevaluate/labourcost")
public class LabourCostAct {

    @Autowired
    private LabourCostAPI labourCostAPI;

    /**
     * 新增劳动成本
     *
     * @param to 劳动成本
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(LabourCostTO to, BindingResult bindingResult) throws ActException {
        try {
            LabourCostVO vo = BeanTransform.copyProperties(labourCostAPI.addModel(to), LabourCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑劳动成本
     *
     * @param to 劳动成本
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(LabourCostTO to, BindingResult bindingResult) throws ActException {
        try {
            LabourCostVO vo = BeanTransform.copyProperties(labourCostAPI.editModel(to), LabourCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除劳动成本
     *
     * @param id 劳动成本id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            labourCostAPI.delete(id);
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
    public Result delete(LabourCostDTO dto) throws ActException {
        try {
            List<LabourCostVO> voList = BeanTransform.copyProperties(labourCostAPI.pageList(dto), LabourCostVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
