package com.bjike.goddess.analysis.action.analysis;

import com.bjike.goddess.analysis.api.IncomeCostAnalysisAPI;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.analysis.vo.CollectAreaVO;
import com.bjike.goddess.analysis.vo.CollectDepartmentVO;
import com.bjike.goddess.analysis.vo.CollectMonthVO;
import com.bjike.goddess.analysis.vo.IncomeCostAnalysisVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
 * 收入成本分析
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("incomecostanalysis")
public class IncomeCostAnalysisAction {
    @Autowired
    private IncomeCostAnalysisAPI incomeCostAnalysisAPI;

    /**
     * 收入成本分析列表总条数
     *
     * @param incomeCostAnalysisDTO 收入成本分析dto
     * @des 获取所有收入成本分析
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws ActException {
        try {
            Long count = incomeCostAnalysisAPI.countIncomeCostAnalysis(incomeCostAnalysisDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收入成本分析
     *
     * @param id
     * @return class IncomeCostAnalysisVO
     * @des 获取一个收入成本分析
     * @version v1
     */
    @GetMapping("v1/analysis/{id}")
    public Result analysis(@PathVariable String id) throws ActException {
        try {
            IncomeCostAnalysisBO incomeCostAnalysisBO = incomeCostAnalysisAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(incomeCostAnalysisBO, IncomeCostAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收入成本分析列表
     *
     * @param incomeCostAnalysisDTO 收入成本分析dto
     * @return class IncomeCostAnalysisVO
     * @des 获取所有收入成本分析
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IncomeCostAnalysisDTO incomeCostAnalysisDTO, HttpServletRequest request) throws ActException {
        try {
            List<IncomeCostAnalysisVO> incomeCostAnalysisVOS = BeanTransform.copyProperties(
                    incomeCostAnalysisAPI.findListIncomeCostAnalysis(incomeCostAnalysisDTO), IncomeCostAnalysisVO.class, request);
            return ActResult.initialize(incomeCostAnalysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析to
     * @return class IncomeCostAnalysisVO
     * @des 添加收入成本分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) IncomeCostAnalysisTO incomeCostAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeCostAnalysisBO incomeCostAnalysisBO = incomeCostAnalysisAPI.insertIncomeCostAnalysis(incomeCostAnalysisTO);
            return ActResult.initialize(incomeCostAnalysisBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析数据to
     * @return class IncomeCostAnalysisVO
     * @des 编辑收入成本分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IncomeCostAnalysisTO incomeCostAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeCostAnalysisBO incomeCostAnalysisBO = incomeCostAnalysisAPI.editIncomeCostAnalysis(incomeCostAnalysisTO);
            return ActResult.initialize(incomeCostAnalysisBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除收入成本分析
     *
     * @param id 用户id
     * @des 根据用户id删除收入成本分析记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            incomeCostAnalysisAPI.removeIncomeCostAnalysis(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总地区收入成本分析
     *
     * @param areas 地区
     * @return class CollectAreaVO
     * @des 汇总收入成本分析
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@RequestParam String[] areas) throws ActException {
        try {
            List<CollectAreaVO> incomeCostAnalysisVOS = BeanTransform.copyProperties(
                    incomeCostAnalysisAPI.collectArea(areas), CollectAreaVO.class);
            return ActResult.initialize(incomeCostAnalysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areasList = incomeCostAnalysisAPI.getArea();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总月份收入成本分析
     *
     * @param months 月份
     * @return class CollectMonthVO
     * @des 汇总收入成本分析
     * @version v1
     */
    @GetMapping("v1/collectMonth")
    public Result collectMonth(@RequestParam String[] months) throws ActException {
        try {
            List<CollectMonthVO> incomeCostAnalysisVOS = BeanTransform.copyProperties(
                    incomeCostAnalysisAPI.collectMonth(months), CollectMonthVO.class);
            return ActResult.initialize(incomeCostAnalysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取月份
     *
     * @des 获取月份集合
     * @version v1
     */
    @GetMapping("v1/month")
    public Result month() throws ActException {
        try {
            List<String> monthsList = incomeCostAnalysisAPI.getMonth();
            return ActResult.initialize(monthsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总部门收入成本分析
     *
     * @param departments 部门
     * @return class CollectDepartmentVO
     * @des 汇总收入成本分析
     * @version v1
     */
    @GetMapping("v1/collectDepartment")
    public Result collectDepartment(@RequestParam String[] departments) throws ActException {
        try {
            List<CollectDepartmentVO> incomeCostAnalysisVOS = BeanTransform.copyProperties(
                    incomeCostAnalysisAPI.collectDepartment(departments), CollectDepartmentVO.class);
            return ActResult.initialize(incomeCostAnalysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取部门
     *
     * @des 获取部门集合
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department() throws ActException {
        try {
            List<String> departmentsList = incomeCostAnalysisAPI.getDepartment();
            return ActResult.initialize(departmentsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}