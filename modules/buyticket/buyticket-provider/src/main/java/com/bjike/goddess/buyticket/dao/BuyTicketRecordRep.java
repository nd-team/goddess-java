package com.bjike.goddess.buyticket.dao;

import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketRecord;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 车票购买记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyTicketRecordRep extends JpaRep<BuyTicketRecord, BuyTicketRecordDTO> {

}