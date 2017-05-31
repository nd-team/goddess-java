package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.service.SkillGradingSer;
import com.bjike.goddess.managepromotion.to.SkillGradingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 技能定级业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("skillGradingApiImpl")
public class SkillGradingApiImpl implements SkillGradingAPI {

    @Autowired
    private SkillGradingSer skillGradingSer;

    @Override
    public Long countSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        return skillGradingSer.countSkillGrading(skillGradingDTO);
    }

    @Override
    public SkillGradingBO getOne(String id) throws SerException {
        return skillGradingSer.getOne(id);
    }

    @Override
    public List<SkillGradingBO> findListSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        return skillGradingSer.findListSkillGrading(skillGradingDTO);
    }

    @Override
    public SkillGradingBO insertSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        return skillGradingSer.insertSkillGrading(skillGradingTO);
    }

    @Override
    public SkillGradingBO editSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        return skillGradingSer.editSkillGrading(skillGradingTO);
    }

    @Override
    public void removeSkillGrading(String id) throws SerException {
        skillGradingSer.removeSkillGrading(id);
    }
}