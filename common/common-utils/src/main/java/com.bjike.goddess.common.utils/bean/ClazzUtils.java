package com.bjike.goddess.common.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类工具
 *
 * @Author: [liguiqin]
 * @Date: [2017-05-09 16:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClazzUtils {


    /**
     * 获取某个类的所有属性,包括父类
     *
     * @param clazz
     * @return
     */
    public static List<Field> getFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        while (null != clazz) { //数据源类所有属性（包括父类）
            fields.addAll(Arrays.asList(clazz.getDeclaredFields())); //源对象属性
            clazz = clazz.getSuperclass();
            if (Object.class.equals(clazz) || null == clazz) {
                break;
            }
        }
        return fields;
    }

    /**
     * 获取某个类的所有方法,包括父类
     *
     * @param clazz
     * @return
     */
    public static List<Method> getMethods(Class clazz) {
        List<Method> methods = new ArrayList<>();
        while (null != clazz) { //目标类所有属性（包括父类）
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
            if (Object.class.equals(clazz) || null == clazz) {
                break;
            }
        }
        return methods;
    }


}
