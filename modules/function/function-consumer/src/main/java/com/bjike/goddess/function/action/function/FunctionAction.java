package com.bjike.goddess.function.action.function;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.function.api.FunctionAPI;
import com.bjike.goddess.function.bo.FunctionBO;
import com.bjike.goddess.function.enums.FunctionType;
import com.bjike.goddess.function.to.FunctionTO;
import com.bjike.goddess.function.vo.FunctionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模块功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("function")
public class FunctionAction {

    @Autowired
    private FunctionAPI functionAPI;

    /**
     * 添加功能
     *
     * @param functionTO 新功能
     * @return class FunctionVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) FunctionTO functionTO, BindingResult result) throws ActException {
        try {
            FunctionBO bo = functionAPI.add(functionTO);
            FunctionVO vo = BeanTransform.copyProperties(bo, FunctionVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑功能
     *
     * @param functionTO 功能
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) FunctionTO functionTO, BindingResult result) throws ActException {
        try {
            functionAPI.edit(functionTO);
            return new ActResult("edit function success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除功能
     *
     * @param id 功能
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result add(@PathVariable String id) throws ActException {
        try {
            functionAPI.delete(id);
            return new ActResult("delete function success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 功能列表
     *
     * @throws ActException
     * @return class FunctionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list() throws ActException {
        try {
            List<FunctionBO> functionBOS = functionAPI.list(null);
            List<FunctionVO> functionVOS = BeanTransform.copyProperties(functionBOS, FunctionVO.class);
            return ActResult.initialize(functionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过分类获取功能列表
     *
     * @throws ActException
     * @return class FunctionVO
     * @version v1
     */
    @GetMapping("v1/{type}/list")
    public Result list(@PathVariable FunctionType type) throws ActException {
        try {
            List<FunctionBO> functionBOS = functionAPI.list(type);
            List<FunctionVO> functionVOS = BeanTransform.copyProperties(functionBOS, FunctionVO.class);
            return ActResult.initialize(functionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户功能列表
     *
     * @throws ActException
     * @return class FunctionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/user/functions")
    public Result userFunctions() throws ActException {
        try {
            List<FunctionBO> functionBOS = functionAPI.userFunctions();
            List<FunctionVO> functionVOS = BeanTransform.copyProperties(functionBOS, FunctionVO.class);
            return ActResult.initialize(functionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}