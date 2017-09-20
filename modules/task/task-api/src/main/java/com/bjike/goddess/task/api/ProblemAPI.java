package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemTO;

/**
 * 问题
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProblemAPI {
    /**
     * 问题添加
     *
     * @param to
     * @throws SerException
     */
    default void add(ProblemTO to) throws SerException {

    }

    /**
     * 问题受理
     *
     * @param to
     * @throws SerException
     */
    default void accept(AcceptTO to) throws SerException {

    }
}
