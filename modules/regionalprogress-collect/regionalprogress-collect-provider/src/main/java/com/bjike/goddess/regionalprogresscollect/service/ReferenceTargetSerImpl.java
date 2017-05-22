package com.bjike.goddess.regionalprogresscollect.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regionalprogresscollect.bo.ReferenceTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.ReferenceTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.ReferenceTarget;
import com.bjike.goddess.regionalprogresscollect.to.FindTO;
import com.bjike.goddess.regionalprogresscollect.to.ReferenceTargetTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regionalprogresscollectSerCache")
@Service
public class ReferenceTargetSerImpl extends ServiceImpl<ReferenceTarget, ReferenceTargetDTO> implements ReferenceTargetSer {

    @Override
    public ReferenceTargetBO save(ReferenceTargetTO to) throws SerException {
        ReferenceTarget entity = BeanTransform.copyProperties(to, ReferenceTarget.class);
        this.countTarget(entity);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    private void countTarget(ReferenceTarget entity) throws SerException {
        LocalDate time;
        if (entity.getMonth() > 12)
            throw new SerException("月份有误");
        if (entity.getMonth() >= 10) {
            time = LocalDate.parse(String.format("%d-%d-01", entity.getYear(), entity.getMonth()));
        } else {
            time = LocalDate.parse(String.format("%d-0%d-01", entity.getYear(), entity.getMonth()));
        }
        entity.setWeekArtificial(this.decimal(entity.getMonthArtificial() / 4));
        entity.setWeekTask(this.decimal(entity.getMonthTask() / 4));
        entity.setDayArtificial(this.decimal(entity.getMonthArtificial() / time.getMonth().maxLength()));
        entity.setDayTask(this.decimal(entity.getMonthTask() / time.getMonth().maxLength()));
    }

    private double decimal(double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public ReferenceTargetBO update(ReferenceTargetTO to) throws SerException {
        ReferenceTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("概数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countTarget(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public ReferenceTargetBO delete(String id) throws SerException {
        ReferenceTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("概数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public ReferenceTargetBO getById(String id) throws SerException {
        ReferenceTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("概数据不存在");
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public List<ReferenceTargetBO> maps(ReferenceTargetDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("type=asc");
        dto.getSorts().add("node=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ReferenceTargetBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ReferenceTargetDTO dto = new ReferenceTargetDTO();
        return super.count(dto);
    }

    @Override
    public ReferenceTargetBO findByTO(FindTO to) throws SerException {
        List<ReferenceTargetBO> list = this.findListByTO(to);
        if (null != list && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<ReferenceTargetBO> findListByTO(FindTO to) throws SerException {
        ReferenceTargetDTO dto = new ReferenceTargetDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getNode()))
            dto.getConditions().add(Restrict.eq("node", to.getNode()));
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        if (null != to.getMonth())
            dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        if (null != to.getYear())
            dto.getConditions().add(Restrict.eq("year", to.getYear()));
        List<ReferenceTarget> list = super.findByCis(dto);
        if (null != list)
            return BeanTransform.copyProperties(list, ReferenceTargetBO.class);
        else
            return new ArrayList<>(0);
    }
}