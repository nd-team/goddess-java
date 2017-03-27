package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.service.MarketInfoSer;
import com.bjike.goddess.market.to.MarketInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场信息管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.566 ]
 * @Description: [ 市场信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketInfoApiImpl")
public class MarketInfoApiImpl implements MarketInfoAPI {
    @Autowired
    private MarketInfoSer marketInfoSer;

    @Override
    public List<MarketInfoBO> findListMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        return marketInfoSer.findListMarketInfo(marketInfoDTO);
    }

    @Override
    public MarketInfoBO insertMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        return marketInfoSer.insertMarketInfo(marketInfoTO);
    }

    @Override
    public MarketInfoBO editMarketInfo(MarketInfoTO marketInfoTO) throws SerException {

        return marketInfoSer.editMarketInfo(marketInfoTO);
    }

    @Override
    public void removeMarketInfo(String id) throws SerException {
        marketInfoSer.remove(id);
    }

    @Override
    public String exportExcel(String customerName) throws SerException {
        return marketInfoSer.exportExcel(customerName);
    }


}