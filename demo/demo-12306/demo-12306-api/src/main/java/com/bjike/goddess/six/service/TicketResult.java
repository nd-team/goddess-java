package com.bjike.goddess.six.service;

import com.bjike.goddess.six.type.TicketType;

/**
 * 购票返回信息
 * @author huanghuanlai
 *
 */
public class TicketResult {

	/**
	 * 购票状态 
	 */
	private TicketType ticketType = TicketType.SUCCESS;
	/**
	 * 购票信息
	 */
	private String message;
	
	
	public TicketType getTicketType() {
		return ticketType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
