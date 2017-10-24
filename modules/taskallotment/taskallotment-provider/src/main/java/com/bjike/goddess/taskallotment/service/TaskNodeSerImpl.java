package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.bo.DayReport.*;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.QuestionDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.*;
import com.bjike.goddess.taskallotment.enums.*;
import com.bjike.goddess.taskallotment.to.CustomTitleTO;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.QuestionTO;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 任务节点业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class TaskNodeSerImpl extends ServiceImpl<TaskNode, TaskNodeDTO> implements TaskNodeSer {
    @Autowired
    private TableSer tableSer;
    @Autowired
    private CustomTitleSer customTitleSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private QuestionSer questionSer;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    /**
     * 再次分发权限（层级级别）
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
                throw new SerException("您不是管理层人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 再次分发权限（层级级别）
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
    public Boolean sonPermission() throws SerException {
        Boolean flagAdd = guideAddIdentity();
        if (flagAdd) {
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
            case AGAIN:
                flag = guideAddIdentity();
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
    public void save(TaskNodeTO to) throws SerException {
        String tableId = to.getTableId();
        Table table = tableSer.findById(tableId);
        TaskNode entity = BeanTransform.copyProperties(to, TaskNode.class, true);
        entity.setTable(table);
        List<CustomTitleTO> titleTOS = to.getCustomTitles();
        List<CustomTitle> titles = new ArrayList<>();
        if (null != titleTOS) {
            int i = 0;
            for (CustomTitleTO titleTO : titleTOS) {
                i++;
                if (titleTO.getMandatory()) {
                    if (null == titleTO.getContent()) {
                        throw new SerException(titleTO.getTitle() + "为必填字段");
                    }
                }
                CustomTitle customTitle = BeanTransform.copyProperties(titleTO, CustomTitle.class, true);
                customTitle.setTitleIndex(i);
                customTitle.setTaskNode(entity);
                titles.add(customTitle);
            }
        }
        TaskType taskType = entity.getTaskType();
        entity.setType(type(taskType));
        entity.setCustomTitles(titles);
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TaskNodeTO to) throws SerException {
        TaskNode entity = update(to);
        super.update(entity);
    }

    @Transactional(rollbackFor = {SerException.class})
    public TaskNode update(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        Table table = entity.getTable();
        LocalDateTime a = entity.getCreateTime();
        List<CustomTitle> customTitles = entity.getCustomTitles();
        if (null != customTitles && !customTitles.isEmpty()) {
            customTitleSer.remove(customTitles);
        }
        QuestionDTO dto = new QuestionDTO();
        dto.getConditions().add(Restrict.eq("taskNode.id", entity.getId()));
        List<Question> questions = questionSer.findByCis(dto);
        if (!questions.isEmpty()) {
            questionSer.remove(questions);
        }
        entity = BeanTransform.copyProperties(to, TaskNode.class, true, "taskStatus", "initiate");
        entity.setCreateTime(a);
        TaskType taskType = entity.getTaskType();
        entity.setType(type(taskType));
        List<CustomTitleTO> customTitleTOS = to.getCustomTitles();
        List<CustomTitle> customTitles1 = new ArrayList<>();
        if (null != customTitleTOS) {
            int i = 0;
            for (CustomTitleTO customTitleTO : customTitleTOS) {
                i++;
                if (customTitleTO.getMandatory()) {
                    if (null == customTitleTO.getContent()) {
                        throw new SerException(customTitleTO.getTitle() + "为必填字段");
                    }
                }
                CustomTitle customTitle = BeanTransform.copyProperties(customTitleTO, CustomTitle.class, true);
                customTitle.setTitleIndex(i);
                customTitle.setTaskNode(entity);
                customTitleSer.save(customTitle);
                customTitles1.add(customTitle);
            }
        }
        List<QuestionTO> questionTOS = to.getQuestions();
        List<Question> questions1 = new ArrayList<>();
        if (null != questionTOS) {
            for (QuestionTO questionTO : questionTOS) {
                Question question = BeanTransform.copyProperties(questionTO, Question.class, true);
                question.setTaskNode(entity);
                questionSer.save(question);
                questions1.add(question);
            }
        }
        entity.setQuestions(questions1);
        entity.setCustomTitles(customTitles1);
        entity.setTable(table);
        entity.setModifyTime(LocalDateTime.now());
        return entity;
    }

    private String type(TaskType taskType) throws SerException {
        if (TaskType.ADMININSTRATION.equals(taskType)) {
            return "内部项目名称";
        } else {
            return "功能流程名称";
        }
    }

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        List<ProjectBO> list = projectSer.list1(dto);
        for (ProjectBO projectBO : list) {
            List<TableBO> tableBOS = projectBO.getTables();
            if (null != tableBOS) {
                for (TableBO t : tableBOS) {
                    TaskNodeDTO taskNodeDTO = new TaskNodeDTO();
                    taskNodeDTO.getConditions().add(Restrict.eq("table.id", t.getId()));
                    List<TaskNode> taskNodes = super.findByCis(taskNodeDTO);
                    List<NodeBO> nodeBOS = BeanTransform.copyProperties(taskNodes, NodeBO.class);
                    t.setNodeS(nodeBOS);
                }
            }
        }
        return list;
    }

    private TaskNodeBO tranBO(TaskNode entity) throws SerException {
        TaskNodeBO bo = BeanTransform.copyProperties(entity, TaskNodeBO.class, "customTitles", "questions", "needTime", "executeTime", "actualTime", "undoneTime", "delayTime", "table");
        bo.setTable(entity.getTable().getName());
        bo.setDepart(entity.getTable().getProject().getDepart());
        bo.setProject(entity.getTable().getProject().getProject());
        bo.setInnerProject(entity.getTable().getProject().getInnerProject());
        List<CustomTitle> customTitles = entity.getCustomTitles();
        if (null != customTitles) {
            List<CustomTitleBO> customTitleBOS = BeanTransform.copyProperties(customTitles, CustomTitleBO.class);
            bo.setCustomTitles(customTitleBOS);
        }
        QuestionDTO dto = new QuestionDTO();
        dto.getConditions().add(Restrict.eq("taskNode.id", entity.getId()));
        List<Question> questions = questionSer.findByCis(dto);
        List<QuestionBO> questionBOS = BeanTransform.copyProperties(questions, QuestionBO.class);
        bo.setQuestions(questionBOS);
        Double needTime = entity.getNeedTime();
        TimeType needType = entity.getNeedType();
        if (null != needType && null != needTime) {
            String need = toString(needTime, needType);
            bo.setNeedTime(need);
        }
        Double executeTime = entity.getExecuteTime();
        TimeType executeType = entity.getExecuteType();
        if (null != executeType && null != executeTime) {
            String execute = toString(executeTime, executeType);
            bo.setExecuteTime(execute);
        }
        Double actualTime = entity.getActualTime();
        TimeType actualType = entity.getActualType();
        if (null != actualType && null != actualTime) {
            String actual = toString(actualTime, actualType);
            bo.setActualTime(actual);
        }
        Double undoneTime = entity.getUndoneTime();
        TimeType undoneType = entity.getUndoneType();
        if (null != undoneTime && null != undoneType) {
            String undone = toString(undoneTime, undoneType);
            bo.setUndoneTime(undone);
        }
        Double delayTime = entity.getDelayTime();
        TimeType delayType = entity.getDelayType();
        if (null != delayTime && null != delayType) {
            String delay = toString(delayTime, delayType);
            bo.setDelayTime(delay);
        }
        bo.setTableId(entity.getTable().getId());
        return bo;
    }

    private String toString(Double time, TimeType timeType) throws SerException {
        String s = null;
        switch (timeType) {
            case MINUTE:
                s = time + "分钟";
                break;
            case HOUR:
                s = time + "小时";
                break;
            case DAY:
                s = time + "天";
                break;
        }
        return s;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        TaskNode entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public TaskNodeBO findByID(String id) throws SerException {
        TaskNode entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return tranBO(entity);
    }

    @Override
    public Long count(TaskNodeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public Boolean checkTime(TaskNodeTO to) throws SerException {
        String execute = to.getExecute();
        if (null == execute) {
            return false;
        }
        String time1 = to.getStartTime().substring(0, to.getStartTime().indexOf(" "));
        LocalDate date = DateUtil.parseDate(time1);
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{date, date}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{date, date}));
        dto.getConditions().add(Restrict.eq("execute", execute));
        List<TaskNode> list = super.findByCis(dto);
        double time = 0;
        for (TaskNode taskNode : list) {
            double needTime = taskNode.getNeedTime();
            TimeType needType = taskNode.getNeedType();
            switch (needType) {
                case DAY:
                    needTime = needTime * 8;
                    break;
                case HOUR:
                    break;
                case MINUTE:
                    needTime = new BigDecimal(needTime / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;
            }
            time += needTime;
        }
        if (time > 8) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void initiateTask(TaskNodeTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        TaskNode entity = update(to);
        entity.setInitiate(name);
        entity.setTaskStatus(TaskStatus.DOING);
        super.update(entity);
        if (null != to.getExecute()) {
            priority(entity);  //处理优先级
        }
        send(name, entity);
    }

    @Transactional(rollbackFor = {SerException.class})
    public void priority(TaskNode taskNode) throws SerException {
        String execute = taskNode.getExecute();
        String time = DateUtil.dateToString(taskNode.getStartTime());
        int index = time.indexOf(" ");
        time = time.substring(0, index);
        LocalDate date = DateUtil.parseDate(time);
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{date, date}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{date, date}));
        dto.getConditions().add(Restrict.eq("execute", execute));
        List<TaskNode> list = super.findByCis(dto);
        TreeSet<TaskNode> set = new TreeSet<>(new Comparator<TaskNode>() {
            @Override
            public int compare(TaskNode o1, TaskNode o2) {
                LocalDateTime eT = o1.getEndTime();
                LocalDateTime eT1 = o2.getEndTime();
                Long mis = eT.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                        - eT1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                if (mis < 0) {    //按任务结束时间排序
                    return 1;
                } else if (mis == 0) {
                    TaskType t = o1.getTaskType();
                    TaskType t1 = o2.getTaskType();
                    switch (t) {      //按任务类型排序
                        case ENGINEERING:
                            if (TaskType.ENGINEERING.equals(t1)) {
                                return 1;
                            } else {
                                return 1;
                            }
                        case ADMININSTRATION:
                            if (TaskType.ENGINEERING.equals(t1)) {
                                return -1;
                            } else if (TaskType.TRAINING.equals(t1)) {
                                return 1;
                            } else {
                                return 1;
                            }
                        case TRAINING:
                            if (TaskType.TRAINING.equals(t1)) {
                                return 1;
                            } else {
                                return -1;
                            }
                    }
                }
                return -1;
            }
        });
        for (TaskNode t : list) {
            set.add(t);       //使用treeset集合进行排序
        }
        int a = list.size();
        for (TaskNode t : set) {
            t.setPriority(a);     //排序后得出优先级
            a--;
            super.update(t);
        }
    }

    private void send(String name, TaskNode entity) throws SerException {
        String charge = entity.getCharge();
        String execute = entity.getExecute();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有任务分发给您");
        String content = null;
        String email = null;
        if (null != charge && null != execute) {
            content = name + "给您分发了您的执行任务，请上系统查看";
            email = internalContactsAPI.getEmail(execute);
        } else if (null != charge) {
            content = name + "给您分发了您负责的任务，请上系统查看";
            email = internalContactsAPI.getEmail(charge);
        } else if (null != execute) {
            content = name + "给您分发了您的执行任务，请上系统查看";
            email = internalContactsAPI.getEmail(execute);
        }
        if (null != email) {
            messageTO.setContent(content);
            messageTO.setReceivers(new String[]{email});
            messageAPI.send(messageTO);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void addTask(TaskNodeTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        String projectId = to.getProjectId();
        String table = to.getTable();
        TableDTO tableDTO = new TableDTO();
        tableDTO.getConditions().add(Restrict.eq("name", table));
        List<Table> tables = tableSer.findByCis(tableDTO);
        String tableId = null;
        if (tables.isEmpty()) {
            Table e = new Table();
            e.setProject(projectSer.findById(projectId));
            e.setName(table);
            e.setStatus(Status.START);
            tableSer.save(e);
            tableId = e.getId();
        } else {
            tableId = tables.get(0).getId();
        }
        TaskNode entity = BeanTransform.copyProperties(to, TaskNode.class, true, "table");
        entity.setTable(tableSer.findById(tableId));
        entity.setInitiate(name);
        entity.setTaskStatus(TaskStatus.DOING);
        List<CustomTitleTO> titleTOS = to.getCustomTitles();
        List<CustomTitle> titles = new ArrayList<>();
        if (null != titleTOS) {
            int i = 0;
            for (CustomTitleTO titleTO : titleTOS) {
                i++;
                CustomTitle customTitle = BeanTransform.copyProperties(titleTO, CustomTitle.class, true);
                customTitle.setTitleIndex(i);
                customTitle.setTaskNode(entity);
                titles.add(customTitle);
            }
        }
        entity.setCustomTitles(titles);
        entity.setType(type(entity.getTaskType()));
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        if (null != entity.getExecute()) {
            priority(entity);  //处理优先级
        }
        send(name, entity);
    }

    @Override
    public List<TaskNodeBO> myInitiate(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("initiate", name));
        List<TaskNode> list = super.findByCis(dto, true);
        List<TaskNodeBO> bos = new ArrayList<>();
        for (TaskNode taskNode : list) {
            bos.add(tranBO(taskNode));
        }
        return bos;
    }

    @Override
    public Long myInitiateNum(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("initiate", name));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void reback(String id) throws SerException {
        delete(id);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void finish(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setContent(to.getContent());
        entity.setActualNum(to.getActualNum());
        entity.setTaskType(to.getTaskType());
        entity.setTaskName(to.getTaskName());
        entity.setExecute(to.getExecute());
        entity.setPlanNum(to.getPlanNum());
        entity.setNeedTime(to.getNeedTime());
        entity.setNeedType(to.getNeedType());
        entity.setActualTime(to.getActualTime());
        entity.setActualType(to.getActualType());
        entity.setUndoneTime(to.getUndoneTime());
        entity.setUndoneType(to.getUndoneType());
        entity.setStartTime(DateUtil.parseDateTime(to.getStartTime()));
        entity.setEndTime(DateUtil.parseDateTime(to.getEndTime()));
        entity.setNotice(to.getNotice());
        entity.setReimbursement(to.getReimbursement());
        entity.setTaskStatus(TaskStatus.FINISH);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void unFinish(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setContent(to.getContent());
        entity.setActualNum(to.getActualNum());
        entity.setTaskType(to.getTaskType());
        entity.setTaskName(to.getTaskName());
        entity.setExecute(to.getExecute());
        entity.setPlanNum(to.getPlanNum());
        entity.setNeedTime(to.getNeedTime());
        entity.setNeedType(to.getNeedType());
        entity.setActualTime(to.getActualTime());
        entity.setActualType(to.getActualType());
        entity.setUndoneTime(to.getUndoneTime());
        entity.setUndoneType(to.getUndoneType());
        entity.setStartTime(DateUtil.parseDateTime(to.getStartTime()));
        entity.setEndTime(DateUtil.parseDateTime(to.getEndTime()));
        entity.setNotice(to.getNotice());
        entity.setReimbursement(to.getReimbursement());
        entity.setTaskStatus(TaskStatus.UNFINISHED);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void pass(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAduitType(AduitType.PASS);
        entity.setResult(to.getResult());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void notPass(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAduitType(AduitType.NOTPASS);
        entity.setNotPassReason(to.getNotPassReason());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<TaskNodeBO> myCharge(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.getConditions().add(Restrict.in("depart", depart));
            List<Project> projects = projectSer.findByCis(projectDTO);
            Set<String> projectIds = projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
            List<Table> tables = tableSer.findByCis(tableDTO);
            Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
            dto.getConditions().add(Restrict.in("table.id", tableIds));
        }
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("charge", name));
        List<TaskNode> list = super.findByCis(dto, true);
        List<TaskNodeBO> bos = new ArrayList<>();
        for (TaskNode taskNode : list) {
            bos.add(tranBO(taskNode));
        }
        return bos;
    }

    @Override
    public Long myChargeNum(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.getConditions().add(Restrict.in("depart", depart));
            List<Project> projects = projectSer.findByCis(projectDTO);
            Set<String> projectIds = projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
            List<Table> tables = tableSer.findByCis(tableDTO);
            Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
            dto.getConditions().add(Restrict.in("table.id", tableIds));
        }
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("charge", name));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void allotment(TaskNodeTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setInitiate(name);
        entity.setExecute(to.getExecute());
        entity.setPlanNum(to.getPlanNum());
        entity.setNeedTime(to.getNeedTime());
        entity.setNeedType(to.getNeedType());
        entity.setStartTime(DateUtil.parseDateTime(to.getStartTime()));
        entity.setEndTime(DateUtil.parseDateTime(to.getEndTime()));
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        priority(entity);    //处理优先级
    }

    @Override
    public List<TaskNodeBO> myExecute(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.getConditions().add(Restrict.in("depart", depart));
            List<Project> projects = projectSer.findByCis(projectDTO);
            Set<String> projectIds = projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
            List<Table> tables = tableSer.findByCis(tableDTO);
            Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
            dto.getConditions().add(Restrict.in("table.id", tableIds));
        }
        String name = userAPI.currentUser().getUsername();
        dto.getConditions().add(Restrict.eq("execute", name));
        List<TaskNode> list = super.findByCis(dto, true);
        List<TaskNodeBO> bos = new ArrayList<>();
        for (TaskNode taskNode : list) {
            bos.add(tranBO(taskNode));
        }
        return bos;
    }

    @Override
    public Long myExecuteNum(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser().getUsername();
        String[] depart = dto.getDepart();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.getConditions().add(Restrict.in("depart", depart));
            List<Project> projects = projectSer.findByCis(projectDTO);
            Set<String> projectIds = projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
            List<Table> tables = tableSer.findByCis(tableDTO);
            Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
            dto.getConditions().add(Restrict.in("table.id", tableIds));
        }
        dto.getConditions().add(Restrict.eq("execute", name));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void report(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = entity.getEndTime();
        Long mis = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (mis < (3600000 * 3)) {   //需提前三小时上报
            throw new SerException("需在任务结束时间提前3小时上报");
        }
        entity.setDelay(to.getDelay());
        entity.setDelayTime(to.getDelayTime());
        entity.setDelayType(to.getDelayType());
        entity.setReportReason(to.getReportReason());
        entity.setReport(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void initiateAgain(TaskNodeTO to) throws SerException {
        String token = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(token);
        String name = userAPI.currentUser().getUsername();
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setInitiate(name);
        entity.setExecute(to.getExecute());
        entity.setPlanNum(to.getPlanNum());
        entity.setNeedTime(to.getNeedTime());
        entity.setNeedType(to.getNeedType());
        entity.setStartTime(DateUtil.parseDateTime(to.getStartTime()));
        entity.setEndTime(DateUtil.parseDateTime(to.getEndTime()));
        entity.setRemark(to.getRemark());
        entity.setSplit(to.getSplit());
        if (to.getSplit()) {
            entity.setDay(to.getDay());
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        priority(entity);    //处理优先级
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void write(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setFinishStatus(to.getFinishStatus());
        entity.setExecuteTime(to.getExecuteTime());
        entity.setExecuteType(to.getExecuteType());
        entity.setStartExecute(DateUtil.parseDateTime(to.getStartExecute()));
        entity.setEndExecute(DateUtil.parseDateTime(to.getEndExecute()));
        entity.setReimbursement(to.getReimbursement());
        entity.setQuestion(to.getQuestion());
        entity.setSummary(to.getSummary());
        entity.setActualTime(to.getActualTime());
        entity.setActualType(to.getActualType());
        entity.setUndoneTime(to.getUndoneTime());
        entity.setUndoneType(to.getUndoneType());
        entity.setFinishTime(DateUtil.parseDateTime(to.getFinishTime()));
        if (entity.getQuestion()) {
            List<Question> questions = new ArrayList<>();
            for (QuestionTO questionTO : to.getQuestions()) {
                Question question = BeanTransform.copyProperties(questionTO, Question.class, true);
                question.setTaskNode(entity);
                questionSer.save(question);
            }
            entity.setQuestions(questions);
        }
        TimeType timeType = entity.getExecuteType();
        double executeTime = entity.getExecuteTime();
        switch (timeType) {
            case MINUTE:
                executeTime = executeTime / 60;
                break;
            case DAY:
                executeTime = executeTime * 8;
                break;
        }
        double efficiency = entity.getPlanNum() * 8 / entity.getActualNum() / executeTime;
        efficiency = new BigDecimal(efficiency).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        entity.setEfficiency(efficiency);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<PersonCountBO> personCount(TaskNodeDTO dto) throws SerException {
        LocalDate start = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea();
        String[] departs = dto.getDepart();
        String[] names = dto.getName();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        return pCount(dto, areas, departs, names);
    }

    private List<PersonCountBO> pCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] names) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.in("area", areas));
        projectDTO.getConditions().add(Restrict.in("depart", departs));
        List<Project> projects = projectSer.findByCis(projectDTO);
        List<PersonCountBO> personCountBOS = new ArrayList<>();
        double differSum = 0;   //总合计
        for (String area : areas) {
            double differArea = 0;   //地区合计
            PersonCountBO personCountBO = new PersonCountBO();
            List<PersonSonBO> personSonBOS = new ArrayList<>();
            for (String depart : departs) {
                double differDepart = 0;   //部门合计
                PersonSonBO personSonBO = new PersonSonBO();
                List<PersonLastBO> personLastBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    personCountBO.setArea(area);
                    personSonBO.setDepart(depart);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    for (String name : names) {
                        PersonLastBO personLastBO = getPerson(taskNodes, name);
                        personLastBOS.add(personLastBO);
                    }
                    differDepart += personLastBOS.stream().mapToDouble(PersonLastBO::getDiffer).sum();
                    differArea += personLastBOS.stream().mapToDouble(PersonLastBO::getDiffer).sum();
                    differSum += personLastBOS.stream().mapToDouble(PersonLastBO::getDiffer).sum();
                }
                personSonBO.setPersonLastS(personLastBOS);
                personSonBOS.add(personSonBO);
                PersonSonBO departCount = departCount(differDepart);
                personSonBOS.add(departCount);
            }
            personCountBO.setPersonSonS(personSonBOS);
            personCountBOS.add(personCountBO);
            PersonCountBO areaCount = sum(differArea, "地区合计");
            personCountBOS.add(areaCount);
        }
        PersonCountBO sum = sum(differSum, "总合计");
        personCountBOS.add(sum);
        return personCountBOS;
    }

    //部门合计
    private PersonSonBO departCount(Double differDepart) throws SerException {
        PersonSonBO depart = new PersonSonBO();
        depart.setDepart("合计");
        List<PersonLastBO> lastBOS = new ArrayList<>();
        PersonLastBO lastBO = new PersonLastBO();
        lastBO.setDiffer(differDepart);
        lastBOS.add(lastBO);
        depart.setPersonLastS(lastBOS);
        return depart;
    }

    //合计
    private PersonCountBO sum(Double differDepart, String type) throws SerException {
        PersonCountBO sum = new PersonCountBO();
        if ("总合计".equals(type)) {
            sum.setArea("总合计");
        } else if ("地区合计".equals(type)) {
            sum.setArea("合计");
        }
        List<PersonSonBO> sonBOS = new ArrayList<>();
        PersonSonBO depart = new PersonSonBO();
        List<PersonLastBO> lastBOS = new ArrayList<>();
        PersonLastBO lastBO = new PersonLastBO();
        lastBO.setDiffer(differDepart);
        lastBOS.add(lastBO);
        depart.setPersonLastS(lastBOS);
        sonBOS.add(depart);
        sum.setPersonSonS(sonBOS);
        return sum;
    }

    private PersonLastBO getPerson(List<TaskNode> taskNodes, String name) throws SerException {
        List<TaskNode> list = taskNodes.stream().filter(taskNode -> FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && name.equals(taskNode.getExecute())).collect(Collectors.toList());
        double differ = 0;
        for (TaskNode t : list) {
            TimeType needType = t.getNeedType();
            double needTime = t.getNeedTime();
            switch (needType) {
                case MINUTE:
                    needTime = new BigDecimal(needTime / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;
                case HOUR:
                    break;
                case DAY:
                    needTime = needTime * 8;
                    break;
            }
            TimeType actualType = t.getActualType();
            double actualTime = t.getActualTime();
            switch (actualType) {
                case MINUTE:
                    actualTime = new BigDecimal(actualTime / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;
                case HOUR:
                    break;
                case DAY:
                    actualTime = actualTime * 8;
                    break;
            }
            differ = differ + (actualTime - needTime);
        }
        PersonLastBO personLastBO = new PersonLastBO();
        personLastBO.setName(name);
        personLastBO.setDiffer(differ);
        return personLastBO;
    }

    @Override
    public List<TimeCountBO> timeCount(TaskNodeDTO dto) throws SerException {
        LocalDate start = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea();
        String[] departs = dto.getDepart();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return tCount(dto, areas, departs);
        } else if (CountType.WHOLE.equals(countType)) {
            List<String> as = areas();
            String[] area = new String[as.size()];
            if (!as.isEmpty()) {
                area = as.toArray(area);
            }
            List<String> ds = departs();
            String[] depart = new String[ds.size()];
            if (!ds.isEmpty()) {
                depart = ds.toArray(depart);
            }
            return tCount(dto, area, depart);
        } else if (CountType.DEPART.equals(countType)) {
            return tCount(dto, areas, departs);
        }
        return null;
    }

    private List<TimeCountBO> tCount(TaskNodeDTO dto, String[] areas, String[] departs) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.in("area", areas));
        projectDTO.getConditions().add(Restrict.in("depart", departs));
        List<Project> projects = projectSer.findByCis(projectDTO);
        List<TimeCountBO> timeCountBOS = new ArrayList<>();
        long noDifferSum = 0;
        long haveDifferSum = 0;  //总合计
        for (String area : areas) {
            long noDifferArea = 0;
            long haveDifferArea = 0;  //地区合计
            TimeCountBO timeCountBO = new TimeCountBO();
            List<TimeSonBO> timeSonBOS = new ArrayList<>();
            for (String depart : departs) {
                long noDifferDepart = 0;
                long haveDifferDepart = 0;  //部门合计
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    timeCountBO.setArea(area);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    TimeSonBO timeSonBO = getTime(taskNodes);
                    timeSonBO.setDepart(depart);
                    timeSonBOS.add(timeSonBO);
                    //部门合计
                    haveDifferDepart += timeSonBOS.stream().mapToLong(TimeSonBO::getHaveDiffer).sum();
                    noDifferDepart += timeSonBOS.stream().mapToLong(TimeSonBO::getNoDiffer).sum();
                    //地区合计
                    haveDifferArea += timeSonBOS.stream().mapToLong(TimeSonBO::getHaveDiffer).sum();
                    noDifferArea += timeSonBOS.stream().mapToLong(TimeSonBO::getNoDiffer).sum();
                    //总合计
                    haveDifferSum += timeSonBOS.stream().mapToLong(TimeSonBO::getHaveDiffer).sum();
                    noDifferSum += timeSonBOS.stream().mapToLong(TimeSonBO::getNoDiffer).sum();
                }
                TimeSonBO departCount = departCount(noDifferDepart, haveDifferDepart);
                timeSonBOS.add(departCount);
            }
            timeCountBO.setTimeSonS(timeSonBOS);
            timeCountBOS.add(timeCountBO);
            TimeCountBO areaCount = sum(noDifferArea, haveDifferArea, "地区合计");
            timeCountBOS.add(areaCount);
        }
        TimeCountBO sum = sum(noDifferSum, haveDifferSum, "总合计");
        timeCountBOS.add(sum);
        return timeCountBOS;
    }

    //部门合计
    private TimeSonBO departCount(Long noDiffer, Long haveDiffer) throws SerException {
        TimeSonBO depart = new TimeSonBO();
        depart.setDepart("合计");
        depart.setNoDiffer(noDiffer);
        depart.setHaveDiffer(haveDiffer);
        return depart;
    }

    //合计
    private TimeCountBO sum(Long noDiffer, Long haveDiffer, String type) throws SerException {
        TimeCountBO sum = new TimeCountBO();
        if ("总合计".equals(type)) {
            sum.setArea("总合计");
        } else if ("地区合计".equals(type)) {
            sum.setArea("合计");
        }
        List<TimeSonBO> timeSonBOS = new ArrayList<>();
        TimeSonBO depart = new TimeSonBO();
        depart.setNoDiffer(noDiffer);
        depart.setHaveDiffer(haveDiffer);
        timeSonBOS.add(depart);
        sum.setTimeSonS(timeSonBOS);
        return sum;
    }

    private TimeSonBO getTime(List<TaskNode> taskNodes) throws SerException {
        List<TaskNode> list = taskNodes.stream().filter(taskNode -> FinishStatus.FINISH.equals(taskNode.getFinishStatus())).collect(Collectors.toList());
        long noDiffer = 0;
        long haveDiffer = 0;
        for (TaskNode t : list) {
            TimeType needType = t.getNeedType();
            double needTime = t.getNeedTime();
            switch (needType) {
                case MINUTE:
                    needTime = new BigDecimal(needTime / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;
                case HOUR:
                    break;
                case DAY:
                    needTime = needTime * 8;
                    break;
            }
            TimeType actualType = t.getActualType();
            double actualTime = t.getActualTime();
            switch (actualType) {
                case MINUTE:
                    actualTime = new BigDecimal(actualTime / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    break;
                case HOUR:
                    break;
                case DAY:
                    actualTime = actualTime * 8;
                    break;
            }
            if (actualTime >= needTime) {
                noDiffer++;
            } else {
                haveDiffer++;
            }
        }
        TimeSonBO timeSonBO = new TimeSonBO();
        timeSonBO.setNoDiffer(noDiffer);
        timeSonBO.setHaveDiffer(haveDiffer);
        return timeSonBO;
    }

    @Override
    public List<ConfirmCountBO> confirmCount(TaskNodeDTO dto) throws SerException {
        LocalDate start = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea();
        String[] departs = dto.getDepart();
        String[] names = dto.getName();
        String[] tables = dto.getTables();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return cCount(dto, areas, departs, null, tables);
        } else if (CountType.WHOLE.equals(countType)) {
            List<String> as = areas();
            String[] area = new String[as.size()];
            if (!as.isEmpty()) {
                area = as.toArray(area);
            }
            List<String> ds = departs();
            String[] depart = new String[ds.size()];
            if (!ds.isEmpty()) {
                depart = ds.toArray(depart);
            }
            return cCount(dto, area, depart, null, tables);
        } else if (CountType.DEPART.equals(countType)) {
            return cCount(dto, areas, departs, null, tables);
        } else if (CountType.PERSON.equals(countType)) {
            return cCount(dto, areas, departs, names, tables);
        }
        return null;
    }

    private List<ConfirmCountBO> cCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] names, String[] ts) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.in("area", areas));
        projectDTO.getConditions().add(Restrict.in("depart", departs));
        List<Project> projects = projectSer.findByCis(projectDTO);
        List<ConfirmCountBO> confirmCountBOS = new ArrayList<>();
        long haveInitiateSum = 0;
        long noInitiateSum = 0;
        long confirmSum = 0;
        long unConfirmSum = 0;
        long toConfirmSum = 0;     //总合计
        for (String area : areas) {    //地区，最外层对象
            long haveInitiateArea = 0;
            long noInitiateArea = 0;
            long confirmArea = 0;
            long unConfirmArea = 0;
            long toConfirmArea = 0;     //地区合计
            ConfirmCountBO confirmCountBO = new ConfirmCountBO();
            List<ConfirmSonBO> confirmSonBOS = new ArrayList<>();
            for (String depart : departs) {   //子层
                ConfirmSonBO confirmSonBO = new ConfirmSonBO();
                List<ConfirmTableBO> confirmTableBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    confirmCountBO.setArea(area);
                    confirmSonBO.setDepart(depart);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    if (null != ts) {
                        tableDTO.getConditions().add(Restrict.in("name", ts));
                    }
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    long haveInitiateDepart = 0;
                    long noInitiateDepart = 0;
                    long confirmDepart = 0;
                    long unConfirmDepart = 0;
                    long toConfirmDepart = 0;     //部门合计
                    for (String table : tableIds) {   //孙子
                        List<ConfirmGrandSonBO> confirmGrandSonBOS = new ArrayList<>();
                        ConfirmTableBO tableBO = new ConfirmTableBO();
                        tableBO.setTableName(tableSer.findById(table).getName());
                        //最底层
                        if (null != names) {
                            for (String name : names) {
                                List<ConfirmLastBO> confirmLastBOS = new ArrayList<>();
                                ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO();
                                confirmGrandSonBO.setName(name);
                                for (TaskType taskType : taskTypes()) {
                                    ConfirmLastBO confirmLastBO = getConfirm1(taskNodes, taskType, name, table);
                                    confirmLastBOS.add(confirmLastBO);
                                }
                                confirmGrandSonBO.setConfirmLastS(confirmLastBOS);
                                confirmGrandSonBOS.add(confirmGrandSonBO);
                                //部门合计
                                haveInitiateDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                                noInitiateDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                                confirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                                unConfirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                                toConfirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                                //地区合计
                                haveInitiateArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                                noInitiateArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                                confirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                                unConfirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                                toConfirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                                //总合计
                                haveInitiateSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                                noInitiateSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                                confirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                                unConfirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                                toConfirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                            }
                        } else {
                            List<ConfirmLastBO> confirmLastBOS = new ArrayList<>();
                            ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO();
                            for (TaskType taskType : taskTypes()) {
                                ConfirmLastBO confirmLastBO = getConfirm(taskNodes, taskType, table);
                                confirmLastBOS.add(confirmLastBO);
                            }
                            confirmGrandSonBO.setConfirmLastS(confirmLastBOS);
                            confirmGrandSonBOS.add(confirmGrandSonBO);
                            //部门合计
                            haveInitiateDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                            noInitiateDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                            confirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                            unConfirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                            toConfirmDepart += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                            //地区合计
                            haveInitiateArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                            noInitiateArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                            confirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                            unConfirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                            toConfirmArea += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                            //总合计
                            haveInitiateSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getHaveInitiate()).sum();
                            noInitiateSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getNoInitiate()).sum();
                            confirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getConfirm()).sum();
                            unConfirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getUnConfirm()).sum();
                            toConfirmSum += confirmLastBOS.stream().mapToLong(confirmLastBO -> confirmLastBO.getToConfirm()).sum();
                        }
                        tableBO.setGrandSonS(confirmGrandSonBOS);
                        confirmTableBOS.add(tableBO);
                    }
                    confirmSonBO.setTableS(confirmTableBOS);
                    confirmSonBOS.add(confirmSonBO);
                    ConfirmSonBO departCount = departSum(haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart);
                    confirmSonBOS.add(departCount);
                }
            }
            confirmCountBO.setConfirmSonS(confirmSonBOS);
            confirmCountBOS.add(confirmCountBO);
            ConfirmCountBO areaCount = sum(haveInitiateArea, noInitiateArea, confirmArea, unConfirmArea, toConfirmArea, "地区合计");
            confirmCountBOS.add(areaCount);
        }
        ConfirmCountBO sum = sum(haveInitiateSum, noInitiateSum, confirmSum, unConfirmSum, toConfirmSum, "总合计");
        confirmCountBOS.add(sum);
        return confirmCountBOS;
    }

    //部门合计
    private ConfirmSonBO departSum(long haveInitiateDepart,
                                   long noInitiateDepart,
                                   long confirmDepart,
                                   long unConfirmDepart,
                                   long toConfirmDepart) throws SerException {
        List<ConfirmLastBO> confirmLastS = new ArrayList<>();
        ConfirmLastBO lastBO = new ConfirmLastBO(haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart);
        confirmLastS.add(lastBO);
        List<ConfirmGrandSonBO> grandSonS = new ArrayList<>();
        ConfirmGrandSonBO grandSonBO = new ConfirmGrandSonBO();
        grandSonBO.setConfirmLastS(confirmLastS);
        grandSonS.add(grandSonBO);
        List<ConfirmTableBO> tableS = new ArrayList<>();
        ConfirmTableBO confirmTableBO = new ConfirmTableBO();
        confirmTableBO.setGrandSonS(grandSonS);
        tableS.add(confirmTableBO);
        ConfirmSonBO departCount = new ConfirmSonBO();
        departCount.setTableS(tableS);
        departCount.setDepart("合计");
        return departCount;
    }

    //总合计
    private ConfirmCountBO sum(long haveInitiateDepart,
                               long noInitiateDepart,
                               long confirmDepart,
                               long unConfirmDepart,
                               long toConfirmDepart, String s) throws SerException {
        List<ConfirmLastBO> confirmLastS = new ArrayList<>();
        ConfirmLastBO lastBO = new ConfirmLastBO(haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart);
        confirmLastS.add(lastBO);
        List<ConfirmGrandSonBO> grandSonS = new ArrayList<>();
        ConfirmGrandSonBO grandSonBO = new ConfirmGrandSonBO();
        grandSonBO.setConfirmLastS(confirmLastS);
        grandSonS.add(grandSonBO);
        List<ConfirmTableBO> tableS = new ArrayList<>();
        ConfirmTableBO confirmTableBO = new ConfirmTableBO();
        confirmTableBO.setGrandSonS(grandSonS);
        tableS.add(confirmTableBO);
        ConfirmSonBO departCount = new ConfirmSonBO();
        departCount.setTableS(tableS);
        List<ConfirmSonBO> sonBOS = new ArrayList<>();
        sonBOS.add(departCount);
        ConfirmCountBO confirmCountBO = new ConfirmCountBO();
        if ("总合计".equals(s)) {
            confirmCountBO.setArea("总合计");
        } else if ("地区合计".equals(s)) {
            confirmCountBO.setArea("合计");
        }
        confirmCountBO.setConfirmSonS(sonBOS);
        return confirmCountBO;
    }

    private ConfirmLastBO getConfirm(List<TaskNode> taskNodes, TaskType taskType, String tableId) throws SerException {
        long haveInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getInitiate()) && tableId.equals(taskNode.getTable().getId())).count();
        long noInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null == taskNode.getInitiate()) && tableId.equals(taskNode.getTable().getId())).count();
        long confirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && taskNode.getConfirm() && tableId.equals(taskNode.getTable().getId())).count();
        long unConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && (!taskNode.getConfirm()) && tableId.equals(taskNode.getTable().getId())).count();
        long toConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && null == taskNode.getConfirm() && tableId.equals(taskNode.getTable().getId())).count();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO();
        confirmLastBO.setTaskType(taskType);
        confirmLastBO.setHaveInitiate(haveInitiate);
        confirmLastBO.setNoInitiate(noInitiate);
        confirmLastBO.setConfirm(confirm);
        confirmLastBO.setUnConfirm(unConfirm);
        confirmLastBO.setToConfirm(toConfirm);
        return confirmLastBO;
    }

    private ConfirmLastBO getConfirm1(List<TaskNode> taskNodes, TaskType taskType, String name, String tableId) throws SerException {
        long haveInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getInitiate()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).count();
        long noInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null == taskNode.getInitiate()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).count();
        long confirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && taskNode.getConfirm() && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).count();
        long unConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && (!taskNode.getConfirm()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).count();
        long toConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && null == taskNode.getConfirm() && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).count();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO();
        confirmLastBO.setTaskType(taskType);
        confirmLastBO.setHaveInitiate(haveInitiate);
        confirmLastBO.setNoInitiate(noInitiate);
        confirmLastBO.setConfirm(confirm);
        confirmLastBO.setUnConfirm(unConfirm);
        confirmLastBO.setToConfirm(toConfirm);
        return confirmLastBO;
    }

    @Override
    public List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException {
        LocalDate start = DateUtil.parseDate(dto.getStartTime());
        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea();
        String[] departs = dto.getDepart();
        String[] names = dto.getName();
        String[] tables = dto.getTables();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return fCount(dto, areas, departs, null, tables);
        } else if (CountType.WHOLE.equals(countType)) {
            List<String> as = areas();
            String[] area = new String[as.size()];
            if (!as.isEmpty()) {
                area = as.toArray(area);
            }
            List<String> ds = departs();
            String[] depart = new String[ds.size()];
            if (!ds.isEmpty()) {
                depart = ds.toArray(depart);
            }
            return fCount(dto, area, depart, null, tables);
        } else if (CountType.DEPART.equals(countType)) {
            return fCount(dto, areas, departs, null, tables);
        } else if (CountType.PERSON.equals(countType)) {
            return fCount(dto, areas, departs, names, tables);
        }
        return null;
    }

    private List<FinishCaseBO> fCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] names, String[] ts) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getConditions().add(Restrict.in("area", areas));
        projectDTO.getConditions().add(Restrict.in("depart", departs));
        List<Project> projects = projectSer.findByCis(projectDTO);
        List<FinishCaseBO> finishCaseBOS = new ArrayList<>();
        double planNumSum = 0;
        double actualNumSum = 0;
        double unFinishNumSum = 0;
        double planTimeSum = 0;
        double actualTimeSum = 0;
        double unFinishTimeSum = 0;
        double reportUnFinishSum = 0;
        double unReportUnFinishSum = 0;   //总合计
        for (String area : areas) {
            double planNumArea = 0;
            double actualNumArea = 0;
            double unFinishNumArea = 0;
            double planTimeArea = 0;
            double actualTimeArea = 0;
            double unFinishTimeArea = 0;
            double reportUnFinishArea = 0;
            double unReportUnFinishArea = 0;   //地区合计
            FinishCaseBO finishCaseBO = new FinishCaseBO();
            List<CaseSonBO> caseSonBOS = new ArrayList<>();
            for (String depart : departs) {
                double planNumDepart = 0;
                double actualNumDepart = 0;
                double unFinishNumDepart = 0;
                double planTimeDepart = 0;
                double actualTimeDepart = 0;
                double unFinishTimeDepart = 0;
                double reportUnFinishDepart = 0;
                double unReportUnFinishDepart = 0;   //部门合计
                CaseSonBO caseSonBO = new CaseSonBO();
                List<CaseTableBO> tableBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    finishCaseBO.setArea(area);
                    caseSonBO.setDepart(depart);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    if (null != ts) {
                        tableDTO.getConditions().add(Restrict.in("name", ts));
                    }
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    for (String table : tableIds) {
                        CaseTableBO tableBO = new CaseTableBO();
                        List<CaseGrandSonBO> grandSonBOS = new ArrayList<>();
                        tableBO.setTableName(tableSer.findById(table).getName());
                        if (null != names) {
                            for (String name : names) {
                                List<CaseLastBO> caseLastBOS = new ArrayList<>();
                                CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO();
                                caseGrandSonBO.setName(name);
                                for (TaskType taskType : taskTypes()) {
                                    CaseLastBO caseLastBO = get1(taskNodes, taskType, name, table);
                                    caseLastBOS.add(caseLastBO);
                                }
                                caseGrandSonBO.setCaseLastS(caseLastBOS);
                                grandSonBOS.add(caseGrandSonBO);
                                //部门合计
                                planNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                                actualNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                                unFinishNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                                planTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                                actualTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                                unFinishTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                                reportUnFinishDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                                unReportUnFinishDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                                //地区合计
                                planNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                                actualNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                                unFinishNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                                planTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                                actualTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                                unFinishTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                                reportUnFinishArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                                unReportUnFinishArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                                //总合计
                                planNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                                actualNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                                unFinishNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                                planTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                                actualTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                                unFinishTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                                reportUnFinishSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                                unReportUnFinishSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                            }
                        } else {
                            List<CaseLastBO> caseLastBOS = new ArrayList<>();
                            CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO();
                            for (TaskType taskType : taskTypes()) {
                                CaseLastBO caseLastBO = get(taskNodes, taskType, table);
                                caseLastBOS.add(caseLastBO);
                            }
                            caseGrandSonBO.setCaseLastS(caseLastBOS);
                            grandSonBOS.add(caseGrandSonBO);
                            //部门合计
                            planNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                            actualNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                            unFinishNumDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                            planTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                            actualTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                            unFinishTimeDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                            reportUnFinishDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                            unReportUnFinishDepart += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                            //地区合计
                            planNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                            actualNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                            unFinishNumArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                            planTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                            actualTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                            unFinishTimeArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                            reportUnFinishArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                            unReportUnFinishArea += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                            //总合计
                            planNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanNum).sum();
                            actualNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualNum).sum();
                            unFinishNumSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishNum).sum();
                            planTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getPlanTime).sum();
                            actualTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getActualTime).sum();
                            unFinishTimeSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnFinishTime).sum();
                            reportUnFinishSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getReportUnFinish).sum();
                            unReportUnFinishSum += caseLastBOS.stream().mapToDouble(CaseLastBO::getUnReportUnFinish).sum();
                        }
                        tableBO.setGrandSonS(grandSonBOS);
                        tableBOS.add(tableBO);
                    }
                }
                caseSonBO.setCaseTableS(tableBOS);
                caseSonBOS.add(caseSonBO);
                CaseSonBO departCount = departCount(planNumDepart, actualNumDepart, unFinishNumDepart, planTimeDepart, actualTimeDepart, unFinishTimeDepart, reportUnFinishDepart, unReportUnFinishDepart);
                caseSonBOS.add(departCount);
            }
            finishCaseBO.setCaseSonS(caseSonBOS);
            finishCaseBOS.add(finishCaseBO);
            FinishCaseBO areaCount = sum(planNumArea, actualNumArea, unFinishNumArea, planTimeArea, actualTimeArea, unFinishTimeArea, reportUnFinishArea, unReportUnFinishArea, "地区合计");
            finishCaseBOS.add(areaCount);
        }
        FinishCaseBO sum = sum(planNumSum, actualNumSum, unFinishNumSum, planTimeSum, actualTimeSum, unFinishTimeSum, reportUnFinishSum, unReportUnFinishSum, "总合计");
        finishCaseBOS.add(sum);
        return finishCaseBOS;
    }

    //部门合计
    private CaseSonBO departCount(Double planNum, Double actualNum, Double unFinishNum, Double planTime, Double actualTime, Double unFinishTime, Double reportUnFinish, Double unReportUnFinish) throws SerException {
        CaseLastBO lastBO = new CaseLastBO(planNum, actualNum, unFinishNum, planTime, actualTime, unFinishTime, reportUnFinish, unReportUnFinish);
        CaseSonBO depart = new CaseSonBO();
        depart.setDepart("合计");
        List<CaseTableBO> caseTableS = new ArrayList<>();
        CaseTableBO tableBO = new CaseTableBO();
        List<CaseGrandSonBO> grandSonS = new ArrayList<>();
        CaseGrandSonBO grandSonBO = new CaseGrandSonBO();
        List<CaseLastBO> caseLastS = new ArrayList<>();
        caseLastS.add(lastBO);
        grandSonBO.setCaseLastS(caseLastS);
        grandSonS.add(grandSonBO);
        tableBO.setGrandSonS(grandSonS);
        caseTableS.add(tableBO);
        depart.setCaseTableS(caseTableS);
        return depart;
    }

    //合计
    private FinishCaseBO sum(Double planNum, Double actualNum, Double unFinishNum, Double planTime, Double actualTime, Double unFinishTime, Double reportUnFinish, Double unReportUnFinish, String type) throws SerException {
        CaseLastBO lastBO = new CaseLastBO(planNum, actualNum, unFinishNum, planTime, actualTime, unFinishTime, reportUnFinish, unReportUnFinish);
        CaseSonBO depart = new CaseSonBO();
        List<CaseTableBO> caseTableS = new ArrayList<>();
        CaseTableBO tableBO = new CaseTableBO();
        List<CaseGrandSonBO> grandSonS = new ArrayList<>();
        CaseGrandSonBO grandSonBO = new CaseGrandSonBO();
        List<CaseLastBO> caseLastS = new ArrayList<>();
        caseLastS.add(lastBO);
        grandSonBO.setCaseLastS(caseLastS);
        grandSonS.add(grandSonBO);
        tableBO.setGrandSonS(grandSonS);
        caseTableS.add(tableBO);
        depart.setCaseTableS(caseTableS);
        List<CaseSonBO> caseSonBOS = new ArrayList<>();
        caseSonBOS.add(depart);
        FinishCaseBO finishCaseBO = new FinishCaseBO();
        if ("地区合计".equals(type)) {
            finishCaseBO.setArea("合计");
        } else if ("总合计".equals(type)) {
            finishCaseBO.setArea("总合计");
        }
        finishCaseBO.setCaseSonS(caseSonBOS);
        return finishCaseBO;
    }

    private CaseLastBO get(List<TaskNode> taskNodes, TaskType taskType, String tableId) throws SerException {
        double planNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getPlanNum).sum();
        double actualNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && null != taskNode.getActualNum() && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualNum).sum();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planTime = new BigDecimal(planMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualTime = new BigDecimal(actualMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneTime = new BigDecimal(undoneMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinish = new BigDecimal(reportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinish = new BigDecimal(unReportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
        CaseLastBO caseLastBO = new CaseLastBO();
        caseLastBO.setTaskType(taskType);
        caseLastBO.setPlanNum(planNum);
        caseLastBO.setActualNum(actualNum);
        caseLastBO.setUnFinishNum(unFinishNum);
        caseLastBO.setPlanTime(planTime);
        caseLastBO.setActualTime(actualTime);
        caseLastBO.setUnFinishTime(undoneTime);
        caseLastBO.setReportUnFinish(reportUnFinish);
        caseLastBO.setUnReportUnFinish(unReportUnFinish);
        return caseLastBO;
    }

    private CaseLastBO get1(List<TaskNode> taskNodes, TaskType taskType, String name, String tableId) throws SerException {
        double planNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getPlanNum).sum();
        double actualNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && (null != taskNode.getActualNum()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualNum).sum();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getNeedTime).sum();
        double planTime = new BigDecimal(planMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double actualTime = new BigDecimal(actualMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneTime = new BigDecimal(undoneMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinish = new BigDecimal(reportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && (null != taskNode.getReport()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute()) && (null != taskNode.getActualTime()) && tableId.equals(taskNode.getTable().getId())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinish = new BigDecimal(unReportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
        CaseLastBO caseLastBO = new CaseLastBO();
        caseLastBO.setTaskType(taskType);
        caseLastBO.setPlanNum(planNum);
        caseLastBO.setActualNum(actualNum);
        caseLastBO.setUnFinishNum(unFinishNum);
        caseLastBO.setPlanTime(planTime);
        caseLastBO.setActualTime(actualTime);
        caseLastBO.setUnFinishTime(undoneTime);
        caseLastBO.setReportUnFinish(reportUnFinish);
        caseLastBO.setUnReportUnFinish(unReportUnFinish);
        return caseLastBO;
    }

    private List<TaskType> taskTypes() throws SerException {
        List<TaskType> list = new ArrayList<>();
        list.add(TaskType.ADMININSTRATION);
        list.add(TaskType.ENGINEERING);
        list.add(TaskType.TRAINING);
        return list;
    }

    @Override
    public List<String> areas() throws SerException {
        List<Project> list = projectSer.findAll();
        Set<String> s = list.stream().map(project -> project.getArea()).collect(Collectors.toSet());
        return new ArrayList<>(s);
    }

    @Override
    public List<String> departs() throws SerException {
        List<Project> list = projectSer.findAll();
        Set<String> s = list.stream().map(project -> project.getDepart()).collect(Collectors.toSet());
        return new ArrayList<>(s);
    }

    @Override
    public void confirm(String id) throws SerException {
        TaskNode entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setConfirm(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void unConfirm(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setConfirm(false);
        entity.setReason(to.getReason());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<DayBO> dayReport(String time, String[] names) throws SerException {
        LocalDate date = DateUtil.parseDate(time);
        List<DayBO> bos = new ArrayList<>();
        for (String string : names) {
            String depart = null;
            String position = null;
            Map<String, String> map = positionUserDetailAPI.departPosition(string);
            Set<String> set = map.keySet();
            for (String s : set) {
                depart = s;
                position = map.get(s);
            }
            TaskNodeDTO dto = new TaskNodeDTO();  //当天
            dto.getConditions().add(Restrict.eq("execute", string));
            dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{date, date}));
            List<TaskNode> currents = super.findByCis(dto);
            TaskNodeDTO next = new TaskNodeDTO();    //明天
            next.getConditions().add(Restrict.eq("execute", string));
            next.getConditions().add(Restrict.between("startTime", new LocalDate[]{date.plusDays(1), date.plusDays(1)}));
            List<TaskNode> nexts = super.findByCis(next);
            for (TaskNode current : currents) {
                String name = current.getExecute();
                DayBO bo = BeanTransform.copyProperties(current, DayBO.class);
                bo.setName(name);
                bo.setTime(time);
                double taskTime = time(current.getNeedTime(), current.getNeedType());
                bo.setTaskTime(taskTime);
                bo.setDepart(depart);
                bo.setPosition(position);
                bos.add(bo);
            }
            for (int i = 0; i < bos.size(); i++) {
                if (i < nexts.size()) {
                    TaskNode taskNode = nexts.get(i);
                    DayBO bo = bos.get(i);
                    bo.setPlan(taskNode.getContent());
                    bo.setPlanTime(time(taskNode.getNeedTime(), taskNode.getNeedType()));
                }
            }
            if (nexts.size() > currents.size()) {    //明天任务条数比当天多的情况
                int i = currents.size();
                while (i < nexts.size()) {
                    DayBO bo = new DayBO();
                    bo.setTime(time);
                    bo.setName(string);
                    bo.setDepart(depart);
                    bo.setPosition(position);
                    TaskNode taskNode = nexts.get(i);
                    bo.setPlan(taskNode.getContent());
                    bo.setPlanTime(time(taskNode.getNeedTime(), taskNode.getNeedType()));
                    bos.add(bo);
                    i++;
                }
            }
        }
        return bos;
    }

    private Double time(double time, TimeType type) throws SerException {
        switch (type) {
            case MINUTE:
                time = time / 60;
                break;
            case DAY:
                time = time * 8;
                break;
        }
        return time;
    }

    @Override
    public DayReportCountBO dayCount(String startTime, String endTime, String[] departIds) throws SerException {
        List<DayCountBO> dayCountBOS = get(startTime, endTime, departIds);
        Set<String> areas = dayCountBOS.stream().filter(dayCountBO -> null != dayCountBO.getArea()).map(DayCountBO::getArea).collect(Collectors.toSet());
        DayReportCountBO dayReportCountBO = new DayReportCountBO();
        dayReportCountBO.setTime(startTime + "-" + endTime);
        List<DayABO> abos = new ArrayList<>();
        int sumNum = 0;   //总人数
        double sumPlanNum = 0;   //计划任务量总合计
        double sumActualNum = 0;   //完成任务量总合计
        double sumTaskTime = 0;   //任务时长总合计
        double sumActualTime = 0;   //实际时长总合计
        double sumOutTime = 0;   //加班时长总合计
        for (String area : areas) {
            DayABO abo = new DayABO();
            abo.setArea(area);
            List<DayBBO> bbos = new ArrayList<>();
            Set<String> departs = dayCountBOS.stream().filter(dayCountBO -> (null != dayCountBO.getDepart()) && area.equals(dayCountBO.getArea())).map(DayCountBO::getDepart).collect(Collectors.toSet());
            for (String depart : departs) {
                int departNum = 0;   //部门总人数
                double departPlanNum = 0;   //计划任务量部门合计
                double departActualNum = 0;   //完成任务量部门合计
                double departTaskTime = 0;   //任务时长部门合计
                double departActualTime = 0;   //实际时长部门合计
                double departOutTime = 0;   //加班时长部门合计
                DayBBO bbo = new DayBBO();
                bbo.setDepart(depart);
                List<DayCBO> cbos = new ArrayList<>();
                Set<String> positions = dayCountBOS.stream().filter(dayCountBO -> (null != dayCountBO.getPosition()) && depart.equals(dayCountBO.getDepart())).map(DayCountBO::getPosition).collect(Collectors.toSet());
                for (String position : positions) {
                    DayCBO cbo = new DayCBO();
                    cbo.setPosition(position);
                    List<DayDBO> dbos = new ArrayList<>();
                    Set<String> names = dayCountBOS.stream().filter(dayCountBO -> (null != dayCountBO.getName()) && position.equals(dayCountBO.getPosition())).map(DayCountBO::getName).collect(Collectors.toSet());
                    for (String name : names) {
                        List<DayCountBO> list = dayCountBOS.stream().filter(dayCountBO -> name.equals(dayCountBO.getName())).collect(Collectors.toList());
                        if (!list.isEmpty()) {
                            DayDBO dbo = BeanTransform.copyProperties(list.get(0), DayDBO.class);
                            sumNum++;
                            departNum++;
                            departPlanNum += dbo.getPlanNum();
                            sumPlanNum += dbo.getPlanNum();
                            departActualNum += dbo.getActualNum();
                            sumActualNum += dbo.getActualNum();
                            departTaskTime += dbo.getTaskTime();
                            sumTaskTime += dbo.getTaskTime();
                            departActualTime += dbo.getActualTime();
                            sumActualTime += dbo.getActualTime();
                            departOutTime += dbo.getOutTime();
                            sumOutTime += dbo.getOutTime();
                            dbos.add(dbo);
                        }
                    }
                    cbo.setSons(dbos);
                    cbos.add(cbo);
                }
                bbo.setSons(cbos);
                bbos.add(bbo);
                //部门合计
                bbos.add(count(departNum, departPlanNum, departActualNum, departTaskTime, departActualTime, departOutTime, "合计"));
            }
            abo.setSons(bbos);
            abos.add(abo);
        }
        DayABO count = new DayABO();
        List<DayBBO> bbos = new ArrayList<>();
        //总合计
        DayBBO bbo = count(sumNum, sumPlanNum, sumActualNum, sumTaskTime, sumActualTime, sumOutTime, "总合计");
        bbos.add(bbo);
        count.setSons(bbos);
        abos.add(count);
        dayReportCountBO.setSons(abos);
        return dayReportCountBO;
    }

    private DayBBO count(int num, double planNum, double actualNum, double taskTime, double actualTime, double outTime, String type) throws SerException {
        DayBBO count = new DayBBO();
        List<DayCBO> cbos = new ArrayList<>();
        if ("总合计".equals(type)) {
            count.setDepart("总合计");
        } else {
            count.setDepart("合计");
        }
        DayCBO cbo = new DayCBO();
        List<DayDBO> dbos = new ArrayList<>();
        DayDBO dbo = new DayDBO();
        dbo.setName(num + "人");
        dbo.setPlanNum(planNum);
        dbo.setActualNum(actualNum);
        dbo.setTaskTime(taskTime);
        dbo.setActualTime(actualTime);
        dbo.setOutTime(outTime);
        dbos.add(dbo);
        cbo.setSons(dbos);
        cbos.add(cbo);
        count.setSons(cbos);
        return count;
    }

    private List<DayCountBO> get(String startTime, String endTime, String[] departIds) throws SerException {
        TaskNodeDTO dto = new TaskNodeDTO();
        LocalDate start = DateUtil.parseDate(startTime);
        LocalDate end = DateUtil.parseDate(endTime);
        dto.getConditions().add(Restrict.between("startExecute", new LocalDate[]{start, start}));
        dto.getConditions().add(Restrict.between("endExecute", new LocalDate[]{end, end}));
        if (null != departIds) {
            Set<String> set = new HashSet<>();
            for (String s : departIds) {
                Set<String> names = departmentDetailAPI.departPersons(s);   //查找某个部门的所有人
                if (null != names) {
                    set.addAll(names);
                }
            }
            dto.getConditions().add(Restrict.in("execute", set));
        }
        List<TaskNode> list = super.findByCis(dto);
        Set<String> names = list.stream().map(TaskNode::getExecute).collect(Collectors.toSet());
        List<DayCountBO> dayCountBOS = new ArrayList<>();
        for (String name : names) {
            Stream<TaskNode> stream = list.stream().filter(taskNode -> name.equals(taskNode.getExecute()));
            //计划任务量
            double planNum = stream.mapToDouble(TaskNode::getPlanNum).sum();
            //完成任务量
            double actualNum = list.stream().filter(taskNode -> name.equals(taskNode.getExecute()) && (null != taskNode.getActualNum())).mapToDouble(TaskNode::getActualNum).sum();
            double needMin = list.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getNeedType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
            double needHour = list.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getNeedType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
            double needDay = list.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getNeedType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
            //任务时长(小时)
            double taskTime = new BigDecimal(needMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + needDay * 8 + needHour;
            double actualMin = list.stream().filter(taskNode -> (null != taskNode.getActualType()) && TimeType.MINUTE.equals(taskNode.getActualType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
            double actualHour = list.stream().filter(taskNode -> (null != taskNode.getActualType()) && TimeType.HOUR.equals(taskNode.getActualType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
            double actualDay = list.stream().filter(taskNode -> (null != taskNode.getActualType()) && TimeType.DAY.equals(taskNode.getActualType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
            //实际时长(小时)
            double actualTime = new BigDecimal(actualMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + actualDay * 8 + actualHour;
            //todo:加班时长(小时)
            PositionDetailBO positionDetailBO = positionUserDetailAPI.getPosition(name);
            DayCountBO dayCountBO = new DayCountBO();
            if (null != positionDetailBO) {
                dayCountBO.setArea(positionDetailBO.getArea());
                dayCountBO.setDepart(positionDetailBO.getDepartmentName());
                dayCountBO.setPosition(positionDetailBO.getPosition());
            }
            dayCountBO.setName(name);
            dayCountBO.setPlanNum(planNum);
            dayCountBO.setActualNum(actualNum);
            dayCountBO.setTaskTime(taskTime);
            dayCountBO.setActualTime(actualTime);
            dayCountBOS.add(dayCountBO);
        }
        return dayCountBOS;
    }

    @Override
    public Double finishDay(String date, String name) throws SerException {
        String sql = "SELECT" +
                "  actualTime," +
                "  actualType " +
                "FROM taskallotment_tasknode " +
                "WHERE '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d') AND finishStatus = 0 AND execute='" + name + "'";
        String[] fileds = new String[]{"actualTime", "actualType"};
        List<ObjectBO> list = super.findBySql(sql, ObjectBO.class, fileds);
        double finishDay = 0;   //当天的任务完成天数
        if (null != list) {
            for (ObjectBO o : list) {
                double time = o.getActualTime();
                int timeType = o.getActualType();
                switch (timeType) {
                    case 0:
                        time = new BigDecimal(time / 60 / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
                        break;
                    case 1:
                        time = new BigDecimal(time / 8).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
                        break;
                }
                finishDay += time;
            }
        }
        return finishDay;
    }

    @Override
    public List<ObjectBO> taskSituation(String[] names, String date) throws SerException {
        String sql = "SELECT actualType,actualTime,finishStatus FROM taskallotment_tasknode " +
                "WHERE execute in ('" + StringUtils.join(names, "','") + "') AND is_confirm=1 AND '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d')";
        String[] fileds = new String[]{"actualType", "actualTime", "finishStatus"};
        List<ObjectBO> list = super.findBySql(sql, ObjectBO.class, fileds);
        return list;
    }
}