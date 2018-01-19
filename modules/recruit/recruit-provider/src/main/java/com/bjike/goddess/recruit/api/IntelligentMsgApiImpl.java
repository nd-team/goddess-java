package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.entity.IntelligentMsg;
import com.bjike.goddess.recruit.service.IntelligentMsgSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 智能消息提醒业务接口实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-01-16 11:07 ]
 * @Description: [ 智能消息提醒业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("intelligentMsgApiImpl")
public class IntelligentMsgApiImpl implements IntelligentMsgAPI {
    @Autowired
    private IntelligentMsgSer intelligentMsgSer;

    @Override
    public void add(IntelligentMsg intelligentMsg) throws SerException {
        intelligentMsgSer.add(intelligentMsg);
    }

    @Override
    public void del(String id) throws SerException {
        intelligentMsgSer.del(id);
    }
}