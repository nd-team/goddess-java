package com.bjike.goddess.reportmanagement.utils;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;

import java.time.LocalDate;

/**
 * 工具类
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-19 11:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Utils {
    public static LocalDate tranTime(String time) throws SerException {
        LocalDate a = null;
        try {
            a = DateUtil.parseDate(time);
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        return a;
    }
}
