package com.bjike.goddess.ticket.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjike.goddess.dbs.common.exception.ActException;
import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.ticket.service.TicketAPI;

/**
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController
@RequestMapping("demo/ticket")
public class TicketAct {
	
	@Autowired
	private TicketAPI ticketAPI;
	
    @GetMapping("buy")
    public String buy(String account,String password,String position) throws ActException{
    	try {
			String message = ticketAPI.buyTicket(null, account, password, position);
			return "{\"msg\":\""+message+"\"}";
		} catch (SerException e) {
			throw new ActException(e.getMessage());
		}
    }
}
