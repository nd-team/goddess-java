package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.WarnBO;
import com.bjike.goddess.budget.dto.WarnDTO;
import com.bjike.goddess.budget.entity.Warn;
import com.bjike.goddess.budget.to.WarnTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 预警业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class WarnSerImpl extends ServiceImpl<Warn, WarnDTO> implements WarnSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public WarnBO save(WarnTO to) throws SerException {
        Warn warn = BeanTransform.copyProperties(to, Warn.class, true);
        super.save(warn);
        return BeanTransform.copyProperties(warn, WarnBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(WarnTO to) throws SerException {
        Warn warn = super.findById(to.getId());
        LocalDateTime a = warn.getCreateTime();
        warn = BeanTransform.copyProperties(to, Warn.class, true);
        warn.setCreateTime(a);
        warn.setModifyTime(LocalDateTime.now());
        super.update(warn);
    }

    @Override
    public List<WarnBO> list(WarnDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Warn> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WarnBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public WarnBO findByID(String id) throws SerException {
        Warn warn = super.findById(id);
        return BeanTransform.copyProperties(warn, WarnBO.class);
    }

    @Override
    public Long countNum(WarnDTO dto) throws SerException {
        return super.count(dto);
    }
}