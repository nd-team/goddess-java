package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.to.CustomizeTO;

import java.util.Set;

/**
 * 进度
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ScheduleAPI {

    default String collect(CollectDTO dto) throws SerException {

        return null;
    }

    /**
     * 字段对应的值
     * @param to
     * @return
     * @throws SerException
     */
    Set<String> values(CustomizeTO to) throws SerException;
}
