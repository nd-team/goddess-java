package com.bjike.goddess.user.utils;

import com.bjike.goddess.common.api.exception.SerException;
import org.apache.commons.lang3.StringUtils;

/**
 * 序列工具
 *
 * @Author: [liguiqin]
 * @Date: [2017-05-18 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeqUtil {

    private static final String EMP_NUMBER = "IKE"; // 员工编号格式
    private static final String ZERO_NUMBER = "000000"; // 员工编号0位数
    private static final Integer SYS_NUMBER_LENGTH = 9; // 员工编号长度

    /**
     * 生成下一个编号
     *
     * @param employeeNumber 最大员工编号
     */
    public static synchronized String generate(String employeeNumber) throws SerException {
        if (StringUtils.isNotBlank(employeeNumber)) {
            Integer number = Integer.parseInt(StringUtils.substringAfter(employeeNumber, EMP_NUMBER)) + 1;
            Integer length = SYS_NUMBER_LENGTH - (String.valueOf(number).length());
            if (length > 0) {
                employeeNumber = EMP_NUMBER + ZERO_NUMBER.substring(0, length - EMP_NUMBER.length());
            } else if (0 == length) {
                employeeNumber = EMP_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return employeeNumber + number;
        } else {
            return generate(EMP_NUMBER + ZERO_NUMBER); //假如为空,则从第一个开始SYS.NO000001
        }

    }

    public static void main(String[] args) throws SerException {
        System.out.println(generate("IKE000002"));
    }
}
