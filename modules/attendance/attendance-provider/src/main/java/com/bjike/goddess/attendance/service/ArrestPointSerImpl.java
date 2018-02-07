package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.ArrestPointBO;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.entity.ArrestPoint;
import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.enums.Week;
import com.bjike.goddess.attendance.to.ArrestPointTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 驻点设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class ArrestPointSerImpl extends ServiceImpl<ArrestPoint, ArrestPointDTO> implements ArrestPointSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ArrestPointBO save(ArrestPointTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        ArrestPoint entity = BeanTransform.copyProperties(to, ArrestPoint.class, true);
        Week[] workDays = to.getWorkDays();
        if (null != to.getWeek() && to.getWeek()) {
            entity.setWorkDay("大小周（8:30-18:00）");
        } else {
            boolean flag = false;
            for (int i = 0; i < workDays.length; i++) {
                if (i != workDays[i].getCode()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                entity.setWorkDay("周一到" + workDays[workDays.length - 1].toString() + "（8:30-18:00）");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < workDays.length; i++) {
                    if (i == workDays.length - 1) {
                        sb.append(workDays[i].toString() + "（8:30-18:00）");
                    } else {
                        sb.append(workDays[i].toString() + "、");
                    }
                }
                entity.setWorkDay(sb.toString());
            }
        }
        entity.setName(name);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ArrestPointBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ArrestPointTO to) throws SerException {
        ArrestPoint entity = super.findById(to.getId());
        ArrestPoint arrestPoint = BeanTransform.copyProperties(to, ArrestPoint.class, true);
        BeanUtils.copyProperties(arrestPoint, entity, "id", "name", "createTime");
        Week[] workDays = to.getWorkDays();
        if (null != to.getWeek()) {
            entity.setWorkDay("大小周（8:30-18:00）");
        } else {
            boolean flag = false;
            for (int i = 0; i < workDays.length; i++) {
                if (i != workDays[i].getCode()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                entity.setWorkDay("周一到" + workDays[workDays.length - 1].toString() + "（8:30-18:00）");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < workDays.length; i++) {
                    if (i == workDays.length - 1) {
                        sb.append(workDays[i].toString() + "（8:30-18:00）");
                    } else {
                        sb.append(workDays[i].toString() + "、");
                    }
                }
                entity.setWorkDay(sb.toString());
            }
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ArrestPointBO> list(ArrestPointDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        String[] pointAreas = dto.getPointAreas();
        if (null != pointAreas) {
            dto.getConditions().add(Restrict.in("pointArea", pointAreas));
        }
        List<ArrestPoint> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ArrestPointBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        ArrestPoint entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ArrestPointBO findByID(String id) throws SerException {
        ArrestPoint entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ArrestPointBO.class);
    }

    @Override
    public Long count(ArrestPointDTO dto) throws SerException {
        String[] pointAreas = dto.getPointAreas();
        if (null != pointAreas) {
            dto.getConditions().add(Restrict.in("pointArea", pointAreas));
        }
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void start(String id) throws SerException {
        ArrestPoint entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setStatus(Status.START);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void stop(String id) throws SerException {
        ArrestPoint entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setStatus(Status.STOP);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public Set<String> pointAreas() throws SerException {
        List<ArrestPoint> list = super.findAll();
        return list.stream().map(arrestPoint -> arrestPoint.getPointArea()).collect(Collectors.toSet());
    }
}