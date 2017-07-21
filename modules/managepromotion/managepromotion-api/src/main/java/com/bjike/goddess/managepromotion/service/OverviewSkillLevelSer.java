package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;

import java.util.List;

/**
 * 技能等级情况概览业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverviewSkillLevelSer extends Ser<OverviewSkillLevel, OverviewSkillLevelDTO> {

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
     * 技能等级情况概览总条数
     */
    default Long countOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        return null;
    }

    /**
     * 一个技能等级情况概览
     *
     * @return class OverviewSkillLevelBO
     */
    default OverviewSkillLevelBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 技能等级情况概览
     *
     * @param overviewSkillLevelDTO 技能等级情况概览dto
     * @return class OverviewSkillLevelBO
     * @throws SerException
     */
    default List<OverviewSkillLevelBO> findListOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        return null;
    }

    /**
     * 添加技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览数据to
     * @return class OverviewSkillLevelBO
     * @throws SerException
     */
    default OverviewSkillLevelBO insertOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        return null;
    }

    /**
     * 编辑技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览数据to
     * @return class OverviewSkillLevelBO
     * @throws SerException
     */
    default OverviewSkillLevelBO editOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除技能等级情况概览
     *
     * @param id
     * @throws SerException
     */
    default void removeOverviewSkillLevel(String id) throws SerException {

    }
}