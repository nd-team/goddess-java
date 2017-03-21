package com.bjike.goddess.message.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.message.dto.GroupMessageDTO;
import com.bjike.goddess.message.entity.GroupMessage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 组消息业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:07:50.375 ]
 * @Description: [ 组消息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "messageSerCache")
@Service
public class GroupMessageSerImpl extends ServiceImpl<GroupMessage, GroupMessageDTO> implements GroupMessageSer {

}