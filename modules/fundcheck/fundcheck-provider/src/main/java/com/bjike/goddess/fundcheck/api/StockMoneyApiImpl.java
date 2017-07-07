package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.service.StockMoneySer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.StockMoneyCollectTO;
import com.bjike.goddess.fundcheck.to.StockMoneyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收到股东款业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("stockMoneyApiImpl")
public class StockMoneyApiImpl implements StockMoneyAPI {
    @Autowired
    private StockMoneySer stockMoneySer;
    @Override
    public Boolean sonPermission() throws SerException {
        return stockMoneySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return stockMoneySer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(StockMoneyDTO stockMoneyDTO) throws SerException {
        return stockMoneySer.count(stockMoneyDTO);
    }

    @Override
    public StockMoneyBO getOne(String id) throws SerException {
        return stockMoneySer.getOne(id);
    }

    @Override
    public List<StockMoneyBO> findList(StockMoneyDTO stockMoneyDTO) throws SerException {
        return stockMoneySer.findList(stockMoneyDTO);
    }

    @Override
    public StockMoneyBO insert(StockMoneyTO stockMoneyTO) throws SerException {
        return stockMoneySer.insert(stockMoneyTO);
    }

    @Override
    public StockMoneyBO edit(StockMoneyTO stockMoneyTO) throws SerException {
        return stockMoneySer.edit(stockMoneyTO);
    }

    @Override
    public void remove(String id) throws SerException {
        stockMoneySer.remove(id);

    }
    @Override
    public List<StockMoneyBO> collect(StockMoneyCollectTO to) throws SerException {
        return stockMoneySer.collect(to);
    }

}