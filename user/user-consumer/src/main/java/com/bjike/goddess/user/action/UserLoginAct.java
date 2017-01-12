package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.UserLoginDTO;
import com.bjike.goddess.user.service.IUserLoginSer;
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
            String token = userLoginSer.login(dto);
            if(StringUtils.isNotBlank(token)){
                handlerRememberMe(dto,request,response);
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
     * 处理记住账号密码
     *
     * @param dto
     */
    private void handlerRememberMe(UserLoginDTO dto,HttpServletRequest request,HttpServletResponse response) {
        if (dto.isRememberMe()) {
            Cookie cookie = new Cookie("account", dto.getAccount());
            cookie.setMaxAge(100);
//            CookieOperate
        } else {
//            CookieOperate.removeCookieByName(dto.getAccount(),request,response);
        }
    }

    /**
     * 把token 写入 cookie
     * @param token
     * @param request
     * @param response
     */
    private void tokenToCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(100);
//        CookieOperate.addCookie(cookie, response);
    }


}