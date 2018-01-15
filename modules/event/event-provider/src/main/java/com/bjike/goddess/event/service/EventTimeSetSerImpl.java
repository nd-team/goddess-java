package com.bjike.goddess.event.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.event.dto.EventTimeSetDTO;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.entity.EventTimeSet;
import com.bjike.goddess.event.entity.TimeSet;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-12-29 15:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "eventSerCache")
@Service
public class EventTimeSetSerImpl extends ServiceImpl<EventTimeSet, EventTimeSetDTO> implements  EventTimeSetSer{

}
