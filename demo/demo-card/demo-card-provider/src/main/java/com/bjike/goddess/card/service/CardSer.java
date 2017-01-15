package com.bjike.goddess.card.service;

import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.exception.QueryException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.RestrictionType;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.ticket.service.TicketAPI;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "CardSerCache")
@Service("cardSer")
public class CardSer extends ServiceImpl<Card, CardDTO> implements CardAPI {

    @Autowired
    private TicketAPI ticketApi;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void initCard(String account, String password) throws SerException{
        Card card = new Card();
        card.setAccount(account);
        card.setPassword(password);
        super.save(card);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "buyTicketForCardConfirm", cancelMethod = "buyTicketForCardCancel")
    public String buyTicketForCard(TransactionContext txContext,String account, String password, String position) throws SerException {
        System.out.println("银行卡购票尝试");
        CardDTO cardDTO = new CardDTO();
        cardDTO.getConditions().add(new Condition("account", account, RestrictionType.EQ));
        cardDTO.getConditions().add(new Condition("password", password, RestrictionType.EQ));

        Card card = super.findOne(cardDTO);
        if (null == card) {
            throw new SerException("帐号或密码错误");
        }
        String message = ticketApi.buyTicket(null, account, password, position);
        card.setMoney(card.getMoney() - 100);//减掉帐户余额
        super.update(card);//更新
        return "购买成功";
    }

    @Transactional(rollbackFor = SerException.class)
    public String buyTicketForCardConfirm(TransactionContext txContext,String account, String password, String position) throws SerException {
        System.out.println("银行卡购票确认");
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String buyTicketForCardCancel(TransactionContext txContext,String account, String password, String position) throws SerException {
        CardDTO cardDTO = new CardDTO();
        cardDTO.getConditions().add(new Condition("account", account, RestrictionType.EQ));
        cardDTO.getConditions().add(new Condition("password", password, RestrictionType.EQ));

        Card card = super.findOne(cardDTO);
        if (null == card) {
            throw new SerException("帐号或密码错误");
        }
        ticketApi.outTicket(null, account, password, position);//退票
        card.setMoney(card.getMoney() + 100);//加回帐户余额
        super.update(card);//更新
        System.out.println("银行卡购票取消");
        return null;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void threeBuyTicketForCard(TransactionContext txContext, String account, String password, String position) throws SerException {

    }

    @Override
    public Card query(Card card) throws QueryException {
        Card card1 = new Card();
        card1.setAccount("123");
        card1.setPassword("123");
        card1.setMoney(1000L);
        return card1;
    }


}