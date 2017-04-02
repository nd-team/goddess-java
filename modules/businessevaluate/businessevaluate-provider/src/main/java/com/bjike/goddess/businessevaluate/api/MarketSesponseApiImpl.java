package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.MarketSesponseBO;
import com.bjike.goddess.businessevaluate.dto.MarketSesponseDTO;
import com.bjike.goddess.businessevaluate.service.MarketSesponseSer;
import com.bjike.goddess.businessevaluate.to.MarketSesponseTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场反应和创新能力业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketSesponseApiImpl")
public class MarketSesponseApiImpl implements MarketSesponseAPI {

    @Autowired
    private MarketSesponseSer marketSesponseSer;

    @Override
    public MarketSesponseBO addModel(MarketSesponseTO to) throws SerException {
        return marketSesponseSer.addModel(to);
    }

    @Override
    public MarketSesponseBO editModel(MarketSesponseTO to) throws SerException {
        return marketSesponseSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        marketSesponseSer.remove(id);
    }

    @Override
    public List<MarketSesponseBO> pageList(MarketSesponseDTO dto) throws SerException {
        return marketSesponseSer.pageList(dto);
    }
}