package com.bjike.goddess.storage.action.storage;

import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.to.StorageUserTO;
import com.bjike.goddess.storage.vo.StorageUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 存储模块用户
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
public class StorageUserAct {

    @Autowired
    private StorageUserAPI storageUserAPI;

    /**
     * 注册
     *
     * @param storageUserTO 存储用户传输对象
     * @version v1
     */
    @PostMapping("v1/register")
    public Result register(@Validated(StorageUserTO.REGISTER.class) StorageUserTO storageUserTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String userToken = request.getHeader(RpcCommon.USER_TOKEN);
            storageUserTO.setUserToken(userToken);
            StorageUserBO storageUserBO = storageUserAPI.register(storageUserTO);
            return ActResult.initialize(BeanTransform.copyProperties(storageUserBO, StorageUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 登录
     *
     * @param storageUserTO 存储用户传输对象
     * @version v1
     */
    @PostMapping("v1/storage/login")
    public Result login(@Validated(StorageUserTO.LOGIN.class) StorageUserTO storageUserTO, BindingResult result) throws ActException {
        try {
            String token = storageUserAPI.login(storageUserTO);
            return ActResult.initialize(token);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 退出
     *
     * @param token 登录存储用户storageToken
     * @version v1
     */
    @GetMapping("v1/sign-out/{token}")
    public Result signOut(@PathVariable String token) throws ActException {
        try {
            Boolean result = storageUserAPI.signOut(token);
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}