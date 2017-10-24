package com.bjike.goddess.push.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.regex.Validator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 推送的用户装置信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PushUserInfoTO extends BaseTO {

    /**
     * 移动端装置的唯一标识
     */
    @NotBlank(groups = ADD.class,message = "移动端装置的唯一标识不能为空")
    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}