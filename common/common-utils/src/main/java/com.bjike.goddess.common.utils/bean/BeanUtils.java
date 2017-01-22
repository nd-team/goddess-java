package com.bjike.goddess.common.utils.bean;

import com.bjike.goddess.common.utils.date.DateUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-22 14:39 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target)  {
        try {
            handleClazz(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(Object source, Object target, String... excludes) {
        try {
            handleClazz(source, target, excludes);
        } catch (Exception e) {
           e.printStackTrace();
        }

    }


    private static void handleClazz(Object source, Object target, String... excludes) throws Exception  {
        Class s_clazz = source.getClass();
        Class t_clazz = target.getClass();
        boolean first =true;
        while (null != t_clazz) { //目标类父类
            if(!first){
                s_clazz = s_clazz.getSuperclass();
                t_clazz = t_clazz.getSuperclass();
                if (Object.class.equals(t_clazz) || null == t_clazz) {
                    break;
                }
            }
            first =false;
            Field[] s_fields = s_clazz.getDeclaredFields();
            Field[] t_fields = t_clazz.getDeclaredFields();
            Method[] methods = t_clazz.getDeclaredMethods();
            for (Field t_field : t_fields) {
                boolean has_ex = false;
                for (String exclude : excludes) {
                    if (exclude.equals(t_field.getName())) {
                        has_ex = true;
                        break;
                    }
                }
                if(has_ex){break;}

                for (Field s_field : s_fields) {
                    if (t_field.getName().equals(s_field.getName())) {
                        t_field.setAccessible(true);
                        s_field.setAccessible(true);
                        Object s_val = s_field.get(source);
                        if (null == s_val) {
                            break;
                        }
                        s_val = convertType(s_field.getType().getSimpleName(), s_val);
                        String methodName = "set" + upperCaseFirst(t_field.getName());
                        for (Method m : methods) {
                            if (m.getName().equals(methodName)) {
                                m.invoke(target, s_val);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private static String upperCaseFirst(String val) {
        if (!Character.isUpperCase(val.charAt(0))) {
            char[] cs = val.toCharArray();
            cs[0] -= 32;
            return String.valueOf(cs);
        }
        return val;
    }

    private static Object convertType(String simpleName, Object val) {
        switch (simpleName) {
            case "LocalDateTime":
                val = String.valueOf(DateUtil.datetimeToString((LocalDateTime) val));
                break;
            case "LocalDate":
                val = String.valueOf(DateUtil.dateToString((LocalDate) val));
                break;
            case "LocalTime":
                val = String.valueOf(DateUtil.timeToString((LocalTime) val));
                break;
        }
        return val;
    }

}
