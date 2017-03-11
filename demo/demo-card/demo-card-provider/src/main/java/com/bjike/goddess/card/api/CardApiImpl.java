package com.bjike.goddess.card.api;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardSer;
import com.bjike.goddess.card.to.CardTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-10 15:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("cardApiImpl")
public class CardApiImpl implements CardAPI {
    @Autowired
    private CardSer cardSer;

    @Override
    public CardBO initCard(CardTO cardTO) throws SerException {
        return cardSer.initCard(cardTO);
    }

    @Override
    public CardBO findByAccount(String account) throws SerException {
        return cardSer.findByAccount(account);
    }

    @Override
    public String buyTicket(TransactionContext txContext, String account, String position) throws SerException {
        return cardSer.buyTicket(txContext, account, position);
    }

    @Override
    public String cancelTicket(TransactionContext txContext,String account, String position) throws SerException {
        return cardSer.cancelTicket(txContext, account, position);
    }
}
