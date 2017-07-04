package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.api.ProfitAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import com.bjike.goddess.reportmanagement.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 利润表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("profit")
public class ProfitAct {
    @Autowired
    private ProfitAPI profitAPI;
    @Autowired
    private FormulaAPI formulaAPI;

    /**
     * 列表
     *
     * @param dto 利润表数据传输
     * @return class ProfitVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ProfitBO> list = profitAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProfitVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 利润表传输对象
     * @return class ProfitVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ProfitTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProfitBO bo = profitAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProfitVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 水平分析
     *
     * @param dto 利润表数据传输
     * @return class ProfitLevelVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/levelAnalyze")
    public Result levelAnalyze(@Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ProfitLevelBO> list = profitAPI.levelAnalyze(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProfitLevelVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 垂直分析
     *
     * @param dto 利润表数据传输
     * @return class ProfitVerticalVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/verticalAnalyze")
    public Result verticalAnalyze(@Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ProfitVerticalBO> list = profitAPI.verticalAnalyze(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProfitVerticalVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分析指标
     *
     * @param dto 利润表数据传输
     * @return class ProfitAnalyzeIndicatorVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/analyzeIndicator")
    public Result analyzeIndicator(@Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ProfitAnalyzeIndicatorBO> list = profitAPI.analyzeIndicator(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProfitAnalyzeIndicatorVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看金额明细
     *
     * @param id  利润表id
     * @param dto 利润表数据传输
     * @return class DetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetails/{id}")
    public Result findDetails(@PathVariable String id, @Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DetailBO> list = profitAPI.findDetails(id, dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看对应的公式
     *
     * @param id  利润id
     * @param dto 利润数据传输
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/lookFormula/{id}")
    public Result lookFormula(@PathVariable String id, @Validated(ProfitDTO.A.class) ProfitDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(dto, formulaDTO);
        request.getSession().setAttribute("id", id);
        try {
            List<FormulaBO> list = formulaAPI.findByFid(id, formulaDTO);
            return ActResult.initialize(BeanTransform.copyProperties(list, FormulaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 利润表id
     * @return class ProfitVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/profit/{id}")
    public Result profit(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProfitBO bo = profitAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProfitVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 利润表传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProfitTO to, BindingResult result) throws ActException {
        try {
            profitAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 利润表id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            profitAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 利润表数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProfitDTO dto) throws ActException {
        try {
            return ActResult.initialize(profitAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}