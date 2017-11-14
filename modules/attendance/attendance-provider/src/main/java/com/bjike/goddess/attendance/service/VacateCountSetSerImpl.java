package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.VacateCountSetBO;
import com.bjike.goddess.attendance.bo.VacateDBO;
import com.bjike.goddess.attendance.bo.VacateMailBO;
import com.bjike.goddess.attendance.bo.overtime.OutWorkDBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkMailBO;
import com.bjike.goddess.attendance.dto.VacateCountSetDTO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;
import com.bjike.goddess.attendance.entity.OverWorkCountSet;
import com.bjike.goddess.attendance.entity.VacateCountSet;
import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.to.VacateCountSetTO;
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
 * 请假汇总通报设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:19 ]
 * @Description: [ 请假汇总通报设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class VacateCountSetSerImpl extends ServiceImpl<VacateCountSet, VacateCountSetDTO> implements VacateCountSetSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private VacateSer vacateSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public VacateCountSetBO save(VacateCountSetTO to) throws SerException {
        VacateCountSet entity = BeanTransform.copyProperties(to, VacateCountSet.class, true);
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
        return BeanTransform.copyProperties(entity, VacateCountSetBO.class);
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
    public void edit(VacateCountSetTO to) throws SerException {
        VacateCountSet entity = super.findById(to.getId());
        VacateCountSet VacateCountSet = BeanTransform.copyProperties(to, VacateCountSet.class, true);
        BeanUtils.copyProperties(VacateCountSet, entity, "id", "creater", "createTime");
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
    public List<VacateCountSetBO> list(VacateCountSetDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<VacateCountSet> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, VacateCountSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        VacateCountSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public VacateCountSetBO findByID(String id) throws SerException {
        VacateCountSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, VacateCountSetBO.class);
    }

    @Override
    public Long count(VacateCountSetDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        List<VacateCountSet> list = super.findAll();
        for (VacateCountSet f : list) {
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
                messageTO.setTitle("请假汇总");
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

    private String sendContent(VacateCountSet f) throws SerException {
        VacateDTO dto = new VacateDTO();
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
        dto.setDeparts(f.getCollectObject().split(","));
        dto.setCountType(CountType.DEPART);
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        List<VacateMailBO> list = vacateSer.vacateCountMail(dto);
        return html(list);
    }

    private String html(List<VacateMailBO> finishCaseBOS) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>请假汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>部门</td>");
        sb.append("<td>请假人</td>");
        sb.append("<td>补班请假时长</td>");
        sb.append("<td>正常工作日内请假时长</td>");
        sb.append("</tr>");
        for (int i = 0; i < finishCaseBOS.size(); i++) {
            List<VacateDBO> sons = finishCaseBOS.get(i).getSons();
            sb.append("<tr>");
            sb.append("<td rowspan='" + sons.size() + "'>" + finishCaseBOS.get(i).getDepart() + "</td>");
            for (int j = 0; j < sons.size(); j++) {
                if (j != 0) {
                    sb.append("<tr>");
                }
                sb.append("<td>" + sons.get(j).getName() + "</td>");
                sb.append("<td>" + sons.get(j).getFillTime() + "</td>");
                sb.append("<td>" + sons.get(j).getNormalTime() + "</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }
}