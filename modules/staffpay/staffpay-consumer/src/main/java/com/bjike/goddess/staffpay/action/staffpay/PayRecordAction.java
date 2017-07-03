package com.bjike.goddess.staffpay.action.staffpay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.api.PayRecordAPI;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.PayRecordDTO;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.vo.AreaCollectVO;
import com.bjike.goddess.staffpay.vo.DepartmentCollectVO;
import com.bjike.goddess.staffpay.vo.NameCollectVO;
import com.bjike.goddess.staffpay.vo.PayRecordVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 已付款记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:00 ]
 * @Description: [ 已付款记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("payrecord")
public class PayRecordAction {
    @Autowired
    private PayRecordAPI payRecordAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = payRecordAPI.guidePermission(guidePermissionTO);
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
     * 已付款记录列表总条数
     *
     * @param payRecordDTO 已付款记录dto
     * @des 获取所有已付款记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PayRecordDTO payRecordDTO) throws ActException {
        try {
            Long count = payRecordAPI.countPayRecord(payRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个已付款记录
     *
     * @param id
     * @return class PayRecordVO
     * @des 获取一个已付款记录
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            PayRecordBO payRecordBO = payRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(payRecordBO, PayRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已付款记录列表
     *
     * @param payRecordDTO 已付款记录dto
     * @return class PayRecordVO
     * @des 获取所有已付款记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PayRecordDTO payRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<PayRecordVO> payRecordVOS = BeanTransform.copyProperties(
                    payRecordAPI.findListPayRecord(payRecordDTO), PayRecordVO.class, request);
            return ActResult.initialize(payRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除已付款记录
     *
     * @param id 用户id
     * @des 根据用户id删除已付款记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            payRecordAPI.removePayRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 地区汇总
     *
     * @param areas 地区
     * @return class AreaCollectVO
     * @des 地区汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@RequestParam String[] areas) throws ActException {
        try {
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(
                    payRecordAPI.collectArea(areas),AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areasList = payRecordAPI.getAreas();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 部门汇总
     *
     * @param departments 部门
     * @return class AreaCollectVO
     * @des 部门汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectDepartment")
    public Result collectDepartment(@RequestParam String[] departments) throws ActException {
        try {
            List<DepartmentCollectVO> departmentCollectVOS = BeanTransform.copyProperties(
                    payRecordAPI.collectDepartment(departments),DepartmentCollectVO.class);
            return ActResult.initialize(departmentCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取部门
     *
     * @des 获取部门集合
     * @version v1
     */
    @GetMapping("v1/departments")
    public Result departments() throws ActException {
        try {
            List<String> areasList = payRecordAPI.getDepartments();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 个人汇总
     *
     * @param names 个人
     * @return class AreaCollectVO
     * @des 个人汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectName")
    public Result collectName(@RequestParam String[] names) throws ActException {
        try {
            List<NameCollectVO> nameCollectVOS = BeanTransform.copyProperties(
                    payRecordAPI.collectName(names),NameCollectVO.class);
            return ActResult.initialize(nameCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取姓名
     *
     * @des 获取姓名集合
     * @version v1
     */
    @GetMapping("v1/names")
    public Result names() throws ActException {
        try {
            List<String> areasList = payRecordAPI.getNames();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}