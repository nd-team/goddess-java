package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;

import java.util.List;

/**
 * 技能晋升申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SkillPromotionApplyAPI {

    /**
     * 技能晋升申请列表总条数
     */
    default Long countSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        return null;
    }
    /**
     * 一个技能晋升申请
     * @return class SkillPromotionApplyBO
     */
    default SkillPromotionApplyBO getOne(String id) throws SerException {return null;}

    /**
     * 技能晋升申请
     *
     * @param skillPromotionApplyDTO 技能晋升申请dto
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default List<SkillPromotionApplyBO> findListSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        return null;
    }

    /**
     * 申请晋升
     *
     * @param skillPromotionApplyTO 申请晋升数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO insertSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 综合素养补充
     *
     * @param skillPromotionApplyTO 综合素养补充数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO editSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 负责人审核
     *
     * @param skillPromotionApplyTO 负责人审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO headAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 预算审核
     *
     * @param skillPromotionApplyTO 预算审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO budgetAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 预算审核
     *
     * @param skillPromotionApplyTO 预算审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO projectManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }

    /**
     * 规划模块审核
     *
     * @param skillPromotionApplyTO 规划模块审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO planAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return null;
    }
    /**
     * 总经办审核
     *
     * @param skillPromotionApplyTO 总经办审核数据to
     * @return class SkillPromotionApplyBO
     * @throws SerException
     */
    default SkillPromotionApplyBO generalManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        return  null;
    }
}