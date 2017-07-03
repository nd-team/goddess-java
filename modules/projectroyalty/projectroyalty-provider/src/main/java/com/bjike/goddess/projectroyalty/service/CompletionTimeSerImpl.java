package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.CompletionTimeBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.CompletionTimeDTO;
import com.bjike.goddess.projectroyalty.entity.CompletionTime;
import com.bjike.goddess.projectroyalty.to.CompletionTimeTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 完工时间业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:44 ]
 * @Description: [ 完工时间业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class CompletionTimeSerImpl extends ServiceImpl<CompletionTime, CompletionTimeDTO> implements CompletionTimeSer {

    @Override
    public CompletionTimeBO save(CompletionTimeTO to) throws SerException {
        CompletionTime entity = BeanTransform.copyProperties(to, CompletionTime.class);
        CompletionTimeDTO dto = new CompletionTimeDTO();
        dto.getConditions().add(Restrict.eq("completion", to.getCompletion()));
        if (super.count(dto) != 0)
            throw new SerException(to.getCompletion() + ":已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, CompletionTimeBO.class);
    }

    @Override
    public CompletionTimeBO update(CompletionTimeTO to) throws SerException {
        CompletionTime entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (to.getCompletion().doubleValue() != entity.getCompletion().doubleValue()) {
            CompletionTimeDTO dto = new CompletionTimeDTO();
            dto.getConditions().add(Restrict.eq("completion", to.getCompletion()));
            if (super.count(dto) != 0)
                throw new SerException(to.getCompletion() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CompletionTimeBO.class);
    }

    @Override
    public CompletionTimeBO delete(String id) throws SerException {
        CompletionTime entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CompletionTimeBO.class);
    }

    @Override
    public CompletionTimeBO getById(String id) throws SerException {
        CompletionTime entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, CompletionTimeBO.class);
    }

    @Override
    public List<CompletionTimeBO> maps(CompletionTimeDTO dto) throws SerException {
        dto.getSorts().add("completion=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), CompletionTimeBO.class);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        List<OpinionBO> bos = new ArrayList<>(0);
        List<CompletionTime> list = super.findAll();
        for (CompletionTime entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getCompletion().toString()));
        return bos;
    }

    @Override
    public Long getTotal() throws SerException {
        CompletionTimeDTO dto = new CompletionTimeDTO();
        return super.count(dto);
    }
}