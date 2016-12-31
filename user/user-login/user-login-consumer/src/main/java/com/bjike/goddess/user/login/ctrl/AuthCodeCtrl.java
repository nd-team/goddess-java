package com.bjike.goddess.user.login.ctrl;

import com.dounine.corgi.commons.ResponseText;
import com.dounine.corgi.spring.rpc.Reference;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.login.dto.UserLoginDto;
import org.ndshop.user.login.service.IUserAuthCodeSer;
import org.ndshop.user.login.service.IUserLoginSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-25 17:28]
 * @Description: [验证码]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RestController
@RequestMapping("showAuthCode")
public class AuthCodeCtrl {
    @Reference
    private IUserAuthCodeSer userAuthCodeSer;

    /**
     * 显示验证码
     * @param account 用户
     * @return
     * @throws SerException
     */
    @GetMapping("showAuthCode")
    public ResponseText showAuthCode(String account) throws SerException {
        return new ResponseText(userAuthCodeSer.showAuthCode(account));
    }

    /**
     * 生成验证码
     * @param account
     * @param request
     * @param response
     * @return
     * @throws SerException
     */
    @GetMapping("generateCode")
    public ResponseText generateCode(String account, HttpServletRequest request, HttpServletResponse response) throws SerException {
        response.setContentType("image/jpeg");
        response.setDateHeader("expries",-1);
        response.setHeader("Cache-Control","no-cache");
        return new ResponseText(userAuthCodeSer.generateCode(account));
    }

}
