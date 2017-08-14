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
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.entity.Event;
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
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
        String project = to.getProject();
        Father father = new Father();
        father.setProject(project);
        fatherSer.save(father);
        UserBO userBO = userAPI.findByUsername(entity.getName());
        List<PositionDetailBO> positionDetailBOs = null;
        if (userBO != null) {
            positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        }
        if (!positionDetailBOs.isEmpty()) {
            entity.setArea(positionDetailBOs.get(0).getArea());
        }
        entity.setGetTime(LocalDateTime.now());
        entity.setFather(father);
        entity.setEventStatus(EventStatus.NOSEENODEAL);
        entity.setYear(entity.getRequestTime().getYear());
        entity.setYear(entity.getRequestTime().getMonthValue());
        super.save(entity);
        return BeanTransform.copyProperties(entity, EventBO.class);
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
                                messageTO.setContent("您有一件项目名称为:" + event.getFather().getProject() + "的" + event.getContent() + "等待您去处理");
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
        List<Event> list = super.findByCis(dto);
        List<ContentBO> contentVOs = new ArrayList<>();
        for (Event event : list) {
            LocalDateTime request = event.getRequestTime();
            if (year == request.getYear() && month == request.getMonthValue()) {
                ContentBO vo = new ContentBO();
                vo.setTime(DateUtil.dateToString(request));
                vo.setContent(event.getFather().getProject() + "的" + event.getContent());
                contentVOs.add(vo);
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
            if (null != time) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time),DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set=new HashSet<>();
                for (Permissions permissions:permissionses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("permissions", integers));
            }
            if (null != eventStatuses) {
                Set<Integer> set=new HashSet<>();
                for (EventStatus permissions:eventStatuses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
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
            if (null != time) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time),DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set=new HashSet<>();
                for (Permissions permissions:permissionses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("permissions", integers));
            }
            if (null != eventStatuses) {
                Set<Integer> set=new HashSet<>();
                for (EventStatus permissions:eventStatuses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
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
            if (null != time) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time),DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set=new HashSet<>();
                for (Permissions permissions:permissionses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("permissions", integers));
            }
            if (null != eventStatuses) {
                Set<Integer> set=new HashSet<>();
                for (EventStatus permissions:eventStatuses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
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
            if (null != time) {
                eventDTO.getConditions().add(Restrict.between("requestTime", new LocalDate[]{DateUtil.parseDate(time),DateUtil.parseDate(time)}));
            }
            if (null != permissionses) {
                Set<Integer> set=new HashSet<>();
                for (Permissions permissions:permissionses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("permissions", integers));
            }
            if (null != eventStatuses) {
                Set<Integer> set=new HashSet<>();
                for (EventStatus permissions:eventStatuses){
                    set.add(permissions.getValue());
                }
                Integer[] integers=new Integer[set.size()];
                integers=set.toArray(integers);
                eventDTO.getConditions().add(Restrict.in("eventStatus", integers));
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
}