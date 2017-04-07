package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MarketChannelBO;
import com.bjike.goddess.marketdevelopment.dto.MarketChannelDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketChannel;
import com.bjike.goddess.marketdevelopment.to.MarketChannelTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 市场挖掘业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:15 ]
 * @Description: [ 市场挖掘业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MarketChannelSerImpl extends ServiceImpl<MarketChannel, MarketChannelDTO> implements MarketChannelSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO save(MarketChannelTO to) throws SerException {
        MarketChannel entity = BeanTransform.copyProperties(to, MarketChannel.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MarketChannelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO update(MarketChannelTO to) throws SerException {
        MarketChannel entity = BeanTransform.copyProperties(to, MarketChannel.class);
        super.update(entity);
        return BeanTransform.copyProperties(entity, MarketChannelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketChannelBO delete(MarketChannelTO to) throws SerException {
        MarketChannel entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByType(String type) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByCourse(String course) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }

    @Override
    public List<MarketChannelBO> findByCourseType(String type, String course) throws SerException {
        MarketChannelDTO dto = new MarketChannelDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketChannel> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketChannelBO.class);
    }
}