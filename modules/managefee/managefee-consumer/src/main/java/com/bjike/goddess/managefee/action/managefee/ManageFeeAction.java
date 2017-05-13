package com.bjike.goddess.managefee.action.managefee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managefee.api.ManageFeeAPI;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.to.ManageFeeTO;
import com.bjike.goddess.managefee.vo.ManageFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 管理费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("managefee")
public class ManageFeeAction {

    @Autowired
    private ManageFeeAPI manageFeeAPI;

    /**
     * 列表总条数
     *
     * @param manageFeeDTO 管理费信息dto
     * @des 获取所有管理费信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            Long count = manageFeeAPI.countManageFee(manageFeeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个管理费
     *
     * @param id 管理费信息id
     * @return class ManageFeeVO
     * @des 根据id获取所有管理费信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ManageFeeVO manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.getOneById(id), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理费列表
     *
     * @param manageFeeDTO 管理费信息dto
     * @param request      前端过滤参数
     * @return class ManageFeeVO
     * @des 获取所有管理费信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListManageFee(ManageFeeDTO manageFeeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.listManageFee(manageFeeDTO), ManageFeeVO.class, request);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加管理费
     *
     * @param manageFeeTO 管理费基本信息数据to
     * @return class ManageFeeVO
     * @des 添加管理费
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addManageFee(@Validated(ManageFeeTO.TestAdd.class) ManageFeeTO manageFeeTO, BindingResult bindingResult) throws ActException {
        try {
            ManageFeeBO manageFeeBO1 = manageFeeAPI.addManageFee(manageFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(manageFeeBO1, ManageFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑管理费
     *
     * @param manageFeeTO 管理费基本信息数据bo
     * @return class ManageFeeVO
     * @des 编辑管理费
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editManageFee(@Validated(ManageFeeTO.TestEdit.class) ManageFeeTO manageFeeTO) throws ActException {
        try {
            ManageFeeBO manageFeeBO1 = manageFeeAPI.editManageFee(manageFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(manageFeeBO1, ManageFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除管理费信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteManageFee(@PathVariable String id) throws ActException {
        try {
            manageFeeAPI.deleteManageFee(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 根据地区汇总
     *
     * @param manageFeeDTO 管理费信息dto
     * @return class ManageFeeVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/ctArea")
    public Result collectCom(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectArea(manageFeeDTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目组汇总
     *
     * @param manageFeeDTO 管理费信息dto
     * @return class ManageFeeVO
     * @des 根据项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctGroup")
    public Result ctGroup(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectGroup(manageFeeDTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总
     *
     * @param manageFeeDTO 管理费信息dto
     * @return class ManageFeeVO
     * @des 根据项目汇总
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result collectPro(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectProject(manageFeeDTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据类别汇总
     *
     * @param manageFeeDTO 管理费信息dto
     * @return class ManageFeeVO
     * @des 根据类别汇总
     * @version v1
     */
    @GetMapping("v1/ctType")
    public Result ctType(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectType(manageFeeDTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有年份
     *
     * @version v1
     */
    @GetMapping("v1/listYear")
    public Result yearList() throws ActException {
        try {
            List<String> list = manageFeeAPI.yearList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result areaList() throws ActException {
        try {
            List<String> list = manageFeeAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目组
     *
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result groupList() throws ActException {
        try {
            List<String> list = manageFeeAPI.groupList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目
     *
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result projectList() throws ActException {
        try {
            List<String> list = manageFeeAPI.projectList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}