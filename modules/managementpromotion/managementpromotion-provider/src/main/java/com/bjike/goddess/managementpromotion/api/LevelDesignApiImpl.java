package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.LevelDesignBO;
import com.bjike.goddess.managementpromotion.dto.LevelDesignDTO;
import com.bjike.goddess.managementpromotion.service.LevelDesignSer;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelDesignTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 管理分类等级设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("levelDesignApiImpl")
public class LevelDesignApiImpl implements LevelDesignAPI {
    @Autowired
    private LevelDesignSer levelDesignSer;

    @Override
    public LevelDesignBO save(LevelDesignTO to) throws SerException {
        return levelDesignSer.save(to);
    }

    @Override
    public void delete(String id) throws SerException {
        levelDesignSer.delete(id);
    }

    @Override
    public void edit(LevelDesignTO to) throws SerException {
        levelDesignSer.edit(to);
    }

    @Override
    public List<LevelDesignBO> find(LevelDesignDTO dto) throws SerException {
        return levelDesignSer.find(dto);
    }

    @Override
    public LevelDesignBO findByID(String id) throws SerException {
        return levelDesignSer.findByID(id);
    }

    @Override
    public Set<String> allClassifications() throws SerException {
        return levelDesignSer.allClassifications();
    }

    @Override
    public Set<String> allDirections(String classification) throws SerException {
        return levelDesignSer.allDirections(classification);
    }

    @Override
    public Set<String> allSkillLevels(String classification, String direction) throws SerException {
        return levelDesignSer.allSkillLevels(classification, direction);
    }

    @Override
    public String findGrade(String classification, String direction, String skillLevel) throws SerException {
        return levelDesignSer.findGrade(classification, direction, skillLevel);
    }

    @Override
    public Long count(LevelDesignDTO dto) throws SerException {
        return levelDesignSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return levelDesignSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return levelDesignSer.guidePermission(guidePermissionTO);
    }
}