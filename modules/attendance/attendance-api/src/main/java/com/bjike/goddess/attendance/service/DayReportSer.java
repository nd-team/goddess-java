package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.DayReportBO;
import com.bjike.goddess.attendance.bo.DayReportCountBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.entity.DayReport;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.DayReport.DayReportMailBO;

import java.util.List;

/**
 * 日报业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DayReportSer extends Ser<DayReport, DayReportDTO> {
    Boolean sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 日报列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DayReportBO> list(DayReportDTO dto) throws SerException;

    /**
     * 日报汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    DayReportCountBO dayCount(DayReportDTO dto) throws SerException;

    /**
     * 日报汇总邮件
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DayReportMailBO> dayCountMail(DayReportDTO dto) throws SerException;
}