package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.TimeSetBO;
import com.bjike.goddess.taskallotment.dto.TimeSetDTO;
import com.bjike.goddess.taskallotment.entity.TimeSet;
import com.bjike.goddess.taskallotment.enums.Spacing;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.to.TimeSetTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 标准工时设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class TimeSetSerImpl extends ServiceImpl<TimeSet, TimeSetDTO> implements TimeSetSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TimeSetBO save(TimeSetTO to) throws SerException {
        String name=userAPI.currentUser().getUsername();
        TimeSet entity = BeanTransform.copyProperties(to, TimeSet.class, true);
        entity.setName(name);
        entity.setTime(LocalDate.now());
        Spacing spacing=entity.getSpacing();
        LocalDateTime lastTime=entity.getLastTime();
        Integer remind=entity.getRemind();
        switch (spacing){
            case MINTUE:
                lastTime=entity.getRemindTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime=entity.getRemindTime().minusHours(remind);
                break;
            case DAY:
                lastTime=entity.getRemindTime().minusDays(remind);
                break;
            case WEEK:
                lastTime=entity.getRemindTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime=entity.getRemindTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        super.save(entity);
        return BeanTransform.copyProperties(entity, TimeSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TimeSetTO to) throws SerException {
        TimeSet entity = super.findById(to.getId());
        TimeSet timeSet = BeanTransform.copyProperties(to, TimeSet.class, true);
        BeanUtils.copyProperties(timeSet,entity,"name","time","createTime","id");
        Spacing spacing=entity.getSpacing();
        LocalDateTime lastTime=entity.getLastTime();
        Integer remind=entity.getRemind();
        switch (spacing){
            case MINTUE:
                lastTime=entity.getRemindTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime=entity.getRemindTime().minusHours(remind);
                break;
            case DAY:
                lastTime=entity.getRemindTime().minusDays(remind);
                break;
            case WEEK:
                lastTime=entity.getRemindTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime=entity.getRemindTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<TimeSetBO> list(TimeSetDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<TimeSet> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, TimeSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        TimeSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public TimeSetBO findByID(String id) throws SerException {
        TimeSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, TimeSetBO.class);
    }

    @Override
    public Long count(TimeSetDTO dto) throws SerException {
        return super.count(dto);
    }
}