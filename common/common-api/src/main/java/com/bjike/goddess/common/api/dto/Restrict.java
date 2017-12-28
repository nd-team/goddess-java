package com.bjike.goddess.common.api.dto;


import com.bjike.goddess.common.api.type.RestrictionType;

import java.io.Serializable;

/**
 * 限定表达式
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-26 08:44]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Restrict implements Serializable {
    private Restrict() {
    }

    public static Condition eq(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.EQ);
    }

    public static Condition between(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.BETWEEN);
    }

    public static Condition like(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.LIKE);
    }

    public static Condition in(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.IN);
    }

    public static Condition gt(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.GT);
    }

    public static Condition lt(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.LT);
    }

    public static Condition gt_eq(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.GTEQ);
    }

    public static Condition lt_eq(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.LTEQ);
    }

    public static Condition or(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.OR);
    }

    public static Condition ne(String filed, Object value) {
        return new Condition(filed, value, RestrictionType.NE);
    }

    public static Condition isNull(String filed) {
        return new Condition(filed, null, RestrictionType.ISNULL);
    }

    public static Condition isNotNull(String filed) {
        return new Condition(filed, null, RestrictionType.ISNOTNULL);
    }

    public static Condition notIn(String field, Object[] values) {
        return new Condition(field, values, RestrictionType.NOTIN);
    }


}
