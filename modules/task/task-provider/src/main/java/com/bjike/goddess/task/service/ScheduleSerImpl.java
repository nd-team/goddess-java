package com.bjike.goddess.task.service;

import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.ModulesAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.bo.collect.Custom;
import com.bjike.goddess.task.bo.collect.TaskCollect;
import com.bjike.goddess.task.bo.collect.TomorrowCollect;
import com.bjike.goddess.task.bo.custmize.*;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.enums.*;
import com.bjike.goddess.task.util.HtmlBuild;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ScheduleSerImpl implements ScheduleSer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ScheduleSerImpl.class);
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private ModulesAPI modulesAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;
    @Autowired
    private CustomizeSer customizeSer;

    @Override
    public Collect collect(CollectDTO dto) throws SerException {
        UserBO user = userAPI.currentUser();
        Map<String, String> map = positionUserDetailAPI.departPosition(user.getUsername());
        Set<String> names = null;
        if (null != map) {
            Set<String> set = map.keySet();
            for (String s : set) {
                List<DepartmentDetailBO> detailBOS = departmentDetailAPI.departByName(new String[]{s});
                if (null != detailBOS && !detailBOS.isEmpty()) {
                    names = departmentDetailAPI.departPersons(detailBOS.get(0).getId());
                }
            }
        }
        Collect collect = new Collect();
        if (dto.isNeedFixed()) {
            String sql = getSql(dto);
            String project = projectAPI.findByID(dto.getProjectId()).getProject();
            try {
                collect.setScaleCount(baseInfoManageAPI.contractScale(project));
            } catch (Exception e) {
                if (e.getMessage().indexOf("Forbid consumer") != -1) {
                    LOGGER.error("businessproject服务不可用!");
                }
            }
            collect.setFinishCount(customizeSer.get(project));
            //todo 出车数量（从出车记录管理获取）未获取
            List<Object> objects = tableSer.findBySql(sql);
            if (null != objects && objects.size() > 0) {
                Object o = objects.get(0);
                if (null != o) {
                    Object[] arrays = (Object[]) o;
                    collect.setOutProject(String.valueOf(arrays[0]));
                    collect.setInnerProject(String.valueOf(arrays[1]));
                    collect.setIsFinish(String.valueOf(arrays[2]));
                    collect.setWorkerCount(Integer.parseInt(String.valueOf(arrays[3])));
                }
            }
        }
        collect.setCustoms(initCustoms(dto));
        //今天任务
        String start = DateUtil.dateToString(LocalDate.now().atTime(00, 00, 01));
        String end = DateUtil.dateToString(LocalDate.now().atTime(23, 59, 59));
        if (null != initDateTask(names, start, end)) {
            collect.setTodayCollects(initDateTask(names, start, end));
        }
        //可添加汇总   collectToday(collects);
        //可添加明天汇总 collectTomorrow(collects);
        //明天任务
        start = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(00, 00, 01));
        end = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(23, 59, 59));
        List<TaskCollect> taskCollects = initDateTask(names, start, end);
        if (null != taskCollects) {
            collect.setTomorrowCollects(BeanTransform.copyProperties(taskCollects, TomorrowCollect.class));
        }
        return collect;
    }

    /**
     * 查询某个日期某些人的任务
     *
     * @param users
     * @param startTime
     * @param endTime
     * @return
     * @throws SerException
     */
    private List<TaskCollect> initDateTask(Set<String> users, String startTime, String endTime) throws SerException {
        if (null != users) {
            String[] username = new String[users.size()];
            int i = 0;
            for (String s : users) {
                username[i] = s;
                i++;
            }
            String names = "'" + StringUtils.join(username, "','") + "'";
            String sql;
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT username, outProject, ");
            sb.append("  innerProject, ");
            sb.append("  taskType, ");
            sb.append(" content, ");
            sb.append(" remark, ");
            sb.append(" planNum, ");
            sb.append(" factNum,differNum, ");
            sb.append("  planDuration, factDuration, ");
            sb.append("  factDuration-planDuration AS differDuration ");
            sb.append(" FROM( ");
            sb.append(" SELECT tn.execute AS username,p.project AS outProject,p.innerProject  , ");
            sb.append(" tn.content,tn.remark,planNum,actualNum AS factNum,(actualNum-planNum ) AS differNum, ");
            sb.append(" CASE tn.taskType WHEN '0' THEN '行政任务' WHEN '1' THEN '工程任务' WHEN '2' THEN '培训任务' END AS taskType, ");
            sb.append(" CASE needType   WHEN '1' THEN 60*needTime    WHEN '2' THEN 24*60*needTime  ELSE needTime END  AS planDuration, ");
            sb.append(" CASE actualType   WHEN '1' THEN 60*actualTime   WHEN '2' THEN 24*60*actualTime  ELSE actualTime END  AS factDuration ");
            sb.append(" FROM taskallotment_tasknode tn ,taskallotment_table t,");
            sb.append(" taskallotment_project p WHERE tn.table_id = t.id AND t.project_id = p.id  ");
            sb.append(" AND ( ");
            sb.append(" tn.startTime BETWEEN '" + startTime + "' AND  '" + endTime + "' ");
            sb.append(" OR tn.endTime BETWEEN '" + startTime + "' AND  '" + endTime + "' ");
            sb.append(" OR tn.startTime < '" + startTime + "' ");
            sb.append(" OR tn.endTime > '" + endTime + "' ");
            sb.append(" ) AND execute IN (" + names + ")  ");
            sb.append(" ) a ");
            sb.append(" ORDER BY username");
            sql = sb.toString();
            String[] fields = new String[]{"username", "outProject", "innerProject", "taskType",
                    "content", "remark", "planNum", "factNum", "differNum", "planDuration", "factDuration", "differDuration"};
            List<TaskCollect> taskCollects = tableSer.findBySql(sql, TaskCollect.class, fields); //查询指定多人的任务
            for (String user : username) {
                boolean exist = false;
                Map<String, String> map = modulesAPI.findModuleAndPost(user);
                for (TaskCollect collect : taskCollects) {
                    if (user.equals(collect.getUsername())) {
                        exist = true;
                        if (null != map) { //设置模块及职位
                            collect.setModule(map.get("module"));
                            collect.setPost(map.get("position"));
                        }
                        break;
                    }
                }
                if (!exist) { //不存在任务则添加空的对象
                    TaskCollect taskCollect = new TaskCollect();
                    if (null != map) { //设置模块及职位
                        taskCollect.setModule(map.get("module"));
                        taskCollect.setPost(map.get("position"));
                    }
                    taskCollect.setUsername(user);
                    taskCollects.add(taskCollect);
                }
            }
            return taskCollects;
        }
        return null;
    }

    /**
     * 查询自定义汇总
     */
    private List<Custom> initCustoms(CollectDTO dto) throws SerException {
        String projectId = dto.getProjectId();
        String[] fields = dto.getFields();
        String cons = null;
        if (null != dto.getTablesId()) {
            cons = "AND t.id IN('" + StringUtils.join(dto.getTablesId(), "','") + "')";
        }
        if (null != fields) {
            //验证表头有效性
            StringBuilder sb = new StringBuilder();
            String fieldId = "'" + StringUtils.join(fields, "','") + "'";
            sb.append("   SELECT a.*,b.content as detail FROM ( ");
            sb.append(" SELECT  tc.title  , COUNT(tc.content) as count ");
            sb.append("  FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t,taskallotment_customtitle tc ");
            sb.append(" WHERE p.id = '" + projectId + "' ");
            sb.append(" AND p.id =t.project_id AND n.table_id=t.id  AND tc.tasknode_id = n.id ");
            if (null != cons) {
                sb.append(cons);
            }
            sb.append(" AND tc.title IN (" + fieldId + ") ");
            sb.append("  GROUP BY ");
            sb.append("  tc.title ) a,( ");
            sb.append(" SELECT title ,GROUP_CONCAT(a.content) AS content FROM( ");
            sb.append(" SELECT tc.title, tc.content FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t,taskallotment_customtitle tc ");
            sb.append(" WHERE p.id = '" + projectId + "' ");
            sb.append(" AND p.id =t.project_id AND n.table_id=t.id  AND tc.tasknode_id = n.id ");
            if (null != cons) {
                sb.append(cons);
            }
            sb.append(" AND tc.title IN (" + fieldId + ") ");
            sb.append(" )a GROUP BY a.title ");
            sb.append(" ) b where a.title=b.title ");
            String sql = sb.toString();
            String[] _fields = new String[]{"title", "count", "detail"};
            List<Custom> customs = tableSer.findBySql(sql, Custom.class, _fields);
            return customs;
        }
        return new ArrayList<>();
    }

    /**
     * 查询 outProject 外部项目,innerProject 内部项目,isFinish 是否完成,workerCount  人工量
     *
     * @param dto
     * @return
     */
    private String getSql(CollectDTO dto) {
        String projectId = dto.getProjectId();
        String cons = null;
        if (null != dto.getTablesId()) {
            cons = " AND t.id IN('" + StringUtils.join(dto.getTablesId(), "','") + "')";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ( ");
        sb.append(" SELECT ");
        sb.append("  p.project AS outProject,p.innerProject, ");
        sb.append("  if(p.status=0,'完成','未完成')AS isFinish FROM ");
        sb.append("  taskallotment_project p, ");
        sb.append("  taskallotment_table t ");
        sb.append("   WHERE p.id = '" + projectId + "' AND p.id = t.project_id ");
        if (null != cons) {
            sb.append(cons);
        }
        sb.append(" ) a, ( ");
        sb.append("  select count(*) as workerCount FROM(SELECT execute FROM taskallotment_tasknode n,taskallotment_project p,taskallotment_table t ");
        sb.append("  WHERE p.id = '" + projectId + "' AND p.id =t.project_id AND n.table_id=t.id");
        if (null != cons) {
            sb.append(cons);
        }
        sb.append(" GROUP BY execute) a ");
        sb.append("  ) b ");
        return sb.toString();
    }


    @Override
    public String buildCollectHtml(Collect collect, CollectType type) throws SerException {
        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
        String valTr = "<tr style='height: 20px'>";
        String endTr = "</tr>";
        //构建表头信息
        StringBuilder sb = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
        sb.append(nameTr);
        List<Field> fields = ClazzUtils.getFields(collect.getClass());
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb.append("<td>");
            sb.append(header.name());
            sb.append("</td>");
        }
        //自定义表头信息
        for (Custom custom : collect.getCustoms()) {
            sb.append("<td>");
            sb.append(custom.getTitle());
            sb.append("</td>");
        }
        sb.append(endTr);
        //构建第一行内容
        sb.append(valTr);
        HtmlBuild.createHtmlVal(sb, collect, headers);
        for (Custom custom : collect.getCustoms()) {
            sb.append("<td>");
            if (null == type) {
                sb.append("数量:" + custom.getCount() + ",详情:" + custom.getDetail());
            } else if (type.equals(CollectType.DETAIL)) {
                sb.append(custom.getDetail());
            } else {
                sb.append(custom.getCount());
            }
            sb.append("</td>");
        }
        sb.append(endTr);
        sb.append("<tr><td style='border-right:none;border-left:none;border-top:none;border-bottom:none'>今天任务<td></tr>");
        //构建今天任务
        HtmlBuild.buildTodayHtml(collect, sb);
        sb.append("<tr></tr><tr><td style='border-right:none;border-left:none;border-top:none;border-bottom:none'>明天任务<td></tr>");
        HtmlBuild.buildTomorrowHtml(collect, sb);
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 自定义汇总
     *
     * @param customize
     * @return
     * @throws SerException
     */
    @Override
    public void customizeCollect(Customize customize) throws SerException {
        boolean available = true; //尝试调用消息接口,服务是否可用,如果不可用则不再进行查询操作
        try {
            messageAPI.read("");
        } catch (Exception e) {
            if (e.getMessage().indexOf("Forbid consumer") != -1) {
                available = false;
                LOGGER.error("消息模块服务不可用!");
            }
        }
        if (available) {
            CustomProject project = new CustomProject();
            String sql = "SELECT p.name as projectName ,t.name as tableName,t.id as tid FROM taskallotment_project p ,taskallotment_table t  " +
                    "WHERE p.id='" + customize.getProjectId() + "'  " +
                    "AND t.project_id=p.id ";
            if (StringUtils.isNotBlank(customize.getTablesId())) {
                String[] tables = customize.getTablesId().split(",");
                sql += "AND t.id in('" + StringUtils.join(tables, "','") + "')";
            }
            //查询包含项目表
            List<Object> objects = tableSer.findBySql(sql);
            if (null != objects && objects.size() > 0) {
                Object[] ot = (Object[]) objects.get(0);
                project.setName(String.valueOf(ot[0])); //初始化项目
                List<CustomTable> tables = new ArrayList<>(objects.size());
                for (Object o : objects) { //初始化项目表
                    ot = (Object[]) o;
                    CustomTable table = new CustomTable();
                    table.setName(String.valueOf(ot[1]));
                    table.setTid(String.valueOf(ot[2]));
                    tables.add(table);
                }
                CustomCondition condition = initCondition(customize);//初始化条件
                initFixedField(tables, condition); //初始化固定表头及内容
                initCustomField(tables, condition);//初始化自定义表头及内容
                initCustomCollect(tables, project);//手动汇总自定义计算
                //--------------------------------
                initDeptCollect(project, customize.getDepartment());//初始化部门汇总
                collectToday(project.getTaskCollects()); //部门今天任务汇总
                collectTomorrow(project.getTomorrowCollects());//部门明天任务汇总
                project.setTables(tables);
                String html = HtmlBuild.createCustomHtml(project, customize.getName());//创建html数据
                //邮件发送
                NoticeType type = customize.getNoticeType();
                String[] strings = null;
                if (type.equals(NoticeType.PERSON)) {
                    strings = customize.getNoticeTarget().split(",");
                } else if (type.equals(NoticeType.DEPT)) {
                    String[] s = customize.getNoticeTarget().split(",");  //部门数组
                    Set<String> set = new HashSet<>();
                    List<DepartmentDetailBO> detailBOS = departmentDetailAPI.departByName(strings);
                    if (null != detailBOS) {
                        for (DepartmentDetailBO d : detailBOS) {
                            Set<String> names = departmentDetailAPI.departPersons(d.getId());
                            if (null != names) {
                                set.addAll(names);
                            }
                        }
                    }
                    List<String> list = new ArrayList<>(set);
                    strings = new String[list.size()];
                    strings = list.toArray(strings);
                } else {
                    List<UserBO> findUserListInOrgan = positionDetailUserAPI.findUserListInOrgan();
                    List<String> list = findUserListInOrgan.stream().map(UserBO::getUsername).collect(Collectors.toList());
                    strings = new String[list.size()];
                    strings = list.toArray(strings);
                }
                if (null != strings) {
                    List<String> mails = internalContactsAPI.getEmails(strings);
                    if (null != mails && !mails.isEmpty()) {
                        String[] recivers = new String[mails.size()];
                        recivers = mails.toArray(recivers);
                        sendMail(customize.getName(), html, recivers);
                    }
                }
            }
        }

    }

    /**
     * 邮件发送
     *
     * @param title
     * @param content
     * @param recivers
     * @throws SerException
     */
    private void sendMail(String title, String content, String[] recivers) throws SerException {
        MessageTO to = new MessageTO(title, content);
        to.setSendType(SendType.EMAIL);
        to.setReceivers(recivers);
        messageAPI.send(to);
    }

    /**
     * 初始化部门汇总
     *
     * @param project
     * @param department
     * @throws SerException
     */
    private void initDeptCollect(CustomProject project, String department) throws SerException {
        Set<String> names = null;
        List<DepartmentDetailBO> detailBOS = departmentDetailAPI.departByName(new String[]{department});
        if (null != detailBOS && !detailBOS.isEmpty()) {
            names = departmentDetailAPI.departPersons(detailBOS.get(0).getId());
        }
        String start = DateUtil.dateToString(LocalDate.now().atTime(00, 00, 01));
        String end = DateUtil.dateToString(LocalDate.now().atTime(23, 59, 59));
        if (null != initDateTask(names, start, end)) {
            project.setTaskCollects(initDateTask(names, start, end));
        }
        //明天任务
        start = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(00, 00, 01));
        end = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(23, 59, 59));
        List<TaskCollect> taskCollects = initDateTask(names, start, end);
        if (null != taskCollects) {
            project.setTomorrowCollects(BeanTransform.copyProperties(taskCollects, TomorrowCollect.class));
        }
        project.setDepartment(department);
    }

    /**
     * 手动汇总自定义计算
     *
     * @param tables
     * @param project
     */
    private void initCustomCollect(List<CustomTable> tables, CustomProject project) {
        List<Field> fields = ClazzUtils.getFields(FixedField.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        Map<String, String> map = new HashMap<>();
        for (ExcelHeader header : headers) {
            map.put(header.name(), "");
        }
        //计算人员
        Set<String> users = new HashSet<>();
        double planNum = 0;
        double needTime = 0;
        List<CustomField> customFields = new ArrayList<>();
        for (CustomTable table : tables) {
            for (FixedField field : table.getFixedFields()) {
                users.add(field.getUsername());
                planNum += Double.parseDouble(field.getPlanNum());
                needTime += Double.parseDouble(field.getNeedTime());
                customFields.addAll(field.getCustomFields());
            }
            for (String title : table.getCustomTitles()) {
                map.put(title, "0");
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //计算自定义汇总
            for (CustomField customField : customFields) {
                if (customField.getName().equals(entry.getKey())) {
                    double val = 0;
                    if (customField.getTitleType().equals(TitleType.INT) || customField.getTitleType().equals(TitleType.DOUBLE)) {
                        val = Double.parseDouble(entry.getValue()) + Double.parseDouble(customField.getValue());
                    } else {
                        val = Double.parseDouble(entry.getValue()) + 1;
                    }
                    map.put(entry.getKey(), String.valueOf(val));
                }
            }
        }
        map.put("人员", String.valueOf(users.size()));
        map.put("计划工作量", String.valueOf(planNum));
        map.put("所需时长", String.valueOf(needTime));
        project.setCollects(map);
    }

    /**
     * 初始化用户自定义表头(必须所有表都包含)
     */
    private void initCustomField(List<CustomTable> tables, CustomCondition condition) throws SerException {
        StringBuilder sb = new StringBuilder();
        String start = condition.getStart();
        String end = condition.getEnd();
        sb.append("  SELECT a.title,a.content,a.titleType,b.tasknode_id as nodeId ");
        sb.append(" FROM taskallotment_customtitle a,( ");
        sb.append("  SELECT a.* ");
        sb.append(" FROM( ");
        sb.append("    SELECT tc.title,tc.tasknode_id ");
        sb.append(" FROM taskallotment_project p, ");
        sb.append("  taskallotment_table t,taskallotment_tasknode tn,taskallotment_customtitle tc ");
        sb.append("  WHERE p.id ='" + condition.getProjectId() + "'");
        if (null != condition.getTablesId()) {
            sb.append("  AND t.id IN('" + StringUtils.join(condition.getTablesId(), "','") + "') ");
        }
        sb.append("  AND tn.table_id = t.id AND tc.tasknode_id=tn.id ");
        sb.append("  GROUP BY tc.tasknode_id,title)a, (");
        sb.append(" SELECT title AS title ");
        sb.append("  FROM( ");
        sb.append("  SELECT tc.title,tc.tasknode_id ");
        sb.append("  FROM taskallotment_project p, ");
        sb.append(" taskallotment_table t,taskallotment_tasknode tn,taskallotment_customtitle tc ");
        sb.append(" WHERE p.id ='" + condition.getProjectId() + "' ");
        if (null != condition.getTablesId()) {
            sb.append("  AND t.id IN('" + StringUtils.join(condition.getTablesId(), "','") + "') ");
        }
        String cond = getTimeSql(start, end);
        if (null != cond) {
            sb.append(cond);
        }
        sb.append("  AND tn.table_id = t.id AND tc.tasknode_id=tn.id ");
        sb.append(" GROUP BY tc.tasknode_id,tc.title) a ");
        sb.append("  GROUP BY title ");
        sb.append("   HAVING COUNT(title)>1 )b");
        sb.append("  WHERE ");
        sb.append("   a.title=b.title) b ");
        sb.append("   WHERE a.title=b.title AND a.tasknode_id=b.tasknode_id");
        String sql = sb.toString();
        String[] fields = new String[]{"name", "value", "titleType", "nodeId"};
        List<CustomField> customFields = tableSer.findBySql(sql, CustomField.class, fields);
        if (null != customFields && customFields.size() > 0) {
            for (CustomTable table : tables) {
                initCustomTitle(table, condition);
                for (CustomField customField : customFields) {
                    for (FixedField fixedField : table.getFixedFields()) {
                        if (fixedField.getNodeId().equals(customField.getNodeId())) {
                            fixedField.getCustomFields().add(customField);
                        }
                    }
                }
            }
        }
    }

    /**
     * 初始化自定义表头
     *
     * @param table
     * @param condition
     * @throws SerException
     */
    private void initCustomTitle(CustomTable table, CustomCondition condition) throws SerException {
        String start = condition.getStart();
        String end = condition.getEnd();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT title AS title ");
        sb.append("  FROM( ");
        sb.append(" SELECT tc.title,tc.tasknode_id ");
        sb.append(" FROM taskallotment_project p, taskallotment_table t,taskallotment_tasknode tn,taskallotment_customtitle tc ");
        sb.append("  WHERE p.id ='" + condition.getProjectId() + "' ");
        if (null != condition.getTablesId()) {
            sb.append("  AND t.id IN('" + StringUtils.join(condition.getTablesId(), "','") + "') ");
        }
        String cond = getTimeSql(start, end);
        if (null != cond) {
            sb.append(cond);
        }
        sb.append(" AND tn.table_id = t.id AND tc.tasknode_id=tn.id ");
        sb.append(" GROUP BY tc.tasknode_id,tc.title) a ");
        sb.append(" GROUP BY title ");
        sb.append(" HAVING COUNT(title)>1 ");
        List<Object> objects = tableSer.findBySql(sb.toString());
        String[] titles = new String[objects.size()];
        objects.toArray(titles);
        table.setCustomTitles(titles);
    }

    /**
     * 初始化固定字段
     *
     * @param tables
     * @param condition
     * @throws SerException
     */
    private void initFixedField(List<CustomTable> tables, CustomCondition condition) throws SerException {
        String start = condition.getStart();
        String end = condition.getEnd();
        String sql;
        StringBuilder sb = new StringBuilder();
        sb.append(" select t.name as tableName,tn.taskName,tn.execute as username ,tn.content,tn.planTime,tn.remark , ");
        sb.append("  CASE tn.taskType WHEN '0' THEN '行政任务' WHEN '1' THEN '工程任务' WHEN '2' THEN '培训任务' END AS taskType, ");
        sb.append(" planNum,tn.needTime,tn.id as nodeId ");
        sb.append("  from taskallotment_project  p, ");
        sb.append("  taskallotment_table t,taskallotment_tasknode tn ");
        sb.append("  where p.id ='" + condition.getProjectId() + "' ");
        if (null != condition.getTablesId()) {
            sb.append("  and t.id in('" + StringUtils.join(condition.getTablesId(), "','") + "') ");
        }
        String cond = getTimeSql(start, end);
        if (null != cond) {//时间过滤
            sb.append(cond);
        }
        sb.append("   and tn.table_id = t.id ");
        if (null != condition.getUsers() && condition.getUsers().size() > 0) { //必须包含部门人员
            sb.append("  and  tn.execute in('" + StringUtils.join(condition.getUsers(), "','") + "') ");
        }
        sb.append(" order by t.id,username ");
        sql = sb.toString();
        String[] fields = new String[]{"tableName", "taskName", "username", "content", "planTime", "remark", "taskType", "planNum", "needTime", "nodeId"};
        List<FixedField> fixedFields = tableSer.findBySql(sql, FixedField.class, fields);
        for (CustomTable table : tables) {
            List<FixedField> tmp_fixed = new ArrayList<>();
            for (FixedField fixedField : fixedFields) {
                if (table.getName().equals(fixedField.getTableName())) {
                    tmp_fixed.add(fixedField);
                }
            }
            table.setFixedFields(tmp_fixed);
        }
    }

    /**
     * 今天任务汇总
     *
     * @param collects
     */
    private void collectToday(List<TaskCollect> collects) {
        TaskCollect collect = new TaskCollect();
        collect.setUsername("汇总");
        collect.setPlanNum(collects.stream().mapToDouble(c -> {
            double rs = c.getPlanNum() != null ? Double.parseDouble(c.getPlanNum()) : 0;
            return rs;
        }).sum() + "");
        collect.setDifferNum(collects.stream().mapToDouble(c -> {
            double rs = c.getDifferNum() != null ? Double.parseDouble(c.getDifferNum()) : 0;
            return rs;
        }).sum() + "");
        collect.setFactNum(collects.stream().mapToDouble(c -> {
            double rs = c.getFactNum() != null ? Double.parseDouble(c.getFactNum()) : 0;
            return rs;
        }).sum() + "");
        collect.setFactDuration(collects.stream().mapToDouble(c -> {
            double rs = c.getFactDuration() != null ? Double.parseDouble(c.getFactDuration()) : 0;
            return rs;
        }).sum() + "");
        collect.setDifferDuration(collects.stream().mapToDouble(c -> {
            double rs = c.getDifferDuration() != null ? Double.parseDouble(c.getDifferDuration()) : 0;
            return rs;
        }).sum() + "");
        collect.setPlanDuration(collects.stream().mapToDouble(c -> {
            double rs = c.getPlanDuration() != null ? Double.parseDouble(c.getPlanDuration()) : 0;
            return rs;
        }).sum() + "");
        collects.add(collect);
    }

    /**
     * 明天任务汇总
     *
     * @param collects
     */
    private void collectTomorrow(List<TomorrowCollect> collects) {
        TomorrowCollect collect = new TomorrowCollect();
        collect.setUsername("汇总");
        collect.setPlanDuration(collects.stream().mapToDouble(c -> {
            double rs = c.getPlanDuration() != null ? Double.parseDouble(c.getPlanDuration()) : 0;
            return rs;
        }).sum() + "");
        collect.setPlanNum(collects.stream().mapToDouble(c -> {
            double rs = c.getPlanNum() != null ? Double.parseDouble(c.getPlanNum()) : 0;
            return rs;
        }).sum() + "");
        collects.add(collect);
    }


    /**
     * 构建条件
     *
     * @param customize
     * @return
     * @throws SerException
     */
    private CustomCondition initCondition(Customize customize) throws SerException {
        DateType dateType = customize.getDateType(); //日期条件
        LocalDateTime start = null;
        LocalDateTime end = null;
        List<UserBO> dept_users = null;//查询结果用户必须包含在此部门人员列表内
        List<UserBO> users = null;//指定查询汇总的人
        if (customize.getSummaryType().equals(SummaryType.DEPT)) {
            dept_users = userAPI.findByDept(customize.getSummaryTarget().split(","));
        } else if (customize.getSummaryType().equals(SummaryType.PERSON)) { //仅查询的人
            String[] nickname = customize.getSummaryTarget().split(",");
            UserDTO dto = new UserDTO();
            dto.getConditions().add(Restrict.in("nickname", nickname));
            users = userAPI.findByCis(dto);
        }
        switch (dateType) {
            case DAY:
                start = LocalDate.now().atTime(00, 00, 01);
                end = LocalDate.now().atTime(23, 59, 59);
                break;
            case WEEK:
                start = DateUtil.getStartWeek().atTime(00, 00, 01);
                end = DateUtil.getEndWeek().atTime(23, 59, 59);
                break;
            case MONTH:
                start = DateUtil.getStartMonth().atTime(00, 00, 01);
                end = DateUtil.getEndMonth().atTime(23, 59, 59);
                break;
            case QUARTER:
                start = DateUtil.getStartQuart();
                end = DateUtil.getEndQuart();
                break;
            case YEAR:
                start = DateUtil.getStartYear().atTime(00, 00, 01);
                end = DateUtil.getEndYear().atTime(23, 59, 59);
                break;
        }
        String[] fields = customize.getFields().split(",");
        CustomCondition customCondition = new CustomCondition();
        customCondition.setStart(DateUtil.dateToString(start));
        customCondition.setEnd(DateUtil.dateToString(end));
        customCondition.setFields(fields);
        customCondition.setProjectId(customize.getProjectId());
        if (null != customize.getTablesId()) {
            customCondition.setTablesId(customize.getTablesId().split(","));
        }
        List<String> tmp_users = new ArrayList<>();
        if (null != dept_users) {
            for (UserBO user : dept_users) {
                tmp_users.add(user.getNickname());
            }
            customCondition.setDeptUsers(tmp_users);
            tmp_users.clear();
        }
        if (null != users) {
            for (UserBO user : users) {
                tmp_users.add(user.getNickname());
            }
            customCondition.setUsers(tmp_users);
        }
        return customCondition;
    }

    private String getTimeSql(String start, String end) {
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            StringBuilder sb = new StringBuilder();
            sb.append("  and  ( ");
            sb.append(" tn.startTime BETWEEN '" + start + "' AND  '" + end + "' ");
            sb.append(" OR tn.endTime BETWEEN '" + start + "' AND  '" + end + "'  ");
            sb.append("  OR tn.startTime < '" + start + "' ");
            sb.append("  OR tn.endTime >  '" + end + "' ) ");
            return sb.toString();
        }
        return null;
    }

}
