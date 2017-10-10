package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.enums.EndTime;
import com.bjike.goddess.attendance.enums.StartTime;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 请假管理业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class VacateSerImpl extends ServiceImpl<Vacate, VacateDTO> implements VacateSer {

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public VacateBO save(VacateTO to) throws SerException {
        String startDate = to.getStartDate();
        String startTime = to.getStartTime().toString().toString() + ":00";
        LocalDateTime start = DateUtil.parseDateTime(startDate + " " +
                startTime);
        long mis = DateUtil.mis(LocalDateTime.now(), start);
        if (mis <= 0) {
            throw new SerException("请假开始时间必须大于当前时间");
        }
        double time = getTime(to);
        if (time > 3) {    //请假大于3天需提前1周
            LocalDate s = DateUtil.parseDate(startDate);
            long days = Math.abs(s.toEpochDay() - LocalDate.now().toEpochDay());
            if (days < 7) {
                throw new SerException("请假>3天的要提前一周");
            }
        }
        String[] mains = to.getMains();
        String[] carbons = to.getCarbons();
        Vacate entity = BeanTransform.copyProperties(to, Vacate.class, true, "startTime", "endTime");
        entity.setMain(toString(mains));
        entity.setCarbon(toString(carbons));
        super.save(entity);
        return BeanTransform.copyProperties(entity, VacateBO.class);
    }

    private String toString(String[] strings) throws SerException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                sb.append(strings[i]);
            } else {
                sb.append(strings[i]+",");
            }
        }
        return sb.toString();
    }

    @Override
    public Double getTime(VacateTO to) throws SerException {
        StartTime startE = to.getStartTime();
        EndTime endE = to.getEndTime();
        String startTime = startE.toString() + ":00";
        String endTime = endE.toString() + ":00";
        String startDate = to.getStartDate();
        String endDate = to.getEndDate();
        LocalDateTime s = DateUtil.parseDateTime(startDate + " " + startTime);
        LocalDateTime e = DateUtil.parseDateTime(endDate + " " + endTime);
        long a = DateUtil.mis(e, s);
        if (a <= 0) {
            throw new SerException("请假结束时间必须大于请假开始时间");
        }
        LocalDate now = LocalDate.now();
        long days = Math.abs(DateUtil.parseDate(endDate).toEpochDay() - DateUtil.parseDate(startDate).toEpochDay());    //先算出请假的日期天数
        double result = 0;
        if (days < 1) {   //开始时间和结束时间都是当天的情况
            long mis = DateUtil.mis(DateUtil.parseDateTime(endDate + " " + endTime), DateUtil.parseDateTime(startDate + " " + startTime));
            if (startE.getCode() <= 6 && endE.getCode() > 6) {   //减去中午1.5个小时
                result = (Double.parseDouble(mis + "") - 1.5 * 3600000) / (3600000 * 8);
            } else {
                result = Double.parseDouble(mis + "") / (3600000 * 8);
            }
        } else {
            LocalDateTime start = DateUtil.parseDateTime(now + " " + startTime);
            LocalDateTime end = DateUtil.parseDateTime(now + " " + endTime);
            long mis = DateUtil.mis(end, start);
            if (mis < 0) {    //结束时间比开始时间小的情况（只看时分秒）
                if (startE.getCode() <= 6) {    //需减去中午1.5小时
                    long sMis = DateUtil.mis(DateUtil.parseDateTime(now + " 18:00:00"), DateUtil.parseDateTime(now + " " + startTime));
                    result += (Double.parseDouble(sMis + "") - 1.5 * 3600000) / (3600000 * 8);
                    if (endE.getCode() > 6) {  //需减去中午1.5小时
                        long eMis = DateUtil.mis(DateUtil.parseDateTime(now + " " + endTime), DateUtil.parseDateTime(now + " 08:30:00"));
                        result += (Double.parseDouble(eMis + "") - 1.5 * 3600000) / (3600000 * 8);
                    } else {
                        long eMis = DateUtil.mis(DateUtil.parseDateTime(now + " " + endTime), DateUtil.parseDateTime(now + " 08:30:00"));
                        result += (Double.parseDouble(eMis + "") / (3600000 * 8));
                    }
                } else {
                    long sMis = DateUtil.mis(DateUtil.parseDateTime(now + " 18:00:00"), DateUtil.parseDateTime(now + " " + startTime));
                    result += Double.parseDouble(sMis + "") / (3600000 * 8);
                    if (endE.getCode() > 6) {  //需减去中午1.5小时
                        long eMis = DateUtil.mis(DateUtil.parseDateTime(now + " " + endTime), DateUtil.parseDateTime(now + " 08:30:00"));
                        result += (Double.parseDouble(eMis + "") - 1.5 * 3600000) / (3600000 * 8);
                    } else {
                        long eMis = DateUtil.mis(DateUtil.parseDateTime(now + " " + endTime), DateUtil.parseDateTime(now + " 08:30:00"));
                        result += Double.parseDouble(eMis + "") / (3600000 * 8);
                    }
                }
                result += (days - 1);
            } else {
                if (startE.getCode() <= 6 && endE.getCode() > 6) {   //减去中午1.5个小时
                    result = (Double.parseDouble(mis + "") - 1.5 * 3600000) / (3600000 * 8);
                } else {
                    result = Double.parseDouble(mis + "") / (3600000 * 8);
                }
                result += days;
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(VacateTO to) throws SerException {
        Vacate entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Vacate.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<VacateBO> list(VacateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Vacate> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, VacateBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        Vacate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public VacateBO findByID(String id) throws SerException {
        Vacate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, VacateBO.class);
    }

    @Override
    public Long count(VacateDTO dto) throws SerException {
        return super.count(dto);
    }
}