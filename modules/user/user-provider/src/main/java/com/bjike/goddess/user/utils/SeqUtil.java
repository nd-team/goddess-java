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
    private static final String EMP_ZERO = "100000"; // 员工编号开始与位数
    private static final String SYS_NUMBER = "SYS.NO"; // 系统编号格式
    private static final String SYS_ZERO = "000000"; //系统编号开始与位数

    /**
     * 生成下一个编号
     *
     * @param employeeNumber 最大员工编号
     */
    public static synchronized String generateEmp(String employeeNumber) throws SerException {
        if (StringUtils.isNotBlank(employeeNumber)) {
            int empLength = EMP_NUMBER.length() + EMP_ZERO.length();
            Integer number = Integer.parseInt(StringUtils.substringAfter(employeeNumber, EMP_NUMBER)) + 1;
            Integer length = empLength - (String.valueOf(number).length());
            if (length > 0) {
                employeeNumber = EMP_NUMBER + EMP_ZERO.substring(0, length - EMP_NUMBER.length());
            } else if (0 == length) {
                employeeNumber = EMP_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return employeeNumber + number;
        } else {
            return generateEmp(EMP_NUMBER + EMP_ZERO); //假如为空,则从第一个开始IKE000001
        }

    }

    /**
     * 生成下一个编号
     *
     * @param systemNO 最大系统编号
     */
    public static synchronized String generateSys(String systemNO) throws SerException {
        if (StringUtils.isNotBlank(systemNO)) {
            int sysLength = SYS_NUMBER.length() + SYS_ZERO.length();
            Integer number = Integer.parseInt(StringUtils.substringAfter(systemNO, SYS_NUMBER)) + 1;
            Integer length = sysLength - (String.valueOf(number).length());
            if (length > 0) {
                systemNO = SYS_NUMBER + SYS_ZERO.substring(0, length - SYS_NUMBER.length());
            } else if (0 == length) {
                systemNO = SYS_NUMBER + number;
            } else {
                throw new SerException("系统编号超出长度:" + length);
            }
            return systemNO + number;
        } else {
            return generateSys(SYS_NUMBER + SYS_ZERO); //假如为空,则从第一个开始SYS.NO000001
        }

    }

    public static void main(String[] args) throws SerException {
        System.out.println(generateSys("SYS.NO000002"));
    }

    /**
     * 自动生成编号
     *
     * @param startNumber 初始字母(五位)
     */
    public static synchronized String appAutogeneration(String startNumber) throws SerException {
        return startNumber + "000001"; //自动生成编号
    }
}
