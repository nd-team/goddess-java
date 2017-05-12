package com.bjike.goddess.projectmarketfee.action.projectmarketfee;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.api.CostAnalysisAPI;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;
import com.bjike.goddess.projectmarketfee.vo.CostAnalysisCoutnVO;
import com.bjike.goddess.projectmarketfee.vo.CostAnalysisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 费用效益分析
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("costanalysis")
public class CostAnalysisAct {
    @Autowired
    private CostAnalysisAPI costAnalysisAPI;

    /**
     * 查找
     *
     * @param dto     费用效益分析分页信息
     * @param request 请求对象
     * @return class CostAnalysisVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CostAnalysisDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CostAnalysisBO> list = costAnalysisAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CostAnalysisVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to      费用效益分析信息
     * @param request 请求对象
     * @return class CostAnalysisVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) CostAnalysisTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CostAnalysisBO bo = costAnalysisAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CostAnalysisVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 费用效益分析信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CostAnalysisTO to, BindingResult result) throws ActException {
        try {
            costAnalysisAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 费用效益分析id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            costAnalysisAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param year    年份
     * @param month   月份
     * @param request 请求对象
     * @return class CostAnalysisCoutnVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/arrivalCount/{year}/{month}")
    public Result arrivalCount(@PathVariable Integer year, @PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<CostAnalysisCountBO> list = costAnalysisAPI.arrivalCount(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, CostAnalysisCoutnVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param year    年份
     * @param month   月份
     * @param request 请求对象
     * @return class CostAnalysisCoutnVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroupCount/{year}/{month}")
    public Result projectGroupCount(@PathVariable Integer year, @PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<CostAnalysisCountBO> list = costAnalysisAPI.projectGroupCount(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, CostAnalysisCoutnVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目名称汇总
     *
     * @param year    年份
     * @param month   月份
     * @param request 请求对象
     * @return class CostAnalysisCoutnVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectNameCount/{year}/{month}")
    public Result projectNameCount(@PathVariable Integer year, @PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<CostAnalysisCountBO> list = costAnalysisAPI.projectNameCount(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, CostAnalysisCoutnVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找汇总信息对应的明细
     *
     * @param arrival      地区
     * @param projectGroup 项目组
     * @param projectName  项目名称
     * @param request      请求对象
     * @return class CostAnalysisVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail/{arrival}/{projectGroup}/{projectName}")
    public Result findDetail(@PathVariable String arrival, @PathVariable String projectGroup, @PathVariable String projectName, HttpServletRequest request) throws ActException {
        try {
            List<CostAnalysisBO> list = costAnalysisAPI.findDetail(arrival, projectGroup, projectName);
            return ActResult.initialize(BeanTransform.copyProperties(list, CostAnalysisVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}