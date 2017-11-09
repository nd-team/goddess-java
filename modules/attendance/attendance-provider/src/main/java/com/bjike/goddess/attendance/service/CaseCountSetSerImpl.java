package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.*;
import com.bjike.goddess.attendance.dto.CaseCountSetDTO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.entity.CaseCountSet;
import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.enums.TotalType;
import com.bjike.goddess.attendance.to.CaseCountSetTO;
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
 * 考勤情况汇总设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-19 06:18 ]
 * @Description: [ 考勤情况汇总设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class CaseCountSetSerImpl extends ServiceImpl<CaseCountSet, CaseCountSetDTO> implements CaseCountSetSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private PunchSonSer punchSonSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CaseCountSetBO save(CaseCountSetTO to) throws SerException {
        CaseCountSet entity = BeanTransform.copyProperties(to, CaseCountSet.class, true);
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
        return BeanTransform.copyProperties(entity, CaseCountSetBO.class);
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
    public void edit(CaseCountSetTO to) throws SerException {
        CaseCountSet entity = super.findById(to.getId());
        CaseCountSet CaseCountSet = BeanTransform.copyProperties(to, CaseCountSet.class, true);
        BeanUtils.copyProperties(CaseCountSet, entity, "id", "creater", "createTime");
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
    public List<CaseCountSetBO> list(CaseCountSetDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<CaseCountSet> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CaseCountSetBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        CaseCountSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public CaseCountSetBO findByID(String id) throws SerException {
        CaseCountSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, CaseCountSetBO.class);
    }

    @Override
    public Long count(CaseCountSetDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        CaseCountSetDTO dto = new CaseCountSetDTO();
        List<CaseCountSet> list = super.findByCis(dto);
        for (CaseCountSet f : list) {
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
                messageTO.setTitle("考勤情况汇总");
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

    private String sendContent(CaseCountSet f) throws SerException {
        PunchDTO dto = new PunchDTO();
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
        if (TotalType.NUMBER.equals(f.getTotalType())) {
            List<CaseCountBO> list = punchSonSer.caseCount(dto);
            return html(list);
        } else {
            List<CaseCountMailBO> list = punchSonSer.caseCountMail(dto);
            return html1(list);
        }
    }

    private String html(List<CaseCountBO> finishCaseBOS) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>考勤情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门</td>");
        sb.append("<td>正常出勤人员</td>");
        sb.append("<td>迟到出勤人员</td>");
        sb.append("<td>有请假出勤人员</td>");
        sb.append("<td>旷工情况</td>");
        sb.append("<td>正常休息情况</td>");
        sb.append("<td>加班情况</td>");
        sb.append("<td>法定节假日休息情况</td>");
        sb.append("</tr>");
        for (int i = 0; i < finishCaseBOS.size(); i++) {
            List<CaseABO> sons = finishCaseBOS.get(i).getSons();
            sb.append("<tr>");
            if (null != finishCaseBOS.get(i).getArea()) {
                sb.append("<td rowspan='" + sons.size() + "'>" + finishCaseBOS.get(i).getArea() + "</td>");
            } else {
                sb.append("<td rowspan='" + sons.size() + "'> </td>");
            }
            for (int j = 0; j < sons.size(); j++) {
                if (j!=0){
                    sb.append("<tr>");
                }
                if (null != sons.get(j).getDepart()) {
                    sb.append("<td>" + sons.get(j).getDepart() + "</td>");
                } else {
                    sb.append("<td> </td>");
                }
                sb.append("<td>" + sons.get(j).getNormalAttendance() + "</td>");
                sb.append("<td>" + sons.get(j).getLateAttendance() + "</td>");
                sb.append("<td>" + sons.get(j).getVacateAttendance() + "</td>");
                sb.append("<td>" + sons.get(j).getAbsenteeism() + "</td>");
                sb.append("<td>" + sons.get(j).getNormalRest() + "</td>");
                sb.append("<td>" + sons.get(j).getOutWork() + "</td>");
                sb.append("<td>" + sons.get(j).getFestival() + "</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    private String html1(List<CaseCountMailBO> finishCaseBOS) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>考勤情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>部门</td>");
        sb.append("<td>正常出勤人员</td>");
        sb.append("<td>迟到出勤人员</td>");
        sb.append("<td>有请假出勤人员</td>");
        sb.append("<td>旷工情况</td>");
        sb.append("<td>正常休息情况</td>");
        sb.append("<td>加班情况</td>");
        sb.append("<td>法定节假日休息情况</td>");
        sb.append("</tr>");
        for (int i = 0; i < finishCaseBOS.size(); i++) {
            int size = size(finishCaseBOS.get(i));
            sb.append("<tr>");
            sb.append("<td rowspan='" + size + "'>" + finishCaseBOS.get(i).getDepart() + "</td>");
            for (int j = 0; j < size; j++) {
                if (j != 0) {
                    sb.append("<tr>");
                }
                if (finishCaseBOS.get(i).getNormalAttendances().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getNormalAttendances().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getNormalAttendances().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getLates().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getLates().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getLates().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getVacateAttendances().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getVacateAttendances().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getVacateAttendances().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getAbsenteeisms().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getAbsenteeisms().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getAbsenteeisms().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getNormalRests().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getNormalRests().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getNormalRests().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getOutWorks().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getOutWorks().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getOutWorks().size();
                    sb.append("<td>/</td>");
                }
                if (finishCaseBOS.get(i).getFestivals().size() > j) {
                    sb.append("<td>" + finishCaseBOS.get(i).getFestivals().get(j).getName() + "</td>");
                } else {
//                int a = size - finishCaseBOS.get(i).getFestivals().size();
                    sb.append("<td>/</td>");
                }
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    private int size(CaseCountMailBO bo) throws SerException {
        List<NormalAttendBO> normalAttendances = bo.getNormalAttendances();
        List<NormalAttendBO> lates = bo.getLates();
        List<NormalAttendBO> vacateAttendances = bo.getVacateAttendances();
        List<NormalAttendBO> absenteeisms = bo.getAbsenteeisms();
        List<NormalAttendBO> normalRests = bo.getNormalRests();
        List<NormalAttendBO> outWorks = bo.getOutWorks();
        List<NormalAttendBO> festivals = bo.getFestivals();
        int a = normalAttendances.size() > lates.size() ? normalAttendances.size() : lates.size();
        a = a > vacateAttendances.size() ? a : vacateAttendances.size();
        a = a > absenteeisms.size() ? a : absenteeisms.size();
        a = a > normalRests.size() ? a : normalRests.size();
        a = a > outWorks.size() ? a : outWorks.size();
        a = a > festivals.size() ? a : festivals.size();
        return a;
    }
}