package com.bjike.goddess.businessevaluate.action.projectcost;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.api.LabourCostAPI;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.businessevaluate.vo.LabourCostVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("labourcost")
public class LabourCostAct {

    @Autowired
    private LabourCostAPI labourCostAPI;
    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LabourCostDTO dto) throws ActException {
        try {
            Long count = labourCostAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目基本信息
     *
     * @param id 项目基本信息id
     * @return class LabourCostVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            LabourCostVO vo = BeanTransform.copyProperties(labourCostAPI.findById(id), LabourCostVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增劳动成本
     *
     * @param to 劳动成本
     * @return class LabourCostVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) LabourCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            LabourCostVO vo = BeanTransform.copyProperties(labourCostAPI.addModel(to), LabourCostVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑劳动成本
     *
     * @param to 劳动成本
     * @return class LabourCostVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({ADD.class}) LabourCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            labourCostAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class LabourCostVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result delete(LabourCostDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<LabourCostVO> voList = BeanTransform.copyProperties(labourCostAPI.pageList(dto), LabourCostVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
