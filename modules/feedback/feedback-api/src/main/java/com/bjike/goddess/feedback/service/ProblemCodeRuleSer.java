package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.feedback.bo.ProblemCodeRuleBO;
import com.bjike.goddess.feedback.dto.ProblemCodeRuleDTO;
import com.bjike.goddess.feedback.entity.ProblemCodeRule;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemCodeRuleTO;

import java.util.List;

/**
 * 问题编码规则业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblemCodeRuleSer extends Ser<ProblemCodeRule, ProblemCodeRuleDTO> {
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
     * 问题编码规则列表总条数
     */
    default Long count(ProblemCodeRuleDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个问题编码规则
     *
     * @return class ProblemCodeRuleBO
     */
    default ProblemCodeRuleBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 问题编码规则列表
     *
     * @param dto 问题编码规则dto
     * @return class ProblemFeedbackBO
     * @throws SerException
     */
    default List<ProblemCodeRuleBO> list(ProblemCodeRuleDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑问题编码规则
     *
     * @param to 问题编码规则数据to
     * @return class ProblemCodeRuleBO
     * @throws SerException
     */
    default ProblemCodeRuleBO edit(ProblemCodeRuleTO to) throws SerException {
        return null;
    }


}