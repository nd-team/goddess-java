package com.bjike.goddess.common.utils.bean;

import com.bjike.goddess.common.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-10 16:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DataTypeUtils {
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");


    /**
     * 数据库类型转换
     *
     * @param val
     * @param type
     * @return
     */
    @SuppressWarnings(value = "all")
    public static Object convertDataType(String val, String type) {
        Object obj = null;
        switch (type) {
            case "Long":
                obj = val;
                break;
            case "String":
                obj = val;
                break;
            case "Double":
                obj = Double.parseDouble(val);
                break;
            case "Float":
                obj = Float.parseFloat(val);
                break;
            case "BigInteger":
                obj = Integer.parseInt(val);
                break;
            case "BigDecimal":
                obj = Double.parseDouble(val);
                break;
            case "Integer":
                obj = Integer.parseInt(val);
                break;
            case "Boolean":
                obj = Boolean.valueOf(val);
                break;
            case "LocalDateTime":
                if (StringUtils.isNumeric(val)) {
                    Date date = new Date(Long.valueOf(val));
                    Instant instant = date.toInstant();
                    ZoneId zoneId = ZoneId.systemDefault();
                    obj = LocalDateTime.ofInstant(instant, zoneId);
                } else {
                    obj = DateUtil.parseDateTime(val);
                }
                break;
            case "LocalTime":
                if (StringUtils.isNumeric(val)) {
                    Date date = new Date(Long.valueOf(val));
                    obj = LocalTime.of(date.getHours(), date.getMinutes());
                } else {
                    obj = DateUtil.parseTime(val);
                }
                break;
            case "LocalDate":
                if (StringUtils.isNumeric(val)) {
                    Date date = new Date(Long.valueOf(val));
                    Instant instant = date.toInstant();
                    ZoneId zoneId = ZoneId.systemDefault();
                    obj = LocalDateTime.ofInstant(instant, zoneId).toLocalDate();
                } else {
                    obj = DateUtil.parseDate(val);
                }
                break;
            default:
                obj = String.valueOf(val);
                break;
        }
        return obj;
    }

}
