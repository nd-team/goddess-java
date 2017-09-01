package com.bjike.goddess.event.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.event.bo.TimeSetBO;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.entity.TimeSet;
import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.enums.Status;
import com.bjike.goddess.event.to.TimeSetTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 提醒间隔时间设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eventSerCache")
@Service
public class TimeSetSerImpl extends ServiceImpl<TimeSet, TimeSetDTO> implements TimeSetSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TimeSetTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Permissions permissions = to.getPermissions();
        TimeSet timeSet = find(name, permissions);
        if (null == timeSet) {
            TimeSet entity = BeanTransform.copyProperties(to, TimeSet.class, true);
            entity.setName(name);
            LocalDateTime setTime = entity.getSetTime();
            LocalDateTime lastTime = null;
            int interval = entity.getInterval();
            switch (entity.getIntervalType()) {
                case DAY:
                    lastTime = setTime.minusDays(interval);
                    break;
                case HOUR:
                    lastTime = setTime.minusHours(interval);
                    break;
                case MINUTE:
                    lastTime = setTime.minusMinutes(interval);
                    break;
            }
            entity.setLastTime(lastTime);
            entity.setStatus(Status.NORMAL);
            super.save(entity);
        } else {
            String id = timeSet.getId();
            LocalDateTime a = timeSet.getCreateTime();
            TimeSet entity = BeanTransform.copyProperties(to, TimeSet.class, true);
            LocalDateTime setTime = entity.getSetTime();
            LocalDateTime lastTime = null;
            Integer interval = entity.getInterval();
            if (interval != null && null != entity.getIntervalType() && null != setTime) {
                switch (entity.getIntervalType()) {
                    case DAY:
                        lastTime = setTime.minusDays(interval);
                        break;
                    case HOUR:
                        lastTime = setTime.minusHours(interval);
                        break;
                    case MINUTE:
                        lastTime = setTime.minusMinutes(interval);
                        break;
                }
            }
            entity.setName(name);
            entity.setLastTime(lastTime);
            entity.setStatus(Status.NORMAL);
            entity.setId(id);
            entity.setCreateTime(a);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
    }

    private TimeSet find(String name, Permissions permissions) throws SerException {
        TimeSetDTO dto = new TimeSetDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("permissions", permissions.getValue()));
        return super.findOne(dto);
    }

    @Override
    public TimeSetBO timeSet(TimeSetDTO dto) throws SerException {
        String name = dto.getName();
        Permissions permissions = dto.getPermissions();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("permissions", permissions.getValue()));
        TimeSet timeSet = super.findOne(dto);
        if (null != timeSet) {
            IntervalType intervalType = timeSet.getIntervalType();
            Integer interval = timeSet.getInterval();
            String intervalTime = null;
            if ((null != intervalType) && (null != interval)) {
                switch (intervalType) {
                    case DAY:
                        intervalTime = "每" + interval + "天";
                        break;
                    case HOUR:
                        intervalTime = "每" + interval + "小时";
                        break;
                    case MINUTE:
                        intervalTime = "每" + interval + "分钟";
                        break;
                }
            }
            TimeSetBO bo = BeanTransform.copyProperties(timeSet, TimeSetBO.class);
            bo.setIntervalTime(intervalTime);
            return bo;
        }
        TimeSetBO bo=new TimeSetBO();
        bo.setPermissions(permissions);
        return bo;
    }

    @Override
    public TimeSetBO timeSet(String name, Permissions permissions) throws SerException {
        TimeSetDTO dto = new TimeSetDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("permissions", permissions.getValue()));
        TimeSet timeSet = super.findOne(dto);
        if (null != timeSet) {
            IntervalType intervalType = timeSet.getIntervalType();
            Integer interval = timeSet.getInterval();
            String intervalTime = null;
            if ((null != intervalType) && (null != interval)) {
                switch (intervalType) {
                    case DAY:
                        intervalTime = "每" + interval + "天";
                        break;
                    case HOUR:
                        intervalTime = "每" + interval + "小时";
                        break;
                    case MINUTE:
                        intervalTime = "每" + interval + "分钟";
                        break;
                }
            }
            TimeSetBO bo = BeanTransform.copyProperties(timeSet, TimeSetBO.class);
            bo.setIntervalTime(intervalTime);
            return bo;
        }
        return null;
    }

    @Override
    public List<TimeSetBO> list(TimeSetDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        UserBO userBO = userAPI.findByUsername(name);
        List<PositionDetailBO> positionDetailBOs = null;
        if (userBO != null) {
            positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        }
        dto.getConditions().add(Restrict.eq("name", name));
        List<TimeSet> list = super.findByCis(dto);
        List<TimeSetBO> bos = new ArrayList<>();
        if (list.isEmpty()) {
            for (Permissions permissions : permissionses()) {
                TimeSetBO bo = new TimeSetBO();
                if (!positionDetailBOs.isEmpty()) {
                    bo.setArea(positionDetailBOs.get(0).getArea());
                    bo.setDepart(positionDetailBOs.get(0).getDepartmentName());
                    bo.setModule(positionDetailBOs.get(0).getModuleName());
                }
                bo.setName(name);
                bo.setPermissions(permissions);
                bo.setStatus(Status.NORMAL);
                bos.add(bo);
            }
            return bos;
        } else {
            List<Permissions> permissionses = new ArrayList<>();
            List<Permissions> all = permissionses();
            for (TimeSet timeSet : list) {
                TimeSetBO timeSetBO = BeanTransform.copyProperties(timeSet, TimeSetBO.class);
                IntervalType intervalType = timeSet.getIntervalType();
                Integer interval = timeSet.getInterval();
                String intervalTime = null;
                if ((null != intervalType) && (null != interval)) {
                    switch (intervalType) {
                        case DAY:
                            intervalTime = "每" + interval + "天";
                            break;
                        case HOUR:
                            intervalTime = "每" + interval + "小时";
                            break;
                        case MINUTE:
                            intervalTime = "每" + interval + "分钟";
                            break;
                    }
                }
                timeSetBO.setIntervalTime(intervalTime);
                if (positionDetailBOs != null && !positionDetailBOs.isEmpty()) {
                    timeSetBO.setArea(positionDetailBOs.get(0).getArea());
                    timeSetBO.setDepart(positionDetailBOs.get(0).getDepartmentName());
                    timeSetBO.setModule(positionDetailBOs.get(0).getModuleName());
                }
                bos.add(timeSetBO);
                permissionses.add(timeSet.getPermissions());
            }
            if (all.removeAll(permissionses)) {
                for (Permissions permissions : all) {
                    TimeSetBO bo = new TimeSetBO();
                    if (positionDetailBOs != null && !positionDetailBOs.isEmpty()) {
                        bo.setArea(positionDetailBOs.get(0).getArea());
                        bo.setDepart(positionDetailBOs.get(0).getDepartmentName());
                        bo.setModule(positionDetailBOs.get(0).getModuleName());
                    }
                    bo.setName(name);
                    bo.setPermissions(permissions);
                    bo.setStatus(Status.NORMAL);
                    bos.add(bo);
                }
            }
            return bos;
        }
    }

    private List<Permissions> permissionses() throws SerException {
        List<Permissions> list = new ArrayList<>();
        list.add(Permissions.SEE);
        list.add(Permissions.MAKE);
        list.add(Permissions.ADUIT);
        list.add(Permissions.CONFIRM);
        list.add(Permissions.CHECK);
        list.add(Permissions.ANALYZE);
        return list;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void freeze(TimeSetDTO dto) throws SerException {
        TimeSet timeSet = find(dto.getName(), dto.getPermissions());
        if (null == timeSet) {
            TimeSet entity = new TimeSet();
            entity.setName(dto.getName());
            entity.setPermissions(dto.getPermissions());
            entity.setStatus(Status.FREEZE);
            super.save(entity);
        } else {
            timeSet.setStatus(Status.FREEZE);
            timeSet.setModifyTime(LocalDateTime.now());
            super.update(timeSet);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void thaw(TimeSetDTO dto) throws SerException {
        TimeSet timeSet = find(dto.getName(), dto.getPermissions());
        timeSet.setStatus(Status.NORMAL);
        timeSet.setModifyTime(LocalDateTime.now());
        super.update(timeSet);
    }

}