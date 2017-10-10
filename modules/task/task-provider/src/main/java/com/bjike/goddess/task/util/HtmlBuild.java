package com.bjike.goddess.task.util;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.bo.collect.TaskCollect;
import com.bjike.goddess.task.bo.collect.TomorrowCollect;
import com.bjike.goddess.task.bo.custmize.CustomField;
import com.bjike.goddess.task.bo.custmize.CustomProject;
import com.bjike.goddess.task.bo.custmize.CustomTable;
import com.bjike.goddess.task.bo.custmize.FixedField;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-28 11:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HtmlBuild {
    private static String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
    private static String valTr = "<tr style='height: 20px'>";
    private static String endTr = "</tr>";
    private static String startTd = "<td>";
    private static String endTd = "</td>";

    public static void buildTodayHtml(Collect collect, StringBuilder sb) throws SerException {
        StringBuilder sb_today = new StringBuilder();
        sb_today.append(nameTr);
        if(StringUtils.isNotBlank(collect.getDepartment())){
            int maxSize =  collect.getTodayCollects().size()+1;
            sb_today.append("<td bgcolor=\"#ffffff\" rowspan='"+maxSize+"'>");
            sb_today.append(collect.getDepartment());
            sb_today.append(endTd);
        }
        List<Field> fields = ClazzUtils.getFields(TaskCollect.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb_today.append(startTd);
            sb_today.append(header.name());
            sb_today.append(endTd);
        }
        sb_today.append(endTr);
        List<TaskCollect> todays = collect.getTodayCollects();
        if (null != todays) {
            for (TaskCollect today : todays) {
                sb_today.append(valTr);
                createHtmlVal(sb_today, today, headers);
                sb_today.append(endTr);

            }
        }
        String temp = sb_today.toString();
        List<TaskCollect> taskCollects = collect.getTodayCollects();
        Map<String, Integer> names = new HashMap<>();
        for (TaskCollect taskCollect : taskCollects) {
            names.put(taskCollect.getUsername(), 0);
        }
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            for (TaskCollect taskCollect : taskCollects) {
                if (entry.getKey().equals(taskCollect.getUsername())) {
                    entry.setValue(entry.getValue() + 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            if (entry.getValue() > 1) {
                temp = temp.replaceFirst(valTr + startTd + entry.getKey() + endTd,
                        valTr + "<td rowspan='" + entry.getValue() + "'>" + entry.getKey() + endTd);
                temp = temp.replaceAll(startTd + entry.getKey() + endTd, "");
            }
        }
        sb.append(temp);
    }

    public static void buildTomorrowHtml(Collect collect, StringBuilder sb) throws SerException {
        //构建明天任务
        StringBuilder sb_tomorrow = new StringBuilder();
        sb_tomorrow.append(nameTr);
        if(StringUtils.isNotBlank(collect.getDepartment())){
            int maxSize =  collect.getTomorrowCollects().size()+1;
            sb_tomorrow.append("<td bgcolor=\"#ffffff\" rowspan='"+maxSize+"'>");
            sb_tomorrow.append(collect.getDepartment());
            sb_tomorrow.append(endTd);
        }
        List<Field> fields = ClazzUtils.getFields(TomorrowCollect.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb_tomorrow.append(startTd);
            sb_tomorrow.append(header.name());
            sb_tomorrow.append(endTd);
        }
        sb_tomorrow.append(endTr);
        List<TomorrowCollect> tomorrows = collect.getTomorrowCollects();
        if (null != tomorrows) {
            for (TomorrowCollect tomorrow : tomorrows) {
                sb_tomorrow.append(valTr);
                createHtmlVal(sb_tomorrow, tomorrow, headers);
                sb_tomorrow.append(endTr);

            }
        }
        Map<String, Integer> names = new HashMap<>();
        for (TomorrowCollect taskCollect : collect.getTomorrowCollects()) {
            names.put(taskCollect.getUsername(), 0);
        }
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            for (TomorrowCollect taskCollect : collect.getTomorrowCollects()) {
                if (entry.getKey().equals(taskCollect.getUsername())) {
                    entry.setValue(entry.getValue() + 1);
                }
            }
        }
        String temp = sb_tomorrow.toString();
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            if (entry.getValue() > 1) {
                temp = temp.replaceFirst(valTr + startTd + entry.getKey() + endTd,
                        valTr + "<td rowspan='" + entry.getValue() + "'>" + entry.getKey() + endTd);
                temp = temp.replaceAll(startTd + entry.getKey() + endTd, "");
            }
        }
        sb.append(temp);
    }


    /**
     * 创建html值行
     *
     * @param sb
     * @param object
     * @param headers
     * @throws SerException
     */
    public static void createHtmlVal(StringBuilder sb, Object object, List<ExcelHeader> headers) throws SerException {
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
                            sb.append(startTd);
                            sb.append(val);
                            sb.append(endTd);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new SerException("类反射读取错误");
        }
    }


    /**
     * 构建自定义汇总html
     *
     * @param project
     * @return
     */
    public static String createCustomHtml(CustomProject project,String name) throws SerException {
        StringBuilder sb = new StringBuilder();
        List<CustomTable> tables = project.getTables();
        if (null != tables && tables.size() > 0) {
            //构建表头内容
            List<Field> fields = ClazzUtils.getFields(FixedField.class);
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            String[] customTitles = tables.get(0).getCustomTitles();
            sb = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
            sb.append("<tr ><td  style=\"font-weight:bold;color='#00CD00'\" colspan='"+(headers.size()+customTitles.length+2)+"'>"+name+"</td></tr>");
            sb.append(nameTr);
            sb.append(startTd);
            sb.append(" ");
            sb.append(endTd);
            sb.append(startTd);
            sb.append(" ");
            sb.append(endTd);

            //固定
            for (ExcelHeader header : headers) {
                sb.append(startTd);
                sb.append(header.name());
                sb.append(endTd);
            }
            //自定义
            for (String title : customTitles) {
                sb.append(startTd);
                sb.append(title);
                sb.append(endTd);
            }
            sb.append(endTr);
            int projectMergeSize=0;
            for(CustomTable table: project.getTables()){
                projectMergeSize += table.getFixedFields().size();
            }
            //构建内容
            boolean is_p = true; //创建项目名
            for (CustomTable table : tables) {
                boolean is_t = true;//创建表名
                for (FixedField fixedField : table.getFixedFields()) {
                    sb.append(valTr);
                    String val = "";
                    if (is_p) {
                        val = project.getName();
                        is_p = false;
                        sb.append("<td rowspan='"+projectMergeSize+"'>");
                        sb.append(val);
                        sb.append(endTd);
                    }
                    if (is_t) {
                        val = table.getName();
                        is_t = false;
                        sb.append("<td rowspan='"+table.getFixedFields().size()+"'>");
                        sb.append(val);
                        sb.append(endTd);
                    }

                    createHtmlVal(sb, fixedField, headers);
                    //自定义
                    for(String custom: customTitles){
                         val="";
                        for (CustomField customField : fixedField.getCustomFields()) {
                            if(customField.getName().equals(custom)){
                                val = customField.getValue();
                                break;
                            }
                        }
                        sb.append(startTd);
                        sb.append(val);
                        sb.append(endTd);
                    }
                    sb.append(endTr);
                }

            }
            //创建汇总
            sb.append(valTr);
            sb.append(startTd);
            sb.append("汇总");
            sb.append(endTd);
            sb.append(startTd);
            sb.append(" ");
            sb.append(endTd);
            String val="";
            for(ExcelHeader header:headers){
                for(Map.Entry<String,String> entry:project.getCollects().entrySet()){
                    if(header.name().equals(entry.getKey())){
                        val = entry.getValue();
                        break;
                    }
                }
                sb.append(startTd);
                sb.append(val);
                sb.append(endTd);
            }

            for(String custom: customTitles){
                for(Map.Entry<String,String> entry:project.getCollects().entrySet()){
                    if(custom.equals(entry.getKey())){
                        val = entry.getValue();
                        break;
                    }
                }
                sb.append(startTd);
                sb.append(val);
                sb.append(endTd);
            }

            sb.append(endTr);
            //创建部门汇总
            Collect collect = new Collect();
            collect.setTodayCollects(project.getTaskCollects());
            collect.setTomorrowCollects(project.getTomorrowCollects());
            collect.setDepartment(project.getDepartment()); //部门汇总
            sb.append("<tr style='height: 20px'>");
            sb.append("<td style=\"font-weight:bold;color='green'\" align='left' colspan='"+customTitles.length+headers.size()+"'>今天任务</td>");
            buildTodayHtml(collect,sb);
            sb.append(endTr);
            sb.append("<tr style='height: 20px'>");
            sb.append("<td style=\"font-weight:bold;color='green'\" align='left' colspan='"+customTitles.length+headers.size()+"'>明天任务计划</td>");
            buildTomorrowHtml(collect,sb);
            sb.append(endTr);
            sb.append("</table>");
        }
        System.out.println(sb.toString());
        return sb.toString();

    }
}
