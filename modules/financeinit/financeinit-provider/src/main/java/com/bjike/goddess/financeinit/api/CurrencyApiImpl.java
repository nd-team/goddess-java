package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.service.CurrencySer;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 币别业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:53 ]
 * @Description: [ 币别业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("currencyApiImpl")
public class CurrencyApiImpl implements CurrencyAPI {

    @Autowired
    private CurrencySer currencySer;

    @Override
    public List<CurrencyBO> listCurrency(CurrencyDTO currencyDTO) throws SerException {
        return currencySer.listCurrency(currencyDTO);
    }

    @Override
    public CurrencyBO addCurrency(CurrencyTO currencyTO) throws SerException {
        return currencySer.addCurrency(currencyTO);
    }

    @Override
    public CurrencyBO editCurrency(CurrencyTO currencyTO) throws SerException {
        return currencySer.editCurrency(currencyTO);
    }

    @Override
    public void deleteCurrency(String id) throws SerException {
        currencySer.deleteCurrency(id);
    }
}