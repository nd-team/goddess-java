package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.*;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.dto.overtime.OverTimesDTO;
import com.bjike.goddess.attendance.entity.PunchSon;
import com.bjike.goddess.attendance.excel.PunchImportExcel;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface PunchSonSer extends Ser<PunchSon, PunchSonDTO> {
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
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(PunchDTO dto) throws SerException;

    /**
     * 考勤情况汇总明细
     * @param dto
     * @return
     * @throws SerException
     */
    List<CaseCountMailBO> caseCountMail(PunchDTO dto) throws SerException;

    /**
     * 考勤情况汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CaseCountBO> caseCount(PunchDTO dto) throws SerException;

    /**
     * 获取某天的补班天数
     *
     * @param date
     * @return
     * @throws SerException
     */
    Double extralWork(String date) throws SerException;

    /**
     * 判断当天是否为节假日
     *
     * @param date
     * @return
     * @throws SerException
     */
    Boolean festival(String date) throws SerException;

    /**
     * 查询某人某一天的加班天数
     *
     * @return
     * @throws SerException
     */
    Double outWorkTime(String name, String date) throws SerException;

    /**
     * 某人当前周从周一至周日请假次数或某个季度分别未打卡次数
     * @param overTimesDTO
     * @return
     * @throws SerException
     */
    default OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO ) throws SerException{return null;}


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
}