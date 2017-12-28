package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.EmployeeFunctionLevel;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillGradingTO;

import java.util.List;

/**
 * 员工职能定级业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmployeeFunctionLevelSer extends Ser<EmployeeFunctionLevel, EmployeeFunctionLevelDTO> {
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
     * 员工职能定级列表总条数
     */
    default Long countEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        return null;
    }

    /**
     * 一个员工职能定级
     *
     * @return class EmployeeFunctionLevelBO
     */
    default EmployeeFunctionLevelBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 员工职能定级
     *
     * @param employeeFunctionLevelDTO 员工职能定级dto
     * @return class EmployeeFunctionLevelBO
     * @throws SerException
     */
    default List<EmployeeFunctionLevelBO> findListEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级数据to
     * @return class EmployeeFunctionLevelBO
     * @throws SerException
     */
    default EmployeeFunctionLevelBO insertSkillGrading(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return null;
    }

    /**
     * 编辑员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级数据to
     * @return class EmployeeFunctionLevelBO
     * @throws SerException
     */
    default EmployeeFunctionLevelBO editEmployeeFunctionLevel(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除员工职能定级
     *
     * @param id
     * @throws SerException
     */
    default void removeEmployeeFunctionLevel(String id) throws SerException {

    }
    /**
     * 技能等级情况概览
     *
     * @param employeeFunctionLevelTO
     * @throws SerException
     */
    default OverviewSkillLevelBO skill(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return null;
    }
}