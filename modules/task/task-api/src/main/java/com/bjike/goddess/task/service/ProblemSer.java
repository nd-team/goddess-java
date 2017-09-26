package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.ProblemBO;
import com.bjike.goddess.task.dto.ProblemDTO;
import com.bjike.goddess.task.entity.Problem;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemEditTO;
import com.bjike.goddess.task.to.ProblemTO;

import java.util.List;

/**
 * 问题业务
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProblemSer extends Ser<Problem, ProblemDTO> {

    /**
     * 问题列表
     *
     * @param dto
     * @throws SerException
     */
    default List<ProblemBO> list(ProblemDTO dto) throws SerException {
        return null;
    }

    /**
     * 问题修改
     *
     * @param to
     * @return
     * @throws SerException
     */
    default void edit(ProblemEditTO to) throws SerException {
    }


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
