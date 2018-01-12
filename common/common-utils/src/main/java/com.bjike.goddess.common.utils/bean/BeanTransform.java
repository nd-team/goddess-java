package com.bjike.goddess.common.utils.bean;


import com.bjike.goddess.common.utils.date.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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
                        //true则处理成date格式
                        Type t_type = t_field.getType();
                        if (t_type.equals(LocalDate.class)) {
                            s_val = DateUtil.parseDate(String.valueOf(s_val));
                        } else if (t_type.equals(LocalTime.class)) {
                            s_val = DateUtil.parseTime(String.valueOf(s_val));
                        } else if (t_type.equals(LocalDateTime.class)) {
                            s_val = DateUtil.parseDateTime(String.valueOf(s_val));
                        }
                    }
                    t_field.set(target, s_val);
                    break;
//                    t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName()) || t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName().substring(0, s_field.getName().length() - 2)) || s_field.getName().substring(0, s_field.getName().length() - 2).equals(t_field.getName())
                } /*else if (t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName()) || t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName().substring(0, s_field.getName().length() - 2)) || s_field.getName().substring(0, s_field.getName().length() - 2).equals(t_field.getName())) {
                    *//**
                     * wany
                     *
                     *//*


                    t_field.setAccessible(true);
                    s_field.setAccessible(true);
                    t_field.set(target, copyProperties(s_field.get(source), Class.forName(t_field.getGenericType().toString().replace("class ", ""))));
                } else if ((t_field.getName().indexOf("BOS") != -1 && s_field.getName().endsWith("s")) || (t_field.getName().indexOf("VOS") != -1 && s_field.getName().endsWith("S")) || (s_field.getName().indexOf("TOS") != -1) && t_field.getName().endsWith("s")) {
                    System.out.println(t_field.getGenericType());
                    System.out.println(s_field.getGenericType());
                    t_field.setAccessible(true);
                    s_field.setAccessible(true);
                    List set = (List) s_field.get(source);
                    t_field.set(target, copyProperties(set, Class.forName(t_field.getGenericType().toString().substring(15, t_field.getGenericType().toString().length() - 1))));
                }*/
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

    private static Object wanyhandlerCopyFields(BeanInfo beanInfo) throws Exception {
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
                String[] s_str = s_field.getGenericType().toString().split("\\.");
                String s_name = s_field.getName();
                String s_string = s_str[s_str.length - 1].replace(">", "");
                String[] t_str = t_field.getGenericType().toString().split("\\.");
                String t_name = t_field.getName();
                String t_string = t_str[t_str.length - 1].replace(">", "");
//                System.out.println(s_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
//                System.out.println("第一if");
                if (t_field.getName().equals(s_field.getName())&& t_field.getGenericType().equals(s_field.getGenericType())) { //同名属性
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
                        //true则处理成date格式
                        Type t_type = t_field.getType();
                        if (t_type.equals(LocalDate.class)) {
                            s_val = DateUtil.parseDate(String.valueOf(s_val));
                        } else if (t_type.equals(LocalTime.class)) {
                            s_val = DateUtil.parseTime(String.valueOf(s_val));
                        } else if (t_type.equals(LocalDateTime.class)) {
                            s_val = DateUtil.parseDateTime(String.valueOf(s_val));
                        }
                    }
                    t_field.set(target, s_val);
                    break;
//                    t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName()) || t_field.getName().substring(0, t_field.getName().length() - 2).equals(s_field.getName().substring(0, s_field.getName().length() - 2)) || s_field.getName().substring(0, s_field.getName().length() - 2).equals(t_field.getName())

                } else if (s_name.replace(t_name,"").equals("BO")||t_name.replace(s_name,"").equals("BO")) {
                    /**
                     * wany
                     *
                     */
//                    System.out.println(s_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
//                    System.out.println("第二if");
//                    System.out.println(s_field.getName());
//                    System.out.println(t_field.getName());
                    t_field.setAccessible(true);
                    s_field.setAccessible(true);
                    t_field.set(target, wanycopyProperties(s_field.get(source), Class.forName(t_field.getGenericType().toString().replace("class ", ""))));
//                    System.out.println("第二if结尾");
                } else if (t_string.replace(s_string, "").equals("BO") || s_string.replace(t_string, "").equals("BO")) {
                    if (s_field.getGenericType().toString().split("\\.")[2].split("<")[0].equals("List")) {
//                        System.out.println(s_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
//                        System.out.println("第仨if");
                        t_field.setAccessible(true);
                        s_field.setAccessible(true);
                        List set = (List) s_field.get(source);
                        t_field.set(target, wanycopyProperties(set, Class.forName(t_field.getGenericType().toString().substring(15, t_field.getGenericType().toString().length() - 1))));
                    } else if (s_field.getGenericType().toString().split("\\.")[2].split("<")[0].equals("Set")) {
//                        System.out.println(s_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
//                        System.out.println("第四if");
                        t_field.setAccessible(true);
                        s_field.setAccessible(true);
                        Set set = (Set) s_field.get(source);
//                        System.out.println(t_field.getGenericType().toString().substring(14, t_field.getGenericType().toString().length() - 1));
                        t_field.set(target, wanycopySet(set, Class.forName(t_field.getGenericType().toString().substring(14, t_field.getGenericType().toString().length() - 1))));
                    }
//                    (t_field.getName().indexOf("BOS") != -1 && s_field.getName().endsWith("s")) || (t_field.getName().indexOf("VOS") != -1 && s_field.getName().endsWith("S")) || (s_field.getName().indexOf("BOS") != -1) && t_field.getName().endsWith("s")
//                    System.out.println(t_string);
//                    System.out.println(t_string.replace(s_string,""));
//                    System.out.println(s_string.replace(t_string, ""));
//                    System.out.println("----------------");
//                    System.out.println(t_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
//                    System.out.println(s_string);
//                    System.out.println(t_string.replace(s_string,""));
//                    System.out.println(s_string.replace(t_string, ""));
//                    System.out.println(s_field.getGenericType().toString().split("\\.")[2].split("<")[0]);
                    String[] strings = s_field.getGenericType().toString().split("\\.");
//                    System.out.println(strings[strings.length - 1].replace(">", ""));

                }
            }
        }
        return target;

    }

    private static <TARGET, SOURCE> List<TARGET> wanycopyList(Collection<SOURCE> sources, BeanInfo beanInfo) throws Exception {
        List<TARGET> targets = new ArrayList(sources.size());
        for (SOURCE source : sources) {
            Object target = beanInfo.getTargetClass().newInstance();
            beanInfo.setSource(source);
            beanInfo.setTarget(target);
            target = wanyhandlerCopyFields(beanInfo);
            targets.add((TARGET) target);
        }
        return targets;
    }

    private static <TARGET, SOURCE> Set<TARGET> wanycopySet(Collection<SOURCE> sources, BeanInfo beanInfo) throws Exception {
        Set<TARGET> targets = new HashSet(sources.size());
        for (SOURCE source : sources) {
            Object target = beanInfo.getTargetClass().newInstance();
            beanInfo.setSource(source);
            beanInfo.setTarget(target);
            target = wanyhandlerCopyFields(beanInfo);
            targets.add((TARGET) target);
        }
        return targets;
    }

    public static <TARGET, SOURCE> List<TARGET> wanycopyProperties(Collection<SOURCE> sources, Class target, String... excludes) {
        if (null != sources && sources.size() > 0) {
            try {
                Object o_source = sources.iterator().next();
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(o_source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setConvertDate(false);
                return wanycopyList(sources, beanInfo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    public static <TARGET, SOURCE> Set<TARGET> wanycopySet(Collection<SOURCE> sources, Class target, String... excludes) {
        if (null != sources && sources.size() > 0) {
            try {
                Object o_source = sources.iterator().next();
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(o_source, o_target);
                beanInfo.setExcludes(excludes);
                beanInfo.setConvertDate(false);
                return wanycopySet(sources, beanInfo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    public static <TARGET, SOURCE> TARGET wanycopyProperties(SOURCE source, Class target, String... excludes) {
        if (null != source) {
            try {
                Object o_target = target.newInstance();
                BeanInfo beanInfo = getBeanInfo(source, o_target);
                beanInfo.setExcludes(excludes);
                o_target = wanyhandlerCopyFields(beanInfo);
                return (TARGET) o_target;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;

    }

}
