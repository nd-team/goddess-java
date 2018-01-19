package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.IntelligentMsgDTO;
import com.bjike.goddess.recruit.entity.IntelligentMsg;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 智能消息提醒业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-01-16 11:07 ]
 * @Description: [ 智能消息提醒业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class IntelligentMsgSerImpl extends ServiceImpl<IntelligentMsg, IntelligentMsgDTO> implements IntelligentMsgSer {

    @Override
    public void add(IntelligentMsg intelligentMsg) throws SerException {
        super.save(intelligentMsg);
    }

    @Override
    public void del(String id) throws SerException {
        super.remove(id);
    }
}