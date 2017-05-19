package com.bjike.goddess.business.action.business;

import com.bjike.goddess.business.api.BusinessRegisterAPI;
import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.vo.BusinessRegisterVO;
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
 * 工商注册
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessregister")
public class BusinessRegisterAction {
    @Autowired
    private BusinessRegisterAPI businessRegisterAPI;
    /**
     * 工商注册列表总条数
     *
     * @param businessRegisterDTO 工商注册dto
     * @des 获取所有工商注册总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessRegisterDTO businessRegisterDTO) throws ActException {
        try {
            Long count = businessRegisterAPI.countBusinessRegister(businessRegisterDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个工商注册
     *
     * @param id
     * @return class BusinessRegisterVO
     * @des 获取一个工商注册
     * @version v1
     */
    @GetMapping("v1/register/{id}")
    public Result register(@PathVariable String id) throws ActException {
        try {
            BusinessRegisterBO businessRegisterBO = businessRegisterAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(businessRegisterBO, BusinessRegisterVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工商注册列表
     *
     * @param businessRegisterDTO 工商注册dto
     * @return class BusinessRegisterVO
     * @des 获取所有工商注册
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessRegisterDTO businessRegisterDTO, HttpServletRequest request) throws ActException {
        try {
            List<BusinessRegisterVO> businessRegisterVOS = BeanTransform.copyProperties
                    (businessRegisterAPI.findListBusinessRegister(businessRegisterDTO),BusinessRegisterVO.class,request);
            return ActResult.initialize(businessRegisterVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterVO
     * @des 添加工商注册
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BusinessRegisterTO businessRegisterTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessRegisterBO businessRegisterBO = businessRegisterAPI.insertBusinessRegister(businessRegisterTO);
            return ActResult.initialize(businessRegisterBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterVO
     * @des 编辑工商注册
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BusinessRegisterTO businessRegisterTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessRegisterBO businessRegisterBO = businessRegisterAPI.editBusinessRegister(businessRegisterTO);
            return ActResult.initialize(businessRegisterBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工商注册
     *
     * @param id 用户id
     * @des 根据用户id删除工商注册记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessRegisterAPI.removeBusinessRegister(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            businessRegisterAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            businessRegisterAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}