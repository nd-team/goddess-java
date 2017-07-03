package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.to.UserTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
public class UserAct {

    @Autowired
    private UserAPI userAPI;

    /**
     * 手机号码是否存在
     *
     * @param phone 手机号码
     * @version v1
     */
    @GetMapping("v1/phone/{phone}/exists")
    public Result existPhone(@PathVariable String phone) throws ActException {
        try {
            Boolean result = (null != userAPI.findByPhone(phone));
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @version v1
     */
    @GetMapping("v1/username/{username}/exists")
    public Result existUsername(@PathVariable String username) throws ActException {
        try {
            Boolean result = (null != userAPI.findByUsername(username));
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加用户
     *
     * @param userTO 用户
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(UserTO userTO) throws ActException {
        try {
            Boolean result = (null != userAPI.add(null, userTO));
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}