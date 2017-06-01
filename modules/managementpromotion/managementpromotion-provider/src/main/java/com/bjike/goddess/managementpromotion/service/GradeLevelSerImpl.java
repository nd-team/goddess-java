package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.bo.GradeLevelBO;
import com.bjike.goddess.managementpromotion.dto.GradeLevelDTO;
import com.bjike.goddess.managementpromotion.entity.GradeLevel;
import com.bjike.goddess.managementpromotion.to.GradeLevelTO;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.bo.HierarchyBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理等级定级业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class GradeLevelSerImpl extends ServiceImpl<GradeLevel, GradeLevelDTO> implements GradeLevelSer {
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private LevelDesignSer levelDesignSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public GradeLevelBO save(GradeLevelTO to) throws SerException {
        GradeLevel gradeLevel = BeanTransform.copyProperties(to, GradeLevel.class, true);
        String grade=levelDesignSer.findGrade(gradeLevel.getClassification(), gradeLevel.getDirection(), gradeLevel.getSkillLevel());
        if (grade==null){
            throw new SerException("获取档次失败，请输入正确的分类，管理方向和技能等级");
        }
        gradeLevel.setGrade(grade);
        gradeLevel.setQuotaJob(gradeLevel.getAllowanceRank() * gradeLevel.getConvertLine());
        gradeLevel.setSubsidySum(gradeLevel.getSubsidyAmount() + gradeLevel.getQuotaJob());
        GradeLevel g = findBySql(gradeLevel.getSystem(), gradeLevel.getClassification(), gradeLevel.getDirection(), gradeLevel.getAllowanceRank());
        if (g != null) {
            gradeLevel.setGrowth(gradeLevel.getSubsidySum() - g.getSubsidySum());
        } else {
            gradeLevel.setGrowth(gradeLevel.getSubsidySum());
        }
        super.save(gradeLevel);
        return BeanTransform.copyProperties(gradeLevel, GradeLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(GradeLevelTO to) throws SerException {
        GradeLevel gradeLevel = super.findById(to.getId());
        if (gradeLevel == null) {
            throw new SerException("对象不存在");
        }
        LocalDateTime a = gradeLevel.getCreateTime();
        gradeLevel = BeanTransform.copyProperties(to, GradeLevel.class, true);
        String grade=levelDesignSer.findGrade(gradeLevel.getClassification(), gradeLevel.getDirection(), gradeLevel.getSkillLevel());
        if (grade==null){
            throw new SerException("获取档次失败，请输入正确的分类，管理方向和技能等级");
        }
        gradeLevel.setGrade(grade);
        gradeLevel.setQuotaJob(gradeLevel.getAllowanceRank() * gradeLevel.getConvertLine());
        gradeLevel.setSubsidySum(gradeLevel.getSubsidyAmount() + gradeLevel.getQuotaJob());
        GradeLevel g = findBySql(gradeLevel.getSystem(), gradeLevel.getClassification(), gradeLevel.getDirection(), gradeLevel.getAllowanceRank());
        if (g != null) {
            gradeLevel.setGrowth(gradeLevel.getSubsidySum() - g.getSubsidySum());
        } else {
            gradeLevel.setGrowth(gradeLevel.getSubsidySum());
        }
        gradeLevel.setCreateTime(a);
        gradeLevel.setModifyTime(LocalDateTime.now());
        super.update(gradeLevel);
    }

    @Override
    public List<GradeLevelBO> find(GradeLevelDTO dto) throws SerException {
        dto.getSorts().add("system=desc");
        dto.getSorts().add("classification=desc");
        dto.getSorts().add("direction=desc");
        List<GradeLevel> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, GradeLevelBO.class);
    }

    @Override
    public GradeLevelBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), GradeLevelBO.class);
    }

    @Override
    public Long count(GradeLevelDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public Set<String> allHierarchys() throws SerException {
        List<HierarchyBO> list = hierarchyAPI.findStatus();
        Set<String> set = new HashSet<String>();
        for (HierarchyBO h : list) {
            set.add(h.getHierarchy());
        }
        return set;
    }

    /**
     * 获取上一个技能等级的具体信息
     *
     * @param allowanceRank 当前记录的职衔补助分数
     * @return class GradeLevel
     * @throws SerException
     */
    private GradeLevel findBySql(String system, String classification, String direction, Integer allowanceRank) throws SerException {
        Integer[] integers = new Integer[]{allowanceRank};
        String[] systems = new String[]{system};
        String[] classifications = new String[]{classification};
        String[] directions = new String[]{direction};
        List<GradeLevel> list = null;
        for (int i = 0; i < integers.length; i++) {
            String sql = "SELECT max(allowanceRank) allowanceRank,skillLevel,subsidySum\n" +
                    "FROM managementpromotion_gradelevel\n" +
                    "WHERE system='" + systems[i] + "' and classification='" + classifications[i] + "' and direction='" + directions[i] + "' and allowanceRank<'" + integers[i] + "'\n" +
                    "GROUP BY skillLevel,subsidySum";
            String[] fields = new String[]{"allowanceRank", "skillLevel", "subsidySum"};
            list = super.findBySql(sql, GradeLevel.class, fields);
        }
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}