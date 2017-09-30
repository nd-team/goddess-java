package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.dto.ConfirmCountEmailDTO;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.ConfirmCountEmail;
import com.bjike.goddess.taskallotment.entity.FinishCountEmail;
import com.bjike.goddess.taskallotment.enums.*;
import com.bjike.goddess.taskallotment.to.ConfirmCountEmailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
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
 * 分配及确认汇总设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:44 ]
 * @Description: [ 分配及确认汇总设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class ConfirmCountEmailSerImpl extends ServiceImpl<ConfirmCountEmail, ConfirmCountEmailDTO> implements ConfirmCountEmailSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private TaskNodeSer taskNodeSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ConfirmCountEmailBO save(ConfirmCountEmailTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        ConfirmCountEmail entity = BeanTransform.copyProperties(to, ConfirmCountEmail.class, true);
        long mis = DateUtil.mis(entity.getSetTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置时间必须大于当前时间");
        }if (null==to.getCountFrequency()&&null==to.getStartTime()&&null==to.getEndTime()){
            throw new SerException("汇总频率和汇总时间区间必须选一个，且只能有一个");
        }if (null!=to.getCountFrequency()&&null!=to.getStartTime()&&null!=to.getEndTime()){
            throw new SerException("汇总频率和汇总时间区间只能有一个");
        }
        entity.setCreater(name);
        String[] countPersonss = to.getCountPersonss();
        String[] areas = to.getAreas();
        String[] departs = to.getDeparts();
        String[] projects = to.getProjects();
        String[] tables = to.getTables();
        String[] forDepartss = to.getForDepartss();
        String[] forPersonss = to.getForPersonss();
        switch (entity.getCountType()) {
            case DEPART:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                break;
            case PERSON:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                entity.setCountPersons(arraysTOString(countPersonss));
                break;
        }
        switch (entity.getForObject()) {
            case DEPART:
                entity.setForDeparts(arraysTOString(forDepartss));
                break;
            case PERSON:
                entity.setForPersons(arraysTOString(forPersonss));
                break;
        }
        int remind = entity.getRemind();
        LocalDateTime lastTime = entity.getLastTime();
        switch (entity.getSpacing()) {
            case MINTUE:
                lastTime = entity.getSetTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime = entity.getSetTime().minusHours(remind);
                break;
            case DAY:
                lastTime = entity.getSetTime().minusDays(remind);
                break;
            case WEEK:
                lastTime = entity.getSetTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime = entity.getSetTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ConfirmCountEmailBO.class);
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
    public void edit(ConfirmCountEmailTO to) throws SerException {
        ConfirmCountEmail entity = super.findById(to.getId());
        ConfirmCountEmail ConfirmCountEmail = BeanTransform.copyProperties(to, ConfirmCountEmail.class, true);
        BeanUtils.copyProperties(ConfirmCountEmail, entity, "id", "creater", "createTime");
        long mis = DateUtil.mis(entity.getSetTime(), LocalDateTime.now());
        if (mis < 0) {
            throw new SerException("设置时间必须大于当前时间");
        }if (null==to.getCountFrequency()&&null==to.getStartTime()&&null==to.getEndTime()){
            throw new SerException("汇总频率和汇总时间区间必须选一个，且只能有一个");
        }if (null!=to.getCountFrequency()&&null!=to.getStartTime()&&null!=to.getEndTime()){
            throw new SerException("汇总频率和汇总时间区间只能有一个");
        }
        String[] countPersonss = to.getCountPersonss();
        String[] areas = to.getAreas();
        String[] departs = to.getDeparts();
        String[] projects = to.getProjects();
        String[] tables = to.getTables();
        String[] forDepartss = to.getForDepartss();
        String[] forPersonss = to.getForPersonss();
        switch (entity.getCountType()) {
            case DEPART:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                break;
            case PERSON:
                entity.setArea(arraysTOString(areas));
                entity.setDepart(arraysTOString(departs));
                entity.setProject(arraysTOString(projects));
                entity.setTable(arraysTOString(tables));
                entity.setCountPersons(arraysTOString(countPersonss));
                break;
        }
        switch (entity.getForObject()) {
            case DEPART:
                entity.setForDeparts(arraysTOString(forDepartss));
                break;
            case PERSON:
                entity.setForPersons(arraysTOString(forPersonss));
                break;
        }
        int remind = entity.getRemind();
        LocalDateTime lastTime = entity.getLastTime();
        switch (entity.getSpacing()) {
            case MINTUE:
                lastTime = entity.getSetTime().minusMinutes(remind);
                break;
            case HOUR:
                lastTime = entity.getSetTime().minusHours(remind);
                break;
            case DAY:
                lastTime = entity.getSetTime().minusDays(remind);
                break;
            case WEEK:
                lastTime = entity.getSetTime().minusWeeks(remind);
                break;
            case MONTH:
                lastTime = entity.getSetTime().minusMonths(remind);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ConfirmCountEmailBO> list(ConfirmCountEmailDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ConfirmCountEmail> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ConfirmCountEmailBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        ConfirmCountEmail entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ConfirmCountEmailBO findByID(String id) throws SerException {
        ConfirmCountEmail entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ConfirmCountEmailBO.class);
    }

    @Override
    public Long count(ConfirmCountEmailDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send() throws SerException {
        ConfirmCountEmailDTO dto = new ConfirmCountEmailDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<ConfirmCountEmail> list = super.findByCis(dto);
        for (ConfirmCountEmail f : list) {
            ForObject forObject = f.getForObject();
            String[] mails = null;
            switch (forObject) {
                case ALL:
                    List<UserBO> users = positionDetailUserAPI.findUserListInOrgan();
                    List<String> names = users.stream().map(userBO -> userBO.getUsername()).collect(Collectors.toList());
                    String[] name = new String[names.size()];
                    name = names.toArray(name);
                    List<String> emails = internalContactsAPI.getEmails(name);
                    mails = new String[emails.size()];
                    mails = emails.toArray(mails);
                    break;
                case DEPART:
                    String[] forDeparts = f.getForDeparts().split(",");   //存的是部门id
                    List<String> list1 = new ArrayList<>();
                    for (String departId : forDeparts) {
                        CommonalityBO commonalityBO = commonalityAPI.findByDepartment(departId);
                        if (null != commonalityBO) {
                            list1.add(commonalityBO.getEmail());
                        }
                    }
                    if (!list1.isEmpty()) {
                        mails = new String[list1.size()];
                        mails = list1.toArray(mails);
                    }
                    break;
                case PERSON:
                    String[] strings = f.getForPersons().split(",");
                    List<String> stringList = internalContactsAPI.getEmails(strings);
                    if (null != stringList && !stringList.isEmpty()) {
                        mails = new String[stringList.size()];
                        mails = stringList.toArray(mails);
                    }
                    break;
            }
            Spacing spacing = f.getSpacing();
            int remind = f.getRemind();
            LocalDateTime lastTime = f.getLastTime();
            LocalDateTime time = null;
            switch (spacing) {
                case MINTUE:
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
            }
            long mis = DateUtil.mis(LocalDateTime.now(), time);
            if (mis >= 0) {
                String content = html(ConfirmCountBOs(f));
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle("分配及确认汇总");
                messageTO.setContent(content);
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (null != mails && mails.length > 0) {
                    messageTO.setReceivers(mails);
                }
                messageAPI.send(messageTO);
                f.setLastTime(LocalDateTime.now());
                f.setModifyTime(LocalDateTime.now());
                super.update(f);
            }
        }
    }

    private List<ConfirmCountBO> ConfirmCountBOs(ConfirmCountEmail f) throws SerException{
        TaskNodeDTO taskNodeDTO = new TaskNodeDTO();
        LocalDate startTime = f.getStartTime();
        LocalDate endTime = f.getEndTime();
        CountType countType = f.getCountType();
        if (CountType.WHOLE.equals(f.getCountType())) {     //整体汇总
            List<String> areas = taskNodeSer.areas();
            List<String> departs = taskNodeSer.departs();
            if (!areas.isEmpty()) {
                String[] area = new String[areas.size()];
                area = areas.toArray(area);
                taskNodeDTO.setArea(area);
            } else {
                taskNodeDTO.setArea(f.getArea().split(","));
            }
            if (!departs.isEmpty()) {
                String[] depart = new String[departs.size()];
                depart = departs.toArray(depart);
                taskNodeDTO.setDepart(depart);
            } else {
                taskNodeDTO.setDepart(f.getDepart().split(","));
            }
        } else {    //其他汇总
            taskNodeDTO.setArea(f.getArea().split(","));
            taskNodeDTO.setDepart(f.getDepart().split(","));
            taskNodeDTO.setTables(f.getTable().split(","));
        }
        if (CountType.PERSON.equals(f.getCountType())) {
            String[] names = f.getCountPersons().split(",");
            taskNodeDTO.setName(names);
        }
        taskNodeDTO.setCountType(countType);
        if (null == startTime && null == endTime) {    //没有汇总时间段就用汇总频率
            CountFrequency countFrequency = f.getCountFrequency();
            switch (countFrequency) {
                case DAY:   //每天
                    startTime = LocalDate.now();
                    endTime = LocalDate.now();
                    break;
                case WEEK:
                    startTime = DateUtil.getStartWeek();
                    endTime = DateUtil.getEndWeek();
                    break;
                case MONTH:
                    startTime = DateUtil.getStartMonth();
                    endTime = DateUtil.getEndMonth();
                    break;
                case SEASON:
                    String s = DateUtil.dateToString(DateUtil.getStartQuart());
                    startTime = DateUtil.parseDate(s.substring(0, s.indexOf(" ")));
                    String e = DateUtil.dateToString(DateUtil.getEndQuart());
                    endTime = DateUtil.parseDate(e.substring(0, e.indexOf(" ")));
                    break;
                case YEAR:
                    startTime = DateUtil.getStartYear();
                    endTime = DateUtil.getEndYear();
                    break;
            }
        }
        taskNodeDTO.setStartTime(DateUtil.dateToString(startTime));
        taskNodeDTO.setEndTime(DateUtil.dateToString(endTime));
        List<ConfirmCountBO> list=taskNodeSer.confirmCount(taskNodeDTO);
        return list;
    }

    private String html(List<ConfirmCountBO> finishCaseBOS) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>分配及确认汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>地区</td>");
        sb.append("<td>项目组/部门</td>");
        sb.append("<td>项目表</td>");
        sb.append("<td>姓名</td>");
        sb.append("<td>任务类型</td>");
        sb.append("<td>已分配</td>");
        sb.append("<td>未分配</td>");
        sb.append("<td>确认接收量</td>");
        sb.append("<td>未接收任务量</td>");
        sb.append("<td>待确认接收量</td>");
        sb.append("</tr>");
        for (int i = 0; i < finishCaseBOS.size(); i++) {
            int oneSize = num(finishCaseBOS.get(i));
            int twoSize = 0;
            List<ConfirmSonBO> sons = finishCaseBOS.get(i).getConfirmSonS();
            if (i < finishCaseBOS.size() - 2) {
                twoSize = (oneSize - 1) / (sons.size() - 1);
                sb.append("<tr>");
                if (null != finishCaseBOS.get(i).getArea()) {
                    sb.append("<td rowspan='" + oneSize + "'>" + finishCaseBOS.get(i).getArea() + "</td>");
                } else {
                    sb.append("<td rowspan='" + oneSize + "'> </td>");
                }
            } else {
                sb.append("<tr>");
                if (null != finishCaseBOS.get(i).getArea()) {
                    sb.append("<td>" + finishCaseBOS.get(i).getArea() + "</td>");
                } else {
                    sb.append("<td> </td>");
                }
            }
            for (int a = 0; a < sons.size(); a++) {
                if (a != 0) {
                    sb.append("<tr>");
                }
                if (a < sons.size() - 1) {
                    if (null != sons.get(a).getDepart()) {
                        sb.append("<td rowspan='" + twoSize + "'>" + sons.get(a).getDepart() + "</td>");
                    } else {
                        sb.append("<td rowspan='" + twoSize + "'> </td>");
                    }
                } else {
                    if (null != sons.get(a).getDepart()) {
                        sb.append("<td>" + sons.get(a).getDepart() + "</td>");
                    } else {
                        sb.append("<td> </td>");
                    }
                }
                List<ConfirmTableBO> caseTableS = sons.get(a).getTableS();
                int threeSize = twoSize / caseTableS.size();
                for (int b = 0; b < caseTableS.size(); b++) {
                    if (b != 0) {
                        sb.append("<tr>");
                    }
                    if (a < sons.size() - 1) {
                        if (null != caseTableS.get(b).getTableName()) {
                            sb.append("<td rowspan='" + threeSize + "'>" + caseTableS.get(b).getTableName() + "</td>");
                        } else {
                            sb.append("<td rowspan='" + threeSize + "'> </td>");
                        }
                    } else {
                        if (null != caseTableS.get(b).getTableName()) {
                            sb.append("<td>" + caseTableS.get(b).getTableName() + "</td>");
                        } else {
                            sb.append("<td> </td>");
                        }
                    }
                    List<ConfirmGrandSonBO> grandSonS = caseTableS.get(b).getGrandSonS();
                    int fourSize = threeSize / grandSonS.size();
                    for (int c = 0; c < grandSonS.size(); c++) {
                        if (c != 0) {
                            sb.append("<tr>");
                        }
                        if (a < sons.size() - 1) {
                            if (null != grandSonS.get(c).getName()) {
                                sb.append("<td rowspan='" + fourSize + "'>" + grandSonS.get(c).getName() + "</td>");
                            } else {
                                sb.append("<td rowspan='" + fourSize + "'> </td>");
                            }
                        } else {
                            if (null != grandSonS.get(c).getName()) {
                                sb.append("<td>" + grandSonS.get(c).getName() + "</td>");
                            } else {
                                sb.append("<td> </td>");
                            }
                        }
                        List<ConfirmLastBO> caseLastS = grandSonS.get(c).getConfirmLastS();
                        for (int d = 0; d < caseLastS.size(); d++) {
                            ConfirmLastBO last = caseLastS.get(d);
                            if (d != 0) {
                                sb.append("<tr>");
                            }
                            if (null != last.getTaskType()) {
                                sb.append("<td>" + last.getTaskType() + "</td>");
                            }else {
                                sb.append("<td> </td>");
                            }
                            sb.append("<td>" + last.getHaveInitiate() + "</td>");
                            sb.append("<td>" + last.getNoInitiate() + "</td>");
                            sb.append("<td>" + last.getConfirm() + "</td>");
                            sb.append("<td>" + last.getUnConfirm() + "</td>");
                            sb.append("<td>" + last.getToConfirm() + "</td>");
                            sb.append("</tr>");
                        }
                    }
                }
            }
        }
        sb.append("</table>");
        System.out.println(sb.toString());
        return sb.toString();
    }

    private Integer num(ConfirmCountBO finishCaseBO) throws SerException {
        int num = 0;
        List<ConfirmSonBO> sons = finishCaseBO.getConfirmSonS();
        for (ConfirmSonBO sonBO : sons) {
            List<ConfirmTableBO> caseTableS = sonBO.getTableS();
            for (ConfirmTableBO table : caseTableS) {
                List<ConfirmGrandSonBO> grandSonS = table.getGrandSonS();
                for (ConfirmGrandSonBO grandSonBO : grandSonS) {
                    List<ConfirmLastBO> caseLastS = grandSonBO.getConfirmLastS();
                    num+=caseLastS.size();
                }
            }
        }
        return num;
    }
}