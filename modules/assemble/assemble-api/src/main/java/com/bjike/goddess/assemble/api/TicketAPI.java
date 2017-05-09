package com.bjike.goddess.assemble.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.assemble.bo.TicketBO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-10 15:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface TicketAPI {
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
    default String buyTicket(TransactionContext txContext, String account, String position)throws SerException {
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
