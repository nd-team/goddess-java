package com.bjike.goddess.incomecheck.action.incomecheck;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.incomecheck.api.CheckIndexAPI;
import com.bjike.goddess.incomecheck.bo.CheckIndexBO;
import com.bjike.goddess.incomecheck.dto.CheckIndexDTO;
import com.bjike.goddess.incomecheck.to.CheckIndexTO;
import com.bjike.goddess.incomecheck.vo.CheckIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("checkindex")
public class CheckIndexAction {

    @Autowired
    private CheckIndexAPI checkIndexAPI;

    /**
     * 列表总条数
     *
     * @param checkIndexDTO 指标设置 信息dto
     * @des 获取所有指标设置 信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CheckIndexDTO checkIndexDTO) throws ActException {
        try {
            Long count = checkIndexAPI.countCheckIndex(checkIndexDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个指标设置 
     *
     * @param id 指标设置 信息id
     * @return class CheckIndexVO
     * @des 根据id获取所有指标设置 信息
     * @version v1
     */
    @GetMapping("v1/getIndexById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            List<CheckIndexVO> checkIndexVOList = BeanTransform.copyProperties(
                    checkIndexAPI.getOneById(id), CheckIndexVO.class);
            return ActResult.initialize(checkIndexVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 指标设置 列表
     *
     * @param checkIndexDTO 指标设置 信息dto
     * @param request      前端过滤参数
     * @return class CheckIndexVO
     * @des 获取所有指标设置 信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCheckIndex(CheckIndexDTO checkIndexDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CheckIndexVO> checkIndexVOList = BeanTransform.copyProperties(
                    checkIndexAPI.listCheckIndex(checkIndexDTO), CheckIndexVO.class, request);
            return ActResult.initialize(checkIndexVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加指标设置 
     *
     * @param checkIndexTO 指标设置 基本信息数据to
     * @return class CheckIndexVO
     * @des 添加指标设置 
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCheckIndex(@Validated CheckIndexTO checkIndexTO, BindingResult bindingResult) throws ActException {
        try {
            CheckIndexBO checkIndexBO1 = checkIndexAPI.addCheckIndex(checkIndexTO);
            return ActResult.initialize(BeanTransform.copyProperties(checkIndexBO1, CheckIndexVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑指标设置 
     *
     * @param checkIndexTO 指标设置 基本信息数据bo
     * @return class CheckIndexVO
     * @des 编辑指标设置 
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCheckIndex(@Validated CheckIndexTO checkIndexTO) throws ActException {
        try {
            CheckIndexBO checkIndexBO1 = checkIndexAPI.editCheckIndex(checkIndexTO);
            return ActResult.initialize(BeanTransform.copyProperties(checkIndexBO1, CheckIndexVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除指标设置 信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCheckIndex(@PathVariable String id) throws ActException {
        try {
            checkIndexAPI.deleteCheckIndex(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    
}