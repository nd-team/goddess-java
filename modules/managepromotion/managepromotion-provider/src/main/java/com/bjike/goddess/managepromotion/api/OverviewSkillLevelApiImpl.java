package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.service.OverviewSkillLevelSer;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能等级情况概览业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("overviewSkillLevelApiImpl")
public class OverviewSkillLevelApiImpl implements OverviewSkillLevelAPI {
    @Autowired
    private OverviewSkillLevelSer overviewSkillLevelSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return overviewSkillLevelSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return overviewSkillLevelSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        return overviewSkillLevelSer.countOverviewSkillLevel(overviewSkillLevelDTO);
    }

    @Override
    public OverviewSkillLevelBO getOne(String id) throws SerException {
        return overviewSkillLevelSer.getOne(id);
    }

    @Override
    public List<OverviewSkillLevelBO> findListOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        return overviewSkillLevelSer.findListOverviewSkillLevel(overviewSkillLevelDTO);
    }

    @Override
    public OverviewSkillLevelBO insertOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        return overviewSkillLevelSer.insertOverviewSkillLevel(overviewSkillLevelTO);
    }

    @Override
    public OverviewSkillLevelBO editOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        return overviewSkillLevelSer.editOverviewSkillLevel(overviewSkillLevelTO);
    }

    @Override
    public void removeOverviewSkillLevel(String id) throws SerException {
        overviewSkillLevelSer.removeOverviewSkillLevel(id);
    }

    @Override
    public OverviewSkillLevelBO findByName(String employeeName) throws SerException {
        return overviewSkillLevelSer.findByName(employeeName);
    }
}