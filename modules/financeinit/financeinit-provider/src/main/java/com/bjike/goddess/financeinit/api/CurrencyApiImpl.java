package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.service.CurrencySer;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置币别业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("currencyApiImpl")
public class CurrencyApiImpl implements CurrencyAPI {
    @Autowired
    private CurrencySer currencySer;
    @Override
    public Long countCurren(CurrencyDTO currencyDTO) throws SerException {
        return currencySer.countCurren(currencyDTO);
    }

    @Override
    public CurrencyBO getOneById(String id) throws SerException {
        return currencySer.getOneById(id);
    }

    @Override
    public List<CurrencyBO> listCurren(CurrencyDTO currencyDTO) throws SerException {
        return currencySer.listCurren(currencyDTO);
    }

    @Override
    public CurrencyBO addCurren(CurrencyTO currencyTO) throws SerException {
        return currencySer.addCurren(currencyTO);
    }

    @Override
    public CurrencyBO editCurren(CurrencyTO currencyTO) throws SerException {
        return currencySer.editCurren(currencyTO);
    }

    @Override
    public void deleteCurren(String id) throws SerException {
        currencySer.deleteCurren(id);
    }
}