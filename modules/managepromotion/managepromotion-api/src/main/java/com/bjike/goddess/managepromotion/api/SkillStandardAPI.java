package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.SkillStandardBO;
import com.bjike.goddess.managepromotion.dto.SkillStandardDTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;

import java.util.List;

/**
 * 技能评定标准业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillStandardAPI {
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
     * 技能评定标准业务列表总条数
     */
    default Long count(SkillStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个技能评定标准业务
     *
     * @return class SkillStandardBO
     */
    default SkillStandardBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 技能评定标准业务
     *
     * @param dto 技能评定标准业务dto
     * @return class SkillStandardBO
     * @throws SerException
     */
    default List<SkillStandardBO> list(SkillStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加技能评定标准业务
     *
     * @param to 技能评定标准业务to
     * @return class SkillStandardBO
     * @throws SerException
     */
    default SkillStandardBO save(SkillStandardTO to) throws SerException {
        return null;
    }

    /**
     * 编辑技能评定标准业务
     *
     * @param to 技能评定标准业务数据to
     * @return class SkillStandardBO
     * @throws SerException
     */
    default SkillStandardBO edit(SkillStandardTO to) throws SerException {
        return null;
    }

    /**
     * 删除技能评定标准业务
     *
     * @param id id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }
}