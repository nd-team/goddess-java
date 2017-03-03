package com.bjike.goddess.ticket.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.ticket.bo.TicketBO;
import com.bjike.goddess.ticket.dto.TicketDTO;
import com.bjike.goddess.ticket.entity.Ticket;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 购票接口
 *
 * @author huanghuanlai
 */
public interface TicketAPI extends SerAPI<Ticket, TicketDTO> {

    /**
     * 创建车票
     *
     * @param ticketBOS
     */
    default List<TicketBO> createTicket(List<TicketBO> ticketBOS)throws SerException {
        return null;
    }


    /**
     * 查询余票
     *
     */
    default List<TicketBO> queryTicket()throws SerException {
        return null;
    }

    /**
     * 用户购票
     *@param account 购买账号
     *@param position 购买座位
     */
    default String buyTicket(TransactionContext txContext,String account,String position)throws SerException {
        return null;
    }


}
