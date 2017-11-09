package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.FinanceAttendanceBO;
import com.bjike.goddess.attendance.bo.FinanceCountBO;
import com.bjike.goddess.attendance.bo.FinanceCountFieldBO;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.to.FinanceAttendanceTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 财务出勤表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinanceAttendanceAPI {
    Boolean sonPermission() throws SerException;
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 财务出勤列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceAttendanceBO> list(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 申请反馈审批
     *
     * @param to
     * @throws SerException
     */
    void apply(FinanceAttendanceTO to) throws SerException;

    /**
     * 审核列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceAttendanceBO> aduitList(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 审核列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long aduitListNum(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    FinanceAttendanceBO one(String id) throws SerException;

    /**
     * 审核
     *
     * @param to
     * @throws SerException
     */
    void audit(FinanceAttendanceTO to) throws SerException;

    /**
     * 汇总详细字段信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceCountFieldBO> fields(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 财务出勤表汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinanceCountBO> financeCount(FinanceAttendanceDTO dto) throws SerException;

    /**
     * 获取某人当天的请假时长
     *
     * @param name
     * @param date
     * @return
     * @throws SerException
     */
    Double vacateDay(String name, String date) throws SerException;
}