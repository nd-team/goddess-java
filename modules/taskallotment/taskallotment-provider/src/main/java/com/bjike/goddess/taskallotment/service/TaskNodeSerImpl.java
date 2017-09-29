package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.dto.QuestionDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.*;
import com.bjike.goddess.taskallotment.enums.*;
import com.bjike.goddess.taskallotment.to.CustomTitleTO;
import com.bjike.goddess.taskallotment.to.QuestionTO;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.poi.ss.formula.functions.T;
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

    private TaskNode update(TaskNodeTO to) throws SerException {
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
    public List<TaskNodeBO> list(TaskNodeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<TaskNode> list = super.findByCis(dto, true);
        List<TaskNodeBO> bos = new ArrayList<>();
        for (TaskNode taskNode : list) {
            bos.add(tranBO(taskNode));
        }
        return bos;
    }

    private TaskNodeBO tranBO(TaskNode entity) throws SerException {
        TaskNodeBO bo = BeanTransform.copyProperties(entity, TaskNodeBO.class, "customTitles", "questions", "needTime", "executeTime", "actualTime", "undoneTime", "delayTime");
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
        Double delayTime = entity.getUndoneTime();
        TimeType delayType = entity.getUndoneType();
        if (null != delayTime && null != delayType) {
            String delay = toString(delayTime, delayType);
            bo.setDelayTime(delay);
        }
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
        if (null==execute){
            throw new SerException("该任务没有执行人");
        }
        String time1=to.getStartTime().substring(0,to.getStartTime().indexOf(" "));
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
            content = name + "给您分发了您的执行任务，请上系查看";
            email = internalContactsAPI.getEmail(execute);
        } else if (null != charge) {
            content = name + "给您分发了您负责的任务，请上系查看";
            email = internalContactsAPI.getEmail(charge);
        } else if (null != execute) {
            content = name + "给您分发了您的执行任务，请上系查看";
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
        TaskNode entity = BeanTransform.copyProperties(to, TaskNode.class, true,"table");
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
    public Long myInitiateNum() throws SerException {
        String name = userAPI.currentUser().getUsername();
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.eq("initiate", name));
        return super.count(dto);
    }

    @Override
    public void reback(String id) throws SerException {
        delete(id);
    }

    @Override
    public void finish(String id) throws SerException {
        TaskNode entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setTaskStatus(TaskStatus.FINISH);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void unFinish(String id) throws SerException {
        TaskNode entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setTaskStatus(TaskStatus.UNFINISHED);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
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
    public Long myChargeNum() throws SerException {
        String name = userAPI.currentUser().getUsername();
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.eq("charge", name));
        return super.count(dto);
    }

    @Override
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
    public Long myExecuteNum() throws SerException {
        String name = userAPI.currentUser().getUsername();
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.eq("execute", name));
        return super.count(dto);
    }

    @Override
    public void report(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = entity.getEndTime();
        Long mis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
    public void initiateAgain(TaskNodeTO to) throws SerException {
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
        if (entity.getQuestion()) {
            List<Question> questions = BeanTransform.copyProperties(to.getQuestions(), Question.class, true);
            entity.setQuestions(questions);
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<PersonCountBO> personCount(TaskNodeDTO dto) throws SerException {
        LocalDateTime start = DateUtil.parseDateTime(dto.getStartTime());
        LocalDateTime end = DateUtil.parseDateTime(dto.getEndTime());
        Set<String> areas = dto.getArea();
        Set<String> departs = dto.getDepart();
        Set<String> names = dto.getName();
        dto.getConditions().add(Restrict.eq("startExecute", start));
        dto.getConditions().add(Restrict.eq("endExecute", end));
        return pCount(dto, areas, departs, names);
    }

    private List<PersonCountBO> pCount(TaskNodeDTO dto, Set<String> areas, Set<String> departs, Set<String> names) throws SerException {
        List<Project> projects = projectSer.findAll();
        List<PersonCountBO> personCountBOS = new ArrayList<>();
        for (String area : areas) {
            PersonCountBO personCountBO = new PersonCountBO();
            List<PersonSonBO> personSonBOS = new ArrayList<>();
            for (String depart : departs) {
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
                }
                personSonBO.setPersonLastS(personLastBOS);
                personSonBOS.add(personSonBO);
            }
            personCountBO.setPersonSonS(personSonBOS);
            personCountBOS.add(personCountBO);
        }
        return personCountBOS;
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
        LocalDateTime start = DateUtil.parseDateTime(dto.getStartTime());
        LocalDateTime end = DateUtil.parseDateTime(dto.getEndTime());
        Set<String> areas = dto.getArea();
        Set<String> departs = dto.getDepart();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.eq("startExecute", start));
        dto.getConditions().add(Restrict.eq("endExecute", end));
        if (null == countType) {
            return tCount(dto, areas, departs);
        } else if (CountType.WHOLE.equals(countType)) {
            return tCount(dto, areas(), departs());
        } else if (CountType.DEPART.equals(countType)) {
            return tCount(dto, areas, departs);
        }
        return null;
    }

    private List<TimeCountBO> tCount(TaskNodeDTO dto, Set<String> areas, Set<String> departs) throws SerException {
        List<Project> projects = projectSer.findAll();
        List<TimeCountBO> timeCountBOS = new ArrayList<>();
        for (String area : areas) {
            TimeCountBO timeCountBO = new TimeCountBO();
            List<TimeSonBO> timeSonBOS = new ArrayList<>();
            for (String depart : departs) {
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
                }
            }
            timeCountBO.setTimeSonS(timeSonBOS);
            timeCountBOS.add(timeCountBO);
        }
        return timeCountBOS;
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
        LocalDateTime start = DateUtil.parseDateTime(dto.getStartTime());
        LocalDateTime end = DateUtil.parseDateTime(dto.getEndTime());
        Set<String> areas = dto.getArea();
        Set<String> departs = dto.getDepart();
        Set<String> names = dto.getName();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.eq("startExecute", start));
        dto.getConditions().add(Restrict.eq("endExecute", end));
        if (null == countType) {
            return cCount(dto, areas, departs, null);
        } else if (CountType.WHOLE.equals(countType)) {
            return cCount(dto, areas(), departs(), null);
        } else if (CountType.DEPART.equals(countType)) {
            return cCount(dto, areas, departs, null);
        } else if (CountType.PERSON.equals(countType)) {
            return cCount(dto, areas, departs, names);
        }
        return null;
    }

    private List<ConfirmCountBO> cCount(TaskNodeDTO dto, Set<String> areas, Set<String> departs, Set<String> names) throws SerException {
        List<Project> projects = projectSer.findAll();
        List<ConfirmCountBO> confirmCountBOS = new ArrayList<>();
        for (String area : areas) {
            ConfirmCountBO confirmCountBO = new ConfirmCountBO();
            List<ConfirmSonBO> confirmSonBOS = new ArrayList<>();
            for (String depart : departs) {
                ConfirmSonBO confirmSonBO = new ConfirmSonBO();
                List<ConfirmGrandSonBO> confirmGrandSonBOS = new ArrayList<>();
                List<ConfirmLastBO> confirmLastBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    confirmCountBO.setArea(area);
                    confirmSonBO.setDepart(depart);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    if (null != names) {
                        for (String name : names) {
                            ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO();
                            confirmGrandSonBO.setName(name);
                            for (TaskType taskType : taskTypes()) {
                                ConfirmLastBO confirmLastBO = getConfirm1(taskNodes, taskType, name);
                                confirmLastBOS.add(confirmLastBO);
                            }
                            confirmGrandSonBO.setConfirmLastS(confirmLastBOS);
                            confirmGrandSonBOS.add(confirmGrandSonBO);
                        }
                    } else {
                        ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO();
                        for (TaskType taskType : taskTypes()) {
                            ConfirmLastBO confirmLastBO = getConfirm(taskNodes, taskType);
                            confirmLastBOS.add(confirmLastBO);
                        }
                        confirmGrandSonBO.setConfirmLastS(confirmLastBOS);
                        confirmGrandSonBOS.add(confirmGrandSonBO);
                    }
                }
                confirmSonBO.setConfirmGrandSonS(confirmGrandSonBOS);
                confirmSonBOS.add(confirmSonBO);
            }
            confirmCountBO.setConfirmSonS(confirmSonBOS);
            confirmCountBOS.add(confirmCountBO);
        }
        return confirmCountBOS;
    }

    private ConfirmLastBO getConfirm(List<TaskNode> taskNodes, TaskType taskType) throws SerException {
        long haveInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getInitiate())).count();
        long noInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null == taskNode.getInitiate())).count();
        long confirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && taskNode.getConfirm()).count();
        long unConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && (!taskNode.getConfirm())).count();
        long toConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && null == taskNode.getConfirm()).count();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO();
        confirmLastBO.setHaveInitiate(haveInitiate);
        confirmLastBO.setNoInitiate(noInitiate);
        confirmLastBO.setConfirm(confirm);
        confirmLastBO.setUnConfirm(unConfirm);
        confirmLastBO.setToConfirm(toConfirm);
        return confirmLastBO;
    }

    private ConfirmLastBO getConfirm1(List<TaskNode> taskNodes, TaskType taskType, String name) throws SerException {
        long haveInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getInitiate()) && name.equals(taskNode.getExecute())).count();
        long noInitiate = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null == taskNode.getInitiate()) && name.equals(taskNode.getExecute())).count();
        long confirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && taskNode.getConfirm() && name.equals(taskNode.getExecute())).count();
        long unConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && (null != taskNode.getConfirm()) && (!taskNode.getConfirm()) && name.equals(taskNode.getExecute())).count();
        long toConfirm = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && null == taskNode.getConfirm() && name.equals(taskNode.getExecute())).count();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO();
        confirmLastBO.setHaveInitiate(haveInitiate);
        confirmLastBO.setNoInitiate(noInitiate);
        confirmLastBO.setConfirm(confirm);
        confirmLastBO.setUnConfirm(unConfirm);
        confirmLastBO.setToConfirm(toConfirm);
        return confirmLastBO;
    }

    @Override
    public List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException {
        LocalDateTime start = DateUtil.parseDateTime(dto.getStartTime());
        LocalDateTime end = DateUtil.parseDateTime(dto.getEndTime());
        Set<String> areas = dto.getArea();
        Set<String> departs = dto.getDepart();
        Set<String> names = dto.getName();
        CountType countType = dto.getCountType();
        dto.getConditions().add(Restrict.eq("startExecute", start));
        dto.getConditions().add(Restrict.eq("endExecute", end));
        if (null == countType) {
            return fCount(dto, areas, departs, null);
        } else if (CountType.WHOLE.equals(countType)) {
            return fCount(dto, areas(), departs(), null);
        } else if (CountType.DEPART.equals(countType)) {
            return fCount(dto, areas, departs, null);
        } else if (CountType.PERSON.equals(countType)) {
            return fCount(dto, areas, departs, names);
        }
        return null;
    }

    private List<FinishCaseBO> fCount(TaskNodeDTO dto, Set<String> areas, Set<String> departs, Set<String> names) throws SerException {
        List<Project> projects = projectSer.findAll();
        List<FinishCaseBO> finishCaseBOS = new ArrayList<>();
        for (String area : areas) {
            FinishCaseBO finishCaseBO = new FinishCaseBO();
            List<CaseSonBO> caseSonBOS = new ArrayList<>();
            for (String depart : departs) {
                CaseSonBO caseSonBO = new CaseSonBO();
                List<CaseGrandSonBO> caseGrandSonS = new ArrayList<>();
                List<CaseLastBO> caseLastBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream().filter(project -> area.equals(project.getArea()) && depart.equals(project.getDepart())).map(project -> project.getId()).collect(Collectors.toSet());
                TableDTO tableDTO = new TableDTO();
                if (!projectIds.isEmpty()) {
                    finishCaseBO.setArea(area);
                    caseSonBO.setDepart(depart);
                    tableDTO.getConditions().add(Restrict.in("project.id", projectIds));
                    List<Table> tables = tableSer.findByCis(tableDTO);
                    Set<String> tableIds = tables.stream().map(table -> table.getId()).collect(Collectors.toSet());
                    dto.getConditions().add(Restrict.in("table.id", tableIds));
                    List<TaskNode> taskNodes = super.findByCis(dto);
                    if (null != names) {
                        for (String name : names) {
                            CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO();
                            caseGrandSonBO.setName(name);
                            for (TaskType taskType : taskTypes()) {
                                CaseLastBO caseLastBO = get1(taskNodes, taskType, name);
                                caseLastBOS.add(caseLastBO);
                            }
                            caseGrandSonBO.setCaseLastS(caseLastBOS);
                            caseGrandSonS.add(caseGrandSonBO);
                        }
                    } else {
                        CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO();
                        for (TaskType taskType : taskTypes()) {
                            CaseLastBO caseLastBO = get(taskNodes, taskType);
                            caseLastBOS.add(caseLastBO);
                        }
                        caseGrandSonBO.setCaseLastS(caseLastBOS);
                        caseGrandSonS.add(caseGrandSonBO);
                    }
                }
                caseSonBO.setCaseGrandSonS(caseGrandSonS);
                caseSonBOS.add(caseSonBO);
            }
            finishCaseBO.setCaseSonS(caseSonBOS);
            finishCaseBOS.add(finishCaseBO);
        }
        return finishCaseBOS;
    }

    private CaseLastBO get(List<TaskNode> taskNodes, TaskType taskType) throws SerException {
        double planNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getPlanNum).sum();
        double actualNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualNum).sum();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getNeedTime).sum();
        double planHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getNeedTime).sum();
        double planDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getNeedTime).sum();
        double planTime = new BigDecimal(planMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
        double actualHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
        double actualDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
        double actualTime = new BigDecimal(actualMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneTime = new BigDecimal(undoneMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinish = new BigDecimal(reportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType())).mapToDouble(TaskNode::getActualTime).sum();
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

    private CaseLastBO get1(List<TaskNode> taskNodes, TaskType taskType, String name) throws SerException {
        double planNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getPlanNum).sum();
        double actualNum = taskNodes.stream().filter(taskNode -> taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualNum).sum();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
        double planHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
        double planDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getNeedType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getNeedTime).sum();
        double planTime = new BigDecimal(planMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
        double actualHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
        double actualDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && FinishStatus.FINISH.equals(taskNode.getFinishStatus()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
        double actualTime = new BigDecimal(actualMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getUndoneType()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getUndoneTime).sum();
        double undoneTime = new BigDecimal(undoneMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getDelayType()) && taskNode.getDelay() && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getDelayTime).sum();
        double reportUnFinish = new BigDecimal(reportUnFinishMin / 60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream().filter(taskNode -> TimeType.MINUTE.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishHour = taskNodes.stream().filter(taskNode -> TimeType.HOUR.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
        double unReportUnFinishDay = taskNodes.stream().filter(taskNode -> TimeType.DAY.equals(taskNode.getActualType()) && (!taskNode.getReport()) && taskType.equals(taskNode.getTaskType()) && name.equals(taskNode.getExecute())).mapToDouble(TaskNode::getActualTime).sum();
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

    private Set<String> areas() throws SerException {
        List<Project> list = projectSer.findAll();
        return list.stream().map(project -> project.getArea()).collect(Collectors.toSet());
    }

    private Set<String> departs() throws SerException {
        List<Project> list = projectSer.findAll();
        return list.stream().map(project -> project.getDepart()).collect(Collectors.toSet());
    }
}