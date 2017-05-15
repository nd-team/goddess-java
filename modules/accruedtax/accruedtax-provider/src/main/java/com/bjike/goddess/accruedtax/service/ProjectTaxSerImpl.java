package com.bjike.goddess.accruedtax.service;

import com.bjike.goddess.accruedtax.bo.ProjectTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.entity.ProjectTax;
import com.bjike.goddess.accruedtax.to.ProjectTaxTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目上税金业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:18 ]
 * @Description: [ 项目上税金业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "accruedtaxSerCache")
@Service
public class ProjectTaxSerImpl extends ServiceImpl<ProjectTax, ProjectTaxDTO> implements ProjectTaxSer {


    @Override
    public Long countProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        Long count = super.count(projectTaxDTO);
        return count;
    }

    @Override
    public ProjectTaxBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        ProjectTax payTax = super.findById(id);
        return BeanTransform.copyProperties(payTax, ProjectTaxBO.class);
    }

    @Override
    public List<ProjectTaxBO> listProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        projectTaxDTO.getSorts().add("createTime=desc");
        List<ProjectTax> list = super.findByCis(projectTaxDTO, true);

        return BeanTransform.copyProperties(list, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectTaxBO addProjectTax(ProjectTaxTO projectTaxTO) throws SerException {

        if(StringUtils.isBlank(projectTaxTO.getPayTaxId())){
            throw  new SerException("应交税金id不能为空");
        }
        ProjectTax projectTax = BeanTransform.copyProperties(projectTaxTO, ProjectTax.class, true);

        projectTax.setRate( projectTax.getActualTax()/projectTax.getPlanTax());
        projectTax.setBalance( projectTax.getActualTax() - projectTax.getPlanTax() );
        projectTax.setCreateTime(LocalDateTime.now());
        super.save(projectTax);
        return BeanTransform.copyProperties(projectTax, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectTaxBO editProjectTax(ProjectTaxTO projectTaxTO) throws SerException {
        ProjectTax projectTax = BeanTransform.copyProperties(projectTaxTO, ProjectTax.class, true);
        ProjectTax cusLevel = super.findById(projectTaxTO.getId());

        BeanUtils.copyProperties(projectTax, cusLevel, "id", "createTime","payTaxId");
        cusLevel.setRate( cusLevel.getActualTax()/cusLevel.getPlanTax());
        cusLevel.setBalance( cusLevel.getActualTax() - cusLevel.getPlanTax() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(projectTax, ProjectTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectTax(String id) throws SerException {

        super.remove(id);
    }

    @Override
    public List<ProjectTaxBO> collectCompany(ProjectTaxDTO projectTaxDTO) throws SerException {
        String project = projectTaxDTO.getProject();
        String[] field = new String[]{"project","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select project , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                " (sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_projecttax where 1=1  ";
        if( StringUtils.isNotBlank(project)){
            sql = " select company , taxDate , targetTax , planTax , actualTax , rate , balance from accruedtax_projecttax where 1=1 and project ='"+project+"' ";
        }
        if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(projectTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(projectTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        sql = sql + " group by project ";
        List<ProjectTaxBO> list = super.findBySql(sql , ProjectTaxBO.class, field);
        return list;
    }

    @Override
    public List<ProjectTaxBO> collectTaxType(ProjectTaxDTO projectTaxDTO) throws SerException {
        String taxType = projectTaxDTO.getTaxType();
        String[] field = new String[]{"taxType","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select taxType , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                " (sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_projecttax where 1=1  ";
        if( StringUtils.isBlank(taxType)){
            sql = " select taxType , taxDate , targetTax , planTax , actualTax , rate , balance from accruedtax_projecttax where 1=1 and taxType ='"+taxType+"' ";
        }
        if( StringUtils.isNotBlank(projectTaxDTO.getStartTime()) && StringUtils.isNotBlank(projectTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(projectTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(projectTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        if( StringUtils.isBlank(taxType)){
            sql = sql + " group by taxType ";
        }
        List<ProjectTaxBO> list = super.findBySql(sql , ProjectTaxBO.class, field);
        return list;
    }

    @Override
    public List<String> listProject() throws SerException {
        String [] field = new String[]{"project"};
        String sql = "select project, 1 from accruedtax_projecttax group by project ";
        List<ProjectTax> list = super.findBySql( sql , ProjectTax.class, field );
        List<String> companyList = list.stream().map(ProjectTax::getProject).collect(Collectors.toList());
        return companyList;
    }

    @Override
    public List<String> listTaxType() throws SerException {
        String [] field = new String[]{"taxType"};
        String sql = "select taxType, 1 from accruedtax_projecttax group by taxType ";
        List<ProjectTax> list = super.findBySql( sql , ProjectTax.class, field );
        List<String> companyList = list.stream().map(ProjectTax::getTaxType).collect(Collectors.toList());
        return companyList;
    }

}