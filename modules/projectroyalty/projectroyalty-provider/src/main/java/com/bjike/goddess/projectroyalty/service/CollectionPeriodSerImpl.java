package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.CollectionPeriodBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.CollectionPeriodDTO;
import com.bjike.goddess.projectroyalty.entity.CollectionPeriod;
import com.bjike.goddess.projectroyalty.to.CollectionPeriodTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 回款周期业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class CollectionPeriodSerImpl extends ServiceImpl<CollectionPeriod, CollectionPeriodDTO> implements CollectionPeriodSer {

    @Override
    public CollectionPeriodBO save(CollectionPeriodTO to) throws SerException {
        CollectionPeriod entity = BeanTransform.copyProperties(to, CollectionPeriod.class);
        CollectionPeriodDTO dto = new CollectionPeriodDTO();
        dto.getConditions().add(Restrict.eq("collection", to.getCollection()));
        if (super.count(dto) != 0)
            throw new SerException(to.getCollection() + ":已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, CollectionPeriodBO.class);
    }

    @Override
    public CollectionPeriodBO update(CollectionPeriodTO to) throws SerException {
        CollectionPeriod entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (to.getCollection().doubleValue() != entity.getCollection().doubleValue()) {
            CollectionPeriodDTO dto = new CollectionPeriodDTO();
            dto.getConditions().add(Restrict.eq("collection", to.getCollection()));
            if (super.count(dto) != 0)
                throw new SerException(to.getCollection() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CollectionPeriodBO.class);
    }

    @Override
    public CollectionPeriodBO delete(String id) throws SerException {
        CollectionPeriod entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CollectionPeriodBO.class);
    }

    @Override
    public CollectionPeriodBO getById(String id) throws SerException {
        CollectionPeriod entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, CollectionPeriodBO.class);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        List<OpinionBO> bos = new ArrayList<>(0);
        List<CollectionPeriod> list = super.findAll();
        for (CollectionPeriod entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getCollection().toString()));
        return bos;
    }

    @Override
    public List<CollectionPeriodBO> maps(CollectionPeriodDTO dto) throws SerException {
        dto.getSorts().add("collection=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), CollectionPeriodBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CollectionPeriodDTO dto = new CollectionPeriodDTO();
        return super.count(dto);
    }
}