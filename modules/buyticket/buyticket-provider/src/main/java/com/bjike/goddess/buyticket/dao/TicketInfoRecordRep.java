package com.bjike.goddess.buyticket.dao;

import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.entity.TicketInfoRecord;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 车票信息记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TicketInfoRecordRep extends JpaRep<TicketInfoRecord, TicketInfoRecordDTO> {

}