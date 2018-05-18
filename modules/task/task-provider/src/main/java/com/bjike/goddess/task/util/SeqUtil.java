package com.bjike.goddess.task.util;

import com.bjike.goddess.common.api.exception.SerException;
import org.apache.commons.lang3.StringUtils;

/**
 * 问题序列生成工具
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeqUtil {
    private static final String PROBLEM_NUM = "PBM";
    private static final String PBM_ZERO = "1000000";
    private static final String ACCEPT_NUM = "AET";
    private static final String AET_ZERO = "1000000";


    public static synchronized String genProblemNum(String problemNum) throws SerException {
        if (StringUtils.isNotBlank(problemNum)) {
            int empLength = PROBLEM_NUM.length() + PBM_ZERO.length();
            Integer number = Integer.parseInt(StringUtils.substringAfter(problemNum, PROBLEM_NUM)) + 1;
            Integer length = empLength - (String.valueOf(number).length());
            if (length > 0) {
                problemNum = PROBLEM_NUM + PBM_ZERO.substring(0, length - PROBLEM_NUM.length());
            } else if (0 == length) {
                problemNum = PROBLEM_NUM + number;
            } else {
                throw new SerException("问题编号超出长度:" + length);
            }
            return problemNum + number;
        } else {
            return genProblemNum(PROBLEM_NUM + PBM_ZERO); //假如为空,则从第一个开始IKE000001
        }
    }

    public static synchronized String genAcceptNum(String acceptNum) throws SerException {
        if (StringUtils.isNotBlank(acceptNum)) {
            int empLength = ACCEPT_NUM.length() + AET_ZERO.length();
            Integer number = Integer.parseInt(StringUtils.substringAfter(acceptNum, ACCEPT_NUM)) + 1;
            Integer length = empLength - (String.valueOf(number).length());
            if (length > 0) {
                acceptNum = ACCEPT_NUM + AET_ZERO.substring(0, length - ACCEPT_NUM.length());
            } else if (0 == length) {
                acceptNum = ACCEPT_NUM + number;
            } else {
                throw new SerException("受理编号超出长度:" + length);
            }
            return acceptNum + number;
        } else {
            return genAcceptNum(ACCEPT_NUM + AET_ZERO); //假如为空,则从第一个开始IKE000001
        }
    }


}
