package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.bo.LevelDesignBO;
import com.bjike.goddess.managementpromotion.dto.LevelDesignDTO;
import com.bjike.goddess.managementpromotion.entity.LevelDesign;
import com.bjike.goddess.managementpromotion.to.LevelDesignTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理分类等级设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class LevelDesignSerImpl extends ServiceImpl<LevelDesign, LevelDesignDTO> implements LevelDesignSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public LevelDesignBO save(LevelDesignTO to) throws SerException {
        LevelDesign levelDesign = BeanTransform.copyProperties(to, LevelDesign.class, true);
        super.save(levelDesign);
        return BeanTransform.copyProperties(levelDesign, LevelDesignBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(LevelDesignTO to) throws SerException {
        LevelDesign levelDesign = super.findById(to.getId());
        if (levelDesign==null){
            throw new SerException("对象不存在");
        }
        LocalDateTime a=levelDesign.getCreateTime();
        levelDesign = BeanTransform.copyProperties(to, LevelDesign.class, true);
        levelDesign.setCreateTime(a);
        levelDesign.setModifyTime(LocalDateTime.now());
        super.update(levelDesign);
    }

    @Override
    public List<LevelDesignBO> find(LevelDesignDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto, true), LevelDesignBO.class);
    }

    @Override
    public LevelDesignBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), LevelDesignBO.class);
    }

    @Override
    public Set<String> allClassifications() throws SerException{
        List<LevelDesign> list=super.findAll();
        Set<String> set=new HashSet<String>();
        for (LevelDesign levelDesign:list){
            set.add(levelDesign.getClassification());
        }
        return set;
    }

    @Override
    public Set<String> allDirections(String classification) throws SerException{
        LevelDesignDTO dto=new LevelDesignDTO();
        dto.getConditions().add(Restrict.eq("classification",classification));
        List<LevelDesign> list=super.findByCis(dto);
        Set<String> set=new HashSet<String>();
        for (LevelDesign l:list){
            set.add(l.getDirection());
        }
        return set;
    }

    @Override
    public Set<String> allSkillLevels(String classification,String direction) throws SerException{
        LevelDesignDTO dto=new LevelDesignDTO();
        dto.getConditions().add(Restrict.eq("classification",classification));
        dto.getConditions().add(Restrict.eq("direction",direction));
        List<LevelDesign> list=super.findByCis(dto);
        Set<String> set=new HashSet<String>();
        for (LevelDesign l:list){
            set.add(l.getSkillLevel());
        }
        return set;
    }

    @Override
    public String findGrade(String classification,String direction,String skillLevel)throws SerException{
        LevelDesignDTO dto=new LevelDesignDTO();
        dto.getConditions().add(Restrict.eq("classification",classification));
        dto.getConditions().add(Restrict.eq("direction",direction));
        dto.getConditions().add(Restrict.eq("skillLevel",skillLevel));
        List<LevelDesign> list=super.findByCis(dto);
        if ((list!=null)&&(list.size()!=0)) {
            return list.get(0).getGrade();
        }
        return null;
    }

    @Override
    public Long count(LevelDesignDTO dto) throws SerException{
        return super.count(dto);
    }
}