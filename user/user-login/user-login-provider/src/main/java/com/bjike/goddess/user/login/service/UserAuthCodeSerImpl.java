package com.bjike.goddess.user.login.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.session.authcode.AuthCode;
import com.bjike.goddess.user.common.session.authcode.AuthCodeSession;
import com.bjike.goddess.user.common.session.validfail.ValidErr;
import com.bjike.goddess.user.common.session.validfail.ValidErrSession;
import com.bjike.goddess.user.common.utils.authCode.AuthCodeGenerate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-26 09:36]
 * @Description: [用户验证码业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserAuthCodeSerImpl implements IUserAuthCodeSer {

    @Override
    public Boolean showAuthCode(String account) throws SerException {

        ValidErr code = ValidErrSession.get(account);
        if (null != code && code.getCount() >= 5) { //验证次数大于5次需要验证码
            return true;
        }
        return false;
    }

    @Override
    public BufferedImage generateCode(String account) throws SerException {
        BufferedImage image = null;
        String code = null;
        Map<String,BufferedImage> imageMap =  AuthCodeGenerate.build();
        for(Map.Entry<String, BufferedImage> entry:imageMap.entrySet()){
            image = entry.getValue();
            code = entry.getKey();
        }
        handleAuthCode(account,code);
        return image;
    }

    /**
     * 保存验证码到session
     * @param account
     * @param code
     */
    private void handleAuthCode(String account,String code){
        AuthCode authCode = new AuthCode();
        authCode.setCode(code);
        AuthCodeSession.put(account,authCode);

    }

}
