package com.bjike.goddess.user.action;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.service.UserAuthCodeAPI;
import com.bjike.goddess.user.utils.AuthCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 17:28]
 * @Description: [验证码]
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
     * @param account 用户
     * @return
     * @throws SerException
     */
    @GetMapping("showAuthCode")
    public ActResult showAuthCode(String account) throws ActException {
        RpcContext.getContext().setAttachment("userToken", "1111");
        try {
            Boolean needCode = userAuthCodeSer.showAuthCode(account);
            return  ActResult.initialize(needCode);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 生成验证码 （登录/找回密码/注册）
     *
     * @param account
     * @param response
     * @return
     * @throws SerException
     */
    @GetMapping("generateCode")
    public void generateCode(String account, HttpServletResponse response) throws ActException {
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
