package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.to.UserTO;
import com.bjike.goddess.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 获取所有用户
     *
     * @des 获取所有用户
     * @version v1
     */
    @GetMapping(value = "v1/userInfo")
    public Result getAllUser() throws ActException {

        try {
            UserDTO userDTO = new UserDTO();
            List<UserBO> user = userAPI.findUserByPage(userDTO);
            List<UserVO> userVOS = BeanTransform.copyProperties(user, UserVO.class);
            return ActResult.initialize(userVOS);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加用户
     *
     * @param userTO 用户数据
     * @des 添加用户和用户相关的信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addUsers(UserTO userTO) throws ActException {
        try {
            UserBO userBO = userAPI.add(null, userTO);
            UserVO userVO = BeanTransform.copyProperties(userBO, UserVO.class);
            return ActResult.initialize(userVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        //TODO:  tanghaixiang 2017-03-09 未做用户注册
    }


    /**
     * 修改用户
     *
     * @param id 用户id
     * @des 根据用户id修改用户
     * @version v1
     */
    @PutMapping("v1/edit/{id}")
    public Result edit(@PathVariable String id) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做修改用户 记得抛异常
        if (StringUtils.isBlank(id)) {
            throw new ActException("id不能为空");
        }
//        try {
//            UserBO userBO = userAPI.updateUser(id);
//            UserVO userVO = BeanTransform.copyProperties(userBO, UserVO.class);
            UserVO userVO = null;
            return ActResult.initialize(userVO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
    }


    /**
     * 删除用户
     *
     * @param id 用户id
     * @des 根据用户id删除用户
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            userAPI.deleteUser(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻用户
     *
     * @param id 用户id
     * @des 根据用户id解冻用户
     * @version v1
     */
    @PutMapping("v1/thow")
    public Result thow(@RequestParam String id) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做解冻用户 记得抛异常
        try {
            userAPI.thowUser(id);
            return new ActResult("thow success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结用户
     *
     * @param id 用户id
     * @des 根据用户id冻结用户
     * @version v1
     */
    @PutMapping("v1/concle")
    public Result concle(@RequestParam String id) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做冻结用户 记得抛异常
        try {
            userAPI.congelUser(id);
            return new ActResult("concle success!");
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
    @PutMapping("v1/sendAccountToEmplore")
    public Result sendAccountToEmp(@RequestParam String id, @RequestParam String emailAccount) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做邮件告知员工账号密码 记得抛异常
        return new ActResult("send success!");
    }


}
