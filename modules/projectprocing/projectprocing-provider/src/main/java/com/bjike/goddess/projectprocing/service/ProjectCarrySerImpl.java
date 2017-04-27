package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectCarryBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.entity.ProjectCarry;
import com.bjike.goddess.projectprocing.entity.ProjectCarry;
import com.bjike.goddess.projectprocing.to.ProjectCarryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目实施业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectCarrySerImpl extends ServiceImpl<ProjectCarry, ProjectCarryDTO> implements ProjectCarrySer {

    @Override
    public Long countProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        if(StringUtils.isNoneBlank(projectCarryDTO.getArea())){
            projectCarryDTO.getConditions().add(Restrict.like("area",projectCarryDTO.getArea()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getBusinessSubject())){
            projectCarryDTO.getConditions().add(Restrict.like("businessSubject",projectCarryDTO.getBusinessSubject()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getBusinessType())){
            projectCarryDTO.getConditions().add(Restrict.like("businessType",projectCarryDTO.getBusinessType()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getSignCondition())){
            projectCarryDTO.getConditions().add(Restrict.like("signCondition",projectCarryDTO.getSignCondition()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getSignProject())){
            projectCarryDTO.getConditions().add(Restrict.like("signProject",projectCarryDTO.getSignProject()));
        }
        return super.count(projectCarryDTO);
    }

    @Override
    public ProjectCarryBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        ProjectCarry projectSituation = super.findById( id );
        return BeanTransform.copyProperties( projectSituation , ProjectCarryBO.class);
    }
    @Override
    public List<ProjectCarryBO> listProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        if(StringUtils.isNoneBlank(projectCarryDTO.getArea())){
            projectCarryDTO.getConditions().add(Restrict.like("area",projectCarryDTO.getArea()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getBusinessSubject())){
            projectCarryDTO.getConditions().add(Restrict.like("businessSubject",projectCarryDTO.getBusinessSubject()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getBusinessType())){
            projectCarryDTO.getConditions().add(Restrict.like("businessType",projectCarryDTO.getBusinessType()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getSignCondition())){
            projectCarryDTO.getConditions().add(Restrict.like("signCondition",projectCarryDTO.getSignCondition()));
        }if(StringUtils.isNoneBlank(projectCarryDTO.getSignProject())){
            projectCarryDTO.getConditions().add(Restrict.like("signProject",projectCarryDTO.getSignProject()));
        }
        List<ProjectCarry> list = super.findByCis(projectCarryDTO,true);
        return BeanTransform.copyProperties(list,ProjectCarryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectCarryBO addProjectCarry(ProjectCarryTO projectCarryTO) throws SerException {
        ProjectCarry projectCarry = BeanTransform.copyProperties(projectCarryTO,ProjectCarry.class,true);
        projectCarry.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectCarry );
        return BeanTransform.copyProperties(projectCarry,ProjectCarryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectCarryBO editProjectCarry(ProjectCarryTO projectCarryTO) throws SerException {
        if(StringUtils.isBlank(projectCarryTO.getId()) ){
            throw  new SerException("编号不能为空");
        }
        ProjectCarry projectCarry = super.findById( projectCarryTO.getId() );
        if( projectCarry == null ){
            throw  new SerException("编辑失败，您填写的数据可能有错");
        }
        ProjectCarry temp = BeanTransform.copyProperties(projectCarryTO,ProjectCarry.class,true);
        BeanUtils.copyProperties(temp,projectCarry,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做

        projectCarry.setModifyTime(LocalDateTime.now());
        super.update( projectCarry );
        return BeanTransform.copyProperties(projectCarry,ProjectCarryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectCarry(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.remove(id);
    }

    @Override
    public List<ProjectCarryBO> searchListProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        List<ProjectCarry> list = super.findByCis(projectCarryDTO,true);
        return BeanTransform.copyProperties(list,ProjectCarryBO.class);
    }
}