package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.enums.CollectType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ScheduleSer {
    default Collect collect(CollectDTO dto) throws SerException {
        return null;
    }
    default String buildCollectHtml(Collect collect, CollectType type) throws SerException {
        return null;
    }

    /**
     * 自定义汇总
     *
     * @return
     * @throws SerException
     */
    default void customizeCollect(Customize customize) throws SerException {
    }

}
