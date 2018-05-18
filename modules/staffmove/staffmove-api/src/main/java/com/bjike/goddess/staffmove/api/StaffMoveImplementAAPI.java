package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmove.bo.OptionBO;
import com.bjike.goddess.staffmove.bo.StaffMoveCollectBO;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementABO;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementBBO;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementADTO;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementATO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementBTO;

import java.util.List;

/**
 * 人员调动实施业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMoveImplementAAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 人员调动实施列表总条数
     */
    default Long count(StaffMoveImplementADTO dto) throws SerException {
        return null;
    }

    /**
     * 一个人员调动实施b
     *
     * @return class StaffMoveImplementBBO
     */
    default StaffMoveImplementBBO getOneB(String id) throws SerException {
        return null;
    }

    /**
     * 一个人员调动实施a
     *
     * @return class StaffMoveImplementABO
     */
    default StaffMoveImplementABO getOneA(String id) throws SerException {
        return null;
    }

    /**
     * 人员调动实施
     *
     * @param dto 人员调动实施dto
     * @return class StaffMoveImplementABO
     * @throws SerException
     */
    default List<StaffMoveImplementABO> list(StaffMoveImplementADTO dto) throws SerException {
        return null;
    }

    /**
     * 添加人员调动实施
     *
     * @param to 人员调动实施数据to
     * @return class StaffMoveImplementABO
     * @throws SerException
     */
    default StaffMoveImplementABO insert(StaffMoveImplementATO to) throws SerException {
        return null;
    }

    /**
     * 编辑人员调动实施
     *
     * @param to 人员调动实施数据to
     * @throws SerException
     */
    default void edit(StaffMoveImplementATO to) throws SerException {
    }

    /**
     * 人员调动
     *
     * @param to 人员调动实施数据to
     * @throws SerException
     */
    default void staffMove(StaffMoveImplementATO to) throws SerException {
    }

    /**
     * 原决策层审核
     *
     * @throws SerException
     */
    default void originalAudit(StaffMoveImplementBTO to) throws SerException {
    }

    /**
     * 调往决策层审核
     *
     * @throws SerException
     */
    default void transferAudit(StaffMoveImplementBTO to) throws SerException {
    }

    /**
     * 总经办审核
     *
     * @throws SerException
     */
    default void generalAudit(StaffMoveImplementBTO to) throws SerException {
    }

    /**
     * 是否解决需求
     *
     * @throws SerException
     */
    default void solve(StaffMoveImplementBTO to) throws SerException {
    }

    /**
     * 根据id删除人员调动实施
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 人员调动管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default List<StaffMoveCollectBO> weekStaff(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 人员调动管理月汇总
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default List<StaffMoveCollectBO> monthStaff(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 人员调动管理累计汇总
     *
     * @param time
     * @return
     * @throws SerException
     */
    default List<StaffMoveCollectBO> totalStaff(String time) throws SerException {
        return null;
    }

    /**
     * 人员调动管理周图表汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default OptionBO weekStaffFigure(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 人员调动管理月图表汇总
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default OptionBO monthStaffFigure(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 人员调动管理累计图表汇总
     *
     * @param time
     * @return
     * @throws SerException
     */
    default OptionBO totalStaffFigure(String time) throws SerException {
        return null;
    }
}