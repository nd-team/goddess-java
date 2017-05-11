package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.businessproject.to.BaseInfoManageTO;
import com.bjike.goddess.businessproject.vo.BaseInfoManageVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 商务项目合同基本信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.350 ]
 * @Description: [ 商务项目合同基本信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("baseinfomanage")
public class BaseInfoManageAction {

    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;

    /**
     * 列表总条数
     *
     * @param baseInfoManageDTO 基本信息信息dto
     * @des 获取所有基本信息信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BaseInfoManageDTO baseInfoManageDTO) throws ActException {
        try {
            Long count = baseInfoManageAPI.countBaseInfoManage(baseInfoManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个基本信息
     *
     * @param id 项目基本信息信息id
     * @des 根据id获取项目基本信息信息
     * @return  class BaseInfoManageVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            BaseInfoManageVO projectCarryVO = BeanTransform.copyProperties(
                    baseInfoManageAPI.getOneById(id), BaseInfoManageVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目合同列表
     *
     * @param baseInfoManageDTO 项目合同信息dto
     * @return class BaseInfoManageVO
     * @des 获取所有项目合同信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<BaseInfoManageVO> baseInfoManageVOList = BeanTransform.copyProperties(
                    baseInfoManageAPI.listBaseInfoManage(baseInfoManageDTO), BaseInfoManageVO.class, request);
            return ActResult.initialize(baseInfoManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 添加项目合同
     *
     * @param baseInfoManageTO 项目合同基本信息数据to
     * @return class BaseInfoManageVO
     * @des 添加项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({BaseInfoManageTO.TestAdd.class}) BaseInfoManageTO baseInfoManageTO) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.addBaseInfoManage(baseInfoManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目合同
     *
     * @param baseInfoManageTO 项目合同基本信息数据bo
     * @return class BaseInfoManageVO
     * @des 添加项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({BaseInfoManageTO.TestEdit.class}) BaseInfoManageTO baseInfoManageTO) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.editBaseInfoManage(baseInfoManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目合同信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            baseInfoManageAPI.deleteBaseInfoManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目合同
     *
     * @param innerProjectNum 内部项目编号
     * @return class BaseInfoManageVO
     * @des 根据内部项目编号获取项目合同
     * @version v1
     */
    @GetMapping("v1/getByInnerProjectNum")
    public Result getBaseInfoManage(@NotBlank String innerProjectNum) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.getInfoByInnerProjectNum(innerProjectNum);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}