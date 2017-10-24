package com.bjike.goddess.push.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.push.dto.PushUserInfoDTO;
import com.bjike.goddess.push.entity.PushUserInfo;
import com.bjike.goddess.push.to.PushUserInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 推送的用户装置信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-11 10:18 ]
 * @Description: [ 推送的用户装置信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "pushSerCache")
@Service
public class PushUserInfoSerImpl extends ServiceImpl<PushUserInfo, PushUserInfoDTO> implements PushUserInfoSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(PushUserInfoTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        String deviceToken = to.getDeviceToken();
        PushUserInfoDTO dto = new PushUserInfoDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        PushUserInfo entity = super.findOne(dto);
        if (null != entity) {
            entity.setDeviceToken(deviceToken);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            entity = new PushUserInfo();
            entity.setName(name);
            entity.setDeviceToken(deviceToken);
            super.save(entity);
        }
    }

    @Override
    public String getToken(String name) throws SerException {
        PushUserInfoDTO dto = new PushUserInfoDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        PushUserInfo entity = super.findOne(dto);
        if (null != entity) {
            return entity.getDeviceToken();
        }
        return null;
    }
}