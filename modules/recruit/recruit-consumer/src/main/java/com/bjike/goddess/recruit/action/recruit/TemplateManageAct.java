package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.TemplateManageAPI;
import com.bjike.goddess.recruit.bo.TemplateManageBO;
import com.bjike.goddess.recruit.dto.TemplateManageDTO;
import com.bjike.goddess.recruit.to.TemplateManageTO;
import com.bjike.goddess.recruit.vo.TemplateManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 模板管理
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("templateManage")
public class TemplateManageAct {

    @Autowired
    private TemplateManageAPI templateManageAPI;

    /**
     * 根据id查询模板管理
     *
     * @param id 模板管理唯一标识
     * @return class TemplateManageVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateManage/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TemplateManageBO bo = templateManageAPI.findById(id);
            TemplateManageVO vo = BeanTransform.copyProperties(bo, TemplateManageVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 模板管理dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TemplateManageDTO dto, BindingResult result) throws ActException {
        try {
            Long count = templateManageAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 模板管理传输对象
     * @return class TemplateManageVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TemplateManageDTO dto) throws ActException {
        try {
            List<TemplateManageBO> boList = templateManageAPI.list(dto);
            List<TemplateManageVO> voList = BeanTransform.copyProperties(boList, TemplateManageVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加模板管理
     *
     * @param to 模板管理to信息
     * @return class TemplateManageVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TemplateManageTO to, BindingResult result) throws ActException {
        try {
            TemplateManageBO bo = templateManageAPI.save(to);
            TemplateManageVO vo = BeanTransform.copyProperties(bo, TemplateManageVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除模板管理
     *
     * @param id 模板管理唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            templateManageAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 模板管理
     *
     * @param to 模板管理to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TemplateManageTO to, BindingResult result) throws ActException {
        try {
            templateManageAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
