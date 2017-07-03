package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.FormulaAPI;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.to.FormulaTO;
import com.bjike.goddess.reportmanagement.vo.FormulaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 对应的公式
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("formula")
public class FormulaAct {
    @Autowired
    private FormulaAPI formulaAPI;

    /**
     * 添加公式
     *
     * @param to 对应的公式传输对象
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FormulaTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FormulaBO bo = formulaAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FormulaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 加公式科目
     *
     * @param to 对应的公式传输对象
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/addProject")
    public Result addProject(@Validated(FormulaTO.A.class) FormulaTO to, BindingResult result, HttpServletRequest request) throws ActException {
        String id = (String) request.getSession().getAttribute("id");
        if (id == null) {
            throw new ActException("添加公式的科目不明确，请重新选择添加公式的科目");
        }
        try {
            to.setForeignId(id);
            FormulaBO bo = formulaAPI.add(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FormulaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 减公式科目
     *
     * @param to 对应的公式传输对象
     * @return class FormulaVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/removeProject")
    public Result removeProject(@Validated(FormulaTO.A.class) FormulaTO to, BindingResult result, HttpServletRequest request) throws ActException {
        String id = (String) request.getSession().getAttribute("id");
        if (id == null) {
            throw new ActException("添加公式的科目不明确，请重新选择添加公式的科目");
        }
        try {
            to.setForeignId(id);
            FormulaBO bo = formulaAPI.remove(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FormulaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除科目
     *
     * @param id 对应的公式id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            formulaAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}