package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.DebtAPI;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.bo.DetailBO;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.DebtVO;
import com.bjike.goddess.reportmanagement.vo.DetailVO;
import com.bjike.goddess.reportmanagement.vo.FormulaVO;
import com.bjike.goddess.reportmanagement.vo.StructureVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 负债表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("debt")
public class DebtAct {
    @Autowired
    private DebtAPI debtAPI;
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

            Boolean isHasPermission = debtAPI.guidePermission(guidePermissionTO);
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
     * @param dto 负债数据传输
     * @return class DebtVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(DebtDTO.A.class) DebtDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DebtBO> list = debtAPI.list(dto);
            List<DebtVO> vos = new ArrayList<>();
            for (DebtBO bo : list) {
                DebtVO vo = BeanTransform.copyProperties(bo, DebtVO.class, request);
                vo.setDebtId(bo.getId());
                vos.add(vo);
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑列表
     *
     * @param dto 负债数据传输
     * @return class DebtVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list1")
    public Result list1(DebtDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DebtBO> list = debtAPI.list1(dto);
            List<DebtVO> vos = new ArrayList<>();
            if (null != list) {
                for (DebtBO bo : list) {
                    DebtVO vo = BeanTransform.copyProperties(bo, DebtVO.class, request);
                    vo.setDebtId(bo.getId());
                    vos.add(vo);
                }
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 负债传输对象
     * @return class DebtVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DebtTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DebtBO bo = debtAPI.save(to);
            DebtVO vo = BeanTransform.copyProperties(bo, DebtVO.class, request);
            vo.setDebtId(bo.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看负债与权益结构分析
     *
     * @param dto 负债数据传输
     * @return class StructureVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/debtStructure")
    public Result debtStructure(@Validated(DebtDTO.A.class) DebtDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<StructureBO> list = debtAPI.debtStructure(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, StructureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看金额明细
     *
     * @param id  负债id
     * @param dto 负债数据传输
     * @return class DetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetails/{id}")
    public Result findDetails(@PathVariable String id, @Validated(DebtDTO.A.class) DebtDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DetailBO> list = debtAPI.findDetails(id, dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看对应的公式
     *
     * @param id  负债id
     * @param dto 负债数据传输
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/lookFormula/{id}")
    public Result lookFormula(@PathVariable String id, @Validated(DebtDTO.A.class) DebtDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
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
     * @param id 负债id
     * @return class DebtVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/debt/{id}")
    public Result debt(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DebtBO bo = debtAPI.findByID(id);
            DebtVO vo = BeanTransform.copyProperties(bo, DebtVO.class, request);
            vo.setDebtId(bo.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 负债传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DebtTO to, BindingResult result) throws ActException {
        try {
            debtAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 负债id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            debtAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 负债数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DebtDTO dto) throws ActException {
        try {
            return ActResult.initialize(debtAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}