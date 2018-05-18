package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.CaseCountBO;
import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchPhoneBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.excel.PunchImportExcel;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 打卡子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PunchSonAPI {
    Boolean sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 打卡
     *
     * @param to
     * @return
     * @throws SerException
     */
    PunchSonBO save(PunchSonTO to) throws SerException;

    /**
     * 查看打卡范围
     *
     * @param longitude 当前经度
     * @param latitude  当前纬度
     * @param area      当前位置
     * @return
     * @throws SerException
     */
    List<String> string(Double longitude, Double latitude, String area) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PunchBO> list(PunchDTO dto) throws SerException;

    /**
     * 移动端列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PunchPhoneBO> phoneList(PunchDTO dto) throws SerException;

    /**
     * 考勤情况汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CaseCountBO> caseCount(PunchDTO dto) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(PunchDTO dto) throws SerException;

    /**
     * 某人当前周从周一至周日请假次数或某个季度分别未打卡次数
     *
     * @param overTimesDTO
     * @return
     * @throws SerException
     */
    default OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO) throws SerException {
        return null;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(PunchDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
     * @throws SerException
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tos
     * @throws SerException
     */
    default void upload(List<PunchImportExcel> tos) throws SerException {
        return;
    }

    /**
     * 当前用户迟到总条数
     *
     * @return
     * @throws SerException
     */
    Long currentUserLateCount() throws SerException;

    /**
     * 当前用户当天是否有打卡
     *
     * @return
     * @throws SerException
     */
    Boolean isPunch(PunchSonTO to) throws SerException;

    /**
     * 根据时间获取当天全部数据
     *
     * @param date date
     * @return class PunchSonBO
     * @throws SerException
     */
    List<PunchSonBO> getPunchSon(String date) throws SerException;

    default List<PunchBO> sonlist(PunchDTO dto) throws SerException{return null;}

   default List<PunchBO> punchList(PunchDTO dto) throws SerException{
        return null;
    }

}