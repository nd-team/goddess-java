package com.bjike.goddess.task.util;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.bo.collect.TaskCollect;
import com.bjike.goddess.task.bo.collect.TomorrowCollect;

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
    static String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
    static String valTr = "<tr style='height: 20px'>";
    static String endTr = "</tr>";

    public static  void buildTodayHtml(Collect collect,StringBuilder sb)throws SerException{
        StringBuilder sb_today = new StringBuilder();
        sb_today.append(nameTr);
       List<Field> fields = ClazzUtils.getFields(TaskCollect.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb_today.append("<td>");
            sb_today.append(header.name());
            sb_today.append("</td>");
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
        Map<String,Integer> names = new HashMap<>();
        for(TaskCollect taskCollect :taskCollects){
            names.put( taskCollect.getUsername(),0);
        }
        for(Map.Entry<String,Integer> entry :names.entrySet()){
            for(TaskCollect taskCollect :taskCollects){
                if(entry.getKey().equals(taskCollect.getUsername())){
                    entry.setValue(entry.getValue()+1);
                }
            }
        }
        for(Map.Entry<String,Integer> entry :names.entrySet()){
            if(entry.getValue()>1){
                temp = temp.replaceFirst(valTr+"<td>"+entry.getKey()+"</td>",
                        valTr+"<td rowspan='"+entry.getValue()+"'>"+entry.getKey()+"</td>");
                temp = temp.replaceAll("<td>"+entry.getKey()+"</td>","");
            }
        }
        sb.append(temp);
    }

    public static  void buildTomorrowHtml(Collect collect,StringBuilder sb)throws SerException{
        //构建明天任务
        StringBuilder sb_tomorrow = new StringBuilder();
        sb_tomorrow.append(nameTr);
        List<Field> fields = ClazzUtils.getFields(TomorrowCollect.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (ExcelHeader header : headers) {
            sb_tomorrow.append("<td>");
            sb_tomorrow.append(header.name());
            sb_tomorrow.append("</td>");
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
        Map<String,Integer> names = new HashMap<>();
        for(TomorrowCollect taskCollect :collect.getTomorrowCollects()){
            names.put( taskCollect.getUsername(),0);
        }
        for(Map.Entry<String,Integer> entry :names.entrySet()){
            for(TomorrowCollect taskCollect :collect.getTomorrowCollects()){
                if(entry.getKey().equals(taskCollect.getUsername())){
                    entry.setValue(entry.getValue()+1);
                }
            }
        }
        String temp = sb_tomorrow.toString();
        for(Map.Entry<String,Integer> entry :names.entrySet()){
            if(entry.getValue()>1){
                temp = temp.replaceFirst(valTr+"<td>"+entry.getKey()+"</td>",
                        valTr+"<td rowspan='"+entry.getValue()+"'>"+entry.getKey()+"</td>");
                temp = temp.replaceAll("<td>"+entry.getKey()+"</td>","");
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
    public  static void createHtmlVal(StringBuilder sb, Object object, List<ExcelHeader> headers) throws SerException {
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
