package com.bjike.goddess.assemble.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assemble.dto.TicketDTO;
import com.bjike.goddess.assemble.entity.Ticket;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 购票接口
 *
 * @author huanghuanlai
 */
public interface TicketSer extends Ser<Ticket, TicketDTO> {

    /**
     * 创建车票
     *
     * @param tickets
     */
    default List<Ticket> createTicket(List<Ticket> tickets)throws SerException {
        return null;
    }


    /**
     * 查询余票
     *
     */
    default List<Ticket> queryTicket()throws SerException {
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
 /**
     * 购票取消
     *@param account 购买账号
     *@param position 购买座位
     */
    default String ticketCancel(TransactionContext txContext,String account,String position)throws SerException {
        return null;
    }


}
