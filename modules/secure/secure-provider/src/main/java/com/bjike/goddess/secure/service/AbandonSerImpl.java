package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.entity.Abandon;
import com.bjike.goddess.secure.to.AbandonTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 放弃购买名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AbandonSerImpl extends ServiceImpl<Abandon, AbandonDTO> implements AbandonSer {
    @Override
    @Transactional
    public AbandonBO save(AbandonTO to) throws SerException {
        Abandon abandon= BeanTransform.copyProperties(to,Abandon.class,true);
        super.save(abandon);
        return BeanTransform.copyProperties(abandon,AbandonBO.class);
    }

    @Override
    @Transactional
    public AbandonBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    @Transactional
    public AbandonBO edit(AbandonTO to) throws SerException {
        Abandon abandon= super.findById(to.getId());
        abandon=BeanTransform.copyProperties(to,Abandon.class,true);
        super.update(abandon);
        return BeanTransform.copyProperties(abandon,AbandonBO.class);
    }

    @Override
    public List<AbandonBO> find(AbandonDTO dto) throws SerException {
        List<Abandon> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,AbandonBO.class);
    }

    @Override
    public AbandonBO findByID(String id) throws SerException {
        Abandon abandon=super.findById(id);
        return BeanTransform.copyProperties(abandon,AbandonBO.class);
    }

    @Override
    public List<AbandonBO> findALL() throws SerException {
        List<Abandon> list=super.findAll();
        return BeanTransform.copyProperties(list,AbandonBO.class);
    }
}