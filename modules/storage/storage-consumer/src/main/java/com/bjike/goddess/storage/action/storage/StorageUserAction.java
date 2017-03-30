package com.bjike.goddess.storage.action.storage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.to.StorageUserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("storage/user")
public class StorageUserAction {

    @Autowired
    private StorageUserAPI storageUserAPI;

    /**
     * 注册
     *
     * @param storageUserTO 存储用户传输对象
     * @version v1
     */
    @PostMapping("v1/register")
    public Result register(@Validated(StorageUserTO.REGISTER.class) StorageUserTO storageUserTO) throws ActException {
        try {
            StorageUserBO storageUserBO = storageUserAPI.register(storageUserTO);
            return ActResult.initialize(storageUserBO);
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
    @PostMapping("v1/login")
    public Result login(@Validated(StorageUserTO.LOGIN.class) StorageUserTO storageUserTO) throws ActException {
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
     * @version v1
     */
    @PostMapping("v1/signOut")
    public Result signOut() throws ActException {
        try {
             Boolean result = storageUserAPI.signOut();
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}