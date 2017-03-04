package com.bjike.goddess.ticket.service;

import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.RestrictionType;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.ticket.bo.TicketBO;
import com.bjike.goddess.ticket.dto.TicketDTO;
import com.bjike.goddess.ticket.entity.Ticket;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@CacheConfig(cacheNames = "TicketSerCache")
@Service("ticketSer")
public class TicketSer extends ServiceImpl<Ticket, TicketDTO> implements TicketAPI {

    @Override
    public List<TicketBO> createTicket(List<TicketBO> ticketBOS) throws SerException {
        List<Ticket> tickets = BeanTransform.copyProperties(ticketBOS, Ticket.class, true);
        super.save(tickets);
        return ticketBOS;
    }

    @Override
    public List<TicketBO> queryTicket() throws SerException {
        TicketDTO dto = new TicketDTO();
        dto.getConditions().add(Restrict.isNull("account"));
        List<TicketBO> bos = BeanTransform.copyProperties(super.findByCis(dto), TicketBO.class);
        return bos;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "buyTicketConfirm", cancelMethod = "buyTicketCancel")
    public String buyTicket(TransactionContext txContext, String account, String position) throws SerException {
        TicketDTO dto = new TicketDTO();
        dto.getConditions().add(Restrict.eq("position", position));
        dto.getConditions().add(Restrict.isNull("account"));
        Ticket ticket = super.findOne(dto);
        if (null == ticket) {
            throw new SerException("该座位不存在,或者已被购买!");
        }
        ticket.setAccount(account); //占用座位
        return "购票成功";
    }


    @Transactional(rollbackFor = SerException.class)
    public String buyTicketConfirm(TransactionContext txContext, String account, String position) throws SerException {
        System.out.println("恭喜您,成功购买:" + position);
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String buyTicketCancel(TransactionContext txContext, String account, String position) throws SerException {
        ticketCancel(txContext, account, position);
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "ticketCancelConfirm", cancelMethod = "ticketCancelRollback")
    @Override
    public String ticketCancel(TransactionContext txContext, String account, String position) throws SerException {
        TicketDTO dto = new TicketDTO();
        dto.getConditions().add(Restrict.eq("position", position));
        Ticket ticket = super.findOne(dto);
        if (null != ticket) {
            ticket.setAccount(null);
            super.modify(ticket); //退回座位
        } else {
            System.out.println("退票失败.");
        }
        return "退票成功";
    }

    @Transactional(rollbackFor = SerException.class)
    public String ticketCancelConfirm(TransactionContext txContext, String account, String position) throws SerException {
        System.out.println("退票成功");
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String ticketCancelRollback(TransactionContext txContext, String account, String position) throws SerException {
        TicketDTO dto = new TicketDTO();
        dto.getConditions().add(Restrict.eq("position", position));
        Ticket ticket = super.findOne(dto);
        if (null != ticket) {
            ticket.setAccount(account);
            super.modify(ticket); //退回座位
        }
        System.out.println("退票失败");
        return null;
    }


}