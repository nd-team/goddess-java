package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.FinanceCountBO;
import com.bjike.goddess.attendance.bo.FinanceCountFieldBO;
import com.bjike.goddess.attendance.bo.FinanceSetBO;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.dto.FinanceSetDTO;
import com.bjike.goddess.attendance.entity.FinanceSet;
import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.to.FinanceSetTO;
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
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 财务出勤设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:15 ]
 * @Description: [ 财务出勤设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class FinanceSetSerImpl extends ServiceImpl<FinanceSet, FinanceSetDTO> implements FinanceSetSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FinanceAttendanceSer financeAttendanceSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FinanceSetBO save(FinanceSetTO to) throws SerException {
        FinanceSet entity = BeanTransform.copyProperties(to, FinanceSet.class, true);
        long mis = DateUtil.mis(entity.getSendTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置发送时间必须大于当前时间");
        }
        String[] sendObjects = to.getSendObjects();
        String[] collectObjects = to.getCollectObjects();
        entity.setSendObject(arraysTOString(sendObjects));
        entity.setCollectObject(arraysTOString(collectObjects));
        int remind = entity.getRemindVal();
        LocalDateTime lastTime = null;
        switch (entity.getRemindFrequency()) {
            case YEAR:
                lastTime = entity.getSendTime().minusYears(remind);
                break;
            case SEASON:
                lastTime = entity.getSendTime().minusMonths(remind * 3);  //一个季度有三个月
                break;
            case MONTH:
                lastTime = entity.getSendTime().minusMonths(remind);
                break;
            case WEEK:
                lastTime = entity.getSendTime().minusWeeks(remind);
                break;
            case DAY:
                lastTime = entity.getSendTime().minusDays(remind);
                break;
            case HOUR:
                lastTime = entity.getSendTime().minusHours(remind);
                break;
            case MIN:
                lastTime = entity.getSendTime().minusMinutes(remind);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setCreater(userAPI.currentUser().getUsername());
        super.save(entity);
        return BeanTransform.copyProperties(entity, FinanceSetBO.class);
    }

    private String arraysTOString(String[] strings) throws SerException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                sb.append(strings[i]);
            } else {
                sb.append(strings[i] + ",");
            }
        }
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(FinanceSetTO to) throws SerException {
        FinanceSet entity = super.findById(to.getId());
        FinanceSet FinanceSet = BeanTransform.copyProperties(to, FinanceSet.class, true);
        BeanUtils.copyProperties(FinanceSet, entity, "id", "creater", "createTime");
        long mis = DateUtil.mis(entity.getSendTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置发送时间必须大于当前时间");
        }
        String[] sendObjects = to.getSendObjects();
        String[] collectObjects = to.getCollectObjects();
        entity.setSendObject(arraysTOString(sendObjects));
        entity.setCollectObject(arraysTOString(collectObjects));
        int remind = entity.getRemindVal();
        LocalDateTime lastTime = null;
        switch (entity.getRemindFrequency()) {
            case YEAR:
                lastTime = entity.getSendTime().minusYears(remind);
                break;
            case SEASON:
                lastTime = entity.getSendTime().minusMonths(remind * 3);  //一个季度有三个月
                break;
            case MONTH:
                lastTime = entity.getSendTime().minusMonths(remind);
                break;
            case WEEK:
                lastTime = entity.getSendTime().minusWeeks(remind);
                break;
            case DAY:
                lastTime = entity.getSendTime().minusDays(remind);
                break;
            case HOUR:
                lastTime = entity.getSendTime().minusHours(remind);
                break;
            case MIN:
                lastTime = entity.getSendTime().minusMinutes(remind);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<FinanceSetBO> list(FinanceSetDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<FinanceSet> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, FinanceSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        FinanceSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public FinanceSetBO findByID(String id) throws SerException {
        FinanceSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, FinanceSetBO.class);
    }

    @Override
    public Long count(FinanceSetDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        List<FinanceSet> list = super.findAll();
        for (FinanceSet f : list) {
            String[] sendObject = f.getSendObject().split(",");
            List<String> mails = internalContactsAPI.getEmails(sendObject);
            if (f.getAll()) {
                List<DepartmentDetailBO> departmentDetailBOs = departmentDetailAPI.departByName(f.getCollectObject().split(","));
                if (null != departmentDetailBOs) {
                    Set<String> set = new HashSet<>();
                    for (DepartmentDetailBO departmentDetailBO : departmentDetailBOs) {
                        Set<String> names = departmentDetailAPI.departPersons(departmentDetailBO.getId());
                        if (null != names) {
                            set.addAll(names);
                        }
                    }
                    if (null == mails) {
                        mails = new ArrayList<>();
                    }
                    if (!set.isEmpty()) {
                        String[] strings = new String[set.size()];
                        strings = set.toArray(strings);
                        List<String> emails = internalContactsAPI.getEmails(strings);
                        if (null != emails) {
                            mails.addAll(emails);
                        }
                    }
                }
            }
            RemindFrequency spacing = f.getRemindFrequency();
            int remind = f.getRemindVal();
            LocalDateTime lastTime = f.getLastTime();
            LocalDateTime time = null;
            switch (spacing) {
                case MIN:
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
                case SEASON:
                    time = lastTime.plusMonths(remind * 3);
                    break;
                case YEAR:
                    time = lastTime.plusYears(remind);
                    break;
            }
            long mis = DateUtil.mis(LocalDateTime.now(), time);
            if (mis >= 0) {
                String content = sendContent(f);
                System.out.println(content);
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle("财务出勤表");
                messageTO.setContent(content);
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (null != mails && !mails.isEmpty()) {
                    List<String> emails = mails.stream().distinct().collect(Collectors.toList());   //去重
                    String[] strings = new String[emails.size()];
                    strings = emails.toArray(strings);
                    messageTO.setReceivers(strings);
                    messageAPI.send(messageTO);
                }
                f.setLastTime(LocalDateTime.now());
                f.setModifyTime(LocalDateTime.now());
                super.update(f);
            }
        }
    }

    private String sendContent(FinanceSet f) throws SerException {
        FinanceAttendanceDTO dto = new FinanceAttendanceDTO();
        CountFrequency countFrequency = f.getCountFrequency();
        String startTime = null;
        String endTime = null;
        switch (countFrequency) {
            case YEAR:
                startTime = DateUtil.dateToString(DateUtil.getStartYear());
                endTime = DateUtil.dateToString(DateUtil.getEndYear());
                break;
            case SEASON:
                String s = DateUtil.dateToString(DateUtil.getStartQuart());
                startTime = s.substring(0, s.indexOf(" "));
                String e = DateUtil.dateToString(DateUtil.getEndQuart());
                endTime = e.substring(0, e.indexOf(" "));
                break;
            case MONTH:
                startTime = DateUtil.dateToString(DateUtil.getStartMonth());
                endTime = DateUtil.dateToString(DateUtil.getEndMonth());
                break;
            case WEEK:
                startTime = DateUtil.dateToString(DateUtil.getStartWeek());
                endTime = DateUtil.dateToString(DateUtil.getEndWeek());
                break;
            case DAY:
                startTime = DateUtil.dateToString(LocalDate.now());
                endTime = DateUtil.dateToString(LocalDate.now());
                break;
        }
        String[] departs = f.getCollectObject().split(",");
        Set<String> names = new HashSet<>();
        List<DepartmentDetailBO> detailBOS = departmentDetailAPI.departByName(departs);
        if (null != detailBOS) {
            for (DepartmentDetailBO departmentDetailBO : detailBOS) {
                names.addAll(departmentDetailAPI.departPersons(departmentDetailBO.getId()));
            }
        }
        String[] strings = new String[names.size()];
        strings = names.toArray(strings);
        dto.setNames(strings);
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        List<FinanceCountFieldBO> fileds = financeAttendanceSer.fields(dto);
        List<FinanceCountBO> counts = financeAttendanceSer.financeCount(dto);
        return html(fileds, counts);

    }

    private String html(List<FinanceCountFieldBO> fileds, List<FinanceCountBO> counts) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>财务出勤表:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        for (FinanceCountFieldBO filed : fileds) {
            if (filed.getSons().isEmpty()) {
                sb.append("<td rowspan='2'>" + filed.getTitle() + "</td>");
            } else {
                sb.append("<td colspan='" + filed.getSons().size() + "'>" + filed.getTitle() + "</td>");
            }
        }
        sb.append("</tr>");
        sb.append("<tr>");
        for (FinanceCountFieldBO filed : fileds) {
            if (!filed.getSons().isEmpty()) {
                for (FinanceCountFieldBO son : filed.getSons()) {
                    sb.append("<td>" + son.getTitle() + "</td>");
                }
            }
        }
        sb.append("</tr>");
        for (FinanceCountBO count : counts) {
            if (0 == count.getIndex()) {
                sb.append("<tr>");
            }
            int maxIndex = fileds.get(fileds.size() - 1).getIndex();
            if (null != count.getRed() && count.getRed()) {
                sb.append("<td style='background:red'>" + count.getValue() + "</td>");
            } else if (null != count.getGreen() && count.getGreen()) {
                sb.append("<td style='background:green'>" + count.getValue() + "</td>");
            } else {
                if (null != count.getValue()) {
                    sb.append("<td>" + count.getValue() + "</td>");
                }else {
                    sb.append("<td> </td>");
                }
            }
            if (maxIndex == count.getIndex()) {
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }
}