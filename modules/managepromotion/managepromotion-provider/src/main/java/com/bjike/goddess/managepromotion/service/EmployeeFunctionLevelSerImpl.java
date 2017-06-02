package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.entity.EmployeeFunctionLevel;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 员工职能定级业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmployeeFunctionLevelSerImpl extends ServiceImpl<EmployeeFunctionLevel, EmployeeFunctionLevelDTO> implements EmployeeFunctionLevelSer {

    @Autowired
    private OverviewSkillLevelSer overviewSkillLevelSer;
    @Override
    public Long countEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        Long count = super.count(employeeFunctionLevelDTO);
        return count;
    }

    @Override
    public EmployeeFunctionLevelBO getOne(String id) throws SerException {
        EmployeeFunctionLevel employeeFunctionLevel = super.findById(id);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    public List<EmployeeFunctionLevelBO> findListEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        List<EmployeeFunctionLevel> employeeFunctionLevels = super.findByCis(employeeFunctionLevelDTO);
        List<EmployeeFunctionLevelBO> employeeFunctionLevelBOS = BeanTransform.copyProperties(employeeFunctionLevels, EmployeeFunctionLevelBO.class);
        return employeeFunctionLevelBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeeFunctionLevelBO insertSkillGrading(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        EmployeeFunctionLevel employeeFunctionLevel = BeanTransform.copyProperties(employeeFunctionLevelTO, EmployeeFunctionLevel.class, true);
        employeeFunctionLevel.setCreateTime(LocalDateTime.now());

        String sql = "select is_subject from managepromotion_employeefunctionlevel where name='' and is_subject=0 ";
        String[] fields = new String[]{"subject"};
        List<EmployeeFunctionLevelBO> levelBOS = super.findBySql(sql, EmployeeFunctionLevelBO.class, fields);

        if (null != levelBOS && levelBOS.size() > 0) {
            throw new SerException("主项只能有一个");
        }
        super.save(employeeFunctionLevel);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeeFunctionLevelBO editEmployeeFunctionLevel(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        EmployeeFunctionLevel employeeFunctionLevel = super.findById(employeeFunctionLevelTO.getId());
        BeanTransform.copyProperties(employeeFunctionLevelTO,employeeFunctionLevel,true);
        employeeFunctionLevel.setModifyTime(LocalDateTime.now());
        String sql = "select is_subject from managepromotion_employeefunctionlevel where name='a' and is_subject=0 ";
        String[] fields = new String[]{"subject"};
        List<EmployeeFunctionLevelBO> levelBOS = super.findBySql(sql, EmployeeFunctionLevelBO.class, fields);

        if (null != levelBOS && levelBOS.size() > 0) {
            throw new SerException("主项只能有一个");
        }
        super.update(employeeFunctionLevel);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    public void removeEmployeeFunctionLevel(String id) throws SerException {
        super.remove(id);

    }
    @Override
    public OverviewSkillLevelBO skill(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {

        EmployeeFunctionLevel employeeFunctionLevel = super.findById(employeeFunctionLevelTO.getId());
        BeanTransform.copyProperties(employeeFunctionLevelTO,employeeFunctionLevel,true);

        OverviewSkillLevel overviewSkillLevel = new OverviewSkillLevel();
        BeanUtils.copyProperties(employeeFunctionLevel,overviewSkillLevel);
        overviewSkillLevel.setPromotedNumber(0);
        if(overviewSkillLevel.getName().equals("")){
            overviewSkillLevel.setPromotedNumber(0+1);
        }
        overviewSkillLevelSer.save(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }
}