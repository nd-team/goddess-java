package com.bjike.goddess.message.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.GET;
import com.bjike.goddess.message.enums.MsgType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 消息推送数据传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.619 ]
 * @Description: [ 消息推送数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MessageDTO extends BaseDTO {
    @NotBlank(message = "用户id不能为空", groups = GET.class)
    private String userId;
    private MsgType msgType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }
}