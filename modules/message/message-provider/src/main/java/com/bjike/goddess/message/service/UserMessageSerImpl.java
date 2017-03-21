package com.bjike.goddess.message.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.message.dto.UserMessageDTO;
import com.bjike.goddess.message.entity.UserMessage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 用户消息业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:40:27.620 ]
 * @Description: [ 用户消息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "messageSerCache")
@Service
public class UserMessageSerImpl extends ServiceImpl<UserMessage, UserMessageDTO> implements UserMessageSer {

}