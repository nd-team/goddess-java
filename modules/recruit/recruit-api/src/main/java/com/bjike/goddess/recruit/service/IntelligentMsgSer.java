package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.dto.IntelligentMsgDTO;
import com.bjike.goddess.recruit.entity.IntelligentMsg;

/**
 * 智能消息提醒业务接口
 *
 * @Author: [ wany ]
 * @Date: [ 2018-01-16 11:07 ]
 * @Description: [ 智能消息提醒业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IntelligentMsgSer extends Ser<IntelligentMsg, IntelligentMsgDTO> {

    void add(IntelligentMsg intelligentMsg) throws SerException;

    void del(String id) throws SerException;

}