package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 币别业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:53 ]
 * @Description: [ 币别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CurrencySerImpl extends ServiceImpl<Currency, CurrencyDTO> implements CurrencySer {

    @Override
    public List<CurrencyBO> listCurrency(CurrencyDTO currencyDTO) throws SerException {
        currencyDTO.getSorts().add("createTime=desc");
        List<Currency> list = super.findByCis(currencyDTO, true);

        return BeanTransform.copyProperties(list, CurrencyBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CurrencyBO addCurrency(CurrencyTO currencyTO) throws SerException {
        Currency currency = BeanTransform.copyProperties(currencyTO,Currency.class,true);
        currency.setCreateTime(LocalDateTime.now());
        super.save( currency );
        return BeanTransform.copyProperties(currency, CurrencyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CurrencyBO editCurrency(CurrencyTO currencyTO) throws SerException {
        Currency currency = BeanTransform.copyProperties(currencyTO,Currency.class,true);
        Currency dbCurrency = super.findById( currencyTO.getId() );

        dbCurrency.setCode( currency.getCode() );
        dbCurrency.setName( currency.getName() );
        dbCurrency.setRate( currency.getRate() );
        dbCurrency.setModifyTime(LocalDateTime.now());
        super.update( dbCurrency );
        return BeanTransform.copyProperties(currency, CurrencyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCurrency(String id) throws SerException {
        super.remove( id );
    }
}