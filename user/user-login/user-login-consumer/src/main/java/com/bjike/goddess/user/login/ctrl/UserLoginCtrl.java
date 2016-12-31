package com.bjike.goddess.user.login.ctrl;

import com.dounine.corgi.commons.ResponseText;
import com.dounine.corgi.spring.rpc.Reference;
import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.utils.cookie.CookieOperate;
import org.ndshop.user.login.dto.UserLoginDto;
import org.ndshop.user.login.service.IUserLoginSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户登录]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RestController
@RequestMapping("login")
public class UserLoginCtrl {

    @Reference
    private IUserLoginSer userLoginSer;

    /**
     * 登录
     * @param dto
     * @param request
     * @param response
     * @return
     */
    @GetMapping("login")
    public ResponseText login(UserLoginDto dto, HttpServletRequest request, HttpServletResponse response){
        try {
            String token = userLoginSer.login(dto);
            if(StringUtils.isNotBlank(token)){
                handlerRememberMe(dto,request,response);
                tokenToCookie(token,request,response);
            }
            return new ResponseText(token);
        }catch (SerException e){
            return   new ResponseText(e.getMessage());
        }
    }

    /**
     * 验证是否已登录
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseText verify(String token){
        try {
            return new ResponseText(userLoginSer.verify(token));
        }catch (SerException e){
            return new ResponseText(e.getMessage());
        }
    }



    /**
     * 处理记住账号密码
     *
     * @param dto
     */
    private void handlerRememberMe(UserLoginDto dto,HttpServletRequest request,HttpServletResponse response) {
        if (dto.isRememberMe()) {
            Cookie cookie = new Cookie("account", dto.getAccount());
            cookie.setMaxAge(100);
            CookieOperate.addCookie(cookie,response);
        } else {
            CookieOperate.removeCookieByName(dto.getAccount(),request,response);
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
        CookieOperate.addCookie(cookie, response);
    }


}