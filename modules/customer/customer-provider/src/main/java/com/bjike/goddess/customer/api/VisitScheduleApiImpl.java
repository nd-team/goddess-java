package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.VisitScheduleBO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;
import com.bjike.goddess.customer.service.VisitScheduleSer;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 拜访日程表业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("visitScheduleApiImpl")
public class VisitScheduleApiImpl implements VisitScheduleAPI {
    @Autowired
    private VisitScheduleSer visitScheduleSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return visitScheduleSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return visitScheduleSer.guidePermission(guidePermissionTO);
    }

    @Override
    public VisitScheduleBO findVisit(VisitScheduleDTO visitScheduleDTO) throws SerException {
        return visitScheduleSer.findVisit(visitScheduleDTO);
    }
}