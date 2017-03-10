package com.bjike.goddess.card.service;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.ticket.service.TicketSer;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardSerImpl extends ServiceImpl<Card, CardDTO> implements CardSer {

    @Autowired
    private TicketSer ticketApi;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public CardBO initCard(String account, String password) throws SerException{
        Card card = new Card();
        card.setAccount(account);
        card.setPassword(password);
        super.save(card);
        return  BeanTransform.copyProperties(card,CardBO.class);
    }



    @Override
    public CardBO findByAccount(String account) throws SerException {
        CardDTO dto = new CardDTO();
        dto.getConditions().add(Restrict.eq("account",account));
        CardBO bo = BeanTransform.copyProperties(super.findOne(dto),CardBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "buyTicketConfirm", cancelMethod = "buyTicketRollback")
    @Override
    public String buyTicket(TransactionContext txContext, String account, String position) throws SerException {
        CardDTO dto = new CardDTO();
        dto.getConditions().add(Restrict.eq("account",account));
        Card card = findOne(dto);
        card.setMoney(card.getMoney()-140);//减掉帐户余额
        super.update(card);
        ticketApi.buyTicket(txContext,account,position);
        return "购票成功";
    }


    @Transactional(rollbackFor = SerException.class)
    public String buyTicketConfirm(TransactionContext txContext,String account,String position) throws SerException {
        System.out.println("购票确认");
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String buyTicketRollback(TransactionContext txContext,String account,  String position) throws SerException {
        cancelTicket(txContext,account,position);
        return null;
    }


    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "cancelTicketConfirm", cancelMethod = "cancelTicketCancel")
    @Override
    public String cancelTicket(TransactionContext txContext, String account, String position) throws SerException {
        CardDTO dto = new CardDTO();
        dto.getConditions().add(Restrict.eq("account",account));
        Card card = findOne(dto);
        if (null == card) {
            throw new SerException("帐号或密码错误");
        }
        ticketApi.ticketCancel(null, account, position);//退票
        card.setMoney(card.getMoney() + 140);//加回帐户余额
        super.update(card);//更新
        System.out.println("购票取消");
        return "取消成功";
    }
}