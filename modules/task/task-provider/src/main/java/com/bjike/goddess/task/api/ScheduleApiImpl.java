package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.service.ScheduleSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("scheduleApiImpl")
public class ScheduleApiImpl implements ScheduleAPI {
    @Autowired
    private ScheduleSer scheduleSer;

    @Override
    public String collect(CollectDTO dto) throws SerException {
        Collect collect = scheduleSer.collect(dto);
        return scheduleSer.buildCollectHtml(collect,dto.getType());
    }
}
