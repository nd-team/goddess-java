package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.CalculateBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingABO;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingCDTO;
import com.bjike.goddess.managepromotion.excel.SonPermissionObject;
import com.bjike.goddess.managepromotion.to.CalculateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillGradingATO;

import java.util.List;

/**
 * 技能定级业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillGradingAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 技能定级列表总条数
     */
    default Long countSkillGrading(SkillGradingCDTO skillGradingCDTO) throws SerException {
        return null;
    }

    /**
     * 一个技能定级
     *
     * @return class SkillGradingABO
     */
    default SkillGradingABO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 技能定级
     *
     * @param skillGradingADTO 技能定级dto
     * @return class SkillGradingABO
     * @throws SerException
     */
    default List<SkillGradingABO> findListSkillGrading(SkillGradingADTO skillGradingADTO) throws SerException {
        return null;
    }

    /**
     * 添加技能定级
     *
     * @param skillGradingATO 技能定级数据to
     * @throws SerException
     */
    default void insertSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
    }

    /**
     * 编辑技能定级
     *
     * @param skillGradingATO 技能定级数据to
     * @throws SerException
     */
    default void editSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
    }

    /**
     * 根据id删除技能定级
     *
     * @param id
     * @throws SerException
     */
    default void removeSkillGrading(String id) throws SerException {

    }

    /**
     * 获取所有技能等级
     *
     * @return class String
     * @throws SerException
     */
    default List<String> getSkillLevel() throws SerException {
        return null;
    }

    /**
     * 计算
     *
     * @param to
     * @throws SerException
     */
    default List<CalculateBO> calculate(CalculateTO to, SkillGradingADTO dto) throws SerException {
        return null;
    }
}