package com.bjike.goddess.push.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.push.service.PushUserInfoSer;
import com.bjike.goddess.push.to.PushUserInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 推送的用户装置信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("pushUserInfoApiImpl")
public class PushUserInfoApiImpl implements PushUserInfoAPI {
    @Autowired
    private PushUserInfoSer pushUserInfoSer;

    @Override
    public void save(PushUserInfoTO to) throws SerException {
        pushUserInfoSer.save(to);
    }

    @Override
    public String getToken(String name) throws SerException {
        return pushUserInfoSer.getToken(name);
    }
}