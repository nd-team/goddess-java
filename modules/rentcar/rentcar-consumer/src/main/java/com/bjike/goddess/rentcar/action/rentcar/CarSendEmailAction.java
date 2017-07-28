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
import com.bjike.goddess.rentcar.entity.CarSendEmail;
import com.bjike.goddess.rentcar.service.CarSendEmailSer;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import com.bjike.goddess.rentcar.vo.CarSendEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发送邮件
 * @Author:			[ jiangzaixuan ]
 * @Date:			[  2017-07-25 09:50 ]
 * @Description:	[ 发送邮件 ]
 * @Version:		[ v1.0.0 ]
 * @Copy:   		[ com.bjike ]
 */
@RestController
@RequestMapping("carsendemail")
public class CarSendEmailAction {

    @Autowired
    private CarSendEmailAPI carSendEmailAPI;
    /**
     * 新增邮件发送对象
     * @param to　　邮件发送对象
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
            CarSendEmailVO vo = BeanTransform.copyProperties(carSendEmailAPI.edit(to),CarSendEmail.class,request);
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
     * @param id
     * @return PositionDetailVO
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
     * @return CarSendEmailVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list() throws ActException{
        try {
            List<CarSendEmailVO> vo = BeanTransform.copyProperties(carSendEmailAPI.list(),CarSendEmailVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件
     * @version v1
     */
    public Result sendSemail() throws ActException{
        try {
            carSendEmailAPI.sendEmail();
            return new ActResult();
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}