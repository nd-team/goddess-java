package com.bjike.goddess.dbs.jpa.utils;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础类型转换工具类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class PrimitiveUtil {
    public static final Class[] PRIMITIVES = new Class[]{
            String.class,
            Integer.class,
            Long.class,
            Char.class,
            Double.class,
            Float.class,
            Boolean.class,
            LocalDate.class,
            LocalDateTime.class,
            LocalTime.class
    };

    public static Class switchType(Object value) {
        Class clazz = null;
        String typeName = value.getClass().getSimpleName();
        Boolean array = false;
        if (typeName.lastIndexOf("[]") != -1) { //数组
            typeName = typeName.substring(0, typeName.length() - 2);//原始类型
            array = true;
        }
        if (!array) {

            for (Class zz : PRIMITIVES) {
                if ((value.getClass()).equals(zz)) {
                    clazz = zz;
                    break;
                }
            }
            if (value.getClass().isEnum()) {
                clazz = Integer.class;
            }
            clazz = (clazz == null ? String.class : clazz);
        } else {
            for (Class zz : PRIMITIVES) {
                if (zz.getSimpleName().equals(typeName)) {
                    clazz = zz;
                    break;
                }
            }
        }

        return clazz;
    }


    /**
     * 类型转换成对应类型
     */
    public static Object[] convertValuesByType(Object value) {
        Integer val = handleEnum(value); //处理枚举类型(把枚举转换成数值)
        if (null != val) value = val;

        Object[] result = null;
        String typeName = value.getClass().getSimpleName();
        Boolean array = false;
        if (typeName.lastIndexOf("[]") != -1) { //数组
            typeName = typeName.substring(0, typeName.length() - 2);//原始类型
            array = true;
        }

        if (!array) { //非多参数
            result = new Object[]{value};
        } else { //多参数处理
                result = new Object[((Object[]) value).length]; //获得参数个数并初始化Object数组
                Object[] temp_arr = null;
                switch (typeName) {
                    case "String":
                        temp_arr = (String[]) value;
                        break;
                    case "Integer":
                        temp_arr = (Integer[]) value;
                        break;
                    case "Long":
                        temp_arr = (Long[]) value;
                        break;
                    case "Char":
                        temp_arr = (Char[]) value;
                        break;
                    case "Double":
                        temp_arr = (Double[]) value;
                        break;
                    case "Float":
                        temp_arr = (Float[]) value;
                        break;
                    case "Boolean":
                        temp_arr = (Boolean[]) value;
                        break;
                    case "LocalDate":
                        temp_arr = (LocalDate[]) value;
                        break;
                    case "LocalDateTime":
                        temp_arr = (LocalDateTime[]) value;
                        break;
                    case "LocalTime":
                        temp_arr = (LocalTime[]) value;
                        break;
                    default:
                        temp_arr = (String[]) value;
                }
                for (int i = 0; i < result.length; i++) {
                    result[i] = temp_arr[i];
                }
            }
        return result;
    }

    /**
     * 处理枚举类型获得实际code编码
     *
     * @param value
     * @return
     */
    private static Integer handleEnum(Object value) {
        Integer result = null;
        try {
            Class zz = value.getClass();
            if (zz.isEnum()) {
                for (Method method : zz.getMethods()) {
                    if (method.getName().equals("getCode")) {
                        result = (Integer) method.invoke(value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
