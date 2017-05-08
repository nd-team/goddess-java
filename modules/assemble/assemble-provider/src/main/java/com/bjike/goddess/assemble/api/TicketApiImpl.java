package com.bjike.goddess.assemble.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.assemble.bo.TicketBO;
import com.bjike.goddess.assemble.entity.Ticket;
import com.bjike.goddess.assemble.service.TicketSer;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-10 15:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("ticketApiImpl")
public class TicketApiImpl implements TicketAPI {
    @Autowired
    private TicketSer ticketSer;

    @Override
    public List<TicketBO> createTicket(List<TicketBO> ticketBOS) throws SerException {
        List<Ticket> tickets = BeanTransform.copyProperties(ticketBOS,Ticket.class,true);
        ticketSer.createTicket(tickets);
        return ticketBOS;
    }

    @Override
    public List<TicketBO> queryTicket() throws SerException {
        return BeanTransform.copyProperties(ticketSer.queryTicket(),TicketBO.class);
    }

    @Override
    public String buyTicket(TransactionContext txContext, String account, String position) throws SerException {
        return ticketSer.buyTicket(txContext, account, position);
    }

    @Override
    public String ticketCancel(TransactionContext txContext, String account, String position) throws SerException {
        return ticketSer.ticketCancel(txContext, account, position);
    }
}
