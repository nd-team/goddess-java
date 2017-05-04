package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserLoginLogAPI;
import com.bjike.goddess.user.bo.UserLoginLogBO;
import com.bjike.goddess.user.vo.UserLoginLogVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户日志操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@DefaultProperties
@RestController
public class LoginLogAct {

    @Autowired
    private UserLoginLogAPI userLoginLogAPI;


    /**
     * 获取登录日志列表
     *
     * @param id 用户id
     * @return class UserLoginLogVO
     * @throws ActException
     * @userToken yes
     * @version v1
     */

    @GetMapping("v1/{id}/logs")
    public Result logs(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<UserLoginLogBO> loginLogs = userLoginLogAPI.findByUserId(id);
            return ActResult.initialize(BeanTransform.copyProperties(loginLogs, UserLoginLogVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}