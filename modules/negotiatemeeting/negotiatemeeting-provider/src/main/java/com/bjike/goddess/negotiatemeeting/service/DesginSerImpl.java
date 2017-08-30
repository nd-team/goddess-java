package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.negotiatemeeting.bo.DesginBO;
import com.bjike.goddess.negotiatemeeting.dto.DesginDTO;
import com.bjike.goddess.negotiatemeeting.entity.Desgin;
import com.bjike.goddess.negotiatemeeting.enums.PlanJobStatus;
import com.bjike.goddess.negotiatemeeting.enums.PlanTimeStatus;
import com.bjike.goddess.negotiatemeeting.to.DesginTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 协商会议组织内容设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 05:39 ]
 * @Description: [ 协商会议组织内容设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
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
    public String content(String meetingTopic,String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
           return o.getContent();
        }
        return null;
    }

    @Override
    public Set<String> normalPlanJobs(String meetingTopic, String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        dto.getConditions().add(Restrict.eq("planJobStatus", PlanJobStatus.NORMAL));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
            set.add(o.getPlanJob());
        }
        return set;
    }

    @Override
    public Set<String> normalPlanTimes(String meetingTopic, String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        dto.getConditions().add(Restrict.eq("planTimeStatus", PlanTimeStatus.NORMAL));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
            set.add(o.getPlanTime());
        }
        return set;
    }

    @Override
    public Set<String> freezePlanJobs(String meetingTopic, String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        dto.getConditions().add(Restrict.eq("planJobStatus", PlanJobStatus.FREEZE));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
            set.add(o.getPlanJob());
        }
        return set;
    }

    @Override
    public Set<String> freezePlanTimes(String meetingTopic, String meetingLevel) throws SerException {
        DesginDTO dto = new DesginDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic", meetingTopic));
        dto.getConditions().add(Restrict.eq("meetingLevel", meetingLevel));
        dto.getConditions().add(Restrict.eq("planTimeStatus", PlanTimeStatus.FREEZE));
        List<Desgin> list = super.findByCis(dto);
        Set<String> set = new HashSet<String>();
        for (Desgin o : list) {
            set.add(o.getPlanTime());
        }
        return set;
    }

    @Override
    public void freezeJob(String id) throws SerException {
        Desgin entity = super.findById(id);
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanJobStatus(PlanJobStatus.FREEZE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thawJob(String id) throws SerException {
        Desgin entity = super.findById(id);
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanJobStatus(PlanJobStatus.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void editJob(DesginTO to) throws SerException {
        Desgin entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanJob(to.getPlanJob());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void freezeTime(String id) throws SerException {
        Desgin entity = super.findById(id);
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanTimeStatus(PlanTimeStatus.FREEZE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thawTime(String id) throws SerException {
        Desgin entity = super.findById(id);
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanTimeStatus(PlanTimeStatus.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void editTime(DesginTO to) throws SerException {
        Desgin entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        entity.setPlanTime(to.getPlanTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
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
    public DesginBO save(DesginTO to) throws SerException {
        Desgin entity = BeanTransform.copyProperties(to, Desgin.class, true);
        entity.setPlanJobStatus(PlanJobStatus.NORMAL);
        entity.setPlanTimeStatus(PlanTimeStatus.NORMAL);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DesginBO.class);
    }

    @Override
    public void edit(DesginTO to) throws SerException {
        Desgin entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Desgin.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        entity.setPlanJobStatus(PlanJobStatus.NORMAL);
        entity.setPlanTimeStatus(PlanTimeStatus.NORMAL);
        super.update(entity);
    }
}