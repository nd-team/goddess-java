package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.entity.MarketInfo;
import com.bjike.goddess.market.to.MarketInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场信息管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.568 ]
 * @Description: [ 市场信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketSerCache")
@Service
public class MarketInfoSerImpl extends ServiceImpl<MarketInfo, MarketInfoDTO> implements MarketInfoSer {

    @Cacheable
    @Override
    public List<MarketInfoBO> findListMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        List<MarketInfo> marketInfos = super.findByCis(marketInfoDTO, true);
        return BeanTransform.copyProperties(marketInfos, MarketInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoBO insertMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        MarketInfo marketInfo = BeanTransform.copyProperties(marketInfoTO, MarketInfo.class,true);
        try {
            //判断是否为有效信息
            if (marketInfo.getEffective()) {
                //判断是否为新项目
                if (marketInfo.getProjectNature().equals("")) {
                    marketInfo.setProjectNature("新项目");
                }
                marketInfo.setEffective(true);
            } else {
                marketInfo.setCreateTime(LocalDateTime.now());
                super.save(marketInfo);
            }
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(marketInfo, MarketInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoBO editMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
      /*  try {
            String customerNum = "";
            CustomerBaseInfoBO customerBaseInfoBO = customerBaseInfoAPI.getCustomerInfoByNum(customerNum);
            if (StringUtils.isNotEmpty(customerBaseInfoBO.getCustomerName())) {
                customerBaseInfoAPI.generateCustomerNum();
            } else if (StringUtils.isNotEmpty(customerBaseInfoBO.getCustomerNum())) {
                customerBaseInfoAPI.addMarketCustomerInfo(customerBaseInfoBO.getCustomerName(), customerBaseInfoBO.getOriganizion());
            } else {*/
        if (!StringUtils.isEmpty(marketInfoTO.getId())) {
            MarketInfo marketInfo = super.findById(marketInfoTO.getId());
            BeanTransform.copyProperties(marketInfoTO, marketInfo, true);
            marketInfo.setModifyTime(LocalDateTime.now());
            super.update(marketInfo);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(marketInfoTO, MarketInfoBO.class);
        /*    }
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
*/
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMarketInfo(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String customerName) throws SerException {
        //TODO: xiazhili 2017-03-21 未做导出
        return null;
    }

}