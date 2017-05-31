package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.DepartMonIndexSetAPI;
import com.bjike.goddess.balancecard.bo.DepartMonIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.to.DepartMonIndexSetTO;
import com.bjike.goddess.balancecard.vo.DepartMonIndexSetVO;
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
import javax.validation.Valid;
import java.util.List;

/**
 * 部门月度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("departmonindexset")
public class DepartMonIndexSetAction {


    @Autowired
    private DepartMonIndexSetAPI departMonIndexSetAPI;

    /**
     *  列表总条数
     *
     * @param departMonIndexSetDTO  部门月度指标信息dto
     * @des 获取所有部门月度指标信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DepartMonIndexSetDTO departMonIndexSetDTO) throws ActException {
        try {
            Long count = departMonIndexSetAPI.countDepartMonIndexSet(departMonIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门月度指标列表
     *
     * @param departMonIndexSetDTO 部门月度指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有部门月度指标信息
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DepartMonIndexSetVO> departMonIndexSetVOList = BeanTransform.copyProperties(
                    departMonIndexSetAPI.listDepartMonIndexSet(departMonIndexSetDTO), DepartMonIndexSetVO.class, request);
            return ActResult.initialize(departMonIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个部门月度指标
     *
     * @param id 部门月度指标信息id
     * @des 获取所有部门月度指标信息
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            DepartMonIndexSetVO departMonIndexSetVOList = BeanTransform.copyProperties(
                    departMonIndexSetAPI.getOneById( id), DepartMonIndexSetVO.class);
            return ActResult.initialize(departMonIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加部门月度指标
     *
     * @param departMonIndexSetTO 部门月度指标基本信息数据to
     * @des 添加部门月度指标
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDepartMonIndexSet(@Validated(DepartMonIndexSetTO.TestAdd.class) DepartMonIndexSetTO departMonIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            DepartMonIndexSetBO departMonIndexSetBO1 = departMonIndexSetAPI.addDepartMonIndexSet(departMonIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departMonIndexSetBO1,DepartMonIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑部门月度指标
     *
     * @param departMonIndexSetTO 部门月度指标基本信息数据bo
     * @des 编辑部门月度指标
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editDepartMonIndexSet(@Validated(DepartMonIndexSetTO.TestAdd.class) DepartMonIndexSetTO departMonIndexSetTO) throws ActException {
        try {
            DepartMonIndexSetBO departMonIndexSetBO1 = departMonIndexSetAPI.editDepartMonIndexSet(departMonIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departMonIndexSetBO1,DepartMonIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除部门月度指标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDepartMonIndexSet(@PathVariable String id) throws ActException {
        try {
            departMonIndexSetAPI.deleteDepartMonIndexSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 分解岗位指标分解
     *
     * @param departMonIndexSetTO 年指标分解部门年指标数据to
     * @des 添加部门月度指标
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/seperateYear")
    public Result seperateDepartYear(@Validated(DepartMonIndexSetTO.TestSer.class) DepartMonIndexSetTO departMonIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            DepartMonIndexSetBO departMonIndexSetBO1 = departMonIndexSetAPI.seperateDepartYear(departMonIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departMonIndexSetBO1,DepartMonIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     *  查看本月列表总条数
     *
     * @param departMonIndexSetDTO  部门月度指标信息dto
     * @des 获取本月所有部门月度指标信息总条数
     * @version v1
     */
    @GetMapping("v1/countNow")
    public Result countNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws ActException {
        try {
            Long count = departMonIndexSetAPI.countNow(departMonIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看本月部门月度指标列表
     *
     * @param departMonIndexSetDTO 部门月度指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有部门月度指标信息
     * @return  class DepartMonIndexSetVO
     * @version v1
     */
    @GetMapping("v1/listNow")
    public Result listNow(DepartMonIndexSetDTO departMonIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DepartMonIndexSetVO> departMonIndexSetVOList = BeanTransform.copyProperties(
                    departMonIndexSetAPI.listNow(departMonIndexSetDTO), DepartMonIndexSetVO.class, request);
            return ActResult.initialize(departMonIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}