package com.bjike.goddess.push.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.push.dto.PushUserInfoDTO;
import com.bjike.goddess.push.entity.PushUserInfo;
import com.bjike.goddess.push.to.PushUserInfoTO;

/**
 * 推送的用户装置信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PushUserInfoSer extends Ser<PushUserInfo, PushUserInfoDTO> {
    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    void save(PushUserInfoTO to) throws SerException;

    /**
     * 获取装置token
     * @param name
     * @return
     * @throws SerException
     */
    String getToken(String name) throws SerException;
}