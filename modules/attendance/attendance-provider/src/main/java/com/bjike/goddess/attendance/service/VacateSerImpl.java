package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.*;
import com.bjike.goddess.attendance.dto.VacateAuditDTO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.dto.VacateSetDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.entity.VacateAudit;
import com.bjike.goddess.attendance.entity.VacateSet;
import com.bjike.goddess.attendance.enums.*;
import com.bjike.goddess.attendance.excel.VacateExportExcel;
import com.bjike.goddess.attendance.excel.VacateImportExcel;
import com.bjike.goddess.attendance.service.overtime.OverWorkSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private VacateAuditSer vacateAuditSer;
    @Autowired
    private VacateSetSer vacateSetSer;
    @Autowired
    private PunchSonSer punchSonSer;
    @Autowired
    private DayReportSer dayReportSer;
    @Autowired
    private OverWorkSer overWorkSer;
    @Autowired
    private FinanceAttendanceSer financeAttendanceSer;
    @Autowired
    private HolidaySetSer holidaySetSer;
    @Autowired
    private AuditTimeSetSer auditTimeSetSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private EventAPI eventAPI;

    private String content = "%s提交了%d天的请假申请，时间为%s至%s,请及时上系统查看审核";
    private String content1 = "%s提交了%d天的请假申请，时间为%s至%s,请注意工作交接情况";
    private String title = "%s的请假申请";

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
            flag = cusPermissionSer.getCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以查看");
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
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应岗位的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("vacate");
        obj.setDescribesion("请假审核列表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = punchSonSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("punchson");
        obj.setDescribesion("考勤列表");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = dayReportSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("dayReport");
        obj.setDescribesion("日报列表");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = vacateSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("vacateSet");
        obj.setDescribesion("请假设置");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = overWorkSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("overWork");
        obj.setDescribesion("加班审核列表");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase1 = financeAttendanceSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("financeAttendance");
        obj.setDescribesion("财务出勤审批列表");
        if (flagSeeBase1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase2 = holidaySetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("holidaySet");
        obj.setDescribesion("假期设置");
        if (flagSeeBase2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase3 = auditTimeSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("auditTimeSet");
        obj.setDescribesion("审批时间设置");
        if (flagSeeBase3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(VacateTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        String startDate = to.getStartDate();
        String startTime = to.getStartTime().toString() + ":00";
        LocalDateTime start = DateUtil.parseDateTime(startDate + " " +
                startTime);
        long mis = DateUtil.mis(start, LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("请假开始时间必须大于当前时间");
        }
        double time = getTime(to);
        check(to, time, userToken);   //检测是否符合请假设置
        String[] mains = to.getMains();
        String[] carbons = to.getCarbons();
        boolean falg = containManager(mains);   //主送人是否包含总经理
        if (time >= 3) {    //请假大于等于3天需提前1周
            LocalDate s = DateUtil.parseDate(startDate);
            long days = Math.abs(s.toEpochDay() - LocalDate.now().toEpochDay());
            if (days < 7) {
                throw new SerException("请假>=3天的要提前一周");
            }
            if (!falg) {
                throw new SerException("请假>=3天的要主送给总经理");
            }
        }
        List<String> list = positionUserDetailAPI.arrangementAndDepartId(to.getName());
        if (null != list) {
            String arrangement = list.get(0);
            if ("决策层".equals(arrangement)) {
                if (time > 1) {
                    if (!falg) {
                        throw new SerException("决策层请假>1天的要主送给总经理");
                    }
                }
            }
        }
        Vacate entity = BeanTransform.copyProperties(to, Vacate.class, true, "startTime", "endTime");
        entity.setMain(toString(mains));
        title = String.format(title, entity.getName());
        List<String> carbon = new ArrayList<>();
        if (null != carbons) {
            entity.setCarbon(toString(carbons));
            carbon = internalContactsAPI.getEmails(carbons);
            if (!carbon.isEmpty()) {
                String[] receivers = new String[carbon.size()];
                receivers = carbon.toArray(receivers);
//                content1 = String.format(content1, entity.getName(), time, to.getStartDate() + to.getStartTime().toString(), to.getEndDate() + to.getEndTime().toString());
                String content = "" + entity.getName() + "提交了" + time + "天的请假申请，时间为" + to.getStartDate() + to.getStartTime().toString() + "至" + to.getEndDate() + to.getEndTime().toString() + ",请及时上系统查看审核";
                send(title, content, receivers);
            }
        }
        if (time < 3) {
            LocalDate s = DateUtil.parseDate(startDate);
            long days = Math.abs(s.toEpochDay() - LocalDate.now().toEpochDay());
            if (days < 1) {
                entity.setAdvance(false);
            } else {
                entity.setAdvance(true);
            }
        }
        entity.setAdvance(true);
        entity.setConform(true);
        entity.setTime(time);
        entity.setStartTime(start);
        LocalDateTime end = DateUtil.parseDateTime(to.getEndDate() + " " +
                to.getEndTime() + ":00");
        entity.setEndTime(end);
        List<VacateAudit> sons = getSon(entity);
        entity.setVacateAudits(sons);
        entity.setDate(LocalDate.now());
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        List<String> main = internalContactsAPI.getEmails(mains);
        if (!main.isEmpty()) {
            String[] receivers = new String[main.size()];
            receivers = main.toArray(receivers);
//            content = String.format(content, entity.getName(), time, to.getStartDate() + to.getStartTime().toString(), to.getEndDate() + to.getEndTime().toString());
            String content = "" + entity.getName() + "提交了" + time + "天的请假申请，时间为" + to.getStartDate() + to.getStartTime().toString() + "至" + to.getEndDate() + to.getEndTime().toString() + ",请及时上系统查看审核";
            send(title, content, receivers);
        }
        for (String name : mains) {
            EventTO eventTO = new EventTO();
            eventTO.setName(name);
            eventTO.setProjectChineseName("考勤");
            eventTO.setProjectEnglishName("attendance");
            eventTO.setFunctionChineseName("请假");
            eventTO.setFunctionEnglishName("vacate");
            eventTO.setContent("请假审核");
            eventTO.setPermissions(Permissions.ADUIT);
            eventTO.setEventId(entity.getId());
            eventTO.setStatus("待审核");
            eventAPI.save(eventTO);
        }
        if (null != carbons) {
            for (String name : carbons) {
                EventTO eventTO = new EventTO();
                eventTO.setName(name);
                eventTO.setProjectChineseName("考勤");
                eventTO.setProjectEnglishName("attendance");
                eventTO.setFunctionChineseName("请假");
                eventTO.setFunctionEnglishName("vacate");
                eventTO.setContent("请假查看");
                eventTO.setPermissions(Permissions.SEE);
                eventTO.setEventId(entity.getId());
                eventTO.setStatus("待查看");
                eventAPI.save(eventTO);
            }
        }
    }

    private void check(VacateTO to, double time, String userToken) throws SerException {
        VacateSetDTO dto = new VacateSetDTO();
        dto.getConditions().add(Restrict.eq("vacateType", to.getVacateType()));
        VacateSet vacateSet = vacateSetSer.findOne(dto);
//        String storageToken = storageUserAPI.getStorageToken("attendance", "123456", "attendance", userToken);
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setPath("/attendance/vacate" + to.getUuid());
//        fileInfo.setStorageToken(storageToken);
//        List<FileBO> list = fileAPI.list(fileInfo);
        if (null != vacateSet) {
            if (time > vacateSet.getMaxDay()) {
                throw new SerException("请假时间不能大于请假设置里的最大可选天数");
            }
//            if (vacateSet.getAttachment()) {
//                if (null == list || list.size() == 0) {
//                    throw new SerException(to.getVacateType().toString() + "必需要上传附件");
//                }
//            }
        }

    }

    private List<VacateAudit> getSon(Vacate entity) throws SerException {
        String[] mains = entity.getMain().split(",");
        List<VacateAudit> list = new ArrayList<>();
        for (String s : mains) {
            VacateAudit vacateAudit = new VacateAudit();
            vacateAudit.setName(s);
            vacateAudit.setVacate(entity);
            vacateAudit.setAduitStatus(AduitStatus.DOING);
            list.add(vacateAudit);
        }
        return list;
    }

    private void send(String title, String content, String[] receivers) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle(title);
        messageTO.setContent(content);
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }

    private Boolean containManager(String[] mains) throws SerException {
        boolean falg = false;
        List<PositionDetailUserBO> managers = positionUserDetailAPI.findManager();
        if (null != managers) {
            List<String> list = Arrays.asList(mains);
            for (PositionDetailUserBO p : managers) {
                if (list.contains(p.getName())) {
                    falg = true;
                    break;
                }
            }
        }
        return falg;
    }

    private String toString(String[] strings) throws SerException {
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

    private VacateBO tranBOPhone(Vacate vacate) throws SerException {
        VacateBO bo = BeanTransform.copyProperties(vacate, VacateBO.class);
        VacateAuditDTO dto = new VacateAuditDTO();
        dto.getConditions().add(Restrict.eq("vacate.id", vacate.getId()));
        List<VacateAudit> list = vacateAuditSer.findByCis(dto);
        List<VacateAuditBO> vacateAuditBOS = BeanTransform.copyProperties(list, VacateAuditBO.class);
        bo.setSons(vacateAuditBOS);
        //通过条数
        long agreeNum = list.stream().filter(vacateAudit -> AduitStatus.AGREE.equals(vacateAudit.getAduitStatus())).count();
        //不通过条数
        long rejectNum = list.stream().filter(vacateAudit -> AduitStatus.REJECT.equals(vacateAudit.getAduitStatus())).count();
        if (agreeNum == list.size()) {
            bo.setAduitStatus(AduitStatus.AGREE);
        } else if (rejectNum == list.size()) {
            bo.setAduitStatus(AduitStatus.REJECT);
        } else {
            bo.setAduitStatus(AduitStatus.DOING);
        }
        return bo;
    }

    private VacateBO tranBO(Vacate vacate) throws SerException {
        VacateBO bo = BeanTransform.copyProperties(vacate, VacateBO.class);
        VacateAuditDTO dto = new VacateAuditDTO();
        dto.getConditions().add(Restrict.eq("vacate.id", vacate.getId()));
        List<VacateAudit> list = vacateAuditSer.findByCis(dto);
        StringBuilder sb = new StringBuilder();
        for (VacateAudit v : list) {
            String advice = v.getAdvice();
            if (null != advice) {
                sb.append(v.getName() + ":" + advice + ";");
            }
        }
        //已审核条数
        int agreeNum = Integer.parseInt(list.stream().filter(vacateAudit -> AduitStatus.AGREE.equals(vacateAudit.getAduitStatus())).count() + "");
        int rejectNum = Integer.parseInt(list.stream().filter(vacateAudit -> AduitStatus.REJECT.equals(vacateAudit.getAduitStatus())).count() + "");
        if (agreeNum == list.size()) {
            bo.setAduitStatus(AduitStatus.AGREE);
        } else if (rejectNum == list.size()) {
            bo.setAduitStatus(AduitStatus.REJECT);
        } else {
            bo.setAduitStatus(AduitStatus.DOING);
        }
        bo.setAdvice(sb.toString());
        return bo;
    }

    @Override
    public List<VacateBO> auditList(VacateDTO dto) throws SerException {
        checkSeeIdentity();
        String name = userAPI.currentUser().getUsername();
        VacateAuditDTO vacateAuditDTO = new VacateAuditDTO();
        vacateAuditDTO.getConditions().add(Restrict.eq("name", name));
        List<VacateAudit> list = vacateAuditSer.findByCis(vacateAuditDTO);
        Set<String> vacateIds = list.stream().map(vacateAudit -> vacateAudit.getVacate().getId()).collect(Collectors.toSet());
        List<VacateBO> bos = new ArrayList<>();
        if (!vacateIds.isEmpty()) {
            dto.getConditions().add(Restrict.in(ID, vacateIds));
            dto.getSorts().add("startTime=desc");
            List<Vacate> vacates = super.findByCis(dto, true);
            for (Vacate v : vacates) {
                bos.add(tranBO(v));
            }
        }
        return bos;
    }

    @Override
    public List<VacateBO> auditListPhone(VacateDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        VacateAuditDTO vacateAuditDTO = new VacateAuditDTO();
        vacateAuditDTO.getConditions().add(Restrict.eq("name", name));
        List<VacateAudit> list = vacateAuditSer.findByCis(vacateAuditDTO);
        Set<String> vacateIds = list.stream().map(vacateAudit -> vacateAudit.getVacate().getId()).collect(Collectors.toSet());
        List<VacateBO> bos = new ArrayList<>();
        if (!vacateIds.isEmpty()) {
            dto.getConditions().add(Restrict.in(ID, vacateIds));
            dto.getSorts().add("date=desc");
            List<Vacate> vacates = super.findByCis(dto, true);
            for (Vacate v : vacates) {
                bos.add(tranBOPhone(v));
            }
        }
        return bos;
    }

    @Override
    public Long auditListCount(VacateDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        VacateAuditDTO vacateAuditDTO = new VacateAuditDTO();
        vacateAuditDTO.getConditions().add(Restrict.eq("name", name));
        List<VacateAudit> list = vacateAuditSer.findByCis(vacateAuditDTO);
        Set<String> vacateIds = list.stream().map(vacateAudit -> vacateAudit.getVacate().getId()).collect(Collectors.toSet());
        if (!vacateIds.isEmpty()) {
            dto.getConditions().add(Restrict.in(ID, vacateIds));
            return super.count(dto);
        } else {
            return 0l;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(VacateTO to) throws SerException {
        checkAddIdentity();
        Vacate entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        String name = userAPI.currentUser().getUsername();
        VacateAuditDTO dto = new VacateAuditDTO();
        dto.getConditions().add(Restrict.eq("vacate.id", entity.getId()));
        dto.getConditions().add(Restrict.eq("name", name));
        VacateAudit vacateAudit = vacateAuditSer.findOne(dto);
        if (!AduitStatus.DOING.equals(vacateAudit.getAduitStatus())) {
            throw new SerException("该请假记录已被审核");
        }
        vacateAudit.setAdvice(to.getAdvice());
        vacateAudit.setAduitStatus(to.getAduitStatus());
        vacateAudit.setDate(LocalDate.now());
        vacateAudit.setModifyTime(LocalDateTime.now());
        vacateAuditSer.update(vacateAudit);
        VacateBO bo = tranBO(entity);
        AduitStatus aduitStatus = bo.getAduitStatus();
        if (!AduitStatus.DOING.equals(aduitStatus)) {
            String vacateName = entity.getName();
            String email = internalContactsAPI.getEmail(vacateName);
            if (null != email) {
                String title = "请假审核";
                String content = "您" + bo.getStartTime() + "至" + bo.getEndTime() + "的请假申请审核" + aduitStatus.toString() + "";
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle(title);
                messageTO.setContent(content);
                messageTO.setReceivers(new String[]{email});
                messageAPI.send(messageTO);
            }
        }
        String eventId = eventAPI.findId(to.getId(), userAPI.currentUser().getUsername());
        if (null != eventId) {
            eventAPI.delete(eventId);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void fill(VacateTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        double time = getTime(to);
        check(to, time, userToken);   //检测是否符合请假设置
        String[] mains = to.getMains();
        boolean falg = containManager(mains);   //主送人是否包含总经理
        if (time >= 3) {    //请假大于等于3天需提前1周
            if (!falg) {
                throw new SerException("请假>=3天的要主送给总经理");
            }
        }
        List<String> list = positionUserDetailAPI.arrangementAndDepartId(to.getName());
        if (null != list) {
            String arrangement = list.get(0);
            if ("决策层".equals(arrangement)) {
                if (time > 1) {
                    if (!falg) {
                        throw new SerException("决策层请假>1天的要主送给总经理");
                    }
                }
            }
        }
        Vacate entity = BeanTransform.copyProperties(to, Vacate.class, true, "startTime", "endTime");
        entity.setMain(toString(mains));
        String[] carbons = to.getCarbons();
        if (null != carbons) {
            entity.setCarbon(toString(carbons));
        }
        title = String.format(title, entity.getName());
        entity.setAdvance(false);
        entity.setConform(true);
        entity.setTime(time);
        LocalDateTime start = DateUtil.parseDateTime(to.getStartDate() + " " +
                to.getStartTime() + ":00");
        LocalDateTime end = DateUtil.parseDateTime(to.getEndDate() + " " +
                to.getEndTime() + ":00");
        entity.setStartTime(start);
        entity.setEndTime(end);
        List<VacateAudit> sons = getSon(entity);
        entity.setVacateAudits(sons);
        entity.setDate(LocalDate.now());
        super.save(entity);
        List<String> main = internalContactsAPI.getEmails(mains);
        if (!main.isEmpty()) {
            String[] receivers = new String[main.size()];
            receivers = main.toArray(receivers);
//            content = String.format(content, entity.getName(), time, to.getStartDate() + to.getStartTime().toString(), to.getEndDate() + to.getEndTime().toString());
            String content = "" + entity.getName() + "提交了" + time + "天的请假申请，时间为" + to.getStartDate() + to.getStartTime().toString() + "至" + to.getEndDate() + to.getEndTime().toString() + ",请及时上系统查看审核";
            send(title, content, receivers);
        }
        for (String name : mains) {
            EventTO eventTO = new EventTO();
            eventTO.setName(name);
            eventTO.setProjectChineseName("考勤");
            eventTO.setProjectEnglishName("attendance");
            eventTO.setFunctionChineseName("请假");
            eventTO.setFunctionEnglishName("vacate");
            eventTO.setContent("请假审核");
            eventTO.setPermissions(Permissions.ADUIT);
            eventTO.setEventId(entity.getId());
            eventTO.setStatus("待审核");
            eventAPI.save(eventTO);
        }
    }

    @Override
    public List<VacateBO> list(VacateDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("name", name));
        List<Vacate> list = super.findByCis(dto, true);
        List<VacateBO> bos = new ArrayList<>();
        for (Vacate v : list) {
            bos.add(tranBO(v));
        }
        return bos;
    }

    @Override
    public List<VacateBO> listPhone(VacateDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("name", name));
        List<Vacate> list = super.findByCis(dto, true);
        List<VacateBO> bos = new ArrayList<>();
        for (Vacate v : list) {
            bos.add(tranBOPhone(v));
        }
        return bos;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        Vacate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        List<VacateAudit> list = entity.getVacateAudits();
        long num = list.stream().filter(vacateAudit -> !AduitStatus.DOING.equals(vacateAudit.getAduitStatus())).count();
        if (num > 0) {
            throw new SerException("该请假记录已审核，不能删除");
        }
        super.remove(id);
    }

    @Override
    public VacateBO findByID(String id) throws SerException {
        Vacate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return tranBO(entity);
    }

    @Override
    public VacateBO findByIDPhone(String id) throws SerException {
        Vacate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return tranBOPhone(entity);
    }

    @Override
    public Long count(VacateDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("name", name));
        return super.count(dto);
    }

    @Override
    public List<VacateBO> findByCon(VacateConDTO vacateConDTO) throws SerException {
        LocalDateTime start = LocalDateTime.parse(vacateConDTO.getStartTime());
        LocalDateTime end = LocalDateTime.parse(vacateConDTO.getEndTime());

        VacateDTO dto = new VacateDTO();

        BeanTransform.copyProperties(vacateConDTO, dto, "serialVersionUID");
        dto.getConditions().add(Restrict.eq("name", vacateConDTO.getEmpName()));
        dto.getConditions().add(Restrict.between("startTime", new LocalDateTime[]{start, end}));


        List<Vacate> vacateList = super.findByCis(dto);
        return BeanTransform.copyProperties(vacateList, VacateBO.class);
    }

    @Override
    public VacateCountBO vacateCount(VacateDTO dto) throws SerException {
        if (CountType.DEPART.equals(dto.getCountType())) {
            String[] departs = dto.getDeparts();
            if (null == departs) {
                throw new SerException("部门汇总必须选择部门");
            }
        }
        return getCount(dto);
    }

    @Override
    public List<VacateMailBO> vacateCountMail(VacateDTO dto) throws SerException {
        List<Vacate> list = new ArrayList<>();
        List<String> ids = vacateIds(dto);
        for (String id : ids) {
            list.add(super.findById(id));
        }
        List<String> extralVacateIds = extralVacates(dto);
        ids.removeAll(extralVacateIds);
        List<Vacate> normals = new ArrayList<>();
        for (String id : ids) {
            normals.add(super.findById(id));   //正常工作日请假信息
        }
        List<Vacate> extrals = new ArrayList<>();
        for (String id : extralVacateIds) {
            extrals.add(super.findById(id));   //补班请假信息
        }
        Set<String> departs = list.stream().map(Vacate::getDepart).collect(Collectors.toSet());
        VacateCountBO vacateCountBO = new VacateCountBO();
        vacateCountBO.setTime(dto.getStartTime() + "-" + dto.getEndTime());
        List<VacateMailBO> bos = new ArrayList<>();
        double sumNormal = 0;   //正常请假时长总汇总
        double sumFill = 0;   //补班请假时长总汇总
        int sumNum = 0;    //总人数汇总
        for (String depart : departs) {
            double departNormal = 0;   //部门正常请假时长汇总
            double departFill = 0;   //部门补班请假时长汇总
            int departNum = 0;    //部门人数汇总
            VacateMailBO bo = new VacateMailBO();
            bo.setDepart(depart);
            List<VacateDBO> sons = new ArrayList<>();
            Set<String> names = list.stream().filter(vacate -> depart.equals(vacate.getDepart())).map(Vacate::getName).collect(Collectors.toSet());
            for (String name : names) {
                //正常请假时长
                double normalTime = normals.stream().filter(vacate -> name.equals(vacate.getName())).mapToDouble(Vacate::getTime).sum();
                departNormal += normalTime;
                sumNormal += normalTime;
                departNum++;
                sumNum++;
                //补班请假时长
                double fillTime = extrals.stream().filter(vacate -> name.equals(vacate.getName())).mapToDouble(Vacate::getTime).sum();
                departFill += fillTime;
                sumFill += fillTime;
                VacateDBO dbo = new VacateDBO();
                dbo.setName(name);
                dbo.setNormalTime(normalTime);
                dbo.setFillTime(fillTime);
                sons.add(dbo);
            }
            bo.setSons(sons);
            bos.add(bo);
            VacateDBO dbo = new VacateDBO(departNum + "", departFill, departNormal);
            VacateMailBO count = new VacateMailBO();
            count.setDepart("合计");
            List<VacateDBO> sons1 = new ArrayList<>();
            sons1.add(dbo);
            count.setSons(sons1);
            bos.add(count);
        }
        VacateDBO dbo = new VacateDBO(sumNum + "", sumFill, sumNormal);
        VacateMailBO count = new VacateMailBO();
        count.setDepart("总合计");
        List<VacateDBO> sons1 = new ArrayList<>();
        sons1.add(dbo);
        count.setSons(sons1);
        bos.add(count);
        return bos;
    }

    //补班的请假信息
    private List<String> extralVacates(VacateDTO dto) throws SerException {
        List<Vacate> list = new ArrayList<>();
        LocalDate startTimes = DateUtil.parseDate(dto.getStartTime());
        LocalDate endTime = DateUtil.parseDate(dto.getEndTime());
        long misDay = DateUtil.misDay(endTime, startTimes);
        for (long i = 0; i <= misDay; i++) {
            LocalDate startTime = startTimes.plusDays(i);
            String date = DateUtil.dateToString(startTime);
            double time = punchSonSer.extralWork(date);   //某天的补班天数
            if (time > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT id FROM attendance_vacate WHERE ");
                if (CountType.DEPART.equals(dto.getCountType())) {
                    sb.append("depart in ('" + StringUtils.join(dto.getDeparts(), "','") + "') AND");
                }
                sb.append("  '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d')");
                List<Vacate> vacates = super.findBySql(sb.toString(), Vacate.class, new String[]{"id"});
                if (null != vacates) {
                    list.addAll(vacates);
                }
            }
        }
        Set<String> ids = new HashSet<>();
        for (Vacate v : list) {
            ids.add(v.getId());  //把重复的去掉
        }
        return new ArrayList<>(ids);
    }

    private List<String> vacateIds(VacateDTO dto) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id FROM attendance_vacate WHERE ");
        if (CountType.DEPART.equals(dto.getCountType())) {
            sb.append("depart in ('" + StringUtils.join(dto.getDeparts(), "','") + "') AND");
        }
        sb.append(" (DATE_FORMAT(startTime, '%Y-%m-%d') BETWEEN '" + dto.getStartTime() + "' AND '" + dto.getEndTime() + "') AND" +
                "      (DATE_FORMAT(endTime, '%Y-%m-%d') BETWEEN '" + dto.getStartTime() + "' AND '" + dto.getEndTime() + "')");
        List<Vacate> list = super.findBySql(sb.toString(), Vacate.class, new String[]{"id"});
        if (null != list) {
            return list.stream().map(Vacate::getId).distinct().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private VacateCountBO getCount(VacateDTO dto) throws SerException {
        List<Vacate> list = new ArrayList<>();
        List<String> ids = vacateIds(dto);
        for (String id : ids) {
            list.add(super.findById(id));
        }
        List<String> extralVacateIds = extralVacates(dto);
        ids.removeAll(extralVacateIds);
        List<Vacate> normals = new ArrayList<>();
        for (String id : ids) {
            normals.add(super.findById(id));   //正常工作日请假信息
        }
        List<Vacate> extrals = new ArrayList<>();
        for (String id : extralVacateIds) {
            extrals.add(super.findById(id));   //补班请假信息
        }
        Set<String> areas = list.stream().map(Vacate::getArea).collect(Collectors.toSet());
        VacateCountBO vacateCountBO = new VacateCountBO();
        vacateCountBO.setTime(dto.getStartTime() + "-" + dto.getEndTime());
        List<VacateABO> abos = new ArrayList<>();
        double sumNormal = 0;   //正常请假时长总汇总
        double sumFill = 0;   //补班请假时长总汇总
        int sumNum = 0;    //总人数汇总
        for (String area : areas) {
            VacateABO abo = new VacateABO();
            abo.setArea(area);
            List<VacateBBO> bbos = new ArrayList<>();
            Set<String> departs = list.stream().filter(vacate -> area.equals(vacate.getArea())).map(Vacate::getDepart).collect(Collectors.toSet());
            for (String depart : departs) {
                double departNormal = 0;   //部门正常请假时长汇总
                double departFill = 0;   //部门补班请假时长汇总
                int departNum = 0;    //部门人数汇总
                VacateBBO bbo = new VacateBBO();
                bbo.setDepart(depart);
                List<VacateCBO> cbos = new ArrayList<>();
                Set<String> positions = list.stream().filter(vacate -> depart.equals(vacate.getDepart())).map(Vacate::getPosition).collect(Collectors.toSet());
                for (String position : positions) {
                    VacateCBO cbo = new VacateCBO();
                    cbo.setPosition(position);
                    List<VacateDBO> dbos = new ArrayList<>();
                    Set<String> names = list.stream().filter(vacate -> position.equals(vacate.getPosition())).map(Vacate::getName).collect(Collectors.toSet());
                    for (String name : names) {
                        //正常请假时长
                        double normalTime = normals.stream().filter(vacate -> name.equals(vacate.getName())).mapToDouble(Vacate::getTime).sum();
                        departNormal += normalTime;
                        sumNormal += normalTime;
                        departNum++;
                        sumNum++;
                        //补班请假时长
                        double fillTime = extrals.stream().filter(vacate -> name.equals(vacate.getName())).mapToDouble(Vacate::getTime).sum();
                        departFill += fillTime;
                        sumFill += fillTime;
                        VacateDBO dbo = new VacateDBO();
                        dbo.setName(name);
                        dbo.setNormalTime(normalTime);
                        dbo.setFillTime(fillTime);
                        dbos.add(dbo);
                    }
                    cbo.setSons(dbos);
                    cbos.add(cbo);
                }
                bbo.setSons(cbos);
                bbos.add(bbo);
                bbos.add(departCount(departNormal, departFill, departNum, null));
            }
            abo.setSons(bbos);
            abos.add(abo);
        }
        VacateABO vacateABO = new VacateABO();
        List<VacateBBO> bbos = new ArrayList<>();
        bbos.add(departCount(sumNormal, sumFill, sumNum, "总合计"));
        vacateABO.setSons(bbos);
        abos.add(vacateABO);
        vacateCountBO.setSons(abos);
        return vacateCountBO;
    }

    /**
     * 计算合计数量
     *
     * @throws SerException
     */
    private VacateBBO departCount(double departNormal, double departFill, int num, String type) throws SerException {
        VacateBBO departCount = new VacateBBO();
        if ("总合计".equals(type)) {
            departCount.setDepart("总合计");
        } else {
            departCount.setDepart("合计");
        }
        List<VacateCBO> cbos = new ArrayList<>();
        List<VacateDBO> dbos = new ArrayList<>();
        VacateCBO cbo = new VacateCBO();
        VacateDBO dbo = new VacateDBO();
        dbo.setNormalTime(departNormal);
        dbo.setFillTime(departFill);
        dbo.setName(num + "人");
        dbos.add(dbo);
        cbo.setSons(dbos);
        cbos.add(cbo);
        departCount.setSons(cbos);
        return departCount;
    }

    @Override
    public Double currentVacateTime(String start, String end, String date) throws SerException {
        StartTime startTime = null;
        EndTime endTime = null;
        switch (start) {
            case "08:30":
                startTime = StartTime.A;
                break;
            case "09:00":
                startTime = StartTime.B;
                break;
            case "09:30":
                startTime = StartTime.C;
                break;
            case "10:00":
                startTime = StartTime.D;
                break;
            case "10:30":
                startTime = StartTime.E;
                break;
            case "11:00":
                startTime = StartTime.F;
                break;
            case "11:30":
                startTime = StartTime.G;
                break;
            case "13:30":
                startTime = StartTime.H;
                break;
            case "14:00":
                startTime = StartTime.I;
                break;
            case "14:30":
                startTime = StartTime.J;
                break;
            case "15:00":
                startTime = StartTime.K;
                break;
            case "15:30":
                startTime = StartTime.L;
                break;
            case "16:00":
                startTime = StartTime.M;
                break;
            case "16:30":
                startTime = StartTime.N;
                break;
            case "17:00":
                startTime = StartTime.O;
                break;
            case "17:30":
                startTime = StartTime.P;
                break;
        }
        switch (end) {
            case "09:00":
                endTime = EndTime.A;
                break;
            case "09:30":
                endTime = EndTime.B;
                break;
            case "10:00":
                endTime = EndTime.C;
                break;
            case "10:30":
                endTime = EndTime.D;
                break;
            case "11:00":
                endTime = EndTime.E;
                break;
            case "11:30":
                endTime = EndTime.F;
                break;
            case "12:00":
                endTime = EndTime.G;
                break;
            case "14:00":
                endTime = EndTime.H;
                break;
            case "14:30":
                endTime = EndTime.I;
                break;
            case "15:00":
                endTime = EndTime.J;
                break;
            case "15:30":
                endTime = EndTime.K;
                break;
            case "16:00":
                endTime = EndTime.L;
                break;
            case "16:30":
                endTime = EndTime.M;
                break;
            case "17:00":
                endTime = EndTime.N;
                break;
            case "17:30":
                endTime = EndTime.O;
                break;
            case "18:00":
                endTime = EndTime.P;
                break;
        }
        VacateTO to = new VacateTO();
        to.setStartDate(date);
        to.setEndDate(date);
        to.setStartTime(startTime);
        to.setEndTime(endTime);
        return getTime(to);
    }

    @Override
    public OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        OverWorkTimesVO overWorkTimesVO = new OverWorkTimesVO();

        String userName = overTimesDTO.getOverWorker();
        OverTimesType overTimesType = overTimesDTO.getOverTimesType();
        if (StringUtils.isBlank(userName)) {
            throw new SerException("请假人员不能为空");
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

                VacateDTO overWorkDTO = new VacateDTO();
                overWorkDTO.getConditions().add(Restrict.eq("name", userName));
                overWorkDTO.getConditions().add(Restrict.lt_eq("startTime", start));
                overWorkDTO.getConditions().add(Restrict.lt_eq("endTime", end));
                Long count = super.count(overWorkDTO);

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

                VacateDTO overWorkDTO = new VacateDTO();
                overWorkDTO.getConditions().add(Restrict.eq("name", userName));
                overWorkDTO.getConditions().add(Restrict.lt_eq("startTime", start));
                overWorkDTO.getConditions().add(Restrict.lt_eq("endTime", end));
                Long count = super.count(overWorkDTO);

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
    public byte[] exportExcel(VacateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Vacate> list = super.findByCis(dto, true);
        List<VacateExportExcel> vacateExportExcels = new ArrayList<>();
        for (Vacate v : list) {
            vacateExportExcels.add(tranBO1(v));
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(vacateExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        VacateImportExcel vacateImportExcel = new VacateImportExcel();
        List<VacateImportExcel> vacateImportExcels = new ArrayList<>(0);
        vacateImportExcels.add(vacateImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(vacateImportExcels, excel);
        return bytes;
    }

    @Transactional
    @Override
    public void upload(List<VacateImportExcel> tos) throws SerException {
        for (VacateImportExcel vacateImportExcel : tos) {
            VacateImportBO vacateImportBO = BeanTransform.copyProperties(vacateImportExcel, VacateImportBO.class, "vacateType", "advance", "conform");
            vacateImportBO.setVacateType(transVacateType1(vacateImportExcel.getVacateType()));
            vacateImportBO.setAdvance(transAdvance1(vacateImportExcel.getAdvance()));
            vacateImportBO.setConform(transConform1(vacateImportExcel.getConform()));
            Vacate vacate = BeanTransform.copyProperties(vacateImportBO, Vacate.class, true);
            vacate.setUuid(UUID.randomUUID().toString());
            VacateAuditImportBO vacateAuditImportBO = BeanTransform.copyProperties(vacateImportExcel, VacateAuditImportBO.class, "aduitStatus");
            vacateAuditImportBO.setAduitStatus(transAduitStatus1(vacateImportExcel.getAduitStatus()));
            VacateAudit vacateAudit = new VacateAudit();
            vacateAudit.setAduitStatus(vacateAuditImportBO.getAduitStatus());
            vacateAudit.setName(vacateAuditImportBO.getName1());
            vacateAudit.setAdvice(vacateAuditImportBO.getAdvice());
            if (StringUtils.isNotBlank(vacateAuditImportBO.getDate1())) {
                vacateAudit.setDate(DateUtil.parseDate(vacateAuditImportBO.getDate1()));
            }
            vacateAudit.setVacate(vacate);
            List<VacateAudit> vacateAudits = new ArrayList<>(0);
            vacateAudits.add(vacateAudit);
            vacate.setVacateAudits(vacateAudits);
            vacate = super.save(vacate);
        }
    }

    private VacateExportExcel tranBO1(Vacate vacate) throws SerException {
        VacateBO bo = BeanTransform.copyProperties(vacate, VacateBO.class, false);
        VacateExportExcel vacateExportExcel = BeanTransform.copyProperties(bo, VacateExportExcel.class, "vacateType", "advance", "conform", "aduitStatus");
        vacateExportExcel.setVacateType(transVacateType(bo.getVacateType()));
        vacateExportExcel.setAdvance(transAdvance(bo.getAdvance()));
        vacateExportExcel.setConform(transConform(bo.getConform()));

        VacateAuditDTO dto = new VacateAuditDTO();
        dto.getConditions().add(Restrict.eq("vacate.id", vacate.getId()));
        List<VacateAudit> list = vacateAuditSer.findByCis(dto);
        StringBuilder sb = new StringBuilder();
        for (VacateAudit v : list) {
            String advice = v.getAdvice();
            if (null != advice) {
                sb.append(v.getName() + ":" + advice + ";");
            }
        }
        //已审核条数
        int agreeNum = Integer.parseInt(list.stream().filter(vacateAudit -> AduitStatus.AGREE.equals(vacateAudit.getAduitStatus())).count() + "");
        int rejectNum = Integer.parseInt(list.stream().filter(vacateAudit -> AduitStatus.REJECT.equals(vacateAudit.getAduitStatus())).count() + "");
        if (agreeNum == list.size()) {
            bo.setAduitStatus(AduitStatus.AGREE);
        } else if (rejectNum == list.size()) {
            bo.setAduitStatus(AduitStatus.REJECT);
        } else {
            bo.setAduitStatus(AduitStatus.DOING);
        }
        bo.setAdvice(sb.toString());

        vacateExportExcel.setAdvice(bo.getAdvice());
        vacateExportExcel.setAduitStatus(transAduitStatus(bo.getAduitStatus()));
        return vacateExportExcel;
    }

    private String transAduitStatus(AduitStatus aduitStatus) throws SerException {
        String string = "";
        switch (aduitStatus) {
            case DOING:
                string = "审核中";
                break;
            case AGREE:
                string = "通过";
                break;
            case REJECT:
                string = "不通过";
                break;
        }
        return string;
    }

    private AduitStatus transAduitStatus1(String string) throws SerException {
        AduitStatus aduitStatus = null;
        if (StringUtils.isBlank(string)) {
            return AduitStatus.DOING;
        }
        switch (string) {
            case "审核中":
                aduitStatus = AduitStatus.DOING;
                break;
            case "通过":
                aduitStatus = AduitStatus.AGREE;
                break;
            case "不通过":
                aduitStatus = AduitStatus.REJECT;
                break;
            default:
                aduitStatus = AduitStatus.DOING;
                break;
        }
        return aduitStatus;
    }

    private String transVacateType(VacateType vacateType) throws SerException {
        String str = "";
        switch (vacateType) {
            case ANNUAL:
                str = "年假";
                break;
            case MATTER:
                str = "事假";
                break;
            case SICK:
                str = "病假";
                break;
            case ADJUST:
                str = "调休";
                break;
            case MARRY:
                str = "婚假";
                break;
            case MATERNITY:
                str = "产假";
                break;
            case PATERNITY:
                str = "陪产假";
                break;
            case CHECK:
                str = "产检假";
                break;
            case FUNERAL:
                str = "丧假";
                break;
            case OTHER:
                str = "其他";
                break;
        }
        return str;
    }

    private VacateType transVacateType1(String str) throws SerException {
        VacateType vacateType = null;
        switch (str) {
            case "年假":
                vacateType = VacateType.ANNUAL;
                break;
            case "事假":
                vacateType = VacateType.MATTER;
                break;
            case "病假":
                vacateType = VacateType.SICK;
                break;
            case "调休":
                vacateType = VacateType.ADJUST;
                break;
            case "婚假":
                vacateType = VacateType.MARRY;
                break;
            case "产假":
                vacateType = VacateType.MATERNITY;
                break;
            case "陪产假":
                vacateType = VacateType.PATERNITY;
                break;
            case "产检假":
                vacateType = VacateType.CHECK;
                break;
            case "丧假":
                vacateType = VacateType.FUNERAL;
                break;
            case "其他":
                vacateType = VacateType.OTHER;
                break;
            default:
                vacateType = VacateType.OTHER;
                break;
        }
        return vacateType;
    }

    private String transAdvance(Boolean advance) throws SerException {
        String string = "";
        if (advance) {
            string = "是";
        } else {
            string = "否";
        }
        return string;
    }

    private Boolean transAdvance1(String advance) throws SerException {
        if (StringUtils.isBlank(advance)) {
            return null;
        }
        Boolean tar = false;
        if ("是".equals(advance)) {
            tar = true;
        }
        return tar;
    }

    private String transConform(Boolean conform) throws SerException {
        String string = "";
        if (conform) {
            string = "是";
        } else {
            string = "否";
        }
        return string;
    }

    private Boolean transConform1(String conform) throws SerException {
        if (StringUtils.isBlank(conform)) {
            return null;
        }
        Boolean tar = false;
        if ("是".equals(conform)) {
            tar = true;
        }
        return tar;
    }
}