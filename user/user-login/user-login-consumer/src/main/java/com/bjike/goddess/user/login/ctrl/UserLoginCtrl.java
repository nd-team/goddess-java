package com.bjike.goddess.user.login.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @Reference
//    private IUserLoginSer userLoginSer;
//
//    /**
//     * 登录
//     * @param com.bjike.goddess.card.dto
//     * @param request
//     * @param response
//     * @return
//     */
//    @GetMapping("login")
//    public ResponseText login(UserLoginDto com.bjike.goddess.card.dto, HttpServletRequest request, HttpServletResponse response){
//        try {
//            String token = userLoginSer.login(com.bjike.goddess.card.dto);
//            if(StringUtils.isNotBlank(token)){
//                handlerRememberMe(com.bjike.goddess.card.dto,request,response);
//                tokenToCookie(token,request,response);
//            }
//            return new ResponseText(token);
//        }catch (SerException e){
//            return   new ResponseText(e.getMessage());
//        }
//    }
//
//    /**
//     * 验证是否已登录
//     * @param token
//     * @return
//     */
//    @GetMapping("verify")
//    public ResponseText verify(String token){
//        try {
//            return new ResponseText(userLoginSer.verify(token));
//        }catch (SerException e){
//            return new ResponseText(e.getMessage());
//        }
//    }
//
//
//
//    /**
//     * 处理记住账号密码
//     *
//     * @param com.bjike.goddess.card.dto
//     */
//    private void handlerRememberMe(UserLoginDto com.bjike.goddess.card.dto,HttpServletRequest request,HttpServletResponse response) {
//        if (com.bjike.goddess.card.dto.isRememberMe()) {
//            Cookie cookie = new Cookie("account", com.bjike.goddess.card.dto.getAccount());
//            cookie.setMaxAge(100);
//            CookieOperate.addCookie(cookie,response);
//        } else {
//            CookieOperate.removeCookieByName(com.bjike.goddess.card.dto.getAccount(),request,response);
//        }
//    }
//
//    /**
//     * 把token 写入 cookie
//     * @param token
//     * @param request
//     * @param response
//     */
//    private void tokenToCookie(String token, HttpServletRequest request, HttpServletResponse response) {
//        Cookie cookie = new Cookie("token", token);
//        cookie.setMaxAge(100);
//        CookieOperate.addCookie(cookie, response);
//    }


}