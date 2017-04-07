package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
import com.bjike.goddess.marketdevelopment.dto.MarketResearchDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketResearch;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 市场调研业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MarketResearchSerImpl extends ServiceImpl<MarketResearch, MarketResearchDTO> implements MarketResearchSer {


    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO save(MarketResearchTO to) throws SerException {
        MarketResearch entity = BeanTransform.copyProperties(to, MarketResearch.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MarketResearchBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO update(MarketResearchTO to) throws SerException {
        MarketResearch entity = BeanTransform.copyProperties(to, MarketResearch.class);
        super.update(entity);
        return BeanTransform.copyProperties(entity, MarketResearchBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO delete(MarketResearchTO to) throws SerException {
        MarketResearch entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, MarketResearchBO.class);
    }

    @Override
    public List<MarketResearchBO> findByType(String type) throws SerException {
        MarketResearchDTO dto = new MarketResearchDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketResearch> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketResearchBO.class);
    }

    @Override
    public List<MarketResearchBO> findByCourse(String course) throws SerException {
        MarketResearchDTO dto = new MarketResearchDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<MarketResearch> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketResearchBO.class);
    }

    @Override
    public List<MarketResearchBO> findByCourseType(String type, String course) throws SerException {
        MarketResearchDTO dto = new MarketResearchDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<MarketResearch> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, MarketResearchBO.class);
    }
}