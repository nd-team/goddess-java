package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能等级情况概览业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class OverviewSkillLevelSerImpl extends ServiceImpl<OverviewSkillLevel, OverviewSkillLevelDTO> implements OverviewSkillLevelSer {

    @Override
    public Long countOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        Long count = super.count(overviewSkillLevelDTO);
        return count;
    }

    @Override
    public OverviewSkillLevelBO getOne(String id) throws SerException {
        OverviewSkillLevel overviewSkillLevel = super.findById(id);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    public List<OverviewSkillLevelBO> findListOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        List<OverviewSkillLevel> overviewSkillLevels = super.findByPage(overviewSkillLevelDTO);
        List<OverviewSkillLevelBO> overviewSkillLevelBOS = BeanTransform.copyProperties(overviewSkillLevels,OverviewSkillLevelBO.class);
        return overviewSkillLevelBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OverviewSkillLevelBO insertOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        OverviewSkillLevel overviewSkillLevel = BeanTransform.copyProperties(overviewSkillLevelTO,OverviewSkillLevel.class,true);
        overviewSkillLevel.setCreateTime(LocalDateTime.now());

        String sql = "SELECT is_subject from managepromotion_overviewskilllevel where is_subject=0 GROUP BY is_subject HAVING count(is_subject)>=5 ";
        String[] fields = new String[]{"subject"};
        List<OverviewSkillLevelBO> skillLevelBOS = super.findBySql(sql, OverviewSkillLevelBO.class, fields);

        if (null != skillLevelBOS && skillLevelBOS.size() > 0) {
            throw new SerException("主项只能有一个");
        }
        if (null != skillLevelBOS && skillLevelBOS.size() > 5) {
            throw new SerException("子项只能有5个");
        }

        super.save(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OverviewSkillLevelBO editOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        OverviewSkillLevel overviewSkillLevel = super.findById(overviewSkillLevelTO.getId());
        BeanTransform.copyProperties(overviewSkillLevelTO,overviewSkillLevel,true);
        overviewSkillLevel.setModifyTime(LocalDateTime.now());
        super.update(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeOverviewSkillLevel(String id) throws SerException {
        super.remove(id);
    }
}