package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.UserLoginDTO;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.service.IUserLoginSer;
import com.bjike.goddess.user.utils.CheckMobile;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户登录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class UserLoginAct {

    @Autowired
    private IUserLoginSer userLoginSer;

    /**
     * 登录
     * @param dto
     * @param request
     * @param response
     * @return
     */
    @GetMapping("login")
    public String login(UserLoginDTO dto, HttpServletRequest request, HttpServletResponse response)throws ActException {
        try {
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
               type = LoginType.MOBILE;
            }
            dto.setLoginType(type);
            String token = userLoginSer.login(dto);
            if(StringUtils.isNotBlank(token)){
                tokenToCookie(token,request,response);
            }
            return token;
        }catch (SerException e){
            throw   new ActException(e.getMessage());
        }
    }

    /**
     * 验证是否已登录
     * @param token
     * @return
     */
    @GetMapping("verify")
    public String verify (String token)throws ActException{
        try {
             return String.valueOf(userLoginSer.verify(token));
        }catch (SerException e){
            throw   new ActException(e.getMessage());
        }
    }

    /**
     * 把token 写入 cookie
     * @param token
     * @param request
     * @param response
     */
    private void tokenToCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                c.setMaxAge(0);
            }
        }

        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60 * 60 * 24);// 立即销毁cookie
        response.addCookie(cookie);
    }


}