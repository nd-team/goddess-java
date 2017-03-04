package com.bjike.goddess.card.service;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.common.api.exception.QueryException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import org.mengyun.tcctransaction.api.TransactionContext;

import javax.validation.Valid;
import java.util.List;

/**
 * 购票接口
 *
 * @author huanghuanlai
 */
public interface CardAPI extends SerAPI<Card, CardDTO> {


    /**
     * 初始化一张卡
     *
     * @param account  卡号
     * @param password 密码
     */
    default CardBO initCard(String account, String password) throws SerException {
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
     * @param txContext
     * @param account 购买账号
     * @param position 座位
     * @return
     * @throws SerException
     */
    default String buyTicket(TransactionContext txContext, String account,String position)throws SerException{
        return null;
    }

    /**
     * 退票
     * @param txContext
     * @param account 购买账号
     * @param position 座位
     * @return
     * @throws SerException
     */
    default String cancelTicket(TransactionContext txContext, String account,String position)throws SerException{
        return null;
    }


}
