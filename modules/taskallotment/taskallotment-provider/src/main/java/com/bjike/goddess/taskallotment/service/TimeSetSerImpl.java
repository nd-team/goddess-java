package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TimeSetBO save(TimeSetTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        TimeSet entity = BeanTransform.copyProperties(to, TimeSet.class, true);
        long mis = DateUtil.mis(entity.getRemindTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("提醒时间必须大于等于当前时间");
        }
        entity.setName(name);
        entity.setTime(LocalDate.now());
        Spacing spacing = entity.getSpacing();
        LocalDateTime lastTime = null;
        Integer remind = entity.getRemind();
        if (null != remind && null != spacing) {
            switch (spacing) {
                case MINTUE:
                    lastTime = entity.getRemindTime().minusMinutes(remind);
                    break;
                case HOUR:
                    lastTime = entity.getRemindTime().minusHours(remind);
                    break;
                case DAY:
                    lastTime = entity.getRemindTime().minusDays(remind);
                    break;
                case WEEK:
                    lastTime = entity.getRemindTime().minusWeeks(remind);
                    break;
                case MONTH:
                    lastTime = entity.getRemindTime().minusMonths(remind);
                    break;
            }
        }
        if (null != lastTime) {
            entity.setLastTime(lastTime);
        } else {
            entity.setLastTime(entity.getRemindTime());
        }
        String[] remindObjects = to.getRemindObjects();
        StringBuilder sb = new StringBuilder();
        if (null != remindObjects) {
            for (int i = 0; i < remindObjects.length; i++) {
                if (i == remindObjects.length - 1) {
                    sb.append(remindObjects[i]);
                } else {
                    sb.append(remindObjects[i] + ",");
                }
            }
        }
        entity.setRemindObject(sb.toString());
        super.save(entity);
        return BeanTransform.copyProperties(entity, TimeSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TimeSetTO to) throws SerException {
        TimeSet entity = super.findById(to.getId());
        TimeSet timeSet = BeanTransform.copyProperties(to, TimeSet.class, true);
        BeanUtils.copyProperties(timeSet, entity, "name", "time", "createTime", "id");
        long mis = DateUtil.mis(entity.getRemindTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("提醒时间必须大于等于当前时间");
        }
        Spacing spacing = entity.getSpacing();
        LocalDateTime lastTime = null;
        Integer remind = entity.getRemind();
        if (null != remind && null != spacing) {
            switch (spacing) {
                case MINTUE:
                    lastTime = entity.getRemindTime().minusMinutes(remind);
                    break;
                case HOUR:
                    lastTime = entity.getRemindTime().minusHours(remind);
                    break;
                case DAY:
                    lastTime = entity.getRemindTime().minusDays(remind);
                    break;
                case WEEK:
                    lastTime = entity.getRemindTime().minusWeeks(remind);
                    break;
                case MONTH:
                    lastTime = entity.getRemindTime().minusMonths(remind);
                    break;
            }
        }
        if (null != lastTime) {
            entity.setLastTime(lastTime);
        } else {
            entity.setLastTime(entity.getRemindTime());
        }
        String[] remindObjects = to.getRemindObjects();
        StringBuilder sb = new StringBuilder();
        if (null != remindObjects) {
            for (int i = 0; i < remindObjects.length; i++) {
                if (i == remindObjects.length - 1) {
                    sb.append(remindObjects[i]);
                } else {
                    sb.append(remindObjects[i] + ",");
                }
            }
        }
        entity.setRemindObject(sb.toString());
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        String format = "%s地区的%s的%s为：%d小时，用于对象是：%s";
        TimeSetDTO dto = new TimeSetDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<TimeSet> list = super.findByCis(dto);
        for (TimeSet t : list) {
            String departId = t.getId();    //数据库存的是部门id
            Integer remind = t.getRemind();
            Spacing spacing = t.getSpacing();
            String content = String.format(format, t.getArea(), t.getDepart(), t.getStandardType().toString(), t.getHour(), t.getForObject().toString());
            List<String> mails = new ArrayList<>();
            String[] strings = t.getRemindObject().split(",");
            mails = Arrays.asList(strings);
            if (t.getSendAll()) {
                Set<String> userIds = departmentDetailAPI.departPersons(departId);
                if (null != userIds) {
                    for (String s : userIds) {
                        String name = userAPI.findNameById(s);
                        mails.add(name);
                    }
                }
            }
            List<String> emails = null;
            if (!mails.isEmpty()) {
                String[] s = new String[mails.size()];
                s = mails.toArray(s);
                emails = internalContactsAPI.getEmails(s);
            }
            if (null != remind && null != spacing) {
                LocalDateTime lastTime = t.getLastTime();
                LocalDateTime time = null;
                switch (spacing) {
                    case MINTUE:
                        time = lastTime.plusMinutes(remind);
                        break;
                    case HOUR:
                        time = lastTime.plusHours(remind);
                        break;
                    case DAY:
                        time = lastTime.plusDays(remind);
                        break;
                    case WEEK:
                        time = lastTime.plusWeeks(remind);
                        break;
                    case MONTH:
                        time = lastTime.plusMonths(remind);
                        break;
                }
                long mis = DateUtil.mis(LocalDateTime.now(), time);
                if (mis >= 0) {
                    if (null != emails && !emails.isEmpty()) {
                        String[] ms = new String[emails.size()];
                        ms = emails.toArray(ms);
                        MessageTO messageTO = new MessageTO();
                        messageTO.setTitle("标准工时设置");
                        messageTO.setContent(content);
                        messageTO.setMsgType(MsgType.SYS);
                        messageTO.setSendType(SendType.EMAIL);
                        messageTO.setRangeType(RangeType.SPECIFIED);
                        //定时发送必须写
                        messageTO.setSenderId("SYSTEM");
                        messageTO.setSenderName("SYSTEM");
                        messageTO.setReceivers(ms);
                        messageAPI.send(messageTO);
                    }
                    t.setLastTime(LocalDateTime.now());
                    t.setModifyTime(LocalDateTime.now());
                    super.update(t);
                }
            } else {     //没有设置发送间隔
                LocalDateTime remindTime = t.getRemindTime();
                long mis = DateUtil.mis(LocalDateTime.now(), remindTime);
                if (mis >= 0) {
                    if (null != emails && !emails.isEmpty()) {
                        String[] ms = new String[emails.size()];
                        ms = emails.toArray(ms);
                        MessageTO messageTO = new MessageTO();
                        messageTO.setTitle("标准工时设置");
                        messageTO.setContent(content);
                        messageTO.setMsgType(MsgType.SYS);
                        messageTO.setSendType(SendType.EMAIL);
                        messageTO.setRangeType(RangeType.SPECIFIED);
                        //定时发送必须写
                        messageTO.setSenderId("SYSTEM");
                        messageTO.setSenderName("SYSTEM");
                        messageTO.setReceivers(ms);
                        messageAPI.send(messageTO);
                    }
                }
            }
        }
    }
}