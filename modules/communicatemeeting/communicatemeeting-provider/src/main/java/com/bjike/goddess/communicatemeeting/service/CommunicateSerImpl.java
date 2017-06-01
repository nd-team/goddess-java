package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.communicatemeeting.bo.CommunicateBO;
import com.bjike.goddess.communicatemeeting.dto.CommunicateDTO;
import com.bjike.goddess.communicatemeeting.entity.Communicate;
import com.bjike.goddess.communicatemeeting.to.CommunicateTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交流讨论业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "communicatemeetingSerCache")
@Service
public class CommunicateSerImpl extends ServiceImpl<Communicate, CommunicateDTO> implements CommunicateSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CommunicateBO oneRound(CommunicateTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Communicate entity = BeanTransform.copyProperties(to, Communicate.class, true);
        entity.setName(name);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommunicateBO.class);
    }

    @Override
    public List<CommunicateBO> twoRound(CommunicateDTO dto) throws SerException {
        List<Communicate> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CommunicateBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void replenish(CommunicateTO to) throws SerException {
        String name=userAPI.currentUser().getUsername();
        Communicate entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        if (!name.equals(entity.getName())){
            throw new SerException("只能补充自己的一轮意见");
        }
        entity.setOpinion(to.getOpinion());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void vote(String id) throws SerException {
        Communicate entity = super.findById(id);
        synchronized (new Object()) {
            if (entity == null) {
                throw new SerException("该对象不存在");
            }
            Integer num = entity.getNum();
            if (num == null) {
                entity.setNum(1);
            } else {
                entity.setNum(entity.getNum() + 1);
            }
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
    }

    @Override
    public CommunicateBO findByID(String id) throws SerException {
        Communicate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, CommunicateBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<CommunicateBO> showVote(CommunicateDTO dto) throws SerException {
        CommunicateDTO dto1 = new CommunicateDTO();
        dto1.getConditions().add(Restrict.isNull("num"));
        List<Communicate> nulls = super.findByCis(dto1);
        for (Communicate c : nulls) {
            c.setNum(0);
            super.update(c);
        }
        dto.getSorts().add("num=desc");
        List<Communicate> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CommunicateBO.class);
    }

    @Override
    public Long countNum(CommunicateDTO dto) throws SerException {
        return super.count(dto);
    }
}