package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.AutoPunchBO;
import com.bjike.goddess.attendance.dto.AutoPunchDTO;
import com.bjike.goddess.attendance.entity.AutoPunch;
import com.bjike.goddess.attendance.to.AutoPunchTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 自动打卡业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class AutoPunchSerImpl extends ServiceImpl<AutoPunch, AutoPunchDTO> implements AutoPunchSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void start(AutoPunchTO to) throws SerException {
        check(to);
        String name = userAPI.currentUser().getUsername();
        AutoPunchDTO dto = new AutoPunchDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        AutoPunch entity = super.findOne(dto);
        if (null != entity) {
            entity.setEnable(true);
            entity.setGoTime(to.getGoTime());
            entity.setOutTime(to.getOutTime());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            entity = new AutoPunch();
            entity.setName(name);
            entity.setEnable(true);
            entity.setGoTime(to.getGoTime());
            entity.setOutTime(to.getOutTime());
            super.save(entity);
        }
    }

    @Override
    public Boolean currentStauts() throws SerException {
        String name = userAPI.currentUser().getUsername();
        AutoPunchDTO dto = new AutoPunchDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        AutoPunch entity = super.findOne(dto);
        if (null == entity) {
            return false;
        }
        return entity.getEnable();
    }

    private void check(AutoPunchTO to) throws SerException {
        String s = DateUtil.dateToString(LocalDate.now());
        LocalDateTime go = DateUtil.parseDateTime(s + " " + to.getGoTime());
        LocalDateTime out = DateUtil.parseDateTime(s + " " + to.getOutTime());
        LocalDateTime go1 = DateUtil.parseDateTime(s + " 08:30:00");
        LocalDateTime out1 = DateUtil.parseDateTime(s + " 18:00:00");
        if (DateUtil.mis(go, go1) > 0) {
            throw new SerException("你设置的上班时间为" + to.getGoTime() + "，正常的上班时间为8:30");
        }
        if (DateUtil.mis(out, out1) < 0) {
            throw new SerException("你设置的下班时间为" + to.getOutTime() + "，正常的下班时间为18:00");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void close() throws SerException {
        String name = userAPI.currentUser().getUsername();
        AutoPunchDTO dto = new AutoPunchDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        AutoPunch entity = super.findOne(dto);
        if (null != entity) {
            entity.setEnable(false);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            entity = new AutoPunch();
            entity.setName(name);
            entity.setEnable(false);
            super.save(entity);
        }
    }

    @Override
    public AutoPunchBO get() throws SerException {
        String name = userAPI.currentUser().getUsername();
        AutoPunchDTO dto = new AutoPunchDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        AutoPunch entity = super.findOne(dto);
        if (null != entity) {
            return BeanTransform.copyProperties(entity, AutoPunchBO.class);
        }
        return null;
    }
}