package com.bjike.goddess.attendance.api.overtime;

import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkRestDayBO;
import com.bjike.goddess.attendance.dto.overtime.*;
import com.bjike.goddess.attendance.to.overtime.OverWorkAuditTO;
import com.bjike.goddess.attendance.to.overtime.OverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.overtime.PositionAndDepartVO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface OverWorkAPI {

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



}