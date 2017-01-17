package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.dto.ext.UserLoginDTO;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.service.UserLoginAPI;
import com.bjike.goddess.user.utils.CheckMobile;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: [用户登录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class LoginAct {

    @Autowired
    private UserLoginAPI userLoginSer;

    /**
     * 登录
     *
     * @param dto
     * @param request
     * @return
     */
    @GetMapping("login")
    public ActResult login(UserLoginDTO dto, HttpServletRequest request) throws ActException {
        try {
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
                type = LoginType.MOBILE;
            }
            dto.setLoginType(type);
            String token = userLoginSer.login(dto);
            return  ActResult.initialize(token);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}