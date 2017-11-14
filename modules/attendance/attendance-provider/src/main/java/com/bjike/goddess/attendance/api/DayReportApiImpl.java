package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.DayReportBO;
import com.bjike.goddess.attendance.bo.DayReportCountBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.service.DayReportSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日报业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dayReportApiImpl")
public class DayReportApiImpl implements DayReportAPI {
    @Autowired
    private DayReportSer dayReportSer;

    @Override
    public List<DayReportBO> list(DayReportDTO dto) throws SerException {
        return dayReportSer.list(dto);
    }

    @Override
    public DayReportCountBO dayCount(DayReportDTO dto) throws SerException {
        return dayReportSer.dayCount(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return dayReportSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dayReportSer.guidePermission(guidePermissionTO);
    }
}