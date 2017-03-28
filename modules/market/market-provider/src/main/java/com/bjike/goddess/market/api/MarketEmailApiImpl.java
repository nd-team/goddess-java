package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.service.MarketEmailSer;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 市场邮件发送定制业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.889 ]
 * @Description: [ 市场邮件发送定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketEmailApiImpl")
public class MarketEmailApiImpl implements MarketEmailAPI {

    private MarketEmailSer marketEmailSer;

    @Override
    public List<MarketEmailBO> collectMarketEmail(String[] works) throws SerException {
        return marketEmailSer.collectMarketEmail(works);
    }
}