package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.entity.AttachedEnd;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.to.AttachedTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
    private boolean b=true;

    @Override
    @Transactional
    public void save() throws SerException {
        List<AttachedBO> list=attachedSer.findALL();
        if(list.size()>0) {
            for (AttachedBO bo : list) {
                if (bo.getAdvice()) {
                    String endTime = bo.getEndTime();
                    LocalDateTime localDateTime = DateUtil.parseDateTime(endTime);
                    localDateTime = localDateTime.minusMonths(1);
                    int year = localDateTime.getYear();
                    int month = localDateTime.getMonthValue();
                    int day = localDateTime.getDayOfMonth();
                    LocalDateTime localDateTime1 = LocalDateTime.now();
                    int year1 = localDateTime1.getYear();
                    int month1 = localDateTime1.getMonthValue();
                    int day1 = localDateTime1.getDayOfMonth();
                    if (year1 == year && month == month1 && day == day1) {
                        AttachedEnd attachedEnd = new AttachedEnd();
                        BeanUtils.copyProperties(bo, attachedEnd);
                        super.save(attachedEnd);
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public AttachedEndBO is_Again(AttachedEndTO to) throws SerException {
        AttachedEnd attachedEnd= super.findById(to.getId());
        attachedEnd=BeanTransform.copyProperties(to,AttachedEnd.class,true);
        super.update(attachedEnd);
        if(to.getIsAttachedAgin()){
            AttachedTO attachedTO= new AttachedTO();
            BeanUtils.copyProperties(to,attachedTO);
            attachedSer.update(attachedTO);
        }
        return BeanTransform.copyProperties(attachedEnd,AttachedEndBO.class);
    }

    @Override
    public List<AttachedEndBO> find(AttachedEndDTO dto) throws SerException {
        LocalDateTime localDateTime=LocalDateTime.now();
        if(localDateTime.getHour()==0){
            b=true;
        }
        if(b){
            this.save();
            b=false;
        }
        List<AttachedEnd> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,AttachedEndBO.class);
    }

    @Override
    public AttachedEndBO findByID(String id) throws SerException {
        AttachedEnd attachedEnd=super.findById(id);
        return BeanTransform.copyProperties(attachedEnd,AttachedEndBO.class);
    }

    @Override
    @Transactional
    public AttachedEndBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }
}