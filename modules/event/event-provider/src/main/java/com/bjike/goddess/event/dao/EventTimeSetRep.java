package com.bjike.goddess.event.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.EventTimeSetDTO;
import com.bjike.goddess.event.entity.Event;
import com.bjike.goddess.event.entity.EventTimeSet;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-12-29 15:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface EventTimeSetRep extends JpaRep<EventTimeSet,EventTimeSetDTO> {
}
