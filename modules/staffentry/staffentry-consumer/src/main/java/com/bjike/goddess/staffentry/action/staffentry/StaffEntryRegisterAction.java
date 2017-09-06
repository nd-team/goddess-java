package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterEmailTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;
import com.bjike.goddess.staffentry.vo.StaffEntryRegisterVO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工入职用户注册
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 17:00]
 * @Description: [员工入职用户注册]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffentryregister")
public class StaffEntryRegisterAction {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private StaffEntryRegisterAPI staffEntryRegisterAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = staffEntryRegisterAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = staffEntryRegisterAPI.guidePermission(guidePermissionTO);
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
     * 注册用户列表总条数
     *
     * @param staffEntryRegisterDTO 注册用户信息dto
     * @des 获取所有注册用户信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffEntryRegisterDTO staffEntryRegisterDTO) throws ActException {
        try {
            Long count = staffEntryRegisterAPI.countStaffEntryRegister(staffEntryRegisterDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个注册用户
     *
     * @param id 注册用户信息id
     * @return class StaffEntryRegisterVO
     * @des 根据id查询注册用户
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            StaffEntryRegisterVO staffEntryRegisterVOList = BeanTransform.copyProperties(
                    staffEntryRegisterAPI.getOne(id), StaffEntryRegisterVO.class);
            return ActResult.initialize(staffEntryRegisterVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param staffEntryRegisterDTO 入职注册dto数据
     * @return class StaffEntryRegisterVO
     * @des 获取所有用户
     * @version v1
     */
    @GetMapping(value = "v1/list")
    public Result getAllUser(StaffEntryRegisterDTO staffEntryRegisterDTO) throws ActException {

        try {
            List<StaffEntryRegisterBO> listBO = staffEntryRegisterAPI.listStaffEntryRegister(staffEntryRegisterDTO);
            List<StaffEntryRegisterVO> list = BeanTransform.copyProperties(listBO, StaffEntryRegisterVO.class);
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加注册用户
     *
     * @param staffEntryRegisterTO 用户数据
     * @return class StaffEntryRegisterVO
     * @des 添加用户和用户相关的信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addUsers(@Validated(StaffEntryRegisterTO.TestAdd.class) StaffEntryRegisterTO staffEntryRegisterTO) throws ActException {
        try {
            StaffEntryRegisterBO staffEntryRegisterBO = staffEntryRegisterAPI.addStaffEntryRegister(staffEntryRegisterTO);
            return ActResult.initialize(BeanTransform.copyProperties(staffEntryRegisterBO, StaffEntryRegisterVO.class));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 修改用户
     *
     * @param staffEntryRegisterTO 用户数据
     * @return class StaffEntryRegisterVO
     * @des 添加用户和用户相关的信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(StaffEntryRegisterTO.TestAdd.class) StaffEntryRegisterTO staffEntryRegisterTO) throws ActException {
        try {
            StaffEntryRegisterBO staffEntryRegisterBO = staffEntryRegisterAPI.editStaffEntryRegister(staffEntryRegisterTO);
            return ActResult.initialize(BeanTransform.copyProperties(staffEntryRegisterBO, StaffEntryRegisterVO.class));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 列表id
     * @des 根据列表id删除用户
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            staffEntryRegisterAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取注册的员工编号
     *
     * @des 获取注册的员工编号
     * @version v1
     */
    @GetMapping("v1/maxEmpNumber")
    public Result maxEmpNumber() throws ActException {
        try {
            String empNumber = staffEntryRegisterAPI.maxEmpNumber();
            return ActResult.initialize(empNumber);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 发送邮件账号密码告知
     *
     * @param staffEntryRegisterEmailTO 帐号密码告知
     * @des 发送邮件
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/send/accountToEmplore")
    public Result sendAccountToEmp(@Validated(StaffEntryRegisterEmailTO.TestAdd.class) StaffEntryRegisterEmailTO staffEntryRegisterEmailTO, BindingResult bindingResult) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做邮件告知员工账号密码 记得抛异常
        try {
            staffEntryRegisterAPI.sendAccountToEmp(staffEntryRegisterEmailTO);
            return new ActResult("send success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
