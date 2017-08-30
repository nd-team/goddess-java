package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffing.bo.PlanSonBO;
import com.bjike.goddess.staffing.dto.PlanSonDTO;
import com.bjike.goddess.staffing.entity.PlanSon;
import com.bjike.goddess.staffing.to.PlanSonTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人数配置-计划子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:58 ]
 * @Description: [ 人数配置-计划子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class PlanSonSerImpl extends ServiceImpl<PlanSon, PlanSonDTO> implements PlanSonSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public PlanSonBO save(PlanSonTO to) throws SerException {
        PlanSon entity = BeanTransform.copyProperties(to, PlanSon.class, true);
        return BeanTransform.copyProperties(entity, PlanSonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(PlanSonTO to) throws SerException {
        PlanSon entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, PlanSon.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<PlanSonBO> list(PlanSonDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<PlanSon> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, PlanSonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        PlanSon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public PlanSonBO findByID(String id) throws SerException {
        PlanSon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, PlanSonBO.class);
    }

    @Override
    public Long count(PlanSonDTO dto) throws SerException {
        return super.count(dto);
    }
}