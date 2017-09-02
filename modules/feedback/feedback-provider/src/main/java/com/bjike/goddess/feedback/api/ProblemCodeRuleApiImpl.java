package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ProblemCodeRuleBO;
import com.bjike.goddess.feedback.dto.ProblemCodeRuleDTO;
import com.bjike.goddess.feedback.service.ProblemCodeRuleSer;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemCodeRuleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题编码规则业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemCodeRuleApiImpl")
public class ProblemCodeRuleApiImpl implements ProblemCodeRuleAPI {
    @Autowired
    private ProblemCodeRuleSer problemCodeRuleSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return problemCodeRuleSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return problemCodeRuleSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(ProblemCodeRuleDTO dto) throws SerException {
        return problemCodeRuleSer.count(dto);
    }

    @Override
    public ProblemCodeRuleBO getOne(String id) throws SerException {
        return problemCodeRuleSer.getOne(id);
    }

    @Override
    public List<ProblemCodeRuleBO> list(ProblemCodeRuleDTO dto) throws SerException {
        return problemCodeRuleSer.list(dto);
    }

    @Override
    public ProblemCodeRuleBO edit(ProblemCodeRuleTO to) throws SerException {
        return problemCodeRuleSer.edit(to);
    }
}