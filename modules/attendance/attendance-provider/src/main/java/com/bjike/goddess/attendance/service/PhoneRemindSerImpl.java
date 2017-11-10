package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.dto.PhoneRemindDTO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.entity.PhoneRemind;
import com.bjike.goddess.attendance.entity.Punch;
import com.bjike.goddess.attendance.entity.PunchSon;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.enums.PunchType;
import com.bjike.goddess.attendance.push.Push;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.push.api.PushUserInfoAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 手机提醒打卡业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class PhoneRemindSerImpl extends ServiceImpl<PhoneRemind, PhoneRemindDTO> implements PhoneRemindSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PunchSonSer punchSonSer;
    @Autowired
    private VacateSer vacateSer;
    @Autowired
    private PunchSer punchSer;
    @Autowired
    private FinanceAttendanceSer financeAttendanceSer;
    @Autowired
    private PushUserInfoAPI pushUserInfoAPI;

    @Override
    public void start() throws SerException {
        String name = userAPI.currentUser().getUsername();
        PhoneRemindDTO dto = new PhoneRemindDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        PhoneRemind remind = super.findOne(dto);
        if (null == remind) {
            remind.setName(name);
            remind.setEnable(true);
            super.save(remind);
        } else {
            remind.setEnable(true);
            remind.setModifyTime(LocalDateTime.now());
            super.update(remind);
        }
    }

    @Override
    public void close() throws SerException {
        String name = userAPI.currentUser().getUsername();
        PhoneRemindDTO dto = new PhoneRemindDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        PhoneRemind remind = super.findOne(dto);
        if (null == remind) {
            remind.setName(name);
            remind.setEnable(false);
            super.save(remind);
        } else {
            remind.setEnable(false);
            remind.setModifyTime(LocalDateTime.now());
            super.update(remind);
        }
    }

    @Override
    public Boolean currentStauts() throws SerException {
        String name = userAPI.currentUser().getUsername();
        PhoneRemindDTO dto = new PhoneRemindDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        PhoneRemind remind = super.findOne(dto);
        if (null == remind) {
            return false;
        }
        return remind.getEnable();
    }

    @Override
    public void quartz() throws SerException {
        PhoneRemindDTO dto = new PhoneRemindDTO();
        dto.getConditions().add(Restrict.eq("enable", Boolean.FALSE));
        List<PhoneRemind> list = super.findByCis(dto);
        LocalDate now = LocalDate.now();
        LocalDateTime go = DateUtil.parseDateTime(DateUtil.dateToString(now) + " 08:30:00");
        LocalDateTime out = DateUtil.parseDateTime(DateUtil.dateToString(now) + " 18:00:00");
        LocalDateTime start = DateUtil.parseDateTime(DateUtil.dateToString(now) + " 08:20:00");
        LocalDateTime last = DateUtil.parseDateTime(DateUtil.dateToString(now) + " 23:30:00");
        //当天要上班
        if (!(punchSonSer.festival(DateUtil.dateToString(now)) || (7 == now.getDayOfWeek().getValue() && 0 == punchSonSer.extralWork(DateUtil.dateToString(now))))) {
            for (PhoneRemind remind : list) {
                boolean flag = false;
                List<Vacate> vacates = vacates(DateUtil.dateToString(now), remind.getName());
                for (Vacate v : vacates) {
                    if (!(go.isEqual(v.getStartTime()) || (v.getStartTime().isBefore(go) && v.getEndTime().isAfter(go)))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {  //没请假
                    Punch punch = find(now, remind.getName());
                    PunchSon shang = null;
                    if (null != punch) {
                        PunchSonDTO sonDTO = new PunchSonDTO();
                        sonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
                        sonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
                        shang = punchSonSer.findOne(sonDTO);
                    }
                    if (DateUtil.mis(LocalDateTime.now(), start) >= 0 && DateUtil.mis(LocalDateTime.now(), out) <= 0) {
                        if (null == shang) {   //上班提醒
                            String ticker = "考勤消息推送";
                            String title = "上班打卡提醒";
                            String text = "亲，今天为上班时间，您还没打卡，请准时打上班卡";
                            if (null == remind.getGoLastTime()) {
                                String deviceToken = pushUserInfoAPI.getToken(remind.getName());
                                try {
                                    if (44 == deviceToken.length()) {
                                        Push.androidUnicast(deviceToken, ticker, title, text);   //安卓消息推送
                                    } else if (64 == deviceToken.length()) {
                                        Push.iosUnicast(deviceToken, text);   //ios消息推送
                                    }
                                } catch (Exception e) {
                                    throw new SerException(e.getMessage());
                                }
                                remind.setGoLastTime(LocalDateTime.now());
                                remind.setModifyTime(LocalDateTime.now());
                                super.update(remind);
                            } else {
                                if (DateUtil.mis(LocalDateTime.now(), remind.getGoLastTime().plusMinutes(10)) >= 0) {
                                    String deviceToken = pushUserInfoAPI.getToken(remind.getName());
                                    try {
                                        if (44 == deviceToken.length()) {
                                            Push.androidUnicast(deviceToken, ticker, title, text);   //安卓消息推送
                                        } else if (64 == deviceToken.length()) {
                                            Push.iosUnicast(deviceToken, text);   //ios消息推送
                                        }
                                    } catch (Exception e) {
                                        throw new SerException(e.getMessage());
                                    }
                                    remind.setGoLastTime(LocalDateTime.now());
                                    remind.setModifyTime(LocalDateTime.now());
                                    super.update(remind);
                                }
                            }
                        }
                    }
                }
                boolean flag1 = false;
                if (financeAttendanceSer.vacateDay(remind.getName(), DateUtil.dateToString(now)) >= 8) {
                    flag1 = true;
                }
                if (flag1) {
                    Punch punch = find(now, remind.getName());
                    PunchSon xia = null;
                    if (null != punch) {
                        PunchSonDTO sonDTO = new PunchSonDTO();
                        sonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
                        sonDTO.getConditions().add(Restrict.eq("punchType", PunchType.AFTER));
                        xia = punchSonSer.findOne(sonDTO);
                    }
                    if (DateUtil.mis(LocalDateTime.now(), out) >= 0 && DateUtil.mis(LocalDateTime.now(), last) <= 0) {
                        if (null == xia) {  //下班提醒
                            String ticker = "考勤消息推送";
                            String title = "下班打卡提醒";
                            String text = "亲，下班时间到了，您还没打卡，请准时打下班卡";
                            if (null == remind.getOutLastTime()) {
                                String deviceToken = pushUserInfoAPI.getToken(remind.getName());
                                try {
                                    if (44 == deviceToken.length()) {
                                        Push.androidUnicast(deviceToken, ticker, title, text);   //安卓消息推送
                                    } else if (64 == deviceToken.length()) {
                                        Push.iosUnicast(deviceToken, text);   //ios消息推送
                                    }
                                } catch (Exception e) {
                                    throw new SerException(e.getMessage());
                                }
                                remind.setOutLastTime(LocalDateTime.now());
                                remind.setModifyTime(LocalDateTime.now());
                                super.update(remind);
                            } else {
                                if (DateUtil.mis(LocalDateTime.now(), remind.getOutLastTime().plusMinutes(30)) >= 0) {
                                    String deviceToken = pushUserInfoAPI.getToken(remind.getName());
                                    try {
                                        if (44 == deviceToken.length()) {
                                            Push.androidUnicast(deviceToken, ticker, title, text);   //安卓消息推送
                                        } else if (64 == deviceToken.length()) {
                                            Push.iosUnicast(deviceToken, text);   //ios消息推送
                                        }
                                    } catch (Exception e) {
                                        throw new SerException(e.getMessage());
                                    }
                                    remind.setOutLastTime(LocalDateTime.now());
                                    remind.setModifyTime(LocalDateTime.now());
                                    super.update(remind);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private Punch find(LocalDate date, String name) throws SerException {
        PunchDTO dto = new PunchDTO();
        dto.getConditions().add(Restrict.eq("date", date));
        dto.getConditions().add(Restrict.eq("name", name));
        return punchSer.findOne(dto);
    }

    private List<Vacate> vacates(String date, String name) throws SerException {
        String sql = "SELECT id " +
                "FROM attendance_vacate " +
                "WHERE '" + date + "' BETWEEN DATE_FORMAT(startTime, '%Y-%m-%d') AND DATE_FORMAT(endTime, '%Y-%m-%d') and name='" + name + "'";
        List<Vacate> list = super.findBySql(sql, Vacate.class, new String[]{"id"});
        List<Vacate> vacates = new ArrayList<>();
        if (null != list) {
            for (Vacate v : list) {
                vacates.add(vacateSer.findById(v.getId()));
            }
        }
        return vacates;
    }
}