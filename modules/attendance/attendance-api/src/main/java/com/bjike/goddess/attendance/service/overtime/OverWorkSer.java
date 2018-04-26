package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.bo.overtime.*;
import com.bjike.goddess.attendance.dto.overtime.*;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.attendance.excel.OverWorkImportExcel;
import com.bjike.goddess.attendance.excel.SonPermissionObject;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.OverWorkTO;
import com.bjike.goddess.attendance.vo.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.OverWorkTimesVO;
import com.bjike.goddess.attendance.vo.PositionAndDepartVO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.time.LocalDate;
import java.util.List;

/**
 * 加班业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverWorkSer extends Ser<OverWork, OverWorkDTO> {
    Boolean sonPermission() throws SerException;

    /**
     * 项目经理考勤权限添加
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 加班列表总条数
     */
    default Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取加班
     *
     * @return class OverWorkBO
     */
    default OverWorkBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 加班列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param overWorkTO 加班信息
     * @return class OverWorkBO
     */
    default OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteOverWork(String id) throws SerException {
        return;
    }

    /**
     * 获取地区
     */
    default List<AreaBO> areaList() throws SerException {
        return null;
    }

    /**
     * 获取任务下达人或加班人员
     */
    default List<String> peopleList() throws SerException {
        return null;
    }


    /**
     * 根据加班人员获取职位和部门
     */
    default PositionAndDepartVO getPositAndDepart(String overWorker) throws SerException {
        return null;
    }


    /**
     * 获取加班时长和可休天数
     */
    default OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws SerException {
        return null;
    }


    /**
     * 加班审核列表总条数
     */
    default Long countAudit(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 加班审核列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> listAudit(OverWorkDTO overWorkDTO) throws SerException {
        return null;
    }

    /**
     * 审核加班
     *
     * @param auditTO 加班信息
     * @return class OverWorkBO
     */
    default OverWorkBO auditOverWork(OverWorkAuditTO auditTO) throws SerException {
        return null;
    }


    /**
     * 剩余加班天数列表总条数
     */
    default Long countRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        return null;
    }

    /**
     * 剩余加班天数列表
     *
     * @return class OverWorkRestDayBO
     */
    default List<OverWorkRestDayBO> listRestDay(OverWorkRestDayDTO overWorkRestDayDTO) throws SerException {
        return null;
    }


    /**
     * 我的加班列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> myListOverWork(PhoneMyOverWorkDTO phoneMyOverWorkDTO) throws SerException {
        return null;
    }

    /**
     * 我录入的加班列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> myEntryList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return null;
    }

    /**
     * 待我审核的加班列表
     *
     * @return class OverWorkBO
     */
    default List<OverWorkBO> myAuditList(PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取加班
     *
     * @return class OverWorkBO
     */
    default OverWorkBO getPhoneOneById(String id) throws SerException {
        return null;
    }

    /**
     * 加班汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    OverWorkCountBO outWorkCount(OverWorkDTO dto) throws SerException;

    /**
     * 加班汇总邮件
     * @param dto
     * @return
     * @throws SerException
     */
    List<OverWorkMailBO> outWorkCountMail(OverWorkDTO dto) throws SerException;

    /**
     * 某人某截止时间的剩余加班天数
     * @param name
     * @param endTime
     * @return
     * @throws SerException
     */
    Double overDay(String name, LocalDate endTime) throws SerException;

    //某人当前周从周一至周五加班次数
    default OverWorkTimesVO userOverTimeCollect(OverTimesDTO overTimesDTO ) throws SerException{return null;}

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(OverWorkDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     *
     * @return
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
    default void upload(List<OverWorkImportExcel> tos) throws SerException {
        return;
    }
    /**
     * 当前用户加班总条数
     *
     * @throws SerException
     */
    Long currentUserCount() throws SerException;

    default  Boolean getDepartment(String idFlag) throws  SerException{return null;}

    default  Boolean guideCusPermission(GuidePermissionTO guidePermissionTO) throws  SerException{return null;}

    default List<SonPermissionObject> theSonPermission() throws SerException {
        return null;
    }

    default List<SonPermissionObject> theSonPerDepart() throws SerException{return null;}


}