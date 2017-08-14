package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.bo.AuswerBO;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.entity.Auswer;
import com.bjike.goddess.system.to.AuswerTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 答案业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class AuswerSerImpl extends ServiceImpl<Auswer, AuswerDTO> implements AuswerSer {
    @Override
    public Long count(AuswerDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public AuswerBO getOne(String id) throws SerException {
        Auswer auswer = super.findById(id);
        return BeanTransform.copyProperties(auswer, AuswerBO.class);
    }

    @Override
    public List<AuswerBO> list(AuswerDTO dto) throws SerException {
        List<Auswer> auswers = super.findByCis(dto);
        List<AuswerBO> auswerBOS = BeanTransform.copyProperties(auswers, AuswerBO.class);
        return auswerBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuswerBO insert(AuswerTO to) throws SerException {
        Auswer auswer = BeanTransform.copyProperties(to, Auswer.class, true);
        auswer.setCreateTime(LocalDateTime.now());
        super.save(auswer);
        return BeanTransform.copyProperties(auswer, AuswerBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuswerBO edit(AuswerTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            Auswer auswer = super.findById(to.getId());
            BeanTransform.copyProperties(to,auswer,true);
            auswer.setModifyTime(LocalDateTime.now());
            super.update(auswer);
            return BeanTransform.copyProperties(auswer,AuswerBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }
}