package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目外包洽谈业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.833 ]
 * @Description: [ 项目外包洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectOutsourcingSerCache")
@Service
public class ProjectOutsourcingSerImpl extends ServiceImpl<ProjectOutsourcing, ProjectOutsourcingDTO> implements ProjectOutsourcingSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO saveProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {
        ProjectOutsourcing model = BeanTransform.copyProperties(to, ProjectOutsourcing.class ,true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to,ProjectOutsourcingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO editProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {

        if(!StringUtils.isEmpty(to.getId())){
            ProjectOutsourcing model = super.findById(to.getId());
            BeanTransform.copyProperties(to,model,true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        }else{
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to,ProjectOutsourcingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        if(dto.getCommunicateUser()!=null){
            dto.getConditions().add(Restrict.like("communicateUser",dto.getCommunicateUser()));
        }
        if(dto.getCommunicateObj()!=null){
            dto.getConditions().add(Restrict.like("communicateObj",dto.getCommunicateObj()));
        }
        if(dto.getCommunicateResult()!=null){
            dto.getConditions().add(Restrict.like("communicateResult",dto.getCommunicateResult()));
        }

        List<ProjectOutsourcingBO> pageList = BeanTransform.copyProperties(super.findByPage(dto),ProjectOutsourcingBO.class);
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingBO> collect(ProjectOutsourcingDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        if(dto.getContractInProject()!=null){
            dto.getConditions().add(Restrict.like("contractInProject",dto.getContractInProject()));
        }
        if(dto.getStartTime()!=null){
            dto.getConditions().add(Restrict.gt("createTime",dto.getStartTime()));
        }
        if(dto.getEndTime()!=null){
            dto.getConditions().add(Restrict.lt("createTime",dto.getEndTime()));
        }
        return setCollectField(super.findByPage(dto));

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void setCollectSend(QuartzCycleType cycle) throws SerException {
        // TODO: 17-3-20
    }

    //设置汇总字段
    public List<ProjectOutsourcingBO> setCollectField(List<ProjectOutsourcing> list) throws SerException{
        List<ProjectOutsourcingBO>  boList = BeanTransform.copyProperties(list,ProjectOutsourcingBO.class);

        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;

        if(boList != null && !boList.isEmpty()){
            for(ProjectOutsourcingBO bo : boList){
                if(bo.getProjectResult() == CommunicateResult.COOPERATE){
                    bo.setCooperate(bo.getProjectResult());
                    totalCooperate++;
                }else if(bo.getProjectResult() == CommunicateResult.TRAIL){
                    bo.setTrail(bo.getProjectResult());
                    totalTrail++;
                }else if(bo.getProjectResult() == CommunicateResult.ABANDON){
                    bo.setAbandon(bo.getProjectResult());
                    totalAbandon++;
                }
            }
            Double totalCostBudget = boList.stream().mapToDouble(p -> p.getCostBudget()).sum();

            ProjectOutsourcingBO total = new ProjectOutsourcingBO("合计" , null , null , null , null , null , totalCostBudget , totalCooperate , totalTrail , totalAbandon);
            boList.add(total);
        }

        return  boList;
    }
}