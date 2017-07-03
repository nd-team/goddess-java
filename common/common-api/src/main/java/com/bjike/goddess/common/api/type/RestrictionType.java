package com.bjike.goddess.common.api.type;

/**
 * jpa映射条件类型
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RestrictionType {
    /**
     * 相等
     */
    EQ,

    /**
     * 在什么之间
     */
    BETWEEN,
    /**
     * 模糊
     */
    LIKE,
    /**
     * 在什么范围之间
     */
    IN,

    /**
     * 大于
     */
    GT,
    /**
     * 小于
     */
    LT,
    /**
     * 或者
     */
    OR,
    /**
     * 不等于
     */
    NE,
    /**
     * 大于等于
     */
    GTEQ,
    /**
     * 小于等于
     */
    LTEQ,
    /**
     * 为空
     */
    ISNULL,
    /**
     * 不为空
     */
    ISNOTNULL,
    /**
     * 不在xx范围
     */
    NOTIN;


    public static RestrictionType valueOf(Object val) {
        String value = String.valueOf(val);
        RestrictionType type = null;
        switch (value) {
            case "EQ":
                type = RestrictionType.EQ;
                break;
            case "BETWEEN":
                type = RestrictionType.BETWEEN;
                break;
            case "LIKE":
                type = RestrictionType.LIKE;
                break;
            case "IN":
                type = RestrictionType.IN;
                break;
            case "GT":
                type = RestrictionType.GT;
                break;
            case "LT":
                type = RestrictionType.LT;
                break;
            case "GTEQ":
                type = RestrictionType.GTEQ;
                break;
            case "LTEQ":
                type = RestrictionType.LTEQ;
                break;
            case "OR":
                type = RestrictionType.OR;
                break;
            case "NE":
                type = RestrictionType.NE;
                break;
            case "ISNULL":
                type = RestrictionType.ISNULL;
                break;
            case "ISNOTNULL":
                type = RestrictionType.ISNOTNULL;
                break;
            case "NOTIN":
                type = RestrictionType.NOTIN;
                break;
            default:
                type = RestrictionType.EQ;
                break;
        }
        return type;
    }

    public static String getRestrict(RestrictionType type) {
        String typeStr;
        switch (type) {
            case EQ:
                typeStr = "equal";
                break;
            case BETWEEN:
                typeStr = "between";
                break;
            case LIKE:
                typeStr = "like";
                break;
            case IN:
                typeStr = "in";
                break;
            case GT:
                typeStr = "greaterThan";
                break;
            case LT:
                typeStr = "lessThan";
                break;
            case GTEQ:
                typeStr = "greaterThanOrEqualTo";
                break;
            case LTEQ:
                typeStr = "lessThanOrEqualTo";
                break;
            case OR:
                typeStr = "or";
                break;
            case NE:
                typeStr = "notEqual";
                break;
            case ISNULL:
                typeStr = "isNull";
                break;
            case ISNOTNULL:
                typeStr = "isNotNull";
                break;
            default:
                typeStr = "equal";
                break;
        }
        return typeStr;
    }

}
