package com.bjike.goddess.card.service;

import com.bjike.goddess.card.dto.CardDTO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 购票接口
 *
 * @author huanghuanlai
 */
public interface CardAPI extends SerAPI<Card, CardDTO> {

    /**
     * 初始化一张卡
     *
     * @param account  帐户
     * @param password 密码
     */
    void initCard(String account, String password) throws SerException;

    /**
     * 自己人购票
     *
     * @param txContext 分布式事务预留字段(必需)
     * @param account   卡号
     * @param password  密码
     * @param position  座位号
     * @return 购票信息
     * @throws SerException 常规业务异常
     */
    String buyTicketForCard(TransactionContext txContext, String account, String password, String position) throws SerException;

    /**
     * 第三方购票
     *
     * @param txContext 分布式事务预留字段(必需)
     * @param account   卡号
     * @param password  密码
     * @param position  座位号
     * @throws SerException 常规业务异常
     */
    void threeBuyTicketForCard(TransactionContext txContext, String account, String password, String position) throws SerException;
}
