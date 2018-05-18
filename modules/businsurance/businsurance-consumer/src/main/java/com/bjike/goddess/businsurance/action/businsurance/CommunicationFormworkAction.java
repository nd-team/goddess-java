package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.CommunicationFormworkAPI;
import com.bjike.goddess.businsurance.bo.CommunicationFormworkBO;
import com.bjike.goddess.businsurance.dto.CommunicationFormworkDTO;
import com.bjike.goddess.businsurance.to.CommunicationFormworkTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.vo.CommunicationFormworkVO;
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
 * 各类交流沟通模块
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-28 05:36 ]
 * @Description: [ 各类交流沟通模块 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("communicationformwork")
public class CommunicationFormworkAction {
    @Autowired
    private CommunicationFormworkAPI communicationFormworkAPI;

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

            Boolean isHasPermission = communicationFormworkAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询各类交流沟通模块
     *
     * @param id 各类交流沟通模块唯一标识
     * @return class CommunicationFormworkVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/communicatetion/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CommunicationFormworkBO bo = communicationFormworkAPI.getOne(id);
            CommunicationFormworkVO vo = BeanTransform.copyProperties(bo, CommunicationFormworkVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 各类交流沟通模块dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated CommunicationFormworkDTO dto, BindingResult result) throws ActException {
        try {
            Long count = communicationFormworkAPI.countComm(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询各类交流沟通模块
     *
     * @param dto 各类交流沟通模块dto
     * @return class CommunicationFormworkVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated CommunicationFormworkDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CommunicationFormworkBO> boList = communicationFormworkAPI.list(dto);
            List<CommunicationFormworkVO> voList = BeanTransform.copyProperties(boList, CommunicationFormworkVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id删除各类交流沟通模块
     *
     * @param id 各类交流沟通模块唯一标识
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable(value = "id") String id) throws ActException {
        try {
            communicationFormworkAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加
     *
     * @param to to
     * @return class CommunicationFormworkBO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CommunicationFormworkTO to, BindingResult result) throws ActException {
        try {
            CommunicationFormworkBO bo = communicationFormworkAPI.save(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑各类交流沟通模块
     *
     * @param to 各类交流沟通模块to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) CommunicationFormworkTO to, BindingResult result) throws ActException {
        try {
            communicationFormworkAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}