package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managepromotion.bo.*;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.entity.EmployeePromoted;
import com.bjike.goddess.managepromotion.to.*;

import java.util.List;

/**
 * 员工已晋升情况业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmployeePromotedSer extends Ser<EmployeePromoted, EmployeePromotedDTO> {
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
     * 员工已晋升情况列表总条数
     */
    default Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return null;
    }

    /**
     * 一个员工已晋升情况
     *
     * @return class EmployeePromotedBO
     */
    default EmployeePromotedBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 员工已晋升情况
     *
     * @param employeePromotedDTO 员工已晋升情况dto
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况数据to
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return null;
    }

    /**
     * 编辑员工已晋升情况
     *
     * @param employeePromotedTO 员工已晋升情况数据to
     * @return class EmployeePromotedBO
     * @throws SerException
     */
    default EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除员工已晋升情况
     *
     * @param id
     * @throws SerException
     */
    default void removeEmployeePromoted(String id) throws SerException {

    }

    /**
     * 汇总
     *
     * @param to
     * @throws SerException
     */
    default List<CollectBO> collect(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    default List<String> getName() throws SerException {
        return null;
    }

    /**
     * 获取所有状态
     *
     * @throws SerException
     */
    default List<String> getStatus() throws SerException {
        return null;
    }

    /**
     * 技能晋升明细周汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectBO
     * @throws SerException
     */
    default List<SkillPromotionDetailCollectBO> detailWeekCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return null;
    }

    /**
     * 技能晋升明细月汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectBO
     * @throws SerException
     */
    default List<SkillPromotionDetailCollectBO> detailMonthCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return null;
    }

    /**
     * 技能晋升明细累计汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectBO
     * @throws SerException
     */
    default List<SkillPromotionDetailCollectBO> detailTotalCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return null;
    }

    /**
     * 各专业技能日汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectBO
     * @throws SerException
     */
    default List<ProfessionalSkillCollectBO> dayProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return null;
    }

    /**
     * 各专业技能周汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectBO
     * @throws SerException
     */
    default List<ProfessionalSkillCollectBO> weekProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return null;
    }

    /**
     * 各专业技能月汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectBO
     * @throws SerException
     */
    default List<ProfessionalSkillCollectBO> monthProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return null;
    }

    /**
     * 各专业技能累计汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectBO
     * @throws SerException
     */
    default List<ProfessionalSkillCollectBO> totalProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return null;
    }

    /**
     * 人员技能&晋升日汇总
     *
     * @param to to
     * @return class StaffSkillCollectBO
     * @throws SerException
     */
    default List<StaffSkillCollectBO> dayStaffCollect(StaffSkillCollectTO to) throws SerException {
        return null;
    }

    /**
     * 人员技能&晋升周汇总
     *
     * @param to to
     * @return class StaffSkillCollectBO
     * @throws SerException
     */
    default List<StaffSkillCollectBO> weekStaffCollect(StaffSkillCollectTO to) throws SerException {
        return null;
    }

    /**
     * 人员技能&晋升月汇总
     *
     * @param to to
     * @return class StaffSkillCollectBO
     * @throws SerException
     */
    default List<StaffSkillCollectBO> monthStaffCollect(StaffSkillCollectTO to) throws SerException {
        return null;
    }

    /**
     * 人员技能&晋升累计汇总
     *
     * @param to to
     * @return class StaffSkillCollectBO
     * @throws SerException
     */
    default List<StaffSkillCollectBO> totalStaffCollect(StaffSkillCollectTO to) throws SerException {
        return null;
    }
}