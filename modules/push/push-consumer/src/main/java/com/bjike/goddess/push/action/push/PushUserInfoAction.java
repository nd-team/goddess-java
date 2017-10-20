package com.bjike.goddess.push.action.push;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.push.api.PushUserInfoAPI;
import com.bjike.goddess.push.to.PushUserInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推送的用户装置信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("pushuserinfo")
public class PushUserInfoAction {
    @Autowired
    private PushUserInfoAPI pushUserInfoAPI;

    /**
     * 推送的用户装置信息登陆
     *
     * @param to to
     * @throws ActException
     * @Version v1
     */
    @PostMapping("v1/login")
    public Result login(@Validated(ADD.class) PushUserInfoTO to, BindingResult result) throws ActException {
        try {
            pushUserInfoAPI.save(to);
            return new ActResult("登陆成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}