package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.market.bo.MarketEmailBO;


/**
 * 市场汇总邮件发送定制业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.882 ]
 * @Description: [ 市场汇总邮件发送定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketEmailAPI {

    /**
     * 汇总
     *
     * @param works works
     * @return class MarketEmailBO
     */
    default MarketEmailBO collectMarketEmail(String[] works) throws SerException {
        return null;
    }


}