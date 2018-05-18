package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.ProblemTypeBO;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.to.ProblemTypeTO;

import java.util.List;

/**
 * 问题类型业务
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProblemTypeSer extends Ser<ProblemType, ProblemTypeDTO> {
    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(ProblemTypeTO to) throws SerException {

    }

    /**
     * 获取所有可用的问题类型
     *
     * @return
     * @throws SerException
     */
    List<ProblemTypeBO> types() throws SerException;
}
