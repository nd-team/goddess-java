package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.FinanceAttendanceBO;
import com.bjike.goddess.attendance.bo.FinanceCountBO;
import com.bjike.goddess.attendance.bo.FinanceCountFieldBO;
import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dao.FinanceAttendanceRep;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.entity.FinanceAttendance;
import com.bjike.goddess.attendance.entity.PageUtils;
import com.bjike.goddess.attendance.entity.Punch;
import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.attendance.enums.GuideAddrStatus;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.attendance.service.overtime.OverWorkSer;
import com.bjike.goddess.attendance.to.FinanceAttendanceTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.ObjectBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 财务出勤表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class FinanceAttendanceSerImpl extends ServiceImpl<FinanceAttendance, FinanceAttendanceDTO> implements FinanceAttendanceSer {
    public static final Logger LOGGER = LoggerFactory.getLogger(FinanceAttendanceSerImpl.class);

    @Autowired
    private PunchSer punchSer;
    @Autowired
    private VacateSer vacateSer;
    @Autowired
    private TaskNodeAPI taskNodeAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;
    @Autowired
    private PunchSonSer punchSonSer;
    @Autowired
    private OverWorkSer overWorkSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private FinanceAttendanceRep financeAttendanceRep;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    private String title = "反馈审核";
    private String content = "%s的财务出勤反馈需要您去审核，请上系统查看审核";

    @Override
    //存入数据库
    public List<FinanceAttendanceBO> list(FinanceAttendanceDTO dto) throws SerException {
        List<FinanceAttendanceBO> bos = new ArrayList<>();
        String[] names = dto.getNames();
        if (null == names || names.length == 0) {
            names = new String[]{userAPI.currentUser().getUsername()};
        }
        List<LocalDate> dates = getDate(dto);
        LocalDate starts = dates.get(0);
        LocalDate end = dates.get(1);
        long misDay = DateUtil.misDay(end, starts);
        for (String name : names) {
            Map<String, String> departPosition = null;
            try {
                departPosition = positionUserDetailAPI.departPosition(name);
            } catch (Exception e) {
                if (e.getMessage().indexOf("Forbid consumer") != -1) {
                    LOGGER.error("organize模块服务不可用!");
                }
            }
            String depart = null;
            if (null != departPosition) {
                Set<String> departs = departPosition.keySet();
                for (String s : departs) {
                    depart = s;
                }
            }
            for (long l = 0; l <= misDay; l++) {
                LocalDate start = starts.plusDays(l);
                String week = null;
                int weekValue = start.getDayOfWeek().getValue();
                switch (weekValue) {
                    case 1:
                        week = "周一";
                        break;
                    case 2:
                        week = "周二";
                        break;
                    case 3:
                        week = "周三";
                        break;
                    case 4:
                        week = "周四";
                        break;
                    case 5:
                        week = "周五";
                        break;
                    case 6:
                        week = "周六";
                        break;
                    case 7:
                        week = "周日";
                        break;
                }
                String s = DateUtil.dateToString(start);
                double finishDay = taskNodeAPI.finishDay(s, name);  //任务完成天数
                double vacateDay = vacateDay(name, s);   //请假天数
                double absenteeismDay = 0;  //旷工天数
                double attendanceDay = 0;  //当天考勤天数
                if (start.isBefore(LocalDate.now())) {
                    if (!(punchSonSer.festival(s) || (7 == weekValue && 0 == punchSonSer.extralWork(s)))) {  //当天要上班
                        double d = 1 - finishDay - vacateDay;
                        absenteeismDay = d <= 0 ? 0 : d;    //当天旷工天数=1-任务完成天数-请假天数
                    }
                }
                PunchDTO punchDTO = new PunchDTO();
                punchDTO.getConditions().add(Restrict.eq("name", name));
                punchDTO.getConditions().add(Restrict.eq("date", start));
                Punch punch = punchSer.findOne(punchDTO);
                if (null != punch) {  //查看当天有没打卡记录
                    attendanceDay = 1 - vacateDay;   //减去请假天数
                }
                //实际出勤天数,谁小取谁
                double actualDay = (attendanceDay <= finishDay) ? attendanceDay : finishDay;
                FinanceAttendance financeAttendance = getOne(name, start);
                FinanceAttendanceBO financeAttendanceBO = new FinanceAttendanceBO();
                if (null != financeAttendance) {
                    financeAttendanceBO = BeanTransform.copyProperties(financeAttendance, FinanceAttendanceBO.class);
                    if (AduitStatus.AGREE.equals(financeAttendance.getAduitStatus())) {
                        financeAttendanceBO.setGreen(true);
                        financeAttendanceBO.setRed(false);
                    }
                } else {   //存入数据库
                    financeAttendanceBO.setDepart(depart);
                    financeAttendanceBO.setName(name);
                    financeAttendanceBO.setTime(s);
                    financeAttendanceBO.setWeek(week);
                    financeAttendanceBO.setVacateDay(vacateDay);
                    financeAttendanceBO.setAbsenteeismDay(absenteeismDay);
                    financeAttendanceBO.setFinishDay(finishDay);
                    financeAttendanceBO.setAttendanceDay(attendanceDay);
                    financeAttendanceBO.setActualDay(actualDay);
                    financeAttendanceBO.setId(checkExist(financeAttendanceBO));
                }
                if (!AduitStatus.AGREE.equals(financeAttendanceBO.getAduitStatus())) {
                    if (!(punchSonSer.festival(s) || (7 == weekValue && 0 == punchSonSer.extralWork(s)))) {  //当天要上班
                        if ((absenteeismDay > 0) || (actualDay < 1 - vacateDay)) {
                            financeAttendanceBO.setRed(true);   //有差异
                            financeAttendanceBO.setGreen(false);
                        }
                    } else {
                        financeAttendanceBO.setRed(false);
                        financeAttendanceBO.setGreen(false);
                    }
                }
                bos.add(financeAttendanceBO);
            }
        }
        return bos;
    }

    private FinanceAttendance getOne(String name, LocalDate time) throws SerException {
        FinanceAttendanceDTO dto = new FinanceAttendanceDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("time", time));
        FinanceAttendance financeAttendance = super.findOne(dto);
        return financeAttendance;
    }

    @Override
    public FinanceAttendanceBO one(String id) throws SerException {
        FinanceAttendance entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, FinanceAttendanceBO.class);
    }

    private List<LocalDate> getDate(FinanceAttendanceDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        LocalDate start = null;
        LocalDate end = null;
        if ((StringUtils.isNotBlank(startTime) && StringUtils.isBlank(endTime)) || (StringUtils.isBlank(startTime) && StringUtils.isNotBlank(endTime))) {
            throw new SerException("请选择正确的时间区间");
        } else if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            start = DateUtil.parseDate(startTime);
            end = DateUtil.parseDate(endTime);
        } else {
            start = DateUtil.getStartWeek();
            end = DateUtil.getEndWeek();
        }
        List<LocalDate> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        return list;
    }

    /**
     * 检测某人某天的数据是否存在数据库
     *
     * @param bo
     * @return
     * @throws SerException
     */
    @Transactional(rollbackFor = SerException.class)
    public String checkExist(FinanceAttendanceBO bo) throws SerException {
        LocalDate time = DateUtil.parseDate(bo.getTime());
        FinanceAttendanceDTO dto = new FinanceAttendanceDTO();
        dto.getConditions().add(Restrict.eq("name", bo.getName()));
        dto.getConditions().add(Restrict.eq("time", time));
        FinanceAttendance financeAttendance = super.findOne(dto);
        if (null == financeAttendance) {
            financeAttendance = BeanTransform.copyProperties(bo, FinanceAttendance.class, true);
            super.save(financeAttendance);
        }
        return financeAttendance.getId();
    }

    private List<VacateBO> vacateRecord(String name, String date) throws SerException {
        String sql = "SELECT id,depart,name,time from attendance_vacate where name ='" + name + "'  " +
                "and ('" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d'))";
        String[] fileds = new String[]{"id", "depart", "name", "time"};
        List<VacateBO> list = super.findBySql(sql, VacateBO.class, fileds);
        List<VacateBO> bos = null;
        if (null != list) {
            bos = new ArrayList<>();
            for (VacateBO vacateBO : list) {
                bos.add(vacateSer.findByID(vacateBO.getId()));
            }
        }
        return bos;
    }

    //获取某人当天的请假时长
    @Override
    public Double vacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list, date);
        }
        return 0d;
    }

    private Double annualVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.ANNUAL.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double matterVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.MATTER.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double sickVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.SICK.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double adjustVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.ADJUST.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double maternityVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.MATERNITY.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double paternityVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.PATERNITY.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double checkVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.CHECK.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double funeralVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.FUNERAL.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double otherVacateDay(String name, String date) throws SerException {
        List<VacateBO> list = vacateRecord(name, date);
        if (null != list) {
            return get(list.stream().filter(vacateBO -> VacateType.OTHER.equals(vacateBO.getVacateType())).collect(Collectors.toList()), date);
        }
        return 0d;
    }

    private Double get(List<VacateBO> list, String date) throws SerException {
        double vacateDay = 0;   //当天的请假时长
        for (VacateBO vacateBO : list) {
            double vacate = 0;
            String sH = null;
            String sM = null;
            String eH = null;
            String eM = null;
            String sT = null;
            String eT = null;
            LocalDateTime st = vacateSer.findById(vacateBO.getId()).getStartTime();   //请假时间
            LocalDateTime et = vacateSer.findById(vacateBO.getId()).getEndTime();   //请假结束时间
            LocalDateTime goTime = DateUtil.parseDateTime(date + " 08:30:00");   //上班时间
            LocalDateTime outTime = DateUtil.parseDateTime(date + " 18:00:00");   //下班时间
            if (DateUtil.mis(st, goTime) >= 0) {   //请假开始时间大于等于当天上班时间
                if (DateUtil.mis(outTime, et) <= 0) {   //请假结束时间大于等于当天下班时间的情况
                    sH = new String(st.getHour() + "");
                    sM = new String(st.getMinute() + "");
                    eH = new String(outTime.getHour() + "");
                    eM = new String(outTime.getMinute() + "");
                    sH = toString(sH);
                    sM = toString(sM);
                    eH = toString(eH);
                    eM = toString(eM);
                    sT = sH + ":" + sM;
                    eT = eH + ":" + eM;
                    vacate = vacateSer.currentVacateTime(sT, eT, date);
                } else {  //请假结束时间小于下班时间
                    sH = new String(st.getHour() + "");
                    sM = new String(st.getMinute() + "");
                    eH = new String(et.getHour() + "");
                    eM = new String(et.getMinute() + "");
                    sH = toString(sH);
                    sM = toString(sM);
                    eH = toString(eH);
                    eM = toString(eM);
                    sT = sH + ":" + sM;
                    eT = eH + ":" + eM;
                    vacate = vacateSer.currentVacateTime(sT, eT, date);
                }
            } else {   //请假开始时间小于当天上班时间
                if (DateUtil.mis(outTime, et) <= 0) {   //请假结束时间大于等于当天下班时间的情况
                    sH = new String(goTime.getHour() + "");
                    sM = new String(goTime.getMinute() + "");
                    eH = new String(outTime.getHour() + "");
                    eM = new String(outTime.getMinute() + "");
                    sH = toString(sH);
                    sM = toString(sM);
                    eH = toString(eH);
                    eM = toString(eM);
                    sT = sH + ":" + sM;
                    eT = eH + ":" + eM;
                    vacate = vacateSer.currentVacateTime(sT, eT, date);
                } else {  //请假结束时间小于下班时间
                    sH = new String(goTime.getHour() + "");
                    sM = new String(goTime.getMinute() + "");
                    eH = new String(et.getHour() + "");
                    eM = new String(et.getMinute() + "");
                    sH = toString(sH);
                    sM = toString(sM);
                    eH = toString(eH);
                    eM = toString(eM);
                    sT = sH + ":" + sM;
                    eT = eH + ":" + eM;
                    vacate = vacateSer.currentVacateTime(sT, eT, date);
                }
            }
            vacateDay += vacate;
        }
        return vacateDay;
    }

    /**
     * 补0
     *
     * @param s
     * @return
     * @throws SerException
     */

    private String toString(String s) throws SerException {
        if (s.length() < 2) {
            s = "0" + s;
        }
        return s;
    }

    @Override
    public void apply(FinanceAttendanceTO to) throws SerException {
        FinanceAttendance entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        if ((null != entity.getPrincipalAduit()) || (null != entity.getManagerAduit())) {
            throw new SerException("该记录已被审核过，不能再提交反馈");
        }
        Map<String, String> departPosition = null;
        try {
            departPosition = positionUserDetailAPI.departPosition(entity.getName());
        } catch (Exception e) {
            if (e.getMessage().indexOf("Forbid consumer") != -1) {
                LOGGER.error("organize模块服务不可用!");
            }
        }
        String depart = null;
        if (null != departPosition) {
            Set<String> departs = departPosition.keySet();
            for (String s : departs) {
                depart = s;
            }
        }
        FinanceAttendance financeAttendance = BeanTransform.copyProperties(to, FinanceAttendance.class, true);
        BeanUtils.copyProperties(financeAttendance, entity, "createTime", "id",
                "name", "time", "vacateDay", "absenteeismDay", "finishDay", "attendanceDay", "actualDay");
        entity.setDepart(depart);
        entity.setApplyTime(LocalDate.now());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        String email = null;
        try {
            email = internalContactsAPI.getEmail(financeAttendance.getPrincipal());
        } catch (Exception e) {
            if (e.getMessage().indexOf("Forbid consumer") != -1) {
                LOGGER.error("contacts模块服务不可用!");
            }
        }
        if (null != email) {
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle(title);
            content = String.format(content, financeAttendance.getName());
            messageTO.setContent(content);
            messageTO.setReceivers(new String[]{email});
            try {
                messageAPI.send(messageTO);
            } catch (Exception e) {
                if (e.getMessage().indexOf("Forbid consumer") != -1) {
                    LOGGER.error("message模块服务不可用!");
                }
            }
        }
    }

    @Override
    public void audit(FinanceAttendanceTO to) throws SerException {
        checkAddIdentity();
        String name = userAPI.currentUser().getUsername();
        FinanceAttendance entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        if (name.equals(entity.getPrincipal())) {
            entity.setPrincipalAduit(to.getAduitStatus());
            entity.setPrincipalReason(to.getReason());
            entity.setPrincipalTime(LocalDateTime.now());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            if (AduitStatus.AGREE.equals(entity.getPrincipalAduit())) {
                String email = null;
                try {
                    email = internalContactsAPI.getEmail(entity.getManager());
                } catch (Exception e) {
                    if (e.getMessage().indexOf("Forbid consumer") != -1) {
                        LOGGER.error("contacts模块服务不可用!");
                    }
                }
                if (null != email) {
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle(title);
                    content = String.format(content, entity.getName());
                    messageTO.setContent(content);
                    messageTO.setReceivers(new String[]{email});
                    try {
                        messageAPI.send(messageTO);
                    } catch (Exception e) {
                        if (e.getMessage().indexOf("Forbid consumer") != -1) {
                            LOGGER.error("message模块服务不可用!");
                        }
                    }
                }
            }
        } else if (name.equals(entity.getManager())) {
            if (null == entity.getPrincipalAduit() || AduitStatus.REJECT.equals(entity.getPrincipalAduit())) {
                throw new SerException("负责人审核通过后才可以审核");
            }
            entity.setManagerAduit(to.getAduitStatus());
            entity.setManagerReason(to.getAduitReason());
            entity.setManagerTime(LocalDateTime.now());
            if (AduitStatus.AGREE.equals(entity.getManagerAduit())) {
                entity.setAduitStatus(AduitStatus.AGREE);
            } else {
                entity.setAduitStatus(AduitStatus.REJECT);
            }
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
    }

    /**
     * 固定字段
     *
     * @return
     * @throws SerException
     */
    private List<FinanceCountFieldBO> fixationFields() throws SerException {
        List<FinanceCountFieldBO> bos = new ArrayList<>();
        int a = 0;
        FinanceCountFieldBO bo1 = new FinanceCountFieldBO();
        bo1.setTitle("区域");
        bo1.setIndex(a);
        bos.add(bo1);
        a++;
        FinanceCountFieldBO bo2 = new FinanceCountFieldBO();
        bo2.setTitle("姓名");
        bo2.setIndex(a);
        bos.add(bo2);
        a++;
        FinanceCountFieldBO bo3 = new FinanceCountFieldBO();
        bo3.setTitle("入职时间");
        bo3.setIndex(a);
        bos.add(bo3);
        a++;
        FinanceCountFieldBO bo4 = new FinanceCountFieldBO();
        bo4.setTitle("薪资计算周期内在职时间");
        bo4.setIndex(a);
        bos.add(bo4);
        a++;
        FinanceCountFieldBO bo5 = new FinanceCountFieldBO();
        bo5.setTitle("离职时间");
        bo5.setIndex(a);
        bos.add(bo5);
        a++;
        FinanceCountFieldBO bo6 = new FinanceCountFieldBO();
        bo6.setTitle("日期");
        List<FinanceCountFieldBO> sons = bo6.getSons();
        FinanceCountFieldBO bo61 = new FinanceCountFieldBO();
        bo61.setTitle("星期");
        bo61.setIndex(a);
        sons.add(bo61);
        bos.add(bo6);
        return bos;
    }

    @Override
    public List<FinanceCountFieldBO> fields(FinanceAttendanceDTO dto) throws SerException {
        List<FinanceCountFieldBO> bos = fixationFields();
        int a = bos.get(bos.size() - 1).getSons().get(0).getIndex() + 1;   //字段下标
        List<LocalDate> dates = getDate(dto);
        LocalDate starts = dates.get(0);
        LocalDate end = dates.get(1);
        long misDay = DateUtil.misDay(end, starts);
        double normalWork = 0;   //正常工作日天数
        double festival = 0;  //法定节假日天数
        double normalRest = 0;  //正常休息天数
        for (long l = 0; l <= misDay; l++) {
            LocalDate start = starts.plusDays(l);
            String week = null;
            int weekValue = start.getDayOfWeek().getValue();
            switch (weekValue) {
                case 1:
                    week = "周一";
                    break;
                case 2:
                    week = "周二";
                    break;
                case 3:
                    week = "周三";
                    break;
                case 4:
                    week = "周四";
                    break;
                case 5:
                    week = "周五";
                    break;
                case 6:
                    week = "周六";
                    break;
                case 7:
                    week = "周日";
                    break;
            }
            String time = DateUtil.dateToString(start);
            if (punchSonSer.festival(time)) {
                festival++;
            } else if (7 == weekValue && 0 == punchSonSer.extralWork(time)) {  //周日并且不用补班;
                normalRest++;
            } else {
                normalWork++;
            }
            FinanceCountFieldBO bo = new FinanceCountFieldBO();
            bo.setTitle(time);
            List<FinanceCountFieldBO> sons = bo.getSons();
            FinanceCountFieldBO son = new FinanceCountFieldBO();
            son.setTitle(week);
            son.setIndex(a);
            sons.add(son);
            bos.add(bo);
            a++;
        }
        FinanceCountFieldBO bo = new FinanceCountFieldBO();
        bo.setTitle("正常工作日:" + normalWork + "天");
        List<FinanceCountFieldBO> sons = bo.getSons();
        FinanceCountFieldBO son = new FinanceCountFieldBO();
        son.setTitle("出勤天数");
        son.setIndex(a);
        sons.add(son);
        a++;
        FinanceCountFieldBO son1 = new FinanceCountFieldBO();
        son1.setTitle("请假天数");
        son1.setIndex(a);
        sons.add(son1);
        a++;
        FinanceCountFieldBO son2 = new FinanceCountFieldBO();
        son2.setTitle("旷工天数");
        son2.setIndex(a);
        sons.add(son2);
        a++;
        FinanceCountFieldBO son3 = new FinanceCountFieldBO();
        son3.setTitle("未完成任务天数");
        son3.setIndex(a);
        sons.add(son3);
        a++;
        FinanceCountFieldBO son4 = new FinanceCountFieldBO();
        son4.setTitle("加班天数");
        son4.setIndex(a);
        sons.add(son4);
        a++;
        bos.add(bo);
        FinanceCountFieldBO bo1 = new FinanceCountFieldBO();
        bo1.setTitle("法定节假日:" + festival + "天");
        List<FinanceCountFieldBO> sons1 = bo1.getSons();
        FinanceCountFieldBO sons1bo1 = new FinanceCountFieldBO();
        sons1bo1.setTitle("实际休息天数");
        sons1bo1.setIndex(a);
        sons1.add(sons1bo1);
        a++;
        FinanceCountFieldBO sons1bo2 = new FinanceCountFieldBO();
        sons1bo2.setTitle("加班天数");
        sons1bo2.setIndex(a);
        sons1.add(sons1bo2);
        a++;
        bos.add(bo1);
        FinanceCountFieldBO bo2 = new FinanceCountFieldBO();
        bo2.setTitle("正常休息天数:" + normalRest + "天");
        List<FinanceCountFieldBO> sons2 = bo2.getSons();
        FinanceCountFieldBO sons2bo1 = new FinanceCountFieldBO();
        sons2bo1.setTitle("实际休息天数");
        sons2bo1.setIndex(a);
        sons2.add(sons2bo1);
        a++;
        FinanceCountFieldBO sons2bo2 = new FinanceCountFieldBO();
        sons2bo2.setTitle("加班天数");
        sons2bo2.setIndex(a);
        sons2.add(sons2bo2);
        a++;
        bos.add(bo2);
        FinanceCountFieldBO bo3 = new FinanceCountFieldBO();
        bo3.setTitle(DateUtil.dateToString(end) + "前剩余加班天数");
        bo3.setIndex(a);
        a++;
        bos.add(bo3);
        FinanceCountFieldBO bo4 = new FinanceCountFieldBO();
        bo4.setTitle("加班抵事假和其他假的的天数");
        bo4.setIndex(a);
        a++;
        bos.add(bo4);
        FinanceCountFieldBO bo5 = new FinanceCountFieldBO();
        bo5.setTitle("抵扣事假和其他假后剩余加班天数");
        bo5.setIndex(a);
        a++;
        bos.add(bo5);
        FinanceCountFieldBO bo6 = new FinanceCountFieldBO();
        bo6.setTitle("月工作日");
        bo6.setIndex(a);
        a++;
        bos.add(bo6);
        FinanceCountFieldBO bo7 = new FinanceCountFieldBO();
        bo7.setTitle("可享受带薪天数");
        bo7.setIndex(a);
        bos.add(bo7);
        return bos;
    }

    @Override
    public List<FinanceCountBO> financeCount(FinanceAttendanceDTO dto) throws SerException {
        List<LocalDate> dates = getDate(dto);
        LocalDate start = dates.get(0);
        LocalDate end = dates.get(1);
        List<FinanceCountBO> bos = new ArrayList<>();
        List<FinanceAttendanceBO> list = noSaveList(dto);
        List<String> names = list.stream().map(FinanceAttendanceBO::getName).distinct().collect(Collectors.toList());
        for (String name : names) {
            List<FinanceAttendanceBO> financeAttendanceBOS = list.stream().filter(financeAttendanceBO -> name.equals(financeAttendanceBO.getName())).collect(Collectors.toList());
            List<FinanceCountBO> nameAll = fixationValue(name, financeAttendanceBOS, start, end);
            bos.addAll(nameAll);
            int a = nameAll.get(nameAll.size() - 1).getIndex() + 1;   //值下标
            for (FinanceAttendanceBO financeAttendanceBO : financeAttendanceBOS) {
                LocalDate time = DateUtil.parseDate(financeAttendanceBO.getTime());
                FinanceAttendanceDTO financeAttendanceDTO = new FinanceAttendanceDTO();
                financeAttendanceDTO.getConditions().add(Restrict.eq("name", financeAttendanceBO.getName()));
                financeAttendanceDTO.getConditions().add(Restrict.eq("time", time));
                FinanceAttendance financeAttendance = super.findOne(financeAttendanceDTO);
                FinanceCountBO bo = getBO(financeAttendance, financeAttendanceBO, a);
                bos.add(bo);
                a++;
            }
            Map<List<FinanceAttendanceBO>, List<String>> map = get(new String[]{name}, DateUtil.misDay(end, start), start, end);
            Set<List<FinanceAttendanceBO>> set = map.keySet();
            List<String> strings = null;
            for (List<FinanceAttendanceBO> bo : set) {
                strings = map.get(bo);
            }
            for (String s : strings) {
                FinanceCountBO bo = new FinanceCountBO();
                bo.setIndex(a);
                bo.setValue(s);
                bo.setRed(false);
                bo.setGreen(false);
                bos.add(bo);
                a++;
            }
        }
        return bos;
    }

    private FinanceCountBO getBO(FinanceAttendance financeAttendance, FinanceAttendanceBO financeAttendanceBO, int index) throws SerException {
        String time = financeAttendanceBO.getTime();
        StringBuilder sb = new StringBuilder();
        FinanceCountBO bo = null;
        if (punchSonSer.festival(time)) {
            bo = new FinanceCountBO("法定假期", index, false, false);
            return bo;
        } else if (7 == DateUtil.parseDate(time).getDayOfWeek().getValue() && 0 == punchSonSer.extralWork(time)) {
            bo = new FinanceCountBO("周末假期", index, false, false);
            return bo;
        }
        if (null != financeAttendance) {
            if (AduitStatus.AGREE.equals(financeAttendance.getAduitStatus())) {    //审核通过
                if (null != financeAttendance.getVacateDayNew()) {
                    sb.append("请假" + financeAttendance.getVacateDayNew() + "天\n");
                } else {
                    sb.append("请假" + financeAttendance.getVacateDay() + "天\n");
                }
                if (null != financeAttendance.getAbsenteeismDayNew()) {
                    sb.append("旷工天数:" + financeAttendance.getAbsenteeismDayNew() + "天\n");
                } else {
                    sb.append("旷工天数:" + financeAttendance.getAbsenteeismDay() + "天\n");
                }
                if (null != financeAttendance.getFinishDayNew()) {
                    sb.append("任务完成天数:" + financeAttendance.getFinishDayNew() + "天\n");
                } else {
                    sb.append("任务完成天数:" + financeAttendance.getFinishDay() + "天\n");
                }
                if (null != financeAttendance.getAttendanceDayNew()) {
                    sb.append("当天考勤天数:" + financeAttendance.getAttendanceDayNew() + "天\n");
                } else {
                    sb.append("当天考勤天数:" + financeAttendance.getAttendanceDay() + "天\n");
                }
                if (null != financeAttendance.getActualDayNew()) {
                    sb.append("实际出勤天数:" + financeAttendance.getActualDayNew() + "天");
                } else {
                    sb.append("实际出勤天数:" + financeAttendance.getActualDay() + "天");
                }
                bo = new FinanceCountBO(sb.toString(), index, false, true);   //审核修改过为绿色
                return bo;
            }
        }
        sb.append("请假" + financeAttendanceBO.getVacateDay() + "天\n");
        sb.append("旷工天数:" + financeAttendanceBO.getAbsenteeismDay() + "天\n");
        sb.append("任务完成天数:" + financeAttendanceBO.getFinishDay() + "天\n");
        sb.append("当天考勤天数:" + financeAttendanceBO.getAttendanceDay() + "天\n");
        sb.append("实际出勤天数:" + financeAttendanceBO.getActualDay() + "天");
        bo = new FinanceCountBO(sb.toString(), index, financeAttendanceBO.getRed(), false);
        return bo;
    }

    //固定值
    private List<FinanceCountBO> fixationValue(String name, List<FinanceAttendanceBO> financeAttendanceBOS, LocalDate start, LocalDate end) throws SerException {
        List<FinanceCountBO> bos = new ArrayList<>();
        int a = 0;
        FinanceCountBO bo = new FinanceCountBO(financeAttendanceBOS.get(0).getDepart(), a, false, false);
        bos.add(bo);
        a++;
        FinanceCountBO bo1 = new FinanceCountBO(name, a, false, false);
        bos.add(bo1);
        a++;
        String entryTime = null;  //入职时间
        try {
            entryTime = entryRegisterAPI.getEntryTime(name);
        } catch (Exception e) {
            if (e.getMessage().indexOf("Forbid consumer") != -1) {
                LOGGER.error("staffentry模块服务不可用!");
            }
        }
        LocalDate entryDate = null;
        if (null != entryTime) {
            entryDate = DateUtil.parseDate(entryTime);
        }
        FinanceCountBO bo2 = new FinanceCountBO(entryTime, a, false, false);
        bos.add(bo2);
        a++;
        String dismissTime = null;   //离职时间
        try {
            dismissTime = dimissionInfoAPI.getTime(name);
        } catch (Exception e) {
            if (e.getMessage().indexOf("Forbid consumer") != -1) {
                LOGGER.error("dimissionInfo模块服务不可用!");
            }
        }
        LocalDate dismissDate = null;
        if (null != dismissTime) {
            dismissDate = DateUtil.parseDate(dismissTime);
        }
        long cycleTime = DateUtil.misDay(end, start) + 1;
        if (null != entryDate) {
            if (DateUtil.misDay(entryDate, start) > 0) {   //入职时间大于汇总开始时间
                if (null == dismissDate) {   //没离职
                    cycleTime = DateUtil.misDay(end, entryDate) + 1;
                } else {   //离职了
                    if (DateUtil.misDay(dismissDate, end) < 0) {  //离职时间小于汇总结束时间
                        cycleTime = DateUtil.misDay(dismissDate, entryDate) + 1;
                    } else {
                        cycleTime = DateUtil.misDay(end, entryDate) + 1;
                    }
                }
            } else {   //入职时间小于汇总开始时间
                if (null != dismissDate) {   //离职了
                    if (DateUtil.misDay(dismissDate, end) < 0) {  //离职时间小于汇总结束时间
                        cycleTime = DateUtil.misDay(dismissDate, start) + 1;
                    }
                }
            }
        } else {   //入职时间为空
            if (null != dismissDate) {   //离职了
                if (DateUtil.misDay(dismissDate, end) < 0) {  //离职时间小于汇总结束时间
                    cycleTime = DateUtil.misDay(dismissDate, start) + 1;
                }
            }
        }

        long salaryDate = DateUtil.misDay(start, end);
        FinanceCountBO bo3 = new FinanceCountBO(salaryDate + "天", a, false, false);
        bos.add(bo3);
        a++;
        FinanceCountBO bo4 = new FinanceCountBO(dismissTime, a, false, false);
        bos.add(bo4);
        a++;
        FinanceCountBO bo5 = new FinanceCountBO(null, a, false, false);
        bos.add(bo5);
        return bos;
    }

    //不存入数据库
    private List<FinanceAttendanceBO> noSaveList(FinanceAttendanceDTO dto) throws SerException {
        String[] names = dto.getNames();
        if (null == names || names.length == 0) {
            names = new String[]{userAPI.currentUser().getUsername()};
        }
        List<LocalDate> dates = getDate(dto);
        LocalDate start = dates.get(0);
        LocalDate end = dates.get(1);
        long misDay = DateUtil.misDay(end, start);
        Map<List<FinanceAttendanceBO>, List<String>> map = get(names, misDay, start, end);
        Set<List<FinanceAttendanceBO>> set = map.keySet();
        List<FinanceAttendanceBO> bos = null;
        for (List<FinanceAttendanceBO> list : set) {
            bos = list;
        }
        return bos;
    }

    private Map<List<FinanceAttendanceBO>, List<String>> get(String[] names, Long misDay, LocalDate starts, LocalDate end) throws SerException {
        Map<List<FinanceAttendanceBO>, List<String>> map = new HashMap<>();
        List<FinanceAttendanceBO> bos = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (String name : names) {
            double normalAttendance = 0;   //正常工作日出勤天数
            double annualVacate = 0;  //年假请假天数
            double matterVacate = 0;  //事假请假天数
            double sickVacate = 0;  //病假请假天数
            double adjustVacate = 0;  //调休请假天数
            double maternityVacate = 0;  //产假请假天数
            double paternityVacate = 0;  //陪产假请假天数
            double checkVacate = 0;  //产检假请假天数
            double funeralVacate = 0;  //丧假请假天数
            double otherVacate = 0;  //其他请假天数
            double vacate = 0;  //请假天数
            double normalAbsenteeism = 0;   //正常工作日旷工天数
            double normalUnFinish = 0;   //正常工作日未完成任务天数
            double normalOutWork = 0;  //正常工作日加班天数
            double festival = 0;  //节假日天数
            double festivalRest = 0;  //节假日实际休息天数
            double festivalOutWork = 0;  //节假日加班天数
            double noramlRest = 0;  //正常休息日天数
            double rest = 0;  //正常休息日实际休息天数
            double outWork = 0;  //正常休息日加班天数
            double remind = 0;   //剩余加班天数
            double offset = 0;  //加班抵消天数
            double lastRemind = 0;   //抵消后的剩余加班天数
            double workDay = 0;   //月工作日
            double paidDay = 0;   //可享受带薪天数
            Map<String, String> departPosition = null;
            try {
                departPosition = positionUserDetailAPI.departPosition(name);
            } catch (Exception e) {
                if (e.getMessage().indexOf("Forbid consumer") != -1) {
                    LOGGER.error("organize模块服务不可用!");
                }
            }
            String depart = null;
            if (null != departPosition) {
                Set<String> departs = departPosition.keySet();
                for (String s : departs) {
                    depart = s;
                }
            }
            for (long l = 0; l <= misDay; l++) {
                LocalDate start = starts.plusDays(l);
                String week = null;
                int weekValue = start.getDayOfWeek().getValue();
                switch (weekValue) {
                    case 1:
                        week = "周一";
                        break;
                    case 2:
                        week = "周二";
                        break;
                    case 3:
                        week = "周三";
                        break;
                    case 4:
                        week = "周四";
                        break;
                    case 5:
                        week = "周五";
                        break;
                    case 6:
                        week = "周六";
                        break;
                    case 7:
                        week = "周日";
                        break;
                }
                String s = DateUtil.dateToString(start);
                double finishDay = taskNodeAPI.finishDay(s, name);  //任务完成天数
                double vacateDay = vacateDay(name, s);   //请假天数
                double absenteeismDay = 0;  //旷工天数
                double attendanceDay = 0;  //当天考勤天数
                if (start.isBefore(LocalDate.now())) {
                    if (!(punchSonSer.festival(s) || (7 == weekValue && 0 == punchSonSer.extralWork(s)))) {  //当天要上班
                        double d = 1 - finishDay - vacateDay;
                        absenteeismDay = d <= 0 ? 0 : d;    //当天旷工天数=1-任务完成天数-请假天数
                        normalAbsenteeism += absenteeismDay;
                    }
                }
                PunchDTO punchDTO = new PunchDTO();
                punchDTO.getConditions().add(Restrict.eq("name", name));
                punchDTO.getConditions().add(Restrict.eq("date", start));
                Punch punch = punchSer.findOne(punchDTO);
                if (null != punch) {  //查看当天有没打卡记录
                    attendanceDay = 1 - vacateDay;   //减去请假天数
                }
                //实际出勤天数,谁小取谁
                double actualDay = (attendanceDay <= finishDay) ? attendanceDay : finishDay;
                if (!(punchSonSer.festival(s) || (7 == weekValue && 0 == punchSonSer.extralWork(s)))) {  //正常工作日合计
                    normalAttendance += attendanceDay;
                    annualVacate += annualVacateDay(name, s);
                    matterVacate += matterVacateDay(name, s);
                    sickVacate += sickVacateDay(name, s);
                    adjustVacate += adjustVacateDay(name, s);
                    maternityVacate += maternityVacateDay(name, s);
                    paternityVacate += paternityVacateDay(name, s);
                    checkVacate += checkVacateDay(name, s);
                    funeralVacate += funeralVacateDay(name, s);
                    otherVacate += otherVacateDay(name, s);
                    normalOutWork += punchSonSer.outWorkTime(name, s);
                    List<ObjectBO> list = taskNodeAPI.taskSituation(new String[]{name}, s);
                    if (null != list) {
                        double unFinishMin = list.stream().filter(objectBO -> null != objectBO.getUndoneType() && null != objectBO.getUndoneTime() && 0 == objectBO.getUndoneType().intValue()).mapToDouble(ObjectBO::getUndoneTime).sum();
                        double unFinishHour = list.stream().filter(objectBO -> null != objectBO.getUndoneType() && null != objectBO.getUndoneTime() && 1 == objectBO.getUndoneType().intValue()).mapToDouble(ObjectBO::getUndoneTime).sum();
                        double unFinishDay = list.stream().filter(objectBO -> null != objectBO.getUndoneType() && null != objectBO.getUndoneTime() && 2 == objectBO.getUndoneType().intValue()).mapToDouble(ObjectBO::getUndoneTime).sum();
                        double time = new BigDecimal(unFinishMin / 60 / 24).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + new BigDecimal(unFinishHour / 24).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + unFinishDay;
                        normalUnFinish += time;
                    }
                } else if (punchSonSer.festival(s)) {   //节假日合计
                    festival++;
                    PunchDTO dto = new PunchDTO();
                    dto.getConditions().add(Restrict.eq("date", start));
                    dto.getConditions().add(Restrict.eq("name", name));
                    if (null == punchSer.findOne(dto)) {
                        festivalRest++;
                    }
                    festivalOutWork += punchSonSer.outWorkTime(name, s);
                } else if (7 == weekValue && 0 == punchSonSer.extralWork(s)) {  //正常休息日合计
                    noramlRest++;
                    PunchDTO dto = new PunchDTO();
                    dto.getConditions().add(Restrict.eq("date", start));
                    dto.getConditions().add(Restrict.eq("name", name));
                    if (null == punchSer.findOne(dto)) {
                        rest++;
                    }
                    outWork += punchSonSer.outWorkTime(name, s);
                }
                FinanceAttendance financeAttendance = getOne(name, start);
                FinanceAttendanceBO financeAttendanceBO = new FinanceAttendanceBO();
                if (null != financeAttendance) {
                    financeAttendanceBO = BeanTransform.copyProperties(financeAttendance, FinanceAttendanceBO.class);
                    if (AduitStatus.AGREE.equals(financeAttendance.getAduitStatus())) {
                        financeAttendanceBO.setGreen(true);
                        financeAttendanceBO.setRed(false);
                    }
                } else {
                    financeAttendanceBO.setDepart(depart);
                    financeAttendanceBO.setName(name);
                    financeAttendanceBO.setTime(s);
                    financeAttendanceBO.setWeek(week);
                    financeAttendanceBO.setVacateDay(vacateDay);
                    financeAttendanceBO.setAbsenteeismDay(absenteeismDay);
                    financeAttendanceBO.setFinishDay(finishDay);
                    financeAttendanceBO.setAttendanceDay(attendanceDay);
                    financeAttendanceBO.setActualDay(actualDay);
                }
                if (!AduitStatus.AGREE.equals(financeAttendanceBO.getAduitStatus())) {
                    if (!(punchSonSer.festival(s) || (7 == weekValue && 0 == punchSonSer.extralWork(s)))) {  //当天要上班
                        if ((absenteeismDay > 0) || (actualDay < 1 - vacateDay)) {
                            financeAttendanceBO.setRed(true);   //有差异
                            financeAttendanceBO.setGreen(false);
                        }
                    } else {
                        financeAttendanceBO.setRed(false);
                        financeAttendanceBO.setGreen(false);
                    }
                }
                bos.add(financeAttendanceBO);
            }
            vacate = annualVacate + matterVacate + sickVacate + adjustVacate + maternityVacate + paternityVacate + checkVacate + funeralVacate + otherVacate;
            remind = overWorkSer.overDay(name, end);
            if (remind > 0 && vacate > 0) {
                offset = remind >= vacate ? vacate : remind;
            }
            lastRemind = remind - vacate;
            workDay = normalAttendance + festival + offset;
            paidDay = festival + noramlRest;
            strings.add(normalAttendance + "");
            if (vacate > 0) {
                StringBuilder sb = new StringBuilder();
                if (annualVacate > 0) {
                    sb.append("请年假" + annualVacate + "天\n");
                }
                if (matterVacate > 0) {
                    sb.append("请事假" + matterVacate + "天\n");
                }
                if (sickVacate > 0) {
                    sb.append("请病假" + sickVacate + "天\n");
                }
                if (adjustVacate > 0) {
                    sb.append("调休" + adjustVacate + "天\n");
                }
                if (maternityVacate > 0) {
                    sb.append("请产假" + maternityVacate + "天\n");
                }
                if (paternityVacate > 0) {
                    sb.append("请陪产假" + paternityVacate + "天\n");
                }
                if (checkVacate > 0) {
                    sb.append("请产检假" + checkVacate + "天\n");
                }
                if (funeralVacate > 0) {
                    sb.append("请丧假" + funeralVacate + "天\n");
                }
                if (otherVacate > 0) {
                    sb.append("请其他假" + otherVacate + "天");
                }
                strings.add(sb.toString());
            } else {
                strings.add(vacate + "");
            }
            strings.add(normalAbsenteeism + "");
            strings.add(normalUnFinish + "");
            strings.add(normalOutWork + "");
            strings.add(festivalRest + "");
            strings.add(festivalOutWork + "");
            strings.add(rest + "");
            strings.add(outWork + "");
            strings.add(remind + "");
            strings.add(offset + "");
            strings.add(lastRemind + "");
            strings.add(workDay + "");
            strings.add(paidDay + "");
        }
        map.put(bos, strings);
        return map;
    }

    @Override
    public List<FinanceAttendanceBO> aduitList(FinanceAttendanceDTO dto) throws SerException {
        checkSeeIdentity();
        List<FinanceAttendanceBO> bos = new ArrayList<>();
        String name = userAPI.currentUser().getUsername();
        List<FinanceAttendance> list = sql(name);
        if (null != list && !list.isEmpty()) {
            List<String> ids = list.stream().map(FinanceAttendance::getId).collect(Collectors.toList());
            dto.getConditions().add(Restrict.in(ID, ids));
            List<FinanceAttendance> financeAttendances = super.findByCis(dto, true);
            bos = BeanTransform.copyProperties(financeAttendances, FinanceAttendanceBO.class);
        }
        return bos;
    }

    @Override
    public Long aduitListNum(FinanceAttendanceDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        List<FinanceAttendance> list = sql(name);
        if (null != list && !list.isEmpty()) {
            List<String> ids = list.stream().map(FinanceAttendance::getId).collect(Collectors.toList());
            dto.getConditions().add(Restrict.in(ID, ids));
            return super.count(dto);
        }
        return 0l;
    }

    private List<FinanceAttendance> sql(String name) throws SerException {
        String sql = "SELECT id " +
                "FROM attendance_financeattendance " +
                "WHERE (principal = '" + name + "' AND principalAduit IS NULL) OR (manager = '" + name + "' AND principalAduit = 0 AND managerAduit IS NULL)";
        String[] fields = new String[]{"id"};
        List<FinanceAttendance> list = super.findBySql(sql, FinanceAttendance.class, fields);
        return list;
    }


    /**
     * -----------------------------------------------------------------------------------------------------------
     */
    /**
     * 自定义Excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public ByteArrayOutputStream excelExport(FinanceAttendanceDTO dto) throws SerException {
        long startTime = System.currentTimeMillis();    //获取开始时间
        XSSFWorkbook wb = new XSSFWorkbook(); // 创建一个工作execl文档
        XSSFSheet sheet = wb.createSheet("财务出勤表" + DateUtil.dateToString(LocalDate.now()));
        //获取全部字段
        List<FinanceCountFieldBO> fieldsBO = fields(dto);
        List<FinanceCountFieldBO> fields = new ArrayList<>();
        //获取动态字段
        for (FinanceCountFieldBO bo : fieldsBO) {
            if (bo.getSons().size() > 0) {
                fields.add(bo);
            }
        }

        //表头样式 和 内容样式
        XSSFCellStyle titleStyle = this.createTitleSytle(wb);
        XSSFCellStyle contentStyle = this.createCotentSytle(wb);

        initHeader(wb, sheet, titleStyle, fields); //初始化表头
        List<List<FinanceCountBO>> financeCountBOS = financeCounts(dto); //获取数据

        initRows(wb, sheet, contentStyle, financeCountBOS); //初始化内容
        System.out.println("程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms");    //输出程序运行时间

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new SerException("文件写入错误");
        }
        return os;
    }

    /**
     * 创建内容行
     *
     * @param wb
     * @param sheet
     */
    public static void initRows(XSSFWorkbook wb, XSSFSheet sheet, XSSFCellStyle contentStyle, List<List<FinanceCountBO>> financeCountBOS) {
        int rowCount = 2;
        for (List<FinanceCountBO> bos : financeCountBOS) {
            XSSFRow row = sheet.createRow(rowCount++);
            row.setHeight((short) 1300);
            int cellIndex = 0;
            for (FinanceCountBO bo : bos) {
                if (bo.getGreen() != null && bo.getRed() != null) {
                    if (bo.getRed()) {
                        contentStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                        contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    } else {
                        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                        contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    }

                }

                XSSFCell cell = row.createCell(cellIndex);
                String val = String.valueOf(bo.getValue());
                cell.setCellStyle(contentStyle);
                cell.setCellValue(val.equals("null") ? "" : val);
//                sheet.setColumnWidth(cellIndex, val.getBytes().length*2*256);//设置自动宽度
                cellIndex++;

            }
        }
    }

    /**
     * 重写方法
     *
     * @param dto
     * @return
     * @throws SerException
     */
    public List<List<FinanceCountBO>> financeCounts(FinanceAttendanceDTO dto) throws SerException {
        List<List<FinanceCountBO>> b = new ArrayList<>();
        List<LocalDate> dates = getDate(dto);
        LocalDate start = dates.get(0);
        LocalDate end = dates.get(1);

        List<FinanceAttendanceBO> list = noSaveList(dto);
        List<String> names = list.stream().map(FinanceAttendanceBO::getName).distinct().collect(Collectors.toList());
        for (String name : names) {
            List<FinanceCountBO> bos = new ArrayList<>();
            List<FinanceAttendanceBO> financeAttendanceBOS = list.stream().filter(financeAttendanceBO -> name.equals(financeAttendanceBO.getName())).collect(Collectors.toList());
            List<FinanceCountBO> nameAll = fixationValue(name, financeAttendanceBOS, start, end);
            bos.addAll(nameAll);
            int a = nameAll.get(nameAll.size() - 1).getIndex() + 1;   //值下标
            for (FinanceAttendanceBO financeAttendanceBO : financeAttendanceBOS) {
                LocalDate time = DateUtil.parseDate(financeAttendanceBO.getTime());
                FinanceAttendanceDTO financeAttendanceDTO = new FinanceAttendanceDTO();
                financeAttendanceDTO.getConditions().add(Restrict.eq("name", financeAttendanceBO.getName()));
                financeAttendanceDTO.getConditions().add(Restrict.eq("time", time));
                FinanceAttendance financeAttendance = super.findOne(financeAttendanceDTO);
                FinanceCountBO bo = getBO(financeAttendance, financeAttendanceBO, a);
                bos.add(bo);
                a++;
            }
            Map<List<FinanceAttendanceBO>, List<String>> map = get(new String[]{name}, DateUtil.misDay(end, start), start, end);
            Set<List<FinanceAttendanceBO>> set = map.keySet();
            List<String> strings = null;
            for (List<FinanceAttendanceBO> bo : set) {
                strings = map.get(bo);
            }
            for (String s : strings) {
                FinanceCountBO bo = new FinanceCountBO();
                bo.setIndex(a);
                bo.setValue(s);
                bo.setRed(false);
                bo.setGreen(false);
                bos.add(bo);
                a++;
            }
            b.add(bos);
        }
        return b;
    }


    /**
     * 创建表头
     * 创建标题行内容
     * 返回标题行内容插入顺序
     *
     * @param sheet
     * @param fields
     */
    public static List<String> initHeader(XSSFWorkbook wb, XSSFSheet sheet, XSSFCellStyle titleStyle, List<FinanceCountFieldBO> fields) {
        //存储内容存放顺序
        List<String> orderLiser = new LinkedList<String>();

        XSSFRow titleRow1 = sheet.createRow(0);
        XSSFRow titleRow2 = sheet.createRow(1);
        XSSFRow titleRow3 = sheet.createRow(2);
        XSSFRow titleRow4 = sheet.createRow(3);
        XSSFRow titleRow5 = sheet.createRow(4);

        titleRow1.setHeight((short) 800);
        titleRow2.setHeight((short) 800);
        titleRow3.setHeight((short) 800);
        titleRow4.setHeight((short) 800);
        titleRow5.setHeight((short) 800);

        //合并单元格 向下合并
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));

        XSSFCell cell = null;
        cell = titleRow1.createCell(0);
        cell.setCellValue("区域");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(0, (short) (10 * 256));


        cell = titleRow1.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(1, (short) (10 * 256));

        cell = titleRow1.createCell(2);
        cell.setCellValue("入职时间");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(2, (short) (10 * 256));

        cell = titleRow1.createCell(3);
        cell.setCellValue("薪资计算周期内在职时间");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(3, (short) (10 * 256));

        cell = titleRow1.createCell(4);
        cell.setCellValue("离职时间");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(4, (short) (10 * 256));

        int merge = 0;    //合并的行数
        int intiNum = 0;  //每一个新的单元格起始位置

        String oldStr = "";  //存放 一级目录
        String newStr = "";  //存放 一级目录

        for (int i = 0; i < fields.size(); i++) {
            FinanceCountFieldBO bo = fields.get(i);
            List<FinanceCountFieldBO> sons = bo.getSons();

            for (int j = 0; j < sons.size(); j++) {
                newStr = bo.getTitle();
                if ("".equals(oldStr)) {     //起始，旧值为空
                    intiNum = 5 + i;
                    cell = titleRow1.createCell(intiNum);
                    cell.setCellValue(newStr);
                    cell.setCellStyle(titleStyle);
                    sheet.setColumnWidth(intiNum, (short) (10 * 256));
                } else if (!oldStr.equals(newStr)) {      //判断新值是否等于旧值，否就新增一个单元格
                    // sheet.addMergedRegion(new CellRangeAddress(0, 0, intiNum, intiNum + merge));    //合并单元格
                    cell = titleRow1.createCell(intiNum + merge + 1);
                    cell.setCellValue(newStr);
                    cell.setCellStyle(titleStyle);
                    sheet.setColumnWidth(intiNum + merge + 1, (short) (10 * 256));
                    intiNum = intiNum + merge + 1;
                    merge = 0;
                } else {
                    merge++;
                }

                cell = titleRow2.createCell(intiNum + merge);
                cell.setCellValue(sons.get(j).getTitle());
                cell.setCellStyle(titleStyle);
                sheet.setColumnWidth(intiNum + merge, (short) (10 * 256));

                oldStr = newStr;   //把新值赋给旧值
            }
            if (sons.size() > 1) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, intiNum, intiNum + merge));//合并最后一个单元格
            }
        }

        //------------------------------------------
        //获取全部行数
        int sum = 0;
        for (int i = 0; i < fields.size(); i++) {
            FinanceCountFieldBO bo = fields.get(i);
            List<FinanceCountFieldBO> sons = bo.getSons();

            if (sons.size() > 1) {
                sum = sum + sons.size();
            } else {
                sum = sum + 1;
            }
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 1, sum + 5, sum + 5));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, sum + 6, sum + 6));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, sum + 7, sum + 7));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, sum + 8, sum + 8));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, sum + 9, sum + 9));


        cell = titleRow1.createCell(sum + 5);
        cell.setCellValue("2018-01-27前剩余加班天数");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(sum + 5, (short) (10 * 256));

        cell = titleRow1.createCell(sum + 6);
        cell.setCellValue("加班抵事假和其他假的的天数");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(sum + 6, (short) (10 * 256));

        cell = titleRow1.createCell(sum + 7);
        cell.setCellValue("抵扣事假和其他假后剩余加班天数");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(sum + 7, (short) (10 * 256));

        cell = titleRow1.createCell(sum + 8);
        cell.setCellValue("月工作日");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(sum + 8, (short) (10 * 256));

        cell = titleRow1.createCell(sum + 9);
        cell.setCellValue("可享受带薪天数");
        cell.setCellStyle(titleStyle);
        sheet.setColumnWidth(sum + 9, (short) (10 * 256));

        return orderLiser;
    }

    /**
     * 创建内容样式
     *
     * @param workBook
     * @return
     */
    private XSSFCellStyle createCotentSytle(XSSFWorkbook workBook) {
        XSSFCellStyle contentStyle = workBook.createCellStyle();
        contentStyle.setWrapText(true);
        XSSFFont contentFont = workBook.createFont();
        contentFont.setFontName("宋体");
        contentStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        contentFont.setFontHeightInPoints((short) 10);
        contentStyle.setFont(contentFont);
        contentStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中


        //---设置边框:
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        return contentStyle;
    }

    /**
     * //创建标题样式
     *
     * @param workBook
     * @return
     */
    private XSSFCellStyle createTitleSytle(XSSFWorkbook workBook) {
        XSSFCellStyle titleStyle = workBook.createCellStyle();
        XSSFFont titlefont = workBook.createFont();
        titlefont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        titlefont.setFontName("宋体");
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setWrapText(true);
        titlefont.setFontHeightInPoints((short) 11);
        titleStyle.setFont(titlefont);
        titleStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //---设置边框:
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        return titleStyle;
    }

    @Override
    public PageUtils findAll(String pageNum, String pageSize, String name) throws SerException {

        Pageable pageable = new PageRequest( Integer.parseInt(pageNum) , Integer.parseInt( pageSize));
        PageUtils pageUtils = new PageUtils(financeAttendanceRep.findAll(pageable).getContent(),
                (int)financeAttendanceRep.findAll(pageable).getTotalElements(),
                Integer.parseInt( pageSize),Integer.parseInt(pageNum)-1);
        return pageUtils;
    }

    @Override
    public void save(FinanceAttendanceDTO dto) throws SerException {
        financeAttendanceRep.saveAndFlush( BeanTransform.copyProperties(dto,FinanceAttendance.class) );
    }

    @Override
    public void delete(String[] ids) throws SerException {
        for (String id : ids){
            financeAttendanceRep.delete(id);
        }
    }

    @Override
    public void Update(FinanceAttendanceDTO dto) throws SerException {
        financeAttendanceRep.saveAndFlush( BeanTransform.copyProperties(dto,FinanceAttendance.class) );
    }
}
