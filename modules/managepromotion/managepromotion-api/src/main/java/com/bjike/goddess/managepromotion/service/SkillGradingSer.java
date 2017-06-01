package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGrading;
import com.bjike.goddess.managepromotion.to.SkillGradingTO;

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
public interface SkillGradingSer extends Ser<SkillGrading, SkillGradingDTO> {

    /**
     * 技能定级列表总条数
     */
    default Long countSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        return null;
    }
    /**
     * 一个技能定级
     * @return class SkillGradingBO
     */
    default SkillGradingBO getOne(String id) throws SerException {return null;}

    /**
     * 技能定级
     *
     * @param skillGradingDTO 技能定级dto
     * @return class SkillGradingBO
     * @throws SerException
     */
    default List<SkillGradingBO> findListSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        return null;
    }

    /**
     * 添加技能定级
     *
     * @param skillGradingTO 技能定级数据to
     * @return class SkillGradingBO
     * @throws SerException
     */
    default SkillGradingBO insertSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        return null;
    }

    /**
     * 编辑技能定级
     *
     * @param skillGradingTO 技能定级数据to
     * @return class SkillGradingBO
     * @throws SerException
     */
    default SkillGradingBO editSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除技能定级
     *
     * @param id
     * @throws SerException
     */
    default void removeSkillGrading(String id) throws SerException {

    }
}