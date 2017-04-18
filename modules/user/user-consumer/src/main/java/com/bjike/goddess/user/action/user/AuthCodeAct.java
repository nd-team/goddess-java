package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserAuthCodeAPI;
import com.bjike.goddess.user.utils.AuthCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
@RequestMapping("auth-code")
public class AuthCodeAct {
    @Autowired
    private UserAuthCodeAPI userAuthCodeAPI;

    /**
     * 登录是否需要验证码
     *
     * @param account 账号email,username,phone
     * @des true代表需要要验证, false代表不需要验证码,返回true时调用generate-code方法生成验证码
     * @version v1
     */
    @GetMapping("v1/show/{account}")
    public Result showAuthCode(@PathVariable String account) throws ActException {
        try {
            Boolean needCode = userAuthCodeAPI.showAuthCode(account);
            return ActResult.initialize(needCode);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 生成验证码
     *
     * @param account 账号email,username,phone
     * @return {name:'testName',type:'string',defaultValue:'',description:'图片流.'}
     * @des 登录找回密码注册, 验证码图片流
     * @version v1
     */
    @GetMapping("v1/generate-code/{account}")
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

            userAuthCodeAPI.handleAuthCode(account, code);
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
