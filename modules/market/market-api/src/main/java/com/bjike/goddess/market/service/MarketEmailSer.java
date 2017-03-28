package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.entity.MarketEmail;

import java.util.List;

/**
 * 市场邮件发送定制业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.883 ]
 * @Description: [ 市场邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketEmailSer extends Ser<MarketEmail, MarketEmailDTO> {

    /**
     * 汇总
     *
     * @param areas areas
     * @return class MarketEmailBO
     */
    default List<MarketEmailBO> collectMarketEmail(String[] areas) throws SerException {
        return null;
    }

}