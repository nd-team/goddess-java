package com.bjike.goddess.taskallotment.service;

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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.bo.DayReport.*;
import com.bjike.goddess.taskallotment.bo.figure.*;
import com.bjike.goddess.taskallotment.dto.*;
import com.bjike.goddess.taskallotment.entity.*;
import com.bjike.goddess.taskallotment.enums.*;
import com.bjike.goddess.taskallotment.excel.TaskNodeExcel;
import com.bjike.goddess.taskallotment.excel.TaskNodeLeadTO;
import com.bjike.goddess.taskallotment.excel.WholeTaskExportExcel;
import com.bjike.goddess.taskallotment.excel.WholeTaskLeadTO;
import com.bjike.goddess.taskallotment.to.*;
import com.bjike.goddess.taskallotment.vo.CollectDataVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
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
    @Autowired
    private TimeSetSer timeSetSer;
    @Autowired
    private EventAPI eventAPI;

    /**
     * 再次分发权限（层级级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "3" );
            if (!flag) {
                throw new SerException ( "您不是管理层人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );
    }

    /**
     * 再次分发权限（层级级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "3" );
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        Boolean flagAdd = guideAddIdentity ();
        if (flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus ();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case AGAIN:
                flag = guideAddIdentity ();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken ( userToken );
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(TaskNodeTO to) throws SerException {
        String tableId = to.getTableId ();
        TaskNode entity = BeanTransform.copyProperties ( to, TaskNode.class, true );
        entity.setTableId ( tableId );
        TaskType taskType = entity.getTaskType ();
        entity.setType ( type ( taskType ) );
//        entity.setCustomTitles(titles);
        super.save ( entity );
        List<CustomTitleTO> titleTOS = to.getCustomTitles ();
        List<CustomTitle> titles = new ArrayList<> ();
        if (null != titleTOS) {
            int i = 0;
            for (CustomTitleTO titleTO : titleTOS) {
                i++;
                if (titleTO.getMandatory ()) {
                    if (null == titleTO.getContent ()) {
                        throw new SerException ( titleTO.getTitle () + "为必填字段" );
                    }
                }
                titleTO.setId ( null );
                CustomTitle customTitle = BeanTransform.copyProperties ( titleTO, CustomTitle.class, true );
                customTitle.setTitleIndex ( i );
                customTitle.setTaskNodeId ( entity.getId () );
                titles.add ( customTitle );
            }
        }
        customTitleSer.save ( titles );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TaskNodeTO to) throws SerException {

        TaskNode entity = update ( to );

    }

    @Transactional(rollbackFor = {SerException.class})
    public TaskNode update(TaskNodeTO to) throws SerException {
//        String name = userAPI.currentUser().getUsername();

        TaskNode entity = super.findById ( to.getId () );

        if (null == entity) {
            throw new SerException ( "该对象不存在" );
        }
//        entity.setInitiate(name);
//        entity.setTaskStatus(TaskStatus.DOING);
//        List<CustomTitle> customTitles = entity.getCustomTitles();
//        if (null != customTitles && !customTitles.isEmpty()) {
//            customTitleSer.remove(customTitles);
//        }
//        QuestionDTO dto = new QuestionDTO();
//        dto.getConditions().add(Restrict.eq("taskNodeId", entity.getId()));
//        List<Question> questions = questionSer.findByCis(dto);
//        if (!questions.isEmpty()) {
//            questionSer.remove(questions);
//        }
        TaskNode taskNode = BeanTransform.copyProperties ( to, TaskNode.class, true );
        BeanUtils.copyProperties ( taskNode, entity, "id", "initiate", "createTime", "fatherId", "haveSon", "tableId", "time" );
        TaskType taskType = entity.getTaskType ();
        entity.setType ( type ( taskType ) );
//        entity.setQuestions(null);
//        entity.setCustomTitles(null);
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        CustomTitleDTO customTitleDTO = new CustomTitleDTO ();
        customTitleDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", entity.getId () ) );
        List<CustomTitleTO> customTitleTOS = to.getCustomTitles ();
        List<CustomTitle> customTitles1 = customTitleSer.findByCis ( customTitleDTO );
        if (!customTitles1.isEmpty ()) {
            customTitleSer.remove ( customTitles1 );
        }
        if (null != customTitleTOS) {
            int i = 0;
            for (CustomTitleTO customTitleTO : customTitleTOS) {
                i++;
                if (customTitleTO.getMandatory ()) {
                    if (null == customTitleTO.getContent ()) {
                        throw new SerException ( customTitleTO.getTitle () + "为必填字段" );
                    }
                }
                customTitleTO.setId ( null );
                CustomTitle customTitle = BeanTransform.copyProperties ( customTitleTO, CustomTitle.class, true );
                customTitle.setTitleIndex ( i );
                customTitle.setTaskNodeId ( entity.getId () );
                customTitleSer.save ( customTitle );
            }
        }
        QuestionDTO questionDTO = new QuestionDTO ();
        questionDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", entity.getId () ) );
        List<Question> questions = questionSer.findByCis ( questionDTO );
        questionSer.remove ( questions );
        List<QuestionTO> questionTOS = to.getQuestions ();
        if (null != questionTOS) {
            for (QuestionTO questionTO : questionTOS) {
                questionTO.setId ( null );
                Question question = BeanTransform.copyProperties ( questionTO, Question.class, true );
                question.setTaskNodeId ( entity.getId () );
                questionSer.save ( question );
            }
        }
        return entity;
    }

    private String type(TaskType taskType) throws SerException {
        if (TaskType.ADMININSTRATION.equals ( taskType )) {
            return "内部项目名称";
        } else {
            return "功能流程名称";
        }
    }

    @Override
    public List<TableBO> list(TableDTO dto) throws SerException {
        List<TableBO> tableBOS = new ArrayList<> ();
        dto.getConditions ().add ( Restrict.eq ( "projectId", dto.getProjectId () ) );
        List<Table> tables = tableSer.findByCis ( dto, true );
        for (Table t : tables) {
            TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
            taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", t.getId () ) );
            taskNodeDTO.getConditions ().add ( Restrict.isNull ( "fatherId" ) );
            List<TaskNode> taskNodes = super.findByCis ( taskNodeDTO );
            List<NodeBO> nodeBOS = BeanTransform.copyProperties ( taskNodes, NodeBO.class );
            TableBO tableBO = BeanTransform.copyProperties ( t, TableBO.class );
            tableBO.setNodeS ( nodeBOS );
            tableBOS.add ( tableBO );
        }
        return tableBOS;
    }

    @Override
    public Long count(TableDTO dto) throws SerException {
        dto.getConditions ().add ( Restrict.eq ( "projectId", dto.getProjectId () ) );
        return tableSer.count ( dto );
    }

    private TaskNodeBO tranBO(TaskNode entity) throws SerException {
        TaskNodeBO bo = BeanTransform.copyProperties ( entity, TaskNodeBO.class, "needTime", "executeTime", "actualTime", "undoneTime", "delayTime", "table" );
        Table table = tableSer.findById ( entity.getTableId () );
        Project project = projectSer.findById ( table.getProjectId () );
        if (project != null && !project.equals ( "" )) {
            bo.setTable ( table.getName () );
            bo.setArea ( project.getArea () );
            bo.setDepart ( project.getDepart () );
            bo.setProject ( project.getProject () );
            bo.setInnerProject ( project.getInnerProject () );
        }
        CustomTitleDTO customTitleDTO = new CustomTitleDTO ();
        customTitleDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", entity.getId () ) );
        List<CustomTitle> customTitles = customTitleSer.findByCis ( customTitleDTO );
        if (null != customTitles) {
            List<CustomTitleBO> customTitleBOS = BeanTransform.copyProperties ( customTitles, CustomTitleBO.class );
            bo.setCustomTitles ( customTitleBOS );
        }
        QuestionDTO dto = new QuestionDTO ();
        dto.getConditions ().add ( Restrict.eq ( "taskNodeId", entity.getId () ) );
        List<Question> questions = questionSer.findByCis ( dto );
        List<QuestionBO> questionBOS = BeanTransform.copyProperties ( questions, QuestionBO.class );
        bo.setQuestions ( questionBOS );
        Double needTime = entity.getNeedTime ();
        bo.setNeedTime1 ( needTime );
        TimeType needType = entity.getNeedType ();
        if (null != needType && null != needTime) {
            String need = toString ( needTime, needType );
            bo.setNeedTime ( need );
        }
        Double executeTime = entity.getExecuteTime ();
        bo.setExecuteTime1 ( executeTime );
        TimeType executeType = entity.getExecuteType ();
        if (null != executeType && null != executeTime) {
            String execute = toString ( executeTime, executeType );
            bo.setExecuteTime ( execute );
        }
        Double actualTime = entity.getActualTime ();
        bo.setActualTime1 ( actualTime );
        TimeType actualType = entity.getActualType ();
        if (null != actualType && null != actualTime) {
            String actual = toString ( actualTime, actualType );
            bo.setActualTime ( actual );
        }
        Double undoneTime = entity.getUndoneTime ();
        bo.setUndoneTime1 ( undoneTime );
        TimeType undoneType = entity.getUndoneType ();
        if (null != undoneTime && null != undoneType) {
            String undone = toString ( undoneTime, undoneType );
            bo.setUndoneTime ( undone );
        }
        Double delayTime = entity.getDelayTime ();
        bo.setDelayTime1 ( delayTime );
        TimeType delayType = entity.getDelayType ();
        if (null != delayTime && null != delayType) {
            String delay = toString ( delayTime, delayType );
            bo.setDelayTime ( delay );
        }
        bo.setTableId ( entity.getTableId () );
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
        TaskNode entity = super.findById ( id );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        QuestionDTO questionDTO = new QuestionDTO ();
        questionDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", id ) );
        List<Question> questions = questionSer.findByCis ( questionDTO );
        if (!questions.isEmpty ()) {
            questionSer.remove ( questions );
        }
//        TaskNodeDTO dto = new TaskNodeDTO ();
//        dto.getConditions ().add ( Restrict.eq ( "fatherId", id ) );
//        List<TaskNode> sons = super.findByCis ( dto );
//        for (TaskNode son : sons) {
//            QuestionDTO questionDTO1 = new QuestionDTO ();
//            questionDTO1.getConditions ().add ( Restrict.eq ( "taskNodeId", son.getId () ) );
//            List<Question> questions1 = questionSer.findByCis ( questionDTO1 );
//            questionSer.remove ( questions1 );
//        }
        CustomTitleDTO titleDTO = new CustomTitleDTO ();
        titleDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", id ) );
        List<CustomTitle> titles = customTitleSer.findByCis ( titleDTO );
        if (!titles.isEmpty ()) {
            customTitleSer.remove ( titles );
        }
        super.remove ( id );
    }

    @Override
    public TaskNodeBO findByID(String id) throws SerException {
        TaskNode entity = super.findById ( id );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        return tranBO ( entity );
    }


    @Override
    public Boolean checkTime(TaskNodeTO to) throws SerException {
        String execute = to.getExecute ();
        if (null == execute) {
            return false;
        }
        String time1 = to.getStartTime ().substring ( 0, to.getStartTime ().indexOf ( " " ) );
        LocalDate date = DateUtil.parseDate ( time1 );
        TaskNodeDTO dto = new TaskNodeDTO ();
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getConditions ().add ( Restrict.between ( "startTime", new LocalDate[]{date, date} ) );
        dto.getConditions ().add ( Restrict.between ( "endTime", new LocalDate[]{date, date} ) );
        dto.getConditions ().add ( Restrict.eq ( "execute", execute ) );
        List<TaskNode> list = super.findByCis ( dto );
        double time = 0;
        for (TaskNode taskNode : list) {
            double needTime = taskNode.getNeedTime ();
            TimeType needType = taskNode.getNeedType ();
            switch (needType) {
                case DAY:
                    needTime = needTime * 8;
                    break;
                case HOUR:
                    break;
                case MINUTE:
                    needTime = new BigDecimal ( needTime / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
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
        String token = RpcTransmit.getUserToken ();
        String name = userAPI.currentUser ().getUsername ();
        TaskNode entity1 = super.findById ( to.getId () );
        if (null == entity1) {
            throw new SerException ( "该对象不存在" );
        }
        if (null != entity1.getInitiate ()) {
            throw new SerException ( "该任务已被分发，不能再次发起" );
        }
        RpcTransmit.transmitUserToken ( token );
        TaskNode entity = update ( to );
        entity.setInitiate ( name );
        entity.setTime ( LocalDateTime.now () );
        super.update ( entity );
        if ((null != entity.getSplit ()) && (entity.getSplit ())) {
            split ( entity );
        }
        if (null != to.getExecute ()) {
            priority ( entity );  //处理优先级
        }
        send ( name, entity );
        if (null != entity.getExecute ()) {
            EventTO eventTO = new EventTO ();
            eventTO.setName ( entity.getExecute () );
            eventTO.setProjectChineseName ( "任务分配" );
            eventTO.setProjectEnglishName ( "taskallotment" );
            eventTO.setFunctionChineseName ( "任务分配" );
            eventTO.setFunctionEnglishName ( "toaskallotment" );
            eventTO.setContent ( "待确认接收" );
            eventTO.setPermissions ( Permissions.CONFIRM );
            eventTO.setEventId ( entity.getId () );
            eventTO.setStatus ( "待确认" );
            eventAPI.save ( eventTO );
        }
    }

    public void split(TaskNode entity) throws SerException {
        double day = entity.getDay ();
        entity.setHaveSon ( true );
        entity.setCreateTime ( LocalDateTime.now () );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        CustomTitleDTO customTitleDTO = new CustomTitleDTO ();
        customTitleDTO.getConditions ().add ( Restrict.eq ( "taskNodeId", entity.getId () ) );
        List<CustomTitle> customTitles = customTitleSer.findByCis ( customTitleDTO );
        String s = DateUtil.dateToString ( entity.getStartTime () );
        String start = StringUtils.substringBefore ( s, " " );
        for (int i = 0; i < day; i++) {
            LocalDateTime st = DateUtil.parseDateTime ( start + " 08:30:00" );
            LocalDateTime et = DateUtil.parseDateTime ( start + " 18:00:00" );
            st = st.plusDays ( i );
            et = et.plusDays ( i );
            TaskNode son = new TaskNode ();
            BeanUtils.copyProperties ( entity, son, "split", "day", "id", "haveSon" );
            son.setStartTime ( st );
            son.setEndTime ( et );
            son.setFatherId ( entity.getId () );
            son.setNeedTime ( new BigDecimal ( entity.getNeedTime () / day ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () );
            super.save ( son );
            for (CustomTitle customTitle : customTitles) {
                CustomTitle title = BeanTransform.copyProperties ( customTitle, CustomTitle.class, true, "id", "taskNodeId" );
                title.setTaskNodeId ( son.getId () );
                customTitleSer.save ( customTitle );
            }
        }
    }

    private List<TaskNode> find(String startTime, String endTime, Set<String> tableIds) throws SerException {
        String[] tables = new String[tableIds.size ()];
        int i = 0;
        for (String s : tableIds) {
            tables[i] = "'" + s + "'";
            i++;
        }
        String tableStr = StringUtils.join ( tables, "," );
        StringBuilder sb = new StringBuilder ();
        sb.append ( "SELECT id FROM taskallotment_tasknode " +
                "WHERE haveSon is NULL AND " +
                "(DATE_FORMAT(startTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') AND ( DATE_FORMAT(endTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') " );
        if (!tableIds.isEmpty ()) {
            sb.append ( "AND table_id IN (" + tableStr + ")" );
        }
        List<TaskNode> list = super.findBySql ( sb.toString (), TaskNode.class, new String[]{"id"} );
        List<TaskNode> taskNodes = new ArrayList<> ();
        if (null != list) {
            for (TaskNode taskNode : list) {
                taskNodes.add ( super.findById ( taskNode.getId () ) );
            }
        }
        return taskNodes;
    }

    private List<TaskNode> phoneFind(String startTime, String endTime, String name) throws SerException {
        StringBuilder sb = new StringBuilder ();
        sb.append ( "SELECT id FROM taskallotment_tasknode " +
                "WHERE haveSon is NULL AND " +
                "(DATE_FORMAT(startTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') AND ( DATE_FORMAT(endTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') and execute='" + name + "'" );
        List<TaskNode> list = super.findBySql ( sb.toString (), TaskNode.class, new String[]{"id"} );
        List<TaskNode> taskNodes = new ArrayList<> ();
        if (null != list) {
            for (TaskNode taskNode : list) {
                taskNodes.add ( super.findById ( taskNode.getId () ) );
            }
        }
        return taskNodes;
    }

    @Override
    public CaseLastBO phoneCount(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        DataType dataType = dto.getDatatype ();
        String startTime = null;
        String endTime = null;
        switch (dataType) {
            case TODAY:
                startTime = DateUtil.dateToString ( LocalDate.now () );
                endTime = DateUtil.dateToString ( LocalDate.now () );
                break;
            case YESTERDAY:
                startTime = DateUtil.dateToString ( LocalDate.now ().minusDays ( 1 ) );
                endTime = DateUtil.dateToString ( LocalDate.now ().minusDays ( 1 ) );
                break;
            case THISWEEK:
                startTime = DateUtil.dateToString ( DateUtil.getStartWeek () );
                endTime = DateUtil.dateToString ( DateUtil.getEndWeek () );
                break;
            case THISMONTH:
                startTime = DateUtil.dateToString ( DateUtil.getStartMonth () );
                endTime = DateUtil.dateToString ( DateUtil.getEndMonth () );
                break;
            case THISQUARTER:
                startTime = DateUtil.dateToString ( DateUtil.getStartQuart () );
                endTime = DateUtil.dateToString ( DateUtil.getEndQuart () );
                break;
            case THISYEAR:
                startTime = DateUtil.dateToString ( DateUtil.getStartYear () );
                endTime = DateUtil.dateToString ( DateUtil.getEndYear () );
                break;
        }

        List<TaskNode> taskNodes = phoneFind ( startTime, endTime, name );
        return finishcounts ( taskNodes );
    }

//    private List<DataType> dataTypes() throws SerException {
//        List<DataType> list = new ArrayList<> ();
//        list.add ( DataType.TODAY );
//        list.add ( DataType.YESTERDAY );
//        list.add ( DataType.THISWEEK );
//        list.add ( DataType.THISMONTH );
//        list.add ( DataType.THISquarter );
//        list.add ( DataType.THISYEAR );
//        return list;
//    }

    @Transactional(rollbackFor = {SerException.class})
    public void priority(TaskNode taskNode) throws SerException {
        String execute = taskNode.getExecute ();
        String time = DateUtil.dateToString ( taskNode.getStartTime () );
        int index = time.indexOf ( " " );
        time = time.substring ( 0, index );
        LocalDate date = DateUtil.parseDate ( time );
        TaskNodeDTO dto = new TaskNodeDTO ();
        dto.getConditions ().add ( Restrict.between ( "startTime", new LocalDate[]{date, date} ) );
        dto.getConditions ().add ( Restrict.between ( "endTime", new LocalDate[]{date, date} ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getConditions ().add ( Restrict.eq ( "execute", execute ) );
        List<TaskNode> list = super.findByCis ( dto );
        TreeSet<TaskNode> set = new TreeSet<> ( new Comparator<TaskNode> () {
            @Override
            public int compare(TaskNode o1, TaskNode o2) {
                LocalDateTime eT = o1.getEndTime ();
                LocalDateTime eT1 = o2.getEndTime ();
                Long mis = eT.atZone ( ZoneId.systemDefault () ).toInstant ().toEpochMilli ()
                        - eT1.atZone ( ZoneId.systemDefault () ).toInstant ().toEpochMilli ();
                if (mis < 0) {    //按任务结束时间排序
                    return 1;
                } else if (mis == 0) {
                    TaskType t = o1.getTaskType ();
                    TaskType t1 = o2.getTaskType ();
                    switch (t) {      //按任务类型排序
                        case ENGINEERING:
                            if (TaskType.ENGINEERING.equals ( t1 )) {
                                return 1;
                            } else {
                                return 1;
                            }
                        case ADMININSTRATION:
                            if (TaskType.ENGINEERING.equals ( t1 )) {
                                return -1;
                            } else if (TaskType.TRAINING.equals ( t1 )) {
                                return 1;
                            } else {
                                return 1;
                            }
                        case TRAINING:
                            if (TaskType.TRAINING.equals ( t1 )) {
                                return 1;
                            } else {
                                return -1;
                            }
                    }
                }
                return -1;
            }
        } );
        for (TaskNode t : list) {
            set.add ( t );       //使用treeset集合进行排序
        }
        int a = list.size ();
        for (TaskNode t : set) {
            t.setPriority ( a );     //排序后得出优先级
            a--;
            t.setModifyTime ( LocalDateTime.now () );
            super.update ( t );
        }
    }

    private void send(String name, TaskNode entity) throws SerException {
        String charge = entity.getCharge ();
        String execute = entity.getExecute ();
        MessageTO messageTO = new MessageTO ();
        messageTO.setTitle ( "有任务分发给您" );
        String content = null;
        String email = null;
        if (null != charge && null != execute) {
            content = name + "给您分发了您的执行任务，请上系统查看";
            email = internalContactsAPI.getEmail ( execute );
        } else if (null != charge) {
            content = name + "给您分发了您负责的任务，请上系统查看";
            email = internalContactsAPI.getEmail ( charge );
        } else if (null != execute) {
            content = name + "给您分发了您的执行任务，请上系统查看";
            email = internalContactsAPI.getEmail ( execute );
        }
        if (null != email) {
            messageTO.setContent ( content );
            messageTO.setReceivers ( new String[]{email} );
            messageAPI.send ( messageTO );
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void addTask(TaskNodeTO to) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        String projectId = to.getProjectId ();
        String table = to.getTable ();
        TableDTO tableDTO = new TableDTO ();
        tableDTO.getConditions ().add ( Restrict.eq ( "name", table ) );
        List<Table> tables = tableSer.findByCis ( tableDTO );
        String tableId = null;
        if (tables.isEmpty ()) {
            Table e = new Table ();
            e.setProjectId ( projectId );
            e.setName ( table );
            e.setCreater ( name );
            e.setStatus ( Status.START );
            tableSer.save ( e );
            tableId = e.getId ();
        } else {
            tableId = tables.get ( 0 ).getId ();
        }
        TaskNode entity = BeanTransform.copyProperties ( to, TaskNode.class, true );
        entity.setTableId ( tableId );
        entity.setInitiate ( name );
        List<CustomTitleTO> titleTOS = to.getCustomTitles ();
        List<CustomTitle> titles = new ArrayList<> ();
        entity.setTime ( LocalDateTime.now () );
        entity.setType ( type ( entity.getTaskType () ) );
        entity.setCreateTime ( LocalDateTime.now () );
        entity.setModifyTime ( LocalDateTime.now () );
        super.save ( entity );
        if ((null != entity.getSplit ()) && (entity.getSplit ())) {
            split ( entity );
        }
        super.update ( entity );
        if (null != titleTOS) {
            int i = 0;
            for (CustomTitleTO titleTO : titleTOS) {
                i++;
                titleTO.setId ( null );
                CustomTitle customTitle = BeanTransform.copyProperties ( titleTO, CustomTitle.class, true );
                customTitle.setTitleIndex ( i );
                customTitle.setTaskNodeId ( entity.getId () );
                titles.add ( customTitle );
            }
        }
        if (null != entity.getExecute ()) {
            priority ( entity );  //处理优先级
        }
        send ( name, entity );
        if (null != entity.getExecute ()) {
            EventTO eventTO = new EventTO ();
            eventTO.setName ( entity.getExecute () );
            eventTO.setProjectChineseName ( "任务分配" );
            eventTO.setProjectEnglishName ( "taskallotment" );
            eventTO.setFunctionChineseName ( "任务分配" );
            eventTO.setFunctionEnglishName ( "toaskallotment" );
            eventTO.setContent ( "待确认接收" );
            eventTO.setPermissions ( Permissions.CONFIRM );
            eventTO.setEventId ( entity.getId () );
            eventTO.setStatus ( "待确认" );
            eventAPI.save ( eventTO );
        }
    }

    @Override
    public List<TaskNodeBO> myInitiate(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        dto.getConditions ().add ( Restrict.eq ( "initiate", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getSorts ().add ( "time=desc" );
        List<TaskNode> list = super.findByCis ( dto, true );
        List<TaskNodeBO> bos = new ArrayList<> ();
        for (TaskNode taskNode : list) {
            bos.add ( tranBO ( taskNode ) );
        }
        return bos;
    }

    @Override
    public Long myInitiateNum(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        dto.getConditions ().add ( Restrict.eq ( "initiate", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        return super.count ( dto );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void reback(String id) throws SerException {
        delete ( id );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void finish(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setContent ( to.getContent () );
        entity.setActualNum ( to.getActualNum () );
        entity.setTaskType ( to.getTaskType () );
        entity.setTaskName ( to.getTaskName () );
        entity.setExecute ( to.getExecute () );
        entity.setPlanNum ( to.getPlanNum () );
        entity.setNeedTime ( to.getNeedTime () );
        entity.setNeedType ( to.getNeedType () );
        entity.setActualTime ( to.getActualTime () );
        entity.setActualType ( to.getActualType () );
        entity.setUndoneTime ( to.getUndoneTime () );
        entity.setUndoneType ( to.getUndoneType () );
        entity.setStartTime ( DateUtil.parseDateTime ( to.getStartTime () ) );
        entity.setEndTime ( DateUtil.parseDateTime ( to.getEndTime () ) );
        entity.setNotice ( to.getNotice () );
        entity.setReimbursement ( to.getReimbursement () );
        entity.setTaskStatus ( TaskStatus.FINISH );
        entity.setFinishStatus ( FinishStatus.FINISH );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        if ((null != entity.getHaveSon ()) && entity.getHaveSon ()) {
            updateFather ( entity.getFatherId () );
        }
        updateTable ( entity );
        String eventId = eventAPI.findId ( entity.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
        if (null != entity.getExecute ()) {
            String eventId1 = eventAPI.findId ( entity.getId (), entity.getExecute () );
            if (null != eventId1) {
                eventAPI.delete ( eventId1 );
            }
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    public void updateFather(String fatherId) throws SerException {
        TaskNodeDTO dto = new TaskNodeDTO ();
        dto.getConditions ().add ( Restrict.eq ( "fatherId", fatherId ) );
        List<TaskNode> sons = super.findByCis ( dto );
        long finishSize = sons.stream ().filter ( taskNode -> TaskStatus.FINISH.equals ( taskNode.getTaskStatus () ) ).count ();
        long unFinishSize = sons.stream ().filter ( taskNode -> TaskStatus.UNFINISHED.equals ( taskNode.getTaskStatus () ) ).count ();
        TaskNode father = super.findById ( fatherId );
        if (sons.size () == finishSize) {
            father.setTaskStatus ( TaskStatus.FINISH );
            father.setModifyTime ( LocalDateTime.now () );
            super.update ( father );
        } else if (sons.size () == unFinishSize) {
            father.setTaskStatus ( TaskStatus.UNFINISHED );
            father.setModifyTime ( LocalDateTime.now () );
            super.update ( father );
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void unFinish(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setContent ( to.getContent () );
        entity.setActualNum ( to.getActualNum () );
        entity.setTaskType ( to.getTaskType () );
        entity.setTaskName ( to.getTaskName () );
        entity.setExecute ( to.getExecute () );
        entity.setPlanNum ( to.getPlanNum () );
        entity.setNeedTime ( to.getNeedTime () );
        entity.setNeedType ( to.getNeedType () );
        entity.setActualTime ( to.getActualTime () );
        entity.setActualType ( to.getActualType () );
        entity.setUndoneTime ( to.getUndoneTime () );
        entity.setUndoneType ( to.getUndoneType () );
        entity.setStartTime ( DateUtil.parseDateTime ( to.getStartTime () ) );
        entity.setEndTime ( DateUtil.parseDateTime ( to.getEndTime () ) );
        entity.setNotice ( to.getNotice () );
        entity.setReimbursement ( to.getReimbursement () );
        entity.setTaskStatus ( TaskStatus.UNFINISHED );
        entity.setFinishStatus ( FinishStatus.UNFINISHED );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        if ((null != entity.getHaveSon ()) && entity.getHaveSon ()) {
            updateFather ( entity.getFatherId () );
        }
        updateTable ( entity );
        String eventId = eventAPI.findId ( entity.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
        if (null != entity.getExecute ()) {
            String eventId1 = eventAPI.findId ( entity.getId (), entity.getExecute () );
            if (null != eventId1) {
                eventAPI.delete ( eventId1 );
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void pass(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setAduitType ( AduitType.PASS );
        entity.setResult ( to.getResult () );
        entity.setTaskStatus ( TaskStatus.DOING );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        String eventId = eventAPI.findId ( to.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void notPass(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setAduitType ( AduitType.NOTPASS );
        entity.setNotPassReason ( to.getNotPassReason () );
        entity.setTaskStatus ( TaskStatus.DOING );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        String eventId = eventAPI.findId ( to.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
    }

    @Override
    public List<TaskNodeBO> myCharge(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart ();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO ();
            projectDTO.getConditions ().add ( Restrict.in ( "depart", depart ) );
            List<Project> projects = projectSer.findByCis ( projectDTO );
            Set<String> projectIds = projects.stream ().map ( project -> project.getId () ).collect ( Collectors.toSet () );
            TableDTO tableDTO = new TableDTO ();
            tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
            List<Table> tables = tableSer.findByCis ( tableDTO );
            Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
            dto.getConditions ().add ( Restrict.in ( "tableId", tableIds ) );
        }
        String name = userAPI.currentUser ().getUsername ();
        dto.getConditions ().add ( Restrict.isNotNull ( "initiate" ) );
        dto.getConditions ().add ( Restrict.eq ( "charge", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getSorts ().add ( "time=desc" );
        List<TaskNode> list = super.findByCis ( dto, true );
        List<TaskNodeBO> bos = new ArrayList<> ();
        for (TaskNode taskNode : list) {
            bos.add ( tranBO ( taskNode ) );
        }
        return bos;
    }

    @Override
    public Long myChargeNum(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart ();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO ();
            projectDTO.getConditions ().add ( Restrict.in ( "depart", depart ) );
            List<Project> projects = projectSer.findByCis ( projectDTO );
            Set<String> projectIds = projects.stream ().map ( project -> project.getId () ).collect ( Collectors.toSet () );
            TableDTO tableDTO = new TableDTO ();
            tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
            List<Table> tables = tableSer.findByCis ( tableDTO );
            Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
            dto.getConditions ().add ( Restrict.in ( "tableId", tableIds ) );
        }
        String name = userAPI.currentUser ().getUsername ();
        dto.getConditions ().add ( Restrict.isNotNull ( "initiate" ) );
        dto.getConditions ().add ( Restrict.eq ( "charge", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        return super.count ( dto );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void allotment(TaskNodeTO to) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setInitiate ( name );
        entity.setExecute ( to.getExecute () );
        entity.setPlanNum ( to.getPlanNum () );
        entity.setNeedTime ( to.getNeedTime () );
        entity.setNeedType ( to.getNeedType () );
        entity.setStartTime ( DateUtil.parseDateTime ( to.getStartTime () ) );
        entity.setEndTime ( DateUtil.parseDateTime ( to.getEndTime () ) );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        priority ( entity );    //处理优先级
    }

    @Override
    public List<TaskNodeBO> myExecute(TaskNodeDTO dto) throws SerException {
        String[] depart = dto.getDepart ();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO ();
            projectDTO.getConditions ().add ( Restrict.in ( "depart", depart ) );
            List<Project> projects = projectSer.findByCis ( projectDTO );
            Set<String> projectIds = projects.stream ().map ( project -> project.getId () ).collect ( Collectors.toSet () );
            TableDTO tableDTO = new TableDTO ();
            tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
            List<Table> tables = tableSer.findByCis ( tableDTO );
            Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
            dto.getConditions ().add ( Restrict.in ( "tableId", tableIds ) );
        }
        String name = userAPI.currentUser ().getUsername ();
        dto.getConditions ().add ( Restrict.isNotNull ( "initiate" ) );
        dto.getConditions ().add ( Restrict.eq ( "execute", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getSorts ().add ( "time=desc" );
        List<TaskNode> list = super.findByCis ( dto, true );
        List<TaskNodeBO> bos = new ArrayList<> ();
        for (TaskNode taskNode : list) {
            bos.add ( tranBO ( taskNode ) );
        }
        return bos;
    }

    @Override
    public Long myExecuteNum(TaskNodeDTO dto) throws SerException {
        String name = userAPI.currentUser ().getUsername ();
        String[] depart = dto.getDepart ();
        if (null != depart) {
            ProjectDTO projectDTO = new ProjectDTO ();
            projectDTO.getConditions ().add ( Restrict.in ( "depart", depart ) );
            List<Project> projects = projectSer.findByCis ( projectDTO );
            Set<String> projectIds = projects.stream ().map ( project -> project.getId () ).collect ( Collectors.toSet () );
            TableDTO tableDTO = new TableDTO ();
            tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
            List<Table> tables = tableSer.findByCis ( tableDTO );
            Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
            dto.getConditions ().add ( Restrict.in ( "tableId", tableIds ) );
        }
        dto.getConditions ().add ( Restrict.isNotNull ( "initiate" ) );
        dto.getConditions ().add ( Restrict.eq ( "execute", name ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        return super.count ( dto );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void report(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        LocalDateTime now = LocalDateTime.now ();
        LocalDateTime endTime = entity.getEndTime ();
        Long mis = endTime.atZone ( ZoneId.systemDefault () ).toInstant ().toEpochMilli ()
                - now.atZone ( ZoneId.systemDefault () ).toInstant ().toEpochMilli ();
        if (mis < (3600000 * 3)) {   //需提前三小时上报
            throw new SerException ( "需在任务结束时间提前3小时上报" );
        }
        entity.setDelay ( to.getDelay () );
        entity.setDelayTime ( to.getDelayTime () );
        entity.setDelayType ( to.getDelayType () );
        entity.setReportReason ( to.getReportReason () );
        entity.setReport ( true );
        entity.setTaskStatus ( TaskStatus.TOBEAUDITED );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        EventTO eventTO = new EventTO ();
        eventTO.setName ( userAPI.currentUser ().getUsername () );
        eventTO.setProjectChineseName ( "任务分配" );
        eventTO.setProjectEnglishName ( "taskallotment" );
        eventTO.setFunctionChineseName ( "任务分配" );
        eventTO.setFunctionEnglishName ( "taskallotment" );
        eventTO.setContent ( "上报审核" );
        eventTO.setPermissions ( Permissions.ADUIT );
        eventTO.setEventId ( entity.getId () );
        eventTO.setStatus ( "待审核" );
        eventAPI.save ( eventTO );
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void initiateAgain(TaskNodeTO to) throws SerException {
        String token = RpcTransmit.getUserToken ();
        checkAddIdentity ();
        RpcTransmit.transmitUserToken ( token );
        String name = userAPI.currentUser ().getUsername ();
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setInitiate ( name );
        entity.setExecute ( to.getExecute () );
        entity.setPlanNum ( to.getPlanNum () );
        entity.setNeedTime ( to.getNeedTime () );
        entity.setNeedType ( to.getNeedType () );
        entity.setStartTime ( DateUtil.parseDateTime ( to.getStartTime () ) );
        entity.setEndTime ( DateUtil.parseDateTime ( to.getEndTime () ) );
        entity.setRemark ( to.getRemark () );
        entity.setSplit ( to.getSplit () );
        entity.setTaskStatus ( TaskStatus.RECEIVE );
        if (to.getSplit ()) {
            entity.setDay ( to.getDay () );
            split ( entity );
        }
        entity.setTime ( LocalDateTime.now () );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        priority ( entity );    //处理优先级
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void write(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setActualNum ( to.getActualNum () );
        entity.setFinishStatus ( to.getFinishStatus () );
        entity.setExecuteTime ( to.getExecuteTime () );
        entity.setExecuteType ( to.getExecuteType () );
        entity.setStartExecute ( DateUtil.parseDateTime ( to.getStartExecute () ) );
        entity.setEndExecute ( DateUtil.parseDateTime ( to.getEndExecute () ) );
        entity.setReimbursement ( to.getReimbursement () );
        entity.setQuestion ( to.getQuestion () );
        entity.setSummary ( to.getSummary () );
        entity.setActualTime ( to.getActualTime () );
        entity.setActualType ( to.getActualType () );
        entity.setUndoneTime ( to.getUndoneTime () );
        entity.setUndoneType ( to.getUndoneType () );
        entity.setFinishTime ( DateUtil.parseDateTime ( to.getFinishTime () ) );
        if (entity.getQuestion ()) {
            if (null != to.getQuestions ()) {
                for (QuestionTO questionTO : to.getQuestions ()) {
                    Question question = BeanTransform.copyProperties ( questionTO, Question.class, true );
                    question.setTaskNodeId ( entity.getId () );
                    questionSer.save ( question );
                }
            }
        }
        TimeType timeType = entity.getExecuteType ();
        double executeTime = entity.getExecuteTime ();
        switch (timeType) {
            case MINUTE:
                executeTime = executeTime / 60;
                break;
            case DAY:
                executeTime = executeTime * 8;
                break;
        }
        double efficiency = entity.getPlanNum () * 8 / entity.getActualNum () / executeTime;
        efficiency = new BigDecimal ( efficiency ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
        entity.setEfficiency ( efficiency );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        EventTO eventTO = new EventTO ();
        eventTO.setName ( entity.getInitiate () );
        eventTO.setProjectChineseName ( "任务分配" );
        eventTO.setProjectEnglishName ( "taskallotment" );
        eventTO.setFunctionChineseName ( "任务分配" );
        eventTO.setFunctionEnglishName ( "toaskallotment" );
        eventTO.setContent ( "待确认完成" );
        eventTO.setPermissions ( Permissions.CONFIRM );
        eventTO.setEventId ( entity.getId () );
        eventTO.setStatus ( "待确认" );
        eventAPI.save ( eventTO );
    }

    @Transactional(rollbackFor = {SerException.class})
    public void writes(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setStartExecute ( DateUtil.parseDateTime ( to.getStartExecute () ) );
        entity.setEndExecute ( DateUtil.parseDateTime ( to.getEndExecute () ) );
//        long mis = DateUtil.mis ( DateUtil.parseDateTime ( to.getEndExecute () ), DateUtil.parseDateTime ( to.getStartExecute () ) );
//        if (mis < 0) {
//            throw new SerException ( "实际结束时间必须大于实际开始时间" );
//        }
//        double hour = mis / (1000 * 60 * 60);
//        entity.setActualTime ( hour );
        entity.setActualTime ( to.getActualTime () );
        entity.setActualType ( TimeType.HOUR );
        entity.setReimbursement ( to.getReimbursement () );
        entity.setQuestion ( to.getQuestion () );
        entity.setSummary ( to.getSummary () );
        if (entity.getQuestion ()) {
            if (null != to.getQuestions ()) {
                for (QuestionTO questionTO : to.getQuestions ()) {
                    Question question = BeanTransform.copyProperties ( questionTO, Question.class, true );
                    question.setTaskNodeId ( entity.getId () );
                    questionSer.save ( question );
                }
            }
        }
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
    }

    @Override
    public List<PersonCountBO> personCount(TaskNodeDTO dto) throws SerException {
//        LocalDate start = DateUtil.parseDate(dto.getStartTime());
//        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea ();
        String[] departs = dto.getDepart ();
        String[] names = dto.getName ();
//        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
//        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
//        dto.getConditions().add(Restrict.isNull("haveSon"));
        return pCount ( dto, areas, departs, names );
    }

    @Override
    public OptionBO personCountFigure(TaskNodeDTO dto) throws SerException {

        String[] areas = dto.getArea ();
        String[] departs = dto.getDepart ();
        String[] usernames = dto.getName ();
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", areas ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", departs ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        List<String> strings = new ArrayList<> ();
        List<Double> datas = new ArrayList<> ();
        double sum = 0;   //合计
        for (String area : areas) {
            for (String depart : departs) {
                Set<String> projectIds = projects.stream ().filter ( project -> area.equals ( project.getArea () ) && depart.equals ( project.getDepart () ) ).map ( project -> project.getId () ).collect ( Collectors.toSet () );
                TableDTO tableDTO = new TableDTO ();
                if (!projectIds.isEmpty ()) {
                    tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
                    List<Table> tables = tableSer.findByCis ( tableDTO );
                    Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
                    List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
                    for (String name : usernames) {
                        if (taskNodes.stream ().map ( TaskNode::getExecute ).distinct ().collect ( Collectors.toList () ).contains ( name )) {
                            //根据地区部门获取标准工时，如若获取不到就默认8小时
                            TimeSetDTO timeSetDTO = new TimeSetDTO ();
                            timeSetDTO.getConditions ().add ( Restrict.eq ( "area", area ) );
                            timeSetDTO.getConditions ().add ( Restrict.eq ( "depart", depart ) );
                            List<TimeSet> timeSetList = timeSetSer.findByCis ( timeSetDTO );
                            Double standardTime = 8d;//默认8小时
                            if (timeSetList != null && timeSetList.size () > 0) {
                                standardTime = timeSetList.get ( 0 ).getHour ();
                            }
                            strings.add ( name );
                            PersonLastBO personLastBO = getPerson ( taskNodes, name, standardTime );
                            datas.add ( personLastBO.getDiffer () );
                            sum += personLastBO.getDiffer ();
                        }
                    }
                }
            }
        }
        datas.add ( sum );
        strings.add ( "合计" );
        String[] name = new String[strings.size ()];
        name = strings.toArray ( name );
        TitleBO titleBO = new TitleBO ();
        titleBO.setText ( "个人汇总" );
        LegendBO legendBO = new LegendBO ();
        legendBO.setData ( new String[]{"工时差异（小时）"} );
        XAxisBO xAxisBO = new XAxisBO ();
        xAxisBO.setData ( name );
        Double[] data = new Double[datas.size ()];
        data = datas.toArray ( data );
        SeriesBO seriesBO = new SeriesBO ();
        seriesBO.setType ( "bar" );
        seriesBO.setName ( "工时差异（小时）" );
        seriesBO.setData ( data );
        List<SeriesBO> series = new ArrayList<> ();
        series.add ( seriesBO );
        OptionBO optionBO = new OptionBO ();
        optionBO.setTitle ( titleBO );
        optionBO.setLegend ( legendBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setSeries ( series );
        optionBO.setyAxis ( new YAxisBO () );
        optionBO.setTooltip ( new TooltipBO () );
        return optionBO;
    }

    private List<PersonCountBO> pCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] names) throws
            SerException {

        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", areas ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", departs ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        List<PersonCountBO> personCountBOS = new ArrayList<> ();
        double differSum = 0;   //总合计
        for (String area : areas) {
            double differArea = 0;   //地区合计
            PersonCountBO personCountBO = new PersonCountBO ();
            List<PersonSonBO> personSonBOS = new ArrayList<> ();
            for (String depart : departs) {
                double differDepart = 0;   //部门合计
                PersonSonBO personSonBO = new PersonSonBO ();
                List<PersonLastBO> personLastBOS = new ArrayList<> ();
                Set<String> projectIds = projects.stream ().filter ( project -> area.equals ( project.getArea () ) && depart.equals ( project.getDepart () ) ).map ( project -> project.getId () ).collect ( Collectors.toSet () );
                TableDTO tableDTO = new TableDTO ();
                if (!projectIds.isEmpty ()) {
                    personCountBO.setArea ( area );
                    personSonBO.setDepart ( depart );
                    tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
                    List<Table> tables = tableSer.findByCis ( tableDTO );
                    Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
//                    dto.getConditions().add(Restrict.in("tableId", tableIds));
                    List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
                    for (String name : names) {
                        if (taskNodes.stream ().map ( TaskNode::getExecute ).distinct ().collect ( Collectors.toList () ).contains ( name )) {
                            //根据地区部门获取标准工时，如若获取不到就默认8小时
                            TimeSetDTO timeSetDTO = new TimeSetDTO ();
                            timeSetDTO.getConditions ().add ( Restrict.eq ( "area", area ) );
                            timeSetDTO.getConditions ().add ( Restrict.eq ( "depart", depart ) );
                            List<TimeSet> timeSetList = timeSetSer.findByCis ( timeSetDTO );
                            Double standardTime = 8d;//默认8小时
                            if (timeSetList != null && timeSetList.size () > 0) {
                                standardTime = timeSetList.get ( 0 ).getHour ();
                            }
                            PersonLastBO personLastBO = getPerson ( taskNodes, name, standardTime );
                            personLastBOS.add ( personLastBO );
                        }
                    }
                    differDepart += personLastBOS.stream ().mapToDouble ( PersonLastBO::getDiffer ).sum ();
                    differArea += personLastBOS.stream ().mapToDouble ( PersonLastBO::getDiffer ).sum ();
                    differSum += personLastBOS.stream ().mapToDouble ( PersonLastBO::getDiffer ).sum ();
                }
                if (!personLastBOS.isEmpty ()) {
                    personSonBO.setPersonLastS ( personLastBOS );
                    personSonBOS.add ( personSonBO );
                }
                if (differDepart != 0) {
                    PersonSonBO departCount = departCount ( differDepart );
                    personSonBOS.add ( departCount );
                }
            }
            if (!personSonBOS.isEmpty ()) {
                personCountBO.setPersonSonS ( personSonBOS );
                personCountBOS.add ( personCountBO );
            }
            if (differArea != 0) {
                PersonCountBO areaCount = sum ( differArea, "地区合计" );
                personCountBOS.add ( areaCount );
            }
        }
        PersonCountBO sum = sum ( differSum, "总合计" );
        personCountBOS.add ( sum );
        return personCountBOS;
    }

    //部门合计
    private PersonSonBO departCount(Double differDepart) throws SerException {
        PersonSonBO depart = new PersonSonBO ();
        depart.setDepart ( "合计" );
        List<PersonLastBO> lastBOS = new ArrayList<> ();
        PersonLastBO lastBO = new PersonLastBO ();
        lastBO.setDiffer ( differDepart );
        lastBOS.add ( lastBO );
        depart.setPersonLastS ( lastBOS );
        return depart;
    }

    //合计
    private PersonCountBO sum(Double differDepart, String type) throws SerException {
        PersonCountBO sum = new PersonCountBO ();
        if ("总合计".equals ( type )) {
            sum.setArea ( "总合计" );
        } else if ("地区合计".equals ( type )) {
            sum.setArea ( "合计" );
        }
        List<PersonSonBO> sonBOS = new ArrayList<> ();
        PersonSonBO depart = new PersonSonBO ();
        List<PersonLastBO> lastBOS = new ArrayList<> ();
        PersonLastBO lastBO = new PersonLastBO ();
        lastBO.setDiffer ( differDepart );
        lastBOS.add ( lastBO );
        depart.setPersonLastS ( lastBOS );
        sonBOS.add ( depart );
        sum.setPersonSonS ( sonBOS );
        return sum;
    }

    private PersonLastBO getPerson(List<TaskNode> taskNodes, String name, Double standardTime) throws
            SerException {
        List<TaskNode> list = taskNodes.stream ().filter ( taskNode -> FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && name.equals ( taskNode.getExecute () ) ).collect ( Collectors.toList () );
        double differ = 0;
        double actual = 0;
        double stand = 0;
        for (TaskNode t : list) {
            TimeType needType = t.getNeedType ();
            double needTime = t.getNeedTime ();
            switch (needType) {
                case MINUTE:
                    needTime = new BigDecimal ( needTime / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                    break;
                case HOUR:
                    break;
                case DAY:
                    needTime = needTime * standardTime;
                    break;
            }
            TimeType actualType = t.getActualType ();
            double actualTime = t.getActualTime ();
            switch (actualType) {
                case MINUTE:
                    actualTime = new BigDecimal ( actualTime / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                    break;
                case HOUR:
                    break;
                case DAY:
                    actualTime = actualTime * standardTime;
                    break;
            }
            stand = stand + needTime;
            actual = actual + actualTime;
            differ = differ + (actualTime - needTime);
        }
        PersonLastBO personLastBO = new PersonLastBO ();
        personLastBO.setName ( name );
        personLastBO.setStandardHour ( stand );
        personLastBO.setActualHour ( actual );

        personLastBO.setDiffer ( differ );
        return personLastBO;
    }

    @Override
    public List<TimeCountBO> timeCount(TaskNodeDTO dto) throws SerException {
//        LocalDate start = DateUtil.parseDate(dto.getStartTime());
//        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea ();
        String[] departs = dto.getDepart ();
        CountType countType = dto.getCountType ();
//        dto.getConditions().add(Restrict.isNull("haveSon"));
//        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
//        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return tCount ( dto, areas, departs );
        } else if (CountType.WHOLE.equals ( countType )) {
            List<String> as = areas ();
            String[] area = new String[as.size ()];
            if (!as.isEmpty ()) {
                area = as.toArray ( area );
            }
            List<String> ds = departs ();
            String[] depart = new String[ds.size ()];
            if (!ds.isEmpty ()) {
                depart = ds.toArray ( depart );
            }
            return tCount ( dto, area, depart );
        } else if (CountType.DEPART.equals ( countType )) {
            return tCount ( dto, areas, departs );
        }
        return null;
    }

    @Override
    public OptionBO timeCountFigure(TaskNodeDTO dto) throws SerException {
        String[] departs = null;
        String[] area = null;
        if (CountType.WHOLE.equals ( dto.getCountType () )) {
            List<String> as = areas ();
            area = new String[as.size ()];
            if (!as.isEmpty ()) {
                area = as.toArray ( area );
            }
            List<String> ds = departs ();
            departs = new String[ds.size ()];
            if (!ds.isEmpty ()) {
                departs = ds.toArray ( departs );
            }
//            List<String> ps1 = projects();
//            String[] ps11 = new String[ps1.size()];
//            if (!ps1.isEmpty()) {
//                ps11 = ds.toArray(ps11);
//            }
        } else {
            departs = dto.getDepart ();
            area = dto.getArea ();
        }
        List<String> list = new ArrayList<> ();
        double noDifferSum = 0;
        double haveDifferSum = 0;
        List<Double> noDiffers = new ArrayList<> ();
        List<Double> haveDiffers = new ArrayList<> ();
        if (null == area) {
            throw new SerException ( "不是整体汇总必须选择地区" );
        }
        if (null == departs) {
            throw new SerException ( "不是整体汇总必须选择部门" );
        }
        for (String depart : departs) {
            ProjectDTO projectDTO = new ProjectDTO ();
            projectDTO.getConditions ().add ( Restrict.in ( "area", area ) );
            List<String> projectIds = projectSer.findByCis ( projectDTO ).stream ().filter ( project -> depart.equals ( project.getDepart () ) ).map ( Project::getId ).distinct ().collect ( Collectors.toList () );
            TableDTO tableDTO = new TableDTO ();
            tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
            Set<String> tableIds = tableSer.findByCis ( tableDTO ).stream ().map ( Table::getId ).collect ( Collectors.toSet () );
            List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
            TimeSonBO timeSonBO = getTime ( taskNodes );
            double noDiffer = Double.parseDouble ( timeSonBO.getNoDiffer () + "" );
            double haveDiffer = Double.parseDouble ( timeSonBO.getHaveDiffer () + "" );
            noDifferSum += noDiffer;
            haveDifferSum += haveDiffer;
            noDiffers.add ( noDiffer );
            haveDiffers.add ( haveDiffer );
            list.add ( depart );
        }
        noDiffers.add ( noDifferSum );
        haveDiffers.add ( haveDifferSum );
        list.add ( "合计" );
        String[] strings = new String[list.size ()];
        strings = list.toArray ( strings );
        OptionBO optionBO = new OptionBO ();
        LegendBO legendBO = new LegendBO ();
        legendBO.setData ( new String[]{"无差异人数", "有差异人数"} );
        TitleBO titleBO = new TitleBO ();
        titleBO.setText ( "人员标准工时汇总" );
        XAxisBO xAxisBO = new XAxisBO ();
        xAxisBO.setData ( strings );
        List<SeriesBO> seriesBOS = new ArrayList<> ();
        SeriesBO seriesBO1 = new SeriesBO ();
        seriesBO1.setName ( "无差异人数" );
        seriesBO1.setType ( "bar" );
        seriesBO1.setData ( toArray ( noDiffers ) );
        seriesBOS.add ( seriesBO1 );
        SeriesBO seriesBO2 = new SeriesBO ();
        seriesBO2.setName ( "有差异人数" );
        seriesBO2.setType ( "bar" );
        seriesBO2.setData ( toArray ( haveDiffers ) );
        seriesBOS.add ( seriesBO2 );
        optionBO.setTitle ( titleBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setLegend ( legendBO );
        optionBO.setSeries ( seriesBOS );
        optionBO.setyAxis ( new YAxisBO () );
        optionBO.setTooltip ( new TooltipBO () );
        return optionBO;
    }

    private Double[] toArray(List<Double> list) throws SerException {
        Double[] doubles = new Double[list.size ()];
        return list.toArray ( doubles );
    }

    private List<TimeCountBO> tCount(TaskNodeDTO dto, String[] areas, String[] departs) throws SerException {
        if (null == areas) {
            throw new SerException ( "不是整体汇总必须选择地区" );
        }
        if (null == departs) {
            throw new SerException ( "不是整体汇总必须选择部门" );
        }
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", areas ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", departs ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        List<TimeCountBO> timeCountBOS = new ArrayList<> ();
        long noDifferSum = 0;
        long haveDifferSum = 0;  //总合计
        for (String area : areas) {
            long noDifferArea = 0;
            long haveDifferArea = 0;  //地区合计
            TimeCountBO timeCountBO = new TimeCountBO ();
            List<TimeSonBO> timeSonBOS = new ArrayList<> ();
            for (String depart : departs) {
                long noDifferDepart = 0;
                long haveDifferDepart = 0;  //部门合计
                Set<String> projectIds = projects.stream ().filter ( project -> area.equals ( project.getArea () ) && depart.equals ( project.getDepart () ) ).map ( project -> project.getId () ).collect ( Collectors.toSet () );
                TableDTO tableDTO = new TableDTO ();
                if (!projectIds.isEmpty ()) {
                    timeCountBO.setArea ( area );
                    tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
                    List<Table> tables = tableSer.findByCis ( tableDTO );
                    Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
//                    dto.getConditions().add(Restrict.in("tableId", tableIds));
                    List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
                    TimeSonBO timeSonBO = getTime ( taskNodes );
                    timeSonBO.setDepart ( depart );
                    timeSonBOS.add ( timeSonBO );
                    //部门合计
                    haveDifferDepart += timeSonBOS.stream ().mapToLong ( TimeSonBO::getHaveDiffer ).sum ();
                    noDifferDepart += timeSonBOS.stream ().mapToLong ( TimeSonBO::getNoDiffer ).sum ();
                    //地区合计
                    haveDifferArea += timeSonBOS.stream ().mapToLong ( TimeSonBO::getHaveDiffer ).sum ();
                    noDifferArea += timeSonBOS.stream ().mapToLong ( TimeSonBO::getNoDiffer ).sum ();
                    //总合计
                    haveDifferSum += timeSonBOS.stream ().mapToLong ( TimeSonBO::getHaveDiffer ).sum ();
                    noDifferSum += timeSonBOS.stream ().mapToLong ( TimeSonBO::getNoDiffer ).sum ();
                }
                if (!(noDifferDepart == 0 && haveDifferDepart == 0)) {
                    TimeSonBO departCount = departCount ( noDifferDepart, haveDifferDepart );
                    timeSonBOS.add ( departCount );
                }
            }
            if (!timeSonBOS.isEmpty ()) {
                timeCountBO.setTimeSonS ( timeSonBOS );
                timeCountBOS.add ( timeCountBO );
            }
            if (!(noDifferArea == 0 && haveDifferArea == 0)) {
                TimeCountBO areaCount = sum ( noDifferArea, haveDifferArea, "地区合计" );
                timeCountBOS.add ( areaCount );
            }
        }
        TimeCountBO sum = sum ( noDifferSum, haveDifferSum, "总合计" );
        timeCountBOS.add ( sum );
        return timeCountBOS;
    }

    //部门合计
    private TimeSonBO departCount(Long noDiffer, Long haveDiffer) throws SerException {
        TimeSonBO depart = new TimeSonBO ();
        depart.setDepart ( "合计" );
        depart.setNoDiffer ( noDiffer );
        depart.setHaveDiffer ( haveDiffer );
        return depart;
    }

    //合计
    private TimeCountBO sum(Long noDiffer, Long haveDiffer, String type) throws SerException {
        TimeCountBO sum = new TimeCountBO ();
        if ("总合计".equals ( type )) {
            sum.setArea ( "总合计" );
        } else if ("地区合计".equals ( type )) {
            sum.setArea ( "合计" );
        }
        List<TimeSonBO> timeSonBOS = new ArrayList<> ();
        TimeSonBO depart = new TimeSonBO ();
        depart.setNoDiffer ( noDiffer );
        depart.setHaveDiffer ( haveDiffer );
        timeSonBOS.add ( depart );
        sum.setTimeSonS ( timeSonBOS );
        return sum;
    }

    private TimeSonBO getTime(List<TaskNode> taskNodes) throws SerException {
        List<TaskNode> list = taskNodes.stream ().filter ( taskNode -> FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).collect ( Collectors.toList () );
        long noDiffer = 0;
        long haveDiffer = 0;
        for (TaskNode t : list) {
            TimeType needType = t.getNeedType ();
            double needTime = t.getNeedTime ();
            switch (needType) {
                case MINUTE:
                    needTime = new BigDecimal ( needTime / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                    break;
                case HOUR:
                    break;
                case DAY:
                    needTime = needTime * 8;
                    break;
            }
            TimeType actualType = t.getActualType ();
            double actualTime = t.getActualTime ();
            switch (actualType) {
                case MINUTE:
                    actualTime = new BigDecimal ( actualTime / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue ();
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
        TimeSonBO timeSonBO = new TimeSonBO ();
        timeSonBO.setNoDiffer ( noDiffer );
        timeSonBO.setHaveDiffer ( haveDiffer );
        return timeSonBO;
    }

    @Override
    public OptionBO confirmCountFigure(TaskNodeDTO dto) throws SerException {
        TitleBO titleBO = new TitleBO ();
        titleBO.setText ( "分配及确认汇总" );
        LegendBO legendBO = new LegendBO ();
        legendBO.setData ( new String[]{"已分配", "未分配", "确认接收量", "未接收任务量", "待确认接收量"} );
        if (CountType.PERSON.equals ( dto.getCountType () )) {
            return confirmCountPersonFigure ( dto, titleBO, legendBO );
        } else if (CountType.WHOLE.equals ( dto.getCountType () )) {
            List<String> as = areas ();
            String[] area = new String[as.size ()];
            if (!as.isEmpty ()) {
                area = as.toArray ( area );
            }
            List<String> ds = departs ();
            String[] depart = new String[ds.size ()];
            if (!ds.isEmpty ()) {
                depart = ds.toArray ( depart );
            }
            return confirmCountFigure ( area, depart, dto.getStartTime (), dto.getEndTime (), titleBO, legendBO, dto.getTables () );
        } else {
            return confirmCountFigure ( dto.getArea (), dto.getDepart (), dto.getStartTime (), dto.getEndTime (), titleBO, legendBO, dto.getTables () );
        }
    }

    private OptionBO confirmCountFigure(String[] area, String[] depart, String startTime, String endTime, TitleBO
            titleBO, LegendBO legendBO, String[] ts) throws SerException {
        if (null == area) {
            throw new SerException ( "不是整体汇总必须选择地区" );
        }
        if (null == depart) {
            throw new SerException ( "不是整体汇总必须选择部门" );
        }
        List<String> names = new ArrayList<> ();
        List<Double> haveInitiates = new ArrayList<> ();
        List<Double> noInitiates = new ArrayList<> ();
        List<Double> confirms = new ArrayList<> ();
        List<Double> unConfirms = new ArrayList<> ();
        List<Double> toConfirms = new ArrayList<> ();
        double haveInitiateSum = 0;
        double noInitiateSum = 0;
        double confirmSum = 0;
        double unConfirmSum = 0;
        double toConfirmSum = 0;   //合计
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", area ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", depart ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        TableDTO tableDTO = new TableDTO ();
        tableDTO.getConditions ().add ( Restrict.in ( "projectId", projects.stream ().map ( Project::getId ).distinct ().collect ( Collectors.toList () ) ) );
        if (null != ts) {
            tableDTO.getConditions ().add ( Restrict.in ( "name", ts ) );
        }
        List<Table> tables = tableSer.findByCis ( tableDTO );
        Set<String> tableIDS = tables.stream ().map ( Table::getId ).collect ( Collectors.toSet () );
        List<TaskNode> taskNodes = find ( startTime, endTime, tableIDS );
        for (String tableID : tableIDS) {
            names.add ( tableSer.findById ( tableID ).getName () );
            double haveInitiateAdmin = 0;
            double noInitiateAdmin = 0;
            double confirmAdmin = 0;
            double unConfirmAdmin = 0;
            double toConfirmAdmin = 0;
            for (TaskType taskType : taskTypes ()) {
                ConfirmLastBO lastBO = getConfirmFigure1 ( taskNodes, taskType, tableID );
                haveInitiateAdmin += lastBO.getHaveInitiate ();
                noInitiateAdmin += lastBO.getNoInitiate ();
                confirmAdmin += lastBO.getConfirm ();
                unConfirmAdmin += lastBO.getUnConfirm ();
                toConfirmAdmin += lastBO.getToConfirm ();
                haveInitiateSum += lastBO.getHaveInitiate ();
                noInitiateSum += lastBO.getNoInitiate ();
                confirmSum += lastBO.getConfirm ();
                unConfirmSum += lastBO.getUnConfirm ();
                toConfirmSum += lastBO.getToConfirm ();
            }
            haveInitiates.add ( haveInitiateAdmin );
            noInitiates.add ( noInitiateAdmin );
            confirms.add ( confirmAdmin );
            unConfirms.add ( unConfirmAdmin );
            toConfirms.add ( toConfirmAdmin );
        }
        names.add ( "合计" );
        String[] strings = new String[names.size ()];
        strings = names.toArray ( strings );
        XAxisBO xAxisBO = new XAxisBO ();
        xAxisBO.setData ( strings );
        haveInitiates.add ( haveInitiateSum );
        noInitiates.add ( noInitiateSum );
        confirms.add ( confirmSum );
        unConfirms.add ( unConfirmSum );
        toConfirms.add ( toConfirmSum );
        OptionBO optionBO = new OptionBO ();
        optionBO.setLegend ( legendBO );
        optionBO.setTitle ( titleBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setSeries ( seriesBOS ( haveInitiates, noInitiates, confirms, unConfirms, toConfirms ) );
        optionBO.setyAxis ( new YAxisBO () );
        optionBO.setTooltip ( new TooltipBO () );
        return optionBO;
    }

    @Override
    public DataBO personBing(TaskNodeDTO dto) throws SerException {
        PersonCountType personCountType = dto.getPersonCountType ();
        String startTime = null;
        String endTime = null;
        switch (personCountType) {
            case DAY:
                startTime = DateUtil.dateToString ( LocalDate.now () );
                endTime = DateUtil.dateToString ( LocalDate.now () );
                break;
            case WEEK:
                startTime = DateUtil.dateToString ( DateUtil.getStartWeek () );
                endTime = DateUtil.dateToString ( DateUtil.getEndWeek () );
                break;
            case SEASON:
                startTime = StringUtils.substringBefore ( DateUtil.dateToString ( DateUtil.getStartQuart () ), " " );
                endTime = StringUtils.substringBefore ( DateUtil.dateToString ( DateUtil.getEndQuart () ), " " );
                break;
            case MONTH:
                startTime = DateUtil.dateToString ( DateUtil.getStartMonth () );
                endTime = DateUtil.dateToString ( DateUtil.getEndMonth () );
                break;
            case YEAR:
                startTime = DateUtil.dateToString ( DateUtil.getStartYear () );
                endTime = DateUtil.dateToString ( DateUtil.getEndYear () );
                break;
        }
        double haveInitiateAdmin = 0;
        double noInitiateAdmin = 0;
        double confirmAdmin = 0;
        double unConfirmAdmin = 0;
        double toConfirmAdmin = 0;
        List<TaskNode> taskNodes = find ( startTime, endTime, new HashSet<> () );
        for (TaskType taskType : taskTypes ()) {
            ConfirmLastBO lastBO = getConfirmFigure ( taskNodes, taskType, dto.getUser () );
            haveInitiateAdmin += lastBO.getHaveInitiate ();
            noInitiateAdmin += lastBO.getNoInitiate ();
            confirmAdmin += lastBO.getConfirm ();
            unConfirmAdmin += lastBO.getUnConfirm ();
            toConfirmAdmin += lastBO.getToConfirm ();
        }
        DataBO dataBO = new DataBO ();
        dataBO.setStrings ( new String[]{"已分配", "未分配", "确认接收量", "未接收任务量", "待确认接收量"} );
        List<PersonLastBO1> list = new ArrayList<> ();
        PersonLastBO1 l1 = new PersonLastBO1 ( haveInitiateAdmin, "已分配" );
        PersonLastBO1 l2 = new PersonLastBO1 ( noInitiateAdmin, "未分配" );
        PersonLastBO1 l3 = new PersonLastBO1 ( confirmAdmin, "确认接收量" );
        PersonLastBO1 l4 = new PersonLastBO1 ( unConfirmAdmin, "未接收任务量" );
        PersonLastBO1 l5 = new PersonLastBO1 ( toConfirmAdmin, "待确认接收量" );
        list.add ( l1 );
        list.add ( l2 );
        list.add ( l3 );
        list.add ( l4 );
        list.add ( l5 );
        dataBO.setData ( list );
        return dataBO;
    }

    private OptionBO confirmCountPersonFigure(TaskNodeDTO dto, TitleBO titleBO, LegendBO legendBO) throws
            SerException {
        List<String> names = new ArrayList<> ();
        List<Double> haveInitiates = new ArrayList<> ();
        List<Double> noInitiates = new ArrayList<> ();
        List<Double> confirms = new ArrayList<> ();
        List<Double> unConfirms = new ArrayList<> ();
        List<Double> toConfirms = new ArrayList<> ();
        double haveInitiateSum = 0;
        double noInitiateSum = 0;
        double confirmSum = 0;
        double unConfirmSum = 0;
        double toConfirmSum = 0;   //合计
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", dto.getArea () ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", dto.getDepart () ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        TableDTO tableDTO = new TableDTO ();
        if (null != dto.getTables ()) {
            tableDTO.getConditions ().add ( Restrict.in ( "name", dto.getTables () ) );
        }
        tableDTO.getConditions ().add ( Restrict.in ( "projectId", projects.stream ().map ( Project::getId ).distinct ().collect ( Collectors.toList () ) ) );
        List<Table> tables = tableSer.findByCis ( tableDTO );
        List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tables.stream ().map ( Table::getId ).collect ( Collectors.toSet () ) );
        for (String name : dto.getName ()) {
            names.add ( name );
            double haveInitiateAdmin = 0;
            double noInitiateAdmin = 0;
            double confirmAdmin = 0;
            double unConfirmAdmin = 0;
            double toConfirmAdmin = 0;
            for (TaskType taskType : taskTypes ()) {
                ConfirmLastBO lastBO = getConfirmFigure ( taskNodes, taskType, name );
                haveInitiateAdmin += lastBO.getHaveInitiate ();
                noInitiateAdmin += lastBO.getNoInitiate ();
                confirmAdmin += lastBO.getConfirm ();
                unConfirmAdmin += lastBO.getUnConfirm ();
                toConfirmAdmin += lastBO.getToConfirm ();
                haveInitiateSum += lastBO.getHaveInitiate ();
                noInitiateSum += lastBO.getNoInitiate ();
                confirmSum += lastBO.getConfirm ();
                unConfirmSum += lastBO.getUnConfirm ();
                toConfirmSum += lastBO.getToConfirm ();
            }
            haveInitiates.add ( haveInitiateAdmin );
            noInitiates.add ( noInitiateAdmin );
            confirms.add ( confirmAdmin );
            unConfirms.add ( unConfirmAdmin );
            toConfirms.add ( toConfirmAdmin );
        }
        names.add ( "合计" );
        String[] strings = new String[names.size ()];
        strings = names.toArray ( strings );
        XAxisBO xAxisBO = new XAxisBO ();
        xAxisBO.setData ( strings );
        haveInitiates.add ( haveInitiateSum );
        noInitiates.add ( noInitiateSum );
        confirms.add ( confirmSum );
        unConfirms.add ( unConfirmSum );
        toConfirms.add ( toConfirmSum );
        OptionBO optionBO = new OptionBO ();
        optionBO.setLegend ( legendBO );
        optionBO.setTitle ( titleBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setSeries ( seriesBOS ( haveInitiates, noInitiates, confirms, unConfirms, toConfirms ) );
        optionBO.setyAxis ( new YAxisBO () );
        optionBO.setTooltip ( new TooltipBO () );
        return optionBO;
    }

    private List<SeriesBO> seriesBOS(List<Double> haveInitiates,
                                     List<Double> noInitiates,
                                     List<Double> confirms,
                                     List<Double> unConfirms,
                                     List<Double> toConfirms) throws SerException {
        SeriesBO series = new SeriesBO ();
        series.setName ( "已分配" );
        series.setType ( "bar" );
        Double[] haveInitiatess = new Double[haveInitiates.size ()];
        haveInitiatess = haveInitiates.toArray ( haveInitiatess );
        series.setData ( haveInitiatess );
        SeriesBO series1 = new SeriesBO ();
        series1.setName ( "未分配" );
        series1.setType ( "bar" );
        Double[] noInitiatess = new Double[noInitiates.size ()];
        noInitiatess = noInitiates.toArray ( noInitiatess );
        series1.setData ( noInitiatess );
        SeriesBO series2 = new SeriesBO ();
        series2.setName ( "确认接收量" );
        series2.setType ( "bar" );
        Double[] confirmss = new Double[confirms.size ()];
        confirmss = confirms.toArray ( confirmss );
        series2.setData ( confirmss );
        SeriesBO series3 = new SeriesBO ();
        series3.setName ( "未接收任务量" );
        series3.setType ( "bar" );
        Double[] unConfirmss = new Double[unConfirms.size ()];
        unConfirmss = unConfirms.toArray ( unConfirmss );
        series3.setData ( unConfirmss );
        SeriesBO series4 = new SeriesBO ();
        series4.setName ( "待确认接收量" );
        series4.setType ( "bar" );
        Double[] toConfirmss = new Double[toConfirms.size ()];
        toConfirmss = toConfirms.toArray ( toConfirmss );
        series4.setData ( toConfirmss );
        List<SeriesBO> seriesBOS = new ArrayList<> ();
        seriesBOS.add ( series );
        seriesBOS.add ( series1 );
        seriesBOS.add ( series2 );
        seriesBOS.add ( series3 );
        seriesBOS.add ( series4 );
        return seriesBOS;
    }

    @Override
    public List<ConfirmCountBO> confirmCount(TaskNodeDTO dto) throws SerException {
//        LocalDate start = DateUtil.parseDate(dto.getStartTime());
//        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea ();
        String[] departs = dto.getDepart ();
        String[] projects = dto.getProjects ();
        String[] names = dto.getName ();
        String[] tables = dto.getTables ();
        CountType countType = dto.getCountType ();
//        dto.getConditions().add(Restrict.isNull("haveSon"));
//        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
//        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return cCount ( dto, areas, departs, projects, null, tables );
        } else if (CountType.WHOLE.equals ( countType )) {
            List<String> as = areas ();
            String[] area = new String[as.size ()];
            if (!as.isEmpty ()) {
                area = as.toArray ( area );
            }
            List<String> ds = departs ();
            String[] depart = new String[ds.size ()];
            if (!ds.isEmpty ()) {
                depart = ds.toArray ( depart );
            }
            List<String> ps1 = projects ();
            String[] ps11 = new String[ps1.size ()];
            if (!ps1.isEmpty ()) {
                ps11 = ds.toArray ( ps11 );
            }
            return cCount ( dto, area, depart, ps11, null, tables );
        } else if (CountType.DEPART.equals ( countType )) {
            return cCount ( dto, areas, departs, projects, null, tables );
        } else if (CountType.PERSON.equals ( countType )) {
            return cCount ( dto, areas, departs, projects, names, tables );
        }
        return null;
    }

    private List<ConfirmCountBO> cCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] ps, String[]
            names, String[] ts) throws
            SerException {
        if (null == areas) {
            throw new SerException ( "不是整体汇总必须选择地区" );
        }
        if (null == departs) {
            throw new SerException ( "不是整体汇总必须选择部门" );
        }
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "area", areas ) );
        projectDTO.getConditions ().add ( Restrict.in ( "depart", departs ) );
        projectDTO.getConditions ().add ( Restrict.in ( "project", ps ) );
        List<Project> projects = projectSer.findByCis ( projectDTO );
        List<ConfirmCountBO> confirmCountBOS = new ArrayList<> ();
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
            ConfirmCountBO confirmCountBO = new ConfirmCountBO ();
            List<ConfirmSonBO> confirmSonBOS = new ArrayList<> ();

            for (String depart : departs) {   //子层
                long haveInitiateDepart = 0;
                long noInitiateDepart = 0;
                long confirmDepart = 0;
                long unConfirmDepart = 0;
                long toConfirmDepart = 0;     //部门合计

                List<ConfirmProBO> caseSonBOS = new ArrayList<> ();
//                List<ConfirmTableBO> confirmTableBOS = new ArrayList<>();
                Set<String> projectIds = projects.stream ().filter ( project -> area.equals ( project.getArea () ) && depart.equals ( project.getDepart () ) ).map ( project -> project.getId () ).collect ( Collectors.toSet () );
                TableDTO tableDTO = new TableDTO ();
                if (!projectIds.isEmpty ()) {
                    ConfirmSonBO confirmSonBO = new ConfirmSonBO ();
                    confirmCountBO.setArea ( area );
                    confirmSonBO.setDepart ( depart );

                    for (String pro : ps) {
                        ConfirmProBO caseProBO = new ConfirmProBO ();
                        caseProBO.setProject ( pro );
                        List<ConfirmTableBO> confirmTableBOS = new ArrayList<> ();
                        tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
                        if (null != ts) {
                            tableDTO.getConditions ().add ( Restrict.in ( "name", ts ) );
                        }
                        List<Table> tables = tableSer.findByCis ( tableDTO );
                        Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
//                    dto.getConditions().add(Restrict.in("tableId", tableIds));
                        List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );

                        for (String table : tableIds) {   //孙子
                            List<ConfirmGrandSonBO> confirmGrandSonBOS = new ArrayList<> ();
                            ConfirmTableBO tableBO = new ConfirmTableBO ();
                            tableBO.setTableName ( tableSer.findById ( table ).getName () );
                            //最底层
                            if (null != names) {
                                for (String name : names) {
                                    List<ConfirmLastBO> confirmLastBOS = new ArrayList<> ();
                                    ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO ();
                                    confirmGrandSonBO.setName ( name );
                                    //TODO 任务内容 该员工在该任务表里面的任务内容
                                    TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
                                    taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", tableIds ) );
                                    taskNodeDTO.getConditions ().add ( Restrict.eq ( "execute", name ) );
                                    List<TaskNode> taskContents = super.findByCis ( taskNodeDTO );
                                    if (taskContents != null && taskContents.size () > 0) {
                                        List<String> temp_content = taskContents.stream ().map ( TaskNode::getContent ).collect ( Collectors.toList () );
                                        confirmGrandSonBO.setTaskContent ( String.join ( ",", temp_content ) );
                                    } else {
                                        confirmGrandSonBO.setTaskContent ( "" );
                                    }
                                    for (TaskType taskType : taskTypes ()) {
                                        ConfirmLastBO confirmLastBO = getConfirm1 ( taskNodes, taskType, name, table );
                                        confirmLastBOS.add ( confirmLastBO );
                                    }
                                    confirmGrandSonBO.setConfirmLastS ( confirmLastBOS );
                                    confirmGrandSonBOS.add ( confirmGrandSonBO );
                                    //部门合计
                                    haveInitiateDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                    noInitiateDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                    confirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                    unConfirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                    toConfirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                                    //地区合计
                                    haveInitiateArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                    noInitiateArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                    confirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                    unConfirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                    toConfirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                                    //总合计
                                    haveInitiateSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                    noInitiateSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                    confirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                    unConfirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                    toConfirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                                }
                            } else {
                                List<ConfirmLastBO> confirmLastBOS = new ArrayList<> ();
                                ConfirmGrandSonBO confirmGrandSonBO = new ConfirmGrandSonBO ();
                                for (TaskType taskType : taskTypes ()) {
                                    ConfirmLastBO confirmLastBO = getConfirm ( taskNodes, taskType, table );
                                    confirmLastBOS.add ( confirmLastBO );
                                }
                                confirmGrandSonBO.setConfirmLastS ( confirmLastBOS );
                                confirmGrandSonBOS.add ( confirmGrandSonBO );
                                //部门合计
                                haveInitiateDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                noInitiateDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                confirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                unConfirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                toConfirmDepart += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                                //地区合计
                                haveInitiateArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                noInitiateArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                confirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                unConfirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                toConfirmArea += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                                //总合计
                                haveInitiateSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getHaveInitiate () ).sum ();
                                noInitiateSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getNoInitiate () ).sum ();
                                confirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getConfirm () ).sum ();
                                unConfirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getUnConfirm () ).sum ();
                                toConfirmSum += confirmLastBOS.stream ().mapToLong ( confirmLastBO -> confirmLastBO.getToConfirm () ).sum ();
                            }
                            tableBO.setGrandSonS ( confirmGrandSonBOS );
                            confirmTableBOS.add ( tableBO );
                        }
//
                        if (!confirmTableBOS.isEmpty ()) {
                            caseProBO.setTableS ( confirmTableBOS );
                            caseSonBOS.add ( caseProBO );
                        }


                    }
                    if (!caseSonBOS.isEmpty ()) {
                        confirmSonBO.setConfirmProBOS ( caseSonBOS );
                        confirmSonBOS.add ( confirmSonBO );
                    }
                    if (!(haveInitiateDepart == 0 && noInitiateDepart == 0 && confirmDepart == 0 && unConfirmDepart == 0 && toConfirmDepart == 0)) {
                        ConfirmSonBO departCount = departSum ( haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart );
                        confirmSonBOS.add ( departCount );
                    }
                }
            }
            if (!confirmSonBOS.isEmpty ()) {
                confirmCountBO.setConfirmSonS ( confirmSonBOS );
                confirmCountBOS.add ( confirmCountBO );
            }
            if (!(haveInitiateArea == 0 && noInitiateArea == 0 && confirmArea == 0 && unConfirmArea == 0 && toConfirmArea == 0)) {
                ConfirmCountBO areaCount = sum ( haveInitiateArea, noInitiateArea, confirmArea, unConfirmArea, toConfirmArea, "地区合计" );
                confirmCountBOS.add ( areaCount );
            }
        }
        ConfirmCountBO sum = sum ( haveInitiateSum, noInitiateSum, confirmSum, unConfirmSum, toConfirmSum, "总合计" );
        confirmCountBOS.add ( sum );
        return confirmCountBOS;
    }

    //部门合计

    private ConfirmSonBO departSum(long haveInitiateDepart,
                                   long noInitiateDepart,
                                   long confirmDepart,
                                   long unConfirmDepart,
                                   long toConfirmDepart) throws SerException {
        List<ConfirmLastBO> confirmLastS = new ArrayList<> ();
        ConfirmLastBO lastBO = new ConfirmLastBO ( haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart );
        confirmLastS.add ( lastBO );
        List<ConfirmGrandSonBO> grandSonS = new ArrayList<> ();
        ConfirmGrandSonBO grandSonBO = new ConfirmGrandSonBO ();
        List<ConfirmProBO> proS = new ArrayList<> ();
        ConfirmProBO confirmProBO = new ConfirmProBO ();
        grandSonBO.setConfirmLastS ( confirmLastS );
        grandSonS.add ( grandSonBO );
        List<ConfirmTableBO> tableS = new ArrayList<> ();
        ConfirmTableBO confirmTableBO = new ConfirmTableBO ();
        confirmTableBO.setGrandSonS ( grandSonS );
        tableS.add ( confirmTableBO );
        ConfirmSonBO departCount = new ConfirmSonBO ();
        confirmProBO.setTableS ( tableS );
        proS.add ( confirmProBO );
        departCount.setConfirmProBOS ( proS );
//        departCount.setTableS(tableS);
        departCount.setDepart ( "合计" );
        return departCount;
    }

    //总合计
    private ConfirmCountBO sum(long haveInitiateDepart,
                               long noInitiateDepart,
                               long confirmDepart,
                               long unConfirmDepart,
                               long toConfirmDepart, String s) throws SerException {
        List<ConfirmLastBO> confirmLastS = new ArrayList<> ();
        ConfirmLastBO lastBO = new ConfirmLastBO ( haveInitiateDepart, noInitiateDepart, confirmDepart, unConfirmDepart, toConfirmDepart );
        confirmLastS.add ( lastBO );
        List<ConfirmGrandSonBO> grandSonS = new ArrayList<> ();
        ConfirmGrandSonBO grandSonBO = new ConfirmGrandSonBO ();
        List<ConfirmProBO> proS = new ArrayList<> ();
        ConfirmProBO confirmProBO = new ConfirmProBO ();
        grandSonBO.setConfirmLastS ( confirmLastS );
        grandSonS.add ( grandSonBO );
        List<ConfirmTableBO> tableS = new ArrayList<> ();
        ConfirmTableBO confirmTableBO = new ConfirmTableBO ();
        confirmTableBO.setGrandSonS ( grandSonS );
        tableS.add ( confirmTableBO );
        ConfirmSonBO departCount = new ConfirmSonBO ();
        confirmProBO.setTableS ( tableS );
        departCount.setConfirmProBOS ( proS );
//        departCount.setTableS(tableS);
        List<ConfirmSonBO> sonBOS = new ArrayList<> ();
        sonBOS.add ( departCount );
        ConfirmCountBO confirmCountBO = new ConfirmCountBO ();
        if ("总合计".equals ( s )) {
            confirmCountBO.setArea ( "总合计" );
        } else if ("地区合计".equals ( s )) {
            confirmCountBO.setArea ( "合计" );
        }
        confirmCountBO.setConfirmSonS ( sonBOS );
        return confirmCountBO;
    }

    private ConfirmLastBO getConfirm(List<TaskNode> taskNodes, TaskType taskType, String tableId) throws
            SerException {
        long haveInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getInitiate ()) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long noInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null == taskNode.getInitiate ()) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long confirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && taskNode.getConfirm () && tableId.equals ( taskNode.getTableId () ) ).count ();
        long unConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && (!taskNode.getConfirm ()) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long toConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && null == taskNode.getConfirm () && tableId.equals ( taskNode.getTableId () ) ).count ();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO ();
        confirmLastBO.setTaskType ( taskType );
        confirmLastBO.setHaveInitiate ( haveInitiate );
        confirmLastBO.setNoInitiate ( noInitiate );
        confirmLastBO.setConfirm ( confirm );
        confirmLastBO.setUnConfirm ( unConfirm );
        confirmLastBO.setToConfirm ( toConfirm );
        return confirmLastBO;
    }

    private ConfirmLastBO getConfirm1(List<TaskNode> taskNodes, TaskType taskType, String name, String
            tableId) throws SerException {
        long haveInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getInitiate ()) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long noInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null == taskNode.getInitiate ()) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long confirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && taskNode.getConfirm () && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long unConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && (!taskNode.getConfirm ()) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).count ();
        long toConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && null == taskNode.getConfirm () && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).count ();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO ();
        confirmLastBO.setTaskType ( taskType );
        confirmLastBO.setHaveInitiate ( haveInitiate );
        confirmLastBO.setNoInitiate ( noInitiate );
        confirmLastBO.setConfirm ( confirm );
        confirmLastBO.setUnConfirm ( unConfirm );
        confirmLastBO.setToConfirm ( toConfirm );
        return confirmLastBO;
    }

    private ConfirmLastBO getConfirmFigure(List<TaskNode> taskNodes, TaskType taskType, String name) throws
            SerException {
        long haveInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getInitiate ()) && name.equals ( taskNode.getExecute () ) ).count ();
        long noInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null == taskNode.getInitiate ()) && name.equals ( taskNode.getExecute () ) ).count ();
        long confirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && taskNode.getConfirm () && name.equals ( taskNode.getExecute () ) ).count ();
        long unConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && (!taskNode.getConfirm ()) && name.equals ( taskNode.getExecute () ) ).count ();
        long toConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && null == taskNode.getConfirm () && name.equals ( taskNode.getExecute () ) ).count ();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO ();
        confirmLastBO.setTaskType ( taskType );
        confirmLastBO.setHaveInitiate ( haveInitiate );
        confirmLastBO.setNoInitiate ( noInitiate );
        confirmLastBO.setConfirm ( confirm );
        confirmLastBO.setUnConfirm ( unConfirm );
        confirmLastBO.setToConfirm ( toConfirm );
        return confirmLastBO;
    }

    private ConfirmLastBO getConfirmFigure1(List<TaskNode> taskNodes, TaskType taskType, String tableID) throws
            SerException {
        long haveInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getInitiate ()) && tableID.equals ( taskNode.getTableId () ) ).count ();
        long noInitiate = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null == taskNode.getInitiate ()) && tableID.equals ( taskNode.getTableId () ) ).count ();
        long confirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && taskNode.getConfirm () && tableID.equals ( taskNode.getTableId () ) ).count ();
        long unConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getConfirm ()) && (!taskNode.getConfirm ()) && tableID.equals ( taskNode.getTableId () ) ).count ();
        long toConfirm = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && null == taskNode.getConfirm () && tableID.equals ( taskNode.getTableId () ) ).count ();
        ConfirmLastBO confirmLastBO = new ConfirmLastBO ();
        confirmLastBO.setTaskType ( taskType );
        confirmLastBO.setHaveInitiate ( haveInitiate );
        confirmLastBO.setNoInitiate ( noInitiate );
        confirmLastBO.setConfirm ( confirm );
        confirmLastBO.setUnConfirm ( unConfirm );
        confirmLastBO.setToConfirm ( toConfirm );
        return confirmLastBO;
    }

    @Override
    public OptionBO finishCountFigure(TaskNodeDTO dto) throws SerException {
        TitleBO titleBO = new TitleBO ();
        titleBO.setText ( "完成情况汇总" );
        LegendBO legendBO = new LegendBO ();
        legendBO.setData ( new String[]{"计划任务量", "完成任务量", "未完成量", "计划总工时（小时）", "已完成工时(小时)", "未完成工时(小时)", "上报未完成工时(小时)", "未上报未完成工时(小时)"} );
        ProjectDTO projectDTO = new ProjectDTO ();
        if (null != dto.getArea () && dto.getArea ().length > 0) {
            projectDTO.getConditions ().add ( Restrict.in ( "area", dto.getArea () ) );
        }
        if (null != dto.getDepart () && dto.getDepart ().length > 0) {
            projectDTO.getConditions ().add ( Restrict.in ( "depart", dto.getDepart () ) );
        }
        List<Project> projects = projectSer.findByCis ( projectDTO );
        TableDTO tableDTO = new TableDTO ();
        if (null != dto.getTables ()) {
            tableDTO.getConditions ().add ( Restrict.in ( "name", dto.getTables () ) );
        }
        tableDTO.getConditions ().add ( Restrict.in ( "projectId", projects.stream ().map ( Project::getId ).distinct ().collect ( Collectors.toList () ) ) );
        List<Table> tables = tableSer.findByCis ( tableDTO );
        Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
        List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
        List<String> names = new ArrayList<> ();
        List<Double> planNums = new ArrayList<> ();
        List<Double> actualNums = new ArrayList<> ();
        List<Double> unFinishNums = new ArrayList<> ();
        List<Double> planTimes = new ArrayList<> ();
        List<Double> actualTimes = new ArrayList<> ();
        List<Double> unFinishTimes = new ArrayList<> ();
        List<Double> reportUnFinishs = new ArrayList<> ();
        List<Double> unReportUnFinishs = new ArrayList<> ();
        double planNumSum = 0;
        double actualNumSum = 0;
        double unFinishNumSum = 0;
        double planTimeSum = 0;
        double actualTimeSum = 0;
        double unFinishTimeSum = 0;
        double reportUnFinishSum = 0;
        double unReportUnFinishSum = 0;
        if (CountType.PERSON.equals ( dto.getCountType () )) {
            for (String name : dto.getName ()) {
                names.add ( name );
                CaseLastBO lastBO = get2 ( taskNodes, name, null );
                planNumSum += lastBO.getPlanNum ();
                actualNumSum += lastBO.getActualNum ();
                unFinishNumSum += lastBO.getUnFinishNum ();
                planTimeSum += lastBO.getPlanTime ();
                actualTimeSum += lastBO.getActualTime ();
                unFinishTimeSum += lastBO.getUnFinishTime ();
                reportUnFinishSum += lastBO.getReportUnFinish ();
                unReportUnFinishSum += lastBO.getUnReportUnFinish ();
                planNums.add ( lastBO.getPlanNum () );
                actualNums.add ( lastBO.getActualNum () );
                unFinishNums.add ( lastBO.getUnFinishNum () );
                planTimes.add ( lastBO.getPlanTime () );
                actualTimes.add ( lastBO.getActualTime () );
                unFinishTimes.add ( lastBO.getUnFinishTime () );
                reportUnFinishs.add ( lastBO.getReportUnFinish () );
                unReportUnFinishs.add ( lastBO.getUnReportUnFinish () );
            }
        } else {
            for (String name : tableIds) {
                names.add ( tableSer.findById ( name ).getName () );
                CaseLastBO lastBO = get2 ( taskNodes, null, name );
                planNumSum += lastBO.getPlanNum ();
                actualNumSum += lastBO.getActualNum ();
                unFinishNumSum += lastBO.getUnFinishNum ();
                planTimeSum += lastBO.getPlanTime ();
                actualTimeSum += lastBO.getActualTime ();
                unFinishTimeSum += lastBO.getUnFinishTime ();
                reportUnFinishSum += lastBO.getReportUnFinish ();
                unReportUnFinishSum += lastBO.getUnReportUnFinish ();
                planNums.add ( lastBO.getPlanNum () );
                actualNums.add ( lastBO.getActualNum () );
                unFinishNums.add ( lastBO.getUnFinishNum () );
                planTimes.add ( lastBO.getPlanTime () );
                actualTimes.add ( lastBO.getActualTime () );
                unFinishTimes.add ( lastBO.getUnFinishTime () );
                reportUnFinishs.add ( lastBO.getReportUnFinish () );
                unReportUnFinishs.add ( lastBO.getUnReportUnFinish () );
            }
        }
        names.add ( "合计" );
        planNums.add ( planNumSum );
        actualNums.add ( actualNumSum );
        unFinishNums.add ( unFinishNumSum );
        planTimes.add ( planTimeSum );
        actualTimes.add ( actualTimeSum );
        unFinishTimes.add ( unFinishTimeSum );
        reportUnFinishs.add ( reportUnFinishSum );
        unReportUnFinishs.add ( unReportUnFinishSum );
        List<SeriesBO> seriesBOS = seriesBOS1 ( planNums, actualNums, unFinishNums, planTimes, actualTimes, unFinishTimes, reportUnFinishs, unReportUnFinishs );
        XAxisBO xAxisBO = new XAxisBO ();
        String[] strings = new String[names.size ()];
        strings = names.toArray ( strings );
        xAxisBO.setData ( strings );
        OptionBO optionBO = new OptionBO ();
        optionBO.setTitle ( titleBO );
        optionBO.setLegend ( legendBO );
        optionBO.setxAxis ( xAxisBO );
        optionBO.setSeries ( seriesBOS );
        optionBO.setyAxis ( new YAxisBO () );
        optionBO.setTooltip ( new TooltipBO () );
        return optionBO;
    }

    private List<SeriesBO> seriesBOS1(List<Double> planNums,
                                      List<Double> actualNums,
                                      List<Double> unFinishNums,
                                      List<Double> planTimes,
                                      List<Double> actualTimes,
                                      List<Double> unFinishTimes,
                                      List<Double> reportUnFinishs,
                                      List<Double> unReportUnFinishs) throws SerException {
        SeriesBO series = new SeriesBO ();
        series.setName ( "计划任务量" );
        series.setType ( "bar" );
        Double[] haveInitiatess = new Double[planNums.size ()];
        haveInitiatess = planNums.toArray ( haveInitiatess );
        series.setData ( haveInitiatess );
        SeriesBO series1 = new SeriesBO ();
        series1.setName ( "完成任务量" );
        series1.setType ( "bar" );
        Double[] noInitiatess = new Double[actualNums.size ()];
        noInitiatess = actualNums.toArray ( noInitiatess );
        series1.setData ( noInitiatess );
        SeriesBO series2 = new SeriesBO ();
        series2.setName ( "未完成量" );
        series2.setType ( "bar" );
        Double[] confirmss = new Double[unFinishNums.size ()];
        confirmss = unFinishNums.toArray ( confirmss );
        series2.setData ( confirmss );
        SeriesBO series3 = new SeriesBO ();
        series3.setName ( "计划总工时（小时）" );
        series3.setType ( "bar" );
        Double[] unConfirmss = new Double[planTimes.size ()];
        unConfirmss = planTimes.toArray ( unConfirmss );
        series3.setData ( unConfirmss );
        SeriesBO series4 = new SeriesBO ();
        series4.setName ( "已完成工时(小时)" );
        series4.setType ( "bar" );
        Double[] toConfirmss = new Double[actualTimes.size ()];
        toConfirmss = actualTimes.toArray ( toConfirmss );
        series4.setData ( toConfirmss );
        SeriesBO series5 = new SeriesBO ();
        series5.setName ( "未完成工时(小时)" );
        series5.setType ( "bar" );
        Double[] unFinishTimess = new Double[unFinishTimes.size ()];
        unFinishTimess = unFinishTimes.toArray ( unFinishTimess );
        series5.setData ( unFinishTimess );
        SeriesBO series6 = new SeriesBO ();
        series6.setName ( "上报未完成工时(小时)" );
        series6.setType ( "bar" );
        Double[] reportUnFinishss = new Double[reportUnFinishs.size ()];
        reportUnFinishss = reportUnFinishs.toArray ( reportUnFinishss );
        series6.setData ( reportUnFinishss );
        SeriesBO series7 = new SeriesBO ();
        series7.setName ( "未上报未完成工时(小时)" );
        series7.setType ( "bar" );
        Double[] unReportUnFinishss = new Double[unReportUnFinishs.size ()];
        unReportUnFinishss = unReportUnFinishs.toArray ( unReportUnFinishss );
        series7.setData ( unReportUnFinishss );
        List<SeriesBO> seriesBOS = new ArrayList<> ();
        seriesBOS.add ( series );
        seriesBOS.add ( series1 );
        seriesBOS.add ( series2 );
        seriesBOS.add ( series3 );
        seriesBOS.add ( series4 );
        seriesBOS.add ( series5 );
        seriesBOS.add ( series6 );
        seriesBOS.add ( series7 );
        return seriesBOS;
    }


    @Override
    public List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException {
//        LocalDate start = DateUtil.parseDate(dto.getStartTime());
//        LocalDate end = DateUtil.parseDate(dto.getEndTime());
        String[] areas = dto.getArea ();
        String[] departs = dto.getDepart ();
        String[] ps = dto.getProjects ();
        String[] names = dto.getName ();
        String[] tables = dto.getTables ();
        CountType countType = dto.getCountType ();
//        dto.getConditions().add(Restrict.isNull("haveSon"));
//        dto.getConditions().add(Restrict.between("startTime", new LocalDate[]{start, end}));
//        dto.getConditions().add(Restrict.between("endTime", new LocalDate[]{start, end}));
        if (null == countType) {
            return fCount ( dto, areas, departs, ps, null, tables );
        } else if (CountType.WHOLE.equals ( countType )) {
            List<String> as = areas ();
            String[] area = new String[as.size ()];
            if (!as.isEmpty ()) {
                area = as.toArray ( area );
            }
            List<String> ds = departs ();
            String[] depart = new String[ds.size ()];
            if (!ds.isEmpty ()) {
                depart = ds.toArray ( depart );
            }
            List<String> ps1 = projects ();
            String[] ps11 = new String[ps1.size ()];
            if (!ps1.isEmpty ()) {
                ps11 = ds.toArray ( ps11 );
            }
            return fCount ( dto, area, depart, ps11, null, tables );
        } else if (CountType.DEPART.equals ( countType )) {
            return fCount ( dto, areas, departs, ps, null, tables );
        } else if (CountType.PERSON.equals ( countType )) {
            return fCount ( dto, areas, departs, ps, names, tables );
        }
        return null;
    }

    private List<FinishCaseBO> fCount(TaskNodeDTO dto, String[] areas, String[] departs, String[] ps, String[]
            names, String[] ts) throws SerException {
        ProjectDTO projectDTO = new ProjectDTO ();
        if (null != areas && areas.length > 0) {
            projectDTO.getConditions ().add ( Restrict.in ( "area", areas ) );
        }
        if (null != departs && departs.length > 0) {
            projectDTO.getConditions ().add ( Restrict.in ( "depart", departs ) );
        }
        if (null != ps && ps.length > 0) {
            projectDTO.getConditions ().add ( Restrict.in ( "project", ps ) );
        }
        List<Project> projects = projectSer.findByCis ( projectDTO );
        List<FinishCaseBO> finishCaseBOS = new ArrayList<> ();
        double planNumSum = 0;
        double actualNumSum = 0;
        double unFinishNumSum = 0;
        double planTimeSum = 0;
        double actualTimeSum = 0;
        double unFinishTimeSum = 0;
        double reportUnFinishSum = 0;
        double unReportUnFinishSum = 0;
        Long admininstrationNumSum = Long.valueOf ( 0 );
        Long engineeringNumSum = Long.valueOf ( 0 );
        Long trainingNumSum = Long.valueOf ( 0 );
        Long todayNumSum = Long.valueOf ( 0 );//总合计
        for (String area : areas) {
            double planNumArea = 0;
            double actualNumArea = 0;
            double unFinishNumArea = 0;
            double planTimeArea = 0;
            double actualTimeArea = 0;
            double unFinishTimeArea = 0;
            double reportUnFinishArea = 0;
            double unReportUnFinishArea = 0;
            Long admininstrationNumArea = Long.valueOf ( 0 );
            Long engineeringNumArea = Long.valueOf ( 0 );
            Long trainingNumArea = Long.valueOf ( 0 );
            Long todayNumArea = Long.valueOf ( 0 );//地区合计
            FinishCaseBO finishCaseBO = new FinishCaseBO ();
            List<CaseSonBO> caseSonBOS = new ArrayList<> ();
            for (String depart : departs) {
//                List<CaseProBO> caseProBOS = new ArrayList<>();
//                for (String pro : ps) {
                double planNumDepart = 0;
                double actualNumDepart = 0;
                double unFinishNumDepart = 0;
                double planTimeDepart = 0;
                double actualTimeDepart = 0;
                double unFinishTimeDepart = 0;
                double reportUnFinishDepart = 0;
                double unReportUnFinishDepart = 0;
                Long admininstrationNumDepart = Long.valueOf ( 0 );
                Long engineeringNumDepart = Long.valueOf ( 0 );
                Long trainingNumDepart = Long.valueOf ( 0 );
                Long todayNumDepart = Long.valueOf ( 0 );//部门合计
//                List<CaseTableBO> tableBOS = new ArrayList<>();
                List<CaseProBO> caseProBOS = new ArrayList<> ();
                Set<String> projectIds = projects.stream ().filter ( project -> area.equals ( project.getArea () ) && depart.equals ( project.getDepart () ) ).map ( project -> project.getId () ).collect ( Collectors.toSet () );
                TableDTO tableDTO = new TableDTO ();
                if (!projectIds.isEmpty ()) {
                    CaseSonBO caseSonBO = new CaseSonBO ();
                    finishCaseBO.setArea ( area );
                    caseSonBO.setDepart ( depart );

                    for (String pro : ps) {
                        CaseProBO caseProBO = new CaseProBO ();
                        caseProBO.setProName ( pro );
                        List<CaseTableBO> tableBOS = new ArrayList<> ();
                        tableDTO.getConditions ().add ( Restrict.in ( "projectId", projectIds ) );
                        if (null != ts) {
                            tableDTO.getConditions ().add ( Restrict.in ( "name", ts ) );
                        }
                        List<Table> tables = tableSer.findByCis ( tableDTO );
                        Set<String> tableIds = tables.stream ().map ( table -> table.getId () ).collect ( Collectors.toSet () );
//                    dto.getConditions().add(Restrict.in("tableId", tableIds));
                        List<TaskNode> taskNodes = find ( dto.getStartTime (), dto.getEndTime (), tableIds );
                        for (String table : tableIds) {
                            CaseTableBO tableBO = new CaseTableBO ();
                            List<CaseGrandSonBO> grandSonBOS = new ArrayList<> ();
                            tableBO.setTableName ( tableSer.findById ( table ).getName () );
                            if (null != names) {
                                //说明是个人汇总
                                for (String name : names) {
                                    List<CaseLastBO> caseLastBOS = new ArrayList<> ();
                                    CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO ();
                                    //人名
                                    caseGrandSonBO.setName ( name );
                                    //TODO 任务内容 该员工在该任务表里面的任务内容
                                    TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
                                    taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", tableIds ) );
                                    taskNodeDTO.getConditions ().add ( Restrict.eq ( "execute", name ) );
                                    List<TaskNode> taskContents = super.findByCis ( taskNodeDTO );
                                    if (taskContents != null && taskContents.size () > 0) {
                                        List<String> temp_content = taskContents.stream ().map ( TaskNode::getContent ).collect ( Collectors.toList () );
                                        caseGrandSonBO.setTaskContent ( String.join ( ",", temp_content ) );
                                    } else {
                                        caseGrandSonBO.setTaskContent ( "" );
                                    }
                                    for (TaskType taskType : taskTypes ()) {
                                        CaseLastBO caseLastBO = get1 ( taskNodes, taskType, name, table );
                                        caseLastBOS.add ( caseLastBO );
                                    }
                                    if (!caseLastBOS.isEmpty ()) {
                                        caseGrandSonBO.setCaseLastS ( caseLastBOS );
                                        grandSonBOS.add ( caseGrandSonBO );
                                    }
                                    //部门合计
                                    planNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                    actualNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                    unFinishNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                    planTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                    actualTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                    unFinishTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                    reportUnFinishDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                    unReportUnFinishDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                    //地区合计
                                    planNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                    actualNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                    unFinishNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                    planTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                    actualTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                    unFinishTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                    reportUnFinishArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                    unReportUnFinishArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                    //总合计
                                    planNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                    actualNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                    unFinishNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                    planTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                    actualTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                    unFinishTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                    reportUnFinishSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                    unReportUnFinishSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                }
                            } else {
                                List<CaseLastBO> caseLastBOS = new ArrayList<> ();
                                CaseGrandSonBO caseGrandSonBO = new CaseGrandSonBO ();
                                for (TaskType taskType : taskTypes ()) {
                                    CaseLastBO caseLastBO = get ( taskNodes, taskType, table );
                                    caseLastBOS.add ( caseLastBO );
                                }
                                if (!caseLastBOS.isEmpty ()) {
                                    caseGrandSonBO.setCaseLastS ( caseLastBOS );
                                    grandSonBOS.add ( caseGrandSonBO );
                                }
                                //部门合计
                                planNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                actualNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                unFinishNumDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                planTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                actualTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                unFinishTimeDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                reportUnFinishDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                unReportUnFinishDepart += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                admininstrationNumDepart += caseLastBOS.stream ().mapToLong ( CaseLastBO::getAdmininstrationNum ).sum ();
                                engineeringNumDepart += caseLastBOS.stream ().mapToLong ( CaseLastBO::getEngineeringNum ).sum ();
                                trainingNumDepart += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTrainingNum ).sum ();
                                todayNumDepart += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTodayNum ).sum ();
                                //地区合计
                                planNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                actualNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                unFinishNumArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                planTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                actualTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                unFinishTimeArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                reportUnFinishArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                unReportUnFinishArea += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                admininstrationNumArea += caseLastBOS.stream ().mapToLong ( CaseLastBO::getAdmininstrationNum ).sum ();
                                engineeringNumArea += caseLastBOS.stream ().mapToLong ( CaseLastBO::getEngineeringNum ).sum ();
                                trainingNumArea += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTrainingNum ).sum ();
                                todayNumArea += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTodayNum ).sum ();
                                //总合计
                                planNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanNum ).sum ();
                                actualNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualNum ).sum ();
                                unFinishNumSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishNum ).sum ();
                                planTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getPlanTime ).sum ();
                                actualTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getActualTime ).sum ();
                                unFinishTimeSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnFinishTime ).sum ();
                                reportUnFinishSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getReportUnFinish ).sum ();
                                unReportUnFinishSum += caseLastBOS.stream ().mapToDouble ( CaseLastBO::getUnReportUnFinish ).sum ();
                                admininstrationNumSum += caseLastBOS.stream ().mapToLong ( CaseLastBO::getAdmininstrationNum ).sum ();
                                engineeringNumSum += caseLastBOS.stream ().mapToLong ( CaseLastBO::getEngineeringNum ).sum ();
                                trainingNumSum += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTrainingNum ).sum ();
                                todayNumSum += caseLastBOS.stream ().mapToLong ( CaseLastBO::getTodayNum ).sum ();
                            }
                            if (!grandSonBOS.isEmpty ()) {
                                tableBO.setGrandSonS ( grandSonBOS );
                                tableBOS.add ( tableBO );
                            }
                        }
                        if (!tableBOS.isEmpty ()) {
                            caseProBO.setCaseTableBOS ( tableBOS );
                            caseProBOS.add ( caseProBO );
                        }
                    }
                    if (!caseProBOS.isEmpty ()) {
                        caseSonBO.setCaseProBOS ( caseProBOS );
                        caseSonBOS.add ( caseSonBO );
                    }
                    if (!(planNumDepart == 0 && actualNumDepart == 0 && unFinishNumDepart == 0 && planTimeDepart == 0 && actualTimeDepart == 0 && unFinishTimeDepart == 0 && reportUnFinishDepart == 0 && unReportUnFinishDepart == 0)) {
                        CaseSonBO departCount = departCount ( planNumDepart, actualNumDepart, unFinishNumDepart, planTimeDepart, actualTimeDepart, unFinishTimeDepart, reportUnFinishDepart, unReportUnFinishDepart, admininstrationNumDepart, engineeringNumDepart, trainingNumDepart, todayNumDepart );
                        caseSonBOS.add ( departCount );
                    }
                }
            }
            if (!caseSonBOS.isEmpty ()) {
                finishCaseBO.setCaseSonS ( caseSonBOS );
                finishCaseBOS.add ( finishCaseBO );
            }
            if (!(planNumArea == 0 && actualNumArea == 0 && unFinishNumArea == 0 && planTimeArea == 0 && actualTimeArea == 0 && unFinishTimeArea == 0 && reportUnFinishArea == 0 && unReportUnFinishArea == 0)) {
                FinishCaseBO areaCount = sum ( planNumArea, actualNumArea, unFinishNumArea, planTimeArea, actualTimeArea, unFinishTimeArea, reportUnFinishArea, unReportUnFinishArea, admininstrationNumArea, engineeringNumArea, trainingNumArea, todayNumArea, "地区合计" );
                finishCaseBOS.add ( areaCount );
            }
        }
        FinishCaseBO sum = sum ( planNumSum, actualNumSum, unFinishNumSum, planTimeSum, actualTimeSum, unFinishTimeSum, reportUnFinishSum, unReportUnFinishSum, admininstrationNumSum, engineeringNumSum, trainingNumSum, todayNumSum, "总合计" );
        finishCaseBOS.add ( sum );
        return finishCaseBOS;
    }


    //部门合计
    private CaseSonBO departCount(Double planNum, Double actualNum, Double unFinishNum, Double planTime, Double
            actualTime, Double unFinishTime, Double reportUnFinish, Double unReportUnFinish, Long admininstrationNum, Long engineeringNum, Long trainingNum, Long todayNum) throws SerException {
        CaseLastBO lastBO = new CaseLastBO ( planNum, actualNum, unFinishNum, planTime, actualTime, unFinishTime, reportUnFinish, unReportUnFinish, admininstrationNum, engineeringNum, trainingNum, todayNum );
        CaseSonBO depart = new CaseSonBO ();
        depart.setDepart ( "合计" );
        List<CaseTableBO> caseTableS = new ArrayList<> ();
        List<CaseProBO> caseProS = new ArrayList<> ();
        CaseProBO caseProBO = new CaseProBO ();
        CaseTableBO tableBO = new CaseTableBO ();
        List<CaseGrandSonBO> grandSonS = new ArrayList<> ();
        CaseGrandSonBO grandSonBO = new CaseGrandSonBO ();
        List<CaseLastBO> caseLastS = new ArrayList<> ();
        caseLastS.add ( lastBO );
        grandSonBO.setCaseLastS ( caseLastS );
        grandSonS.add ( grandSonBO );
        tableBO.setGrandSonS ( grandSonS );
        caseTableS.add ( tableBO );
        caseProBO.setCaseTableBOS ( caseTableS );
        caseProS.add ( caseProBO );
        depart.setCaseProBOS ( caseProS );
        return depart;
    }

    //合计
    private FinishCaseBO sum(Double planNum, Double actualNum, Double unFinishNum, Double planTime, Double
            actualTime, Double unFinishTime, Double reportUnFinish, Double unReportUnFinish, Long admininstrationNum, Long engineeringNum, Long trainingNum, Long todayNum, String type) throws
            SerException {
        CaseLastBO lastBO = new CaseLastBO ( planNum, actualNum, unFinishNum, planTime, actualTime, unFinishTime, reportUnFinish, unReportUnFinish, admininstrationNum, engineeringNum, trainingNum, todayNum );
        CaseSonBO depart = new CaseSonBO ();
        List<CaseTableBO> caseTableS = new ArrayList<> ();
        CaseTableBO tableBO = new CaseTableBO ();
        List<CaseProBO> caseProS = new ArrayList<> ();
        CaseProBO proBO = new CaseProBO ();
        List<CaseGrandSonBO> grandSonS = new ArrayList<> ();
        CaseGrandSonBO grandSonBO = new CaseGrandSonBO ();
        List<CaseLastBO> caseLastS = new ArrayList<> ();
        caseLastS.add ( lastBO );
        grandSonBO.setCaseLastS ( caseLastS );
        grandSonS.add ( grandSonBO );
        tableBO.setGrandSonS ( grandSonS );
        caseTableS.add ( tableBO );
        proBO.setCaseTableBOS ( caseTableS );
        caseProS.add ( proBO );
        depart.setCaseProBOS ( caseProS );
        List<CaseSonBO> caseSonBOS = new ArrayList<> ();
        caseSonBOS.add ( depart );
        FinishCaseBO finishCaseBO = new FinishCaseBO ();
        if ("地区合计".equals ( type )) {
            finishCaseBO.setArea ( "合计" );
        } else if ("总合计".equals ( type )) {
            finishCaseBO.setArea ( "总合计" );
        }
        finishCaseBO.setCaseSonS ( caseSonBOS );
        return finishCaseBO;
    }

    private CaseLastBO get(List<TaskNode> taskNodes, TaskType taskType, String tableId) throws SerException {
        double planNum = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getPlanNum ).sum ();
        double actualNum = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && null != taskNode.getActualNum () && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualNum ).sum ();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
        CaseLastBO caseLastBO = new CaseLastBO ();
        caseLastBO.setTaskType ( taskType );
        caseLastBO.setPlanNum ( planNum );
        caseLastBO.setActualNum ( actualNum );
        caseLastBO.setUnFinishNum ( unFinishNum );
        caseLastBO.setPlanTime ( planTime );
        caseLastBO.setActualTime ( actualTime );
        caseLastBO.setUnFinishTime ( undoneTime );
        caseLastBO.setReportUnFinish ( reportUnFinish );
        caseLastBO.setUnReportUnFinish ( unReportUnFinish );
        return caseLastBO;
    }

    private CaseLastBO get2(List<TaskNode> taskNodes, String name, String tableId) throws SerException {
        CaseLastBO caseLastBO = new CaseLastBO ();
        if (null != name) {
            double planNum = taskNodes.stream ().filter ( taskNode -> name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getPlanNum ).sum ();
            double actualNum = taskNodes.stream ().filter ( taskNode -> name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualNum ()) ).mapToDouble ( TaskNode::getActualNum ).sum ();
            double unFinishNum = planNum - actualNum;
            double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
            double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
            double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
            double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
            double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
            caseLastBO.setPlanNum ( planNum );
            caseLastBO.setActualNum ( actualNum );
            caseLastBO.setUnFinishNum ( unFinishNum );
            caseLastBO.setPlanTime ( planTime );
            caseLastBO.setActualTime ( actualTime );
            caseLastBO.setUnFinishTime ( undoneTime );
            caseLastBO.setReportUnFinish ( reportUnFinish );
            caseLastBO.setUnReportUnFinish ( unReportUnFinish );
        } else {
            double planNum = taskNodes.stream ().filter ( taskNode -> tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getPlanNum ).sum ();
            double actualNum = taskNodes.stream ().filter ( taskNode -> (null != taskNode.getActualNum ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualNum ).sum ();
            double unFinishNum = planNum - actualNum;
            double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
            double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
            double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
            double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
            double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
            double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
            double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
            caseLastBO.setPlanNum ( planNum );
            caseLastBO.setActualNum ( actualNum );
            caseLastBO.setUnFinishNum ( unFinishNum );
            caseLastBO.setPlanTime ( planTime );
            caseLastBO.setActualTime ( actualTime );
            caseLastBO.setUnFinishTime ( undoneTime );
            caseLastBO.setReportUnFinish ( reportUnFinish );
            caseLastBO.setUnReportUnFinish ( unReportUnFinish );
        }
        return caseLastBO;
    }

    private CaseLastBO get1(List<TaskNode> taskNodes, TaskType taskType, String name, String tableId) throws SerException {
        double planNum = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getPlanNum ).sum ();
        double actualNum = taskNodes.stream ().filter ( taskNode -> taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualNum ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualNum ).sum ();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && taskType.equals ( taskNode.getTaskType () ) && name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualTime ()) && tableId.equals ( taskNode.getTableId () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
        CaseLastBO caseLastBO = new CaseLastBO ();
        caseLastBO.setTaskType ( taskType );
        caseLastBO.setPlanNum ( planNum );
        caseLastBO.setActualNum ( actualNum );
        caseLastBO.setUnFinishNum ( unFinishNum );
        caseLastBO.setPlanTime ( planTime );
        caseLastBO.setActualTime ( actualTime );
        caseLastBO.setUnFinishTime ( undoneTime );
        caseLastBO.setReportUnFinish ( reportUnFinish );
        caseLastBO.setUnReportUnFinish ( unReportUnFinish );
        return caseLastBO;
    }

//    private CaseLastBO finishcounts(List<TaskNode> taskNodes) throws SerException {
//        double planNum = taskNodes.stream ().mapToDouble ( TaskNode::getPlanNum ).sum ();
//        double actualNum = taskNodes.stream ().filter ( taskNode -> (null != taskNode.getActualNum ()) ).mapToDouble ( TaskNode::getActualNum ).sum ();
//        double unFinishNum = planNum - actualNum;
//        double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
//        double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
//        double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
//        double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
//        double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
//        double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
//        double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
//        double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
//        double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
//        double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
//        double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
//        double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
//        double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
//        double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
//        double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
//        long admininstrationNum = taskNodes.stream ().filter ( taskNode -> TaskType.ADMININSTRATION.equals ( taskNode.getTaskType () ) ).count ();
//        long engineeringNum = taskNodes.stream ().filter ( taskNode -> TaskType.ENGINEERING.equals ( taskNode.getTaskType () ) ).count ();
//        long trainingNum = taskNodes.stream ().filter ( taskNode -> TaskType.TRAINING.equals ( taskNode.getTaskType () ) ).count ();
//        long todayNum = admininstrationNum + engineeringNum + trainingNum;  //今日任务
//        CaseLastBO caseLastBO = new CaseLastBO ();
//        caseLastBO.setPlanNum ( planNum );
//        caseLastBO.setActualNum ( actualNum );
//        caseLastBO.setUnFinishNum ( unFinishNum );
//        caseLastBO.setPlanTime ( planTime );
//        caseLastBO.setActualTime ( actualTime );
//        caseLastBO.setUnFinishTime ( undoneTime );
//        caseLastBO.setReportUnFinish ( reportUnFinish );
//        caseLastBO.setUnReportUnFinish ( unReportUnFinish );
//        caseLastBO.setAdmininstrationNum ( admininstrationNum );
//        caseLastBO.setEngineeringNum ( engineeringNum );
//        caseLastBO.setTrainingNum ( trainingNum );
//        caseLastBO.setTodayNum ( todayNum );
//        return caseLastBO;
//    }

    private CaseLastBO finishcounts(List<TaskNode> taskNodes) throws SerException {
        double planNum = taskNodes.stream ().mapToDouble ( TaskNode::getPlanNum ).sum ();
        double actualNum = taskNodes.stream ().filter ( taskNode -> (null != taskNode.getActualNum ()) ).mapToDouble ( TaskNode::getActualNum ).sum ();
        double unFinishNum = planNum - actualNum;
        double planMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
        double planTime = new BigDecimal ( planMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + planDay * 8 + planHour;     //计划总工时
        double actualMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && FinishStatus.FINISH.equals ( taskNode.getFinishStatus () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;     //已完成工时
        double undoneMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getUndoneType () ) ).mapToDouble ( TaskNode::getUndoneTime ).sum ();
        double undoneTime = new BigDecimal ( undoneMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + undoneDay * 8 + undoneHour;   //未完成工时
        double reportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getDelayType () ) && taskNode.getDelay () ).mapToDouble ( TaskNode::getDelayTime ).sum ();
        double reportUnFinish = new BigDecimal ( reportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + reportUnFinishDay * 8 + reportUnFinishHour;   //上报未完成工时
        double unReportUnFinishMin = taskNodes.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishHour = taskNodes.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinishDay = taskNodes.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getActualType () ) && (null != taskNode.getReport ()) && (!taskNode.getReport ()) && (null != taskNode.getActualTime ()) ).mapToDouble ( TaskNode::getActualTime ).sum ();
        double unReportUnFinish = new BigDecimal ( unReportUnFinishMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + unReportUnFinishDay * 8 + unReportUnFinishHour;  //未上报未完成工时
        long admininstrationNum = taskNodes.stream ().filter ( taskNode -> TaskType.ADMININSTRATION.equals ( taskNode.getTaskType () ) ).count ();
        long engineeringNum = taskNodes.stream ().filter ( taskNode -> TaskType.ENGINEERING.equals ( taskNode.getTaskType () ) ).count ();
        long trainingNum = taskNodes.stream ().filter ( taskNode -> TaskType.TRAINING.equals ( taskNode.getTaskType () ) ).count ();
        long todayNum = admininstrationNum + engineeringNum + trainingNum;  //今日任务
        CaseLastBO caseLastBO = new CaseLastBO ();
        caseLastBO.setPlanNum ( planNum );
        caseLastBO.setActualNum ( actualNum );
        caseLastBO.setUnFinishNum ( unFinishNum );
        caseLastBO.setPlanTime ( planTime );
        caseLastBO.setActualTime ( actualTime );
        caseLastBO.setUnFinishTime ( undoneTime );
        caseLastBO.setReportUnFinish ( reportUnFinish );
        caseLastBO.setUnReportUnFinish ( unReportUnFinish );
        caseLastBO.setAdmininstrationNum ( admininstrationNum );
        caseLastBO.setEngineeringNum ( engineeringNum );
        caseLastBO.setTrainingNum ( trainingNum );
        caseLastBO.setTodayNum ( todayNum );
        return caseLastBO;
    }

    private List<TaskType> taskTypes() throws SerException {
        List<TaskType> list = new ArrayList<> ();
        list.add ( TaskType.ADMININSTRATION );
        list.add ( TaskType.ENGINEERING );
        list.add ( TaskType.TRAINING );
        return list;
    }

    @Override
    public List<String> areas() throws SerException {
        List<Project> list = projectSer.findAll ();
        Set<String> s = list.stream ().map ( project -> project.getArea () ).collect ( Collectors.toSet () );
        return new ArrayList<> ( s );
    }

    @Override
    public List<String> departs() throws SerException {
        List<Project> list = projectSer.findAll ();
        Set<String> s = list.stream ().map ( project -> project.getDepart () ).collect ( Collectors.toSet () );
        return new ArrayList<> ( s );
    }

//    private List<String> projects() throws SerException {
//        List<Project> list = projectSer.findAll ();
//        Set<String> s = list.stream ().map ( project -> project.getProject () ).collect ( Collectors.toSet () );
//        return new ArrayList<> ( s );
//    }

    private List<String> projects() throws SerException {
        List<Project> list = projectSer.findAll ();
        Set<String> s = list.stream ().map ( project -> project.getProject () ).collect ( Collectors.toSet () );
        return new ArrayList<> ( s );
    }

    @Override
    public void confirm(String id) throws SerException {
        TaskNode entity = super.findById ( id );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setConfirm ( true );
        entity.setTaskStatus ( TaskStatus.DOING );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        String eventId = eventAPI.findId ( entity.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
        EventTO eventTO = new EventTO ();
        eventTO.setName ( entity.getExecute () );
        eventTO.setProjectChineseName ( "任务分配" );
        eventTO.setProjectEnglishName ( "taskallotment" );
        eventTO.setFunctionChineseName ( "任务分配" );
        eventTO.setFunctionEnglishName ( "toaskallotment" );
        eventTO.setContent ( "执行任务中" );
        eventTO.setPermissions ( Permissions.MAKE );
        eventTO.setEventId ( entity.getId () );
        eventTO.setStatus ( "待制作" );
        eventAPI.save ( eventTO );
    }

    @Override
    public void unConfirm(TaskNodeTO to) throws SerException {
        TaskNode entity = super.findById ( to.getId () );
        if (entity == null) {
            throw new SerException ( "该对象不存在" );
        }
        entity.setConfirm ( false );
        entity.setReason ( to.getReason () );
        entity.setTaskStatus ( TaskStatus.NOTRECEIVE );
        entity.setModifyTime ( LocalDateTime.now () );
        super.update ( entity );
        String eventId = eventAPI.findId ( entity.getId (), userAPI.currentUser ().getUsername () );
        if (null != eventId) {
            eventAPI.delete ( eventId );
        }
    }

    @Override
    public List<DaysBO> dayReport(String time, String[] names) throws SerException {
        LocalDate date = DateUtil.parseDate ( time );
        List<DaysBO> bos = new ArrayList<> ();
        for (String string : names) {
            String depart = null;
            String position = null;
            Map<String, String> map = positionUserDetailAPI.departPosition ( string );
            Set<String> set = map.keySet ();
            for (String s : set) {
                depart = s;
                position = map.get ( s );
            }
            TaskNodeDTO dto = new TaskNodeDTO ();  //当天
            dto.getConditions ().add ( Restrict.eq ( "execute", string ) );
            dto.getConditions ().add ( Restrict.between ( "startTime", new LocalDate[]{date, date} ) );
            List<TaskNode> currents = super.findByCis ( dto );
            TaskNodeDTO next = new TaskNodeDTO ();    //明天
            next.getConditions ().add ( Restrict.eq ( "execute", string ) );
            next.getConditions ().add ( Restrict.between ( "startTime", new LocalDate[]{date.plusDays ( 1 ), date.plusDays ( 1 )} ) );
            List<TaskNode> nexts = super.findByCis ( next );
            for (TaskNode current : currents) {
                String name = current.getExecute ();
                DaysBO bo = BeanTransform.copyProperties ( current, DaysBO.class );
                bo.setName ( name );
                bo.setTime ( time );
                double taskTime = time ( current.getNeedTime (), current.getNeedType () );
                bo.setTaskTime ( taskTime );
                bo.setDepart ( depart );
                bo.setPosition ( position );
                bos.add ( bo );
            }
            for (int i = 0; i < bos.size (); i++) {
                if (i < nexts.size ()) {
                    TaskNode taskNode = nexts.get ( i );
                    DaysBO bo = bos.get ( i );
                    bo.setPlan ( taskNode.getContent () );
                    bo.setPlanTime ( time ( taskNode.getNeedTime (), taskNode.getNeedType () ) );
                }
            }
            if (nexts.size () > currents.size ()) {    //明天任务条数比当天多的情况
                int i = currents.size ();
                while (i < nexts.size ()) {
                    DaysBO bo = new DaysBO ();
                    bo.setTime ( time );
                    bo.setName ( string );
                    bo.setDepart ( depart );
                    bo.setPosition ( position );
                    TaskNode taskNode = nexts.get ( i );
                    bo.setPlan ( taskNode.getContent () );
                    bo.setPlanTime ( time ( taskNode.getNeedTime (), taskNode.getNeedType () ) );
                    bos.add ( bo );
                    i++;
                }
            }
        }
        return bos;
    }

    private Double time(double time, TimeType type) {
        switch (type) {
            case MINUTE:
                time = new BigDecimal ( time / 60 ).setScale ( 4, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                break;
            case DAY:
                time = time * 8;
                break;
        }
        return time;
    }

    @Override
    public DayReportCountBO dayCount(String startTime, String endTime, String[] departIds) throws SerException {
        List<DayCountBO> dayCountBOS = get ( startTime, endTime, departIds );
        Set<String> areas = dayCountBOS.stream ().filter ( dayCountBO -> null != dayCountBO.getArea () ).map ( DayCountBO::getArea ).collect ( Collectors.toSet () );
        DayReportCountBO dayReportCountBO = new DayReportCountBO ();
        dayReportCountBO.setTime ( startTime + "-" + endTime );
        List<DayABO> abos = new ArrayList<> ();
        int sumNum = 0;   //总人数
        double sumPlanNum = 0;   //计划任务量总合计
        double sumActualNum = 0;   //完成任务量总合计
        double sumTaskTime = 0;   //任务时长总合计
        double sumActualTime = 0;   //实际时长总合计
        double sumOutTime = 0;   //加班时长总合计
        for (String area : areas) {
            DayABO abo = new DayABO ();
            abo.setArea ( area );
            List<DayBBO> bbos = new ArrayList<> ();
            Set<String> departs = dayCountBOS.stream ().filter ( dayCountBO -> (null != dayCountBO.getDepart ()) && area.equals ( dayCountBO.getArea () ) ).map ( DayCountBO::getDepart ).collect ( Collectors.toSet () );
            for (String depart : departs) {
                int departNum = 0;   //部门总人数
                double departPlanNum = 0;   //计划任务量部门合计
                double departActualNum = 0;   //完成任务量部门合计
                double departTaskTime = 0;   //任务时长部门合计
                double departActualTime = 0;   //实际时长部门合计
                double departOutTime = 0;   //加班时长部门合计
                DayBBO bbo = new DayBBO ();
                bbo.setDepart ( depart );
                List<DayCBO> cbos = new ArrayList<> ();
                Set<String> positions = dayCountBOS.stream ().filter ( dayCountBO -> (null != dayCountBO.getPosition ()) && depart.equals ( dayCountBO.getDepart () ) ).map ( DayCountBO::getPosition ).collect ( Collectors.toSet () );
                for (String position : positions) {
                    DayCBO cbo = new DayCBO ();
                    cbo.setPosition ( position );
                    List<DayDBO> dbos = new ArrayList<> ();
                    Set<String> names = dayCountBOS.stream ().filter ( dayCountBO -> (null != dayCountBO.getName ()) && position.equals ( dayCountBO.getPosition () ) ).map ( DayCountBO::getName ).collect ( Collectors.toSet () );
                    for (String name : names) {
                        List<DayCountBO> list = dayCountBOS.stream ().filter ( dayCountBO -> name.equals ( dayCountBO.getName () ) ).collect ( Collectors.toList () );
                        if (!list.isEmpty ()) {
                            DayDBO dbo = BeanTransform.copyProperties ( list.get ( 0 ), DayDBO.class );
                            sumNum++;
                            departNum++;
                            departPlanNum += dbo.getPlanNum ();
                            sumPlanNum += dbo.getPlanNum ();
                            departActualNum += dbo.getActualNum ();
                            sumActualNum += dbo.getActualNum ();
                            departTaskTime += dbo.getTaskTime ();
                            sumTaskTime += dbo.getTaskTime ();
                            departActualTime += dbo.getActualTime ();
                            sumActualTime += dbo.getActualTime ();
                            departOutTime += dbo.getOutTime ();
                            sumOutTime += dbo.getOutTime ();
                            dbos.add ( dbo );
                        }
                    }
                    cbo.setSons ( dbos );
                    cbos.add ( cbo );
                }
                bbo.setSons ( cbos );
                bbos.add ( bbo );
                //部门合计
                bbos.add ( count ( departNum, departPlanNum, departActualNum, departTaskTime, departActualTime, departOutTime, "合计" ) );
            }
            abo.setSons ( bbos );
            abos.add ( abo );
        }
        DayABO count = new DayABO ();
        List<DayBBO> bbos = new ArrayList<> ();
        //总合计
        DayBBO bbo = count ( sumNum, sumPlanNum, sumActualNum, sumTaskTime, sumActualTime, sumOutTime, "总合计" );
        bbos.add ( bbo );
        count.setSons ( bbos );
        abos.add ( count );
        dayReportCountBO.setSons ( abos );
        return dayReportCountBO;
    }

    @Override
    public List<DayReportMailBO> dayCountMail(String startTime, String endTime, String[] departIds) throws
            SerException {
        List<DayCountBO> dayCountBOS = get ( startTime, endTime, departIds );
        Set<String> departs = dayCountBOS.stream ().filter ( dayCountBO -> null != dayCountBO.getDepart () ).map ( DayCountBO::getDepart ).collect ( Collectors.toSet () );
        DayReportCountBO dayReportCountBO = new DayReportCountBO ();
        dayReportCountBO.setTime ( startTime + "-" + endTime );
        List<DayReportMailBO> bos = new ArrayList<> ();
        int sumNum = 0;   //总人数
        double sumPlanNum = 0;   //计划任务量总合计
        double sumActualNum = 0;   //完成任务量总合计
        double sumTaskTime = 0;   //任务时长总合计
        double sumActualTime = 0;   //实际时长总合计
        double sumOutTime = 0;   //加班时长总合计
        for (String depart : departs) {
            int departNum = 0;   //部门总人数
            double departPlanNum = 0;   //计划任务量部门合计
            double departActualNum = 0;   //完成任务量部门合计
            double departTaskTime = 0;   //任务时长部门合计
            double departActualTime = 0;   //实际时长部门合计
            double departOutTime = 0;   //加班时长部门合计
            DayReportMailBO bo = new DayReportMailBO ();
            bo.setDepart ( depart );
            List<DayDBO> sons = new ArrayList<> ();
            Set<String> names = dayCountBOS.stream ().filter ( dayCountBO -> (null != dayCountBO.getName ()) && depart.equals ( dayCountBO.getDepart () ) ).map ( DayCountBO::getName ).collect ( Collectors.toSet () );
            for (String name : names) {
                List<DayCountBO> list = dayCountBOS.stream ().filter ( dayCountBO -> name.equals ( dayCountBO.getName () ) ).collect ( Collectors.toList () );
                if (!list.isEmpty ()) {
                    DayDBO dbo = BeanTransform.copyProperties ( list.get ( 0 ), DayDBO.class );
                    sumNum++;
                    departNum++;
                    departPlanNum += dbo.getPlanNum ();
                    sumPlanNum += dbo.getPlanNum ();
                    departActualNum += dbo.getActualNum ();
                    sumActualNum += dbo.getActualNum ();
                    departTaskTime += dbo.getTaskTime ();
                    sumTaskTime += dbo.getTaskTime ();
                    departActualTime += dbo.getActualTime ();
                    sumActualTime += dbo.getActualTime ();
                    departOutTime += dbo.getOutTime ();
                    sumOutTime += dbo.getOutTime ();
                    sons.add ( dbo );
                }
            }
            bo.setSons ( sons );
            bos.add ( bo );
            DayReportMailBO count = new DayReportMailBO ();
            count.setDepart ( "合计" );
            List<DayDBO> sons1 = new ArrayList<> ();
            DayDBO dbo = new DayDBO ( departNum + "", departPlanNum, departActualNum, departTaskTime, departActualTime, departOutTime );
            sons1.add ( dbo );
            count.setSons ( sons1 );
            bos.add ( count );
        }
        DayReportMailBO count = new DayReportMailBO ();
        count.setDepart ( "总合计" );
        List<DayDBO> sons1 = new ArrayList<> ();
        DayDBO dbo = new DayDBO ( sumNum + "", sumPlanNum, sumActualNum, sumTaskTime, sumActualTime, sumOutTime );
        sons1.add ( dbo );
        count.setSons ( sons1 );
        bos.add ( count );
        return bos;
    }

    private DayBBO count(int num, double planNum, double actualNum, double taskTime, double actualTime,
                         double outTime, String type) throws SerException {
        DayBBO count = new DayBBO ();
        List<DayCBO> cbos = new ArrayList<> ();
        if ("总合计".equals ( type )) {
            count.setDepart ( "总合计" );
        } else {
            count.setDepart ( "合计" );
        }
        DayCBO cbo = new DayCBO ();
        List<DayDBO> dbos = new ArrayList<> ();
        DayDBO dbo = new DayDBO ();
        dbo.setName ( num + "人" );
        dbo.setPlanNum ( planNum );
        dbo.setActualNum ( actualNum );
        dbo.setTaskTime ( taskTime );
        dbo.setActualTime ( actualTime );
        dbo.setOutTime ( outTime );
        dbos.add ( dbo );
        cbo.setSons ( dbos );
        cbos.add ( cbo );
        count.setSons ( cbos );
        return count;
    }

    private List<DayCountBO> get(String startTime, String endTime, String[] departIds) throws SerException {
        List<DayCountBO> dayCountBOS = new ArrayList<> ();
        StringBuilder sb = new StringBuilder ();
        sb.append ( "SELECT DISTINCT id" +
                "        FROM taskallotment_tasknode" +
                "        WHERE " );
        if (null != departIds) {
            Set<String> set = new HashSet<> ();
            for (String s : departIds) {
                Set<String> names = departmentDetailAPI.departPersons ( s );   //查找某个部门的所有人
                if (null != names) {
                    set.addAll ( names );
                }
            }
            String[] executes = new String[set.size ()];
            int i = 0;
            for (String s : set) {
                executes[i] = "'" + s + "'";
                i++;
            }
            String executeStr = StringUtils.join ( executes, "," );
            if (executes.length > 0) {
                sb.append ( "execute in (" + executeStr + ") AND " );
            }
        }
        sb.append ( "(DATE_FORMAT(startTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "')  and (DATE_FORMAT(endTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') and haveSon is null" );
        List<TaskNode> ids = super.findBySql ( sb.toString (), TaskNode.class, new String[]{"id"} );
        List<TaskNode> list = new ArrayList<> ();
        if (null != ids) {
            for (TaskNode t : ids) {
                list.add ( super.findById ( t.getId () ) );
            }
        }
        Set<String> names = list.stream ().map ( TaskNode::getExecute ).collect ( Collectors.toSet () );
        for (String name : names) {
            Stream<TaskNode> stream = list.stream ().filter ( taskNode -> name.equals ( taskNode.getExecute () ) );
            //计划任务量
            double planNum = stream.mapToDouble ( TaskNode::getPlanNum ).sum ();
            //完成任务量
            double actualNum = list.stream ().filter ( taskNode -> name.equals ( taskNode.getExecute () ) && (null != taskNode.getActualNum ()) ).mapToDouble ( TaskNode::getActualNum ).sum ();
            double needMin = list.stream ().filter ( taskNode -> TimeType.MINUTE.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double needHour = list.stream ().filter ( taskNode -> TimeType.HOUR.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            double needDay = list.stream ().filter ( taskNode -> TimeType.DAY.equals ( taskNode.getNeedType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getNeedTime ).sum ();
            //任务时长(小时)
            double taskTime = new BigDecimal ( needMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + needDay * 8 + needHour;
            double actualMin = list.stream ().filter ( taskNode -> (null != taskNode.getActualType ()) && TimeType.MINUTE.equals ( taskNode.getActualType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualHour = list.stream ().filter ( taskNode -> (null != taskNode.getActualType ()) && TimeType.HOUR.equals ( taskNode.getActualType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            double actualDay = list.stream ().filter ( taskNode -> (null != taskNode.getActualType ()) && TimeType.DAY.equals ( taskNode.getActualType () ) && name.equals ( taskNode.getExecute () ) ).mapToDouble ( TaskNode::getActualTime ).sum ();
            //实际时长(小时)
            double actualTime = new BigDecimal ( actualMin / 60 ).setScale ( 2, BigDecimal.ROUND_HALF_UP ).doubleValue () + actualDay * 8 + actualHour;
            //加班时长(小时)
            double outTime = outTime ( name, startTime, endTime );
            PositionDetailBO positionDetailBO = positionUserDetailAPI.getPosition ( name );
            DayCountBO dayCountBO = new DayCountBO ();
            if (null != positionDetailBO) {
                dayCountBO.setArea ( positionDetailBO.getArea () );
                dayCountBO.setDepart ( positionDetailBO.getDepartmentName () );
                dayCountBO.setPosition ( positionDetailBO.getPosition () );
            }
            dayCountBO.setOutTime ( outTime );
            dayCountBO.setName ( name );
            dayCountBO.setPlanNum ( planNum );
            dayCountBO.setActualNum ( actualNum );
            dayCountBO.setTaskTime ( taskTime );
            dayCountBO.setActualTime ( actualTime );
            dayCountBOS.add ( dayCountBO );
        }
        return dayCountBOS;
    }

    //查询某人某段时间的加班时长，注意，考勤数据库与任务分配数据库必须为同一个，不考虑分库
    private Double outTime(String name, String startTime, String endTime) throws SerException {
        String sql = "SELECT sum(overLong) time " +
                "FROM attendance_overwork " +
                "WHERE overWorker = '" + name + "' AND " +
                "      (DATE_FORMAT(overStartTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "') OR " +
                "      (DATE_FORMAT(overEndTime, '%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "')";
        List<ObjectBO> list = super.findBySql ( sql, ObjectBO.class, new String[]{"time"} );
        if (null != list) {
            return list.stream ().filter ( objectBO -> null != objectBO.getTime () ).mapToDouble ( ObjectBO::getTime ).sum ();
        }
        return 0d;
    }

    @Override
    public Double finishDay(String date, String name) throws SerException {
        String sql = "SELECT" +
                "  actualTime," +
                "  actualType " +
                "FROM taskallotment_tasknode " +
                "WHERE '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d') AND taskStatus = 0 AND execute='" + name + "' and haveSon is null";
        String[] fileds = new String[]{"actualTime", "actualType"};
        List<ObjectBO> list = super.findBySql ( sql, ObjectBO.class, fileds );
        double finishDay = 0;   //当天的任务完成天数
        if (null != list) {
            for (ObjectBO o : list) {
                double time = o.getActualTime ();
                int timeType = o.getActualType ();
                switch (timeType) {
                    case 0:
                        time = new BigDecimal ( time / 60 / 8 ).setScale ( 4, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                        break;
                    case 1:
                        time = new BigDecimal ( time / 8 ).setScale ( 4, BigDecimal.ROUND_HALF_UP ).doubleValue ();
                        break;
                }
                finishDay += time;
            }
        }
        return finishDay;
    }

    @Override
    public List<ObjectBO> taskSituation(String[] names, String date) throws SerException {
        String sql = "SELECT undoneTime,undoneType,actualType,actualTime,taskStatus FROM taskallotment_tasknode " +
                "WHERE execute in ('" + StringUtils.join ( names, "','" ) + "') AND is_confirm=1 AND '" + date + "' BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d') and haveSon is null";
        String[] fileds = new String[]{"undoneTime", "undoneType", "actualType", "actualTime", "taskStatus"};
        List<ObjectBO> list = super.findBySql ( sql, ObjectBO.class, fileds );
        return list;
    }

    @Override
    public byte[] exportExcel(TaskNodeDTO dto) throws SerException {
        dto.getConditions ().add ( Restrict.eq ( "tableId", dto.getTableId () ) );
        dto.getConditions ().add ( Restrict.in ( "taskName", dto.getTaskNames () ) );
        List<TaskNode> list = super.findByCis ( dto );
        List<TaskNodeExcel> toList = new ArrayList<> ();
        for (TaskNode taskNode : list) {
            TaskNodeExcel excel = new TaskNodeExcel ();
            BeanUtils.copyProperties ( taskNode, excel );
            toList.add ( excel );
        }
        Excel excel = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( toList, excel );
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<TaskNodeLeadTO> toList, String tableId) throws SerException {
        List<TaskNode> list = BeanTransform.copyProperties ( toList, TaskNode.class, true );
        for (TaskNode taskNode : list) {
            taskNode.setTableId ( tableId );
            super.save ( taskNode );
            if (null != taskNode.getSplit () && taskNode.getSplit ()) {
                split ( taskNode );
            }
        }
    }

    @Override
    public void leadWholeTableExcel(List<WholeTaskLeadTO> toList, String projectId) throws SerException {
        if (StringUtils.isBlank ( projectId )) {
            throw new SerException ( "项目表id不能为空" );
        }
        List<TaskNode> list = new ArrayList<> ();
        String tableName = "";
        String tableId = "";
        String temp_tableId = "";
        Table table = new Table ();
        for (WholeTaskLeadTO wholeTask : toList) {
            if (!tableName.equals ( wholeTask.getName () )) {
                table.setCreater ( wholeTask.getCreater () );
                table.setName ( wholeTask.getName () );
                table.setStatus ( Status.START );
//                table.setFinishStatus(FinishStatus.UNFINISHED);
                table.setProjectId ( projectId );
                table = tableSer.save ( table );

                tableName = wholeTask.getName ();
                temp_tableId = table.getId ();
                tableId = table.getId ();
            } else {
                tableId = temp_tableId;
            }

            TaskNode taskNode = BeanTransform.copyProperties ( wholeTask, TaskNode.class, true, "name", "status", "creater" );
            taskNode.setTableId ( tableId );
            System.out.println ( tableId );
            list.add ( taskNode );
            table = new Table ();
        }
        super.save ( list );
    }

    @Override
    public byte[] wholeExportExcel(String projectId) throws SerException {
        List<WholeTaskExportExcel> exportExcelList = new ArrayList<> ();

        if (StringUtils.isBlank ( projectId )) {
            throw new SerException ( "项目id不能为空" );
        }
        Project project = projectSer.findById ( projectId );
        if (null == project) {
            throw new SerException ( "该项目id不存在" );
        }

        TableDTO tableDTO = new TableDTO ();
        tableDTO.getSorts ().add ( "createTime=desc" );
        tableDTO.getConditions ().add ( Restrict.eq ( "projectId", projectId ) );
        List<Table> tableList = tableSer.findByCis ( tableDTO );
        if (tableList == null) {
            exportExcelList.add ( new WholeTaskExportExcel () );

        } else {
            for (Table table : tableList) {
                TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
                taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", table.getId () ) );
                List<TaskNode> taskNodeList = super.findByCis ( taskNodeDTO );
                if (taskNodeList != null) {
                    List<WholeTaskExportExcel> temp = new ArrayList<> ();
                    for (TaskNode nodeTask : taskNodeList) {
                        WholeTaskExportExcel wholeTask = BeanTransform.copyProperties ( nodeTask, WholeTaskExportExcel.class, "planTime", "startTime", "endTime" );
                        wholeTask.setPlanTime ( DateUtil.dateToString ( nodeTask.getPlanTime () ).replaceAll ( "T", " " ) );
                        if (null != nodeTask.getStartTime ()) {
                            wholeTask.setStartTime ( nodeTask.getStartTime () );
                        }
                        if (null != wholeTask.getEndTime ()) {
                            wholeTask.setEndTime ( nodeTask.getEndTime () );
                        }
                        wholeTask.setTableId ( table.getId () );
                        wholeTask.setStatus ( table.getStatus () );
                        wholeTask.setCreater ( table.getCreater () );
                        wholeTask.setName ( table.getName () );

                        temp.add ( wholeTask );
                    }
                    exportExcelList.addAll ( temp );
                }
            }

        }
        Excel excel = new Excel ( 0, 2 );
        XSSFWorkbook wb = new XSSFWorkbook ();
        XSSFSheet sheet = wb.createSheet ( excel.getSheetName () );
        XSSFCellStyle contentStyle = ExcelUtil.getStyle ( wb, excel.getContentBGColor () );
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD;//Excel表头
        XSSFRow row = sheet.createRow ( 0 );//创建第一行
        row.setHeight ( (short) 400 );//设置第一行单元格的高度

        for (int i = 0, length = excelHeader.length; i < length; i++) {
            XSSFCell cell = row.createCell ( i );
            if (i == 0 || i == 4 || i == 8) {
                sheet.setColumnWidth ( i, 2000 ); //设置单元格的宽
            } else if (i == 18) {
                sheet.setColumnWidth ( i, 5000 );
            } else {
                sheet.setColumnWidth ( i, 4000 );
            }
            cell.setCellValue ( excelHeader[i] );//设置单元格的值
            cell.setCellStyle ( ExcelUtil.getStyle ( wb, excel.getHeaderBGColor () ) );   //设置样式
        }

        if (exportExcelList != null && exportExcelList.size () > 0) {
            createRowDetail ( exportExcelList, row, sheet );//填充数据

        }

        ByteArrayOutputStream os = new ByteArrayOutputStream ();
        try {
            wb.write ( os );
        } catch (IOException e) {
            throw new RuntimeException ( e.getMessage () );
        }

        return os.toByteArray ();


    }

    /**
     * @param list  整体任务数据信息集合
     * @param row
     * @param sheet Excel表单
     * @description 创建数据行
     */
    public void createRowDetail(List<WholeTaskExportExcel> list, XSSFRow row, XSSFSheet sheet) {
        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        String tableId = list.get ( 0 ).getTableId ();
        if (list != null && list.size () > 0) {
            firstRow = 1;
            lastRow = 1;
            firstCol = 0;
            lastCol = 0;
        }
        int index = 0;
        int showFlag = 0;
        for (WholeTaskExportExcel exportEntity : list) {
            lastRow = index;

            if (!exportEntity.getTableId ().equals ( tableId ) && firstRow == lastRow) {
                //不合并
                firstRow++;
                showFlag = 1;
            } else if (exportEntity.getTableId ().equals ( tableId ) && firstRow == lastRow) {
                //合并
                if (index != 0 && index < list.size () - 1 && !tableId.equals ( list.get ( index + 1 ).getTableId () )) {
//                    sheet = assiableMergeData(sheet, firstRow - showFlag, lastRow);
                    sheet = assiableMergeData ( sheet, lastRow + 1 - showFlag, lastRow + 1 );

                }
                if (index == list.size () - 1) {
                    sheet = assiableMergeData ( sheet, lastRow + 1 - showFlag, lastRow + 1 );
                }
                firstRow++;
                tableId = exportEntity.getTableId ();
            }

            if (exportEntity.getTableId ().equals ( tableId )) {
                showFlag++;
            } else if (!exportEntity.getTableId ().equals ( tableId )) {
                tableId = exportEntity.getTableId ();
            }

            ++index;
            row = sheet.createRow ( index );    // 每循环一次创建一行
            int callIndex = 0;
//            row.createCell(callIndex++).setCellValue(index);//设置行的索引
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getName () );
            row.createCell ( callIndex++ ).setCellValue ( Status.START.equals ( exportEntity.getStatus () ) ? "启用" : "禁用" );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getCreater () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getInitiate () == null ? "" : exportEntity.getInitiate () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getTaskName () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getCharge () == null ? "" : exportEntity.getCharge () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getExecute () == null ? "" : exportEntity.getExecute () );
            row.createCell ( callIndex++ ).setCellValue ( "" + exportEntity.getPlanTime () );
            row.createCell ( callIndex++ ).setCellValue ( TaskType.ADMININSTRATION.equals ( exportEntity.getTaskType () ) ? "行政任务" : TaskType.ENGINEERING.equals ( exportEntity.getTaskType () ) ? "工程任务" : "培训任务" );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getType () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getContent () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getPlanNum () );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getActualNum () == null ? "" : exportEntity.getActualNum () + "" );
            row.createCell ( callIndex++ ).setCellValue ( time ( exportEntity.getNeedTime (), exportEntity.getNeedType () ) );
            row.createCell ( callIndex++ ).setCellValue ( TimeType.DAY.equals ( exportEntity.getNeedType () ) ? "天" : TimeType.HOUR.equals ( exportEntity.getNeedType () ) ? "小时" : "分钟" );
            row.createCell ( callIndex++ ).setCellValue ( null == exportEntity.getStartTime () ? "" : DateUtil.dateToString ( exportEntity.getStartTime () ) );
            row.createCell ( callIndex++ ).setCellValue ( null == exportEntity.getEndTime () ? "" : DateUtil.dateToString ( exportEntity.getStartTime () ) );
            row.createCell ( callIndex++ ).setCellValue ( exportEntity.getRemark () == null ? "" : exportEntity.getExecute () );
        }

    }

    private XSSFSheet assiableMergeData(XSSFSheet sheet, int firstRow, int lastRow) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress ( firstRow, lastRow, 0, 0 );
        sheet.addMergedRegion ( cellRangeAddress );
        cellRangeAddress = new CellRangeAddress ( firstRow, lastRow, 1, 1 );
        sheet.addMergedRegion ( cellRangeAddress );
        cellRangeAddress = new CellRangeAddress ( firstRow, lastRow, 2, 2 );
        sheet.addMergedRegion ( cellRangeAddress );
        cellRangeAddress = new CellRangeAddress ( firstRow, lastRow, 3, 3 );
        sheet.addMergedRegion ( cellRangeAddress );
        return sheet;
    }

    public static final String[] EXCELHEAD = {"表名称", "状态", "创建人", "发起人",
            "任务名称", "负责人", "执行人", "计划执行时间", "任务类型", "类型", "任务内容", "计划任务量", "完成任务量", "所需时长", "所需时长时间类型",
            "任务开始时间", "任务结束时间", "备注"};

    @Override
    public List<String> taskNames(String tableID) throws SerException {
        TaskNodeDTO dto = new TaskNodeDTO ();
        dto.getConditions ().add ( Restrict.eq ( "tableId", tableID ) );
        return super.findByCis ( dto ).stream ().map ( TaskNode::getTaskName ).distinct ().collect ( Collectors.toList () );
    }

    @Override
    public CollectDataVO personProjectCollect(CollectDataTO collectDataTO) throws SerException {
        TimeStatus timeStatus = collectDataTO.getTimeStatus ();
        String userName = collectDataTO.getUserName ();
        if (StringUtils.isBlank ( userName )) {
            throw new SerException ( "用户名不能为空" );
        }
        if (null == timeStatus) {
            throw new SerException ( "时间汇总类型不能为空" );
        }

        //TODO 根据时间类型计算时间
        LocalDateTime start = LocalDateTime.now ();
        LocalDateTime end = LocalDateTime.now ();
        List<String> tableId = new ArrayList<> ();
        switch (collectDataTO.getTimeStatus ()) {
            case YEAR:
                if (StringUtils.isBlank ( "" + collectDataTO.getYear () ) || collectDataTO.getYear () < 1900) {
                    throw new SerException ( "年份不能为空且请输入正确年份" );
                }
                start = DateUtil.parseDateTime ( collectDataTO.getYear () + "-01-01 00:00:01" );
                end = DateUtil.parseDateTime ( collectDataTO.getYear () + "-12-31 12:59:59" );

                break;
            case MONTH:
                if (StringUtils.isBlank ( "" + collectDataTO.getYear () ) || collectDataTO.getYear () < 1900
                        || StringUtils.isBlank ( "" + collectDataTO.getMonth () ) || collectDataTO.getMonth () < 1 || collectDataTO.getMonth () > 12) {
                    throw new SerException ( "年份或月份不能为空且请输入正确年份和月份" );
                }
                start = LocalDateTime.of ( collectDataTO.getYear (), collectDataTO.getMonth (), 1, 0, 0, 1 );
                end = start.with ( TemporalAdjusters.lastDayOfMonth () );
                break;
            case WEEK:
                if (StringUtils.isBlank ( "" + collectDataTO.getYear () ) || collectDataTO.getYear () < 1900
                        || StringUtils.isBlank ( "" + collectDataTO.getMonth () ) || collectDataTO.getMonth () < 1 || collectDataTO.getMonth () > 12
                        || StringUtils.isBlank ( "" + collectDataTO.getWeek () )) {
                    throw new SerException ( "年份或月份或周数不能为空" );
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes ( collectDataTO.getYear (), collectDataTO.getMonth (), collectDataTO.getWeek () );
                start = LocalDateTime.of ( dateDuring[0].getYear (), dateDuring[0].getMonth (), dateDuring[0].getDayOfMonth (), 0, 0, 1 );
                end = LocalDateTime.of ( dateDuring[1].getYear (), dateDuring[1].getMonth (), dateDuring[1].getDayOfMonth (), 12, 59, 59 );
                break;
            default:
                return null;
        }


        //获取当前用户该时间段内参与的任务
        TaskNodeDTO dto = new TaskNodeDTO ();
        dto.getConditions ().add ( Restrict.eq ( "execute", userName ) );
        dto.getConditions ().add ( Restrict.isNull ( "haveSon" ) );
        dto.getConditions ().add ( Restrict.between ( "planTime", new LocalDateTime[]{start, end} ) );
        //没有父节点
        dto.getConditions ().add ( Restrict.isNull ( "fatherId" ) );
        List<TaskNode> listNoFather = super.findByCis ( dto );
        if (listNoFather != null && listNoFather.size () > 0) {
            tableId.addAll ( listNoFather.stream ().map ( TaskNode::getTableId ).collect ( Collectors.toList () ) );
        }

        //有父节点
        String[] filed = new String[]{"execute", "tableId"};
        StringBuffer sql = new StringBuffer ( "" );
        sql.append ( " select b.execute as execute , b.table_id as tableId from taskallotment_tasknode b where b.id in ( " )
                .append ( " select a.father_id from taskallotment_tasknode a  where a.haveSon IS NULL and father_id IS NOT NULL " )
                .append ( " and execute = '" + userName + "' and planTime BETWEEN '" + start + "' and '" + end + "' " )
                .append ( " ) and b.haveSon IS  NULL and b.father_id IS NULL " )
        ;
        listNoFather = super.findBySql ( sql.toString (), TaskNode.class, filed );
        if (listNoFather != null && listNoFather.size () > 0) {
            tableId.addAll ( listNoFather.stream ().map ( TaskNode::getTableId ).collect ( Collectors.toList () ) );
        }
        if (tableId == null || tableId.size () <= 0) {
            return null;
        }
        //根据任务获取参与的内部项目名称
        TableDTO tableDTO = new TableDTO ();
        tableDTO.getConditions ().add ( Restrict.in ( "id", tableId ) );
        List<Table> tables = tableSer.findByCis ( tableDTO );
        List<String> projectId = tables.stream ().map ( Table::getProjectId ).collect ( Collectors.toList () );
        ProjectDTO projectDTO = new ProjectDTO ();
        projectDTO.getConditions ().add ( Restrict.in ( "id", projectId ) );
        List<Project> projectList = projectSer.findByCis ( projectDTO );
        List<String> innerProjectName = projectList.stream ().map ( Project::getInnerProject ).collect ( Collectors.toList () );

        CollectDataVO collectDataVO = new CollectDataVO ();
        collectDataVO.setPersonName ( userName );
        collectDataVO.setProjectName ( innerProjectName );

        return collectDataVO;
    }

    @Override
    public List<com.bjike.goddess.taskallotment.bo.TaskNodeExcel> findByDTO(TaskNodeDTO dto) throws SerException {
        List<TaskNode> list = super.findByCis ( dto );
        return BeanTransform.copyProperties ( list, com.bjike.goddess.taskallotment.bo.TaskNodeExcel.class );
    }

    @Override
    public void editStatus(String id, TaskStatus taskStatus) throws SerException {
        Table table = tableSer.findById ( id );
        if (null != table) {
            TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
            taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", table.getId () ) );
            List<TaskNode> taskNodeList = super.findByCis ( taskNodeDTO );
            if (!taskNodeList.isEmpty ()) {
                for (TaskNode t : taskNodeList) {
                    t.setTaskStatus ( taskStatus );
                    if (taskStatus.equals ( TaskStatus.FINISH )) {
                        t.setFinishStatus ( FinishStatus.FINISH );
                    } else if (taskStatus.equals ( TaskStatus.UNFINISHED )) {
                        t.setFinishStatus ( FinishStatus.UNFINISHED );
                    }
                    t.setModifyTime ( LocalDateTime.now () );
                }
                super.update ( taskNodeList );
            }
            table.setTaskStatus ( taskStatus );
            table.setModifyTime ( LocalDateTime.now () );
            tableSer.update ( table );
        } else {
            TaskNode taskNode = super.findById ( id );
            if (null == taskNode) {
                throw new SerException ( "该对象不存在" );
            }
            taskNode.setTaskStatus ( taskStatus );
            if (taskStatus.equals ( TaskStatus.FINISH )) {
                taskNode.setFinishStatus ( FinishStatus.FINISH );
            } else if (taskStatus.equals ( TaskStatus.UNFINISHED )) {
                taskNode.setFinishStatus ( FinishStatus.UNFINISHED );
            }
            taskNode.setModifyTime ( LocalDateTime.now () );
            super.update ( taskNode );
            updateTable ( taskNode );
        }
    }

    private void updateTable(TaskNode taskNode) throws SerException {
        TaskNodeDTO taskNodeDTO = new TaskNodeDTO ();
        taskNodeDTO.getConditions ().add ( Restrict.eq ( "tableId", taskNode.getTableId () ) );
        List<TaskNode> taskNodeList = super.findByCis ( taskNodeDTO );
        long finishTotal = taskNodeList.stream ().filter ( taskNode1 -> TaskStatus.FINISH.equals ( taskNode1.getTaskStatus () ) ).count ();
        long unFinishTotal = taskNodeList.stream ().filter ( taskNode1 -> TaskStatus.UNFINISHED.equals ( taskNode1.getTaskStatus () ) ).count ();
        Table father = tableSer.findById ( taskNode.getTableId () );
        if (null != father) {
            if (finishTotal == taskNodeList.size ()) {
                father.setTaskStatus ( TaskStatus.FINISH );
                father.setModifyTime ( LocalDateTime.now () );
                tableSer.update ( father );
            } else if (unFinishTotal == taskNodeList.size ()) {
                father.setTaskStatus ( TaskStatus.UNFINISHED );
                father.setModifyTime ( LocalDateTime.now () );
                tableSer.update ( father );
            }
        }
    }
}