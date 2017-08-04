package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffing.bo.ActualSonBO;
import com.bjike.goddess.staffing.dto.ActualSonDTO;
import com.bjike.goddess.staffing.entity.ActualSon;
import com.bjike.goddess.staffing.to.ActualSonTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人数配置-实际子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:19 ]
 * @Description: [ 人数配置-实际子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class ActualSonSerImpl extends ServiceImpl<ActualSon, ActualSonDTO> implements ActualSonSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ActualSonBO save(ActualSonTO to) throws SerException {
        ActualSon entity = BeanTransform.copyProperties(to, ActualSon.class, true);
        return BeanTransform.copyProperties(entity, ActualSonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ActualSonTO to) throws SerException {
        ActualSon entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ActualSon.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ActualSonBO> list(ActualSonDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ActualSon> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ActualSonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        ActualSon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ActualSonBO findByID(String id) throws SerException {
        ActualSon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ActualSonBO.class);
    }

    @Override
    public Long count(ActualSonDTO dto) throws SerException {
        return super.count(dto);
    }
}