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

    default CardBO findByAccount(String account) throws SerException {
        return null;
    }

    default String buyTicket(TransactionContext txContext, String account,String position)throws SerException{
        return null;
    }


}
