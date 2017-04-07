package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectSituationBO;
import com.bjike.goddess.projectprocing.dto.ProjectSituationDTO;
import com.bjike.goddess.projectprocing.entity.ProjectSituation;
import com.bjike.goddess.projectprocing.to.ProjectSituationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目情况业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectSituationSerImpl extends ServiceImpl<ProjectSituation, ProjectSituationDTO> implements ProjectSituationSer {



    @Override
    public Long countProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        return super.count(projectSituationDTO);
    }

    @Override
    public List<ProjectSituationBO> listProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        List<ProjectSituation> list = super.findByCis(projectSituationDTO,true);
        return BeanTransform.copyProperties(list,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSituationBO addProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        ProjectSituation projectSituation = BeanTransform.copyProperties(projectSituationTO,ProjectSituation.class,true);
        projectSituation.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectSituation );
        return BeanTransform.copyProperties(projectSituation,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSituationBO editProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        if(StringUtils.isBlank(projectSituationTO.getId()) ){
            throw  new SerException("编号不能为空");
        }
        ProjectSituation projectSituation = super.findById( projectSituationTO.getId() );
        ProjectSituation temp = BeanTransform.copyProperties(projectSituationTO,ProjectSituation.class,true);
        BeanUtils.copyProperties(temp,projectSituation,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.update( projectSituation );
        return BeanTransform.copyProperties(projectSituation,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectSituation(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.remove(id);
    }

    @Override
    public List<ProjectSituationBO> searchListProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        List<ProjectSituation> list = super.findByCis(projectSituationDTO,true);
        return BeanTransform.copyProperties(list,ProjectSituationBO.class);
    }
}