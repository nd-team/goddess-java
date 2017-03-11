package com.bjike.goddess.card.api;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.to.CardTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-10 14:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CardAPI {

    /**
     * 初始化一张卡
     *
     * @param cardTO  数据传输对象

     */
    default CardBO initCard(CardTO cardTO) throws SerException {
        return null;
    }

    /**
     * 通过卡号查找卡新
     * @param account
     * @return
     * @throws SerException
     */
    default CardBO findByAccount(String account) throws SerException {
        return null;
    }

    /**
     * 购票
     * @param account 购买账号
     * @param position 座位
     * @return
     * @throws SerException
     */
    default String buyTicket(TransactionContext txContext,String account, String position)throws SerException{
        return null;
    }

    /**
     * 退票
     * @param account 购买账号
     * @param position 座位
     * @return
     * @throws SerException
     */
    default String cancelTicket(TransactionContext txContext,String account,String position)throws SerException{
        return null;
    }
}
