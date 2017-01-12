package com.bjike.goddess.common.api.dto;


import com.bjike.goddess.common.api.type.RestrictionType;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-26 08:44]
 * @Description: [限定表达式]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Restrict {
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


}
