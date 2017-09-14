package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.EmployeePromotedAPI;
import com.bjike.goddess.managepromotion.bo.EmployeePromotedBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.to.*;
import com.bjike.goddess.managepromotion.vo.CollectVO;
import com.bjike.goddess.managepromotion.vo.EmployeePromotedVO;
import com.bjike.goddess.managepromotion.vo.SkillPromotionApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工已晋升情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("employeepromoted")
public class EmployeePromotedAction {

    @Autowired
    private EmployeePromotedAPI  employeePromotedAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = employeePromotedAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 员工已晋升情况列表总条数
     *
     * @param employeePromotedDTO 员工已晋升情况记录dto
     * @des 获取所有员工已晋升情况
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EmployeePromotedDTO employeePromotedDTO) throws ActException {
        try {
            Long count = employeePromotedAPI.countEmployeePromoted(employeePromotedDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个员工已晋升情况
     *
     * @param id
     * @return class EmployeePromotedVO
     * @des 获取一个员工已晋升情况
     * @version v1
     */
    @GetMapping("v1/promot/{id}")
    public Result promot(@PathVariable String id) throws ActException {
        try {
            EmployeePromotedBO employeePromotedBO = employeePromotedAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(employeePromotedBO, EmployeePromotedVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 员工已晋升情况列表
     *
     * @param employeePromotedDTO 员工已晋升情况记录dto
     * @return class EmployeePromotedVO
     * @des 获取所有员工已晋升情况
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmployeePromotedDTO employeePromotedDTO, HttpServletRequest request) throws ActException {
        try {
            List<EmployeePromotedVO> employeePromotedVOS = BeanTransform.copyProperties(
                    employeePromotedAPI.findListEmployeePromoted(employeePromotedDTO), EmployeePromotedVO.class, request);
            return ActResult.initialize(employeePromotedVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况to
     * @return class EmployeePromotedVO
     * @des 添加员工已晋升情况
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) EmployeePromotedTO employeePromotedTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeePromotedBO employeePromotedBO = employeePromotedAPI.insertEmployeePromoted(employeePromotedTO);
            return ActResult.initialize(employeePromotedBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况数据to
     * @return class EmployeePromotedVO
     * @des 添加员工已晋升情况
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EmployeePromotedTO employeePromotedTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeePromotedBO employeePromotedBO = employeePromotedAPI.editEmployeePromoted(employeePromotedTO);
            return ActResult.initialize(employeePromotedBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工已晋升情况
     *
     * @param id 用户id
     * @des 根据用户id删除员工已晋升情况
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            employeePromotedAPI.removeEmployeePromoted(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 姓名汇总
     *
     * @param to 汇总
     * @return class CollectVO
     * @des 姓名汇总
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name(@Validated(NameTO.collect.class) NameTO to,BindingResult bindingResult,HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to,CollectTO.class);
            List<CollectVO> collectVOS = BeanTransform.copyProperties(
                    employeePromotedAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 时间汇总
     *
     * @param to 汇总
     * @return class CollectVO
     * @des 时间汇总
     * @version v1
     */
    @GetMapping("v1/times")
    public Result times(@Validated(TimesTO.collect.class) TimesTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to,CollectTO.class);
            List<CollectVO> collectVOS = BeanTransform.copyProperties(
                    employeePromotedAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 状态汇总
     *
     * @param to 汇总
     * @return class CollectVO
     * @des 状态汇总
     * @version v1
     */
    @GetMapping("v1/status")
    public Result status(@Validated(StatusTO.collect.class) StatusTO to,BindingResult bindingResult,HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to,CollectTO.class);
            List<CollectVO> collectVOS = BeanTransform.copyProperties(
                    employeePromotedAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有姓名
     *
     * @des 获取所有姓名
     * @version v1
     */
    @GetMapping("v1/getNames")
    public Result getNames() throws ActException {
        try {
            List<String> nameList = employeePromotedAPI.getName();
            return ActResult.initialize(nameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有状态
     *
     * @des 获取所有状态
     * @version v1
     */
    @GetMapping("v1/getStatus")
    public Result getStatus() throws ActException {
        try {
            List<String> statusList = employeePromotedAPI.getStatus();
            return ActResult.initialize(statusList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}