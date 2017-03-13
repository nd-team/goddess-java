package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工入职用户注册
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 17:00]
 * @Description: [员工入职用户注册]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffentry/staffentryregister")
public class StaffEntryRegisterAction {

    @Autowired
    private UserAPI userAPI;

    /**
    * 添加用户 和用户相关的信息
    * @param userBO 用户数据
    * @return class user
    * @throws SerException
    */
    @PostMapping("v1/add")
    public ActResult addUsers(UserBO userBO) throws ActException {

        //TODO:  tanghaixiang 2017-03-09 未做用户注册
        return ActResult.initialize( null);
    }


    /**
    * 根据用户id删除用户
    * @param id 用户id
    * @return
    * @throws SerException
    */
    @DeleteMapping("v1/delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
            //TODO:  tanghaixiang 2017-03-09 未做删除用户 记得抛异常
            return new ActResult("delete success!");
    }


    /**
    * 获取所有用户
    * @param
    * @return class user
    * @throws SerException
    */
    @GetMapping(value = "v1/userInfo" )
    public ActResult getAllUser( ) throws ActException{

        try {
            //TODO:  tanghaixiang 2017-03-09 未做获取用户信息 记得抛异常
//            List<UserVO> userVOS = BeanTransform.copyProperties( userAPI.findAllGoods() , UserVO.class);
            List<UserBO> user =  userAPI.list();
            List<UserVO> userVOS = new ArrayList<>();
            return ActResult.initialize(userVOS);
        } catch (Exception e) {
            throw  new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id修改用户
     * @param id 用户id
     * @return
     * @throws SerException
     */
    @PutMapping("v1/edit/{id}")
    public ActResult edit(@PathVariable String id ) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做修改用户 记得抛异常
        return new ActResult("edit success!");
    }

    /**
     * 根据用户id解冻用户
     * @param id 用户id
     * @return
     * @throws SerException
     */
    @PutMapping("v1/thow")
    public ActResult thow(@RequestParam String id) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做解冻用户 记得抛异常
        return new ActResult("thow success!");
    }

    /**
     * 根据用户id冻结用户
     * @param id 用户id
     * @return
     * @throws SerException
     */
    @PutMapping("v1/concle")
    public ActResult concle(@RequestParam String id) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做冻结用户 记得抛异常
        return new ActResult("concle success!");
    }


    /**
     *
     * @param id 用户id
     * @param emailAccount 用户入职注册个人邮箱
     * @return
     * @throws ActException
     */
    @PutMapping("v1/sendAccountToEmplore")
    public ActResult sendAccountToEmp(@RequestParam String id, @RequestParam String emailAccount) throws ActException {
        //TODO: tanghaixiang 2017-03-09 未做邮件告知员工账号密码 记得抛异常
        return new ActResult("send success!");
    }



}
