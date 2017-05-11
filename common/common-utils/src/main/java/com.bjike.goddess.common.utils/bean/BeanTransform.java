package com.bjike.goddess.common.utils.bean;


import com.bjike.goddess.common.utils.date.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 对象转换业务工具, 时间类型会相应转换成字符串类型, 请确保目标源包含同名字符串类型属性
 * 因远程调用不允许出现(LocalDateTime,LocalDate,LocalTime)等类型,时间类型必须转换成字符串(默认)
 * 同样的,保存实体的时候也不能直接出现时间类型,convertDate参数为true时会把字符串转换成相应时间类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-24 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BeanTransform {
    private static final Type[] DATE_TYPES = new Type[]{LocalDateTime.class, LocalDate.class, LocalTime.class};


    /**
     * 对象属性复制list 通过request忽略属性
     *
     * @param sources 对象源
     * @param target  目标对象
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET, SOURCE> List<TARGET> copyProperties(Collection<SOURCE> sources, Class target, HttpServletRequest request) {
        String[] excludes = getExcludes(request);
        String[] includes = getIncludes(request);
        if (null != sources && sources.size() > 0) {
            try {
                Object o_source = sources.iterator().next();
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(o_source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setConvertDate(false);
                beanInfo.setIncludes(includes);
                return copyList(sources, beanInfo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return copyProperties(sources, target, excludes);
    }

    /**
     * 对象属性复制 通过request忽略属性
     *
     * @param source 源对象
     * @param target 目标对象
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET> TARGET copyProperties(Object source, Class target, HttpServletRequest request) {
        if (null != source) {
            try {
                String[] excludes = getExcludes(request);
                String[] includes = getIncludes(request);
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setIncludes(includes);
                o_target = handlerCopyFields(beanInfo);
                return (TARGET) o_target;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }


    /**
     * 复制列表对象属性
     *
     * @param sources  转换对象源列表
     * @param target   目标类
     * @param excludes 过滤字段
     * @return List<TARGET>目标对象列表
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET, SOURCE> List<TARGET> copyProperties(Collection<SOURCE> sources, Class target, String... excludes) {
        if (null != sources && sources.size() > 0) {
            try {
                Object o_source = sources.iterator().next();
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(o_source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setConvertDate(false);
                return copyList(sources, beanInfo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;


    }


    /**
     * 复制列表对象属性
     *
     * @param sources     转换对象源列表
     * @param target      目标类
     * @param excludes    过滤字段
     * @param convertDate 是否处理字符串转换日期 true：字符串转日期 ,false： 日期转字符串
     * @return List<TARGET>目标对象列表
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET, SOURCE> List<TARGET> copyProperties(Collection<SOURCE> sources, Class target, boolean convertDate, String... excludes) {
        if (null != sources && sources.size() > 0) {
            try {
                Object o_target = target.newInstance();
                Object o_source = sources.iterator().next();
                BeanInfo beanInfo = getBeanInfo(o_source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setConvertDate(convertDate);
                return copyList(sources, beanInfo);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }
        return null;

    }


    /**
     * @param source   源对象
     * @param target   目标类
     * @param <TARGET> 目标对象
     * @param excludes 过滤属性
     * @return 目标对象
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET, SOURCE> TARGET copyProperties(SOURCE source, Class target, String... excludes) {
        if (null != source) {
            try {
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(source, o_target);
                beanInfo.setExcludes(excludes);
                o_target = handlerCopyFields(beanInfo);
                return (TARGET) o_target;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;

    }


    /**
     * 对象属性复制
     *
     * @param source   源对象
     * @param target   目标对象
     * @param excludes 过滤字段
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static void copyProperties(Object source, Object target, String... excludes) {
        try {
            BeanInfo beanInfo = getBeanInfo(source, target);
            beanInfo.setExcludes(excludes);
            handlerCopyFields(beanInfo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     * 该方法会判定包含合法日期的字符串并转换到相应属性
     * 对象属性复制
     * 是否处理字符串转换日期
     *
     * @param source      源对象
     * @param target      目标对象
     * @param convertDate 是否处理字符串转换日期 true：字符串转日期 ,false： 日期转字符串
     * @param excludes    过滤字段
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static void copyProperties(Object source, Object target, boolean convertDate, String... excludes) {
        try {
            BeanInfo beanInfo = getBeanInfo(source, target);
            beanInfo.setConvertDate(convertDate);
            beanInfo.setExcludes(excludes);
            handlerCopyFields(beanInfo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * @param source      源对象
     * @param target      目标类
     * @param convertDate 是否处理字符串转换日期 true：字符串转日期 ,false： 日期转字符串
     * @param <TARGET>    目标对象
     * @param excludes    过滤属性
     * @return
     * @throws RuntimeException 反射复制属性类异常,时间格式转换异常
     */
    public static <TARGET, SOURCE> TARGET copyProperties(SOURCE source, Class target, boolean convertDate, String... excludes) {
        if (null != source) {
            try {
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(source, o_target);
                beanInfo.setConvertDate(convertDate);
                beanInfo.setExcludes(excludes);
                o_target = handlerCopyFields(beanInfo);
                return (TARGET) o_target;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;

    }

    private static <TARGET, SOURCE> List<TARGET> copyList(Collection<SOURCE> sources, BeanInfo beanInfo) throws Exception {
        List<TARGET> targets = new ArrayList(sources.size());
        for (SOURCE source : sources) {
            Object target = beanInfo.getTargetClass().newInstance();
            beanInfo.setSource(source);
            beanInfo.setTarget(target);
            target = handlerCopyFields(beanInfo);
            targets.add((TARGET) target);
        }
        return targets;
    }


    /**
     * 处理反射类及复制属性
     *
     * @throws Exception
     */
    private static Object handlerCopyFields(BeanInfo beanInfo) throws Exception {
        String[] excludes = beanInfo.getExcludes();
        String[] includes = beanInfo.getIncludes();
        Object source = beanInfo.getSource();
        Object target = beanInfo.getTarget();
        List<Field> s_fields = beanInfo.getSourceFields(); //源类属性列表
        List<Field> t_fields = beanInfo.getTargetFields();//目标类属性列表
        boolean convertDate = beanInfo.isConvertDate();
        for (Field t_field : t_fields) {
            if (null != excludes) {
                boolean has_ex = excludeField(excludes, t_field);
                if (has_ex) {
                    continue;
                }
            }
            if (null != includes) {
                boolean is_in = includeField(includes, t_field);
                if (!is_in) {
                    continue;
                }
            }
            for (Field s_field : s_fields) {
                if (t_field.getName().equals(s_field.getName())) { //同名属性
                    t_field.setAccessible(true);
                    s_field.setAccessible(true);
                    Object s_val = s_field.get(source);

                    if (null == s_val) {
                        break;
                    }
                    if ("String".equals(s_val.getClass().getSimpleName())) {
                        String val = (String) s_val;
                        if ("".equals(val.trim())) {
                            break;
                        }
                    }
                    if (!convertDate) { //处理字符串转日期
                        Type s_type = s_field.getType();
                        for (Type type : DATE_TYPES) {
                            if (type.equals(s_type)) {
                                s_val = DateUtil.dateToString(s_val);
                                break;
                            }
                        }
                    } else {
                        Type t_type = t_field.getType();
                        if (t_type.equals(LocalDate.class)) {
                            s_val = DateUtil.parseDate(String.valueOf(s_val));
                        } else if (t_type.equals(LocalTime.class)) {
                            s_val = DateUtil.parseTime(String.valueOf(s_val));
                        } else if (t_type.equals(LocalDateTime.class)) {
                            s_val = DateUtil.parseDateTime(String.valueOf(s_val));
                        }
                    }
                    t_field.set(target,s_val);
                    break;
                }
            }
        }
        return target;

    }

    private static boolean excludeField(String[] excludes, Field field) {
        boolean has_ex = false;
        for (String exclude : excludes) {
            if (exclude.equals("*") && !"id".equals(field.getName())) { //过滤除id外的所有属性
                has_ex = true;
                break;
            }
            if (exclude.equals(field.getName())) {
                has_ex = true;
                break;
            }
        }
        return has_ex;
    }

    private static boolean includeField(String[] includes, Field field) {
        for (String include : includes) {
            if (include.equals(field.getName())) { //过滤除id外的所有属性
                return true;
            }

        }
        return false;
    }


    private static String upperCaseFirst(String val) {
        if (!Character.isUpperCase(val.charAt(0))) {
            char[] cs = val.toCharArray();
            cs[0] -= 32;
            return String.valueOf(cs);
        }
        return val;
    }


    private static BeanInfo getBeanInfo(Object source, Object target) {
        BeanInfo beanInfo = new BeanInfo(source, target);
        Class s_clazz = source.getClass();
        Class t_clazz = target.getClass();
        List<Field> s_fields = ClazzUtils.getFields(s_clazz); //源类属性列表
        List<Field> t_fields = ClazzUtils.getFields(t_clazz);//目标类属性列表
        beanInfo.setTargetFields(t_fields);
        beanInfo.setSourceFields(s_fields);
        return beanInfo;
    }

    private static String[] getExcludes(HttpServletRequest request) {
        String ex = request.getParameter("_excludes");
        if (null != ex) {
            return ex.split(",");
        }
        return null;
    }

    private static String[] getIncludes(HttpServletRequest request) {
        String in = request.getParameter("_includes");
        if (null != in) {
            return in.split(",");
        }
        return null;

    }

}
