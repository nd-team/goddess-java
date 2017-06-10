package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
import com.bjike.goddess.marketdevelopment.dto.MarketResearchDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketResearch;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String researchManage = "research-manage";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO save(MarketResearchTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(marketManage) && !marPermissionSer.getMarPermission(researchManage))
            throw new SerException("您的帐号没有权限");
        MarketResearch entity = BeanTransform.copyProperties(to, MarketResearch.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MarketResearchBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO update(MarketResearchTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(researchManage))
            throw new SerException("您的帐号没有权限");
        try {
            MarketResearch entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, MarketResearchBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketResearchBO delete(MarketResearchTO to) throws SerException {
        if (!marPermissionSer.getMarPermission(researchManage))
            throw new SerException("您的帐号没有权限");
        MarketResearch entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
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

    @Override
    public List<MarketResearch> findByPage(MarketResearchDTO dto) throws SerException {
        if (!marPermissionSer.getMarPermission(researchManage))
            throw new SerException("您的帐号没有权限");
        return super.findByPage(dto);
    }
}