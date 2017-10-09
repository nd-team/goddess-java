package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchGrandSonDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.entity.ArrestPoint;
import com.bjike.goddess.attendance.entity.Punch;
import com.bjike.goddess.attendance.entity.PunchGrandSon;
import com.bjike.goddess.attendance.entity.PunchSon;
import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.enums.PunchStatus;
import com.bjike.goddess.attendance.enums.PunchType;
import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private PunchGrandSonSer punchGrandSonSer;

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
        PunchStatus punchStatus = null;
        if (PunchSource.MOBILE.equals(to.getPunchSource())) {   //移动端
            Double longitude = to.getLongitude();
            Double latitude = to.getLatitude();
            punchStatus = punchStatus(longitude, latitude, userBO.getUsername());
        } else {   //pc端
            DepartmentDetailBO departmentDetailBO = positionDetailUserAPI.areaAndDepart(userBO.getUsername());
            if (null == departmentDetailBO) {
                throw new SerException("系统异常，无法查出当前用户所属地区");
            }
            if (to.getArea().contains(departmentDetailBO.getArea())) {
                punchStatus = PunchStatus.NORMAL;
            } else {
                punchStatus = PunchStatus.OUTSIDE;    //外勤
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
            long mis = DateUtil.mis(punchSon.getPunchTime(), time);
            if (mis >= 0) {
                PunchGrandSon grandSon = new PunchGrandSon();
                grandSon.setPunchSonId(punchSon.getId());
                grandSon.setPunchStatus(PunchStatus.LATE);
                punchGrandSonSer.save(grandSon);
                if (num < 3) {    //当前月迟到次数小于三次(10分钟以内)，免扣
                    PunchGrandSon grandSon1 = new PunchGrandSon();
                    grandSon1.setPunchSonId(punchSon.getId());
                    grandSon1.setPunchStatus(PunchStatus.FEE);
                    punchGrandSonSer.save(grandSon1);
                }
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
            end = DateUtil.parseDate(year + "-" + month + "-20");    //当月的20号
            if (1 != month) {
                month = month - 1;
            } else {    //1月的上一个月为上一年的12月
                year = year - 1;
                month = 12;
            }
            start = DateUtil.parseDate(year + "-" + month + "-21");   //上月的21号
        } else {
            start = DateUtil.parseDate(year + "-" + month + "-21");   //当月的21号
            if (12 != month) {
                month = month + 1;
            } else {
                year = year + 1;
                month = 1;
            }
            end = DateUtil.parseDate(year + "-" + month + "-20");    //下月的20号
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
    public String string(Double longitude, Double latitude, String area) throws SerException {
        String name = userAPI.currentUser().getUsername();
        PunchStatus punchStatus = punchStatus(longitude, latitude, name);
        if (PunchStatus.NORMAL.equals(punchStatus)) {
            return "已进入考勤范围：" + area;
        }
        return "您不在考勤范围内：" + area;
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
        List<Punch> punches = punchSer.findByCis(dto);
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
}