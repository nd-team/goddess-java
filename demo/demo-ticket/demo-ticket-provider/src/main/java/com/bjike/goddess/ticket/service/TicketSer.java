package com.bjike.goddess.ticket.service;

import com.bjike.goddess.dbs.common.dto.Condition;
import com.bjike.goddess.dbs.common.enums.RestrictionType;
import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.ticket.dto.TicketDTO;
import com.bjike.goddess.ticket.entity.Ticket;

import java.time.LocalDateTime;

import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "TicketSerCache")
@Service("ticketSer")
public class TicketSer extends ServiceImpl<Ticket, TicketDTO> implements TicketAPI {

	@Override
	@Transactional(rollbackFor = SerException.class)
	@Compensable(confirmMethod = "buyTicketConfirm", cancelMethod = "buyTicketCancel")
	public String buyTicket(TransactionContext txContext, String account, String password, String position) throws SerException {
		Ticket ticket = new Ticket();
		ticket.setCard("68234-3423-5435-34234");
		ticket.setMoney(145);
		ticket.setOffTime(LocalDateTime.now());
		ticket.setOwner("452122xxxxxxxxxxxxxx");
		ticket.setPosition(position);// 座位号唯一
		super.save(ticket);
		return "购买成功";
	}

	@Transactional(rollbackFor = SerException.class)
	public String buyTicketConfirm(TransactionContext txContext, String account, String password, String position) throws SerException {
		System.out.println("恭喜您,成功购买:" + position);
		return null;
	}

	@Transactional(rollbackFor = SerException.class)
	public String buyTicketCancel(TransactionContext txContext, String account, String password, String position) throws SerException {
		System.out.println("购票事务回滚中...");
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.getConditions().add(new Condition("position", position, RestrictionType.EQ));
		Ticket ticket = super.findOne(ticketDTO);
		if (null != ticket) {
			super.remove(ticket);
			System.out.println("购票事务回滚成功.");
		} else {
			System.out.println("购票事务回滚失败.");
		}
		return null;
	}

	@Override
	@Compensable(confirmMethod = "outTicketConfirm", cancelMethod = "outTicketCancel")
	public void outTicket(TransactionContext txContext, String account, String password, String position) throws SerException {

	}

	public void outTicketConfirm(TransactionContext txContext, String account, String password, String position) throws SerException {

	}

	public void outTicketCancel(TransactionContext txContext, String account, String password, String position) throws SerException {

	}

}