package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.api.UserFindPwdAPI;
import com.bjike.goddess.user.bo.UserSimpleBO;
import com.bjike.goddess.user.service.UserFindPwdSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 找回密码
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("findPwd")
public class FindPwdAct {

    @Autowired
    private UserFindPwdAPI userFindPwdAPI;

    /**
     * 验证码验证
     *
     * @param account  账号email,username,phone
     * @param authCode 验证码
     * @version v1
     */
    @GetMapping("v1/verify{account}/{authCode}")
    public Result verifyAccount(@RequestParam String account, @RequestParam String authCode) throws ActException {
        try {
            UserSimpleBO simpleBO = userFindPwdAPI.verifyAccount(account, authCode);
            return ActResult.initialize(simpleBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送手机验证码
     *
     * @param nickname 昵称
     * @version v1
     */

    @GetMapping("v1/sendCode/{nickname}")
    public Result sendCode(@PathVariable String nickname) throws ActException {
        try {
            Boolean result = userFindPwdAPI.sendCodeByNickname(nickname);
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}