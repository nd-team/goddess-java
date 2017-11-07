package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.VisitScheduleBO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;

/**
 * 拜访日程表业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VisitScheduleAPI {
    default VisitScheduleBO findVisit(VisitScheduleDTO visitScheduleDTO) throws SerException {
        return null;
    }
}