package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.*;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchGrandSonDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.entity.*;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.attendance.enums.*;
import com.bjike.goddess.attendance.excel.PunchExportExcel;
import com.bjike.goddess.attendance.excel.PunchImportExcel;
import com.bjike.goddess.attendance.service.overtime.OverWorkSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.PunchSonVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.ObjectBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 打卡子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class PunchSonSerImpl extends ServiceImpl<PunchSon, PunchSonDTO> implements PunchSonSer {
    @Autowired
    private PunchSer punchSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ArrestPointSer arrestPointSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PunchGrandSonSer punchGrandSonSer;
    @Autowired
    private PunchSonSer punchSonSer;
    @Autowired
    private TaskNodeAPI taskNodeAPI;
    @Autowired
    private ExtralOverWorkSonSer extralOverWorkSonSer;
    @Autowired
    private FinanceAttendanceSer financeAttendanceSer;
    @Autowired
    private OverWorkSer overWorkSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1");
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
        if (flagSee) {
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
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public PunchSonBO save(PunchSonTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        LocalDate date = LocalDate.now();
        PunchDTO punchDTO = new PunchDTO();
        punchDTO.getConditions().add(Restrict.eq("date", date));
        punchDTO.getConditions().add(Restrict.eq("name", userBO.getUsername()));
        Punch punch = punchSer.findOne(punchDTO);
        if (null == punch) {
            punch = new Punch();
            punch.setName(userBO.getUsername());
            punch.setDate(date);
            punchSer.save(punch);
        }
        PunchSonDTO dto = new PunchSonDTO();
        dto.getConditions().add(Restrict.eq("punchId", punch.getId()));
        dto.getConditions().add(Restrict.eq("punchType", to.getPunchType()));
        PunchSon entity = super.findOne(dto);
        PunchSon punchSon = null;
        if (null == entity) {
            punchSon = new PunchSon();
            punchSon.setArea(to.getArea());
            punchSon.setPunchTime(LocalDateTime.now());
            punchSon.setPunchType(to.getPunchType());
            punchSon.setPunchSource(to.getPunchSource());
            punchSon.setPunchId(punch.getId());
            super.save(punchSon);
        } else {     //覆盖
            punchSon = entity;
            punchSon.setArea(to.getArea());
            punchSon.setPunchTime(LocalDateTime.now());
            punchSon.setPunchSource(to.getPunchSource());
            punchSon.setModifyTime(LocalDateTime.now());
            super.update(punchSon);
        }
        PunchStatus punchStatus = PunchStatus.NORMAL;
        if (PunchSource.MOBILE.equals(to.getPunchSource())) {   //移动端
            Double longitude = to.getLongitude();
            Double latitude = to.getLatitude();
            punchStatus = punchStatus(longitude, latitude, userBO.getUsername());
        } else {   //pc端
            DepartmentDetailBO departmentDetailBO = positionDetailUserAPI.areaAndDepart(userBO.getUsername());
            if (null == departmentDetailBO) {
                throw new SerException("系统异常，无法查出当前用户所属地区");
            }
            if ((null != to.getArea()) && (to.getArea().contains(departmentDetailBO.getArea()))) {
                punchStatus = PunchStatus.NORMAL;
            } else if ((null != to.getArea()) && (!(to.getArea().contains(departmentDetailBO.getArea())))) {
                punchStatus = PunchStatus.OUTSIDE;    //外勤
            } else if ((null != to.getArea()) && (!(to.getArea().contains("内网IP//内网IP/内网IP")))) {
                punchStatus = PunchStatus.NORMAL;
            }
        }
        saveSon(punchSon, userBO.getUsername(), punchStatus, date);
        PunchSonBO bo = BeanTransform.copyProperties(punchSon, PunchSonBO.class);
        String status = findPunchStatus(punchSon);
        bo.setPunchStatus(status);
        return bo;
    }

    @Transactional(rollbackFor = {SerException.class})
    public void saveSon(PunchSon punchSon, String name, PunchStatus punchStatus, LocalDate date) throws SerException {
        PunchGrandSonDTO dto = new PunchGrandSonDTO();
        dto.getConditions().add(Restrict.eq("punchSonId", punchSon.getId()));
        List<PunchGrandSon> list = punchGrandSonSer.findByCis(dto);
        if (!list.isEmpty()) {
            punchGrandSonSer.remove(list);    //覆盖，先把之前的打卡状态先删除掉
        }
        if (PunchType.GO.equals(punchSon.getPunchType())) {    //上班时会出现迟到和外勤的情况
            long num = num(name, date);
            boolean flag = false;
            boolean flag1 = false;
            LocalDateTime time = DateUtil.parseDateTime(DateUtil.dateToString(date) + " 08:40:00");    //迟到大于十分钟没得免扣
            LocalDateTime time1 = DateUtil.parseDateTime(DateUtil.dateToString(date) + " 08:31:00");
            long mis = DateUtil.mis(punchSon.getPunchTime(), time);
            long mis1 = DateUtil.mis(punchSon.getPunchTime(), time1);
            if (mis1 >= 0 && mis < 0 && num < 3) {    //当前月迟到次数小于三次(10分钟以内)，免扣
                PunchGrandSon grandSon = new PunchGrandSon();
                grandSon.setPunchSonId(punchSon.getId());
                grandSon.setPunchStatus(PunchStatus.LATE);
                punchGrandSonSer.save(grandSon);
                PunchGrandSon grandSon1 = new PunchGrandSon();
                grandSon1.setPunchSonId(punchSon.getId());
                grandSon1.setPunchStatus(PunchStatus.FEE);
                punchGrandSonSer.save(grandSon1);
                flag = true;
            } else if ((mis1 >= 0 && mis < 0) || mis >= 0) {
                PunchGrandSon grandSon = new PunchGrandSon();
                grandSon.setPunchSonId(punchSon.getId());
                grandSon.setPunchStatus(PunchStatus.LATE);
                punchGrandSonSer.save(grandSon);
                flag = true;
            }
            if (PunchStatus.OUTSIDE.equals(punchStatus)) {
                PunchGrandSon grandSon = new PunchGrandSon();
                grandSon.setPunchSonId(punchSon.getId());
                grandSon.setPunchStatus(PunchStatus.OUTSIDE);
                punchGrandSonSer.save(grandSon);
                flag1 = true;
            }
            if (!(flag || flag1)) {   //只要有一个条件不符合就算不正常
                PunchGrandSon grandSon = new PunchGrandSon();
                grandSon.setPunchSonId(punchSon.getId());
                grandSon.setPunchStatus(PunchStatus.NORMAL);
                punchGrandSonSer.save(grandSon);
            }
        } else {
            PunchGrandSon grandSon = new PunchGrandSon();
            grandSon.setPunchSonId(punchSon.getId());
            grandSon.setPunchStatus(punchStatus);
            punchGrandSonSer.save(grandSon);
        }
    }

    private Long num(String name, LocalDate date) throws SerException {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        LocalDate start = null;
        LocalDate end = null;
        if (day < 21) {   //21号前算上个月的计薪周期
            if (new String(month + "").length() < 2) {
                end = DateUtil.parseDate(year + "-0" + month + "-20");    //当月的20号
            } else {
                end = DateUtil.parseDate(year + "-" + month + "-20");    //当月的20号
            }
            if (1 != month) {
                month = month - 1;
            } else {    //1月的上一个月为上一年的12月
                year = year - 1;
                month = 12;
            }
            if (new String(month + "").length() < 2) {
                start = DateUtil.parseDate(year + "-0" + month + "-21");   //上月的21号
            } else {
                start = DateUtil.parseDate(year + "-" + month + "-21");   //上月的21号
            }
        } else {
            if (new String(month + "").length() < 2) {
                start = DateUtil.parseDate(year + "-0" + month + "-21");   //当月的21号
            } else {
                start = DateUtil.parseDate(year + "-" + month + "-21");   //当月的21号
            }
            if (12 != month) {
                month = month + 1;
            } else {
                year = year + 1;
                month = 1;
            }
            if (new String(month + "").length() < 2) {
                end = DateUtil.parseDate(year + "-0" + month + "-20");    //下月的20号
            } else {
                end = DateUtil.parseDate(year + "-" + month + "-20");    //下月的20号
            }
        }
        LocalDate[] times = new LocalDate[]{start, end};
        PunchDTO punchDTO = new PunchDTO();
        punchDTO.getConditions().add(Restrict.eq("name", name));
        punchDTO.getConditions().add(Restrict.between("date", times));
        //获取当前月所有的上班卡信息
        List<String> punchIds = punchSer.findByCis(punchDTO).stream().map(punch -> punch.getId()).collect(Collectors.toList());
        PunchSonDTO punchSonDTO = new PunchSonDTO();
        punchSonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
        punchSonDTO.getConditions().add(Restrict.in("punchId", punchIds));
        List<PunchSon> list = super.findByCis(punchSonDTO);
        long num = 0;
        for (PunchSon p : list) {
            LocalDate time = punchSer.findById(p.getPunchId()).getDate();
            LocalDateTime min = DateUtil.parseDateTime(DateUtil.dateToString(time) + " 08:31:00");
            LocalDateTime max = DateUtil.parseDateTime(DateUtil.dateToString(time) + " 08:40:00");
            long a = DateUtil.mis(p.getPunchTime(), min);
            long b = DateUtil.mis(p.getPunchTime(), max);
            if (a >= 0 && b < 0) {
                num++;
            }
        }
        return num;
    }

    @Override
    public List<String> string(Double longitude, Double latitude, String area) throws SerException {
        List<String> list = new ArrayList<>();
        String name = userAPI.currentUser().getUsername();
        PunchStatus punchStatus = punchStatus(longitude, latitude, name);
        if (PunchStatus.NORMAL.equals(punchStatus)) {
            list.add("已进入考勤范围：" + area);
            list.add("green");
            return list;
        }
        list.add("您不在考勤范围内：" + area);
        list.add("red");
        return list;
    }

    private PunchStatus punchStatus(Double longitude, Double latitude, String name) throws SerException {
        DepartmentDetailBO departmentDetailBO = positionDetailUserAPI.areaAndDepart(name);
        if (null == departmentDetailBO) {
            throw new SerException("系统异常，无法查出当前用户所属地区");
        }
        ArrestPointDTO arrestPointDTO = new ArrestPointDTO();
        arrestPointDTO.getConditions().add(Restrict.eq("area", departmentDetailBO.getArea()));
        arrestPointDTO.getConditions().add(Restrict.eq("status", Status.START));
        List<ArrestPoint> arrestPoints = arrestPointSer.findByCis(arrestPointDTO);
        if (arrestPoints.isEmpty()) {
            throw new SerException("该用户所属的地区没有驻点");
        }
        for (ArrestPoint arrestPoint : arrestPoints) {
            double r = 6371;//地球半径千米
            double dis = Double.parseDouble(arrestPoint.getRange() + "") / 1000;   //范围，千米
            double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(arrestPoint.getLatitude() * Math.PI / 180));
            dlng = dlng * 180 / Math.PI;//角度转为弧度
            double dlat = dis / r;
            dlat = dlat * 180 / Math.PI;
            double minlat = arrestPoint.getLatitude() - dlat;
            double maxlat = arrestPoint.getLatitude() + dlat;    //最大的纬度
            double minlng = arrestPoint.getLongitude() - dlng;
            double maxlng = arrestPoint.getLongitude() + dlng;   //最大的经度
            boolean b1 = latitude >= minlat && latitude <= maxlat;
            boolean b2 = longitude >= minlng && longitude <= maxlng;
            if (b1 && b2) {
                return PunchStatus.NORMAL;
            }
        }
        return PunchStatus.OUTSIDE;
    }


    @Override
    public List<PunchBO> list(PunchDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        if (StringUtils.isNotBlank(dto.getName())) {
            name = dto.getName();
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            LocalDate s = DateUtil.parseDate(startTime);
            LocalDate e = DateUtil.parseDate(endTime);
            LocalDate[] date = new LocalDate[]{s, e};
            dto.getConditions().add(Restrict.between("date", date));
        }
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getSorts().add("date=desc");
        String depart = null;
        DepartmentDetailBO departmentDetailBO = positionDetailUserAPI.areaAndDepart(name);
        if (null != departmentDetailBO) {
            depart = departmentDetailBO.getDepartment();
        }
        List<Punch> punches = punchSer.findByCis(dto, true);
        List<PunchBO> bos = new ArrayList<>();
        for (Punch punch : punches) {
            PunchBO punchBO = BeanTransform.copyProperties(punch, PunchBO.class);
            if (null != depart) {
                punchBO.setProject(depart);
            }
            List<PunchSonBO> gos = new ArrayList<>();
            List<PunchSonBO> afters = new ArrayList<>();
            PunchSonDTO punchSonDTO = new PunchSonDTO();
            punchSonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
            PunchSon go = super.findOne(punchSonDTO);   //上班
            if (null != go) {
                PunchSonBO punchSonBO = BeanTransform.copyProperties(go, PunchSonBO.class);
                punchSonBO.setPunchStatus(findPunchStatus(go));
                gos.add(punchSonBO);
            }
            PunchSonDTO punchSonDTO1 = new PunchSonDTO();
            punchSonDTO1.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO1.getConditions().add(Restrict.eq("punchType", PunchType.AFTER));
            PunchSon after = super.findOne(punchSonDTO1);   //下班
            if (null != after) {
                PunchSonBO punchSonBO = BeanTransform.copyProperties(after, PunchSonBO.class);
                punchSonBO.setPunchStatus(findPunchStatus(after));
                afters.add(punchSonBO);
            }
            punchBO.setGos(gos);
            punchBO.setAfters(afters);
            bos.add(punchBO);
        }
        return bos;
    }

    @Override
    public List<PunchPhoneBO> phoneList(PunchDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        if (StringUtils.isNotBlank(dto.getName())) {
            name = dto.getName();
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            LocalDate s = DateUtil.parseDate(startTime);
            LocalDate e = DateUtil.parseDate(endTime);
            LocalDate[] date = new LocalDate[]{s, e};
            dto.getConditions().add(Restrict.between("date", date));
        }
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getSorts().add("date=desc");
        String depart = null;
        DepartmentDetailBO departmentDetailBO = positionDetailUserAPI.areaAndDepart(name);
        if (null != departmentDetailBO) {
            depart = departmentDetailBO.getDepartment();
        }
        List<Punch> punches = punchSer.findByCis(dto, true);
        List<PunchPhoneBO> bos = new ArrayList<>();
        for (Punch punch : punches) {
            PunchPhoneBO punchBO = BeanTransform.copyProperties(punch, PunchPhoneBO.class);
            if (null != depart) {
                punchBO.setProject(depart);
            }
            List<PunchSonPhoneBO> gos = new ArrayList<>();
            List<PunchSonPhoneBO> afters = new ArrayList<>();
            PunchSonDTO punchSonDTO = new PunchSonDTO();
            punchSonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
            PunchSon go = super.findOne(punchSonDTO);   //上班
            if (null != go) {
                PunchSonPhoneBO punchSonBO = BeanTransform.copyProperties(go, PunchSonPhoneBO.class);
                punchSonBO.setSons(phonePunchStatus(go));
                gos.add(punchSonBO);
            }
            PunchSonDTO punchSonDTO1 = new PunchSonDTO();
            punchSonDTO1.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO1.getConditions().add(Restrict.eq("punchType", PunchType.AFTER));
            PunchSon after = super.findOne(punchSonDTO1);   //下班
            if (null != after) {
                PunchSonPhoneBO punchSonBO = BeanTransform.copyProperties(after, PunchSonPhoneBO.class);
                punchSonBO.setSons(phonePunchStatus(after));
                afters.add(punchSonBO);
            }
            punchBO.setGos(gos);
            punchBO.setAfters(afters);
            bos.add(punchBO);
        }
        return bos;
    }

    private String findPunchStatus(PunchSon punchSon) throws SerException {
        PunchGrandSonDTO dto = new PunchGrandSonDTO();
        dto.getConditions().add(Restrict.eq("punchSonId", punchSon.getId()));
        List<PunchGrandSon> sons = punchGrandSonSer.findByCis(dto);   //查看打卡状态
        StringBuilder punchStatus = new StringBuilder();
        for (int i = 0; i < sons.size(); i++) {
            if (i == sons.size() - 1) {
                punchStatus.append(sons.get(i).getPunchStatus().toString());
            } else {
                punchStatus.append(sons.get(i).getPunchStatus().toString() + ",");
            }
        }
        return punchStatus.toString();
    }

    private List<PunchGrandSonBO> phonePunchStatus(PunchSon punchSon) throws SerException {
        List<PunchGrandSonBO> list = new ArrayList<>();
        PunchGrandSonDTO dto = new PunchGrandSonDTO();
        dto.getConditions().add(Restrict.eq("punchSonId", punchSon.getId()));
        List<PunchGrandSon> sons = punchGrandSonSer.findByCis(dto);   //查看打卡状态
        for (PunchGrandSon son : sons) {
            PunchGrandSonBO grandSonBO = new PunchGrandSonBO();
            grandSonBO.setPunchStatus(son.getPunchStatus());
            list.add(grandSonBO);
        }
        return list;
    }

    @Override
    public Long count(PunchDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        if (StringUtils.isNotBlank(dto.getName())) {
            name = dto.getName();
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            LocalDate s = DateUtil.parseDate(startTime);
            LocalDate e = DateUtil.parseDate(endTime);
            LocalDate[] date = new LocalDate[]{s, e};
            dto.getConditions().add(Restrict.between("date", date));
        }
        dto.getConditions().add(Restrict.eq("name", name));
        return punchSer.count(dto);
    }

    @Override
    public List<CaseCountMailBO> caseCountMail(PunchDTO dto) throws SerException {
        List<CaseCountMailBO> list = new ArrayList<>();
        LocalDate starts = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        long misDay = DateUtil.misDay(end, starts);
        dto.getConditions().add(Restrict.between("date", new LocalDate[]{starts, end}));
        CountType countType = dto.getCountType();
        String[] departs = dto.getDeparts();
        List<DepartmentDetailBO> detailBOS = null;
        if (CountType.DEPART.equals(countType)) {
            detailBOS = departmentDetailAPI.departByName(departs);
        }
        if (null != detailBOS) {
            Set<String> absenteeisms1 = new HashSet<>();
            Set<String> normalRests1 = new HashSet<>();
            Set<String> festivals1 = new HashSet<>();
            Set<String> outWorks1 = new HashSet<>();
            Set<String> vacateAttendances1 = new HashSet<>();
            Set<String> normals1 = new HashSet<>();
            Set<String> lates1 = new HashSet<>();
            for (DepartmentDetailBO depart : detailBOS) {
                Set<String> absenteeisms = new HashSet<>();
                Set<String> normalRests = new HashSet<>();
                Set<String> festivals = new HashSet<>();
                Set<String> outWorks = new HashSet<>();
                CaseABO abo = new CaseABO();
                abo.setDepart(depart.getDepartment());
                Set<String> names = departmentDetailAPI.departPersons(depart.getId());   //查出某部门下的所有人
                if (null != names && !names.isEmpty()) {
                    for (int i = 0; i <= misDay; i++) {
                        LocalDate start = starts.plusDays(i);
                        String s = DateUtil.dateToString(start);
                        List<Set<String>> situations = situations1(names, s);
                        absenteeisms.addAll(situations.get(0));
                        absenteeisms1.addAll(absenteeisms);
                        normalRests.addAll(situations.get(1));
                        normalRests1.addAll(normalRests);
                        festivals.addAll(situations.get(2));
                        festivals1.addAll(festivals);
                        outWorks.addAll(situations.get(3));
                        outWorks1.addAll(outWorks);
                    }
                    dto.getConditions().add(Restrict.in("name", names));
                    List<Punch> punches = punchSer.findByCis(dto);
                    List<PunchGrandSon> punchGrandSons = grandSons(punches);
                    //正常出勤
                    List<String> normalIds = punchGrandSons.stream().filter(punchGrandSon -> PunchStatus.NORMAL.equals(punchGrandSon.getPunchStatus())).map(PunchGrandSon::getPunchSonId).collect(Collectors.toList());
                    List<String> normals = names(normalIds);
                    normals1.addAll(normals);
                    //迟到出勤
                    List<String> lateIds = punchGrandSons.stream().filter(punchGrandSon -> PunchStatus.LATE.equals(punchGrandSon.getPunchStatus())).map(PunchGrandSon::getPunchSonId).collect(Collectors.toList());
                    List<String> lates = names(lateIds);
                    lates1.addAll(lates);
                    //有请假出勤人员
                    Set<String> vacateAttendances = new HashSet<>();
                    for (Punch punch : punches) {   //某部门下某时间段的所有打卡记录
                        if (checkVatecate(punch.getName(), DateUtil.dateToString(punch.getDate()))) {
                            vacateAttendances.add(punch.getName());
                        }
                    }
                    vacateAttendances1.addAll(vacateAttendances);
                    CaseCountMailBO bo = new CaseCountMailBO();
                    bo.setDepart(depart.getDepartment());
                    bo.setNormalAttendances(bos(normals));
                    bo.setLates(bos(lates));
                    bo.setVacateAttendances(bos(new ArrayList<>(vacateAttendances)));
                    bo.setAbsenteeisms(bos(new ArrayList<>(absenteeisms)));
                    bo.setNormalRests(bos(new ArrayList<>(normalRests)));
                    bo.setOutWorks(bos(new ArrayList<>(outWorks)));
                    bo.setFestivals(bos(new ArrayList<>(festivals)));
                    list.add(bo);
                    list.add(departCount(normals, lates, vacateAttendances, absenteeisms, normalRests, festivals, outWorks));
                }
            }
            list.add(departCount(new ArrayList<>(normals1), new ArrayList<>(lates1), vacateAttendances1, absenteeisms1, normalRests1, festivals1, outWorks1));
        }
        return list;
    }

    private CaseCountMailBO departCount(List<String> normals, List<String> lates, Set<String> vacateAttendances,
                                        Set<String> absenteeisms, Set<String> normalRests, Set<String> festivals, Set<String> outWorks) throws SerException {
        CaseCountMailBO depart = new CaseCountMailBO();
        depart.setDepart("合计");
        depart.setNormalAttendances(size(normals));
        depart.setLates(size(lates));
        depart.setVacateAttendances(size(new ArrayList<>(vacateAttendances)));
        depart.setAbsenteeisms(size(new ArrayList<>(absenteeisms)));
        depart.setNormalRests(size(new ArrayList<>(normalRests)));
        depart.setOutWorks(size(new ArrayList<>(outWorks)));
        depart.setFestivals(size(new ArrayList<>(festivals)));
        return depart;
    }

    private List<NormalAttendBO> size(List<String> list) throws SerException {
        List<NormalAttendBO> normalAttendances = new ArrayList<>();
        NormalAttendBO bo1 = new NormalAttendBO();
        bo1.setName(list.size() + "");
        normalAttendances.add(bo1);
        return normalAttendances;
    }

    private List<NormalAttendBO> bos(List<String> strings) throws SerException {
        List<NormalAttendBO> list = new ArrayList<>();
        for (String s : strings) {
            NormalAttendBO bo = new NormalAttendBO();
            bo.setName(s);
            list.add(bo);
        }
        return list;
    }

    private List<String> names(List<String> ids) throws SerException {
        if (!ids.isEmpty()) {
            PunchSonDTO dto = new PunchSonDTO();
            dto.getConditions().add(Restrict.in(ID, ids));
            List<PunchSon> sons = punchSonSer.findByCis(dto);
            List<String> puchIds = sons.stream().map(PunchSon::getPunchId).collect(Collectors.toList());
            PunchDTO punchDTO = new PunchDTO();
            punchDTO.getConditions().add(Restrict.in(ID, puchIds));
            return punchSer.findByCis(punchDTO).stream().map(Punch::getName).distinct().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<CaseCountBO> caseCount(PunchDTO dto) throws SerException {
        List<CaseCountBO> list = new ArrayList<>();
        LocalDate starts = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        long misDay = DateUtil.misDay(end, starts);
        dto.getConditions().add(Restrict.between("date", new LocalDate[]{starts, end}));
        CountType countType = dto.getCountType();
        String[] departs = dto.getDeparts();
        List<DepartmentDetailBO> detailBOS = null;
        if (CountType.DEPART.equals(countType)) {
            if (null == departs) {
                throw new SerException("部门汇总必须选择部门");
            }
            detailBOS = departmentDetailAPI.departByName(departs);
        } else if (CountType.WHOLE.equals(countType)) {
            detailBOS = departmentDetailAPI.findStatus();
        }
        if (null != detailBOS) {
            long sumNormalAttendance = 0;
            long sumLateAttendance = 0;
            long sumVacateAttendance = 0;
            long sumAbsenteeism = 0;
            long sumNormalRest = 0;
            long sumOutWork = 0;
            long sumFestival = 0;    //计算合计
            Set<String> areas = detailBOS.stream().map(DepartmentDetailBO::getArea).collect(Collectors.toSet());
            for (String area : areas) {
                List<CaseABO> abos = new ArrayList<>();
                CaseCountBO caseCountBO = new CaseCountBO();
                caseCountBO.setArea(area);
                List<DepartmentDetailBO> departList = detailBOS.stream().filter(departmentDetailBO -> area.equals(departmentDetailBO.getArea())).collect(Collectors.toList());
                for (DepartmentDetailBO depart : departList) {
                    CaseABO abo = new CaseABO();
                    abo.setDepart(depart.getDepartment());
                    Set<String> names = departmentDetailAPI.departPersons(depart.getId());   //查出某部门下的所有人
                    if (null != names && !names.isEmpty()) {
                        long absenteeism = 0;   //旷工情况
                        long normalRest = 0;  //正常休息情况
                        long festival = 0;   //法定节假日休息情况
                        long outWork = 0;   //加班情况
                        for (int i = 0; i <= misDay; i++) {
                            LocalDate start = starts.plusDays(i);
                            String s = DateUtil.dateToString(start);
                            List<Long> situations = situations(names, s);
                            absenteeism += situations.get(0);
                            normalRest += situations.get(1);
                            festival += situations.get(2);
                            outWork += situations.get(3);
                        }
                        if (!names.isEmpty()) {
                            dto.getConditions().add(Restrict.in("name", names));
                        }
                        List<Punch> punches = punchSer.findByCis(dto);
                        List<PunchGrandSon> punchGrandSons = grandSons(punches);
                        //正常出勤
                        long normalAttendance = punchGrandSons.stream().filter(punchGrandSon -> PunchStatus.NORMAL.equals(punchGrandSon.getPunchStatus())).count();
                        //迟到出勤
                        long lateAttendance = punchGrandSons.stream().filter(punchGrandSon -> PunchStatus.LATE.equals(punchGrandSon.getPunchStatus())).count();
                        //有请假出勤人员
                        long vacateAttendance = 0;
                        for (Punch punch : punches) {   //某部门下某时间段的所有打卡记录
                            if (checkVatecate(punch.getName(), DateUtil.dateToString(punch.getDate()))) {
                                vacateAttendance++;
                            }
                        }
                        sumNormalAttendance += normalAttendance;
                        sumLateAttendance += lateAttendance;
                        sumVacateAttendance += vacateAttendance;
                        sumAbsenteeism += absenteeism;
                        sumNormalRest += normalRest;
                        sumOutWork += outWork;
                        sumFestival += festival;
                        abo.setNormalAttendance(normalAttendance);
                        abo.setLateAttendance(lateAttendance);
                        abo.setVacateAttendance(vacateAttendance);
                        abo.setAbsenteeism(absenteeism);
                        abo.setNormalRest(normalRest);
                        abo.setOutWork(outWork);
                        abo.setFestival(festival);
                        abos.add(abo);
                    }
                }
                caseCountBO.setSons(abos);
                list.add(caseCountBO);
            }
            CaseCountBO sum = sum(sumNormalAttendance, sumLateAttendance, sumVacateAttendance, sumAbsenteeism, sumNormalRest, sumOutWork, sumFestival);
            list.add(sum);
        }
        return list;
    }

    private CaseCountBO sum(long sumNormalAttendance, long sumLateAttendance, long sumVacateAttendance,
                            long sumAbsenteeism, long sumNormalRest, long sumNormalOutWork, long sumFestival) throws SerException {
        CaseCountBO sum = new CaseCountBO();
        List<CaseABO> abos = new ArrayList<>();
        CaseABO abo = new CaseABO();
        abo.setDepart("合计");
        abo.setNormalAttendance(sumNormalAttendance);
        abo.setLateAttendance(sumLateAttendance);
        abo.setVacateAttendance(sumVacateAttendance);
        abo.setAbsenteeism(sumAbsenteeism);
        abo.setNormalRest(sumNormalRest);
        abo.setOutWork(sumNormalOutWork);
        abo.setFestival(sumFestival);
        abos.add(abo);
        sum.setSons(abos);
        return sum;
    }

    private List<PunchGrandSon> grandSons(List<Punch> punches) throws SerException {
        if (punches.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> punchIds = punches.stream().map(Punch::getId).collect(Collectors.toList());
        PunchSonDTO sonDTO = new PunchSonDTO();
        sonDTO.getConditions().add(Restrict.in("punchId", punchIds));
        sonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
        List<PunchSon> punchSons = super.findByCis(sonDTO);
        List<String> sonIds = punchSons.stream().map(PunchSon::getId).collect(Collectors.toList());
        PunchGrandSonDTO grandSonDTO = new PunchGrandSonDTO();
        grandSonDTO.getConditions().add(Restrict.in("punchSonId", sonIds));
        List<PunchGrandSon> punchGrandSons = punchGrandSonSer.findByCis(grandSonDTO);
        return punchGrandSons;
    }

    //查看某人某天有无请假
    private Boolean checkVatecate(String name, String date) throws SerException {
        String sql = "SELECT id FROM attendance_vacate " +
                "WHERE name='" + name + "' AND '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d')";
        List<Vacate> list = super.findBySql(sql, Vacate.class, new String[]{"id"});
        if (null != list && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    private Set<String> outWork1(Set<String> names, String date) throws SerException {
        Set<String> set = new HashSet<>();
        String[] areasTemp = new String[names.size()];
        int i = 0;
        for (String s : names) {
            areasTemp[i] = "'" + s + "'";
            i++;
        }
        String areaStr = StringUtils.join(areasTemp, ",");
        String sql = "";
        if (!names.isEmpty()) {
            sql = "SELECT distinct id FROM attendance_overwork" +
                    "    WHERE overWorker in (" + areaStr + ")  AND '" + date + "' BETWEEN DATE_FORMAT(overStartTime,'%Y-%m-%d') AND DATE_FORMAT(overEndTime,'%Y-%m-%d')";
        } else {
            sql = "SELECT distinct id FROM attendance_overwork" +
                    "  where '" + date + "' BETWEEN DATE_FORMAT(overStartTime,'%Y-%m-%d') AND DATE_FORMAT(overEndTime,'%Y-%m-%d')";
        }
        List<OverWork> list = super.findBySql(sql, OverWork.class, new String[]{"id"});
        if (null != list) {
            for (OverWork o : list) {
                set.add(overWorkSer.findById(o.getId()).getOverWorker());
            }
        }
        return set;
    }

    //某部门某一天的加班情况
    private Long outWork(Set<String> names, String date) throws SerException {
        long outWork = 0;   //加班情况
        String[] areasTemp = new String[names.size()];
        int i = 0;
        for (String s : names) {
            areasTemp[i] = "'" + s + "'";
            i++;
        }
        String areaStr = StringUtils.join(areasTemp, ",");
        String sql = "";
        if (!names.isEmpty()) {
            sql = "SELECT distinct id FROM attendance_overwork" +
                    "    WHERE overWorker in (" + areaStr + ")  AND '" + date + "' BETWEEN DATE_FORMAT(overStartTime,'%Y-%m-%d') AND DATE_FORMAT(overEndTime,'%Y-%m-%d')";
        } else {
            sql = "SELECT distinct id FROM attendance_overwork" +
                    "  where '" + date + "' BETWEEN DATE_FORMAT(overStartTime,'%Y-%m-%d') AND DATE_FORMAT(overEndTime,'%Y-%m-%d')";
        }
        List<OverWork> list = super.findBySql(sql, OverWork.class, new String[]{"id"});
        if (null != list) {
            outWork = list.size();
        }
        return outWork;
    }

    @Override
    //查询某些人的某一天的加班时长
    public Double outWorkTime(String name, String date) throws SerException {
        String sql = " SELECT distinct id FROM attendance_overwork" +
                "    WHERE overWorker='" + name + "'  AND '" + date + "' BETWEEN DATE_FORMAT(overStartTime,'%Y-%m-%d') AND DATE_FORMAT(overEndTime,'%Y-%m-%d')";
        List<OverWork> list = super.findBySql(sql, OverWork.class, new String[]{"id"});
        if (null != list) {
            return list.stream().mapToDouble(OverWork::getOverLong).sum();
        }
        return 0d;
    }

    private List<Set<String>> situations1(Set<String> names, String date) throws SerException {
        Set<String> absenteeisms = new HashSet<>();
        Set<String> normalRests = new HashSet<>();
        normalRests.addAll(names);
        Set<String> festivals = new HashSet<>();
        festivals.addAll(names);
        Set<String> outWorks = outWork1(names, date);
        LocalDate current = DateUtil.parseDate(date);
        double extral = extralWork(date);   //补班天数
        if ((7 != current.getDayOfWeek().getValue()) && (!festival(date))) {  //当天要上班
            for (String name : names) {
                double finishTime = 0;  //完成任务天数
                List<ObjectBO> list = taskNodeAPI.taskSituation(new String[]{name}, date);
                if (null != list) {  //当天有任务的情况
                    double min = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 0 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double hour = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 1 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double day = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 2 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    finishTime = new BigDecimal(min / 60 / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + new BigDecimal(hour / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + day;
                }
                double vatecate = financeAttendanceSer.vacateDay(name, date);   //当天请假时长
                if (finishTime < 1 - vatecate) {
                    absenteeisms.add(name);
                }
            }
        } else if (extral > 0) {  //当天需要补班
            for (String name : names) {
                double finishTime = 0;  //完成任务天数
                List<ObjectBO> list = taskNodeAPI.taskSituation(new String[]{name}, date);
                if (null != list) {  //当天有任务的情况
                    Stream<ObjectBO> stream = list.stream();
                    double min = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 0 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double hour = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 1 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double day = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 2 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    finishTime = new BigDecimal(min / 60 / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + new BigDecimal(hour / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + day;
                }
                double vatecate = financeAttendanceSer.vacateDay(name, date);   //当天请假时长
                if (finishTime < extral - vatecate) {
                    absenteeisms.add(name);
                }
            }
        }
        PunchDTO punchDTO = new PunchDTO();
        punchDTO.getConditions().add(Restrict.eq("date", date));
        punchDTO.getConditions().add(Restrict.in("name", names));
        List<Punch> punches = punchSer.findByCis(punchDTO);
        if (7 == current.getDayOfWeek().getValue() && extral <= 0) {  //正常周日，不需要上班
            normalRests.removeAll(punches.stream().map(Punch::getName).collect(Collectors.toSet()));
        }
        if (festival(date)) {  //节假日
            festivals.removeAll(punches.stream().map(Punch::getName).collect(Collectors.toSet()));
        }
        List<Set<String>> list = new ArrayList<>();
        list.add(absenteeisms);
        list.add(normalRests);
        list.add(festivals);
        list.add(outWorks);
        return list;
    }

    //获取某部门某一天的旷天情况和正常休息情况和法定节假日休息情况,加班情况
    private List<Long> situations(Set<String> names, String date) throws SerException {
        List<Long> result = new ArrayList<>();
        long absenteeism = 0;  //旷工情况
        long normalRest = 0;   //正常休息情况
        long festival = 0;   //法定节假日休息情况
        long outWork = outWork(names, date);   //加班情况
        LocalDate current = DateUtil.parseDate(date);
        double extral = extralWork(date);   //补班天数
        if ((7 != current.getDayOfWeek().getValue()) && (!festival(date))) {  //当天要上班
            for (String name : names) {
                double finishTime = 0;  //完成任务天数
                List<ObjectBO> list = taskNodeAPI.taskSituation(new String[]{name}, date);
                if (null != list) {  //当天有任务的情况
                    double min = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 0 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double hour = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 1 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double day = list.stream().filter(objectBO -> 0 == objectBO.getTaskStatus() && 2 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    finishTime = new BigDecimal(min / 60 / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + new BigDecimal(hour / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + day;
                }
                double vatecate = financeAttendanceSer.vacateDay(name, date);   //当天请假时长
                if (finishTime < 1 - vatecate) {
                    absenteeism++;
                }
            }
        } else if (extral > 0) {  //当天需要补班
            for (String name : names) {
                double finishTime = 0;  //完成任务天数
                List<ObjectBO> list = taskNodeAPI.taskSituation(new String[]{name}, date);
                if (null != list) {  //当天有任务的情况
                    Stream<ObjectBO> stream = list.stream();
                    double min = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 0 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double hour = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 1 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    double day = stream.filter(objectBO -> 0 == objectBO.getTaskStatus() && 2 == objectBO.getActualType()).mapToDouble(ObjectBO::getActualTime).sum();
                    finishTime = new BigDecimal(min / 60 / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + new BigDecimal(hour / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + day;
                }
                double vatecate = financeAttendanceSer.vacateDay(name, date);   //当天请假时长
                if (finishTime < extral - vatecate) {
                    absenteeism++;
                }
            }
        }
        PunchDTO punchDTO = new PunchDTO();
        punchDTO.getConditions().add(Restrict.eq("date", date));
        punchDTO.getConditions().add(Restrict.in("name", names));
        List<Punch> punches = punchSer.findByCis(punchDTO);
        if (7 == current.getDayOfWeek().getValue() && extral <= 0) {  //正常周日，不需要上班
            normalRest = names.size() - punches.size();
        }
        if (festival(date)) {  //节假日
            festival = names.size() - punches.size();
        }
        result.add(absenteeism);
        result.add(normalRest);
        result.add(festival);
        result.add(outWork);
        return result;
    }

    //判断当天是否为节假日
    @Override
    public Boolean festival(String date) throws SerException {
        String sql = "SELECT id FROM attendance_holidayset " +
                "WHERE '" + date + "' BETWEEN startTime AND endTime";
        List<HolidaySet> holidaySets = super.findBySql(sql, HolidaySet.class, new String[]{"id"});
        if (null != holidaySets && !holidaySets.isEmpty()) {
            return true;
        }
        return false;
    }

    //获取某天的补班天数
    @Override
    public Double extralWork(String date) throws SerException {
        String sql = "SELECT id  FROM attendance_extraloverworkson " +
                "WHERE '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d')";
        List<ExtralOverWorkSon> list = super.findBySql(sql, ExtralOverWorkSon.class, new String[]{"id"});
        double time = 0;
        if (null != list) {
            for (ExtralOverWorkSon e : list) {
                ExtralOverWorkSon son = extralOverWorkSonSer.findById(e.getId());
                time += DateUtil.mis(son.getEndTime(), son.getStartTime());
            }
        }
        return time;
    }

    @Override
    public OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        OverWorkTimesVO overWorkTimesVO = new OverWorkTimesVO();

        String userName = overTimesDTO.getOverWorker();
        OverTimesType overTimesType = overTimesDTO.getOverTimesType();
        if (StringUtils.isBlank(userName)) {
            throw new SerException("打卡人员不能为空");
        }
        if (null == overTimesType) {
            throw new SerException("汇总时间类型不能为空");
        }

        LocalDate timeBegan = LocalDate.now();
        LocalDate timeEnd = LocalDate.now();
        switch (overTimesDTO.getOverTimesType()) {
            case WEEK:
                if (StringUtils.isBlank("" + overTimesDTO.getYear()) || StringUtils.isBlank("" + overTimesDTO.getMonth()) || StringUtils.isBlank("" + overTimesDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(overTimesDTO.getYear(), overTimesDTO.getMonth(), overTimesDTO.getWeek());
                timeBegan = dateDuring[0];
                timeEnd = dateDuring[1];
                break;
            case QUART:
                if (StringUtils.isBlank("" + overTimesDTO.getYear()) || overTimesDTO.getQuart() > 4 || overTimesDTO.getQuart() < 1) {
                    throw new SerException("年份(year)或季度（quart）不能为空,且year大于1900,quart在1～4之间");
                }
                switch (overTimesDTO.getQuart()) {
                    case 1:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-01-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-03-31");
                        break;
                    case 2:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-04-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-06-30");
                        break;
                    case 3:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-07-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-09-31");
                        break;
                    case 4:
                        timeBegan = DateUtil.parseDate(overTimesDTO.getYear() + "-10-01");
                        timeEnd = DateUtil.parseDate(overTimesDTO.getYear() + "-12-31");
                        break;
                    default:
                        break;
                }
                break;
            default:
                return null;
        }
        if (overTimesType.equals(OverTimesType.WEEK)) {
            while (timeBegan.isBefore(timeEnd) || timeBegan.isEqual(timeEnd)) {
                DayOfWeek week = timeBegan.getDayOfWeek();
                LocalDateTime start = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 0, 0, 1);
                LocalDateTime end = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 12, 59, 59);

                PunchDTO punch = new PunchDTO();
                punch.getConditions().add(Restrict.eq("name", userName));
                punch.getConditions().add(Restrict.eq("date", timeBegan));
                Punch p = punchSer.findOne(punch);

                PunchSonDTO overWorkDTO = new PunchSonDTO();
                overWorkDTO.getConditions().add(Restrict.eq("punch_id", p.getId()));
                overWorkDTO.getConditions().add(Restrict.between("punchTime", new LocalDateTime[]{start, end}));
                Long count = super.count(overWorkDTO);
                if (count == 1) {
                    //说明上班或下班没有打卡
                    count = 1L;
                } else if (count == 0) {
                    //说明上班和下班都没有打卡
                    count = 2L;
                } else if (count == 2) {
                    //说明都打了卡
                    count = 0L;
                }

                switch (week) {
                    case MONDAY:
                        overWorkTimesVO.setFirst("" + count);
                        break;
                    case TUESDAY:
                        overWorkTimesVO.setSecond("" + count);
                        break;
                    case WEDNESDAY:
                        overWorkTimesVO.setThird("" + count);
                        break;
                    case THURSDAY:
                        overWorkTimesVO.setFour("" + count);
                        break;
                    case FRIDAY:
                        overWorkTimesVO.setFive("" + count);
                        break;
                    case SATURDAY:
                        overWorkTimesVO.setSix("" + count);
                        break;
                    case SUNDAY:
                        overWorkTimesVO.setSeven("" + count);
                        break;
                    default:
                        break;

                }
                timeBegan = timeBegan.plusDays(1);
            }
            overWorkTimesVO.setFirst(StringUtils.isBlank(overWorkTimesVO.getFirst()) ? 0 + "" : overWorkTimesVO.getFirst());
            overWorkTimesVO.setSecond(StringUtils.isBlank(overWorkTimesVO.getSecond()) ? 0 + "" : overWorkTimesVO.getSecond());
            overWorkTimesVO.setThird(StringUtils.isBlank(overWorkTimesVO.getThird()) ? 0 + "" : overWorkTimesVO.getThird());
            overWorkTimesVO.setFour(StringUtils.isBlank(overWorkTimesVO.getFour()) ? 0 + "" : overWorkTimesVO.getFour());
            overWorkTimesVO.setFive(StringUtils.isBlank(overWorkTimesVO.getFive()) ? 0 + "" : overWorkTimesVO.getFive());
            overWorkTimesVO.setSix(StringUtils.isBlank(overWorkTimesVO.getSix()) ? 0 + "" : overWorkTimesVO.getSix());
            overWorkTimesVO.setSeven(StringUtils.isBlank(overWorkTimesVO.getSeven()) ? 0 + "" : overWorkTimesVO.getSeven());

        } else if (overTimesType.equals(OverTimesType.QUART)) {
            while (timeEnd.getYear() == timeBegan.getYear() && timeEnd.getMonthValue() - timeBegan.getMonthValue() <= 2 && timeEnd.getMonthValue() - timeBegan.getMonthValue() >= 0) {
                int monthMinus = timeEnd.getMonthValue() - timeBegan.getMonthValue();
                LocalDateTime start = LocalDateTime.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth(), 0, 0, 1);
                LocalDateTime end = start.with(TemporalAdjusters.lastDayOfMonth());
                LocalDate startDate = LocalDate.of(timeBegan.getYear(), timeBegan.getMonth(), timeBegan.getDayOfMonth());
                LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
                int totalDay = end.getDayOfMonth() - start.getDayOfMonth();

                PunchDTO punch = new PunchDTO();
                punch.getConditions().add(Restrict.eq("name", userName));
                punch.getConditions().add(Restrict.between("date", new LocalDate[]{startDate, endDate}));
                List<Punch> pList = punchSer.findByCis(punch);
                List<String> pid = pList.stream().map(Punch::getId).collect(Collectors.toList());

                PunchSonDTO overWorkDTO = new PunchSonDTO();
                overWorkDTO.getConditions().add(Restrict.in("punch_id", pid));
                overWorkDTO.getConditions().add(Restrict.between("punchTime", new LocalDateTime[]{start, end}));
                //数据库里面的打卡次数
                Long count = super.count(overWorkDTO);
                //本月应该要打卡的次数
                Long punchCount = totalDay * 2L;
                count = punchCount - count;

                switch (monthMinus) {
                    case 2:
                        overWorkTimesVO.setFirstMonth(start + "月-" + count);
                        break;
                    case 1:
                        overWorkTimesVO.setSecndMonth(start + "月-" + count);
                        break;
                    case 0:
                        overWorkTimesVO.setThirdMonth(start + "月-" + count);
                        break;
                    default:
                        break;

                }

                timeBegan = timeBegan.plusMonths(1);
            }
            overWorkTimesVO.setFirstMonth(StringUtils.isBlank(overWorkTimesVO.getFirstMonth()) ? 0 + "" : overWorkTimesVO.getFirstMonth());
            overWorkTimesVO.setSecndMonth(StringUtils.isBlank(overWorkTimesVO.getSecndMonth()) ? 0 + "" : overWorkTimesVO.getSecndMonth());
            overWorkTimesVO.setThirdMonth(StringUtils.isBlank(overWorkTimesVO.getThirdMonth()) ? 0 + "" : overWorkTimesVO.getThirdMonth());
        }

        overWorkTimesVO.setOverTimesType(overTimesType);
        overWorkTimesVO.setUserName(userName);


        return overWorkTimesVO;
    }

    @Override
    public byte[] exportExcel(PunchDTO dto) throws SerException {
        List<PunchExportBO> punchExportBOs = new ArrayList<>(0);
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            LocalDate s = DateUtil.parseDate(startTime);
            LocalDate e = DateUtil.parseDate(endTime);
            LocalDate[] date = new LocalDate[]{s, e};
            dto.getConditions().add(Restrict.between("date", date));
        }
        dto.getSorts().add("date=desc");
        List<Punch> punches = punchSer.findByCis(dto, false);
        List<PunchPhoneBO> bos = new ArrayList<>();
        for (Punch punch : punches) {
            PunchExportBO punchExportBO = BeanTransform.copyProperties(punch, PunchExportBO.class);

            PunchSonDTO punchSonDTO = new PunchSonDTO();
            punchSonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO.getConditions().add(Restrict.eq("punchType", PunchType.GO));
            PunchSon go = super.findOne(punchSonDTO);   //上班
            if (null != go) {
                BeanTransform.copyProperties(go, punchExportBO, false, "punchStatus", "punchSource", "punchType");
                punchExportBO.setPunchSource(transPunchSource(go.getPunchSource()));
                punchExportBO.setPunchStatus(phonePunchStatus1(go));
                punchExportBO.setPunchType("上班");
            }
            punchExportBOs.add(punchExportBO);

            PunchExportBO punchExportBO1 = BeanTransform.copyProperties(punch, PunchExportBO.class);
            PunchSonDTO punchSonDTO1 = new PunchSonDTO();
            punchSonDTO1.getConditions().add(Restrict.eq("punchId", punch.getId()));
            punchSonDTO1.getConditions().add(Restrict.eq("punchType", PunchType.AFTER));
            PunchSon afters = super.findOne(punchSonDTO1);   //下班
            if (null != afters) {
                BeanTransform.copyProperties(afters, punchExportBO1, false, "punchStatus", "punchSource", "punchType");
                punchExportBO1.setPunchSource(transPunchSource(afters.getPunchSource()));
                punchExportBO1.setPunchStatus(phonePunchStatus1(afters));
                punchExportBO1.setPunchType("下班");
            }
            punchExportBOs.add(punchExportBO1);
        }
        List<PunchExportExcel> punchExportExcels = BeanTransform.copyProperties(punchExportBOs, PunchExportExcel.class, "serialVersionUID");
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(punchExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        PunchImportExcel punchImportExcel = new PunchImportExcel();
        List<PunchImportExcel> list = new ArrayList<>(0);
        list.add(punchImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Transactional
    @Override
    public void upload(List<PunchImportExcel> tos) throws SerException {
        for (PunchImportExcel excel : tos) {
            Punch punch = BeanTransform.copyProperties(excel, Punch.class, true);
            PunchDTO punchDTO = new PunchDTO();
            punchDTO.getConditions().add(Restrict.eq("name", punch.getName()));
            punchDTO.getConditions().add(Restrict.eq("date", punch.getDate()));
            List<Punch> punches = punchSer.findByCis(punchDTO);
            if (null == punches || punches.size() < 1) {
                punch = punchSer.save(punch);
            }
            PunchSonImportBO punchSonImportBO = BeanTransform.copyProperties(excel, PunchSonImportBO.class, true);

            PunchDTO punchDTO1 = new PunchDTO();
            punchDTO1.getConditions().add(Restrict.eq("name", punchSonImportBO.getName()));
            punchDTO1.getConditions().add(Restrict.eq("date", punchSonImportBO.getDate()));
            List<Punch> punches1 = punchSer.findByCis(punchDTO1);
            if (null != punches1 && punches1.size() > 0) {
                PunchSon punchSon = BeanTransform.copyProperties(punchSonImportBO, PunchSon.class, true, "punchSource", "punchType");
                punchSon.setPunchSource(transPunchSource1(punchSonImportBO.getPunchSource()));
                punchSon.setPunchType(transPunchType(punchSonImportBO.getPunchType()));
                punchSon.setPunchId(punches1.get(0).getId());
                punchSon = punchSonSer.save(punchSon);

                String[] punchStatus = punchSonImportBO.getPunchStatus().split(",");
                for (String str : punchStatus) {
                    PunchGrandSon punchGrandSon = BeanTransform.copyProperties(punchSonImportBO, PunchGrandSon.class, true, "punchStatus");
                    punchGrandSon.setPunchSonId(punchSon.getId());
                    punchGrandSon.setPunchStatus(transPunchStatus1(str));
                    punchGrandSonSer.save(punchGrandSon);
                }
            }
        }
    }

    private String transPunchStatus(PunchStatus punchStatus) throws SerException {
        String str = "";
        switch (punchStatus) {
            case NORMAL:
                str = "正常";
                break;
            case LATE:
                str = "迟到";
                break;
            case OUTSIDE:
                str = "外勤";
                break;
            case FEE:
                str = "免扣";
                break;
            default:
                str = "外勤";
                break;
        }
        return str;
    }

    private PunchStatus transPunchStatus1(String string) throws SerException {
        PunchStatus punchStatus = null;
        switch (string) {
            case "正常":
                punchStatus = PunchStatus.NORMAL;
                break;
            case "迟到":
                punchStatus = PunchStatus.LATE;
                break;
            case "外勤":
                punchStatus = PunchStatus.OUTSIDE;
                break;
            case "免扣":
                punchStatus = PunchStatus.FEE;
                break;
            default:
                punchStatus = PunchStatus.OUTSIDE;
        }
        return punchStatus;
    }

    private String transPunchSource(PunchSource punchSource) throws SerException {
        String str = "";
        switch (punchSource) {
            case PC:
                str = "PC端";
                break;
            case MOBILE:
                str = "移动端";
                break;
            default:
                str = "移动端";
                break;
        }
        return str;
    }

    private PunchSource transPunchSource1(String string) throws SerException {
        PunchSource punchSource = null;
        switch (string) {
            case "PC端":
                punchSource = PunchSource.PC;
                break;
            case "移动端":
                punchSource = PunchSource.MOBILE;
                break;
            default:
                punchSource = PunchSource.MOBILE;
                break;
        }
        return punchSource;
    }

    private PunchType transPunchType(String string) throws SerException {
        PunchType punchType = null;
        switch (string) {
            case "上班":
                punchType = PunchType.GO;
                break;
            case "下班":
                punchType = PunchType.AFTER;
                break;
            default:
                punchType = PunchType.AFTER;
                break;
        }
        return punchType;

    }

    private String phonePunchStatus1(PunchSon punchSon) throws SerException {
        List<PunchGrandSonBO> list = new ArrayList<>();
        PunchGrandSonDTO dto = new PunchGrandSonDTO();
        dto.getConditions().add(Restrict.eq("punchSonId", punchSon.getId()));
        List<PunchGrandSon> sons = punchGrandSonSer.findByCis(dto);   //查看打卡状态
        StringBuilder sb = new StringBuilder();
        for (PunchGrandSon son : sons) {
            sb.append(transPunchStatus(son.getPunchStatus()));
            sb.append(",");
        }
        String str = sb.toString().substring(0, sb.toString().length() - 1);
        return str;
    }

    public static void main(String sfsd[]) {
        PunchSon punchSon = new PunchSon();
        punchSon.setArea("nu");
        PunchExportBO bo = new PunchExportBO();
//        bo.setArea("GUAF");
        bo.setName("fdsfdsfd");
        BeanUtils.copyProperties(punchSon, bo);
        System.out.println(bo.getArea());
        System.out.println(bo.getName());

    }

    @Override
    public Long currentUserLateCount() throws SerException {
        Long count = 0l;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        PunchDTO punchDTO = new PunchDTO();
        punchDTO.getConditions().add(Restrict.eq("name", userBO.getUsername()));
        List<Punch> punchs = punchSer.findByCis(punchDTO);
        if (punchs != null && punchs.size() > 0) {
            for (Punch punch : punchs) {
                PunchSonDTO punchSonDTO = new PunchSonDTO();
                punchSonDTO.getConditions().add(Restrict.eq("punchId", punch.getId()));
                List<PunchSon> punchSonList = super.findByCis(punchSonDTO);
                if (punchSonList != null && punchSonList.size() > 0) {
                    for (PunchSon punchSon : punchSonList) {
                        PunchGrandSonDTO punchGrandSonDTO = new PunchGrandSonDTO();
                        punchGrandSonDTO.getConditions().add(Restrict.eq("punchSonId", punchSon.getId()));
                        punchGrandSonDTO.getConditions().add(Restrict.eq("punchStatus", PunchStatus.LATE));
                        List<PunchGrandSon> punchGrandSonList = punchGrandSonSer.findByPage(punchGrandSonDTO);
                        if (punchGrandSonList != null && punchGrandSonList.size() > 0) {
                            count += punchGrandSonList.size();
                        }
                    }
                }
            }
        }
        return count;
    }

    @Override
    public Boolean isPunch(PunchSonTO to) throws SerException {

        String sql = " SELECT count(*) AS count " +
                "FROM goddess_taskallotment.attendance_punch ap LEFT JOIN goddess_taskallotment.attendance_punchson aps ON ap.id = aps.punch_id " +
                "WHERE  ap.date = '" + LocalDate.now() + "' AND aps.punchType = " + to.getPunchType().getCode() + " " +
                " and ap.name='" + userAPI.currentUser().getUsername() + "' ";

        List<Object> objects = punchSonSer.findBySql(sql);
        if (objects != null && objects.size() > 0) {
            String length = objects.get(0).toString();

            if (Integer.parseInt(length) > 0) {
                return true;
            } else {
                return false;
            }
        }
        return null;
    }

    @Override
    public List<PunchSonBO> getPunchSon(String date) throws SerException {
        //判断是否为空
        PunchDTO punchDTO = new PunchDTO();
        if (date!=null){
            punchDTO.getConditions().add(Restrict.eq("date",date));
        }else {
            punchDTO.getConditions().add(Restrict.eq("date",LocalDate.now()));
        }

        punchDTO.getConditions().add(Restrict.eq("name",userAPI.currentUser().getUsername()));
        List<Punch> punchList = punchSer.findByCis(punchDTO);

        if (!punchList.isEmpty() && punchList.size() > 0 ){
            Punch punch = punchList.get(0);
            PunchSonDTO sonDTO = new PunchSonDTO();
            sonDTO.getConditions().add(Restrict.eq("punchId",punch.getId()));
            List<PunchSon> punchSonList =  super.findByCis(sonDTO);
            List<PunchSonBO> punchSonBOS = BeanTransform.copyProperties(punchSonList,PunchSonBO.class);
            return punchSonBOS;
        }

        return null;
    }
}