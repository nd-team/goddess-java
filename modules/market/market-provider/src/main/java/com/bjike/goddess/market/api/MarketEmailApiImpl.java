package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.service.MarketEmailSer;
import com.bjike.goddess.market.to.MarketEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private MarketEmailSer marketEmailSer;

    @Override
    public Long counts(MarketEmailDTO marketEmailDTO) throws SerException {
        return marketEmailSer.counts(marketEmailDTO);
    }


    @Override
    public MarketEmailBO getOne(String id) throws SerException {
        return marketEmailSer.getOne(id);
    }


    @Override
    public List<MarketEmailBO> listMarketEmail(MarketEmailDTO marketEmailDTO) throws SerException {
        return marketEmailSer.listMarketEmail(marketEmailDTO);
    }

    @Override
    public MarketEmailBO addMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        marketEmailTO.setLastSendTime(DateUtil.dateToString(LocalDateTime.now()));
        return marketEmailSer.addMarketEmail(marketEmailTO);
    }

    @Override
    public MarketEmailBO editMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        return marketEmailSer.editMarketEmail(marketEmailTO);
    }

    @Override
    public void deleteMarketEmail(String id) throws SerException {
        marketEmailSer.deleteMarketEmail(id);
    }

    @Override
    public List<MarketEmailBO> collectMarketEmail(String[] area) throws SerException {
        return marketEmailSer.collectMarketEmail(area);
    }
}