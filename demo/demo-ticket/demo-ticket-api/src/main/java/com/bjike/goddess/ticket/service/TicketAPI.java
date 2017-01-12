package com.bjike.goddess.ticket.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.ticket.dto.TicketDTO;
import com.bjike.goddess.ticket.entity.Ticket;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 购票接口
 * @author huanghuanlai
 *
 */
public interface TicketAPI extends SerAPI<Ticket, TicketDTO> {
	
	/**
	 * 购票
	 * @param txContext 分布式事务预留字段(必需)
	 * @param account 12306帐户
	 * @param password 12306密码
	 * @param position 座位号
	 * @return 购票信息
	 * @throws SerException 常规业务异常
	 */
	String buyTicket(TransactionContext txContext,String account,String password,String position) throws SerException;

	/**
	 * 退票
	 * @param txContext 分布式事务预留字段(必需)
	 * @param account 12306帐户
	 * @param password 12306密码
	 * @param position 座位号
	 * @throws SerException 常规业务异常
	 */
	void outTicket(TransactionContext txContext,String account,String password,String position) throws SerException;
}
