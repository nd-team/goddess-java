package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.dto.ProjectAcceptanceDTO;
import com.bjike.goddess.projectprocing.entity.ProjectAcceptance;
import com.bjike.goddess.projectprocing.to.ProjectAcceptanceTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目验收情况业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectAcceptanceSerImpl extends ServiceImpl<ProjectAcceptance, ProjectAcceptanceDTO> implements ProjectAcceptanceSer {

    @Override
    public Long countProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return super.count(projectAcceptanceDTO);
    }

    @Override
    public List<ProjectAcceptanceBO> listProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        List<ProjectAcceptance> list = super.findByCis(projectAcceptanceDTO,true);
        return BeanTransform.copyProperties(list,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectAcceptanceBO addProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        ProjectAcceptance projectAcceptance = BeanTransform.copyProperties(projectAcceptanceTO,ProjectAcceptance.class,true);
        projectAcceptance.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectAcceptance );
        return BeanTransform.copyProperties(projectAcceptance,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectAcceptanceBO editProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        if(StringUtils.isBlank(projectAcceptanceTO.getId()) ){
            throw  new SerException("编号不能为空");
        }
        ProjectAcceptance projectAcceptance = super.findById( projectAcceptanceTO.getId() );
        ProjectAcceptance temp = BeanTransform.copyProperties(projectAcceptanceTO,ProjectAcceptance.class,true);
        BeanUtils.copyProperties(temp,projectAcceptance,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.update( projectAcceptance );
        return BeanTransform.copyProperties(projectAcceptance,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectAcceptance(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.remove(id);
    }

    @Override
    public List<ProjectAcceptanceBO> searchListProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        List<ProjectAcceptance> list = super.findByCis(projectAcceptanceDTO,true);
        return BeanTransform.copyProperties(list,ProjectAcceptanceBO.class);
    }
}