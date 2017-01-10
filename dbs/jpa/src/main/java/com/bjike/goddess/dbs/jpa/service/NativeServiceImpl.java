package com.bjike.goddess.dbs.jpa.service;

import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.dbs.common.service.INativeService;
import com.bjike.goddess.dbs.jpa.constant.FinalCommons;
import com.bjike.goddess.dbs.jpa.utils.CharacterUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-27 15:47]
 * @Description: [原生sql查询请继承该实现类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class NativeServiceImpl<BE> implements INativeService {
    @Autowired
    protected EntityManager entityManager;
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<BE> findBySql(String sql, Class clazz, String[] fields)throws SerException {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object> resultList = nativeQuery.getResultList();
        List<BE> list = new ArrayList<>(resultList.size());
        Method[] all_methods = clazz.getMethods();
        List<Method> methods = new ArrayList<>();
        for (Method m : all_methods) {
            if (m.getName().indexOf("set") != -1) {
                methods.add(m);
            }
        }
        //解析查询结果
        try {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] arr_obj = (Object[]) resultList.get(i);
                Object obj = clazz.newInstance();
                for (int j = 0; j < fields.length; j++) {
                    for (Method m : methods) {
                        if (m.getName().indexOf(CharacterUtil.upperCaseFirst(fields[j])) != -1) {
                            m.invoke(obj, convertDataType(arr_obj[j]));
                            break;
                        }
                    }
                }
                list.add((BE)obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 数据库类型转换
     *
     * @param obj
     * @return
     */
    private Object convertDataType(Object obj) {
        if (null != obj) {
            String simpleName = obj.getClass().getSimpleName();
            String val = obj.toString();
            switch (simpleName) {
                case "Float":
                    obj = Float.parseFloat(val);
                    break;
                case "Double":
                    obj = Double.parseDouble(val);
                    break;
                case "Integer":
                    obj = Integer.parseInt(val);
                    break;
                case "Timestamp":
                    val = val.substring(0, val.length() - 2);
                    obj = LocalDateTime.parse(val, FORMAT);
                    break;

            }
        }
        return obj;
    }

}
