package com.bjike.goddess.common.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-21 13:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BeanInfo {
    public BeanInfo(Object source, Object target) {
        this.source = source;
        this.target = target;
    }

    /**
     * 目标源对象
     */
    private Object source;
    /**
     * 目标对象
     */
    private Object target;
    /**
     * 是否转换日期
     */
    private boolean convertDate = false;
    /**
     * 过滤属性
     */
    private String[] excludes;

    /**
     * 只查属性
     */
    private String[] includes;
    /**
     * 目标类属性
     */
    private List<Field> sourceFields;
    /**
     * 源类属性
     */
    private List<Field> targetFields;
    /**
     * 目标类方法
     */
    private List<Method> targetMethods;

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public Class getTargetClass() {
        return this.getTarget().getClass();
    }


    public void setTarget(Object target) {
        this.target = target;
    }

    public boolean isConvertDate() {
        return convertDate;
    }

    public void setConvertDate(boolean convertDate) {
        this.convertDate = convertDate;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public List<Field> getSourceFields() {
        return sourceFields;
    }

    public void setSourceFields(List<Field> sourceFields) {
        this.sourceFields = sourceFields;
    }

    public List<Field> getTargetFields() {
        return targetFields;
    }

    public void setTargetFields(List<Field> targetFields) {
        this.targetFields = targetFields;
    }

    public List<Method> getTargetMethods() {
        return targetMethods;
    }

    public void setTargetMethods(List<Method> targetMethods) {
        this.targetMethods = targetMethods;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }
}
