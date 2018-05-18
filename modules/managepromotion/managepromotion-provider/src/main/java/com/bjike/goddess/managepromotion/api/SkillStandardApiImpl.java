package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.SkillStandardBO;
import com.bjike.goddess.managepromotion.dto.SkillStandardDTO;
import com.bjike.goddess.managepromotion.entity.SkillStandard;
import com.bjike.goddess.managepromotion.service.SkillStandardSer;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 技能评定标准业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("skillStandardApiImpl")
public class SkillStandardApiImpl implements SkillStandardAPI {
    @Autowired
    private SkillStandardSer skillStandardSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return skillStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return skillStandardSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(SkillStandardDTO dto) throws SerException {
        return skillStandardSer.count(dto);
    }
    @Override
    public SkillStandardBO getOne(String id) throws SerException {
        return skillStandardSer.getOne(id);
    }

    @Override
    public List<SkillStandardBO> list(SkillStandardDTO dto) throws SerException {
        return skillStandardSer.list(dto);
    }


    @Override
    public SkillStandardBO save(SkillStandardTO to) throws SerException {
        return skillStandardSer.save(to);
    }

    @Override
    public SkillStandardBO edit(SkillStandardTO to) throws SerException {
        return skillStandardSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        skillStandardSer.delete(id);

    }
}