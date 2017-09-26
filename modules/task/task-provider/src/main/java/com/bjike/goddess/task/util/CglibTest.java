package com.bjike.goddess.task.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 09:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CglibTest {
    /**
     *
     *
     SELECT MAX(CASE name WHEN '列1' THEN name ELSE '' END) '列1', MAX(CASE name WHEN '列2' THEN name ELSE '' END) '列2', MAX(CASE name WHEN '列3' THEN name ELSE '' END) '列3', MAX(CASE name WHEN '列4' THEN name ELSE '' END) '列4', MAX(CASE name WHEN '任务内容' THEN name ELSE '' END) '任务内容'
     FROM (
     SELECT name
     FROM task_field
     WHERE tid='c6d23165-278e-4ea0-b917-78d3c6c97eb0'
     ORDER BY seq ASC) a UNION ALL
     SELECT *
     FROM(
     SELECT MAX(CASE name WHEN '列1' THEN value ELSE '' END) '列1', MAX(CASE name WHEN '列2' THEN value ELSE '' END) '列2', MAX(CASE name WHEN '列3' THEN value ELSE '' END) '列3', MAX(CASE name WHEN '列4' THEN value ELSE '' END) '列4', MAX(CASE name WHEN '任务内容' THEN value ELSE '' END) '任务内容'
     FROM((
     SELECT name,value,fid,rid,seq
     FROM (
     SELECT b.*
     FROM task_table a,task_field b
     WHERE a.id='c6d23165-278e-4ea0-b917-78d3c6c97eb0' AND a.id = b.tid AND b.node='0')a,(
     SELECT a.fid,b.id AS rid,c.val AS value
     FROM task_grid a, (
     SELECT *
     FROM task_row
     LIMIT 0,10) b,task_val c
     WHERE b.tid ='c6d23165-278e-4ea0-b917-78d3c6c97eb0' AND a.rid=b.id AND c.id=a.vid) b
     WHERE a.id=b.fid))a,task_row c
     WHERE a.rid=c.id
     GROUP BY rid
     ORDER BY c.seq ASC)b
     *
     *
     * @param args
     * @throws Exception
     */


    public static void main(String[] args) throws Exception{
        HashMap fieldMap = new HashMap();
        fieldMap.put("field1", Class.forName("java.lang.String"));
        fieldMap.put("field2", Class.forName("java.lang.String"));
        fieldMap.put("field3", Class.forName("java.lang.String"));
        CglibBean fieldBean = new CglibBean(fieldMap);
        fieldBean.setValue("field1", "id");
        fieldBean.setValue("field2", "name");
        fieldBean.setValue("field3", "address");

// 设置类成员属性
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
// 生成动态 Bean
        CglibBean bean = new CglibBean(propertyMap);
// 给 Bean 设置值
        bean.setValue("id", new Integer(123));
        bean.setValue("name", "454");
        bean.setValue("address", "789");
        CglibBean bean2 = new CglibBean(propertyMap);
// 给 Bean 设置值
        bean2.setValue("id", new Integer(112));
        bean2.setValue("name", "333");
        bean2.setValue("address", "3332");

// 获得bean的实体
        Object object = bean.getObject();
        Object object2 = bean2.getObject();
        List<Object> objects = new ArrayList<>();
        objects.add(object);
        objects.add(object2);
        TableData data = new TableData(fieldBean.getObject(), objects);
        System.out.println(JSON.toJSONString(data));
    }
}
