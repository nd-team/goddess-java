package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.CalculateBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingABO;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingCDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;
import com.bjike.goddess.managepromotion.excel.SonPermissionObject;
import com.bjike.goddess.managepromotion.service.SkillGradingSer;
import com.bjike.goddess.managepromotion.to.CalculateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillGradingATO;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return skillGradingSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return skillGradingSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countSkillGrading(SkillGradingCDTO skillGradingCDTO) throws SerException {
        return skillGradingSer.countSkillGrading(skillGradingCDTO);
    }

    @Override
    public SkillGradingABO getOne(String id) throws SerException {
        return skillGradingSer.getOne(id);
    }

    @Override
    public List<SkillGradingABO> findListSkillGrading(SkillGradingADTO skillGradingADTO) throws SerException {
        return skillGradingSer.findListSkillGrading(skillGradingADTO);
    }

    @Override
    public void insertSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
        skillGradingSer.insertSkillGrading(skillGradingATO);
    }

    @Override
    public void editSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
        skillGradingSer.editSkillGrading(skillGradingATO);
    }

    @Override
    public void removeSkillGrading(String id) throws SerException {
        skillGradingSer.removeSkillGrading(id);
    }
    @Override
    public List<CalculateBO> calculate(CalculateTO to) throws SerException {
        return skillGradingSer.calculate(to);
    }
}