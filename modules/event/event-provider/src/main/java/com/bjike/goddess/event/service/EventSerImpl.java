package com.bjike.goddess.event.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.event.bo.*;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.EventTimeSetDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.entity.Event;
import com.bjike.goddess.event.entity.EventTimeSet;
import com.bjike.goddess.event.entity.Father;
import com.bjike.goddess.event.entity.TimeSet;
import com.bjike.goddess.event.enums.*;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.event.to.GuidePermissionTO;
import com.bjike.goddess.event.vo.SonPermissionObject;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.Even;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 事件业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eventSerCache")
@Service
public class EventSerImpl extends ServiceImpl<Event, EventDTO> implements EventSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private FatherSer fatherSer;
    @Autowired
    private TimeSetSer timeSetSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    @Autowired
    private EventTimeSetSer eventTimeSetSer;

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
                throw new SerException("您不是相应岗位的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("1");
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

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("event");
        obj.setDescribesion("所有人事件");
        if (flagSeeSign) {
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
            case COUNT:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public EventBO save(EventTO to) throws SerException {
        Event entity = BeanTransform.copyProperties(to, Event.class, true);
//        String project = to.getProject();
        Father father = new Father();
        father.setProjectChineseName(to.getProjectChineseName());
        father.setProjectEnglishName(to.getProjectEnglishName());
        father.setFunctionChineseName(to.getFunctionChineseName());
        father.setFunctionEnglishName(to.getFunctionEnglishName());
        fatherSer.save(father);
        UserBO userBO = userAPI.findByUsername(entity.getName());
        List<PositionDetailBO> positionDetailBOs = null;
        if (userBO != null) {
            positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        }
        if (positionDetailBOs != null && positionDetailBOs.size()>0) {
            entity.setArea(positionDetailBOs.get(0).getArea());
        }
        entity.setGetTime(LocalDateTime.now());
        entity.setFather(father);
        entity.setEventStatus(EventStatus.NOSEENODEAL);
        entity.setYear(LocalDateTime.now().getYear());
        entity.setMonth(LocalDateTime.now().getMonthValue());
        super.save(entity);
        return BeanTransform.copyProperties(entity, EventBO.class);
    }

    @Override
    public void deleteEvent(String eventId) throws SerException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.getConditions().add(Restrict.eq("eventId", eventId));
        List<Event> eventList = super.findByCis(eventDTO);
        super.remove(eventList);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(EventTO to) throws SerException {
        Event entity = super.findById(to.getId());
        entity.setEventStatus(to.getEventStatus());
        entity.setActualTime(DateUtil.parseDateTime(to.getActualTime()));
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quarzt() throws SerException {
        List<Event> list = super.findAll();
        for (Event event : list) {
            if (!EventStatus.HAVEDEAL.equals(event.getEventStatus())) {
                TimeSetBO timeSetBO = timeSetSer.timeSet(event.getName(), event.getPermissions());
                if ((null != timeSetBO) && Status.NORMAL.equals(timeSetBO.getStatus())) {
                    LocalDateTime lastTime = DateUtil.parseDateTime(timeSetBO.getLastTime());
                    IntervalType intervalType = timeSetBO.getIntervalType();
                    Integer interval = timeSetBO.getInterval();
                    LocalDateTime time = null;
                    if (null != intervalType && null != interval) {
                        switch (intervalType) {
                            case DAY:
                                time = lastTime.plusDays(interval);
                                break;
                            case HOUR:
                                time = lastTime.plusHours(interval);
                                break;
                            case MINUTE:
                                time = lastTime.plusMinutes(interval);
                                break;
                        }
                        Long mis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                                - time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();      //当前时间和发送时间作比较
                        if (mis <= 0) {
                            String email = internalContactsAPI.getEmail(event.getName());
                            TimeSet timeSet = BeanTransform.copyProperties(timeSetBO, TimeSet.class, true);
                            timeSet.setLastTime(LocalDateTime.now());
                            timeSet.setModifyTime(LocalDateTime.now());
                            timeSetSer.update(timeSet);     //更新上次发送时间
                            if (null != email) {
                                MessageTO messageTO = new MessageTO();
                                messageTO.setTitle("您有一件待办事件处理");
                                messageTO.setContent("您有一件项目名称为:" + event.getFather().getFunctionChineseName() + "的" + event.getContent() + "等待您去处理");
                                messageTO.setMsgType(MsgType.SYS);
                                messageTO.setSendType(SendType.EMAIL);
                                messageTO.setRangeType(RangeType.SPECIFIED);
                                //定时发送必须写
                                messageTO.setSenderId("SYSTEM");
                                messageTO.setSenderName("SYSTEM");
                                messageTO.setReceivers(new String[]{email});
                                messageAPI.send(messageTO);
                            }
                        }
                    }
                }
                Long mis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                        - event.getRequestTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                if (mis > 0) {
                    event.setEventStatus(EventStatus.NODEAL);
                    event.setModifyTime(LocalDateTime.now());
                    super.update(event);
                }
            }
        }
    }

    @Override
    public List<ContentBO> findByMonth(EventDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        int month = dto.getMonth();
        int year = dto.getYear();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.ne("eventStatus", EventStatus.HAVEDEAL.getValue()));
        List<Event> list = super.findByCis(dto);
        List<ContentBO> contentVOs = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Event event : list) {
                LocalDateTime request = event.getRequestTime();
                if (year == request.getYear() && month == request.getMonthValue()) {
                    ContentBO vo = new ContentBO();
                    vo.setTime(DateUtil.dateToString(request));
                    vo.setContent(event.getFather().getProjectChineseName() + "的" + event.getContent());
                    contentVOs.add(vo);
                }
            }
        }
        return contentVOs;
    }

    @Override
    public List<FatherBO> list(FatherDTO dto) throws SerException {
        String time = dto.getTime();
        Permissions[] permissionses = dto.getPermissionses();
        EventStatus[] eventStatuses = dto.getEventStatuses();
        String name = userAPI.currentUser().getUsername();
        UserBO userBO = userAPI.findByUsername(name);
        List<PositionDetailBO> positionDetailBOs = null;
        if (userBO != null) {
            positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        }
        List<Father> fathers = fatherSer.findByCis(dto, true);
        List<FatherBO> boList = new ArrayList<>();
        for (Father father : fathers) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.getSorts().add("requestTime=asc");
            if (StringUtils.isNotBlank(time)) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time), DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set = new HashSet<>();
                for (Permissions permissions : permissionses) {
                    if (permissions != null) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("permissions", integers));
                }
            }
            if (null != eventStatuses) {
                Set<Integer> set = new HashSet<>();
                for (EventStatus permissions : eventStatuses) {
                    if (permissions != null) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
                }
            }
            eventDTO.getConditions().add(Restrict.eq("father.id", father.getId()));
            eventDTO.getConditions().add(Restrict.eq("name", name));
            List<Event> list = super.findByCis(eventDTO);
            List<EventBO> eventBOs = BeanTransform.copyProperties(list, EventBO.class);
            if (null != eventBOs) {
                for (EventBO bo : eventBOs) {
                    TimeSetBO timeSetBO = timeSetSer.timeSet(bo.getName(), bo.getPermissions());
                    if (timeSetBO != null && null != timeSetBO.getIntervalTime() && null != timeSetBO.getColor()) {
                        bo.setRemindTime(timeSetBO.getIntervalTime());
                        bo.setColor(timeSetBO.getColor());
                    }
                    if ((null != positionDetailBOs) && (!positionDetailBOs.isEmpty())) {
//                        bo.setArea(positionDetailBOs.get(0).getArea());
                        bo.setDepart(positionDetailBOs.get(0).getDepartmentName());
                        bo.setModule(positionDetailBOs.get(0).getModuleName());
                        bo.setPosition(positionDetailBOs.get(0).getPosition());
                    }
                }
            }
            if (!list.isEmpty()) {
                FatherBO fatherBO = BeanTransform.copyProperties(father, FatherBO.class);
                fatherBO.setEventBOs(eventBOs);
                boList.add(fatherBO);
            }
        }
        return boList;
    }

    @Override
    public List<FatherBO> allList(FatherDTO dto) throws SerException {
        checkSeeIdentity();
        String time = dto.getTime();
        Permissions[] permissionses = dto.getPermissionses();
        EventStatus[] eventStatuses = dto.getEventStatuses();
        List<Father> fathers = fatherSer.findByCis(dto, true);
        List<FatherBO> boList = new ArrayList<>();
        for (Father father : fathers) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.getSorts().add("requestTime=asc");
            if (StringUtils.isNotBlank(time)) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time), DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set = new HashSet<>();
                for (Permissions permissions : permissionses) {
                    if (null != permissions) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("permissions", integers));
                }
            }
            if (null != eventStatuses) {
                Set<Integer> set = new HashSet<>();
                for (EventStatus permissions : eventStatuses) {
                    if (null != permissions) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
                }
            }
            eventDTO.getConditions().add(Restrict.eq("father.id", father.getId()));
            List<Event> list = super.findByCis(eventDTO);
            List<EventBO> eventBOs = BeanTransform.copyProperties(list, EventBO.class);
            if (null != eventBOs) {
                for (EventBO bo : eventBOs) {
                    TimeSetBO timeSetBO = timeSetSer.timeSet(bo.getName(), bo.getPermissions());
                    if (timeSetBO != null && null != timeSetBO.getIntervalTime() && null != timeSetBO.getColor()) {
                        bo.setRemindTime(timeSetBO.getIntervalTime());
                        bo.setColor(timeSetBO.getColor());
                    }
                    UserBO userBO = userAPI.findByUsername(bo.getName());
                    List<PositionDetailBO> positionDetailBOs = null;
                    if (userBO != null) {
                        positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
                    }
                    if ((null != positionDetailBOs) && (!positionDetailBOs.isEmpty())) {
//                        bo.setArea(positionDetailBOs.get(0).getArea());
                        bo.setDepart(positionDetailBOs.get(0).getDepartmentName());
                        bo.setModule(positionDetailBOs.get(0).getModuleName());
                        bo.setPosition(positionDetailBOs.get(0).getPosition());
                    }
                }
            }
            if (!list.isEmpty()) {
                FatherBO fatherBO = BeanTransform.copyProperties(father, FatherBO.class);
                fatherBO.setEventBOs(eventBOs);
                boList.add(fatherBO);
            }
        }
        return boList;
    }

    @Override
    public EventBO findByID(String id) throws SerException {
        Event entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, EventBO.class);
    }

    @Override
    public Long count(FatherDTO dto) throws SerException {
        String time = dto.getTime();
        Permissions[] permissionses = dto.getPermissionses();
        EventStatus[] eventStatuses = dto.getEventStatuses();
        String name = userAPI.currentUser().getUsername();
        List<Father> fathers = fatherSer.findByCis(dto);
        List<FatherBO> boList = new ArrayList<>();
        for (Father father : fathers) {
            EventDTO eventDTO = new EventDTO();
            if (StringUtils.isNotBlank(time)) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time), DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set = new HashSet<>();
                for (Permissions permissions : permissionses) {
                    if (null != permissions) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("permissions", integers));
                }
            }
            if (null != eventStatuses) {
                Set<Integer> set = new HashSet<>();
                for (EventStatus permissions : eventStatuses) {
                    if (permissions != null) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
                }
            }
            eventDTO.getConditions().add(Restrict.eq("father.id", father.getId()));
            eventDTO.getConditions().add(Restrict.eq("name", name));
            List<Event> list = super.findByCis(eventDTO);
            List<EventBO> eventBOs = BeanTransform.copyProperties(list, EventBO.class);
            if (!list.isEmpty()) {
                FatherBO fatherBO = BeanTransform.copyProperties(father, FatherBO.class);
                fatherBO.setEventBOs(eventBOs);
                boList.add(fatherBO);
            }
        }
        return Long.parseLong(boList.size() + "");
    }

    @Override
    public Long allCount(FatherDTO dto) throws SerException {
        String time = dto.getTime();
        Permissions[] permissionses = dto.getPermissionses();
        EventStatus[] eventStatuses = dto.getEventStatuses();
        List<Father> fathers = fatherSer.findByCis(dto);
        List<FatherBO> boList = new ArrayList<>();
        for (Father father : fathers) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.getSorts().add("getTime=asc");
            if (StringUtils.isNotBlank(time)) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time), DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set = new HashSet<>();
                for (Permissions permissions : permissionses) {
                    if (null != permissions) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("permissions", integers));
                }
            }
            if (null != eventStatuses) {
                Set<Integer> set = new HashSet<>();
                for (EventStatus permissions : eventStatuses) {
                    if (null != permissions) {
                        set.add(permissions.getValue());
                    }
                }
                if (!set.isEmpty()) {
                    Integer[] integers = new Integer[set.size()];
                    integers = set.toArray(integers);
                    eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
                }
            }
            eventDTO.getConditions().add(Restrict.eq("father.id", father.getId()));
            List<Event> list = super.findByCis(eventDTO);
            List<EventBO> eventBOs = BeanTransform.copyProperties(list, EventBO.class);
            if (!list.isEmpty()) {
                FatherBO fatherBO = BeanTransform.copyProperties(father, FatherBO.class);
                fatherBO.setEventBOs(eventBOs);
                boList.add(fatherBO);
            }
        }
        return Long.parseLong(boList.size() + "");
    }

    @Override
    public List<AreaCountBO> zongCount(EventDTO dto) throws SerException {
        checkSeeIdentity();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        Integer week = dto.getWeek();
        String[] areas = dto.getAreas();
        List<AreaCountBO> vos = new ArrayList<>();
        for (String area : areas) {
            dto.getConditions().add(Restrict.eq("area", area));
            if (null != year && null != month && null != week) {
                LocalDate[] times = DateUtil.getWeekTimes(year, month, week);
                dto.getConditions().add(Restrict.between("requestTime", times));
            } else if (null != year && null != month) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
            } else if (null != year) {
                dto.getConditions().add(Restrict.eq("year", year));
            }
            List<Event> list = super.findByCis(dto);
            int see = 0;
            int analyze = 0;
            int check = 0;
            int confirm = 0;
            int aduit = 0;
            int make = 0;
            int sum = 0;
            for (Event event : list) {
                sum++;
                Permissions permissions = event.getPermissions();
                switch (permissions) {
                    case SEE:
                        see++;
                        break;
                    case ANALYZE:
                        analyze++;
                        break;
                    case CHECK:
                        check++;
                        break;
                    case CONFIRM:
                        confirm++;
                        break;
                    case ADUIT:
                        aduit++;
                        break;
                    case MAKE:
                        make++;
                        break;
                }
            }
            if (sum != 0) {
                AreaCountBO areaCountVO = new AreaCountBO();
                areaCountVO.setArea(area);
                areaCountVO.setSee(see);
                areaCountVO.setAnalyze(analyze);
                areaCountVO.setCheck(check);
                areaCountVO.setConfirm(confirm);
                areaCountVO.setAduit(aduit);
                areaCountVO.setMake(make);
                areaCountVO.setSum(sum);
                vos.add(areaCountVO);
            }
        }
        return vos;
    }

    @Override
    public List<AreaCountBO> haveDealCount(EventDTO dto) throws SerException {
        checkSeeIdentity();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        Integer week = dto.getWeek();
        String[] areas = dto.getAreas();
        List<AreaCountBO> vos = new ArrayList<>();
        for (String area : areas) {
            dto.getConditions().add(Restrict.eq("area", area));
            dto.getConditions().add(Restrict.eq("eventStatus", EventStatus.HAVEDEAL.getValue()));
            if (null != year && null != month && null != week) {
                LocalDate[] times = DateUtil.getWeekTimes(year, month, week);
                dto.getConditions().add(Restrict.between("requestTime", times));
            } else if (null != year && null != month) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
            } else if (null != year) {
                dto.getConditions().add(Restrict.eq("year", year));
            }
            List<Event> list = super.findByCis(dto);
            int see = 0;
            int analyze = 0;
            int check = 0;
            int confirm = 0;
            int aduit = 0;
            int make = 0;
            int sum = 0;
            for (Event event : list) {
                sum++;
                Permissions permissions = event.getPermissions();
                switch (permissions) {
                    case SEE:
                        see++;
                        break;
                    case ANALYZE:
                        analyze++;
                        break;
                    case CHECK:
                        check++;
                        break;
                    case CONFIRM:
                        confirm++;
                        break;
                    case ADUIT:
                        aduit++;
                        break;
                    case MAKE:
                        make++;
                        break;
                }
            }
            if (sum != 0) {
                AreaCountBO areaCountVO = new AreaCountBO();
                areaCountVO.setArea(area);
                areaCountVO.setSee(see);
                areaCountVO.setAnalyze(analyze);
                areaCountVO.setCheck(check);
                areaCountVO.setConfirm(confirm);
                areaCountVO.setAduit(aduit);
                areaCountVO.setMake(make);
                areaCountVO.setSum(sum);
                vos.add(areaCountVO);
            }
        }
        return vos;
    }

    @Override
    public List<AreaCountBO> noDealCount(EventDTO dto) throws SerException {
        checkSeeIdentity();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        Integer week = dto.getWeek();
        String[] areas = dto.getAreas();
        List<AreaCountBO> vos = new ArrayList<>();
        for (String area : areas) {
            dto.getConditions().add(Restrict.eq("area", area));
            dto.getConditions().add(Restrict.in("eventStatus", new Integer[]{EventStatus.NOSEENODEAL.getValue(), EventStatus.SEENODEAL.getValue()}));
            if (null != year && null != month && null != week) {
                LocalDate[] times = DateUtil.getWeekTimes(year, month, week);
                dto.getConditions().add(Restrict.between("requestTime", times));
            } else if (null != year && null != month) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
            } else if (null != year) {
                dto.getConditions().add(Restrict.eq("year", year));
            }
            List<Event> list = super.findByCis(dto);
            int see = 0;
            int analyze = 0;
            int check = 0;
            int confirm = 0;
            int aduit = 0;
            int make = 0;
            int sum = 0;
            for (Event event : list) {
                sum++;
                Permissions permissions = event.getPermissions();
                switch (permissions) {
                    case SEE:
                        see++;
                        break;
                    case ANALYZE:
                        analyze++;
                        break;
                    case CHECK:
                        check++;
                        break;
                    case CONFIRM:
                        confirm++;
                        break;
                    case ADUIT:
                        aduit++;
                        break;
                    case MAKE:
                        make++;
                        break;
                }
            }
            if (sum != 0) {
                AreaCountBO areaCountVO = new AreaCountBO();
                areaCountVO.setArea(area);
                areaCountVO.setSee(see);
                areaCountVO.setAnalyze(analyze);
                areaCountVO.setCheck(check);
                areaCountVO.setConfirm(confirm);
                areaCountVO.setAduit(aduit);
                areaCountVO.setMake(make);
                areaCountVO.setSum(sum);
                vos.add(areaCountVO);
            }
        }
        return vos;
    }

    @Override
    public List<AreaCountBO> passNoDealCount(EventDTO dto) throws SerException {
        checkSeeIdentity();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        Integer week = dto.getWeek();
        String[] areas = dto.getAreas();
        List<AreaCountBO> vos = new ArrayList<>();
        for (String area : areas) {
            dto.getConditions().add(Restrict.eq("area", area));
            dto.getConditions().add(Restrict.eq("eventStatus", EventStatus.NODEAL.getValue()));
            if (null != year && null != month && null != week) {
                LocalDate[] times = DateUtil.getWeekTimes(year, month, week);
                dto.getConditions().add(Restrict.between("requestTime", times));
            } else if (null != year && null != month) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
            } else if (null != year) {
                dto.getConditions().add(Restrict.eq("year", year));
            }
            List<Event> list = super.findByCis(dto);
            int see = 0;
            int analyze = 0;
            int check = 0;
            int confirm = 0;
            int aduit = 0;
            int make = 0;
            int sum = 0;
            for (Event event : list) {
                sum++;
                Permissions permissions = event.getPermissions();
                switch (permissions) {
                    case SEE:
                        see++;
                        break;
                    case ANALYZE:
                        analyze++;
                        break;
                    case CHECK:
                        check++;
                        break;
                    case CONFIRM:
                        confirm++;
                        break;
                    case ADUIT:
                        aduit++;
                        break;
                    case MAKE:
                        make++;
                        break;
                }
            }
            if (sum != 0) {
                AreaCountBO areaCountVO = new AreaCountBO();
                areaCountVO.setArea(area);
                areaCountVO.setSee(see);
                areaCountVO.setAnalyze(analyze);
                areaCountVO.setCheck(check);
                areaCountVO.setConfirm(confirm);
                areaCountVO.setAduit(aduit);
                areaCountVO.setMake(make);
                areaCountVO.setSum(sum);
                vos.add(areaCountVO);
            }
        }
        return vos;
    }

    @Override
    public List<ClassifyCountBO> classifyCount(EventDTO dto) throws SerException {
        checkSeeIdentity();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        Integer week = dto.getWeek();
        Permissions[] classifys = dto.getClassifys();
        List<ClassifyCountBO> vos = new ArrayList<>();
        for (Permissions permissions : classifys) {
            String classify = null;
            switch (permissions) {
                case SEE:
                    classify = "查看";
                    break;
                case ANALYZE:
                    classify = "分析";
                    break;
                case CHECK:
                    classify = "核对";
                    break;
                case CONFIRM:
                    classify = "确认";
                    break;
                case ADUIT:
                    classify = "审核";
                    break;
                case MAKE:
                    classify = "制作";
                    break;
            }
            dto.getConditions().add(Restrict.eq("permissions", permissions.getValue()));
            if (null != year && null != month && null != week) {
                LocalDate[] times = DateUtil.getWeekTimes(year, month, week);
                dto.getConditions().add(Restrict.between("requestTime", times));
            } else if (null != year && null != month) {
                dto.getConditions().add(Restrict.eq("year", year));
                dto.getConditions().add(Restrict.eq("month", month));
            } else if (null != year) {
                dto.getConditions().add(Restrict.eq("year", year));
            }
            List<Event> list = super.findByCis(dto);
            int haveDeal = 0;
            int noDeal = 0;
            int passNoDeal = 0;
            int dealing = 0;
            int sum = 0;
            for (Event event : list) {
                sum++;
                EventStatus eventStatus = event.getEventStatus();
                switch (eventStatus) {
                    case HAVEDEAL:
                        haveDeal++;
                        break;
                    case NOSEENODEAL:
                        noDeal++;
                        break;
                    case SEENODEAL:
                        dealing++;
                        break;
                    case NODEAL:
                        passNoDeal++;
                        break;
                }
            }
            if (sum != 0) {
                ClassifyCountBO classifyCountVO = new ClassifyCountBO();
                classifyCountVO.setClassify(classify);
                classifyCountVO.setHaveDeal(haveDeal);
                classifyCountVO.setNoDeal(noDeal);
                classifyCountVO.setPassNoDeal(passNoDeal);
                classifyCountVO.setDealing(dealing);
                classifyCountVO.setSum(sum);
                vos.add(classifyCountVO);
            }
        }
        return vos;
    }

    @Override
    public List<AppListDataBO> findAppList(String type) throws SerException {
        List<Event> eventList = new ArrayList<>();
        if (StringUtils.isBlank(type)) {
            eventList = super.findAll();
        } else {
            EventDTO eventDTO = new EventDTO();
            switch (type) {
                case "SEE":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.SEE.getValue()));
                    break;
                case "MAKE":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.MAKE.getValue()));
                    break;
                case "ADUIT":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.ADUIT.getValue()));
                    break;
                case "CONFIRM":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.CONFIRM.getValue()));
                    break;
                case "CHECK":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.CHECK.getValue()));
                    break;
                case "ANALYZE":
                    eventDTO.getConditions().add(Restrict.eq("permissions", Permissions.ANALYZE.getValue()));
                    break;
                case "HAVEDEAL":
                    eventDTO.getConditions().add(Restrict.eq("eventStatus", EventStatus.HAVEDEAL.getValue()));
                    break;
                case "NOSEENODEAL":
                    eventDTO.getConditions().add(Restrict.eq("eventStatus", EventStatus.NOSEENODEAL.getValue()));
                    break;
                case "SEENODEAL":
                    eventDTO.getConditions().add(Restrict.eq("eventStatus", EventStatus.SEENODEAL.getValue()));
                    break;
                case "NODEAL":
                    eventDTO.getConditions().add(Restrict.eq("eventStatus", EventStatus.NODEAL.getValue()));
                    break;
                default:
                    break;
            }
            eventList = super.findByCis(eventDTO, true);
        }
        List<AppListDataBO> appListDataBOList = new ArrayList<>();
        if (eventList != null && eventList.size() > 0) {
            for (Event event : eventList) {
                UserBO userBO = userAPI.findByUsername(event.getName());
                List<EntryRegisterBO> entryRegisterBOS = entryRegisterAPI.getEntryRegisterByName(event.getName());
                AppListDataBO appListDataBO = new AppListDataBO();
                appListDataBO.setId(event.getId());
                if (entryRegisterBOS != null && entryRegisterBOS.size() > 0) {
                    appListDataBO.setDeparment(entryRegisterBOS.get(0).getDepartment());
                }
                if (userBO != null) {
                    appListDataBO.setHeadPortrait(userBO.getHeadSculpture());
                    appListDataBO.setUserName(userBO.getUsername());
                }
                appListDataBO.setGetTime(event.getGetTime().toString());
                appListDataBO.setContent(event.getContent());
                appListDataBO.setContents(event.getContent().split("&"));
                appListDataBO.setFatherBO(BeanTransform.copyProperties(event.getFather(), FatherBO.class));
                appListDataBO.setStatus(event.getStatus());
                appListDataBOList.add(appListDataBO);
            }
        }
        return appListDataBOList;
    }

    @Override
    public FatherBO findFatherById(String id) throws SerException {
        Event event = super.findById(id);
        return BeanTransform.copyProperties(event.getFather(), FatherBO.class);
    }

    @Override
    public Long currentUserEvenCount() throws SerException {
        Long count = 0l;
        List<Event> eventList = super.findAll();
        if (eventList != null && eventList.size() > 0) {
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            for (Event event : eventList) {
                if (userBO.getUsername().equals(event.getName())) {
                    count += 1;
                }
            }

        }
        return count;
    }


    /**
     * 通过计划类型获取对应的数据内容
     *      month   月计划
     *      week    周计划
     *      day     日计划
     *
     * 当计划类型为 (MONTH)月份
     *      只需要传输 年-月
     * 当计划类型为 (WEEK)周
     *      只需要传输 年-月-周数
     * 当计划类型为 (DAY)日
     *      只需要传输 年-月-日
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FatherBO> findByPlanType(EventDTO dto) throws SerException {
        FatherDTO fatherDTO = new FatherDTO();
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        List<FatherBO> boList = new ArrayList<>();

        if (dto.getPlanTypes() != null) {
            if (PlanType.MONTH.name().equals(dto.getPlanTypes()[0].name())) {
                LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
                LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
                fatherDTO.getConditions().add(Restrict.between("startDate", new LocalDate[]{startDate, endDate}));
            } else if (PlanType.WEEK.name().equals(dto.getPlanTypes()[0].name())) {
                Integer week = dto.getWeek();
                LocalDate[] WeekStr = DateUtil.getWeekTimes(year, month, week);
                LocalDate startDate = WeekStr[0];
                LocalDate endDate = WeekStr[1];
                fatherDTO.getConditions().add(Restrict.between("startDate", new LocalDate[]{startDate, endDate}));
            } else if (PlanType.DAY.name().equals(dto.getPlanTypes()[0].name())) {
                Integer day = dto.getDay();
                String monthStr = "";
                String dayStr = "";
                if (month < 10){
                    monthStr = "0"+month;
                }else {
                    monthStr = month+"";
                }
                if (day < 10){
                    dayStr = "0"+day;
                }else {
                    dayStr = day+"";
                }
                fatherDTO.getConditions().add(Restrict.between("startDate", new LocalDate[]{DateUtil.parseDate(year + "-" + monthStr + "-" + dayStr), DateUtil.parseDate(year + "-" + monthStr + "-" + dayStr)}));
            } else if (PlanType.ALL.name().equals(dto.getPlanTypes()[0].name())) {

            }

            String name = userAPI.currentUser().getUsername();
            UserBO userBO = userAPI.findByUsername(name);
            List<PositionDetailBO> positionDetailBOs = null;
            if (userBO != null) {
                positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
            }
            List<Father> fathers = fatherSer.findByCis(fatherDTO);

            for (Father father : fathers) {
                EventDTO eventDTO = new EventDTO();
                eventDTO.getSorts().add("requestTime=asc");

                eventDTO.getConditions().add(Restrict.eq("father.id", father.getId()));
                eventDTO.getConditions().add(Restrict.eq("name", name));
                List<Event> list = super.findByCis(eventDTO);
                List<EventBO> eventBOs = BeanTransform.copyProperties(list, EventBO.class);
                if (null != eventBOs) {
                    for (EventBO bo : eventBOs) {

                        EventTimeSetDTO eventTimeSetDTO = new EventTimeSetDTO();
                        eventTimeSetDTO.getConditions().add(Restrict.eq("event.id",bo.getId()));
                        EventTimeSet eventTimeSet = eventTimeSetSer.findOne(eventTimeSetDTO);
                        if (eventTimeSet != null && null != eventTimeSet){
                            bo.setRemindTime(eventTimeSet.getEventTime().name());
                            bo.seteStatus(eventTimeSet.getStatus());
                        }

                        if ((null != positionDetailBOs) && (!positionDetailBOs.isEmpty())) {
                            bo.setDepart(positionDetailBOs.get(0).getDepartmentName());
                            bo.setModule(positionDetailBOs.get(0).getModuleName());
                            bo.setPosition(positionDetailBOs.get(0).getPosition());
                        }
                    }
                }
                if (!list.isEmpty()) {
                    FatherBO fatherBO = BeanTransform.copyProperties(father, FatherBO.class);
                    fatherBO.setEventBOs(eventBOs);
                    boList.add(fatherBO);
                }
            }
        }
        return boList;
    }

    /**
     * 根据日期和名字 查询当太年是否创建过数据库
     *      如果已经创建，就不需要新增
     *      如果没有创建，就新增一条记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    public List<Father> findByDateName(EventTO dto) throws SerException {
        String sql = "SELECT a.id,startDate,functionChineseName,functionEnglishName FROM event_father a LEFT JOIN event_event b ON a.id = b.father_id WHERE a.startDate = '"+dto.getStartDate()+"' AND b.name = '"+dto.getName()+"'";
        String[] fields = {"id","functionChineseName","functionEnglishName"};
        List<Father> fathers = super.findBySql(sql,Father.class,fields);

        return fathers;
    }

    /**
     * 判断新增计划是否为空
     *      主表(Father)一天一个人 只有一条记录
     *          比如: 2017-12-29
     *                  8：30 - 10：00
     *                  10：00 - 12：00
     *                  13：30 - 15：00
     *                  15：00 - 18：00
     *      Father 只可以存在一条记录 (ID) 关联
     *          子表 可以存在多条记录 (father.id) 关联
     *
     * @Date:2017-12-29
     *
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public EventBO saveEvTo(EventTO to) throws SerException {
        Event entity = BeanTransform.copyProperties(to, Event.class, true);
        Father father = null;

        /**
         *  保存父级表 Father表格
         */
        if ( findByDateName(to).size() > 0 && !findByDateName(to).isEmpty() ) {
            father = findByDateName(to).get(0);
        }else {
            father = new Father();
            father.setProjectChineseName(to.getProjectChineseName());
            father.setProjectEnglishName(to.getProjectEnglishName());
            father.setFunctionChineseName(to.getFunctionChineseName());
            father.setFunctionEnglishName(to.getFunctionEnglishName());
            father.setStartDate( DateUtil.parseDate(to.getStartDate()) );
            fatherSer.save(father);
        }
        /**
        * 保存事件子表  event
        */
        UserBO userBO = userAPI.findByUsername(entity.getName());
        List<PositionDetailBO> positionDetailBOs = null;
        if (userBO != null) {
            positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        }
        if (positionDetailBOs != null && positionDetailBOs.size()>0) {
            entity.setArea(positionDetailBOs.get(0).getArea());
        }
        entity.setGetTime(LocalDateTime.now());
        entity.setFather(father);
        entity.setEventStatus(EventStatus.NOSEENODEAL);
        entity.setYear(LocalDateTime.now().getYear());
        entity.setMonth(LocalDateTime.now().getMonthValue());
        super.save(entity);

        /**
         * 保存event间隔时间设置
         */
        EventTimeSet eventTimeSet = new EventTimeSet();
        eventTimeSet.setEvent(entity);
        eventTimeSet.setInterval(5);
        eventTimeSet.setStatus(to.geteStatus());
        eventTimeSet.setName(entity.getName());
        eventTimeSet.setEventTime(to.getEventTime());
        eventTimeSet.setIntervalType(IntervalType.MINUTE);
        eventTimeSetSer.save(eventTimeSet);

        return BeanTransform.copyProperties(entity, EventBO.class);
    }


    @Override
    public EventBO update(EventTO to) throws SerException {
        Event event = super.findById(to.getId());
        if (null == event){
            throw new SerException("实体对象不存在!");
        }else {
            if (event.getEventStatus().name().equals(EventStatus.NOSEENODEAL.name())){
                event.setEventStatus(EventStatus.HAVEDEAL);
            }else {
                event.setEventStatus(EventStatus.NOSEENODEAL);
            }
            super.update(event);
            return BeanTransform.copyProperties(event, EventBO.class);
        }
    }
}