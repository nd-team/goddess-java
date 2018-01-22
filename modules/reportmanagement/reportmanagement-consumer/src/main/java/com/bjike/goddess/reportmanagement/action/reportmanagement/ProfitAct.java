package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.api.ProfitAPI;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitFormulaDTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitFormulaTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import com.bjike.goddess.reportmanagement.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class ProfitAct extends BaseFileAction{
    @Autowired
    private ProfitAPI profitAPI;
    @Autowired
    private FormulaAPI formulaAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = formulaAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

    /**
     * 利润增减率分析列表
     *
     * @return class ProfitFormulaVO
     * @version v1
     */
    @GetMapping("v1/formula/list")
    public Result formulaList(ProfitFormulaDTO profitFormulaDTO) throws ActException {
        try {
            List<ProfitFormulaBO> bos = profitAPI.decreaseRatioList(profitFormulaDTO);
            return ActResult.initialize(BeanTransform.copyProperties(bos, ProfitFormulaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询利润增减率分析总条数
     *
     * @version v1
     */
    @GetMapping("v1/formula/total")
    public Result getFormulaTotal(ProfitFormulaDTO dto) throws ActException {
        try {
            return ActResult.initialize(profitAPI.getFormulaTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 利润增减率分析添加
     *
     * @param to
     * @version v1
     */
    @PostMapping("v1/add/formula")
    public Result addFormula(@Validated(ADD.class) ProfitFormulaTO to, BindingResult bindingResult) throws ActException {
        try {
            profitAPI.addFormula(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 利润增减率分析编辑
     *
     * @param to
     * @version v1
     */
    @PutMapping("v1/edit/formula/{id}")
    public Result editFormula(@Validated(EDIT.class) ProfitFormulaTO to, BindingResult bindingResult) throws ActException {
        try {
            profitAPI.editFormula(to);
            return ActResult.initialize("EDIT SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 利润增减率分析删除
     *
     * @param id
     * @version v1
     */
    @DeleteMapping("v1/delete/formula/{id}")
    public Result deleteFormula(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            profitAPI.deleteFormula(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取利润增减率分析
     *
     * @version v1
     */
    @GetMapping("v1/find/formula/{id}")
    public Result findFormulaByID(@PathVariable String id) throws ActException {
        try {
            ProfitFormulaBO profitFormulaBO = profitAPI.findFormulaByID(id);
            return ActResult.initialize(profitFormulaBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 变动情况分析列表
     *
     * @return class ProfitFormulaVO
     * @version v1
     */
    @GetMapping("v1/analysisChanges/list")
    public Result analysisChangesList(ProfitFormulaDTO dto) throws ActException {
        try {
            List<ProfitFormulaBO> bos = profitAPI.analysisChangesList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, ProfitFormulaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 变动情况分析列表添加
     *
     * @version v1
     */
    @PostMapping("v1/analysisChanges/add")
    public Result analysisChangesAdd(@Validated(ADD.class) ProfitFormulaTO to, BindingResult bindingResult) throws ActException {
        try {
            profitAPI.analysisChangesAdd(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 变动情况分析列表总条数
     *
     * @version v1
     */
    @GetMapping("v1/analysisChanges/total")
    public Result analysisChangesTotal(ProfitFormulaDTO dto) throws ActException {
        try {
            return ActResult.initialize(profitAPI.analysisChangesTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出excel
     *
     * @param dto 利润表
     * @des 导出利润表
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ProfitDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "利润表.xlsx";
            super.writeOutFile(response, profitAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}