package com.bjike.goddess.function.action.function;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.function.api.UserFunctionAPI;
import com.bjike.goddess.function.to.UserFunctionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@LoginAuth
@RestController
@RequestMapping("user-function")
public class UserFunctionAction {

    @Autowired
    private UserFunctionAPI userFunctionAPI;

    /**
     * 添加用户功能
     *
     * @param userFunctionTO 新用户功能
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) UserFunctionTO userFunctionTO, BindingResult result) throws ActException {
        try {
            userFunctionAPI.add(userFunctionTO);
            return new ActResult("add function success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除用户功能
     *
     * @param id 功能id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            userFunctionAPI.delete(id);
            return new ActResult("delete function success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}