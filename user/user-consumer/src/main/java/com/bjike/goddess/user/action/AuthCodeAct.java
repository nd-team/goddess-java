package com.bjike.goddess.user.action;

import com.alibaba.fastjson.JSONObject;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.service.UserAuthCodeAPI;
import com.bjike.goddess.user.utils.AuthCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 验证码
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 17:28]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("authCode")
public class AuthCodeAct {
    @Autowired
    private UserAuthCodeAPI userAuthCodeSer;

    /**
     * 显示验证码（登录是否需要验证码）
     *
     * @param account 账号(email,username,phone)
     * @return true代表需要要验证,false代表不需要验证码
     * @throws SerException
     */
    @GetMapping("showAuthCode/{account}")
    public ActResult showAuthCode(@PathVariable String account) throws ActException {
        try {
            Boolean needCode = userAuthCodeSer.showAuthCode(account);
            return ActResult.initialize(needCode);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 生成验证码 （登录/找回密码/注册）
     *
     * @param account  账号(email,username,phone)
     * @param response
     * @return 验证码图片流
     * @throws SerException
     */
    @GetMapping("generateCode/{account}")
    public void generateCode(@PathVariable String account, HttpServletResponse response) throws ActException {
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        try {
            Map<String, BufferedImage> imageMap = AuthCodeGenerate.build();
            BufferedImage bufferedImage = null;
            String code = null;
            for (Map.Entry<String, BufferedImage> entry : imageMap.entrySet()) {
                bufferedImage = entry.getValue();
                code = entry.getKey();
            }
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
            bufferedImage.flush();

            userAuthCodeSer.handleAuthCode(account, code);
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
