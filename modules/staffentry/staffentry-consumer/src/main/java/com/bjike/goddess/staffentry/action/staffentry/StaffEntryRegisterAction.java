package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.staffentry.vo.StaffEntryRegisterVO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * @des 根据id查询注册用户
     * @return class StaffEntryRegisterVO
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
     * 获取所有用户列表
     *
     * @param staffEntryRegisterDTO 入职注册dto数据
     * @des 获取所有用户
     * @return class StaffEntryRegisterVO
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
     * @des 添加用户和用户相关的信息
     * @return class StaffEntryRegisterVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addUsers(@Validated(StaffEntryRegisterTO.TestAdd.class) StaffEntryRegisterTO staffEntryRegisterTO) throws ActException {
        try {
            StaffEntryRegisterBO staffEntryRegisterBO = staffEntryRegisterAPI.addStaffEntryRegister( staffEntryRegisterTO);
            return ActResult.initialize(BeanTransform.copyProperties(staffEntryRegisterBO,StaffEntryRegisterVO.class));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 修改用户
     *
     * @param staffEntryRegisterTO 用户数据
     * @des 添加用户和用户相关的信息
     * @return class StaffEntryRegisterVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(StaffEntryRegisterTO.TestAdd.class) StaffEntryRegisterTO staffEntryRegisterTO) throws ActException {
        try {
            StaffEntryRegisterBO staffEntryRegisterBO = staffEntryRegisterAPI.editStaffEntryRegister( staffEntryRegisterTO);
            return ActResult.initialize(BeanTransform.copyProperties(staffEntryRegisterBO,StaffEntryRegisterVO.class));

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
     * 发送邮件
     *
     * @param id           用户id
     * @param emailAccount 用户入职注册个人邮箱
     * @des 发送邮件
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/sendAccountToEmplore")
    public Result sendAccountToEmp(@RequestParam String id, @RequestParam String emailAccount) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做邮件告知员工账号密码 记得抛异常
        return new ActResult("send success!");
    }


}
