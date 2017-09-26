package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.bo.collect.Custom;
import com.bjike.goddess.task.bo.collect.TaskCollect;
import com.bjike.goddess.task.bo.collect.TomorrowCollect;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.enums.CollectType;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ScheduleSerImpl implements ScheduleSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private TableSer tableSer;

    @Override
    public Collect collect(CollectDTO dto) throws SerException {
        UserBO user = userAPI.currentUser();
        String userId = user.getId();
        UserDetailBO detail = userDetailAPI.findByUserId(userId);
        String departmentId = detail.getDepartmentId();
        List<UserBO> users = userAPI.findByDept(departmentId); //查询自己所在部门的所有用户
        Collect collect = new Collect();
        if (dto.isNeedFixed()) {
            String sql = getSql(dto);
            //todo 总规模数（从商务合同管理获取），出车数量（从出车记录管理获取）未获取
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
        collect.setTodayCollects(initDateTask(users, start, end));
        //明天任务
        start = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(00, 00, 01));
        end = DateUtil.dateToString(LocalDate.now().plusDays(1).atTime(00, 00, 01));
        List<TaskCollect> taskCollects = initDateTask(users, start, end);
        collect.setTomorrowCollects(BeanTransform.copyProperties(taskCollects, TomorrowCollect.class));
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
    private List<TaskCollect> initDateTask(List<UserBO> users, String startTime, String endTime) throws SerException {
        String[] username = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            username[i] = users.get(i).getNickname();
        }
        String names = "'" + StringUtils.join(username, "','") + "'";
        //todo  String module; //模块  String post; //岗位 未获取
        String sql;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT username,GROUP_CONCAT(outProject) AS outProject, ");
        sb.append(" GROUP_CONCAT(innerProject) AS innerProject, ");
        sb.append(" GROUP_CONCAT(taskType) AS taskType, ");
        sb.append(" GROUP_CONCAT(content) AS content, ");
        sb.append(" GROUP_CONCAT(remark) AS remark, ");
        sb.append(" SUM(planNum) AS planNum, ");
        sb.append(" SUM(factNum) AS factNum,SUM(differNum) AS differNum, ");
        sb.append(" SUM(planDuration) AS planDuration,SUM(factDuration) AS factDuration, ");
        sb.append(" SUM(planDuration-factDuration) AS differDuration ");
        sb.append(" FROM( ");
        sb.append(" SELECT tn.execute AS username,p.project AS outProject,p.innerProject  , ");
        sb.append(" tn.content,tn.remark,planNum,actualNum AS factNum,(planNum-actualNum ) AS differNum, ");
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
        sb.append(" GROUP BY username");
        sql = sb.toString();
        String[] fields = new String[]{"username", "outProject", "innerProject", "taskType",
                "content","remark", "planNum", "factNum", "differNum", "planDuration", "factDuration", "differDuration"};
        return tableSer.findBySql(sql, TaskCollect.class, fields);

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
            cons = "AND t.id IN('" + StringUtils.join(dto.getTablesId(), "','") + "')";
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
        sb.append(" GROUP BY execute)a ");
        sb.append("  ) b ");
        return sb.toString();
    }


    @Override
    public String buildCollectHtml(Collect collect, CollectType type) throws SerException {
        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
        String valTr = "<tr style='height: 20px'>";
        String endTr = "</tr>";
        //构建表头信息
        StringBuilder sb = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellpadding='1' cellspacing='2'>");
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
        createHtmlVal(sb, collect, headers);
        for (Custom custom : collect.getCustoms()) {
            sb.append("<td>");
            if (null == type) {
                sb.append("数量[" + custom.getCount() + "],详情[" + custom.getDetail() + "]");
            } else if (type.equals(CollectType.DETAIL)) {
                sb.append(custom.getDetail());
            } else {
                sb.append(custom.getCount());
            }
            sb.append("</td>");
        }
        sb.append(endTr);


        sb.append("<tr><td colspan=2 style='border-right:none;border-left:none;border-top:none;border-bottom:none'>今天任务<td></tr>");
        //构建今天任务
        sb.append(nameTr);
        fields = ClazzUtils.getFields(TaskCollect.class);
        headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb.append("<td>");
            sb.append(header.name());
            sb.append("</td>");
        }
        sb.append(endTr);
        List<TaskCollect> todays = collect.getTodayCollects();
        if (null != todays) {
            for (TaskCollect today : todays) {
                sb.append(valTr);
                createHtmlVal(sb, today, headers);
                sb.append(endTr);

            }
        }
        sb.append("<tr></tr><tr><td colspan=2  style='border-right:none;border-left:none;border-top:none;border-bottom:none'>明天任务<td></tr>");
        //构建明天任务
        sb.append(nameTr);
        fields = ClazzUtils.getFields(TomorrowCollect.class);
        headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb.append("<td>");
            sb.append(header.name());
            sb.append("</td>");
        }
        sb.append(endTr);
        List<TomorrowCollect> tomorrows = collect.getTomorrowCollects();
        if (null != tomorrows) {
            for (TomorrowCollect tomorrow : tomorrows) {
                sb.append(valTr);
                createHtmlVal(sb, tomorrow, headers);
                sb.append(endTr);

            }
        }

        sb.append("</table>");
        return sb.toString();
    }

    private void createHtmlVal(StringBuilder sb, Object object, List<ExcelHeader> headers) throws SerException {
        List<Field> fields = ClazzUtils.getFields(object.getClass());
        try {
            for (ExcelHeader header : headers) {
                for (Field f : fields) {
                    if (null != f.getAnnotation(ExcelHeader.class)) {
                        String fieldName = f.getAnnotation(ExcelHeader.class).name();
                        if (header.name().equals(fieldName)) {
                            f.setAccessible(true);
                            Object val = f.get(object);
                            val = val != null ? val : "";
                            sb.append("<td>");
                            sb.append(val);
                            sb.append("</td>");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new SerException("类反射读取错误");
        }

    }
}
