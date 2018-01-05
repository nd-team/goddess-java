package com.bjike.goddess.rentcar.action.rentcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.rentcar.api.CarSendEmailAPI;
import com.bjike.goddess.rentcar.bo.CarSendEmailBO;
import com.bjike.goddess.rentcar.dto.CarSendEmailDTO;
import com.bjike.goddess.rentcar.entity.CarSendEmail;
import com.bjike.goddess.rentcar.service.CarSendEmailSer;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import com.bjike.goddess.rentcar.vo.CarSendEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发送邮件
 * @Author:	[ jiangzaixuan ]
 * @Date: [  2017-07-25 09:50 ]
 * @Description: [ 发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("carsendemail")
public class CarSendEmailAction {

    @Autowired
    private CarSendEmailAPI carSendEmailAPI;

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

            Boolean isHasPermission = carSendEmailAPI.guidePermission(guidePermissionTO);
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
     * 新增邮件发送对象
     * @param to 邮件发送对象
     * @return class CarSendEmailVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) CarSendEmailTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException{
        try{
            CarSendEmailVO carSendEmailVO = BeanTransform.copyProperties(carSendEmailAPI.add(to), CarSendEmailVO.class,request);
            return ActResult.initialize(carSendEmailVO);
        }catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 编辑
     * @param to 邮件发送对象
     * @return class CarSendEmailVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CarSendEmailTO to,BindingResult bindingResult,HttpServletRequest request) throws ActException{
        try {
            CarSendEmailVO vo = BeanTransform.copyProperties(carSendEmailAPI.edit(to),CarSendEmailVO.class,request);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException((e.getMessage()));
        }
    }

    /**
     * 查询出所有未冻结的部门
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/find/department")
    public Result findDepartMent() throws ActException{
        try {
            List<DepartmentDetailVO> vo = BeanTransform.copyProperties(carSendEmailAPI.findDepartMent(),DepartmentDetailVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门id查询出该部门下所有的岗位
     * @param id 部门id
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/find/position/{id}")
    public Result findPositon(@PathVariable String id,HttpServletRequest request) throws ActException{
        try {
            List<PositionDetailVO> vo = BeanTransform.copyProperties(carSendEmailAPI.findPosition(id),PositionDetailVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送对象列表
     * @return class CarSendEmailVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list() throws ActException{
        try {
            List<CarSendEmailBO> boList = carSendEmailAPI.list();
            List<CarSendEmailVO> vo = BeanTransform.copyProperties(boList,CarSendEmailVO.class,true);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件
     * @version v1
     */
    @GetMapping("v1/sendEmail")
    public Result sendEmail() throws ActException{
        try {
            carSendEmailAPI.sendEmail();
            return new ActResult("发送邮件成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件提醒功能
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/sendEmail/remind")
    public Result sendEmailRemind() throws ActException{
        try {
            carSendEmailAPI.sendEmailRemind();
            return new ActResult("发送邮件成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得总条数
     * @param dto 发送邮件对象
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CarSendEmailDTO dto) throws ActException{
        try {
            Long count = carSendEmailAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id来获取单条数据
     * @param id 发送对象id
     * @return class CarSendEmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            CarSendEmailBO bo = carSendEmailAPI.findOne(id);
            CarSendEmailVO vo = BeanTransform.copyProperties(bo,CarSendEmailVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            carSendEmailAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}