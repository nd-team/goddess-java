package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.quartz.api.ScheduleJobAPI;
import com.bjike.goddess.quartz.api.ScheduleJobGroupAPI;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.entity.AttachedEnd;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 挂靠到期业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedEndSerImpl extends ServiceImpl<AttachedEnd, AttachedEndDTO> implements AttachedEndSer {
    @Autowired
    private AttachedSer attachedSer;
    @Autowired
    private ScheduleJobAPI scheduleJobAPI;
    @Autowired
    private ScheduleJobGroupAPI scheduleJobGroupAPI;
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quartz() throws SerException {
//        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
//        scheduleJobGroupTO.setName("定时查找挂靠即将到期人员工作组");
//        scheduleJobGroupTO.setEnable(true);
//        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.AttachedEndAPI");
        scheduleJobTO.setName("定时查找挂靠即将到期人员");
        scheduleJobTO.setMethod("send");
        scheduleJobTO.setExpression("0 0/1440 * * * ?");   //每24个小时执行一次
        scheduleJobTO.setDescription("查找挂靠即将到期人员，并通知综合资源部");
        scheduleJobTO.setEnable(true);
        scheduleJobTO.setAddress("localhost:51101");
        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
        scheduleJobAPI.add(scheduleJobTO);
    }


    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO save(AttachedEndTO to) throws SerException {
        AttachedEnd entity = BeanTransform.copyProperties(to, AttachedEnd.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AttachedEndBO.class);
//        List<AttachedBO> list = attachedSer.findALL();
//        if (list.size() > 0) {
//            for (AttachedBO bo : list) {
//                if (bo.getAdvice()) {
//                    String endTime = bo.getEndTime();
//                    LocalDateTime localDateTime = DateUtil.parseDateTime(endTime);
//                    localDateTime = localDateTime.minusMonths(1);
//                    int year = localDateTime.getYear();
//                    int month = localDateTime.getMonthValue();
//                    int day = localDateTime.getDayOfMonth();
//                    LocalDateTime localDateTime1 = LocalDateTime.now();
//                    int year1 = localDateTime1.getYear();
//                    int month1 = localDateTime1.getMonthValue();
//                    int day1 = localDateTime1.getDayOfMonth();
//                    if (year1 == year && month == month1 && day == day1) {
//                        AttachedEnd attachedEnd = new AttachedEnd();
//                        BeanUtils.copyProperties(bo, attachedEnd);
//                        super.save(attachedEnd);
//                    }
//                }
//            }
//        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO is_Again(AttachedEndTO to) throws SerException {
        AttachedEnd attachedEnd = super.findById(to.getId());
        if (attachedEnd==null){
            throw new SerException("该对象不存在");
        }
//        LocalDateTime a = attachedEnd.getCreateTime();
//        attachedEnd = BeanTransform.copyProperties(to, AttachedEnd.class, true);
//        attachedEnd.setCreateTime(a);
        attachedEnd.setIsAttachedAgin(to.getIsAttachedAgin());
        attachedEnd.setModifyTime(LocalDateTime.now());
        super.update(attachedEnd);
        if (to.getIsAttachedAgin()) {
//            AttachedTO attachedTO = new AttachedTO();
//            BeanUtils.copyProperties(to, attachedTO);
//            attachedSer.update(attachedTO);
        } else {
            RemoveEmployeeTO removeEmployeeTO = new RemoveEmployeeTO();
            removeEmployeeTO.setRemoveName(attachedEnd.getAttachedName());
            removeEmployeeTO.setRemoveCity(attachedEnd.getAttachedArrival());
            removeEmployeeSer.save(removeEmployeeTO);
        }
        return BeanTransform.copyProperties(attachedEnd, AttachedEndBO.class);
    }

    @Override
    public List<AttachedEndBO> find(AttachedEndDTO dto) throws SerException {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        int year = localDateTime.getYear();
//        int month = localDateTime.getMonthValue();
//        int day = localDateTime.getDayOfMonth();
//        if ((year1 != year) || (month1 != month) || (day1 != day)) {
//            b = true;
//        }
//        if (b) {
//            this.save();
//            year1 = year;
//            month1 = month;
//            day1 = day;
//            b = false;
//        }
        List<AttachedEnd> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedEndBO.class);
    }

    @Override
    public AttachedEndBO findByID(String id) throws SerException {
        AttachedEnd attachedEnd = super.findById(id);
        return BeanTransform.copyProperties(attachedEnd, AttachedEndBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void send() throws SerException {
        List<AttachedBO> boList = attachedSer.findALL();
        LocalDate now = LocalDate.now();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有社保挂靠即将到期的人员");
        StringBuilder sb = new StringBuilder();
        boolean b = false;
        for (AttachedBO bo : boList) {
            if (StringUtils.isNotBlank(bo.getEndTime())) {
                LocalDate endTime = DateUtil.parseDate(bo.getEndTime());
                if (now == (endTime.minusMonths(1))) {
                    b = true;
                    String name = bo.getAttachedName();
                    sb.append(name + "，");
                    AttachedEndTO attachedEndTO = new AttachedEndTO();
                    BeanUtils.copyProperties(bo, attachedEndTO);
                    save(attachedEndTO);
                }
            }
        }
        if (b) {
            sb.append("社保挂靠即将到期，请及时处理!");
            messageTO.setContent(sb.toString());
            //todo:发邮件到综合资源部公邮
        }
    }

    @Override
    public Long count(AttachedEndDTO dto) throws SerException {
        return super.count(dto);
    }
}