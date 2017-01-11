package com.bjike.goddess.six.service;

import org.mengyun.tcctransaction.api.TransactionContext;

import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.dbs.common.service.IService;
import com.bjike.goddess.six.dto.TicketDTO;
import com.bjike.goddess.six.entity.Ticket;

/**
 * 购票接口
 * @author huanghuanlai
 *
 */
public interface TicketAPI extends IService<Ticket, TicketDTO> {
	
	/**
	 * 购票
	 * @param txContent 分布式事务预留字段(必需)
	 * @param account 12306帐户
	 * @param password 12306密码
	 * @param position 座位号
	 * @return 购票信息
	 * @throws SerException 常规业务异常
	 */
	String buyTicket(TransactionContext txContext,String account,String password,String position) throws SerException;

	/**
	 * 退票
	 * @param account 12306帐户
	 * @param password 12306密码
	 * @param position 座位号
	 * @throws SerException 常规业务异常
	 */
	void outTicket(TransactionContext txContext,String account,String password,String position) throws SerException;
}
