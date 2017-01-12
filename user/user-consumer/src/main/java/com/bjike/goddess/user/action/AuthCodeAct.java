package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.service.IUserAuthCodeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-25 17:28]
 * @Description: [验证码]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("showAuthCode")
public class AuthCodeAct {
    @Autowired
    private IUserAuthCodeSer userAuthCodeSer;

    /**
     * 显示验证码
     * @param account 用户
     * @return
     * @throws SerException
     */
    @GetMapping("showAuthCode")
    public String showAuthCode(String account) throws SerException {
        return  String.valueOf(userAuthCodeSer.showAuthCode("12121"));
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
    public void  generateCode(String account, HttpServletRequest request, HttpServletResponse response) throws ActException {
        response.setContentType("image/jpeg");
        response.setDateHeader("expries",-1);
        response.setHeader("Cache-Control","no-cache");
        try {
            BufferedImage bufferedImage =  userAuthCodeSer.generateCode(account);
            ImageIO.write(bufferedImage , "JPEG", response.getOutputStream());
            bufferedImage.flush();
        }catch (IOException e){
            throw new ActException(e.getMessage());
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
