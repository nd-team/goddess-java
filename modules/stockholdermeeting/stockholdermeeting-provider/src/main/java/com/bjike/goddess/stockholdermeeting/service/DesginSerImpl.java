package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.stockholdermeeting.bo.DesginBO;
import com.bjike.goddess.stockholdermeeting.dto.DesginDTO;
import com.bjike.goddess.stockholdermeeting.entity.Desgin;
import com.bjike.goddess.stockholdermeeting.to.DesginTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "stockholdermeetingSerCache")
@Service
public class DesginSerImpl extends ServiceImpl<Desgin, DesginDTO> implements DesginSer {
    @Override
    public Set<String> meetingTopics() throws SerException {
        Set<String> set = new HashSet<String>();
        List<Desgin> list = super.findAll();
        for (Desgin o : list) {
            set.add(o.getMeetingTopic());
        }
        return set;
    }

    @Override
    public Set<String> meetingLevels(String meetingTopic) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
            set.add(o.getMeetingLevel());
        }
        return set;
    }

    @Override
    public String topicContent(String meetingTopic) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        List<Desgin> list = super.findByCis(dto);
        for (Desgin o : list) {
            return o.getTopicContent();
        }
        return null;
    }

    @Override
    public String function(String meetingTopic) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        List<Desgin> list = super.findByCis(dto);
        for (Desgin o : list) {
            return o.getFunction();
        }
        return null;
    }

    @Override
    public String planTime(String meetingTopic, String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        List<Desgin> list = super.findByCis(dto);
        for (Desgin o : list) {
            return o.getPlanTime();
        }
        return null;
    }

    @Override
    public DesginBO findByID(String id) throws SerException {
        Desgin entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, DesginBO.class);
    }

    @Override
    public List<DesginBO> list(DesginDTO dto) throws SerException {
        List<Desgin> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, DesginBO.class);
    }

    @Override
    public Long countNum(DesginDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DesginBO save(DesginTO to) throws SerException {
        Desgin entity = BeanTransform.copyProperties(to, Desgin.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DesginBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(DesginTO to) throws SerException {
        Desgin entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Desgin.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }
}