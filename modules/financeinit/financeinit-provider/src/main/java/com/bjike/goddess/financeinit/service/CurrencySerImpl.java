package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设置币别业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CurrencySerImpl extends ServiceImpl<Currency, CurrencyDTO> implements CurrencySer {
    @Override
    public Long countCurren(CurrencyDTO currencyDTO) throws SerException {
        Long count = super.count(currencyDTO);
        return count;
    }

    @Override
    public CurrencyBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        Currency currency = super.findById(id);
        return BeanTransform.copyProperties(currency, CurrencyBO.class);
    }

    @Override
    public List<CurrencyBO> listCurren(CurrencyDTO currencyDTO) throws SerException {
        List<Currency> list = super.findByCis(currencyDTO, true);
        return BeanTransform.copyProperties(list, CurrencyBO.class);
    }

    @Override
    public CurrencyBO addCurren(CurrencyTO currencyTO) throws SerException {
        Currency currency = BeanTransform.copyProperties(currencyTO,Currency.class,true);
        currency.setCreateTime(LocalDateTime.now());
        super.save(currency);
        return BeanTransform.copyProperties(currency,CurrencyBO.class);
    }

    @Override
    public CurrencyBO editCurren(CurrencyTO currencyTO) throws SerException {
        Currency currency = super.findById(currencyTO.getId());
        LocalDateTime date = currency.getCreateTime();
        currency = BeanTransform.copyProperties(currencyTO,Currency.class);
        currency.setCreateTime(date);
        currency.setModifyTime(LocalDateTime.now());
        super.update(currency);
        return BeanTransform.copyProperties(currency,CurrencyBO.class);
    }

    @Override
    public void deleteCurren(String id) throws SerException {
        super.remove(id);
    }
}